/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import INDICADORES.facturasinstalacion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import CLASES.Clase_Estado_Individual;
import CLASES.EstadoIndivudaulBancosHILO;
import CLASES.Eventos;
import CLASES.EventosEstadoIndividual;
import CLASES.FacturasCobradas;
import CLASES.HiloFacturasCobradas;
import CLASES.bas;
import CLASES.cuentasporbanco;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class VentanaAUXMOVIMIENTOSController implements Initializable {

    List listaclientebn = new ArrayList();
    List listaefectivo = new ArrayList();

    LocalDate iniciobusq, finbusq;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Calendar fecha1 = Calendar.getInstance();
    bas q = new bas();
    ObservableList<facturasinstalacion> estadodeinstalacion = FXCollections.observableArrayList();
    String fechinicio = "", fechafinal = "";
    @FXML
    private AnchorPane RP;
    @FXML
    private Label TOTALENCAJA;
    @FXML
    private Button buscar;
    @FXML
    private DatePicker iniciodebusqueda;
    @FXML
    private DatePicker finaldebusqueda;
    @FXML
    private Tab efectivotab;
    @FXML
    private TextField totalpagoefecivo;
    @FXML
    private TableView pagoefectivo;
    @FXML
    private TableColumn<String, FacturasCobradas> numero;
    @FXML
    private TableColumn<String, FacturasCobradas> cliente;
    @FXML
    private TableColumn<String, FacturasCobradas> valor;
    @FXML
    private TableColumn<String, FacturasCobradas> fecha;
    @FXML
    private Tab otrosingresostab;
    @FXML
    private TableView tablaancos;
    @FXML
    private TableColumn<String, cuentasporbanco> clientebancos;
    @FXML
    private TableColumn<String, cuentasporbanco> fechabancos;
    @FXML
    private TableColumn<String, cuentasporbanco> valorbancos;
    @FXML
    private TableColumn<String, cuentasporbanco> evidenciabancos;
    @FXML
    private TableColumn<Integer, cuentasporbanco> nitem;
    @FXML
    private TableView eventos;
    @FXML
    private TableColumn<String, Eventos> clienteeve;
    @FXML
    private TableColumn<String, Eventos> causa;
    @FXML
    private TableColumn<String, Eventos> estado;
    @FXML
    private TextField totalbancos;
    @FXML
    private TableView cobroinstalaciones;
    @FXML
    private TableColumn<String, facturasinstalacion> clienteinstalaciones;
    @FXML
    private TableColumn<String, facturasinstalacion> fechainstalacion;
    @FXML
    private TableColumn<String, facturasinstalacion> valorinstalaciones;
    @FXML
    private TableColumn<String, facturasinstalacion> estadoinstalaciones;
    @FXML
    private TextField totalinstalaciones;
    @FXML
    private TextField egresos;
    String ruta = "";
    String CondicionCajero = "";
    @FXML
    private ComboBox<String> CAJERO;
    Clase_Estado_Individual ob;
    @FXML
    private TextField clientebusqueefectivo;
    @FXML
    private Button btnbusqedaefectivo;
    @FXML
    private TextField busquedadeposito;
    @FXML
    private Button busquedaBancosbtn;
    
    @FXML
    private Label total_item;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        busquedaBancosbtn.setOnAction((event) -> {
            SelecionarfiladeCliente(busquedadeposito.getText(), tablaancos, clientebancos);
        });
        busquedadeposito.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {

                SelecionarfiladeCliente(busquedadeposito.getText(), tablaancos, clientebancos);
            }
        });
        btnbusqedaefectivo.setOnAction((event) -> {
            SelecionarfiladeCliente(clientebusqueefectivo.getText(), pagoefectivo, cliente);
        });
        clientebusqueefectivo.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
            SelecionarfiladeCliente(clientebusqueefectivo.getText(), pagoefectivo, cliente);
            }
        });
        tablaancos.setRowFactory(tv -> {
            TableRow<cuentasporbanco> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    cuentasporbanco rowData = row.getItem();
                    q.abriarchivos(rowData.getHyperlink());
                }
            });
            return row;
        });

        q.Cajerosdisponibles(CAJERO);
        Valores_iniciales_Tablas();
        ContextMenu context = new ContextMenu();
        ContextMenu contextEfec = new ContextMenu();
        MenuItem Item2 = new MenuItem("Ver Historial de Cliente");
        MenuItem Item1 = new MenuItem("Ver Comprobante");
        Item1.setOnAction((ActionEvent event) -> {
            if (!tablaancos.getSelectionModel().isEmpty()) {
                ruta = String.valueOf(evidenciabancos.getCellObservableValue(tablaancos.getSelectionModel().getSelectedIndex()).getValue());

            }
            q.abriarchivos(ruta);

        });

        Item2.setOnAction((ActionEvent event) -> {
            if (!pagoefectivo.getSelectionModel().isEmpty()) {
                ob = new Clase_Estado_Individual(String.valueOf(cliente.getCellObservableValue(pagoefectivo.getSelectionModel().getSelectedIndex()).getValue()));
                ob.cargarvalores();

            }

        });
        contextEfec.getItems().addAll(Item2);
        context.getItems().addAll(Item1);
        tablaancos.setContextMenu(context);
        pagoefectivo.setContextMenu(contextEfec);

        iniciodebusqueda.setValue(LocalDate.now());
        finaldebusqueda.setValue(LocalDate.now());

       
        buscar.setOnAction((event) -> {
            if (!CAJERO.getSelectionModel().isEmpty()) {
                CondicionCajero = " And serieca ='" + q.SerieCajeros(CAJERO.getSelectionModel().getSelectedItem()) + "'";
            } else {
                CondicionCajero = "";
            }
            Actualizar();

        });

        Actualizar();
    }

    public void SelecionarfiladeCliente(String clienteTXT, TableView tabla, TableColumn columna) {
        if (!clienteTXT.trim().isEmpty()) {
            for (int i = 0; i < tabla.getItems().size(); i++) {
                if (String.valueOf(columna.getCellObservableValue(i).getValue()).equals(clienteTXT.trim())) {
                    tabla.getSelectionModel().select(i);
                    tabla.scrollTo(i);
                    break;
                }
            }
        }
    }

    public void datosbusqueda() {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        fechinicio = f.format(fecha1.getTime());
        fechafinal = f.format(fecha1.getTime());
        iniciobusq = iniciodebusqueda.getValue();
        fechinicio = String.valueOf(iniciodebusqueda.getValue().format(formatter));
        finbusq = finaldebusqueda.getValue();
        fechafinal = String.valueOf(finbusq.format(formatter));
    }
