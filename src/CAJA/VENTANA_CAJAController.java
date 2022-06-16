/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAJA;

import CLASES.ClaseDepositosVerificacion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import INDICADORES.VistaReportesController;
import CLASES.bas;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import CLASES.ClaseDetallevision;
import CLASES.Clase_Estado_Individual;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javafxapplication4.VariablesDeUso;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class VENTANA_CAJAController implements Initializable {
    
    ObservableList<ClaseDetallevision> detalle = FXCollections.observableArrayList();
    bas q = new bas();
    Parent we;
    @FXML
    private AnchorPane COBROS;
    @FXML
    private AnchorPane GENERADOR;
    @FXML
    private AnchorPane REPORTEDEPAGO;
    @FXML
    private AnchorPane generaryautorizar;
    @FXML
    private TableView<ClaseDepositosVerificacion> tabladepositos;
    @FXML
    private TableColumn<String, ClaseDepositosVerificacion> IDREGISTRO;
    @FXML
    private TableColumn<String, ClaseDepositosVerificacion> ndocumento;
    @FXML
    private TableColumn<String, ClaseDepositosVerificacion> cliente;
    @FXML
    private TableColumn<String, ClaseDepositosVerificacion> fecha;
    @FXML
    private TableColumn<String, ClaseDepositosVerificacion> valor;
    @FXML
    private TextField NDOCUMENTO;
    @FXML
    private Button VERIFICAR;
    @FXML
    private TableView<ClaseDetallevision> TABLADETALLES;
    @FXML
    private TableColumn<Integer, ClaseDetallevision> IDDETALLE;
    @FXML
    private TableColumn<String, ClaseDetallevision> DETALLE;
    @FXML
    private TableColumn<String, ClaseDetallevision> VALORTOTAL;
    @FXML
    private TableColumn<String, ClaseDetallevision> CLIENTE;
    @FXML
    private TableColumn<String, ClaseDetallevision> FACTSRI;
    @FXML
    private RadioButton FACTACTIVAS;
    @FXML
    private RadioButton FACTINACTIVAS;
    @FXML
    private RadioButton FACTANULADAS;
    @FXML
    private Button BUSCAR;
    @FXML
    private Label TOTALREGISTROS;
    @FXML
    private Label VALORMONETARIO;
    final ToggleGroup group1 = new ToggleGroup();
    @FXML
    private ComboBox DETALLEcomo;
    @FXML
    private TextField CEDULACLIENTE;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ContextMenu context2 = new ContextMenu();
        MenuItem context2Item1 = new MenuItem("Modificar Valor");
        MenuItem context2Item2 = new MenuItem("Eliminar Item");
        context2.getItems().addAll(context2Item1, context2Item2);
        TABLADETALLES.setContextMenu(context2);
         context2Item1.setOnAction((event) -> {
            
             CREARALERTASECambiarValor();
        });
         context2Item2.setOnAction((event) -> {
           CREARALERTASEliminarItem();
            
        });
        BUSCAR.setOnAction((event) -> {
            if(CEDULACLIENTE.getText().trim().isEmpty()){
               BuscarDetalle(); 
            }else {
                CargarFacturasdeClientes();
            }
            
        });
        String DatosDetalle[] = {"Instalacion", "Migracion", "Cambio de Domicilio", "Cambio de ONT", "ONT",
             "ROUTERS", "Cambio de Contrato", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
             "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre", "Solo Planes", "Omitir Planes", "General"};
        
        for (int i = 0; i < DatosDetalle.length; i++) {
            DETALLEcomo.getItems().addAll(DatosDetalle[i]);
        }
        //  DETALLEcomo.getItems().addAll((Object) DatosDetalle);
        
        TABLADETALLES.setItems(detalle);
        IDDETALLE.setCellValueFactory(new PropertyValueFactory<>("IDDETALLE"));
        DETALLE.setCellValueFactory(new PropertyValueFactory<>("Detalle"));
        VALORTOTAL.setCellValueFactory(new PropertyValueFactory<>("ValorTotal"));
        CLIENTE.setCellValueFactory(new PropertyValueFactory<>("Cliente"));
        FACTSRI.setCellValueFactory(new PropertyValueFactory<>("NfacSRI"));
        
        FACTACTIVAS.setSelected(true);
        FACTACTIVAS.setToggleGroup(group1);
        FACTINACTIVAS.setToggleGroup(group1);
        FACTANULADAS.setToggleGroup(group1);
        ContextMenu context = new ContextMenu();
        MenuItem Item1 = new MenuItem("Ver Documento");
        context.getItems().addAll(Item1);
        tabladepositos.setContextMenu(context);
        Item1.setOnAction((event) -> {
            Abrir();
            
        });
        VERIFICAR.setOnAction((event) -> {
            if (!NDOCUMENTO.getText().trim().isEmpty()) {
                Busqueda();
            } else {
                q.notificaciones("Campos Vacios ", "I");
            }
        });
        NDOCUMENTO.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!NDOCUMENTO.getText().trim().isEmpty()) {
                    Busqueda();
                } else {
                    q.notificaciones("Campos Vacios ", "I");
                }
            }
            
        });
        tabladepositos.setItems(clt);
        IDREGISTRO.setCellValueFactory(new PropertyValueFactory<>("IDREGISTRO"));
        ndocumento.setCellValueFactory(new PropertyValueFactory<>("ndocumento"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        try {
            we = FXMLLoader.load(getClass().getResource("/CAJA/MODULOCAJA.fxml"));
            we.autosize();
            q.Estilos(we);
            COBROS.getChildren().add(we);
            
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/CAJA/MODULODEFACTURAR.fxml"));
            we.autosize();
            q.Estilos(we);
            GENERADOR.getChildren().add(we);
            
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/Reporte_de_Pagos.fxml"));
            we.autosize();
            q.Estilos(we);
            REPORTEDEPAGO.getChildren().add(we);
            
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/CAJA/Generador_de_Facturas.fxml"));
            we.autosize();
            q.Estilos(we);
            generaryautorizar.getChildren().add(we);
            
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ObservableList<ClaseDepositosVerificacion> clt = FXCollections.observableArrayList();
    
    public void Busqueda() {
        tabladepositos.getItems().clear();
        String Consulta = "SELECT  [IDDEPOSITO]\n"
                + " ,[CEDULACLIENTE]\n"
                + ",[NUMERODECOMPROBANTE]\n"
                + ",[FECHADEPAGO]\n"
                + ",[VALORCOBRADO]\n"
                + "  FROM [BDAirnet].[dbo].[tvdjDepositos] where NUMERODECOMPROBANTE like '%" + NDOCUMENTO.getText().trim() + "%'";
        System.out.println(Consulta);
        q.auxConsulta = q.tablas(Consulta);
        try {
            while (q.auxConsulta.next()) {
                clt.add(new ClaseDepositosVerificacion(q.auxConsulta.getString("IDDEPOSITO"),
                        q.auxConsulta.getString("NUMERODECOMPROBANTE"),
                        q.BusquedaNombreporCedula(q.auxConsulta.getString("CEDULACLIENTE")),
                        q.auxConsulta.getString("FECHADEPAGO"),
                        q.auxConsulta.getFloat("VALORCOBRADO")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VENTANA_CAJAController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabladepositos.setItems(clt);
        if (tabladepositos.getItems().isEmpty()) {
            tabladepositos.setPlaceholder(new Label("No existen resulados en la busqueda"));
        }
    }
    
    public void Abrir() {
        String Ruta = "";
        if (!tabladepositos.getSelectionModel().isEmpty()) {
            //  IDREGISTRO.getCellObservableValue(tabladepositos.getSelectionModel().getSelectedIndex()).getValue();
            ResultSet we = q.tablas("select EVIDENCIADERESPALDO FROM [BDAirnet].[dbo].[tvdjDepositos] where IDDEPOSITO ="
                    + IDREGISTRO.getCellObservableValue(tabladepositos.getSelectionModel().getSelectedIndex()).getValue());
            try {
                while (we.next()) {
                    Ruta = we.getString("EVIDENCIADERESPALDO");
                }
                //**************************      
            } catch (SQLException ex) {
                Logger.getLogger(VENTANA_CAJAController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        q.abriarchivos(Ruta);
    }
    
    public void BuscarDetalle() {
        TABLADETALLES.getItems().clear();
        int c = 0;
        double valor = 0;
        String query = "select iddetalle,detalle,valortotal,cliente,numerofactura from [BDAirnet].[dbo].[detallesfacxtura] where ";
        
        if (FACTACTIVAS.isSelected()) {
            query = query + " Estado ='1' ";
        } else if (FACTINACTIVAS.isSelected()) {
            query = query + " Estado ='0' and numerofactura not like '%ANU%'";
        } else if (FACTANULADAS.isSelected()) {
            query = query + " Estado ='0' and numerofactura  like '%ANU%'";
        }
        
        if (!DETALLEcomo.getSelectionModel().isEmpty()) {
            if (DETALLEcomo.getSelectionModel().getSelectedItem().toString().equals("Solo Planes")) {
                
                query = query + "and (detalle  like '%Enero%' "
                        + "or  detalle  like '%Febrero%' "
                        + "or  detalle  like '%Marzo%' "
                        + "or detalle  like '%Abril%' "
                        + "or detalle  like '%Mayo%' "
                        + "or detalle  like '%Junio%' "
                        + "or detalle  like '%Julio%' "
                        + "or detalle  like '%Agosto%' "
                        + "or detalle  like '%Septiembre%' "
                        + "or detalle  like '%Octubre%' "
                        + "or detalle  like '%Noviembre%' "
                        + "or detalle  like '%Diciembre%')";
            } else if (DETALLEcomo.getSelectionModel().getSelectedItem().toString().equals("Omitir Planes")) {
                query = query + " and (detalle not like '%Enero%' "
                        + "or  detalle not like '%Febrero%' "
                        + "or  detalle not like '%Marzo%' "
                        + "or detalle not like '%Abril%' "
                        + "or detalle not like '%Mayo%' "
                        + "or detalle not like '%Junio%' "
                        + "or detalle not like '%Julio%' "
                        + "or detalle not like '%Agosto%' "
                        + "or detalle not like '%Septiembre%' "
                        + "or detalle not like '%Octubre%' "
                        + "or detalle not like '%Noviembre%' "
                        + "or detalle not like '%Diciembre%')";
            } else if (DETALLEcomo.getSelectionModel().getSelectedItem().toString().equals("General")) {
                query = query + " ";
            } else {
                query = query + " and detalle  like '%" + DETALLEcomo.getSelectionModel().getSelectedItem().toString() + "%'";
            }
        }
        query = query + " ORDER BY iddetalle ASC ";
        ResultSet Deta = q.tablas(query);
        try {
            while (Deta.next()) {
                detalle.add(new ClaseDetallevision(Deta.getInt("iddetalle"),
                         Deta.getString("valortotal").replace(" ", "").isEmpty() ? "-" : Deta.getString("detalle").trim(),
                         Deta.getString("valortotal").isEmpty() ? "0" : Deta.getString("valortotal"),
                         Deta.getString("cliente").trim(),
                         Deta.getString("numerofactura") == null ? "-" : Deta.getString("numerofactura").trim()));
                c++;
                valor = valor + q.ConvertidorStringaDouble(Deta.getString("valortotal").replace(" ", "").isEmpty() ? "0" : Deta.getString("valortotal"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VENTANA_CAJAController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TABLADETALLES.setItems(detalle);
        TOTALREGISTROS.setText(String.valueOf(c));
        VALORMONETARIO.setText(String.valueOf(valor));
        
    }

    public void CREARALERTASEliminarItem() {
        // Create the custom 
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Credenciales de Anulacion de Facturas");
        dialog.setHeaderText("Solicite al supervisor las credenciales para proceder con el proceso");

// Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
// Set the button types.
        ButtonType loginButtonType = new ButtonType("Anular", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField username = new TextField();
       username.setText(String.valueOf(detalle.get(TABLADETALLES.getSelectionModel().getSelectedIndex()).getIDDETALLE()));
        username.setEditable(false);
        PasswordField password = new PasswordField();
        password.setPromptText("Ingrese las Credenciales de supervisor");
        
        grid.add(new Label("N° de Factura:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Key :"), 0, 1);
        grid.add(password, 1, 1);

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
                q.DeleteEliminar("delete [BDAirnet].[dbo].[detallesfacxtura] where iddetalle="+username.getText());
            }
            
        });
    }

    public void CREARALERTASECambiarValor() {
        // Create the custom 
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Actualizacion de Valores Facturas");
        dialog.setHeaderText("Solicite al supervisor las credenciales para proceder con el proceso");

// Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
// Set the button types.
        ButtonType loginButtonType = new ButtonType("Actualizar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField username = new TextField();
         username.setText(String.valueOf(detalle.get(TABLADETALLES.getSelectionModel().getSelectedIndex()).getIDDETALLE()));
        username.setEditable(false);
        TextField ValorActualizar = new TextField();
        ValorActualizar.setPromptText("Ingrese el valor");
        PasswordField password = new PasswordField();
        password.setPromptText("Ingrese las Credenciales de supervisor");
        
        grid.add(new Label("N° de Factura:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Nuevo Valor :"), 0, 1);
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
                q.DeleteEliminar("update [BDAirnet].[dbo].[detallesfacxtura] set"
                        + " valorunitario='"+ValorActualizar.getText()+"'"
                          + ",claveacceso='" + VariablesDeUso.nombreusuario.replace(" ", "") 
                        + "', valortotal='"+ValorActualizar.getText()+"' where  iddetalle="+username.getText());
            }
            
        });
    }
    public void CargarFacturasdeClientes(){
        TABLADETALLES.getItems().clear();
        int c = 0;
        double valor = 0;
        String query = "select iddetalle,detalle,valortotal,cliente,numerofactura from [BDAirnet].[dbo].[detallesfacxtura] where codigo ='"+CEDULACLIENTE.getText().trim()+"' order by iddetalle asc";
        
        ResultSet Deta = q.tablas(query);
        try {
            while (Deta.next()) {
                detalle.add(new ClaseDetallevision(Deta.getInt("iddetalle"),
                         Deta.getString("valortotal").replace(" ", "").isEmpty() ? "-" : Deta.getString("detalle").trim(),
                         Deta.getString("valortotal").isEmpty() ? "0" : Deta.getString("valortotal"),
                         Deta.getString("cliente").trim(),
                         Deta.getString("numerofactura") == null ? "-" : Deta.getString("numerofactura").trim()));
                c++;
                valor = valor + q.ConvertidorStringaDouble(Deta.getString("valortotal").replace(" ", "").isEmpty() ? "0" : Deta.getString("valortotal"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VENTANA_CAJAController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TABLADETALLES.setItems(detalle);
        TOTALREGISTROS.setText(String.valueOf(c));
        VALORMONETARIO.setText(String.valueOf(valor));
    }
}
