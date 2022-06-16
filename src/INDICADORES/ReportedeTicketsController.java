/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import CLASES.Clase_Uso_Tickets;
import CLASES.bas;
import CLASES.tikes;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class ReportedeTicketsController implements Initializable {
 Clase_Uso_Tickets objetoClaseTikes= new Clase_Uso_Tickets();
 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  bas a = new  bas() ;
    @FXML
    private TextField buscarponombre;
    @FXML
    private TableView tikeprincipal;
    @FXML
    private TableColumn<String,tikes> numero;
    @FXML
    private TableColumn<String,tikes> departamento;
    @FXML
    private TableColumn<String,tikes> tecnicoA;
    @FXML
    private TableColumn<String,tikes> fecha;
    @FXML
    private TableColumn<String,tikes> ubicacion;
    @FXML
    private TableColumn<String,tikes> abierto;
    @FXML
    private TableColumn<String,tikes> UltimaR;
    @FXML
    private TableColumn<String,tikes> usuario;
    @FXML
    private TableColumn<String,tikes> asunto;
    @FXML
    private DatePicker fechainicial;
    @FXML
    private DatePicker fechafinal;
    @FXML
    private Button buscar;
    @FXML
    private Button reporte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fechainicial.setValue(LocalDate.now());
        fechafinal.setValue(LocalDate.now());
        objetoClaseTikes.Inicial_Reporte();
        a.prediccion(buscarponombre, objetoClaseTikes.getLista_clientes_Tickets());
        
        buscar.setOnAction((event) -> {
            busquedatikec();
        });
        reporte.setOnAction((event) -> {
           LocalDate value2 =  fechainicial.getValue();
          LocalDate value3 =  fechafinal.getValue();
          if(String.valueOf(value2).equals(null) || String.valueOf(value3).equals(null) ){
              a.notificaciones("DEBE INGRESAR EL RAGO DE BUSQUEDA", "I");
          }else {
              objetoClaseTikes.Reportes_impreciones_General(buscarponombre.getText(), String.valueOf(value2.format(formatter)), String.valueOf(value3.format(formatter)));
          }
            
        });
    }    

    @FXML
    private void btonuevoreparacion(ActionEvent event) {
        
    }

    @FXML
    private void actualizar(ActionEvent event) {
       Valores_iniciales_Tablas();
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
     tikeprincipal.getItems().clear();
     }
    
    public void busquedatikec(){
          LocalDate value2 =  fechainicial.getValue();
          LocalDate value3 =  fechafinal.getValue();
         
           
          tikeprincipal.setItems(objetoClaseTikes.getObservable_Contenedor());
         //where  asunto='"+tipo+"'and  estado = 'Cerrada' and (fecha between '"+fci+"' and '"+fci3+"')"
         Valores_iniciales_Tablas();
      objetoClaseTikes.Funciones_Reportes(buscarponombre.getText(), String.valueOf(value2.format(formatter)), String.valueOf(value3.format(formatter)));
    }
}
