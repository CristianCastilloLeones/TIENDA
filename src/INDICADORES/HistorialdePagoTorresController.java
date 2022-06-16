/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import CLASES.HistorialdePagoTorre;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class HistorialdePagoTorresController implements Initializable {

    bas q = new bas();
    ObservableList<HistorialdePagoTorre> clt = FXCollections.observableArrayList();
    @FXML
    private DatePicker USQUEDAINICUIAL;
    @FXML
    private DatePicker BUSQUEDAFINAL;
    @FXML
    private Button NUEVO;
    @FXML
    private TableView HISTORIALPAGOTORRE;
    @FXML
    private TableColumn<HistorialdePagoTorre, String> NUMERORECIBO;
    @FXML
    private TableColumn<HistorialdePagoTorre, String> FECHA;
    @FXML
    private TableColumn<HistorialdePagoTorre, String> NOMBRE;
    @FXML
    private TableColumn<HistorialdePagoTorre, Float> VALORTOTAL;
    @FXML
    private TableColumn<HistorialdePagoTorre, String> EMPLEADOEMISOR;
    @FXML
    private TableColumn<HistorialdePagoTorre, Boolean> PAGADO;
    @FXML
    private TableColumn<HistorialdePagoTorre, Boolean> IMPRESO;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NUEVO.setOnAction((event) -> {
            q.ventanas("/CAJA/torrespago.fxml");
        });
        ValoresIniales();
        USQUEDAINICUIAL.setOnAction((event) -> {
            CargarBusqueda();
        });
        BUSQUEDAFINAL.setOnAction((event) -> {
            CargarBusqueda();
        });
        USQUEDAINICUIAL.setValue(q.FechaActual()/*LocalDate.of((q.FechaActualCalendario().getTime().getYear() + 1900), q.FechaActualCalendario().getTime().getDate(), 1)*/);
        BUSQUEDAFINAL.setValue(q.FechaActual());
        CargarBusqueda();
    }

    public void ValoresIniales() {
        HISTORIALPAGOTORRE.setItems(clt);
        NUMERORECIBO.setCellValueFactory(new PropertyValueFactory<>("NUMERORECIBO"));
        FECHA.setCellValueFactory(new PropertyValueFactory<>("FECHA"));
        NOMBRE.setCellValueFactory(new PropertyValueFactory<>("NOMBRE"));
        VALORTOTAL.setCellValueFactory(new PropertyValueFactory<>("VALORTOTAL"));
        EMPLEADOEMISOR.setCellValueFactory(new PropertyValueFactory<>("EMPLEADOEMISOR"));
        PAGADO.setCellValueFactory(new PropertyValueFactory<>("PAGADO"));
        IMPRESO.setCellValueFactory(new PropertyValueFactory<>("IMPRESO"));
    }

    public void CargarBusqueda() {
        clt.clear();
        q.consulta = q.tablas("SELECT  [Empleado] ,[FECHA],[NOMBRE],[NUMERORECIBO] ,[IMPRESO] ,[PAGADO],"
                + "[ValorTotal] ,[SERIECAJA] FROM [BDAirnet].[dbo].[tvVistaPagoTorre] "
                + "where CONVERT(date,FECHA,111) BETWEEN '" + USQUEDAINICUIAL.getValue().toString() + " 00:00:00' AND '" + BUSQUEDAFINAL.getValue().toString() + " 23:59:59' ");
        try {
            while (q.consulta.next()) {
                clt.add(new HistorialdePagoTorre(q.consulta.getString("NUMERORECIBO"),
                         q.consulta.getString("FECHA"),
                         q.consulta.getString("NOMBRE"),
                         q.consulta.getFloat("ValorTotal"),
                         q.consulta.getString("Empleado"),
                         q.consulta.getBoolean("PAGADO"),
                         q.consulta.getBoolean("IMPRESO")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistorialdePagoTorresController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(HISTORIALPAGOTORRE.getItems().isEmpty()){
            HISTORIALPAGOTORRE.setPlaceholder(new Label("No se encontro Valores en los Rangos Establecidos"));
        }
    }

}
