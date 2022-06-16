/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import CLASES.EstadoIndivudaulBancosHILO;
import CLASES.cuentasporbanco;
import CLASES.Clase_Estado_Individual;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class FacturasDepositoController implements Initializable {

    @FXML
    private TableView tablaefectivo;
    @FXML
    private TableColumn<String, cuentasporbanco> numerotfactura;
    @FXML
    private TableColumn<String, cuentasporbanco> fecha;
    @FXML
    private TableColumn<String, cuentasporbanco> total;
    @FXML
    private Label cliente;
    @FXML
    private Label totaldeudas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cliente.setText(Clase_Estado_Individual.clientetext);
        numerotfactura.setCellValueFactory(new PropertyValueFactory<>("Clientecobrado"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fechacobrada"));
        total.setCellValueFactory(new PropertyValueFactory<>("Valorcobrado"));
        facturas_Depositos();
    }    
    
    public void facturas_Depositos() {
        tablaefectivo.getItems().clear();
         EstadoIndivudaulBancosHILO task = new EstadoIndivudaulBancosHILO(cliente.getText(),"1","1","0");
          task.setOnSucceeded((succeededEvent) -> {
             tablaefectivo.setItems((ObservableList) task.getValue());
            double vt = 0;
        for (int i = 0; i < tablaefectivo.getItems().size(); i++) {
            vt = vt + Double.parseDouble(String.valueOf(total.getCellObservableValue(i).getValue()).replace(",", "."));
        }
      if(tablaefectivo.getItems().isEmpty()){
                tablaefectivo.setPlaceholder(new Label("No hay Datos de Depositos del Cliente..."));
            }
        
        }
        
        
        );
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
        
    }
    
}
