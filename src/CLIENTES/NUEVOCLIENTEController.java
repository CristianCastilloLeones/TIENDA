/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLIENTES;

import java.io.File;

import java.net.URL;
import java.sql.Date;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import CLASES.VerificarIP;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class NUEVOCLIENTEController implements Initializable {

    String tipodedocumento = "";
    String tipoista = "";
    int discayter = 1, tubo2 = 0;

    boolean dicapt = false;
    int dia, mes, año;
    bas q = new bas();
    final ToggleGroup group = new ToggleGroup();
    String fci = "";
    @FXML
    private Tab datosgenerales;
    @FXML
    private TextField nombres;
    @FXML
    private TextField ruc;
    @FXML
    private TextField direccion;
    @FXML
    private TextField telefo1;
    @FXML
    private TextField telefono1;
    @FXML
    private TextField correo;
    @FXML
    private Button next1;
    @FXML
    private Tab descripcionplan;
    @FXML
    private Button anterior2;
    @FXML
    private Button next2;
    @FXML
    private Tab formapago;
    @FXML
    private Button guardar;
    @FXML
    private ComboBox<String> nodo;
    @FXML
    private ComboBox<String> plan;
    @FXML
    private TextField detalle;
    @FXML
    private TextField costoinstalacion;
    @FXML
    private TextField ip4;
    @FXML
    private DatePicker fechainstalacion;
    @FXML
    private TextField ponmac;
    @FXML
    private TextField costomensual;
    @FXML
    private ImageView detallesplan;
    @FXML
    private TabPane vistas;
    @FXML
    private ComboBox<String> tipopago;
    @FXML
    private ComboBox<String> diapago;
    @FXML
    private ComboBox<String> creacionfact;
    @FXML
    private ComboBox<String> tiempoespera;
    @FXML
    private RadioButton fb;
    @FXML
    private RadioButton re;
    @FXML
    private ComboBox<String> ciudad;
    @FXML
    private CheckBox descuentoporterceraedad;
    @FXML
    private CheckBox descuentopordiscapacidad;
    @FXML
    private Pane materialesadicionales;
    @FXML
    private CheckBox tubo;
    @FXML
    private CheckBox cheque;
    @FXML
    private CheckBox tarjeta;
    @FXML
    private CheckBox efectivo;
    @FXML
    private TextField apellidos;
    @FXML
    private CheckBox pasaporte;
    @FXML
    private ImageView imgCargar;
    @FXML
    private CheckBox descuentoporPromo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        imgCargar.setVisible(false);

        nombres.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {

                apellidos.requestFocus();
            }
        });
        apellidos.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
                ruc.requestFocus();
            }
        });
        ruc.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {

                if (q.VerificarCedula(ruc.getText().trim())) {
                    direccion.requestFocus();
                    next1.setDisable(false);
                    if (q.ExistenciadeCliente(ruc.getText().trim())) {
                        q.notificaciones("El cliente ya esta Registrado y esta Desactivado", "I");
                        next1.setDisable(true);
                    }

                } else {
                    q.notificaciones("Cedula Invalida verifique para continuar", "I");
                    next1.setDisable(true);
                }

            }
        });
        direccion.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {

                telefo1.requestFocus();
            }
        });
        telefo1.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {

                telefono1.requestFocus();
            }
        });
        telefono1.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
                correo.requestFocus();
            }
        });
        nombres.setOnKeyTyped((event) -> {
            q.IngresarSoloLetras(event.getCharacter().charAt(0), event);
        });

        ruc.setOnKeyTyped((event) -> {
            try {

                if (!pasaporte.isSelected()) {
                    q.IngresarSoloNumeros(event.getCharacter().charAt(0), event);
                }

            } catch (Exception ex) {
            }
        });
        telefo1.setOnKeyTyped((event) -> {
            q.IngresarSoloNumeros(event.getCharacter().charAt(0), event);
        });
        telefono1.setOnKeyTyped((event) -> {

            q.IngresarSoloNumeros(event.getCharacter().charAt(0), event);
        });
        //   imprmir.setOnAction((event) -> {
        //     impr();
        //  });
        tipopago.getItems().addAll("Deposito", "Efectivo", "Tarjeta de Credito");
        diapago.getItems().addAll("Dia-1", "Dia-2", "Dia-3", "Dia-4", "Dia-5", "Dia-6", "Dia-7", "Dia-8", "Dia-9", "Dia-10", "Dia-11", "Dia-12", "Dia-13", "Dia-14", "Dia-15");
        creacionfact.getItems().addAll("Dia-1", "Dia-2", "Dia-3", "Dia-4", "Dia-5");
        tiempoespera.getItems().addAll("Dia-1", "Dia-2", "Dia-3", "Dia-4", "Dia-5", "Dia-6", "Dia-7", "Dia-8", "Dia-9", "Dia-10", "Dia-11", "Dia-12", "Dia-13", "Dia-14", "Dia-15");
        q.consulta = q.tablas("SELECT [NOMBRE] FROM [BDAirnet].[dbo].[TBCANTON]");
        try {
            while (q.consulta.next()) {
                ciudad.getItems().add(q.consulta.getString("NOMBRE"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NUEVOCLIENTEController.class.getName()).log(Level.SEVERE, null, ex);
        }

        fb.setToggleGroup(group);
        re.setToggleGroup(group);

        plan.setOnAction((event) -> {

            // q.SentenciaSQL = ("SELECT [NOMBRE],[COMPARTICION],[DESCARGA],[SUBIDA],[CONSUMO],[INSTALACION],[ANCHOBANDA],[CANTDISPOSITIVOS] FROM [BDAirnet].[dbo].[View_ListaPlanes] where NOMBRE ='" + plan.getSelectionModel().getSelectedItem() + "'");
            q.consulta = q.tablas("SELECT [NOMBRE],[COMPARTICION],[DESCARGA],[SUBIDA],[CONSUMO],[INSTALACION],[ANCHOBANDA],[CANTDISPOSITIVOS] FROM [BDAirnet].[dbo].[View_ListaPlanes] where NOMBRE ='" + plan.getSelectionModel().getSelectedItem() + "'");
            try {
                while (q.consulta.next()) {
                    costoinstalacion.setText(q.consulta.getString("INSTALACION"));
                    costomensual.setText(q.consulta.getString("CONSUMO"));
                    detalle.setText("Comparticion: " + q.consulta.getString("COMPARTICION")
                            + " - Descarga:" + q.consulta.getString("DESCARGA")
                            + " - Subida " + q.consulta.getString("SUBIDA")
                            + " - No Dispositivos : " + q.consulta.getString("CANTDISPOSITIVOS"));
                    if (plan.getSelectionModel().getSelectedItem().contains("100")) {
                        q.consulta = q.tablas("SELECT top (1) pon FROM [BDAirnet].[dbo].[tvproductosdj] where producto = 'ONT FIBRA OPTICA MIGRACION' and estado ='Inactivo'");
                        while (q.consulta.next()) {
                            ponmac.setText(q.consulta.getString("pon").replace(" ", ""));
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(NUEVOCLIENTEController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ip4.setText(q.NuevaIP(plan.getSelectionModel().getSelectedItem(), false));
        });

        guardar.setOnAction((event) -> {
            GuardarTodo(event);
        });
        fb.setOnMouseClicked((event) -> {

            materialesadicionales.setDisable(true);
            try {
                cargarvalores();
                verificarproductos("ONT FIBRA OPTICA");
                q.consulta = q.tablas("SELECT top (1) pon FROM [BDAirnet].[dbo].[tvproductosdj] where producto = 'ONT FIBRA OPTICA' and estado ='Inactivo'");

                while (q.consulta.next()) {
                    ponmac.setText(q.consulta.getString("pon").replace(" ", ""));
                }
            } catch (SQLException ex) {
                Logger.getLogger(NUEVOCLIENTEController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        re.setOnMouseClicked((event) -> {
            materialesadicionales.setDisable(false);
            try {
                cargarvalores();
                verificarproductos("Router TPLINK");
                q.consulta = q.tablas("SELECT top (1) pon "
                        + "FROM [BDAirnet].[dbo].[tvproductosdj] "
                        + "where "
                        + "producto = 'Router TPLINK' "
                        + "and "
                        + "estado ='Inactivo'");

                while (q.consulta.next()) {
                    ponmac.setText(q.consulta.getString("pon"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(NUEVOCLIENTEController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tubo.setOnMouseClicked((event) -> {
            if (tubo.isSelected()) {
                tubo2 = 12;
            } else {
                tubo2 = 0;
            }
        });

    }

    public void cargarvalores() throws SQLException {

        if (fb.isSelected()) {
            nodo.getItems().removeAll();
            q.consulta = q.tablas("SELECT [NOMBRE]FROM [BDAirnet].[dbo].[TBNODO] where TIPO='Fibra Óptica'");
            try {
                while (q.consulta.next()) {
                    nodo.getItems().addAll(q.consulta.getString("NOMBRE"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(NUEVOCLIENTEController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (re.isSelected()) {
            nodo.getItems().removeAll();

            q.consulta = q.tablas("SELECT [NOMBRE]FROM [BDAirnet].[dbo].[TBNODO] where TIPO='Radio Enlace'");
            try {
                while (q.consulta.next()) {
                    nodo.getItems().addAll(q.consulta.getString("NOMBRE"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(NUEVOCLIENTEController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        q.consulta = q.tablas("SELECT [NOMBRE] FROM [BDAirnet].[dbo].[View_ListaPlanes]");
        while (q.consulta.next()) {
            plan.getItems().addAll(q.consulta.getString("NOMBRE"));
        }
    }

    @FXML
    public void siguiente1() {
        int datosg = 0;
        if (!nombres.getText().isEmpty() && !apellidos.getText().isEmpty()) {
            datosg++;
            if (!ruc.getText().isEmpty()) {
                datosg++;
                if (!direccion.getText().isEmpty()) {
                    datosg++;
                    if (!telefo1.getText().isEmpty()) {
                        datosg++;
                        if (!telefono1.getText().isEmpty()) {
                            datosg++;
                            if (!correo.getText().isEmpty() && correo.getText().contains("@")) {

                                datosg++;
                                if (!ciudad.getSelectionModel().isEmpty()) {
                                    datosg++;
                                    if (datosg == 7) {
                                        if (pasaporte.isSelected()) {
                                            datosgenerales.setDisable(true);
                                            descripcionplan.setDisable(false);
                                            vistas.getSelectionModel().select(descripcionplan);
                                        } else {
                                            if (q.VerificarCedula(ruc.getText().trim())) {
                                                datosgenerales.setDisable(true);
                                                descripcionplan.setDisable(false);
                                                vistas.getSelectionModel().select(descripcionplan);
                                            }
                                        }
                                    }
                                } else {
                                    q.notificaciones("Seleccione la Ciudad", "");
                                }
                            } else {
                                q.notificaciones("Campo de correo incompleto o vacio", "");
                            }
                        } else {
                            q.notificaciones("Campo de Telefono 2 vacio", "");
                        }
                    } else {
                        q.notificaciones("Campo de Telefono 1 vacio", "");
                    }
                } else {
                    q.notificaciones("Campos de direccion vacio", "");
                }
            } else {
                q.notificaciones("Campo de ruc invalido o vacio ", "");
            }
        } else {
            q.notificaciones("Campo de nombre vacio", "");
        }

    }

    @FXML
    public void Anterior1() throws SQLException {
        datosgenerales.setDisable(false);
        descripcionplan.setDisable(true);
        vistas.getSelectionModel().select(datosgenerales);
    }
    VerificarIP ipvg = new VerificarIP();

    @FXML
    public void siguiente2() {
        dia = fechainstalacion.getValue().getDayOfMonth();
        fci = String.valueOf(fechainstalacion.getValue());

        if (!nodo.getSelectionModel().isEmpty()) {

            if (!plan.getSelectionModel().isEmpty()) {

                if (!detalle.getText().isEmpty()) {

                    if (!fci.isEmpty()) {

                        if (fb.isSelected()) {

                            if (ipvg.buscar(ip4.getText())) {
                                tipoista = "Fibra Óptica";
                                datosgenerales.setDisable(true);
                                descripcionplan.setDisable(true);
                                formapago.setDisable(false);
                                vistas.getSelectionModel().select(formapago);
                            } else {
                                q.notificaciones("Ip invalidad o Repetida Verifique los Datos para Continuar", "I");
                            }

                        } else {

                            if (ipvg.buscar(ip4.getText())) {
                                tipoista = "Radio Enlace";
                                datosgenerales.setDisable(true);
                                descripcionplan.setDisable(true);
                                formapago.setDisable(false);
                                vistas.getSelectionModel().select(formapago);
                            } else {
                                q.notificaciones("Ip invalidad o Repetida Verifique los Datos para Continuar", "I");
                            }

                        }

                    } else {
                        q.notificaciones("Seleccione la fecha de instalacion", "");

                    }
                } else {
                    q.notificaciones("Campo de detalle Vacios", "");

                }
            } else {
                q.notificaciones("Seleccione el Plan a Contratar", "");

            }
        } else {
            q.notificaciones("Seleccione el nodo de Red", "");

        }

    }

    public void guardar() {
        int sqw = 0;

        String normal, disc;
        if (!tipopago.getSelectionModel().isEmpty()) {
            sqw++;
            if (!diapago.getSelectionModel().isEmpty()) {
                sqw++;
                if (!creacionfact.getSelectionModel().isEmpty()) {
                    sqw++;
                    if (!tiempoespera.getSelectionModel().isEmpty()) {
                        sqw++;
                        if (sqw == 4) {
                            if (descuentoporterceraedad.isSelected() || descuentopordiscapacidad.isSelected()) {
                                discayter = 2;
                                dicapt = true;
                                disc = "X";
                                normal = "";
                            } else {
                                normal = "X";
                                disc = "";

                            }

                            if (pasaporte.isSelected()) {
                                tipodedocumento = "Pasaporte";
                            } else if (ruc.getText().length() == 10) {
                                tipodedocumento = "Cédula";
                            } else if (ruc.getText().length() == 13) {
                                tipodedocumento = "RUC";
                            }
                            String codigo_cliente = q.ObtenerValorCampo(" top(1) CODIGOCLIENTE", "  TbCliente", " order by CONVERT(int, SUBSTRING(CODIGOCLIENTE, 4, 6)) desc");
                            String cod = "Air" + String.valueOf(Integer.parseInt(codigo_cliente.substring(3, 7)) + 1);

                            try {
                                // esta es el problema de no guardar
                                q.nuevocliente(cod, ruc.getText().trim(), tipodedocumento, nombres.getText().trim(), apellidos.getText().trim(), direccion.getText().trim(), telefo1.getText().trim(), telefono1.getText().trim(), true, correo.getText().trim(), Date.valueOf(q.FechanuevoCliente()), dicapt, tarjeta.isSelected(), efectivo.isSelected(), true, cheque.isSelected(), 1, "I");
                            } catch (SQLException | ParseException ex) {
                                Logger.getLogger(NUEVOCLIENTEController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            String valz = "update [BDAirnet].[dbo].[TBCLIENTE] set crearfactura='" + creacionfact.getSelectionModel().getSelectedItem() + "',";
                            valz = valz + "diacorte='" + tiempoespera.getSelectionModel().getSelectedItem() + "',";
                            valz = valz + "diapago='" + diapago.getSelectionModel().getSelectedItem() + "',";
                            valz = valz + "normal='" + normal + "',";
                            valz = valz + "dis='" + disc + "',";
                            valz = valz + "nodoprincipal='" + nodo.getSelectionModel().getSelectedItem() + "',";
                            valz = valz + "plancontratado='" + plan.getSelectionModel().getSelectedItem() + "',";
                            valz = valz + "detalle='" + detalle.getText() + "',";
                            valz = valz + "ipv4='" + ip4.getText().trim() + "',";
                            valz = valz + " Comparticion='4:1' ,ponmac='" + ponmac.getText().trim() + "',";
                            valz = valz + "fechainstalacion='" + fci + "',";
                            valz = valz + "ciudad='" + ciudad.getSelectionModel().getSelectedItem() + "'";
                            valz = valz + "where CODIGOCLIENTE ='" + cod + "'";
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(NUEVOCLIENTEController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            int codigco = Integer.parseInt(q.ObtenerValorCampo1("CODIGOCONTRATO", "TBCONTRATO", "")) + 1;
                            try {
                                q.Nuevocontrato(1, q.ObtenerID("Idcliente", "TbCliente", ""), String.valueOf(codigco), tipoista, Date.valueOf(q.FechanuevoCliente()), Float.parseFloat(costoinstalacion.getText()), Float.parseFloat(costomensual.getText()) / discayter, true, 2, String.valueOf(codigco), "I");
                            } catch (SQLException ex) {
                                Logger.getLogger(NUEVOCLIENTEController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            // actualizacion de datos cliente    
                            q.UpdateModificar(valz);
                            // creacion de factura 
                            // creacion de detalle
                            //  Double propo = (30 - dia) * (q.ConvertidorStringaDouble(costomensual.getText().trim()) / 30) / discayter;
                            //  Double total = (q.ConvertidorStringaDouble(costoinstalacion.getText().trim()) + tubo2);
                            q.UpdateModificar("update [BDAirnet].[dbo].[tvproductosdj] set  estado='Activo',cliente='" + nombres.getText().trim() + " " + apellidos.getText().trim() + "',fechaactiva='" + q.FechaformatoCalendaerio() + "',activa='SI' ,CONTRATO='" + String.valueOf(codigco) + "' where pon='" + ponmac.getText() + "'");
                            q.cargarvaloresPropiedades();
                            //**************************************************************  
                            File directorio = new File(q.properties.getProperty("RutaArchivos") + "\\Clientes\\" + nombres.getText().replace(" ", "") + apellidos.getText().replace(" ", "") + "\\Facturas");
                            // creacion de archivos de contratos y directorios
                            if (!directorio.exists()) {
                                if (directorio.mkdirs()) {

                                }
                            }
                            q.Facturar(nombres.getText() + " " + apellidos.getText(),
                                     q.FormatoDecimal((q.ConvertidorStringaDouble(costoinstalacion.getText().trim()) + tubo2)),
                                     "INSTALACION",
                                     1,
                                     ruc.getText(),
                                     "1",
                                     "I");
                            q.Facturar(nombres.getText() + " " + apellidos.getText(),
                                     q.FormatoDecimal((30 - dia) * (q.ConvertidorStringaDouble(costomensual.getText().trim()) / 30) / discayter),
                                     "Proporcional al Mes " + LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase() + " " + LocalDate.now().getYear(),
                                     1,
                                     ruc.getText(),
                                     "1",
                                     "I");
                            q.NuevaIP(plan.getSelectionModel().getSelectedItem(), true);
                            if (descuentoporPromo.isSelected()) {
                                //q.ObtenerID("Idcliente", "TbCliente", "")
                                q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[TablaPromociones]\n"
                                        + "           (\n"
                                        + "           [IDCLIENTE]\n"
                                        + "           ,[FECHADECULMINACION]\n"
                                        + "           ,[FECHADEINICIO]\n"
                                        + "           ,[ESTADO])\n"
                                        + "     VALUES\n"
                                        + "           (\n"
                                        + "           "+q.ObtenerID("Idcliente", "TbCliente", "")+"\n"
                                        + "           ,getdate()\n"
                                        + "           ,getdate()\n"
                                        + "           ,1)");
                            }

                        }

                    } else {
                        q.notificaciones("Seleccione tiempo de Espera", "I");

                    }
                } else {
                    q.notificaciones("Seleccione dia de Creacion de Factura", "I");

                }
            } else {
                q.notificaciones("Seleccione el dia de pago", "I");

            }
        } else {
            q.notificaciones("Seleccione el tipo de pago", "I");

        }
    }
    int cuantos = 0;

    public void verificarproductos(String x) throws SQLException {

        q.consulta = q.tablas("SELECT COUNT (*) as cantidad  FROM [BDAirnet].[dbo].[tvproductosdj] where producto = '" + x + "' and estado ='Inactivo'");
        while (q.consulta.next()) {
            cuantos = (q.consulta.getInt("cantidad"));
        }
        if (cuantos <= 6 && cuantos > 0) {
            q.notificaciones("Hay " + cuantos + " existentes notifique a su supervisor", "");
        } else if (cuantos == 0) {
            q.notificaciones("No hay stock ", "I");

        }
    }

    /*
    public void Facturarmeses(double Valor_Total) throws SQLException {
        Month mes1 = LocalDate.now().getMonth();
        int año2 = LocalDate.now().getYear();
        String[] Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        switch (mes1.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase()) {
            case "ENERO":
                for (int i = 1; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "FEBRERO":
                for (int i = 2; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "MARZO":
                for (int i = 3; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "ABRIL":
                for (int i = 4; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "MAYO":
                for (int i = 5; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "JUNIO":
                for (int i = 6; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "JULIO":
                for (int i = 7; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "AGOSTO":
                for (int i = 8; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "SEPTIEMBRE":
                for (int i = 9; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "OCTUBRE":
                for (int i = 10; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "NOVIEMBRE":
                for (int i = 11; i < Meses.length; i++) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Meses[i] + "\t" + año2, 1, ruc.getText(), "1", "E");
                }
                break;
            case "DICIEMBRE":
                for (String Mese : Meses) {
                    q.Facturar(nombres.getText() + " " + apellidos.getText(), q.FormatoDecimal(Valor_Total), "Mes " + Mese + "\t" + (año2 + 1), 1, ruc.getText(), "1", "E");
                }
                break;

        }
    }
     */
    public synchronized void GuardarTodo(ActionEvent eAction) {

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                imgCargar.setVisible(true);
                guardar();

                // aqui va todo el proceso a ejecutarse 
                return null;
            }
        };
        task.setOnSucceeded(event -> {
            q.notificaciones("Verifique los datos en la vista del cliente", "I");
            imgCargar.setVisible(false);// aqui va que hacer cuando ya acabe de ejecutarse el hilo
            ResestVentana();
            q.notificaciones("Actualice la lista de Clientes", "I");

        });
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    public void ResestVentana() {
        tipodedocumento = "";
        tipoista = "";
        discayter = 1;
        tubo2 = 0;
        dicapt = false;
        fci = "";
        vistas.getSelectionModel().select(datosgenerales);
        datosgenerales.setDisable(false);
        formapago.setDisable(true);
        nombres.setText("");
        ruc.setText("");
        direccion.setText("");
        telefo1.setText("");
        telefono1.setText("");
        correo.setText("");
        nodo.getItems().clear();
        plan.getItems().clear();
        detalle.setText("");
        costoinstalacion.setText("");
        ip4.setText("");
        fechainstalacion.setValue(LocalDate.now());
        ponmac.setText("");
        costomensual.setText("");
        fb.setSelected(false);
        re.setSelected(false);
        descuentoporterceraedad.setSelected(false);
        descuentopordiscapacidad.setSelected(false);
        tubo.setSelected(false);
        cheque.setSelected(false);
        tarjeta.setSelected(false);
        efectivo.setSelected(false);
        apellidos.setText("");
        pasaporte.setSelected(false);
        imgCargar.setVisible(false);
    }

}
