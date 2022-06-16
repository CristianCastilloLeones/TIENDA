/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import CLASES.Clase_Uso_Tickets;
import CLASES.bas;
import CLASES.tikes;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class RESPORTESTABController implements Initializable {

    /*REPORTES GENERAL */
    bas q = new bas();
    //  ObservableList<facturasinstalacion> estadodeinstalacion = FXCollections.observableArrayList();
    /*ESTADO INDIVIDUA DE CLIENTE */

 /*TICKET EFECTUADOS */
    DateTimeFormatter formatterT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /*REPORTES GENERAL */
    @FXML
    private Tab TabGenerados;
    /*TICKET EFECTUADOS */
    @FXML
    private Tab TabCerrados;
    @FXML
    private Button reporte;
    @FXML
    private TextField buscarponombre;
    @FXML
    private TableView tikeprincipal;
    @FXML
    private TableColumn<String, tikes> numeroTICKECTSEFECTUADOS;
    @FXML
    private TableColumn<String, tikes> departamento;
    @FXML
    private TableColumn<String, tikes> tecnicoA;
    @FXML
    private TableColumn<String, tikes> fechaTICKECTSEFECTUADOS;
    @FXML
    private TableColumn<String, tikes> ubicacion;
    @FXML
    private TableColumn<String, tikes> abierto;
    @FXML
    private TableColumn<String, tikes> UltimaR;
    @FXML
    private TableColumn<String, tikes> usuario;
    @FXML
    private TableColumn<String, tikes> asunto;
    @FXML
    private DatePicker fechainicialTICKECTSEFECTUADOS;
    @FXML
    private DatePicker fechafinalTICKECTSEFECTUADOS;
    @FXML
    private Button buscarTICKECTSEFECTUADOS;
    @FXML
    private AnchorPane CLIENTAS;
    Parent we;
    @FXML
    private AnchorPane servitecnico;
    @FXML
    private AnchorPane reporteOnt;
    @FXML
    private Button Cierrecaj;
    @FXML
    private AnchorPane flujodeejfectivo;
    @FXML
    private Button Aperturadecaja;
    @FXML
    private AnchorPane RP;
    @FXML
    private Button verdetalles;
    @FXML
    private AnchorPane CierredeCaja;
    @FXML
    private AnchorPane ReporteDepsoitos;
    @FXML
    private AnchorPane PaneCliente;
    @FXML
    private AnchorPane facturacionpane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*REPORTE GENERAL PROCESOS*/
        TabCerrados.setOnSelectionChanged((event) -> {
            if (TabCerrados.isSelected()) {
                fechainicialTICKECTSEFECTUADOS.setValue(LocalDate.now());
                fechafinalTICKECTSEFECTUADOS.setValue(LocalDate.now());
                CargarDados();
            }
        });
        verdetalles.setOnAction((event) -> {
            q.ventanasAxu("/INDICADORES/VentanaAUXMOVIMIENTOS.fxml", "Reporte de Movimientos");
        });
        Aperturadecaja.setOnAction((event) -> {
            q.ventanas("/CAJA/Apertura_de_Caja.fxml");
        });
        Cierrecaj.setOnAction((event) -> {
            q.ventanas("/INDICADORES/CierredeCaja.fxml");
        });
        Valores_iniciales_Tablas();
        /*TICKET EFECTUADOS */

        buscarTICKECTSEFECTUADOS.setOnAction((event) -> {
            busquedatikec();
        });
        reporte.setOnAction((event) -> {
            if (String.valueOf(fechainicialTICKECTSEFECTUADOS.getValue()) == null || String.valueOf(fechafinalTICKECTSEFECTUADOS.getValue()) == null) {
                q.notificaciones("DEBE INGRESAR EL RAGO DE BUSQUEDA", "I");
            } else {
                objetoClaseTikes = new Clase_Uso_Tickets();
                objetoClaseTikes.Reportes_impreciones_General(buscarponombre.getText(), String.valueOf(fechainicialTICKECTSEFECTUADOS.getValue().format(formatterT)), String.valueOf(fechafinalTICKECTSEFECTUADOS.getValue().format(formatterT)));
            }

        });

        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/ReporteDeposito.fxml"));
            we.autosize();
            ReporteDepsoitos.getChildren().add(we);

        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/VentanaAUXMOVIMIENTOS.fxml"));
            we.autosize();
            RP.getChildren().add(we);

        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/Reporte_Ingresos.fxml"));
            we.autosize();
            flujodeejfectivo.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/ReporteCierreCaja.fxml"));
            we.autosize();
            CierredeCaja.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/CLIENTES_B.fxml"));
            we.autosize();
            CLIENTAS.getChildren().add(we);
            q.Estilos(we);
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/ReporteAreaTecnica.fxml"));
            we.autosize();
            servitecnico.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/Ventana_AUXReportONT.fxml"));
            we.autosize();
            reporteOnt.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/INDICADORES/LISTACLIENTES.fxml"));
            we.autosize();
            PaneCliente.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            we = FXMLLoader.load(getClass().getResource("/CAJA/Listado_Clientes_General.fxml"));
            we.autosize();
            facturacionpane.getChildren().add(we);
        } catch (IOException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //facturacionpane
       
    }

    @FXML
    private void actualizar(ActionEvent event) {
        busquedatikec();
    }

    public void Valores_iniciales_Tablas() {
        numeroTICKECTSEFECTUADOS.setCellValueFactory(new PropertyValueFactory<>("numero"));
        departamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tecnicoA.setCellValueFactory(new PropertyValueFactory<>("tecnicoA"));
        asunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        fechaTICKECTSEFECTUADOS.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        abierto.setCellValueFactory(new PropertyValueFactory<>("abierto"));
        UltimaR.setCellValueFactory(new PropertyValueFactory<>("UltimaR"));

    }

    public void busquedatikec() {
         objetoClaseTikes = new Clase_Uso_Tickets();
        tikeprincipal.getItems().clear();
        tikeprincipal.setItems(objetoClaseTikes.getObservable_Contenedor());
        objetoClaseTikes.Funciones_Reportes(buscarponombre.getText().trim(), String.valueOf(fechainicialTICKECTSEFECTUADOS.getValue().format(formatterT)).trim(), String.valueOf(fechafinalTICKECTSEFECTUADOS.getValue().format(formatterT)).trim());
    }
Clase_Uso_Tickets objetoClaseTikes;
    public void CargarDados() {
         objetoClaseTikes = new Clase_Uso_Tickets();
        objetoClaseTikes.Inicial_Reporte();
        q.prediccion(buscarponombre, objetoClaseTikes.getLista_clientes_Tickets());
    }
    /*TICKET EFECTUADOS FIN*/

}
