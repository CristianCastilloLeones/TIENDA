/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import CLASES.bas;
import CLASES.Clase_Estado_Individual;
import CLASES.ProcesoVistaClientes;
import CLASES.FacturasCobradas;
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
public class FacturasEfectivoController implements Initializable {
 bas q = new bas();
    @FXML
    private TableView tablaefectivo;
    @FXML
    private TableColumn<String, FacturasCobradas> numerotfactura;
    @FXML
    private TableColumn<String, FacturasCobradas> fecha;
    @FXML
    private TableColumn<String, FacturasCobradas> total;
    @FXML
    private Label cliente;
    @FXML
    private Label totaldeduda;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cliente.setText(Clase_Estado_Individual.clientetext);
        numerotfactura.setCellValueFactory(new PropertyValueFactory<>("Clientecobrado"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fechacobrada"));
        total.setCellValueFactory(new PropertyValueFactory<>("Valorcobrado"));
        Facturas_enfectivo();
    }    
    
    public void Facturas_enfectivo() {
      tablaefectivo.getItems().clear();
        ProcesoVistaClientes task = new ProcesoVistaClientes("1",cliente.getText());
        task.setOnRunning((succeesesEvent) -> {
          });
        task.setOnSucceeded((succeededEvent) -> {
            tablaefectivo.setItems((ObservableList) task.getValue());
            double vt = 0;
            for (int i = 0; i < tablaefectivo.getItems().size(); i++) {
                vt = vt + Double.parseDouble(String.valueOf(total.getCellObservableValue(i).getValue()).replace(",", "."));
            }
            
            totaldeduda.setText(q.FormatoDecimal(vt));
            
            if(tablaefectivo.getItems().isEmpty()){
                tablaefectivo.setPlaceholder(new Label("No hay Datos Relacionados al Cliente..."));
            }
        });
        
         ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);   

    }
}
