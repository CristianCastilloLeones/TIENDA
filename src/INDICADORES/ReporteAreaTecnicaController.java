/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import CLASES.ClaseUtilidadesdeEmpleados;
import CLASES.Clase_Uso_Tickets;
import CLASES.bas;
import CLASES.tikes;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class ReporteAreaTecnicaController implements Initializable {

    Clase_Uso_Tickets onj = new Clase_Uso_Tickets();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
 //   List list = new ArrayList();
    bas q = new bas();
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    String valorres = null;
    @FXML
    private TextField buscarponombre;
    @FXML
    private TableView tikeprincipal;
    @FXML
    private TableColumn<String, tikes> numero;
    @FXML
    private TableColumn<String, tikes> usuario;
    @FXML
    private TableColumn<String, tikes> ubicacion;
    @FXML
    private TableColumn<String, tikes> tecnicoA;
    @FXML
    private TableColumn<String, tikes> fecha;
    @FXML
    private TableColumn<String, tikes> asunto;
    @FXML
    private TableColumn<String, tikes> UltimaR;
    @FXML
    private Button imprimir;
    @FXML
    private DatePicker fechainicial;
    @FXML
    private DatePicker fechafinal;
    @FXML
    private Button buscar;
    @FXML
    private BarChart<String, Number> registros;
    @FXML
    private ImageView imagendecarga;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buscar.setOnAction((event) -> {
            DatosTablas();
        CARGARGRAFICOS();
        });
        Valores_iniciales_Tablas();
        tikeprincipal.setPlaceholder(new Label("Selecione un rango de Fechas y un Colaborador para realizar la busqueda"));
        imprimir.setOnAction((event) -> {
            MetodoImpresion();
        });
        fechafinal.setValue(LocalDate.now());
        fechainicial.setValue(LocalDate.now());
        yAxis.setLabel("N° de Incidencias");
        xAxis.setTickMarkVisible(true);
        xAxis.setTickLabelRotation(90);
        xAxis.setTickLabelFill(Color.RED);
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("RE001NA",
                "RE002NAR", "RE003INTER", "RE004UP", "RE005DOWN", "RE007POE",
                "RE008RTR", "RE009CP", "RE010WIR", "RE011PTC", "RE012TV", "RE014RSTCP",
                "RE015RSTRT", "RE016FINAL", "RE017ALIG", "RE018EBY",
                "RE019DOM", "RE020MIG", "RE021INS", "FO001ROJO", "FO002NAR",
                "FO003INTER", "FO004UP", "FO005DOWN",
                "FO007PS", "FO008RTR", "FO009ONT",
                "FO010WIR", "FO011PTC", "FO012TV",
                "FO013PEER", "FO014RSTON", "FO015RSTRT",
                "FO016FINAL", "FO017CONM", "FO018CONMC",
                "FO019EBY", "FO020INS", "FO021DOM",
                "FO022MIG", "G001CANL", "G002INROU",
                "G003INRPRO", "G004APREP", "G005CONTCAM",
                "G006RECONEX", "G007CONAPC", "G008CONUPC")));
        xAxis.setLabel("Eventos");
        fechainicial.valueProperty().addListener((observable, oldValue, newValue) -> {
        DatosTablas();
        CARGARGRAFICOS();
            
        });
        buscarponombre.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                 DatosTablas();
        CARGARGRAFICOS();
                
            }
        });
    }

    @FXML
    private void actualizar(ActionEvent event) {
         tikeprincipal.getItems().clear();
         registros.getData().clear();
    }

    public void Grafico() throws SQLException {
    onj.CodigoErrores(" and  tecnico='" + buscarponombre.getText() + "'  and (convert(date, fecha, 111) between '" + fechainicial.getValue().format(formatter) + "' and '" + fechafinal.getValue().format(formatter) + "')");

    }

    public void Valores_iniciales_Tablas() {
        numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tecnicoA.setCellValueFactory(new PropertyValueFactory<>("tecnicoA"));
        asunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        UltimaR.setCellValueFactory(new PropertyValueFactory<>("UltimaR"));
        tikeprincipal.getItems().clear();
        ClaseUtilidadesdeEmpleados objetoBu = new ClaseUtilidadesdeEmpleados();
        objetoBu.PredictorListaEmpleados(buscarponombre);
    }

    public void CqargarValores() {
         tikeprincipal.setPlaceholder(new Label("Estamos buscando sus dados Espere..."));
             onj.getError().clear();
            onj.getValoresError().clear();
        if (!buscarponombre.getText().trim().isEmpty()) {
            
            //   Valores_iniciales_Tablas();
            onj.FuncoReporteG(" and tecnico='" + buscarponombre.getText() + "'  and (convert(date, fecha, 111) between '" + fechainicial.getValue().format(formatter) + "' and '" + fechafinal.getValue().format(formatter) + "')");
            
            /*   try {
                Grafico();
            } catch (SQLException ex) {
                Logger.getLogger(ReporteAreaTecnicaController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }

    }

    public void ImprimirREporte() {
        onj.ReporteServicioTecnicoImpre(buscarponombre.getText(), fechainicial.getValue().format(formatter), fechafinal.getValue().format(formatter), valorres);
    }

    public synchronized  void MetodoImpresion() {
        
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                
   // aqui va todo el proceso a ejecutarse
   imagendecarga.setVisible(true);
   ImprimirREporte();
          return null ; }
        };
      task.setOnSucceeded(event -> {
         // aqui va que hacer cuando ya acabe de ejecutarse el hilo
         imagendecarga.setVisible(false);
    });
    Thread t = new Thread(task);
    t.setDaemon(true);
    t.start();
    }
    public synchronized void CARGARGRAFICOS() {

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
               imagendecarga.setVisible(true);
                Grafico();
                // aqui va todo el proceso a ejecutarse 
                return null;
            }
        };
        task.setOnSucceeded(event -> {
            // aqui va que hacer cuando ya acabe de ejecutarse el hilo
            
            valorres = " ";
            if (!onj.getError().isEmpty() && !onj.getValoresError().isEmpty()) {
                XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                series1.setName("Eventos Realizados " + buscarponombre.getText());

                String cError;
                int valorErro;

                for (int i = 0; i < onj.getError().size(); i++) {
                    cError = onj.getError().get(i).toString();
                    valorErro = Integer.parseInt(onj.getValoresError().get(i).toString());
                    valorres = valorres + "  " + cError + ":" + valorErro;
                    series1.getData().add(new XYChart.Data<>(cError, valorErro));
                }
                registros.getData().addAll(series1);
            }

        });
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
    public synchronized  void DatosTablas() {
        
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
           // aqui va todo el proceso a ejecutarse 
          
           CqargarValores();
          return null ; }
        };
      task.setOnSucceeded(event -> {
         // aqui va que hacer cuando ya acabe de ejecutarse el hilo
         imagendecarga.setVisible(false);
         tikeprincipal.setItems(onj.getObservable_Contenedor_CerradosReporte());
         if(tikeprincipal.getItems().isEmpty()){
             tikeprincipal.setPlaceholder(new Label("No se Encuentra Datos con los parametros establecidos"));
         }
         
    });
    Thread t = new Thread(task);
    t.setDaemon(true);
    t.start();
    }

}
