/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAJA;

import CLASES.Clase_Estado_Individual;
import CLASES.ListadoClientesGeneral;
import CLASES.bas;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class Listado_Clientes_GeneralController implements Initializable {
    
    bas q = new bas();
    Integer añoactual=q.FechaActualCalendario().getTime().getYear()+1900;
    Integer mesactual=q.FechaActualCalendario().getTime().getMonth();
    ObservableList<ListadoClientesGeneral> CLIENTES_LISTA = FXCollections.observableArrayList();
    @FXML
    private TableView TablaClientes;
    @FXML
    private TableColumn<Integer, ListadoClientesGeneral> IDCLIENTE;
    @FXML
    private TableColumn<String, ListadoClientesGeneral> CLIENTES;
    @FXML
    private TableColumn<String, ListadoClientesGeneral> IDENTIFICACION;
    @FXML
    private TableColumn<String, ListadoClientesGeneral> PLAN_CONTRATADO;
    @FXML
    private TableColumn<Float, ListadoClientesGeneral> VALOR_MENSUAL;
    @FXML
    private TableColumn<String, ListadoClientesGeneral> IP;
    @FXML
    private TableColumn<String, ListadoClientesGeneral> ESTADO_SERVICIO;
    @FXML
    private TableColumn<String, ListadoClientesGeneral> ESTADO_CLIENTES;
    @FXML
    private TableColumn<String, ListadoClientesGeneral> ESTADOFACTURADO;
    @FXML
    private TextField TXTCLIENTES;
    @FXML
    private Label NUMEORRESULTADOS;
    @FXML
    private Button BUSCARCLIENTES;
    @FXML
    private RadioButton NOFACTU;
    @FXML
    private RadioButton FACURADOS;
    @FXML
    private ChoiceBox MESES;
    Clase_Estado_Individual ob;
    @FXML
    private Button FACTURAR;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FACTURAR.setOnAction((event) -> {
            CREARALERTASECambiarValor();
        });
        String[] Meses_F = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre",""};
        
        BUSCARCLIENTES.setOnAction((event) -> {
           if(TXTCLIENTES.getText().trim().isEmpty()){
               Busqueda();
           }
            
        });
        ContextMenu context = new ContextMenu();
         MenuItem Item1 = new MenuItem("Ver Cliente");
         
         if(mesactual==11){
             mesactual=0;
             añoactual=añoactual+1;
         }else {
             mesactual =mesactual +1;
         }
         FACTURAR.setText("Facturar "+Meses_F[mesactual]);
         MenuItem Item2 = new MenuItem("Facturar "+Meses_F[mesactual]);
           context.getItems().addAll(Item1,Item2);
            TablaClientes.setContextMenu(context);
             Item1.setOnAction((event) -> {
            ob = new Clase_Estado_Individual(String.valueOf(CLIENTES.getCellObservableValue(TablaClientes.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("D");
            
        });
             String Detale="Mes "+Meses_F[mesactual]+" "+añoactual;
        Item2.setOnAction((event) -> {
            q.Facturar(String.valueOf(CLIENTES.getCellObservableValue(TablaClientes.getSelectionModel().getSelectedIndex()).getValue()),
                    String.valueOf(VALOR_MENSUAL.getCellObservableValue(TablaClientes.getSelectionModel().getSelectedIndex()).getValue())
                    ,Detale
                    ,1
                    , String.valueOf(IDENTIFICACION.getCellObservableValue(TablaClientes.getSelectionModel().getSelectedIndex()).getValue()), 
                    "1", "E"); 
            q.notificaciones("Facturado con exito", "I");
        });
             
             
         final ToggleGroup group1 = new ToggleGroup();
         NOFACTU.setToggleGroup(group1);
         FACURADOS.setToggleGroup(group1);
         NOFACTU.setSelected(true);
        MESES.getItems().addAll("Enero",
                 "Febrero",
                 "Marzo",
                 "Abril",
                 "Mayo",
                 "Junio",
                 "Julio",
                 "Agosto",
                 "Septiembre",
                 "Octubre",
                 "Noviembre"
                ,"Diciembre");
        MESES.getSelectionModel().select(mesactual);
        TablaClientes.setItems(CLIENTES_LISTA);
        IDCLIENTE.setCellValueFactory(new PropertyValueFactory<>("IDCLIENTE"));
        CLIENTES.setCellValueFactory(new PropertyValueFactory<>("CLIENTES"));
        IDENTIFICACION.setCellValueFactory(new PropertyValueFactory<>("IDENTIFICACION"));
        PLAN_CONTRATADO.setCellValueFactory(new PropertyValueFactory<>("PLAN_CONTRATADO"));
        VALOR_MENSUAL.setCellValueFactory(new PropertyValueFactory<>("VALOR_MENSUAL"));
        IP.setCellValueFactory(new PropertyValueFactory<>("IP"));
        ESTADO_SERVICIO.setCellValueFactory(new PropertyValueFactory<>("ESTADO_SERVICIO"));
        ESTADO_CLIENTES.setCellValueFactory(new PropertyValueFactory<>("ESTADO_CLIENTES"));
        ESTADOFACTURADO.setCellValueFactory(new PropertyValueFactory<>("ESTADOFACTURADO"));

    }

    public void Busqueda() {
        TablaClientes.getItems().clear();
        if(NOFACTU.isSelected()){
            String queryNoFac = "SELECT  tabclie.[IDCLIENTE]\n"
                + "      ,(tabclie.[NOMBRES]+' '+tabclie.[APELLIDOS]) as CLIENTES\n"
                + "	  ,tabclie.[IDENTIFICACION]\n"
                + "	  ,tabclie.[plancontratado]\n"
                + "	  ,tabclie.[VALORMENSUAL]\n"
                + "      ,tabclie.[ipv4]\n"
                + "      ,tabclie.[ESTADO]\n"
                + "      ,tabclie.ESTADOSERVICIO\n"
                + "	  ,'0' as ESTADOFACTURADO\n"
                + "  FROM [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] as tabclie \n"
                + "  where not EXISTS\n"
                + "   (select fact.Estado from [BDAirnet].[dbo].[detallesfacxtura] as fact where \n"
               + "   detalle like '%"+MESES.getSelectionModel().getSelectedItem()+" "+añoactual.toString()+"%' and codigo=tabclie.IDENTIFICACION) ";
             ResultSet N_Fact = q.tablas(queryNoFac);
             SubConsultaTabla(N_Fact);
        }else {
            String queryFac = "SELECT  tabclie.[IDCLIENTE]\n"
                + "      ,(tabclie.[NOMBRES]+' '+tabclie.[APELLIDOS]) as CLIENTES\n"
                + "	  ,tabclie.[IDENTIFICACION]\n"
                + "	  ,tabclie.[plancontratado]\n"
                + "	  ,tabclie.[VALORMENSUAL]\n"
                + "      ,tabclie.[ipv4]\n"
                + "      ,tabclie.[ESTADO]\n"
                + "      ,tabclie.ESTADOSERVICIO\n"
                + "	  ,'1' as ESTADOFACTURADO\n"
                + "  FROM [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] as tabclie \n"
                + "  where  EXISTS\n"
                + "   (select fact.Estado from [BDAirnet].[dbo].[detallesfacxtura] as fact where \n"
                + "   detalle like '%"+MESES.getSelectionModel().getSelectedItem()+" "+añoactual.toString()+"%' and codigo=tabclie.IDENTIFICACION) ";
            ResultSet S_Fact = q.tablas(queryFac);
            SubConsultaTabla(S_Fact);
        }
       
        // 

    }
    
    public void SubConsultaTabla(ResultSet X){
        int contadorRegistros=0;
        try {
            while (X.next()) {
                CLIENTES_LISTA.add(new ListadoClientesGeneral(X.getInt("IDCLIENTE"),
                         X.getString("CLIENTES"),
                         X.getString("IDENTIFICACION"),
                         X.getString("plancontratado").trim(),
                         X.getFloat("VALORMENSUAL"),
                         X.getString("ipv4"),
                         X.getString("ESTADOSERVICIO"),
                         X.getString("ESTADO"),
                         X.getString("ESTADOFACTURADO")));
                contadorRegistros++;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Listado_Clientes_GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TablaClientes.setItems(CLIENTES_LISTA);
        if (TablaClientes.getItems().isEmpty()) {
            TablaClientes.setPlaceholder(new Label("No Presenta resultados en la busqueda"));
        }
        NUMEORRESULTADOS.setText(String.valueOf(contadorRegistros));
    }
    
    public boolean FacturacionGrupal(){
          String[] Meses_F = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre",""};
          
        if(NOFACTU.isSelected() && !TablaClientes.getItems().isEmpty()){
             String Detale="Mes "+Meses_F[mesactual]+" "+añoactual;
            for (int i = 0; i < TablaClientes.getItems().size(); i++) {
                if("1".equals(String.valueOf(ESTADO_SERVICIO.getCellObservableValue(i).getValue())) 
                        && 
                   "1".equals(String.valueOf(ESTADO_CLIENTES.getCellObservableValue(i).getValue()))
                      &&
                        Float.parseFloat(String.valueOf(VALOR_MENSUAL.getCellObservableValue(i).getValue()))>0)
                {
                    
                  
                   q.Facturar(
                        String.valueOf(CLIENTES.getCellObservableValue(i).getValue()),
                    String.valueOf(VALOR_MENSUAL.getCellObservableValue(i).getValue())
                    ,Detale
                    ,1
                    , String.valueOf(IDENTIFICACION.getCellObservableValue(i).getValue())
                    ,"1"
                    , "E"); 
                   
                 }else if("0".equals(String.valueOf(ESTADO_SERVICIO.getCellObservableValue(i).getValue())) && 
                         "1".equals(String.valueOf(ESTADO_CLIENTES.getCellObservableValue(i).getValue()))){
                     System.out.println("--------------------");
                    System.out.println(String.valueOf(CLIENTES.getCellObservableValue(i).getValue())); 
                    System.out.println("*************");
                 }
                
            }
            q.notificaciones("Facturado con exito", "I");
            return true;
        }else {
            return false;
        }
    }

    public void CREARALERTASECambiarValor() {
        // Create the custom 
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Generar Facturas");
        dialog.setHeaderText("Solicite al supervisor las credenciales para proceder con el proceso");

// Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
// Set the button types.
        ButtonType loginButtonType = new ButtonType("Generar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        PasswordField password = new PasswordField();
        password.setPromptText("Ingrese las Credenciales de supervisor");
        
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
     

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>("1", password.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            if (usernamePassword.getValue().equals("1725746513")) {
               if(!FacturacionGrupal()){
                  q.notificaciones("No se pudo Completar la accion error de Valores", "I");
                        }else {
                   q.notificaciones("Exito al completar la accion", "I");
               }
               
            }else {
                q.notificaciones("No se pudo Completar la accion error de Credenciales", "I");
            }
        });
    }
}
