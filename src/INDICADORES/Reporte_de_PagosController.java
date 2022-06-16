/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import CLASES.Clase_Estado_Individual;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import CLASES.Clase_RecepciondePago;
import CLASES.ClasedePropiedades;
import javafxapplication4.ExcelExport;
import CLASES.FacturasCobradas;
import CLASES.ProcesoVistaClientes;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class Reporte_de_PagosController implements Initializable {

    bas q = new bas();
    @FXML
    private TextField CLIENTE;
    @FXML
    private TableView historialpago;
    @FXML
    private TableColumn<String, FacturasCobradas> Fechatrasaccion;
    @FXML
    private TableColumn<String, FacturasCobradas> numerofactura;
    @FXML
    private TableColumn<String, FacturasCobradas> detalleFacturaPagadas;
    @FXML
    private TableColumn<String, FacturasCobradas> totalfacturaspagadas;
    @FXML
    private TableColumn<String, FacturasCobradas> DEBE;
    @FXML
    private TableColumn<String, FacturasCobradas> SALDO;
    @FXML
    private Button exportar;
    @FXML
    private Button imprimirreporte;
    @FXML
    private Label T3;
    @FXML
    private Label T2;
    @FXML
    private Label T1;
    VariablesDeUso onVariable = new VariablesDeUso();
    Clase_RecepciondePago objetoinicial = new Clase_RecepciondePago();
    ClasedePropiedades propie = new ClasedePropiedades();

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
if(Clase_Estado_Individual.clientetext!=null){
    CLIENTE.setText(Clase_Estado_Individual.clientetext);
    facturasCancelada();
}else {
    
}
        
        imprimirreporte.setOnAction((event) -> {
            q.notificaciones("Archivo aun no elaborado", "I");
        });
        q.listaclient(onVariable.getListadeClientes());
        q.prediccion(CLIENTE, onVariable.getListadeClientes());

        ValoresIiniciale();
        exportar.setOnAction((event) -> {
            try {
                showReport();
            } catch (IOException ex) {
                Logger.getLogger(Reporte_de_PagosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        CLIENTE.setOnKeyPressed((KeyEvent event) -> {
            if (!CLIENTE.getText().replace(" ", "").isEmpty()) {
                if (event.getCode() == KeyCode.ENTER) {
                    facturasCancelada();
                }
            }
        });
    }

    public void ValoresIiniciale() {
        Fechatrasaccion.setCellValueFactory(new PropertyValueFactory<>("FechadeRegistro"));
        numerofactura.setCellValueFactory(new PropertyValueFactory<>("Clientecobrado"));
        detalleFacturaPagadas.setCellValueFactory(new PropertyValueFactory<>("fechacobrada"));
        totalfacturaspagadas.setCellValueFactory(new PropertyValueFactory<>("Valorcobrado"));
        DEBE.setCellValueFactory(new PropertyValueFactory<>("ValorDeuda"));
        SALDO.setCellValueFactory(new PropertyValueFactory<>("Saldo"));
    }

    public void facturasCancelada() {
        historialpago.getItems().clear();
        ProcesoVistaClientes task = new ProcesoVistaClientes("2", CLIENTE.getText());
        task.setOnRunning((succeesesEvent) -> {
        });
        task.setOnSucceeded((succeededEvent) -> {
            historialpago.setItems((ObservableList) task.getValue());
            Sumas();
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
    }

    public void showReport() throws FileNotFoundException, IOException {
        if (!historialpago.getItems().isEmpty()) {
            ExcelExport valor = new ExcelExport();
            valor.export(historialpago, "Reporte de " + CLIENTE.getText());

        } else {
            q.notificaciones("Tabla vacia, no se puede realizar la Exportacion", "I");
        }

    }

    public void Sumas() {
        onVariable.Encerar();
        for (int i = 0; i < historialpago.getItems().size(); i++) {
            onVariable.setValorTotal(q.ConvertidorStringaDouble(String.valueOf(DEBE.getCellObservableValue(i).getValue()).contains("-")?"0":String.valueOf(DEBE.getCellObservableValue(i).getValue())));
        }
        T1.setText(q.FormatoDecimal(onVariable.getValorTotal()));
        onVariable.Encerar();
        for (int i = 0; i < historialpago.getItems().size(); i++) {
            onVariable.setValorTotal(q.ConvertidorStringaDouble(String.valueOf(totalfacturaspagadas.getCellObservableValue(i).getValue()).contains("-")?"0":String.valueOf(totalfacturaspagadas.getCellObservableValue(i).getValue())));
        }
        T2.setText(q.FormatoDecimal(onVariable.getValorTotal()));
        T3.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(T1.getText())-q.ConvertidorStringaDouble(T2.getText())));
    }

}