public String Completar(int X) {
        if (X > 0 && X < 10) {
            String Z = String.valueOf(X);
            return Z = "0" + Z;

        }
        return String.valueOf(X);
    }
ResultSet facturins;
    public void facturasdeinstalaciones() throws SQLException {
        
        double vt = 0;
        String tipo = "";
        cobroinstalaciones.getItems().clear();
        
        //*********************
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
                            CONDICION= CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(mesfinal) + "/"+q.ObtnerAñoActual()+"' )  and (detalle LIKE '%CAMBI%'  or detalle LIKE '%MIGRA%' or detalle LIKE '%INSTALACION%') "+ CondicionCajero);
                        } else {
                            CONDICION= CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(mesfinal) + "/"+q.ObtnerAñoActual()+"'")+(" or ");
                        }

                    }
                     facturins  = q.tablas("  SELECT  cliente,Pagado,valortotal,numerofactura,detalle FROM [BDAirnet].[dbo].[VistaOtrosPagos]\n"
                            + "   where " + CONDICION + "  order by nuemrofactura asc");
                     while (facturins.next()) {
            if (facturins.getString("detalle").trim().toUpperCase().contains("INSTALACION")) {
                tipo = "I";
            } else if (facturins.getString("detalle").trim().toUpperCase().contains("MIGRACION")) {
                tipo = "M";
            } else if (facturins.getString("detalle").trim().toUpperCase().contains("DOMICILIO")) {
                tipo = "D";
            }

            estadodeinstalacion.add(new facturasinstalacion(facturins.getString("cliente"), facturins.getString("Pagado"), facturins.getString("valortotal"), facturins.getString("numerofactura"), tipo));
        }
         } else if (mesinicial < mesfinal) {
                    String CONDICION = "( ";
                    int axu = iniciodebusqueda.getValue().lengthOfMonth();
                    for (int j = mesinicial; j <= mesfinal; j++) {

                        for (int i = diainico; i <= axu; i++) {

                            if (j == mesfinal && i == diaFinal) {
                                CONDICION=CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(j) + "/"+q.ObtnerAñoActual()+"' ) and (detalle LIKE '%CAMBI%'  or detalle LIKE '%MIGRA%' or detalle LIKE '%INSTALACION%')  "+ CondicionCajero );
                                
                            } else {
                                CONDICION=CONDICION+(" fechadepago  ='" + Completar(i) + "/" + Completar(j) + "/"+q.ObtnerAñoActual()+"'")+(" or ");
                                
                            }

                        }
                        diainico = 1;
                        axu = diaFinal;

                    }
                     facturins  = q.tablas("  SELECT  cliente,Pagado,valortotal,numerofactura,detalle FROM [BDAirnet].[dbo].[VistaOtrosPagos] \n"
                            + "   where " + CONDICION + "  order by nuemrofactura asc");
                     while (facturins.next()) {
            if (facturins.getString("detalle").trim().toUpperCase().contains("INSTALACION")) {
                tipo = "I";
            } else if (facturins.getString("detalle").trim().toUpperCase().contains("MIGRACION")) {
                tipo = "M";
            } else if (facturins.getString("detalle").trim().toUpperCase().contains("DOMICILIO")) {
                tipo = "D";
            }

            estadodeinstalacion.add(new facturasinstalacion(facturins.getString("cliente"), facturins.getString("Pagado"), facturins.getString("valortotal"), facturins.getString("numerofactura"), tipo));
        }

                   /* try {
                        while (facturins.next()) {
                            if (!facturins.getString("valortotal").isEmpty()) {
                                vt = vt + q.ConvertidorStringaDouble(facturins.getString("valortotal"));
                            }
                        }

                    } catch (SQLException ex) {
                        q.notificaciones("Error de Conexion ", "I");
                    }*/

                } else if (mesinicial > mesfinal) {

                }
        
        
        
        
        
        
        
        
        
        
        //************************************************
        
       /* ResultSet facturins = q.tablas("SELECT  dbo.detallesfacxtura.numerofactura, dbo.tvdjFacurascliente.nuemrofactura, dbo.detallesfacxtura.detalle, dbo.tvdjFacurascliente.estado, dbo.detallesfacxtura.Estado AS EstadoDetadlle, \n"
                + "  dbo.detallesfacxtura.valortotal, dbo.tvdjFacurascliente.cliente, dbo.tvdjFacurascliente.formapago, dbo.tvdjFacurascliente.Pagado\n"
                + "FROM   dbo.detallesfacxtura INNER JOIN\n"
                + "   dbo.tvdjFacurascliente ON dbo.detallesfacxtura.codigo = dbo.tvdjFacurascliente.codigocontrato AND dbo.detallesfacxtura.numerofactura = dbo.tvdjFacurascliente.nuemrofactura\n"
                + "\n"
                + "where ( dbo.detallesfacxtura.detalle like'%INSTALACION%' or dbo.detallesfacxtura.detalle like'%MIGRA%' OR dbo.detallesfacxtura.detalle like'%DOMICILIO%') " + CondicionCajero + "  and fechadepago between '" + fechinicio + "' and '" + fechafinal + "' order by dbo.tvdjFacurascliente.nuemrofactura asc");

        */
        
        
        for (int i = 0; i < cobroinstalaciones.getItems().size(); i++) {
            vt = vt + Double.parseDouble(String.valueOf(valorinstalaciones.getCellObservableValue(i).getValue()).replace(",", "."));
        }
        totalinstalaciones.setText(q.FormatoDecimal(vt));

    }

    public void salidadedinero() throws SQLException {
        eventos.getItems().clear();
        String valorcondion = "";
        if (!CAJERO.getSelectionModel().isEmpty()) {
            valorcondion = " and entregadopor ='" + CAJERO.getSelectionModel().getSelectedItem() + "'";
        }

        EventosEstadoIndividual task = new EventosEstadoIndividual("1", fechinicio, fechafinal, valorcondion);
        task.setOnRunning((succeesesEvent) -> {

        });
        task.setOnSucceeded((succeededEvent) -> {
            eventos.setItems((ObservableList) task.getValue());
            double vt = 0;
            for (int i = 0; i < eventos.getItems().size(); i++) {
                vt = vt + Double.parseDouble(String.valueOf(estado.getCellObservableValue(i).getValue()).replace(",", "."));
            }
            egresos.setText(String.valueOf(q.FormatoDecimal(vt)));

            vt = 0;
            for (int i = 0; i < pagoefectivo.getItems().size(); i++) {

                vt = vt + Double.parseDouble(String.valueOf(valor.getCellObservableValue(i).getValue()).replace(",", "."));
            }
            totalpagoefecivo.setText(String.valueOf(q.FormatoDecimal(vt)).replace(",", "."));
            TOTALENCAJA.setText(String.valueOf(q.FormatoDecimal(Double.parseDouble(totalpagoefecivo.getText().replace(",", ".")) - Double.parseDouble(egresos.getText().replace(",", ".")))));
        });
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
    }

    public void valorfacturasefectivo() throws SQLException {
        pagoefectivo.getItems().clear();
        HiloFacturasCobradas task = new HiloFacturasCobradas(fechinicio, fechafinal, CondicionCajero);
        task.setOnSucceeded((succeededEvent) -> {
            pagoefectivo.setItems((ObservableList) task.getValue());
            double vt = 0;
            for (int i = 0; i < pagoefectivo.getItems().size(); i++) {
                listaefectivo.add(String.valueOf(cliente.getCellObservableValue(i).getValue()));
                vt = vt + Double.parseDouble(String.valueOf(valor.getCellObservableValue(i).getValue()).replace(",", "."));
            }
            totalpagoefecivo.setText(String.valueOf(q.FormatoDecimal(vt)).replace(",", "."));
            TOTALENCAJA.setText(String.valueOf(q.FormatoDecimal(Double.parseDouble(totalpagoefecivo.getText().replace(",", ".")) - Double.parseDouble(egresos.getText().replace(",", ".")))));
            q.prediccion(clientebusqueefectivo, listaefectivo);
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);

    }

    public void valordepositos() throws SQLException {
        Clase_Estado_Individual.clientetext=null;
        tablaancos.getItems().clear();
        EstadoIndivudaulBancosHILO task = new EstadoIndivudaulBancosHILO("1", fechinicio, fechafinal, CondicionCajero);
        task.setOnSucceeded((succeededEvent) -> {
            tablaancos.setItems((ObservableList) task.getValue());
            double vt = 0;
            for (int i = 0; i < tablaancos.getItems().size(); i++) {
                listaclientebn.add(String.valueOf(clientebancos.getCellObservableValue(i).getValue()));
                vt = vt + Double.parseDouble(String.valueOf(valorbancos.getCellObservableValue(i).getValue()).replace(",", "."));
            }
            totalbancos.setText(String.valueOf(q.FormatoDecimal(vt)));
            q.prediccion(busquedadeposito, listaclientebn);
        });
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
    }

    public void Actualizar() {
        try {
            listaclientebn.clear();
            listaefectivo.clear();
            datosbusqueda();
            facturasdeinstalaciones();
            salidadedinero();
            valorfacturasefectivo();
            valordepositos();
            colores();
        } catch (SQLException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Valores_iniciales_Tablas() {
        cobroinstalaciones.setItems(estadodeinstalacion);
        clienteinstalaciones.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        fechainstalacion.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        valorinstalaciones.setCellValueFactory(new PropertyValueFactory<>("valor"));
        estadoinstalaciones.setCellValueFactory(new PropertyValueFactory<>("estadoIns"));
        clienteeve.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        causa.setCellValueFactory(new PropertyValueFactory<>("causa"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        clientebancos.setCellValueFactory(new PropertyValueFactory<>("Clientecobrado"));
        fechabancos.setCellValueFactory(new PropertyValueFactory<>("fechacobrada"));
        valorbancos.setCellValueFactory(new PropertyValueFactory<>("Valorcobrado"));
        evidenciabancos.setCellValueFactory(new PropertyValueFactory<>("hyperlink"));
        nitem.setCellValueFactory(new PropertyValueFactory<>("item"));
        numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("Clientecobrado"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fechacobrada"));
        valor.setCellValueFactory(new PropertyValueFactory<>("Valorcobrado"));

    }

    private void colores() {
        PseudoClass insta = PseudoClass.getPseudoClass("highlighted4");
        PseudoClass migra = PseudoClass.getPseudoClass("highlighted5");
        PseudoClass domi = PseudoClass.getPseudoClass("highlighted6");
        cobroinstalaciones.setRowFactory(TableView -> {
            TableRow<facturasinstalacion> fila = new TableRow<>();
            fila.itemProperty().addListener((observable, oldValue, newValue) -> {

                fila.pseudoClassStateChanged(insta, observable.getValue().getTipo().equals("I"));
                fila.pseudoClassStateChanged(migra, observable.getValue().getTipo().equals("M"));
                fila.pseudoClassStateChanged(domi, observable.getValue().getTipo().equals("D"));
            });
            return fila;
        });
    }
}
