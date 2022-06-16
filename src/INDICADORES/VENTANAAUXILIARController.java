/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import CLASES.ClaseFuncinClientes;
import CLASES.Clase_Estado_Individual;
import CLASES.Clase_clientes;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class VENTANAAUXILIARController implements Initializable {
    bas q = new bas ();
    Clase_Estado_Individual ob;
    @FXML
    private TableColumn<String, Clase_clientes> CLIENTE;
    @FXML
    private TableColumn<String, Clase_clientes>  IDENTIFICACION;
    @FXML
    private TableColumn<String, Clase_clientes>  DEUDA;
    @FXML
    private TextField TOTALDEUDA;
    @FXML
    private TableView tikeprincipal;
    @FXML
    private Button refresh;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh.setOnAction((event) -> {
           ValorReuda(); 
        });
        
        valoresTabla();
        ContextMenu context = new ContextMenu();
        MenuItem Item1 = new MenuItem("Deudas");
         MenuItem Item2 = new MenuItem("Pagos Efectivo");
         MenuItem Item3 = new MenuItem("Pagos Deposito");
         MenuItem Item4 = new MenuItem("Datos");
        Item1.setOnAction((event) -> {
            ob = new Clase_Estado_Individual(String.valueOf(CLIENTE.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("A");
        });
        Item2.setOnAction((event) -> {
            ob = new Clase_Estado_Individual(String.valueOf(CLIENTE.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("B");
        });
        Item3.setOnAction((event) -> {
            ob = new Clase_Estado_Individual(String.valueOf(CLIENTE.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("C");
        });
        Item4.setOnAction((event) -> {
            ob = new Clase_Estado_Individual(String.valueOf(CLIENTE.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("D");
        });
        context.getItems().addAll(Item1,Item2,Item3,Item4);
        tikeprincipal.setContextMenu(context);
        ValorReuda();
        tikeprincipal.setRowFactory(TableView -> {
            
             TableRow<Clase_clientes> fila= new TableRow<>();
             //if(fila.itemProperty().getValue().getClientes().contains("Total de Deuda Plan"))
            // fila.setBackground(new Background(new BackgroundFill(Paint.valueOf("#1CDBDB"), CornerRadii.EMPTY, Insets.EMPTY)));
             fila.itemProperty().addListener((observable, oldValue, newValue) -> {
            PseudoClass higlighted3 = PseudoClass.getPseudoClass("highlighted7");
             fila.pseudoClassStateChanged(higlighted3, observable.getValue().getClientes().contains("Plan")) ;
             fila.pseudoClassStateChanged(higlighted3, observable.getValue().getClientes().contains("Total de Deuda Plan")) ;
            });
            return fila;
        });
    }    
    
     
    public void valoresTabla() {
        CLIENTE.setCellValueFactory(new PropertyValueFactory<>("Clientes"));

        IDENTIFICACION.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        DEUDA.setCellValueFactory(new PropertyValueFactory<>("valores"));
        tikeprincipal.getItems().clear();
    }
     VariablesDeUso onVariable = new VariablesDeUso();
    public void valoresInicales() {
     
        
        onVariable.setValorTotal(0);
        ClaseFuncinClientes  obdeuda = new ClaseFuncinClientes();
        obdeuda.NuevaEstilodBusqueda(tikeprincipal, CLIENTE, TOTALDEUDA);
    }
    
     public   void ValorReuda() {
        
        
         tikeprincipal.setCursor(Cursor.WAIT);
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                valoresInicales();
          return null ; }
        };
      task.setOnSucceeded(event -> {
         tikeprincipal.setCursor(Cursor.DEFAULT);
         

            //   cargando.setVisible(false);
            
    });
    Thread t = new Thread(task);
    //t.setDaemon(true);
    t.start();
    }
     
   
}
