/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AJUSTE;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class NuevoPersonalController implements Initializable {

    bas q = new bas();
    List cargolista = new ArrayList();
    List tiposangre = new ArrayList();
    List tipolicencialista = new ArrayList();
    List tipoGENERO = new ArrayList();
    @FXML
    private Button guardar;
    @FXML
    private TextField cargoocupar;
    @FXML
    private CheckBox dicapacidad;
    @FXML
    private TextField porcentajedica;
    @FXML
    private TextField nombres;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField direccion;
    @FXML
    private TextField identificacion;
    @FXML
    private DatePicker fechanacimiento;
    @FXML
    private TextField telefono1;
    @FXML
    private TextField telefono2;
    @FXML
    private TextField correo;
    @FXML
    private TextField estadocivil;
    @FXML
    private TextField sangre;
    @FXML
    private CheckBox poseelicencia;
    @FXML
    private TextField tipolicencia;
    @FXML
    private TextField genero;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField passusuario;
    @FXML
    private Label inico1;
    @FXML
    private Label fin1;
    @FXML
    private Label inicio2;
    @FXML
    private Label fin2;
    @FXML
    private CheckBox factura;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombres.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                System.out.println(key);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");
                } else {

                }

            } catch (Exception ex) {
            }
        });
        apellidos.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                System.out.println(key);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");
                } else {

                }

            } catch (Exception ex) {
            }
        });
        telefono2.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                System.out.println(key);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.' || key == ' ') {

                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");
                }

            } catch (Exception ex) {
            }
        });
        telefono1.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                System.out.println(key);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == ' ') {

                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");
                }

            } catch (Exception ex) {
            }
        });
        identificacion.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                System.out.println(key);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == ' ') {

                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");

                }

            } catch (Exception ex) {
            }
        });
        porcentajedica.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                System.out.println(key);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {

                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");

                }

            } catch (Exception ex) {
            }
        });
        poseelicencia.setOnAction((event) -> {
            if (poseelicencia.isSelected()) {
                tipolicencia.setDisable(false);
            } else {
                tipolicencia.setDisable(true);
            }
        });
        dicapacidad.setOnAction((event) -> {
            if (dicapacidad.isSelected()) {
                porcentajedica.setDisable(false);
            } else {
                porcentajedica.setDisable(true);
            }
        });
        try {
            // TODO
            llenarlistas();
        } catch (SQLException ex) {
            Logger.getLogger(NuevoPersonalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        q.prediccion(genero, tipoGENERO);
        q.prediccion(cargoocupar, cargolista);
        q.prediccion(sangre, tiposangre);
        q.prediccion(tipolicencia, tipolicencialista);
        guardar.setOnAction((event) -> {
            guardardatos(event);
        });

    }

    public void guardardatos(ActionEvent actionEvent) {
        //int idempleado,int idsucursal,String tipoidentificacion, String identificacion,String Nombre,String Apellidos,boolean Estado,String Direccion,String Email,String Celular1
//,String Celular2,String fechanacimiento,String licencia,String sangre,boolean discapacidad,String porcentajediscapa,String Genero,String Estadocivil,int cargo,String tipohorario,String horainicio1,String horafin1,String horaini2,String horafin2
//,String tipo
        LocalDate value = fechanacimiento.getValue();
        String fci = String.valueOf(value);
        if (!poseelicencia.isSelected()) {
            tipolicencia.setText("NO POSEE");
        }
        if (!dicapacidad.isSelected()) {
            porcentajedica.setText("0");
        }
        int cgo = Integer.parseInt(q.ObtenerValorCampo3("IDCARGO", "[BDAirnet].[dbo].[TBCARGO]", "where NOMBRE='" + cargoocupar.getText() + "'"));

        boolean verificador = false;
        verificador = q.VerificarCedula(identificacion.getText());
        if (verificador) {
            if (!nombres.getText().isEmpty() && !apellidos.getText().isEmpty() && !direccion.getText().isEmpty() && !telefono1.getText().isEmpty() && !telefono2.getText().isEmpty()
                    && !correo.getText().isEmpty() && !estadocivil.getText().isEmpty() && !sangre.getText().isEmpty() && !tipolicencia.getText().isEmpty() && !genero.getText().isEmpty()
                    && !usuario.getText().isEmpty() && !passusuario.getText().isEmpty()) {
                q.nuevopersonal(0, 1, "Cédula", identificacion.getText(), nombres.getText(), apellidos.getText(), true, direccion.getText(), correo.getText(), telefono1.getText(),
                        telefono2.getText(), fci, tipolicencia.getText(), sangre.getText(), dicapacidad.isSelected(), porcentajedica.getText(), genero.getText(), estadocivil.getText(), cgo, "COMPLETO", inico1.getText(), fin1.getText(), inicio2.getText(), fin2.getText(), "I");

                String idempleado = q.ObtenerValorCampo3("IDEMPLEADO", "TBEMPLEADO", " WHERE IDENTIFICACION = '" + identificacion.getText() + "'");
                String idtipousuario = q.ObtenerValorCampo3("IDTIPOUSUARIO", "TBCARGO", " WHERE IDCARGO = " + cgo);

                if (q.EjecutarPROCEDUREUsuario(0, idempleado, idtipousuario, usuario.getText(), q.getMD5(passusuario.getText()), "1", "0", "I")) {
                    Node source = (Node) actionEvent.getSource();
                    Stage stage1 = (Stage) source.getScene().getWindow();
                    stage1.close();
                    q.email(correo.getText(), "Bienvenid@ a AIRNET by AIRFIBER", false, "");
                    q.notificaciones("Exito al Guardar Datos ", "I");

                }

            }

            if (factura.isSelected()) {
                
                q.InsertInsertar("INSERT INTO  [BDAirnet].[dbo].[tdvjSeriesCaja]\n"
                        + "           ([SERIE1]\n"
                        + "           ,[SERIE2]\n"
                        + "           ,[VALORACTUAL]\n"
                        + "           ,[NOMBREUSUARIO])\n"
                        + "     VALUES\n"
                        + "           ('001'\n"
                        + "           ,'037'\n"
                        + "           ,0\n"
                        + "           ,'"+usuario.getText()+" "+apellidos.getText()+"')");
            }
        } else {
            q.notificaciones("Datos incompretos revice y vuelva a intentar", "");

        }
    }

    public void llenarlistas() throws SQLException {
        String cargo = "SELECT [NOMBRE] FROM [BDAirnet].[dbo].[TBCARGO]";
        ResultSet carg = q.tablas(cargo);
        while (carg.next()) {
            cargolista.add(carg.getString("NOMBRE"));
        }
        tipoGENERO.add("Masculino");
        tipoGENERO.add("Femenino");
        tipoGENERO.add("LGBTI");
        tiposangre.add("A+");
        tiposangre.add("A-");
        tiposangre.add("B+");
        tiposangre.add("B-");
        tiposangre.add("AB+");
        tiposangre.add("AB-");
        tiposangre.add("O+");
        tiposangre.add("O-");
        tipolicencialista.add("Tipo A");
        tipolicencialista.add("Tipo B");
        tipolicencialista.add("Tipo C");
        tipolicencialista.add("Tipo F");
        tipolicencialista.add("Tipo D");
        tipolicencialista.add("Tipo E");

    }
}
