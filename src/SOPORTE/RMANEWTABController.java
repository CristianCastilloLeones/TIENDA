/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SOPORTE;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import CLASES.Clase_Uso_Tickets;
import CLASES.ClasedeHilos;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;
import CLASES.tikes;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class RMANEWTABController implements Initializable {

    bas a = new bas();

    Clase_Uso_Tickets usoTikects = new Clase_Uso_Tickets();
    @FXML
    private TextField buscarponombre;
    @FXML
    private TableView tikeprincipal;
    @FXML
    private TableColumn<String, tikes> numero;
    @FXML
    private TableColumn<String, tikes> departamento;
    @FXML
    private TableColumn<String, tikes> tecnicoA;
    @FXML
    private TableColumn<Date, tikes> fecha;
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
    private Button imprimir;
    @FXML
    private DatePicker fechainicial;
    @FXML
    private DatePicker fechafinal;
    @FXML
    private Button buscar;
    @FXML
    private TextField buscarponombre1;
    @FXML
    private TableView tikeprincipal1;
    @FXML
    private TableColumn<String, tikes> numero1;
    @FXML
    private TableColumn<String, tikes> departamento1;
    @FXML
    private TableColumn<String, tikes> tecnicoA1;
    @FXML
    private TableColumn<Date, tikes> fecha1;
    @FXML
    private TableColumn<String, tikes> ubicacion1;
    @FXML
    private TableColumn<String, tikes> abierto1;
    @FXML
    private TableColumn<String, tikes> UltimaR1;
    @FXML
    private TableColumn<String, tikes> usuario1;
    @FXML
    private TableColumn<String, tikes> asunto1;
    @FXML
    private Button imprimir1;
    @FXML
    private DatePicker fechainicial1;
    @FXML
    private DatePicker fechafinal1;
    @FXML
    private Button buscar1;
    @FXML
    private TextField buscarponombre11;
    @FXML
    private TableView tikeprincipal11;
    @FXML
    private TableColumn<String, tikes> numero11;
    @FXML
    private TableColumn<String, tikes> departamento11;
    @FXML
    private TableColumn<String, tikes> tecnicoA11;
    @FXML
    private TableColumn<Date, tikes> fecha11;
    @FXML
    private TableColumn<String, tikes> ubicacion11;
    @FXML
    private TableColumn<String, tikes> abierto11;
    @FXML
    private TableColumn<String, tikes> UltimaR11;
    @FXML
    private TableColumn<String, tikes> usuario11;
    @FXML
    private TableColumn<String, tikes> asunto11;
    @FXML
    private Button imprimir11;
    @FXML
    private DatePicker fechainicial11;
    @FXML
    private DatePicker fechafinal11;
    @FXML
    private Button buscar11;
    @FXML
    private Button reporteindividual;
    @FXML
    private Button reportecerradoindividual;
    @FXML
    private Tab TabGenerados;
    @FXML
    private Tab TabContestados;
    @FXML
    private Tab TabCerrados;
    @FXML
    private Button actualizarGenerados;
    @FXML
    private Button ActuliazarContestados;
    @FXML
    private Button ActualizarCerrados;
    @FXML
    private Button Nuevo;
    @FXML
    private Button btncerrartodo;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        

        // TODO
        btncerrartodo.setOnAction((event) -> {
            for (int i = 0; i < tikeprincipal1.getItems().size(); i++) {
                String actual = ("UPDATE  [BDAirnet].[dbo].[tickets] set Detalleadicional = '"
                        + "Cerrado por - " + VariablesDeUso.nombreusuario + "',respuesta = 'Contestada',estado='Cerrada',comentario ='Ultima Modificacion :"
                        + a.Fechafacturas() + " - " + "Cerrado por Lote" + "', evidencia = '" + "No subio Evidencia" + "' where numero = '"
                        + String.valueOf(numero1.getCellObservableValue(i).getValue()) + "'");
                a.UpdateModificar(actual);
            }
            a.notificaciones("Tabla Actualizada", "I");
            Actuliazar();

        });
        ValoresColumnas();

        ventana_Generados();

        Ventana_contestado();

        Ventana_Cerrados();
       // colores();

        actualizarGenerados.setOnAction((event) -> {
            Actuliazar();
        });
        ActuliazarContestados.setOnAction((event) -> {
            Actuliazar();
        });
        ActualizarCerrados.setOnAction((event) -> {
            Actuliazar();
        });
        ContextMenu context = new ContextMenu();
        MenuItem Item1 = new MenuItem("Modificar");
        MenuItem Item2 = new MenuItem("Ver");
        MenuItem Item3 = new MenuItem("Cerrar");
        MenuItem Item4 = new MenuItem("Eliminar");
        Item4.setOnAction((event) -> {
            String patron = "";
            if (patron.equals("1")) {
                try {
                    a.UpdateModificar("update [BDAirnet].[dbo].[tickets] set estado ='Suspendido' where numero ='" + VariablesDeUso.val + "'");
                    a.notificaciones("Se Cerro Correctamente el  Ticket  N° :" + VariablesDeUso.val, "I");

                } catch (Exception e) {
                    a.notificaciones("Se produjo un error de nivel :" + e.getMessage(), "I");
                }
            } else {
                a.notificaciones("No posee el Rango para realizar esta accion" + VariablesDeUso.val, "I");
            }
        });
        Item3.setOnAction((event) -> {
            String patron = "";
            if (patron.equals("1")) {
                a.UpdateModificar("UPDATE [BDAirnet].[dbo].[tickets] set estado='Cerrada' where numero = '" + VariablesDeUso.val + "'");
                a.notificaciones("Se Cerro Correctamente el  Ticket  N°: " + VariablesDeUso.val, "I");
            } else {
                a.notificaciones("No posee el Rango para realizar esta accion" + VariablesDeUso.val, "I");
            }
        });
        Item2.setOnAction((event) -> {
            a.ventanas("/SOPORTE/VERTICKET.fxml");
        });
        Item1.setOnAction((event) -> {
            a.ventanas("/SOPORTE/MODIFITICKET.fxml");
        });
        tikeprincipal.setRowFactory(tv -> {
            TableRow<tikes> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                tikes rowData = row.getItem();
                VariablesDeUso.val = rowData.getNumero();
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    a.ventanas("/SOPORTE/MODIFITICKET.fxml");
                }
            });
            return row;
        });
        context.getItems().addAll(Item1, Item2, Item3, Item4);
        tikeprincipal.setContextMenu(context);
        tikeprincipal1.setContextMenu(context);
        tikeprincipal11.setContextMenu(context);

        buscar.setOnAction((event) -> {
            System.gc();
            busqueda();
            // colores(usoTikects);

        });
        buscar1.setOnAction((event) -> {
            System.gc();
            busqueda();
            // colores();

        });
        buscar11.setOnAction((event) -> {
            System.gc();
            busqueda();
            // colores();

        });
        reporteindividual.setOnMouseClicked((event) -> {
            usoTikects.Reportes_impreciones(VariablesDeUso.val);
        });
        reportecerradoindividual.setOnMouseClicked((event) -> {
            usoTikects.Reportes_impreciones(VariablesDeUso.val);
        });
        imprimir.setOnMouseClicked((event) -> {
            usoTikects.Reportes_impreciones(VariablesDeUso.val);
        });
        imprimir1.setOnMouseClicked((event) -> {
            usoTikects.Reportes_impreciones(VariablesDeUso.val);
        });
        imprimir11.setOnMouseClicked((event) -> {
            usoTikects.Reportes_impreciones(VariablesDeUso.val);
        });
        tikeprincipal.setOnMouseClicked((event) -> {
            if (!tikeprincipal.getSelectionModel().isEmpty()) {
                VariablesDeUso.val = (usoTikects.getObservable_Contenedor().get(tikeprincipal.getSelectionModel().getSelectedIndex()).getNumero());
            }
        });
        tikeprincipal1.setOnMouseClicked((event) -> {
            if (!tikeprincipal1.getSelectionModel().isEmpty()) {
                VariablesDeUso.val = (usoTikects.getObservable_Contenedor_Constestado().get(tikeprincipal1.getSelectionModel().getSelectedIndex()).getNumero());
            }
        });
        tikeprincipal11.setOnMouseClicked((event) -> {
            if (!tikeprincipal11.getSelectionModel().isEmpty()) {
                System.gc();
                VariablesDeUso.val = (usoTikects.getObservable_Contenedor_Cerrados().get(tikeprincipal11.getSelectionModel().getSelectedIndex()).getNumero());
            }
        });
        buscarponombre.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.gc();
                busquedanombre();
            }
        });
        buscarponombre1.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.gc();
                busquedanombre();
            }
        });
        buscarponombre11.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.gc();
                busquedanombre();
            }
        });
        
        tikeprincipal.setRowFactory(TableView -> {   
             TableRow<tikes> fila = new TableRow<>();
            fila.itemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue.getFecha().equals(new Date(usoTikects.fecha24))){
                     fila.pseudoClassStateChanged(usoTikects.higlighted2, true);
                }else if(newValue.getFecha().before(new Date(usoTikects.fecha48))){
                      fila.pseudoClassStateChanged(usoTikects.higlighted3, true);
                }

            });
            return fila;
        });

    }

    @FXML
    private void btonuevoreparacion(ActionEvent event) {
        a.ventanas("/SOPORTE/NUEVOTICKET.fxml");
        // usoTikects.Reportes_impreciones(VariablesDeUso.val);

    }

    public void ValoresColumnas() {
        numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        departamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        asunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        tecnicoA.setCellValueFactory(new PropertyValueFactory<>("tecnicoA"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        abierto.setCellValueFactory(new PropertyValueFactory<>("abierto"));
        UltimaR.setCellValueFactory(new PropertyValueFactory<>("UltimaR"));
        numero1.setCellValueFactory(new PropertyValueFactory<>("numero"));
        departamento1.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        usuario1.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        asunto1.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        tecnicoA1.setCellValueFactory(new PropertyValueFactory<>("tecnicoA"));
        fecha1.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ubicacion1.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        abierto1.setCellValueFactory(new PropertyValueFactory<>("abierto"));
        UltimaR1.setCellValueFactory(new PropertyValueFactory<>("UltimaR"));
        numero11.setCellValueFactory(new PropertyValueFactory<>("numero"));
        departamento11.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        usuario11.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        asunto11.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        tecnicoA11.setCellValueFactory(new PropertyValueFactory<>("tecnicoA"));
        fecha11.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ubicacion11.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        abierto11.setCellValueFactory(new PropertyValueFactory<>("abierto"));
        UltimaR11.setCellValueFactory(new PropertyValueFactory<>("UltimaR"));

    }
    ClasedeHilos hilos;
    public void ventana_Generados() {
         hilos = ClasedeHilos.creayempieza(usoTikects, "A");
        tikeprincipal.getItems().clear();
        usoTikects = hilos.getObjeto();
        tikeprincipal.setItems(usoTikects.Observable_Contenedor);
        do {
        } while (hilos.hilo.isAlive());
        a.prediccion(buscarponombre, usoTikects.getLista_clientes_Tickets());

    }

    public void Ventana_contestado() {
         hilos = ClasedeHilos.creayempieza(usoTikects, "B");
        tikeprincipal1.getItems().clear();
        usoTikects = hilos.getObjeto();
        do {

        } while (hilos.hilo.isAlive());
        tikeprincipal1.setItems(usoTikects.Observable_Contenedor_Constestado);
        a.prediccion(buscarponombre1, usoTikects.getLista_clientes_TicketsC());

    }

    public void Ventana_Cerrados() {
        tikeprincipal11.getItems().clear();
         hilos = ClasedeHilos.creayempieza(usoTikects, "C");
        usoTikects = hilos.getObjeto();
        do {

        } while (hilos.hilo.isAlive());
        a.prediccion(buscarponombre11, usoTikects.getLista_clientes_TicketsCE());
        tikeprincipal11.setPlaceholder(new Label("Ingrese el nombre del abonado para buscar el Historial...."));

    }

    private void busqueda() {
        if (TabGenerados.isSelected()) {
            Valores_inicialesTablas(tikeprincipal);
            usoTikects.Busqueda_Fecha(String.valueOf(fechainicial.getValue()), String.valueOf(fechafinal.getValue()));
        } else if (TabContestados.isSelected()) {
            Valores_inicialesTablas(tikeprincipal1);
            usoTikects.Busqueda_Fecha_RMAR(String.valueOf(fechainicial1.getValue()), String.valueOf(fechafinal1.getValue()));
        } else if (TabCerrados.isSelected()) {
            Valores_inicialesTablas(tikeprincipal11);
            usoTikects.Busqueda_Fecha_RMAC(String.valueOf(fechainicial11.getValue()), String.valueOf(fechafinal11.getValue()));
        }

    }

    public void Valores_inicialesTablas(TableView tablareceptora) {
        if (TabGenerados.isSelected()) {
            tablareceptora.setItems(usoTikects.getObservable_Contenedor());
        } else if (TabContestados.isSelected()) {
            tablareceptora.setItems(usoTikects.getObservable_Contenedor_Constestado());
        } else if (TabCerrados.isSelected()) {
            tablareceptora.setItems(usoTikects.getObservable_Contenedor_Cerrados());
        }
    }

    private void busquedanombre() {
        if (TabGenerados.isSelected()) {
            Valores_inicialesTablas(tikeprincipal);
            usoTikects.Busqueda_nombre(buscarponombre.getText());
        } else if (TabContestados.isSelected()) {
            Valores_inicialesTablas(tikeprincipal1);
            usoTikects.Busqueda_Nombre_RMAR(buscarponombre1.getText());
        } else if (TabCerrados.isSelected()) {
            Valores_inicialesTablas(tikeprincipal11);
            usoTikects.Busqueda_Nombre_RMAC(buscarponombre11.getText());
        }
    }
    

  

    public void Actuliazar() {
        if (TabGenerados.isSelected()) {
            ventana_Generados();
        } else if (TabContestados.isSelected()) {
            Ventana_contestado();
        } else if (TabCerrados.isSelected()) {
            Ventana_Cerrados();
        }
    }


    /*  public synchronized void CargarTablas() {
        tikeprincipal.setPlaceholder(new Label("Estamos Cargando sus Dados Espere..."));
        Task task = new Task() {
            @Override
            protected Object call() {
                // aqui va todo el proceso a ejecutarse 
                ventana_Generados();
                return null;
            }
        };
        task.setOnSucceeded(event -> {
            tikeprincipal.setItems(usoTikects.Observable_Contenedor);
            colores();
            HiloTicketCerrados();
        });
        System.gc();
        t = new Thread(task);
        t.setDaemon(true);
        t.start();

    }*/

 /*  public synchronized void HiloTicketCerrados() {

        Task task = new Task() {
            @Override
            protected Object call() {
                Ventana_contestado();

                return null;
            }
        };
        task.setOnSucceeded(event -> {
            tikeprincipal1.setItems(usoTikects.Observable_Contenedor_Constestado);
            HiloTicket();
        });

        System.gc();
        t = new Thread(task);
        t.setDaemon(true);
        t.start();

    }*/

 /*  public synchronized void HiloTicket() {

        Task task = new Task() {
            @Override
            protected Object call() {
                Ventana_Cerrados();
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            a.prediccion(buscarponombre11, usoTikects.getLista_clientes_TicketsCE());
            tikeprincipal11.setPlaceholder(new Label("Ingrese el nombre del abonado para buscar el Historial...."));
        });
        System.gc();
        t = new Thread(task);
        t.setDaemon(true);
        t.start();

    }*/
}
