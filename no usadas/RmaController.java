/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import static javafxapplication4.Ventana_PrincipalController.val;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
/**
 * FXML Controller class
 *
  * @author cl
 */
public class RmaController implements Initializable {
    bas a = new  bas() ;
    Clase_Uso_Tickets usoTikects=new Clase_Uso_Tickets();
    @FXML
    TableColumn botones = new TableColumn("Controles");
@FXML
TableView  tikeprincipal;
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
   private TableColumn <Date,tikes> fecha = new  TableColumn<>("Fecha");
   @FXML
    private TableColumn <String,tikes> ubicacion = new  TableColumn<>("Ubicacion");
   @FXML 
   private TableColumn <String,tikes> abierto = new  TableColumn<>("Abierto");
   @FXML
   private TableColumn <String,tikes> UltimaR = new  TableColumn<>("UltimaR");
   @FXML
   private TableColumn <String,tikes> Actions = new  TableColumn<>("");  
   @FXML
   private TableColumn<String,tikes> Actions2 = new  TableColumn<>("");
   @FXML
   private TableColumn<String,tikes> Actions3= new  TableColumn<>("");;
   @FXML
   private TableColumn<String,tikes> Actions4= new  TableColumn<>("");
    @FXML
    private TextField buscarponombre;
    @FXML
    private DatePicker fechainicial;
    @FXML
    private DatePicker fechafinal;
    @FXML
    private Button buscar;
    @FXML
    private Button imprimir;
    /**
     * Initializes the controller class.
     */
    
    @FXML
   private void btonuevoreparacion(ActionEvent event){
      a.ventanas("NUEVOTICKET.fxml");
        
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          buscar.setOnAction((event) -> {
             busqueda();
         });
        imprimir.setOnAction((event) -> {
        
            try {
                imprimir();
            } catch (JRException | IOException ex) {
                Logger.getLogger(RmaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
       buscar.setOnAction((event) -> {
         busqueda();
         colores();
       
      });
       
        tikeprincipal.setOnMouseClicked((event) -> {
        if (!tikeprincipal.getSelectionModel().isEmpty()){
                 val= (usoTikects.getObservable_Contenedor().get(tikeprincipal.getSelectionModel().getSelectedIndex()).getNumero()) ;}});
         a.prediccion(buscarponombre, usoTikects.getLista_clientes_Tickets());
         buscarponombre.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
                busquedanombre();
            
            }} });
         
          esperando();
          colores();
    }  
      @FXML
    public void actualizar (ActionEvent event){
        esperando();
          colores();}
    private  void  esperando(){
     tikeprincipal.setItems(usoTikects.getObservable_Contenedor());
     Valores_inicialesTablas();
     usoTikects.Busqueda_general();
     a.prediccion(buscarponombre, usoTikects.getLista_clientes_Tickets()); 
     
    }
    private void busqueda(){
           LocalDate value2 =  fechainicial.getValue();
           String fci = String.valueOf(value2);
           LocalDate value3 =  fechafinal.getValue();
           String fci3 = String.valueOf(value3);
           tikeprincipal.setItems(usoTikects.getObservable_Contenedor());
           Valores_inicialesTablas();
           usoTikects.Busqueda_Fecha(fci, fci3);
     }
    private void busquedanombre(){
     tikeprincipal.setItems(usoTikects.getObservable_Contenedor());
     Valores_inicialesTablas();
     usoTikects.Busqueda_nombre(buscarponombre.getText());}
    private void imprimir () throws JRException, IOException {
        usoTikects.Reportes_impreciones(val);
           String jr =null;
                     try {        
                            Map parametro = new HashMap();
                            parametro.put("CONDICION","Espera");
                            parametro.put("CONDICION2"," ");
                            jr = "\\\\S-AIRCONTROL\\Nuevo Systema\\ArchivosdeImpresion\\Reporteenespera.jasper";
                            String printFileName;
                            printFileName = JasperFillManager.fillReportToFile(jr, parametro,a.cone());
                  a.pdfg(printFileName);
             } catch (SQLServerException ex) {
                 System.out.println(ex.getMessage());
             }
    }
    
 
 
private void colores(){
    Calendar fecha1 = Calendar.getInstance();
    fecha1 = Calendar.getInstance();
        String fecha24=String.valueOf(fecha1.getTime().getYear()+1900)+"/"+String.valueOf(fecha1.getTime().getMonth()+1)+"/"+String.valueOf(fecha1.getTime().getDate()-1);
        String fecha48=String.valueOf(fecha1.getTime().getYear()+1900)+"/"+String.valueOf(fecha1.getTime().getMonth()+1)+"/"+String.valueOf(fecha1.getTime().getDate()-1);
            PseudoClass higlighted2 =PseudoClass.getPseudoClass("highlighted1");
            PseudoClass higlighted3 =PseudoClass.getPseudoClass("highlighted2");
            tikeprincipal.setRowFactory(TableView -> {
             TableRow<tikes> fila= new TableRow<>();
             fila.itemProperty().addListener((observable, oldValue, newValue) -> {
             fila.pseudoClassStateChanged(higlighted2, observable.getValue().getFecha().equals(new Date(fecha24))) ;
             fila.pseudoClassStateChanged(higlighted3, observable.getValue().getFecha().before(new Date(fecha48))) ;
             });
             return fila;
         }); 
}
public void Valores_inicialesTablas(){
     numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
     departamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
     usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
     asunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
     tecnicoA.setCellValueFactory(new PropertyValueFactory<>("tecnicoA"));
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
