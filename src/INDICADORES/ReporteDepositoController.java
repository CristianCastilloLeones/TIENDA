/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import CLASES.ClaseReporteDeposito;
import CLASES.Clase_Estado_Individual;
import javafxapplication4.ExcelExport;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class ReporteDepositoController implements Initializable {

    Clase_Estado_Individual ob;
    bas q = new bas();
    VariablesDeUso onVariable = new VariablesDeUso();
    ObservableList<ClaseReporteDeposito> ObservaleDepositos = FXCollections.observableArrayList();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    int numero=0;
    @FXML
    private TableView tikeprincipal;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> NUMEROFACTURA;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> numedodeposito;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> FECHAPAGODEPSITO;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> FECHADEPOSITO;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> CAJERODEPOSITO;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> VALORCOBRADODEPOSITODOC;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> VALORCOBRADODEPOSITO;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> IDENTIFICACIONDEPOSITO;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> DETALLEDEPOSITO;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> COMENTARIODEPOSITO;
    @FXML
    private TableColumn<String, ClaseReporteDeposito> IDDEPOSITO;
    @FXML
    private DatePicker rangoinicial;
    @FXML
    private DatePicker rangofinal;
    @FXML
    private Button buscar;
    @FXML
    private ImageView cargando;
    @FXML
    private Button imprimir;
    @FXML
    private TextField TOTALCOBRADO;
    @FXML
    private ComboBox<String> busquedacajero;
    @FXML
    private Label numerodeitem;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tikeprincipal.setRowFactory(tv -> {
            TableRow<ClaseReporteDeposito> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    ClaseReporteDeposito rowData = row.getItem();
                    System.out.println(rowData.getIDDEPOSITO());
                    if (rowData.getIDDEPOSITO().contains("\\")) {
                        q.abriarchivos(rowData.getIDDEPOSITO());
                    } else {
                        q.abriarchivos(q.BusquedadeEvidenciadeposito(rowData.getIDDEPOSITO()));
                    }

                }
            });
            row.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    ClaseReporteDeposito rowData = row.getItem();
                   
                    if (rowData.getIDDEPOSITO().contains("\\")) {
                        q.abriarchivos(rowData.getIDDEPOSITO());
                    } else {
                        q.abriarchivos(q.BusquedadeEvidenciadeposito(rowData.getIDDEPOSITO()));
                    }

                }
            });
            return row;
        });
        /*
        .setOnKeyPressed((KeyEvent event) -> {
            if (!nombrecliente.getText().replace(" ", "").isEmpty()) {
                if (event.getCode() == KeyCode.ENTER) {
                    imagendecarga.setVisible(true);
                    objetoinicial.ValoresIniciales(q.BusquedaCedula(nombrecliente.getText()), nombrecliente.getText());
                    cedulatext.setText(Clase_RecepciondePago.getCedulaCliente());
                    valordebusqueda();
                }
            }
        });
        */
      
        // TODO
        //**************************
        ContextMenu context = new ContextMenu();
        MenuItem Item1 = new MenuItem("Ver Comprobante");
        MenuItem Item2 = new MenuItem("Ver Historial de Cliente");
         MenuItem Item3 = new MenuItem("Ver Historial de Depositos");
          MenuItem Item4 = new MenuItem("Ver Historial de Efectivo");
        Item1.setOnAction((ActionEvent event) -> {
            if (!tikeprincipal.getSelectionModel().isEmpty()) {
                q.abriarchivos(q.BusquedadeEvidenciadeposito(String.valueOf(IDDEPOSITO.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue())));
            }

        });
        Item2.setOnAction((ActionEvent event) -> {
            if (!tikeprincipal.getSelectionModel().isEmpty()) {
                ob = new Clase_Estado_Individual(String.valueOf(IDENTIFICACIONDEPOSITO.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue()));
                ob.cargarvalores();

            }

        });
        Item3.setOnAction((ActionEvent event) -> {
            if (!tikeprincipal.getSelectionModel().isEmpty()) {
               Clase_Estado_Individual.clientetext=String.valueOf(IDENTIFICACIONDEPOSITO.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue());
               q.ventanas("/javafxapplication4/facturasDeposito.fxml");
            }

        });
        Item4.setOnAction((ActionEvent event) -> {
            if (!tikeprincipal.getSelectionModel().isEmpty()) {
               Clase_Estado_Individual.clientetext=String.valueOf(IDENTIFICACIONDEPOSITO.getCellObservableValue(tikeprincipal.getSelectionModel().getSelectedIndex()).getValue());
               q.ventanas("/javafxapplication4/facturasEfectivo.fxml");
            }

        });
        context.getItems().addAll(Item1, Item2,Item3,Item4);
        tikeprincipal.setContextMenu(context);

        //*************************
        rangoinicial.setValue(q.FechaActual());
        rangofinal.setValue(q.FechaActual());
        cargando.setVisible(false);
        tikeprincipal.setPlaceholder(new Label("Seleccione el rango de Fechas y El cajero que desea ver el historial"));
        buscar.setOnAction((event) -> {
            cargando.setVisible(true);
            tikeprincipal.setPlaceholder(new Label("Espere Estamos cargando sus datos"));
            CalculoDeuda();
        });
        imprimir.setOnAction((event) -> {
            try {
                showReport();
            } catch (IOException ex) {
                Logger.getLogger(ReporteCierreCajaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        q.Cajerosdisponibles(busquedacajero);
        ParametrosIniciales();
    }

    public void ParametrosIniciales() {
        tikeprincipal.setItems(ObservaleDepositos);
        NUMEROFACTURA.setCellValueFactory(new PropertyValueFactory<>("NUMEROFACTURA"));
        numedodeposito.setCellValueFactory(new PropertyValueFactory<>("NumeroDeposito"));
        FECHAPAGODEPSITO.setCellValueFactory(new PropertyValueFactory<>("FECHAPAGODEPSITO"));
        FECHADEPOSITO.setCellValueFactory(new PropertyValueFactory<>("FECHADEPOSITO"));
        CAJERODEPOSITO.setCellValueFactory(new PropertyValueFactory<>("CAJERODEPOSITO"));
        VALORCOBRADODEPOSITODOC.setCellValueFactory(new PropertyValueFactory<>("VALORCOBRADODEPOSITODOC"));
        VALORCOBRADODEPOSITO.setCellValueFactory(new PropertyValueFactory<>("VALORCOBRADODEPOSITO"));
        IDENTIFICACIONDEPOSITO.setCellValueFactory(new PropertyValueFactory<>("IDENTIFICACIONDEPOSITO"));
        DETALLEDEPOSITO.setCellValueFactory(new PropertyValueFactory<>("DETALLEDEPOSITO"));
        COMENTARIODEPOSITO.setCellValueFactory(new PropertyValueFactory<>("COMENTARIODEPOSITO"));
        IDDEPOSITO.setCellValueFactory(new PropertyValueFactory<>("IDDEPOSITO"));

    }

    public void llenar() {
        numero=0;
        String Condicionadicional = (busquedacajero.getSelectionModel().getSelectedItem() == null ? " order by IDDEPOSITO asc" : "and GUARDADOPOR LIKE '%" + busquedacajero.getSelectionModel().getSelectedItem() + "%' order by IDDEPOSITO asc");
        ObservaleDepositos.clear();
        tikeprincipal.getItems().clear();
        if(rangoinicial.getValue().getMonthValue()<=5){
           FacturacionAntigua(); 
        }else if (rangoinicial.getValue().getMonthValue()==12 && rangoinicial.getValue().getYear()==2020 ){
            FacturacionAntigua(); 
        }
       
        q.consulta = q.tablas(" SELECT [IDDEPOSITO]\n"
                + "      ,[NUMEROFACTURA]\n"
                + "      ,[CEDULACLIENTE]\n"
                + "      ,[NUMERODECOMPROBANTE]\n"
                + "      ,[FECHADEPAGO]\n"
                + "      ,[FECHADEDOCUMENTO]\n"
                + "      ,[VALORDEPOSITODOCUMENTO]\n"
                + "      ,[VALORCOBRADO]\n"
                + "      ,[DETALLEDEFACTURA]\n"
                + "      ,[COMENTARIO]\n"
                + "      ,[GUARDADOPOR]\n"
                + "  FROM [BDAirnet].[dbo].[tvdjDepositos] "
                + " where  (FECHADEPAGO between '"
                + "" + q.FechaformatCierredeCaja(rangoinicial.getValue().toString()) + "' and '" + q.FechaformatCierredeCaja(rangofinal.getValue().toString()) + "' )" + Condicionadicional);
        try {
            while (q.consulta.next()) {
                if(!numerofactura.contains(q.consulta.getString("NUMEROFACTURA").trim()+q.consulta.getString("CEDULACLIENTE").trim())){
                  ObservaleDepositos.add(new ClaseReporteDeposito(q.consulta.getString("NUMEROFACTURA"),
                        q.consulta.getString("NUMERODECOMPROBANTE"),
                        q.consulta.getString("FECHADEPAGO"),
                        q.consulta.getString("FECHADEDOCUMENTO"),
                        q.consulta.getString("GUARDADOPOR"),
                        q.consulta.getString("VALORDEPOSITODOCUMENTO"),
                        q.consulta.getString("VALORCOBRADO"),
                        q.BusquedaNombreporCedula(q.consulta.getString("CEDULACLIENTE")),
                        q.consulta.getString("DETALLEDEFACTURA").trim(),
                        q.consulta.getString("COMENTARIO"),
                        q.consulta.getString("IDDEPOSITO")));  
                  numero++;
                }
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(ReporteCierreCajaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showReport() throws FileNotFoundException, IOException {
        if (!tikeprincipal.getItems().isEmpty()) {
            ExcelExport valor = new ExcelExport();
            valor.export(tikeprincipal, "Reporte_Depositos");

        } else {
            q.notificaciones("Tabla vacia no se puede realizar la Exportacion", "I");
        }

    }

    public synchronized void CalculoDeuda() {

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                Valor();
                llenar();
                return null;
            }
        };
        task.setOnSucceeded(event -> {
            onVariable.Encerar();
            numerodeitem.setText("N° de items:"+String.valueOf(numero));
            cargando.setVisible(false);
            tikeprincipal.setItems(ObservaleDepositos);
           /* for (int j = 0; j < tikeprincipal.getItems().size(); j++) {
                onVariable.setValorTotal(Double.parseDouble(String.valueOf(VALORCOBRADODEPOSITO.getCellObservableValue(j).getValue())));
            }
            TOTALCOBRADO.setText(q.FormatoDecimal1(onVariable.getValorTotal()));*/
        });
        if (tikeprincipal.getItems().isEmpty()) {
            cargando.setVisible(false);
            tikeprincipal.setPlaceholder(new Label("No hay Datos en los parametros buscados"));
        }
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
List numerofactura= new ArrayList();
    public void FacturacionAntigua() {
        
        String Condicionadicional = (busquedacajero.getSelectionModel().getSelectedItem() == null ? " order by  idfact asc" : " and Facturadopor like '%" + busquedacajero.getSelectionModel().getSelectedItem() + "%' order by  idfact asc");
        
        //ObservaleDepositos.clear();
        tikeprincipal.getItems().clear();
       
        q.consulta = q.tablas("  SELECT nuemrofactura,codigocontrato,fechadepago,total,Facturadopor ,evidencia \n"
                + " FROM [BDAirnet].[dbo].[tvdjFacurascliente] where formapago='Otros' and  "
                + " (CONVERT (date, fechadepago, 103) between '"
                + "" + q.FechaformatCierredeCaja(rangoinicial.getValue().toString()) + "' and '" + q.FechaformatCierredeCaja(rangofinal.getValue().toString()) + "' )" + Condicionadicional);
        try {
            while (q.consulta.next()) {
                ObservaleDepositos.add(new ClaseReporteDeposito(q.consulta.getString("nuemrofactura"),
                        "",
                        q.consulta.getString("fechadepago"),
                        "",
                        q.consulta.getString("Facturadopor"),
                        "",
                        q.consulta.getString("total") ==null ?"25.0":q.consulta.getString("total"),
                        q.BusquedaNombreporCedula(q.consulta.getString("codigocontrato")) ,
                        "",
                        "",
                        q.consulta.getString("evidencia")));
                numerofactura.add(q.consulta.getString("nuemrofactura").trim()+q.consulta.getString("codigocontrato").trim());
                numero++;
                

            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ReporteCierreCajaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Valor(){
        
        try {
            TOTALCOBRADO.setText("");
            q.auxConsulta=q.tablas("SELECT SUM(total)as valorTotal\n" +
                    "                 FROM [BDAirnet].[dbo].[tvdjFacurascliente] where formapago='Otros' and  \n" +
                    "                 (CONVERT (date, fechadepago, 103) between '"+rangoinicial.getValue().format(formatter)+"' and '"+rangofinal.getValue().format(formatter)+"') ");
            while(q.auxConsulta.next()){
                  TOTALCOBRADO.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(q.auxConsulta.getString("valorTotal"))));
                 
            }} catch (SQLException ex) {
            Logger.getLogger(ReporteDepositoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
