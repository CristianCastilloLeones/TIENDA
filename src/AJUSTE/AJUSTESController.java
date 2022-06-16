/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AJUSTE;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class AJUSTESController implements Initializable {

    Node source;
    bas propied = new bas();
    @FXML
    private CheckBox edicontraseña;
    @FXML
    private CheckBox editDireccion;
    @FXML
    private CheckBox edittelefono;
    @FXML
    private CheckBox ediemail;
    @FXML
    private TextField ci;
    @FXML
    private TextField actualcontraseña;
    @FXML
    private TextField nuevacontraseña;
    @FXML
    private TextField direccion;
    @FXML
    private TextField telefono;
    @FXML
    private TextField email;
    @FXML
    private CheckBox pruebas;
    @FXML
    private CheckBox produccion;
    @FXML
    private Button cambiarambiente;
    @FXML
    private Button guardarcambiosruta;
    @FXML
    private Button ACTUALIZARDIRECTORIOS;
    @FXML
    private TextField rutaserver;
    @FXML
    private Button editarcambios;
    @FXML
    private TextField NombreBD;
    @FXML
    private TextField UsuarioBD;
    @FXML
    private PasswordField contraseñaBD;
    @FXML
    private TextField correoemisor;
    @FXML
    private TextField puertocorreo;
    @FXML
    private PasswordField contreseacorreoemisor;
    @FXML
    private Button guardarcambioscorreo;
    @FXML
    private TextField mailsmtphost;
    @FXML
    private Button DirectoriosdeArchivos;
    @FXML
    private TextField directoriosyutilidades;
    @FXML
    private Button seleccionardirectorios;
    @FXML
    private Tab PestañaServerCorreo;
    @FXML
    private Tab PestañabaseDatos;
    @FXML
    private Button NuevoPersonal;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NuevoPersonal.setOnAction((event) -> {
            propied.ventanas("/AJUSTE/Nuevo personal.fxml");
        });
        DirectoriosdeArchivos.setOnAction((event) -> {
            propied.CargarPropiedades();
            propied.properties.setProperty("RutaArchivos", directoriosyutilidades.getText());
            propied.GuardarCambiosPropiedades();
            CrearDirectoriosdeUtilidades();
            cargarvaloresPropiedades();
        });
        seleccionardirectorios.setOnAction((event) -> {
            SeleccionarDirectorioUtilidades();
        });
        guardarcambioscorreo.setOnAction((event) -> {
            EditarDatosdeCorreo();
        });
        guardarcambiosruta.setOnAction((event) -> {
            CreaDatosConexionBaseDatos();
        });
        cambiarambiente.setOnAction((event) -> {
            EditarAmbiente();
        });
        ACTUALIZARDIRECTORIOS.setOnAction((event) -> {
            crearArchivosdePropiedades();
        });

        edicontraseña.setOnAction((event) -> {
            if (edicontraseña.isSelected()) {
                ci.setDisable(false);
                actualcontraseña.setDisable(false);
                nuevacontraseña.setDisable(false);
            } else {
                ci.setDisable(true);
                actualcontraseña.setDisable(true);
                nuevacontraseña.setDisable(true);
            }
        });
        editDireccion.setOnAction((event) -> {
            if (editDireccion.isSelected()) {
                direccion.setDisable(false);

            } else {
                direccion.setDisable(true);

            }
        });

        edittelefono.setOnAction((event) -> {
            if (edittelefono.isSelected()) {
                telefono.setDisable(false);

            } else {
                telefono.setDisable(true);

            }
        });
        ediemail.setOnAction((event) -> {
            if (ediemail.isSelected()) {
                email.setDisable(false);
            } else {
                email.setDisable(true);

            }
        });
        editarcambios.setOnAction((event) -> {
            EdidarDatosClientes(event);
        });
        cargarvaloresPropiedades();

    }

    public void crearDirectorios() {

        propied.CreacionArchivo(true);

    }

    public void EditarDatosdeCorreo() {
        if (!correoemisor.getText().isEmpty() && !puertocorreo.getText().isEmpty() && !contreseacorreoemisor.getText().isEmpty() && !mailsmtphost.getText().isEmpty()) {
            propied.CargarPropiedades();
            propied.properties.replace("CorreoEmisor", correoemisor.getText());
            propied.properties.replace("Puerto", puertocorreo.getText());
            propied.properties.replace("ClaveCorreoEmisor", contreseacorreoemisor.getText());
            propied.properties.replace("MailHost", mailsmtphost.getText());
            propied.GuardarCambiosPropiedades();
            propied.notificaciones("Se Realizaron los Cambios con Exito", "I");
            cargarvaloresPropiedades();
        } else {
            propied.notificaciones("Campos incompletos", "I");
        }

    }

    public void CreaDatosConexionBaseDatos() {
        // rutaserver

        if (!rutaserver.getText().isEmpty() && !NombreBD.getText().isEmpty() && !UsuarioBD.getText().isEmpty() && !contraseñaBD.getText().isEmpty()) {
            propied.CargarPropiedades();
            propied.properties.replace("RutaServidor", rutaserver.getText());
            propied.properties.replace("NombreBD", NombreBD.getText());
            propied.properties.replace("UsuarioBD", UsuarioBD.getText());
            propied.properties.replace("ClaveBD", contraseñaBD.getText());
            propied.GuardarCambiosPropiedades();
            propied.notificaciones("Se Realizaron los Cambios con Exito", "I");
            cargarvaloresPropiedades();
        } else {
            propied.notificaciones("Campos incompletos", "I");
        }
    }

    public void crearArchivosdePropiedades() {
        propied.CreacionArchivo(true);
    }

    public void EdidarDatosClientes(ActionEvent event) {
        if (edicontraseña.isSelected()
                || editDireccion.isSelected()
                || edittelefono.isSelected()
                || ediemail.isSelected()) {
            if (edicontraseña.isSelected()) {
                if (!ci.getText().isEmpty() && !actualcontraseña.getText().isEmpty() && !nuevacontraseña.getText().isEmpty()) {
                    if (ci.getText().equals(nuevacontraseña.getText())) {
                        propied.UpdateModificarDATOSAVISO("update  [BDAirnet].[dbo].[TBUSUARIO]  set [CLAVE]='" + propied.getMD5(nuevacontraseña.getText())
                                + "' where [CLAVE]='" + propied.getMD5(actualcontraseña.getText()) + "' and IDEMPLEADO='" + propied.IDEMPLEADO() + "'");
                        propied.notificaciones("Se Realizaron los Cambios con Exito ", "I");
                        propied.notificaciones("Cerrando Session", "I");
                        source = (Node) event.getSource();
                        Stage stage1 = (Stage) source.getScene().getWindow();
                        stage1.close();
                        propied.ventanas("login.fxml");

                    } else {
                        propied.notificaciones("La contraseñas no Coinciden", "I");
                    }
                } else {
                    propied.notificaciones("Complete todos lo campos para proceder.", "I");
                }
            }
            propied.ActualizacionEmpledado(editDireccion.isSelected(), direccion.getText(), edittelefono.isSelected(), telefono.getText(), ediemail.isSelected(), email.getText());
            propied.notificaciones("Se Realizaron los Cambios con Exito ", "I");

        } else {
            propied.notificaciones("No se realizaron Cambios ", "I");
        }

    }

    public void EditarAmbiente() {

        propied.CargarPropiedades();
        if (pruebas.isSelected()) {
            propied.properties.replace("Ambiente", "PRUEBAS");
            propied.GuardarCambiosPropiedades();
            propied.notificaciones("Se Realizo el Cambio con exito", "I");
        } else if (produccion.isSelected()) {

            propied.properties.replace("Ambiente", "PRODUCCION");
            propied.GuardarCambiosPropiedades();
            propied.notificaciones("Se Realizo el Cambio con exito", "I");
            cargarvaloresPropiedades();
        }
    }

    public void cargarvaloresPropiedades() {
        propied.CargarPropiedades();
        if ((propied.properties.getProperty("Ambiente")).contains("PRODUCCION")) {
            produccion.setSelected(true);
        } else {
            pruebas.setSelected(true);
        }
        rutaserver.setText(propied.properties.getProperty("RutaServidor"));
        NombreBD.setText(propied.properties.getProperty("NombreBD"));
        UsuarioBD.setText(propied.properties.getProperty("UsuarioBD"));
        contraseñaBD.setText(propied.properties.getProperty("ClaveBD"));
        correoemisor.setText(propied.properties.getProperty("CorreoEmisor"));
        puertocorreo.setText(propied.properties.getProperty("Puerto"));
        contreseacorreoemisor.setText(propied.properties.getProperty("ClaveCorreoEmisor"));
        mailsmtphost.setText(propied.properties.getProperty("MailHost"));
        directoriosyutilidades.setText(propied.properties.getProperty("RutaArchivos"));
        PestañaServerCorreo.setDisable(propied.DesahabilitarNiveles(propied.properties.getProperty("TipoUsuario")));
        PestañabaseDatos.setDisable(propied.DesahabilitarNiveles(propied.properties.getProperty("TipoUsuario")));
    }

    public void SeleccionarDirectorioUtilidades() {
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Buscar Directorio");
        File archivoElegido = fileChooser.showDialog(null);
        //Mostrar el nombre del archvivo en un campo de texto
        directoriosyutilidades.setText(archivoElegido.getPath());
    }

    public void CrearDirectoriosdeUtilidades() {
        CrearcionCarper("Clientes");
        CrearcionCarper("ArchivosdeImpresion");
        CrearcionCarper("RecibosdePago");
        CrearcionCarper("SRI");
        CrearcionCarper("evidencia");

    }

    public void CrearcionCarper(String Carpeta) {
        File directorio = new File(directoriosyutilidades.getText() + "\\" + Carpeta);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                propied.notificaciones("Directorio " + Carpeta + " Creado", "I");
            }
        } else {
            propied.notificaciones("Directorio " + Carpeta + " Ya Existe", "I");
        }
    }
}
