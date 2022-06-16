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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import CLASES.ClasereporteCierredeCaja;
import javafxapplication4.ExcelExport;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class ReporteCierreCajaController implements Initializable {

    bas q = new bas();
    VariablesDeUso onVariable = new VariablesDeUso();
    ObservableList<ClasereporteCierredeCaja> ObservableCierre = FXCollections.observableArrayList();
    @FXML
    private TableView tikeprincipal;
    @FXML
    private TableColumn<String, ClasereporteCierredeCaja> CAJERO;
    @FXML
    private TableColumn<String, ClasereporteCierredeCaja> FECHAAPERTURACAJA;
    @FXML
    private TableColumn<String, ClasereporteCierredeCaja> FECHACIERRECAJA;
    @FXML
    private TableColumn<String, ClasereporteCierredeCaja> CERRADAPOR;
    @FXML
    private TableColumn<String, ClasereporteCierredeCaja> MONTOINICLA;
    @FXML
    private TableColumn<String, ClasereporteCierredeCaja> TOTALEFECTIVO;
    @FXML
    private TableColumn<String, ClasereporteCierredeCaja> TOTOALCHEQUES;
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
    private TextField TOTALDEUDA;
    @FXML
    private ComboBox<String> busquedacajero;
    String Condicion;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    public void showReport() throws FileNotFoundException, IOException {
        if (!tikeprincipal.getItems().isEmpty()) {
            ExcelExport valor = new ExcelExport();
            valor.export(tikeprincipal, "Reporte_CierredeCaja");

        } else {
            q.notificaciones("Tabla vacia no se puede realizar la Exportacion", "I");
        }

    }

    public void llenar() {
        String Condicionadicional = (busquedacajero.getSelectionModel().getSelectedItem() == null ?"": "and cajero LIKE '%" + busquedacajero.getSelectionModel().getSelectedItem() + "%'" );
        ObservableCierre.clear();
      tikeprincipal.getItems().clear();
       q.consulta = q.tablas("  select "
                + "[cajero] "
                + ",[fechadelabor],"
                + "[fechadecierre],"
                + "[Cerradopor],"
                + "[montoinicial],"
                + "[totalefectivo],"
                + "[totalcheques] FROM [BDAirnet].[dbo].[tvdjCierre/aperturadeCaja] where Estado=0 and (fechadecierre between '"
                + "" +q.FechaformatCierredeCaja(rangoinicial.getValue().toString())+ " 00:00:00' and '" + q.FechaformatCierredeCaja(rangofinal.getValue().toString()) + " 23:59:00' )" + Condicionadicional);
        try {
            while (q.consulta.next()) {
                ObservableCierre.add(new ClasereporteCierredeCaja(
                        q.consulta.getString("cajero"),
                        q.consulta.getString("fechadelabor"),
                        q.consulta.getString("fechadecierre"),
                        q.consulta.getString("Cerradopor"),
                        q.consulta.getString("montoinicial"),
                        q.consulta.getString("totalefectivo"),
                        q.consulta.getString("totalcheques")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReporteCierreCajaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ParametrosIniciales() {
        tikeprincipal.setItems(ObservableCierre);
        CAJERO.setCellValueFactory(new PropertyValueFactory<>("CAJERO"));
        FECHAAPERTURACAJA.setCellValueFactory(new PropertyValueFactory<>("FECHAAPERTURACAJA"));
        FECHACIERRECAJA.setCellValueFactory(new PropertyValueFactory<>("FECHACIERRECAJA"));
        CERRADAPOR.setCellValueFactory(new PropertyValueFactory<>("CERRADAPOR"));
        MONTOINICLA.setCellValueFactory(new PropertyValueFactory<>("MONTOINICLA"));
        TOTALEFECTIVO.setCellValueFactory(new PropertyValueFactory<>("TOTALEFECTIVO"));
        TOTOALCHEQUES.setCellValueFactory(new PropertyValueFactory<>("TOTOALCHEQUES"));

    }

    public synchronized void CalculoDeuda() {

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                llenar();
                return null;
            }
        };
        task.setOnSucceeded(event -> {
            onVariable.setValorTotal(0);

            cargando.setVisible(false);
            tikeprincipal.setItems(ObservableCierre);
            for (int j = 0; j < tikeprincipal.getItems().size(); j++) {
                onVariable.setValorTotal(Double.parseDouble(String.valueOf(TOTALEFECTIVO.getCellObservableValue(j).getValue())));
            }
            TOTALDEUDA.setText(q.FormatoDecimal1(onVariable.getValorTotal()));
        });
        if (tikeprincipal.getItems().isEmpty()) {
            cargando.setVisible(false);
            tikeprincipal.setPlaceholder(new Label("No hay Datos en los parametros buscados"));
        }
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
}
