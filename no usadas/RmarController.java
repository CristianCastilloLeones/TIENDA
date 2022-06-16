/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import static javafxapplication4.Ventana_PrincipalController.val;



/**
 * FXML Controller class
 *
  * @author cl
 */
public class RmarController implements Initializable {
   Clase_Uso_Tickets objetoClaseTikes= new Clase_Uso_Tickets();
   
     String num;

    bas a = new  bas() ;
   
     @FXML
     private TableColumn <String,tikes> numero = new  TableColumn<>("Numero");
     @FXML
     private TableColumn <String,tikes> departamento = new  TableColumn<>("Departamento");
     @FXML
     private TableColumn <String,tikes> usuario = new  TableColumn<>("Cliente");
     @FXML
     private TableColumn <String,tikes> asunto = new  TableColumn<>("Asunto");
     @FXML
     private TableColumn <String,tikes> tecnicoA = new  TableColumn<>("TecnicoA");
     @FXML
     private TableColumn <String,tikes> fecha = new  TableColumn<>("Fecha");
     @FXML
     private TableColumn <String,tikes> ubicacion = new  TableColumn<>("Ubicacion");
     @FXML
     private TableColumn <String,tikes> abierto = new  TableColumn<>("Abierto");
     @FXML
     private TableColumn <String,tikes> UltimaR = new  TableColumn<>("UltimaR");
    @FXML
    private TextField buscarponombre;
    @FXML
    private TableView tikeprincipal;
    @FXML
    private TableColumn<String,tikes> botones;
    @FXML
    private TableColumn<String,tikes>  Actions;
    @FXML
    private TableColumn<String,tikes>  Actions2;
    @FXML
    private TableColumn<String,tikes>  Actions3;
    @FXML
    private TableColumn<String,tikes> Actions4;
    @FXML
    private Button imprimir;
    @FXML
    private DatePicker fechainicial;
    @FXML
    private DatePicker fechafinal;
    @FXML
    private Button buscar;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         buscar.setOnAction((event) -> {
             busqueda();
         });
        esperando();
        a.prediccion(buscarponombre, objetoClaseTikes.getLista_clientes_Tickets());
        
         buscarponombre.setOnKeyPressed((KeyEvent event) -> {
             if(event.getCode() == KeyCode.ENTER){
                 busquedanombre();}
         });
        
        tikeprincipal.setOnMouseClicked((event) -> {
             
            if (!tikeprincipal.getSelectionModel().isEmpty()){
                 num= (objetoClaseTikes.getObservable_Contenedor().get(tikeprincipal.getSelectionModel().getSelectedIndex()).getNumero()) ;
                val=num;
            }
            
             
         });
        imprimir.setOnMouseClicked((event) -> {
                     objetoClaseTikes.Reportes_impreciones(num);
        });
        
    }    
    public void esperando(){ 
     tikeprincipal.setItems(objetoClaseTikes.getObservable_Contenedor());
     Valores_iniciales_Tablas();
     objetoClaseTikes.Busqueda_general_RMAR();
     a.prediccion(buscarponombre, objetoClaseTikes.getLista_clientes_Tickets());
    }
     @FXML
   private void btonuevoreparacion(ActionEvent event){
      a.ventanas("NUEVOTICKET.fxml");
     
   }
   
   
   public void busquedanombre(){
    tikeprincipal.setItems(objetoClaseTikes.getObservable_Contenedor());
     Valores_iniciales_Tablas();
     objetoClaseTikes.Busqueda_Nombre_RMAR(buscarponombre.getText());
       
          
    }

    @FXML
    private void actualizar(ActionEvent event) {
        esperando();
    }
    
     public void busqueda(){
          LocalDate value2 =  fechainicial.getValue();
          LocalDate value3 =  fechafinal.getValue();
          tikeprincipal.setItems(objetoClaseTikes.getObservable_Contenedor());
          
         objetoClaseTikes.Busqueda_Fecha_RMAR(String.valueOf(value2), String.valueOf(value3));
      
    }
     public void Valores_iniciales_Tablas(){
     numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
     departamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
     usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
     tecnicoA.setCellValueFactory(new PropertyValueFactory<>("tecnicoA"));
     asunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
     fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
     ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion")); 
     abierto.setCellValueFactory(new PropertyValueFactory<>("abierto"));
     UltimaR.setCellValueFactory(new PropertyValueFactory<>("UltimaR"));
     Actions.setCellValueFactory(new PropertyValueFactory<>("button"));
     Actions2.setCellValueFactory(new PropertyValueFactory<>("button2"));
     Actions3.setCellValueFactory(new PropertyValueFactory<>("button3"));
     Actions4.setCellValueFactory(new PropertyValueFactory<>("button4"));
     tikeprincipal.getItems().clear();
     }
}
