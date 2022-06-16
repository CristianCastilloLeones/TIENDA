/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import CLASES.ClaseSalidaDinero;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import CLASES.Clase_Estado_Individual;
import CLASES.Eventos;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class VistaReportesController implements Initializable {

    String selector = "A";
    List listadecliente = new ArrayList();
    bas q = new bas();
    Task Tasaefeciva = null, bancos = null, salida = null, instalaciones = null;
    DecimalFormat formato1 = new DecimalFormat("#.00");

    ObservableList<ClaseSalidaDinero> Eventosvi = FXCollections.observableArrayList();

    String fechinicio = "", fechafinal = "";

    //****************************
    //****************************
    Parent we;
    @FXML
    private DatePicker iniciodebusqueda;
    @FXML
    private DatePicker finaldebusqueda;

    @FXML
    private TableView eventos;
    @FXML
    private TableColumn<String, ClaseSalidaDinero> clienteeve;
    @FXML
    private TableColumn<String, ClaseSalidaDinero> causa;
    @FXML
    private TableColumn<String, ClaseSalidaDinero> estado;
     @FXML
    private TableColumn<String, ClaseSalidaDinero> FECHA;
    @FXML
    private Button buscar;

    @FXML
    private TextField egresos;
    @FXML
    private Button SalidaDinero;
    @FXML
    private Button PagoTorres;
    @FXML
    private Button Promesdepago;
    @FXML
    private TextField Bcausa;
    @FXML
    private TextField Tsolicitante;
   

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Tsolicitante.setOnKeyPressed((KeyEvent event) -> {
           
                if (event.getCode() == KeyCode.F6) {

                    {
                     SaldoActual();
                    }
                }
            
        });
        eventos.setItems(Eventosvi);
        clienteeve.setCellValueFactory(new PropertyValueFactory<>("Solicitante"));
        causa.setCellValueFactory(new PropertyValueFactory<>("Detalle"));
        estado.setCellValueFactory(new PropertyValueFactory<>("Valor"));
        FECHA.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        eventos.getItems().clear();

        SalidaDinero.setOnAction((event) -> {
            q.ventanas("/CAJA/registrosalidaddedinero.fxml");
        });
        PagoTorres.setOnAction((event) -> {
            q.ventanas("/CAJA/torrespago.fxml");
        });
        Promesdepago.setOnAction((event) -> {
            q.ventanas("/CAJA/Promesadepago.fxml");
        });
        // TODO
        iniciodebusqueda.setValue(LocalDate.now());
        finaldebusqueda.setValue(LocalDate.now());

        buscar.setOnAction((event) -> {
            try {
                datosbusqueda();
                salidadedinero();
            } catch (SQLException ex) {
                Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ResultSet solicitantes = q.tablas("SELECT ([NOMBRES]+''+[APELLIDOS]) as nombre FROM [BDAirnet].[dbo].[TBEMPLEADO]");
        try {
            while (solicitantes.next()) {
                listadecliente.add(solicitantes.getString("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        q.prediccion(Tsolicitante, listadecliente);
        Thread hilo = new Thread(createWorker());
        hilo.start();
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                datosbusqueda();
                salidadedinero();
                return true;
            }
        };
    }

    public void datosbusqueda() {
        LocalDate iniciobusq, finbusq;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Calendar fecha1 = Calendar.getInstance();
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        fechinicio = f.format(fecha1.getTime());
        fechafinal = f.format(fecha1.getTime());
        iniciobusq = iniciodebusqueda.getValue();
        fechinicio = String.valueOf(iniciobusq.format(formatter));
        finbusq = finaldebusqueda.getValue();
        fechafinal = String.valueOf(finbusq.format(formatter));
    }

    public void Actualizar() {
        try {

            datosbusqueda();
            salidadedinero();

        } catch (SQLException ex) {
            Logger.getLogger(VistaReportesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salidadedinero() throws SQLException {
        Eventosvi.clear();
        double vt = 0;
        String consultaeventos = null;
        if (Bcausa.getText().replace(" ", "").isEmpty() && Tsolicitante.getText().replace(" ", "").isEmpty()) {
            consultaeventos = "SELECT fechasalida,[solitadopor],[detalle],[cantidad] FROM [BDAirnet].[dbo].[tvdjSalidadedinero] where fechasalida between ' " + fechinicio + " 00:00:00:000' and '" + fechafinal + "  23:59:00:000'";
        } else {
            if (!Bcausa.getText().replace(" ", "").isEmpty() && Tsolicitante.getText().replace(" ", "").isEmpty()) {
                consultaeventos = "SELECT fechasalida,[solitadopor],[motivo],[cantidad] FROM [BDAirnet].[dbo].[tvdjSalidadedinero] where  motivo like '%" + Bcausa.getText() + "%' and (fechasalida between ' " + fechinicio + " 00:00:00:000' and '" + fechafinal + "  23:59:00:000')";
            } else if (Bcausa.getText().replace(" ", "").isEmpty() && !Tsolicitante.getText().replace(" ", "").isEmpty()) {
                consultaeventos = "SELECT fechasalida,[solitadopor],[motivo],[cantidad] FROM [BDAirnet].[dbo].[tvdjSalidadedinero] where  solitadopor like '%" + Tsolicitante.getText() + "%' and (fechasalida between ' " + fechinicio + " 00:00:00:000' and '" + fechafinal + "  23:59:00:000')";
            } else {
                if (!Bcausa.getText().replace(" ", "").isEmpty() && !Tsolicitante.getText().replace(" ", "").isEmpty()) {
                    consultaeventos = "SELECT fechasalida,[solitadopor],[motivo],[cantidad] FROM [BDAirnet].[dbo].[tvdjSalidadedinero] where motivo like '%" + Bcausa.getText() + "%' and solitadopor like '%" + Tsolicitante.getText() + "%' and (fechasalida between ' " + fechinicio + " 00:00:00:000' and '" + fechafinal + "  23:59:00:000')";
                }
            }

        }

        ResultSet eventost = q.tablas(consultaeventos);
        while (eventost.next()) {
            Eventosvi.add(new ClaseSalidaDinero (eventost.getString("fechasalida"),eventost.getString("solitadopor"), eventost.getString("detalle"), eventost.getString("cantidad")));
        }

        for (int i = 0; i < eventos.getItems().size(); i++) {
            vt = vt + Double.parseDouble(String.valueOf(estado.getCellObservableValue(i).getValue()).replace(",", "."));
        }
        egresos.setText(String.valueOf(formato1.format(vt)).replace(",", "."));

    }

    
    public void SaldoActual(){
        Clase_Estado_Individual obClase_Estado_Individual=new Clase_Estado_Individual(fechinicio,fechafinal,VariablesDeUso.nombreusuario);
        obClase_Estado_Individual.FacturadoTotal();
    }
}
