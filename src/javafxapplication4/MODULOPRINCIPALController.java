/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import CLASES.ClaseVentanaPrincipal;
import CLASES.NavegacionEntreVentanas;
import CLASES.bas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class MODULOPRINCIPALController implements Initializable {

    @FXML
    private Label usuario;
    @FXML
    private TextField cliente3;
    @FXML
    private Button BuscarCliente;
    @FXML
    private Button inico;
    @FXML
    private Button clientes;
    @FXML
    private Button planesyservicios;
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
    
    Stage CLIENTE= new Stage();
    Stage productos= new Stage();
    Stage soporte= new Stage();
    Stage CAJA= new Stage();
    Stage logistica= new Stage();
    Stage indicadores= new Stage();
    Stage gestion= new Stage();
    Stage contable= new Stage();
    Stage AJUSTES= new Stage();
    bas a = new bas();
    VariablesDeUso ob = new VariablesDeUso();
    @FXML
    private Button actualizarlistacliente;
    @FXML
    private ImageView botonactualizar;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        CLIENTE.setOnCloseRequest((WindowEvent event) -> {
            
            
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
        
        
        // tabPane.getTabs().add(createTab(content));
      
        ListadeClientes();
        a.Reponsive(cliente3, "P");
        a.Reponsive(botonactualizar, "P");
        a.Reponsive(BuscarCliente, "P");
        a.Reponsive(usuario, "P");
        a.Reponsive(cliente3, "P");
        a.Reponsive(cliente3, "M");
        a.Reponsive(botonactualizar, "M");

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
           
        });
        clientes.setOnAction((event) -> {

            VentanasNivel2("/CLIENTES/NUEVOCLIENTE.fxml",        CLIENTE );
        });
        serviciotecnico.setOnAction((event) -> {
  
            VentanasNivel2("/SOPORTE/RMANEWTAB.fxml", soporte );
        });
        caja.setOnAction((event) -> {
            Clase_VistaCLientes.Factorbusqueda =null;
            VentanasNivel2("/CAJA/VENTANA_CAJA.fxml",CAJA );
        });
        bodega.setOnAction((event) -> {
 
            VentanasNivel2("/LOGISTICA/InventariosTaB.fxml",logistica );
        });
        reportes.setOnAction((event) -> {
   
            VentanasNivel2("/INDICADORES/RESPORTESTAB.fxml",  indicadores );
        });
        gestionderedes.setOnAction((event) -> {

            VentanasNivel2("/GESTION/corteservicio.fxml",  gestion );
        });
        contabilidad.setOnAction((event) -> {

            VentanasNivel2("/CONTABLE/CONTABLE.fxml",   contable );
        });
        ajustes.setOnAction((event) -> {

            VentanasNivel2("/AJUSTE/AJUSTES.fxml",  AJUSTES);
        });
        planesyservicios.setOnAction((event) -> {

            VentanasNivel2("/PRODUCTOS/PLANSERV.fxml",productos);
        }
        );

    }   
    public void VentanasNivel2(String Ventana, Stage stage) {
        NavegacionEntreVentanas na = new NavegacionEntreVentanas(Ventana, stage,false);
        na.VentanaP();
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
    
}
