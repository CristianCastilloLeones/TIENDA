/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTABLE;

import CAJA.MODULODEFACTURARController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import INDICADORES.VistaReportesController;
import CLASES.bas;
import FacturacionElectronica.DatosFacturacionElectronica;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.util.Pair;
import javafxapplication4.VariablesDeUso;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class CONTABLEController implements Initializable {

    ObservableList<ClasedeFacturacionElectronica> clt = FXCollections.observableArrayList();
    bas q = new bas();
    Parent we;
    @FXML
    private AnchorPane egresos;
    @FXML
    private AnchorPane INGRESOS;
    @FXML
    private AnchorPane FACTUReRO;
    @FXML
    private AnchorPane CAJACHICA;
    @FXML
    private TextField SRIPRUEBAS;
    @FXML
    private TextField SRIPRODUCCION;
    @FXML
    private DatePicker ATSINICIOFECHA;
    @FXML
    private DatePicker ATSVENTASFINFECHA;
    @FXML
    private Button GENERAR;
    @FXML
    private Tab TabRegistroPago;
    @FXML
    private Tab TabIngresos;
    @FXML
    private Tab TabCajaChica;
    @FXML
    private Tab TabFacturacionEle;
    @FXML
    private TextField firmaelecrtronica;
    @FXML
    private Tab TabEgresos;
    @FXML
    private AnchorPane auxpagoTorres;
    @FXML
    private AnchorPane tabarchivobanco;
    @FXML
    private AnchorPane cierredecaja;
    @FXML
    private Tab archFacturacionElectronica;
    @FXML
    private TableView tablaFacturaionElectronica;
    @FXML
    private TableColumn<String, ClasedeFacturacionElectronica> numerofacelectronica;
    @FXML
    private TableColumn<String, ClasedeFacturacionElectronica> nuremobasefacturaelectronica;
    @FXML
    private TableColumn<String, ClasedeFacturacionElectronica> clientebasefactura;
    @FXML
    private TableColumn<Float, ClasedeFacturacionElectronica> valorfacturadoV;
    @FXML
    private TableColumn<String, ClasedeFacturacionElectronica> seriecaja;
    @FXML
    private TableColumn<String, ClasedeFacturacionElectronica> fechageneradaFactura;
    @FXML
    private TableColumn<String, ClasedeFacturacionElectronica> estadoSRI;
    @FXML
    private TableColumn<Integer, ClasedeFacturacionElectronica> IDFACTURA;
    @FXML
    private DatePicker INICIOBUSQUEDAFACTURACION;
    @FXML
    private DatePicker FECHAFINBUSQUEDA;
    @FXML
    private Label valortotal;
    @FXML
    private Label totalitem;
    @FXML
    private AnchorPane archClientes;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @FXML
    private Button buscar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        INICIOBUSQUEDAFACTURACION.setValue(LocalDate.now());
        FECHAFINBUSQUEDA.setValue(LocalDate.now());
        q.CargarPropiedades();
        TabIngresos.setDisable(q.DesahabilitarNiveles(q.properties.getProperty("TipoUsuario")));
        TabCajaChica.setDisable(q.DesahabilitarNiveles(q.properties.getProperty("TipoUsuario")));
        TabFacturacionEle.setDisable(q.DesahabilitarNiveles(q.properties.getProperty("TipoUsuario")));
        buscar.setDisable(q.DesahabilitarNiveles(q.properties.getProperty("TipoUsuario")));
        valoresTabla();
        buscar.setOnAction((event) -> {
            try {
                queryDataFacElec();
            } catch (SQLException ex) {
                Logger.getLogger(CONTABLEController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ContextMenu context = new ContextMenu();
        MenuItem Item1 = new MenuItem("Ver Historial de RIDE");
        MenuItem Item2 = new MenuItem("AUTORIZAR SRI");
        MenuItem Item3 = new MenuItem("ANULAR FACTURA");
        MenuItem Item4 = new MenuItem("Ver detalles de Factura");
        context.getItems().addAll(Item1, Item2, Item3, Item4);
        Item1.setOnAction((event) -> {
            String Cliente = String.valueOf(clientebasefactura.getCellObservableValue(tablaFacturaionElectronica.getSelectionModel().getSelectedIndex()).getValue());
            q.abrirRide( Cliente);
        });
        Item2.setOnAction((event) -> {
           int IDFAC=0;
         String X=  String.valueOf(clt.get(tablaFacturaionElectronica.getSelectionModel().getSelectedIndex()).getIDFACTURA());
         if(X!=null){
             IDFAC = Integer.parseInt(X);
             DatosFacturacionElectronica facturacion = new DatosFacturacionElectronica(IDFAC);
               try {
                   if(facturacion.principal()){
                        String Cliente = String.valueOf(clientebasefactura.
                                getCellObservableValue(tablaFacturaionElectronica.getSelectionModel().getSelectedIndex()).getValue());
                        q.abrirRide(q.properties.getProperty("RutaArchivos") + "\\Clientes\\" + Cliente.replace(" ", "") + "\\" + "Facturas");
                   }else {
                      q.notificaciones("Error al Enviar comprobante notficar al administrador del Sistema", "I");
                   }
               } catch (IOException | JRException ex) {
                   Logger.getLogger(CONTABLEController.class.getName()).log(Level.SEVERE, null, ex);
               }
         }
        });
        Item3.setOnAction((event) -> {
             CREARALERTASECambiarValor();   
        });
        Item4.setOnAction((event) -> {
           String Cliente = String.valueOf(clientebasefactura.getCellObservableValue(tablaFacturaionElectronica.getSelectionModel().getSelectedIndex()).getValue());
            BuscarFacturacionExistente(q.BusquedaCedula(Cliente)
                    ,String.valueOf(clt.get(tablaFacturaionElectronica.getSelectionModel().getSelectedIndex()).getNuremobasefacturaelectronica()));
        });
        tablaFacturaionElectronica.setContextMenu(context);
        try {
            we = FXMLLoader.load(getClass().getResource("ArchivoBanco.fxml"));
            we.autosize();
            tabarchivobanco.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(CONTABLEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/HistorialdePagoTorres.fxml"));
            we.autosize();
            auxpagoTorres.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(CONTABLEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/VentanaAUXMOVIMIENTOS.fxml"));
            we.autosize();
            INGRESOS.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(CONTABLEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/CAJA/MODULOCAJA.fxml"));
            we.autosize();
            FACTUReRO.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(CONTABLEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/Reporte_Ingresos.fxml"));
            we.autosize();
            CAJACHICA.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(CONTABLEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/VistaReportes.fxml"));
            we.autosize();
            egresos.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(CONTABLEController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/CierredeCaja.fxml"));
            we.autosize();
            cierredecaja.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(CONTABLEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void valoresTabla() {
        tablaFacturaionElectronica.setItems(clt);
        numerofacelectronica.setCellValueFactory(new PropertyValueFactory<>("numerofacelectronica"));
        nuremobasefacturaelectronica.setCellValueFactory(new PropertyValueFactory<>("nuremobasefacturaelectronica"));
        clientebasefactura.setCellValueFactory(new PropertyValueFactory<>("clientebasefactura"));
        valorfacturadoV.setCellValueFactory(new PropertyValueFactory<>("valorfacturadoV"));
        seriecaja.setCellValueFactory(new PropertyValueFactory<>("seriecaja"));
        fechageneradaFactura.setCellValueFactory(new PropertyValueFactory<>("fechageneradaFactura"));
        estadoSRI.setCellValueFactory(new PropertyValueFactory<>("estadoSRI"));
        IDFACTURA.setCellValueFactory(new PropertyValueFactory<>("IDFACTURA"));
        tablaFacturaionElectronica.getItems().clear();
    }

    public void queryDataFacElec() throws SQLException {
        clt.clear();
        valortotal.setText("0");
        totalitem.setText("0");
        String[] FechaDeconpuesta = INICIOBUSQUEDAFACTURACION.getValue().format(formatter).split("/");
        String[] FechaFinalDes = FECHAFINBUSQUEDA.getValue().format(formatter).split("/");
        int diainico = Integer.parseInt(FechaDeconpuesta[0]);
        int diaFinal = Integer.parseInt(FechaFinalDes[0]);
        int mesinicial = Integer.parseInt(FechaDeconpuesta[1]);
        int mesfinal = Integer.parseInt(FechaFinalDes[1]);
        String CONDICION = "(";
        for (int i = diainico; i <= diaFinal; i++) {
            if (i == diaFinal) {
                CONDICION = CONDICION + (" fechadepago  ='" + Completar(i) + "/" + Completar(mesfinal) + "/" + q.ObtnerAñoActual() + "' ) ");
            } else {
                CONDICION = CONDICION + (" fechadepago  ='" + Completar(i) + "/" + Completar(mesfinal) + "/" + q.ObtnerAñoActual() + "'") + (" or ");
            }

        }

        int c = 0;
        float tot = 0, valor = 0;
        q.consulta = q.tablas("SELECT nuemrofactura,cliente,fechagenerada,SRI,total,serieca ,idfact FROM [BDAirnet].[dbo].[tvdjFacurascliente] where total is not null and serieca is not null and " + CONDICION + " order by idfact asc");
        while (q.consulta.next()) {
            valor = q.consulta.getFloat("total");
            clt.add(new ClasedeFacturacionElectronica(String.valueOf(c++),
                    q.consulta.getString("nuemrofactura").trim(),
                    q.consulta.getString("cliente").trim(),
                    valor,
                    q.consulta.getString("serieca").trim(),
                    q.consulta.getString("fechagenerada").trim(),
                    q.consulta.getString("SRI").replace(" ", "") == "SI" ? "AUTORIZADA" : "PENDIENTE ENVIO",
                     q.consulta.getInt("idfact"))
            );
            tot = tot + valor;
        }

        tablaFacturaionElectronica.setItems(clt);
        valortotal.setText(String.valueOf(tot));
        totalitem.setText(String.valueOf(c));
    }

    public String Completar(int X) {
        if (X > 0 && X < 10) {
            String Z = String.valueOf(X);
            return Z = "0" + Z;

        }
        return String.valueOf(X);
    }
    
       public void CREARALERTASECambiarValor() {
        // Create the custom 
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Anulacion Facturas");
        dialog.setHeaderText("Solicite al supervisor las credenciales para proceder con el proceso");

// Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
// Set the button types.
        ButtonType loginButtonType = new ButtonType("ANULAR", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField username = new TextField();
         username.setText(String.valueOf(clt.get(tablaFacturaionElectronica.getSelectionModel().getSelectedIndex()).getIDFACTURA()));
        username.setEditable(false);
        username.setEditable(false);
        TextField ValorActualizar = new TextField();
        ValorActualizar.setText(String.valueOf(clt.get(tablaFacturaionElectronica.getSelectionModel().getSelectedIndex()).getNuremobasefacturaelectronica()));
        ValorActualizar.setEditable(false);
        PasswordField password = new PasswordField();
        password.setPromptText("Ingrese las Credenciales de supervisor");
        
        grid.add(new Label("ID FACTURA:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("N° de Factura :"), 0, 1);
        grid.add(ValorActualizar, 1, 1);
        grid.add(new Label("Credencial :"), 0, 2);
        grid.add(password, 1, 2);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
        
        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });
        
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            if (usernamePassword.getValue().equals("1725746513")) {
               ;
                q.DeleteEliminar("update [BDAirnet].[dbo].[tvdjFacurascliente] set"
                        + " fechadepago='ANULADA',formapago='ANULADA', estado='ANULADA' where  idfact="+username.getText());
                q.UpdateModificar("update [BDAirnet].[dbo].[detallesfacxtura] set numerofactura = null , Estado=1 "
                        + "where codigo='"+ q.BusquedaCedula(String.valueOf(clt.get(tablaFacturaionElectronica.getSelectionModel().getSelectedIndex()).getClientebasefactura())) +"' and "
                                + "numerofactura='"+ValorActualizar.getText()+"'" );
            }
            
        });
    }
        Alert alert;
        public void Alestas(String MS){
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
	alert.setTitle("AVISO DE FACTURACION");
	alert.setContentText(MS);
        alert.showAndWait();
	
    }
        public void BuscarFacturacionExistente(String Cedula,String NumeroFactura){
            String Detalle="";
        q.auxConsulta = q.tablas(" select detalle,QUIENGENERA,FECHADEORIGEN,valortotal from [BDAirnet].[dbo].[detallesfacxtura] where codigo ='"
                +Cedula+"' and numerofactura ='"+NumeroFactura+"'");
         try {
             while(q.auxConsulta.next()){
                 Detalle=Detalle +(" Detalle "+q.auxConsulta.getString("detalle").trim()+
                             "\n Valor Total :"+q.auxConsulta.getString("valortotal")+
                             "\n Generado por :"+q.auxConsulta.getString("QUIENGENERA")+
                             "\n en Fecha : "+q.auxConsulta.getString("FECHADEORIGEN"));
             }
             if(!Detalle.trim().isEmpty()){
                 Alestas(Detalle);
             }
         } catch (SQLException ex) {
             Logger.getLogger(MODULODEFACTURARController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
