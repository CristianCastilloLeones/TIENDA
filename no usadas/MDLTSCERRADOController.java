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




/**
 * FXML Controller class
 *
  * @author cl
 */
public class MDLTSCERRADOController implements Initializable {
  Clase_Uso_Tickets objetoClaseTikes= new Clase_Uso_Tickets();
     bas a = new  bas() ;
  
    private    String num;
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
    private TableColumn<String,tikes> Actions;
    @FXML
    private TableColumn<String,tikes> Actions2;
    @FXML
    private TableColumn<String,tikes> Actions3;
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
          buscar.setOnAction((event) -> {
             busqueda();
         });
        buscarponombre.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.ENTER){
                busquedanombre();}
          });
      
        // TODO
         esperando();
         
         tikeprincipal.setOnMouseClicked((event) -> {
             
            if (!tikeprincipal.getSelectionModel().isEmpty()){
                 num= (objetoClaseTikes.getObservable_Contenedor().get(tikeprincipal.getSelectionModel().getSelectedIndex()).getNumero()) ;
                 
            } });
        imprimir.setOnMouseClicked((event) -> {
                  objetoClaseTikes.Reportes_impreciones(num);
        });
        a.prediccion(buscarponombre, objetoClaseTikes.getLista_clientes_Tickets());
       
    } 
     public void busquedanombre(){
     tikeprincipal.setItems(objetoClaseTikes.getObservable_Contenedor());
      Valores_iniciales_Tablas();
     objetoClaseTikes.Busqueda_Nombre_RMAC(buscarponombre.getText());
      }
    
  public void esperando(){

    tikeprincipal.setItems(objetoClaseTikes.getObservable_Contenedor());
    Valores_iniciales_Tablas();
  objetoClaseTikes.Busqueda_RMAC();
   a.prediccion(buscarponombre, objetoClaseTikes.getLista_clientes_Tickets());
       
    }
   @FXML
   private void btonuevoreparacion(ActionEvent event){
      a.ventanas("NUEVOTICKET.fxml");
       
   }
 

    @FXML
    private void actualizar(ActionEvent event) {
        esperando();
    }
   public void busqueda(){
         
           LocalDate value2 =  fechainicial.getValue();
           LocalDate value3 =  fechafinal.getValue();
      tikeprincipal.setItems(objetoClaseTikes.getObservable_Contenedor());
       Valores_iniciales_Tablas();
     objetoClaseTikes.Busqueda_Fecha_RMAC( String.valueOf(value2), String.valueOf(value3));
         
  
    }
   public void Valores_iniciales_Tablas(){
     numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
     departamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
     usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
     asunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
     tecnicoA.setCellValueFactory(new PropertyValueFactory<>("tecnicoA"));
     fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
     ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
     abierto.setCellValueFactory(new PropertyValueFactory<>("abierto"));
     UltimaR.setCellValueFactory(new PropertyValueFactory<>("UltimaR"));
    tikeprincipal.getItems().clear();  
   }
}
