/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

//import com.guigarage.responsive.ResponsiveHandler;
import CLASES.ClaseVentanaPrincipal;
import CLASES.bas;
import CLASES.HilosClasePrincipal;
import CLASES.NavegacionEntreVentanas;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class Ventana_PrincipalController implements Initializable {

    static Parent we;
    bas a = new bas();
    VariablesDeUso ob = new VariablesDeUso();

    @FXML
    private TextField cliente3;
    @FXML
    private ImageView botonactualizar;
    @FXML
    private Label usuario;
    @FXML
    private AnchorPane content;
    @FXML
    private Button inico;
    @FXML
    private Button clientes;
    @FXML
    private Button serviciotecnico;
    @FXML
    private Button caja;
    @FXML
    private Button bodega;
    @FXML
    private Button reportes;
    @FXML
    private Button gestionderedes;
    @FXML
    private Button contabilidad;
    @FXML
    private Button ajustes;
    @FXML
    private Button planesyservicios;
    @FXML
    private Button BuscarCliente;
    @FXML
    private Button actualizarlistacliente;
    @FXML
    private ImageView cargandoImagen;
    @FXML
    private TabPane tabPane;

    Stage CLIENTE= new Stage();
    Stage productos= new Stage();
    Stage soporte= new Stage();
    Stage CAJA= new Stage();
    Stage logistica= new Stage();
    Stage indicadores= new Stage();
    Stage gestion= new Stage();
    Stage contable= new Stage();
    Stage AJUSTES= new Stage();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CLIENTE.setOnCloseRequest((WindowEvent event) -> {
            CLIENTE.close();
            
        });
        productos.setOnCloseRequest((WindowEvent event) -> {
            
        });
        soporte.setOnCloseRequest((WindowEvent event) -> {
            
        });
        CAJA.setOnCloseRequest((WindowEvent event) -> {
            
        });
        logistica.setOnCloseRequest((WindowEvent event) -> {
            
        });
        indicadores.setOnCloseRequest((WindowEvent event) -> {
            
        });
        gestion.setOnCloseRequest((WindowEvent event) -> {
            
        });
        contable.setOnCloseRequest((WindowEvent event) -> {
            
        });
        AJUSTES.setOnCloseRequest((WindowEvent event) -> {
            
        });
        
        tabPane.requestFocus();
        tabPane.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            if (e.getCode() == KeyCode.TAB) {
                tabPane.getSelectionModel().selectNext();

            }

        });
        // tabPane.getTabs().add(createTab(content));
        cargandoImagen.setVisible(false);
        ListadeClientes();
        a.Reponsive(cliente3, "P");
        a.Reponsive(botonactualizar, "P");
        a.Reponsive(content, "P");
        a.Reponsive(BuscarCliente, "P");
        a.Reponsive(usuario, "P");
        a.Reponsive(cliente3, "P");
        a.Reponsive(cliente3, "M");
        a.Reponsive(botonactualizar, "M");
        a.Reponsive(content, "M");
        a.Reponsive(BuscarCliente, "M");
        a.Reponsive(usuario, "M");
        a.Reponsive(cliente3, "M");
        BuscarCliente.setOnAction((event) -> {
            if (!cliente3.getText().trim().isEmpty()) {
                Clase_VistaCLientes.Factorbusqueda = cliente3.getText();
                a.ventanas("/VISTACLIENTES/vistaclientes.fxml");
            } else {
                a.notificaciones("Llene todos los campos", "I");
            }
        });
        actualizarlistacliente.setOnAction((event) -> {
            ListadeClientes();

        });

        botonactualizar.setOnMouseReleased((event) -> {
            ListadeClientes();
        });
        a.cargarvaloresPropiedades();

        menu(a.properties.getProperty("TipoUsuario"));
        usuario.setText(VariablesDeUso.getNombreusuario());
        cliente3.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!cliente3.getText().replace(" ", "").isEmpty()) {
                    Clase_VistaCLientes.Factorbusqueda = cliente3.getText();
                    a.ventanas("/VISTACLIENTES/vistaclientes.fxml");
                } else {
                    a.notificaciones("Llene todos los campos", "I");
                }

            }
        });

        /// botonones 
        inico.setOnAction((event) -> {
            //cargandoImagen.setVisible(true);
            //NombredelHilo("/CAJA/MODULOCAJA.fxml");
        });
        clientes.setOnAction((event) -> {
            //    cargandoImagen.setVisible(true);
            //     NombredelHilo("/CLIENTES/NUEVOCLIENTE.fxml");
            VentanasNivel2("/CLIENTES/NUEVOCLIENTE.fxml",        CLIENTE );
        });
        serviciotecnico.setOnAction((event) -> {
            //    cargandoImagen.setVisible(true);
            //   NombredelHilo("/SOPORTE/RMANEWTAB.fxml");
            VentanasNivel2("/SOPORTE/RMANEWTAB.fxml", soporte );
        });
        caja.setOnAction((event) -> {
            // Clase_VistaCLientes.Factorbusqueda = null;
            // cargandoImagen.setVisible(true);
            //  NombredelHilo("/CAJA/VENTANA_CAJA.fxml");
            VentanasNivel2("/CAJA/VENTANA_CAJA.fxml",CAJA );
        });
        bodega.setOnAction((event) -> {
            //  cargandoImagen.setVisible(true);
            //  NombredelHilo("/LOGISTICA/InventariosTaB.fxml");
            VentanasNivel2("/LOGISTICA/InventariosTaB.fxml",logistica );
        });
        reportes.setOnAction((event) -> {
            //  cargandoImagen.setVisible(true);
            //   NombredelHilo("/INDICADORES/RESPORTESTAB.fxml");
            VentanasNivel2("/INDICADORES/RESPORTESTAB.fxml",  indicadores );
        });
        gestionderedes.setOnAction((event) -> {
            //  a.ventanas("/GESTION/corteservicio.fxml");
            VentanasNivel2("/GESTION/corteservicio.fxml",  gestion );
        });
        contabilidad.setOnAction((event) -> {
            //   cargandoImagen.setVisible(true);
            //   NombredelHilo("/CONTABLE/CONTABLE.fxml");
            VentanasNivel2("/CONTABLE/CONTABLE.fxml",   contable );
        });
        ajustes.setOnAction((event) -> {
            // cargandoImagen.setVisible(true);
            // NombredelHilo("/AJUSTE/AJUSTES.fxml");
            VentanasNivel2("/AJUSTE/AJUSTES.fxml",  AJUSTES);
        });
        planesyservicios.setOnAction((event) -> {
           // cargandoImagen.setVisible(true);
           // NombredelHilo("/PRODUCTOS/PLANSERV.fxml");
            VentanasNivel2("/PRODUCTOS/PLANSERV.fxml",productos);
        }
        );
        // a.prediccion(cliente3,ClaseVentanaPrincipal.ListaCliente);
        //  ListadeClientes();
        // TODO
    }

    public void ListadeClientes() {
        ClaseVentanaPrincipal objetoPricipal = new ClaseVentanaPrincipal();
        objetoPricipal.NombredelHilo(cliente3);

    }

    public void menu(String usuaario) {

        switch (usuaario) {
            case "SuperUsuario":
                clientes.setDisable(false);
                serviciotecnico.setDisable(false);
                caja.setDisable(false);
                bodega.setDisable(false);
                reportes.setDisable(false);
                gestionderedes.setDisable(false);
                contabilidad.setDisable(false);
                ajustes.setDisable(false);
                planesyservicios.setDisable(false);
                cliente3.setDisable(false);
                break;
            case "Secretario":
                clientes.setDisable(false);
                serviciotecnico.setDisable(false);
                caja.setDisable(false);
                bodega.setDisable(true);
                reportes.setDisable(true);
                gestionderedes.setDisable(true);
                contabilidad.setDisable(false);
                ajustes.setDisable(false);
                planesyservicios.setDisable(true);
                cliente3.setDisable(false);
                break;
            case "Administrador":
                clientes.setDisable(false);
                serviciotecnico.setDisable(false);
                caja.setDisable(false);
                bodega.setDisable(false);
                reportes.setDisable(false);
                gestionderedes.setDisable(false);
                contabilidad.setDisable(false);
                ajustes.setDisable(false);
                planesyservicios.setDisable(false);
                cliente3.setDisable(false);
                break;
            case "Tecnico":
                clientes.setDisable(true);
                serviciotecnico.setDisable(false);
                caja.setDisable(true);
                bodega.setDisable(true);
                reportes.setDisable(true);
                gestionderedes.setDisable(true);
                contabilidad.setDisable(true);
                ajustes.setDisable(false);
                planesyservicios.setDisable(true);
                cliente3.setDisable(true);
                break;
            case "Contabilidad":
                clientes.setDisable(false);
                serviciotecnico.setDisable(false);
                caja.setDisable(false);
                bodega.setDisable(false);
                reportes.setDisable(false);
                gestionderedes.setDisable(false);
                contabilidad.setDisable(false);
                ajustes.setDisable(false);
                planesyservicios.setDisable(false);
                cliente3.setDisable(false);
                break;
            case "Programador":
                clientes.setDisable(false);
                serviciotecnico.setDisable(false);
                caja.setDisable(false);
                bodega.setDisable(false);
                reportes.setDisable(false);
                gestionderedes.setDisable(false);
                contabilidad.setDisable(false);
                ajustes.setDisable(false);
                planesyservicios.setDisable(false);
                cliente3.setDisable(false);

                break;
            default:
                clientes.setDisable(true);
                serviciotecnico.setDisable(true);
                caja.setDisable(true);
                bodega.setDisable(true);
                reportes.setDisable(true);
                gestionderedes.setDisable(true);
                contabilidad.setDisable(true);
                ajustes.setDisable(false);
                planesyservicios.setDisable(true);
                cliente3.setDisable(true);
                break;
        }

    }
    HilosClasePrincipal hilos;
    Stage stage;

    public void VentanasNivel2(String Ventana, Stage stage) {
        NavegacionEntreVentanas na = new NavegacionEntreVentanas(Ventana, stage,false);
        na.VentanaP();
    }

    public synchronized void NombredelHilo(String Ventana) {
        int c = 0;
        cargandoImagen.setVisible(true);
        hilos = HilosClasePrincipal.creayempieza(we, Ventana);
        do {
            if (c == 0) {
                cargandoImagen.setVisible(true);
                c++;
            }
        } while (hilos.hilo.isAlive());
        a.Estilos(hilos.getWe());
        // content.getChildren().clear();
        //  content.getChildren().add(hilos.getWe());
        //content.visibleProperty().setValue(Boolean.FALSE);

        tabPane.getTabs().add(createTab(Ventana.replace(".fxml", ""), hilos.getWe()));
        // SingleSelectionModel<Tab> selectionmodel =tabPane.getSelectionModel();
        // selectionmodel.select(tabCount);

        //    Scene scene = new Scene(hilos.getWe());
        //  stage.setScene(scene);
        //   stage.show();
        cargandoImagen.setVisible(false);
    }
    private int tabCount = 0;
    Tab tab;

    public Tab createTab(String X, Node node) {

        tabCount++;

        tab = new Tab();
        tab.setText(X);
        tab.setTooltip(new Tooltip("Ventana de " + X));
        tab.setContent(node);

        tabPane.getSelectionModel().select(tab);
        return tab;

    }

}
