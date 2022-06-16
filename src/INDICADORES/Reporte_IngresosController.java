/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import INDICADORES.EstadodeCuentasIndividualController;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import CLASES.Clase_Estado_Individual;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class Reporte_IngresosController implements Initializable {

    bas q = new bas();
    String fechinicio = "", fechafinal = "";
    @FXML
    private DatePicker fin;
    @FXML
    private DatePicker inico;
    @FXML
    private Button btoejecutar;
    @FXML
    private TextField INSTALA;
    @FXML
    private TextField MIGRA;
    @FXML
    private TextField CAMBIO;
    @FXML
    private TextField OTROS;
    @FXML
    private TextField TOTAL;
    @FXML
    private TextField MIGRACIONES;
    @FXML
    private TextField CAMBIODOMICILIO;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inico.setValue(LocalDate.now());
        fin.setValue(LocalDate.now());

        btoejecutar.setOnAction((event) -> {
            datosbusqueda();
            facturasdeinstalaciones();
            facturasdeMigraciones();
            facturasdeCambios();
            busqueda();

        });
    }

    public void busqueda() {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                double depositos = 0, egresos = 0, mes = 0;
                ResultSet Busquedadefacturas = q.tablas("SELECT total FROM [BDAirnet].[dbo].[tvdjFacurascliente] where  formapago='Efectivo' and estado='INACTIVA' and (CONVERT (datetime, fechadepago, 103) between '" + fechinicio + "' and '" + fechafinal + "')");//q.tablas("SELECT codigocontrato,[nuemrofactura],[cliente],[fechadepago],[total] FROM [BDAirnet].[dbo].[tvdjFacurascliente] where formapago='Efectivo' and fechadepago like '%/%' and estado='INACTIVA' and (try_convert (datetime, fechadepago, 103) between '"+fechinicio+"' and '"+fechafinal+"')");
                try {
                    while (Busquedadefacturas.next()) {

                        mes = mes + q.ConvertidorStringaDouble(Busquedadefacturas.getString("total").replace(" ", ""));
                    }
                    depositos = facturas_Depositos();
                    egresos = salidadedinero();
                    INSTALA.setText(q.FormatoDecimal(mes-q.ConvertidorStringaDouble(OTROS.getText())-q.ConvertidorStringaDouble(MIGRACIONES.getText())-q.ConvertidorStringaDouble(CAMBIODOMICILIO.getText())));
                    MIGRA.setText(q.FormatoDecimal(depositos));
                    CAMBIO.setText(q.FormatoDecimal(egresos));
                    TOTAL.setText(q.FormatoDecimal(
                    q.ConvertidorStringaDouble(INSTALA.getText())+q.ConvertidorStringaDouble(MIGRA.getText())+
                    q.ConvertidorStringaDouble(OTROS.getText())+q.ConvertidorStringaDouble(MIGRACIONES.getText())+q.ConvertidorStringaDouble(CAMBIODOMICILIO.getText())
                     -q.ConvertidorStringaDouble(CAMBIO.getText())
                    ));

                } catch (SQLException ex) {
                    Logger.getLogger(Reporte_IngresosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }

        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }
    ResultSet facturins;

    public void facturasdeinstalaciones() {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {

                double vt = 0;
                String[] FechaDeconpuesta = fechinicio.split("/");
                String[] FechaFinalDes = fechafinal.split("/");
                int diainico = Integer.parseInt(FechaDeconpuesta[0]);
                int diaFinal = Integer.parseInt(FechaFinalDes[0]);
                int mesinicial = Integer.parseInt(FechaDeconpuesta[1]);
                int mesfinal = Integer.parseInt(FechaFinalDes[1]);

                if (mesinicial == mesfinal) {
                    String CONDICION = "(";
                    for (int i = diainico; i <= diaFinal; i++) {
                        if (i == diaFinal) {
                            CONDICION= CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(mesfinal) + "/"+q.ObtnerAñoActual()+"' ) and detalle LIKE '%INSTALACION%' ");
                        } else {
                            CONDICION= CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(mesfinal) + "/"+q.ObtnerAñoActual()+"'")+(" or ");
                        }

                    }

                    facturins = q.tablas("SELECT  [valortotal]  FROM [BDAirnet].[dbo].[VistaOtrosPagos]\n"
                            + "   where " + CONDICION + "  order by nuemrofactura asc");
                    try {
                        while (facturins.next()) {
                            if (!facturins.getString("valortotal").isEmpty()) {
                                vt = vt + q.ConvertidorStringaDouble(facturins.getString("valortotal"));
                            }

                        }

                    } catch (SQLException ex) {
                        q.notificaciones("Error de Conexion ", "I");
                    }

                } else if (mesinicial < mesfinal) {
                    String CONDICION = " ( ";
                    int axu = inico.getValue().lengthOfMonth();
                    for (int j = mesinicial; j <= mesfinal; j++) {

                        for (int i = diainico; i <= axu; i++) {

                            if (j == mesfinal && i == diaFinal) {
                                CONDICION=CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(j) + "/"+q.ObtnerAñoActual()+"' ) and detalle LIKE '%INSTALACION%' ");
                                
                            } else {
                                CONDICION=CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(j) + "/"+q.ObtnerAñoActual()+"'")+(" or ");
                                
                            }

                        }
                        diainico = 1;
                        axu = diaFinal;

                    }

                    facturins = q.tablas("SELECT  [valortotal]  FROM [BDAirnet].[dbo].[VistaOtrosPagos]\n"
                            + "   where " + CONDICION + "  order by nuemrofactura asc");

                    try {
                        while (facturins.next()) {
                            if (!facturins.getString("valortotal").isEmpty()) {
                                vt = vt + q.ConvertidorStringaDouble(facturins.getString("valortotal"));
                            }
                        }

                    } catch (SQLException ex) {
                        q.notificaciones("Error de Conexion ", "I");
                    }

                } else if (mesinicial > mesfinal) {

                }

                /*      ResultSet facturins = q.tablas("SELECT  [valortotal]  FROM [BDAirnet].[dbo].[VistaOtrosPagos]\n" +
"   where fechadepago  ='02/08/2021'  order by nuemrofactura asc");/*("SELECT  dbo.detallesfacxtura.numerofactura, dbo.tvdjFacurascliente.nuemrofactura, dbo.detallesfacxtura.detalle, dbo.tvdjFacurascliente.estado, dbo.detallesfacxtura.Estado AS EstadoDetadlle, \n"
                        + "  dbo.detallesfacxtura.valortotal, dbo.tvdjFacurascliente.cliente, dbo.tvdjFacurascliente.formapago, dbo.tvdjFacurascliente.Pagado\n"
                        + "FROM   dbo.detallesfacxtura INNER JOIN\n"
                        + "   dbo.tvdjFacurascliente ON dbo.detallesfacxtura.codigo = dbo.tvdjFacurascliente.codigocontrato AND dbo.detallesfacxtura.numerofactura = dbo.tvdjFacurascliente.nuemrofactura\n"
                        + "\n"
                        + "where ( dbo.detallesfacxtura.detalle like'%INSTALACION%' or dbo.detallesfacxtura.detalle like'%MIGRA%' OR dbo.detallesfacxtura.detalle like'%DOMICILIO%') and fechadepago between '" + fechinicio + "' and '" + fechafinal + "' order by dbo.tvdjFacurascliente.nuemrofactura asc");*/
                OTROS.setText(q.FormatoDecimal(vt));

                return null;
            }

        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }

    public String Completar(int X) {
        if (X > 0 && X < 10) {
            String Z = String.valueOf(X);
            return Z = "0" + Z;

        }
        return String.valueOf(X);
    }

    public void datosbusqueda() {
        LocalDate iniciobusq, finbusq;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Calendar fecha1 = Calendar.getInstance();
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        fechinicio = f.format(fecha1.getTime());
        fechafinal = f.format(fecha1.getTime());
        iniciobusq = inico.getValue();
        fechinicio = String.valueOf(iniciobusq.format(formatter));
        finbusq = fin.getValue();
        fechafinal = String.valueOf(finbusq.format(formatter));
    }

    public float salidadedinero() {
        float otro = 0;
        ResultSet st = q.tablas("SELECT [cantidad] FROM [BDAirnet].[dbo].[tvdjSalidadedinero] where fechasalida between ' " + fechinicio + " 00:00:00:000' and '" + fechafinal + "  23:59:00:000'");
        try {
            while (st.next()) {
                otro = otro + st.getFloat("cantidad");
            }
            return otro;
        } catch (SQLException ex) {
            Logger.getLogger(Clase_Estado_Individual.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public float facturas_Depositos() {

        float domicilio = 0;
        ResultSet facturasdeposito = q.tablas("SELECT total FROM [BDAirnet].[dbo].[tvdjFacurascliente] where  formapago='Otros' and estado='INACTIVA' and (CONVERT (datetime, fechadepago, 103) between '" + fechinicio + "' and '" + fechafinal + "')");
        try {
            while (facturasdeposito.next()) {
                domicilio = domicilio + facturasdeposito.getFloat("total");
            }
            return domicilio;
        } catch (SQLException ex) {
            Logger.getLogger(EstadodeCuentasIndividualController.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    ResultSet Migra;
    public void facturasdeMigraciones() {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {

                double vt = 0;
                String[] FechaDeconpuesta = fechinicio.split("/");
                String[] FechaFinalDes = fechafinal.split("/");
                int diainico = Integer.parseInt(FechaDeconpuesta[0]);
                int diaFinal = Integer.parseInt(FechaFinalDes[0]);
                int mesinicial = Integer.parseInt(FechaDeconpuesta[1]);
                int mesfinal = Integer.parseInt(FechaFinalDes[1]);

                if (mesinicial == mesfinal) {
                    String CONDICION = "(";
                    for (int i = diainico; i <= diaFinal; i++) {
                        if (i == diaFinal) {
                            CONDICION= CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(mesfinal) + "/"+q.ObtnerAñoActual()+"' ) and detalle LIKE '%MIGRA%' ");
                        } else {
                            CONDICION= CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(mesfinal) + "/"+q.ObtnerAñoActual()+"'")+(" or ");
                        }

                    }
                    Migra = q.tablas("SELECT  [valortotal]  FROM [BDAirnet].[dbo].[VistaOtrosPagos]\n"
                            + "   where " + CONDICION + "  order by nuemrofactura asc");
                    try {
                        while (Migra.next()) {
                            if (!Migra.getString("valortotal").isEmpty()) {
                                vt = vt + q.ConvertidorStringaDouble(Migra.getString("valortotal"));
                            }

                        }

                    } catch (SQLException ex) {
                        q.notificaciones("Error de Conexion ", "I");
                    }

                } else if (mesinicial < mesfinal) {
                    String CONDICION = "(";
                    int axu = inico.getValue().lengthOfMonth();
                    for (int j = mesinicial; j <= mesfinal; j++) {

                        for (int i = diainico; i <= axu; i++) {

                            if (j == mesfinal && i == diaFinal) {
                                CONDICION=CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(j) + "/"+q.ObtnerAñoActual()+"' ) and detalle LIKE '%MIGRA%' ");
                                
                            } else {
                                CONDICION=CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(j) + "/"+q.ObtnerAñoActual()+"'")+(" or ");
                                
                            }

                        }
                        diainico = 1;
                        axu = diaFinal;

                    }
                    Migra = q.tablas("SELECT  [valortotal]  FROM [BDAirnet].[dbo].[VistaOtrosPagos]\n"
                            + "   where " + CONDICION + "  order by nuemrofactura asc");

                    try {
                        while (Migra.next()) {
                            if (!Migra.getString("valortotal").isEmpty()) {
                                vt = vt + q.ConvertidorStringaDouble(Migra.getString("valortotal"));
                            }
                        }

                    } catch (SQLException ex) {
                        q.notificaciones("Error de Conexion ", "I");
                    }

                } else if (mesinicial > mesfinal) {

                }
             MIGRACIONES.setText(q.FormatoDecimal(vt));

                return null;
            }

        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }
    ResultSet CAMBIOS;
    public void facturasdeCambios() {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {

                double vt = 0;
                String[] FechaDeconpuesta = fechinicio.split("/");
                String[] FechaFinalDes = fechafinal.split("/");
                int diainico = Integer.parseInt(FechaDeconpuesta[0]);
                int diaFinal = Integer.parseInt(FechaFinalDes[0]);
                int mesinicial = Integer.parseInt(FechaDeconpuesta[1]);
                int mesfinal = Integer.parseInt(FechaFinalDes[1]);

                if (mesinicial == mesfinal) {
                    String CONDICION = "(";
                    for (int i = diainico; i <= diaFinal; i++) {
                        if (i == diaFinal) {
                            CONDICION= CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(mesfinal) + "/"+q.ObtnerAñoActual()+"' ) and detalle LIKE '%CAMBI%' ");
                        } else {
                            CONDICION= CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(mesfinal) + "/"+q.ObtnerAñoActual()+"'")+(" or ");
                        }

                    }
                    CAMBIOS = q.tablas("SELECT  [valortotal]  FROM [BDAirnet].[dbo].[VistaOtrosPagos]\n"
                            + "   where " + CONDICION + "  order by nuemrofactura asc");
                    try {
                        while (CAMBIOS.next()) {
                            if (!CAMBIOS.getString("valortotal").isEmpty()) {
                                vt = vt + q.ConvertidorStringaDouble(CAMBIOS.getString("valortotal"));
                            }

                        }

                    } catch (SQLException ex) {
                        q.notificaciones("Error de Conexion ", "I");
                    }

                } else if (mesinicial < mesfinal) {
                    String CONDICION = "( ";
                    int axu = inico.getValue().lengthOfMonth();
                    for (int j = mesinicial; j <= mesfinal; j++) {

                        for (int i = diainico; i <= axu; i++) {

                            if (j == mesfinal && i == diaFinal) {
                                CONDICION=CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(j) + "/"+q.ObtnerAñoActual()+"' ) and detalle LIKE '%CAMBI%' ");
                                
                            } else {
                                CONDICION=CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(j) + "/"+q.ObtnerAñoActual()+"'")+(" or ");
                                
                            }

                        }
                        diainico = 1;
                        axu = diaFinal;

                    }
                    CAMBIOS = q.tablas("SELECT  [valortotal]  FROM [BDAirnet].[dbo].[VistaOtrosPagos]\n"
                            + "   where " + CONDICION + "  order by nuemrofactura asc");

                    try {
                        while (CAMBIOS.next()) {
                            if (!CAMBIOS.getString("valortotal").isEmpty()) {
                                vt = vt + q.ConvertidorStringaDouble(CAMBIOS.getString("valortotal"));
                            }
                        }

                    } catch (SQLException ex) {
                        q.notificaciones("Error de Conexion ", "I");
                    }

                } else if (mesinicial > mesfinal) {

                }
             CAMBIODOMICILIO.setText(q.FormatoDecimal(vt));

                return null;
            }

        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }
}
