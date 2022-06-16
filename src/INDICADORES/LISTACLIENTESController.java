/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import CLASES.Clase_Estado_Individual;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import CLASES.LISTACLIENTESCLASS;
import CLASES.bas;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class LISTACLIENTESController implements Initializable {
     ObservableList<LISTACLIENTESCLASS> productos = FXCollections.observableArrayList();
     bas q = new bas();
    @FXML
    private TableView tikeprincipal;
    @FXML
    private TableColumn<String, LISTACLIENTESCLASS> usuario;
    @FXML
    private TableColumn<String, LISTACLIENTESCLASS>  IP;
    @FXML
    private TableColumn<String, LISTACLIENTESCLASS>  red;
    @FXML
    private Button buscar;
    @FXML
    private RadioButton fo;
    @FXML
    private RadioButton radio;
    @FXML
    private DatePicker fin;
    @FXML
    private DatePicker inicio;
    @FXML
    private Label total;
    final ToggleGroup group1 = new ToggleGroup();
    Clase_Estado_Individual ob;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fo.setToggleGroup(group1);
        radio.setToggleGroup(group1);
    tikeprincipal.setItems(productos);
    usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
    IP.setCellValueFactory(new PropertyValueFactory<>("IP"));
    red.setCellValueFactory(new PropertyValueFactory<>("red"));
    fo.setSelected(true);
    inicio.setValue(q.FechaActual());
    fin.setValue(q.FechaActual());
    //Buscar();
    ContextMenu context = new ContextMenu();
         MenuItem Item1 = new MenuItem("Ver Cliente");
         MenuItem Item2 = new MenuItem("Facturar Cliente");
         MenuItem Item3 = new MenuItem("Historial de Pago");
           context.getItems().addAll(Item1,Item2,Item3);
            tikeprincipal.setContextMenu(context);
           Item1.setOnAction((event) -> {
            ob = new Clase_Estado_Individual(String.valueOf(usuario.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("D");
            
        });
           Item2.setOnAction((event) -> {
            ob = new Clase_Estado_Individual(String.valueOf(usuario.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("A");
        });
           Item3.setOnAction((event) -> {
            ob = new Clase_Estado_Individual(String.valueOf(usuario.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("B");
            ob.cargarvalores2("C");
        });
    buscar.setOnAction((event) -> {
        total.setText("0");
        Buscar();
    });
    }    
    
    public void Buscar(){
        int contador =0;
         tikeprincipal.getItems().clear();
      String consulta = "SELECT [ipv4]\n" +
"      ,[nodoprincipal]\n" +
"      ,[plancontratado]\n" +
"      ,([NOMBRES]+' '+ [APELLIDOS]) as CLIENTE\n" +
"      ,[VALORMENSUAL]\n" +
"      ,[VALORINTALACION]\n" +
"      ,[TIPOINSTALACION]\n" +
"      ,[FECHAINGRESO]\n" +
"  FROM [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where ESTADO =1 " ;
      if (radio.isSelected()){
           consulta  = consulta + "and TIPOINSTALACION ='Radio Enlace'";
      }else {
            consulta  = consulta + "and TIPOINSTALACION ='Fibra Óptica'";
      }
      consulta = consulta + "and  FECHAINGRESO between "
              + "'"+q.FechaformatCierredeCaja(inicio.getValue().toString())+" 00:00:00' and "
              + "'"+q.FechaformatCierredeCaja(fin.getValue().toString())+" 23:59:00'";
      
      q.auxConsulta = q.tablas(consulta);
         try {
             while (q.auxConsulta.next()) {
                 
                 productos.add(new LISTACLIENTESCLASS(q.auxConsulta.getString("CLIENTE")
                         , q.auxConsulta.getString("ipv4").replace(" ", "")
                         , q.auxConsulta.getString("nodoprincipal").replace(" ", "")));
                 q.auxConsulta.getString("CLIENTE");
                 q.auxConsulta.getString("ipv4");
                 q.auxConsulta.getString("nodoprincipal");
                 contador++;
             }  } catch (SQLException ex) {
             Logger.getLogger(LISTACLIENTESController.class.getName()).log(Level.SEVERE, null, ex);
         }
         tikeprincipal.setItems(productos);
         if(tikeprincipal.getItems().isEmpty()){
             tikeprincipal.setPlaceholder(new Label("No Presenta resultados en la busqueda"));
         }
         total.setText(String.valueOf(contador));
    }
    
    
}
