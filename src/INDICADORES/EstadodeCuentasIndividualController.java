/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import CLASES.Clase_Estado_Individual;
import CLASES.EstadoIndivudaulBancosHILO;
import CLASES.Eventos;
import CLASES.EventosEstadoIndividual;
import CLASES.FacturasCobradas;
import CLASES.FacturasporCobrado;
import CLASES.FacturasporCobrarIndivualHilo;
import CLASES.ProcesoVistaClientes;
import CLASES.bas;
import CLASES.cuentasporbanco;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class EstadodeCuentasIndividualController implements Initializable {
    
   
    bas q = new bas();
    @FXML
    private Button realizarbusqueda;
    @FXML
    private TableView tablafacturacliente;
    @FXML
    private TableColumn<String, FacturasCobradas> cliente;
    @FXML
    private TableColumn<String, FacturasCobradas> fecha;
    @FXML
    private TableColumn<String, FacturasCobradas> valor;
    @FXML
    private TableView facturasdepositocliente;
    @FXML
    private TableColumn<String, cuentasporbanco> clientedepsoito;
    @FXML
    private TableColumn<String, cuentasporbanco> fechadeposito;
    @FXML
    private TableColumn<String, cuentasporbanco> valordeposito;
    @FXML
    private TableView eventoscliente;
    @FXML
    private TableColumn<String, Eventos> clienteeve;
    @FXML
    private TableColumn<String, Eventos> causa;
    @FXML
    private TableColumn<String, Eventos> estado;
    @FXML
    private TableView facturasporcobrartablacliente;
    @FXML
    private TableColumn<String, FacturasporCobrado> clientefactxcobrar;
    @FXML
    private TableColumn<String, FacturasporCobrado> fechaporcobrar;
    @FXML
    private TableColumn<String, FacturasporCobrado> valorporcobrar;
    @FXML
    private TextField clientetext;
    @FXML
    private Label efectivolabel;
    @FXML
    private Label Deudaslabel;
    @FXML
    private Label depsotislabel;
    @FXML
    private Button verfactu;
    @FXML
    private Button CortedeServicio;
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CortedeServicio.setOnAction((event) -> {
            q.ConseguiriCorte(clientetext.getText());
        });
        // TODO
        clientetext.setDisable(true);
        clientetext.setText(Clase_Estado_Individual.clientetext);
        Valores_inicales_Tabla();
        clientetext.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    Facturas_enfectivo();
                    facturas_Depositos();
                    Facturas_Eventos();
                    Facturas_x_Cobrar();
                   
                }
            }
        });
        verfactu.setOnAction((event) -> {
            q.CargarPropiedades();
            if (!clientetext.getText().replace(" ", "").isEmpty()) {
                File objetofile = new File( q.properties.getProperty("RutaArchivos")+"\\Clientes\\" + clientetext.getText().replace(" ", "") + "\\" + "Facturas");
                try {
                    Desktop.getDesktop().open(objetofile);
                } catch (IOException ex) {
                    Logger.getLogger(EstadodeCuentasIndividualController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
            
        realizarbusqueda.setOnAction((event) -> {
            Facturas_enfectivo();
            facturas_Depositos();
            Facturas_Eventos();
            Facturas_x_Cobrar();
          
        });
       
            Facturas_enfectivo();
            facturas_Depositos();
            Facturas_Eventos();
            Facturas_x_Cobrar();
       

    }

    public void Valores_inicales_Tabla() {
        cliente.setCellValueFactory(new PropertyValueFactory<>("Clientecobrado"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fechacobrada"));
        valor.setCellValueFactory(new PropertyValueFactory<>("Valorcobrado"));
        clientedepsoito.setCellValueFactory(new PropertyValueFactory<>("Clientecobrado"));
        fechadeposito.setCellValueFactory(new PropertyValueFactory<>("fechacobrada"));
        valordeposito.setCellValueFactory(new PropertyValueFactory<>("Valorcobrado"));
        clienteeve.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        causa.setCellValueFactory(new PropertyValueFactory<>("causa"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        clientefactxcobrar.setCellValueFactory(new PropertyValueFactory<>("clienteporcobrar"));
        fechaporcobrar.setCellValueFactory(new PropertyValueFactory<>("Fechaporcobrar"));
        valorporcobrar.setCellValueFactory(new PropertyValueFactory<>("Valorporcobrar"));
        
    }

    public void Facturas_enfectivo() {
      tablafacturacliente.getItems().clear();
        ProcesoVistaClientes task = new ProcesoVistaClientes("1",clientetext.getText());
        task.setOnRunning((succeesesEvent) -> {
          });
        task.setOnSucceeded((succeededEvent) -> {
            tablafacturacliente.setItems((ObservableList) task.getValue());
            double vt = 0;
            for (int i = 0; i < tablafacturacliente.getItems().size(); i++) {
                vt = vt + Double.parseDouble(String.valueOf(valor.getCellObservableValue(i).getValue()).replace(",", "."));
            }
            
            efectivolabel.setText(q.FormatoDecimal(vt));
            
            if(tablafacturacliente.getItems().isEmpty()){
                tablafacturacliente.setPlaceholder(new Label("No hay Datos Relacionados al Cliente..."));
            }
        });
        
         ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);   

    }

    public void facturas_Depositos() {
        facturasdepositocliente.getItems().clear();
         EstadoIndivudaulBancosHILO task = new EstadoIndivudaulBancosHILO(clientetext.getText(),"1","1","");
          task.setOnSucceeded((succeededEvent) -> {
             facturasdepositocliente.setItems((ObservableList) task.getValue());
            double vt = 0;
        for (int i = 0; i < facturasdepositocliente.getItems().size(); i++) {
            vt = vt + Double.parseDouble(String.valueOf(valordeposito.getCellObservableValue(i).getValue()).replace(",", "."));
        }
      if(facturasdepositocliente.getItems().isEmpty()){
                facturasdepositocliente.setPlaceholder(new Label("No hay Datos de Depositos del Cliente..."));
            }
        
        }
        
        
        );
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
        
    }

    public void Facturas_Eventos()  {
        
        eventoscliente.getItems().clear();
        EventosEstadoIndividual task = new EventosEstadoIndividual(clientetext.getText(),"1","1","");
        task.setOnRunning((succeesesEvent) -> {
          
        });
        task.setOnSucceeded((succeededEvent) -> {
             eventoscliente.setItems((ObservableList) task.getValue());
             
          if(eventoscliente.getItems().isEmpty()){
                eventoscliente.setPlaceholder(new Label("No Registra Tickets de servicio"));
            }
        
        
        });
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
    }

    public void Facturas_x_Cobrar() {
        facturasporcobrartablacliente.getItems().clear();
       
      //  HiloRecepciondePago task = new HiloRecepciondePago(q.BusquedaCedula(clientetext.getText()),"E");
        
         FacturasporCobrarIndivualHilo task = new FacturasporCobrarIndivualHilo(clientetext.getText());
        task.setOnRunning((succeesesEvent) -> {
          
        });
        task.setOnSucceeded((succeededEvent) -> {
            
             facturasporcobrartablacliente.setItems((ObservableList) task.getValue());
          double   vt = 0;
        for (int i = 0; i < facturasporcobrartablacliente.getItems().size(); i++) {
            vt = vt + Double.parseDouble(String.valueOf(valorporcobrar.getCellObservableValue(i).getValue()).replace(",", "."));
        }
        Deudaslabel.setText(q.FormatoDecimal(vt));
        if(facturasporcobrartablacliente.getItems().isEmpty()){
                facturasporcobrartablacliente.setPlaceholder(new Label("El Cliente no presenta deudas"));
            }
        
        });
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
    }


}
