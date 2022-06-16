/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import CLASES.ClaseFuncinClientes;
import CLASES.ClaseHilosDeudas;
import CLASES.Clase_Estado_Individual;
import CLASES.Clase_clientes;
import javafxapplication4.ExcelExport;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;
import java.sql.SQLException;



/**
 * FXML Controller class
 *
  * @author cl
 */
public class CLIENTES_BController implements Initializable {
    Clase_Estado_Individual ob;
    bas q = new bas();
    @FXML
    private TableView tikeprincipal;
    @FXML
    private TableColumn<String, Clase_clientes> usuario;

    @FXML
    private TableColumn<String, Clase_clientes> cedula;

    @FXML
    private TableColumn<String, Clase_clientes> deuda;
    @FXML
    private Button buscar;

    @FXML
    private ImageView cargando;
    @FXML
    private TextField TOTALDEUDA;
    VariablesDeUso onVariable = new VariablesDeUso();
    @FXML
    private Button imprimir;
    @FXML
    private Button Revision;
    @FXML
    private TableColumn<Integer, Clase_clientes> ID;


   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Revision.setOnAction((event) -> {
            q.ventanasAxu("/INDICADORES/VENTANAAUXILIAR.fxml", "Ventana de Verificacion");
        });
        
        imprimir.setOnAction((event) -> {
           try {
               // --- Show Jasper Report on click-----
               showReport();
           } catch (IOException ex) {
               Logger.getLogger(CLIENTES_BController.class.getName()).log(Level.SEVERE, null, ex);
           }

        
        });
       
      
        
        cargando.setVisible(false);
        tikeprincipal.setPlaceholder(new Label("Presione en Buscar para cargar Datos...."));
        ContextMenu context = new ContextMenu();
        MenuItem Item1 = new MenuItem("Ver Informe Individual");
         MenuItem Item2 = new MenuItem("Ver Detalle de deuda");
         MenuItem Item3 = new MenuItem("Desactivar Cliente de la Base de Datos");
        Item1.setOnAction((event) -> {
           ob = new Clase_Estado_Individual(String.valueOf(usuario.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("A");
        });
        Item2.setOnAction((event) -> {
            ob = new Clase_Estado_Individual(String.valueOf(usuario.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("B");
        });
        Item3.setOnAction((event) -> {
            q.UpdateModificar(" update [BDAirnet].[dbo].[TBCLIENTE] set ESTADO=0 where IDCLIENTE="
                    +String.valueOf(ID.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
             q.DeleteEliminar("delete [BDAirnet].[dbo].[detallesfacxtura] WHERE codigo ='"+String.valueOf(cedula.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue())+"' AND ESTADO=1 ");
        });
        context.getItems().addAll(Item1,Item2,Item3);
        tikeprincipal.setContextMenu(context);
   
     
       
        //   exportar.setVisible(false);
        valoresTabla();
        
        tikeprincipal.setTooltip(new Tooltip("Haga Click Derecho para ver mas detalles Inviduales"));
        buscar.setTooltip(new Tooltip("Haga Click para empezar la Busqueda"));
        buscar.setOnAction((event) -> {
            ValorReuda();
        });
        

        // valoresInicales();
    }

    public void showReport() throws FileNotFoundException, IOException {
        if (!tikeprincipal.getItems().isEmpty()) {
             ExcelExport  valor = new ExcelExport();
                 valor.export(tikeprincipal,"Reporte_Clientes");
              
            }else {
            q.notificaciones("Tabla vacia no se puede realizar la Exportacion", "I");
        }
       
     }
    ClaseHilosDeudas hilo;
    public void valoresInicales() {
        cargando.setVisible(true);
        
        onVariable.setValorTotal(0);
      
        ClaseFuncinClientes  obdeuda = new ClaseFuncinClientes();
        try {
            obdeuda.DeudasporNumerocedulaAgrupados(tikeprincipal, usuario, TOTALDEUDA);
              } catch (SQLException ex) {
            Logger.getLogger(CLIENTES_BController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public synchronized  void ValorReuda() {
         tikeprincipal.setPlaceholder(new Label("Espere estamos cargando sus Datos"));
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                valoresInicales();
          return null ; }
        };
      task.setOnSucceeded(event -> {
          cargando.setVisible(false);
           onVariable.setValorTotal(0);

            //   cargando.setVisible(false);
            for (int j = 0; j < tikeprincipal.getItems().size(); j++) {
                onVariable.setValorTotal(q.ConvertidorStringaDouble(String.valueOf(deuda.getCellObservableValue(j).getValue())));
            }
             TOTALDEUDA.setText(q.FormatoDecimal1(onVariable.getValorTotal()));
    });
    Thread t = new Thread(task);
    //t.setDaemon(true);
    t.start();
    }
    
    
    public void valoresTabla() {
        usuario.setCellValueFactory(new PropertyValueFactory<>("Clientes"));
       
        cedula.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
       
        deuda.setCellValueFactory(new PropertyValueFactory<>("valores"));
      
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tikeprincipal.getItems().clear();
    }

   

}
