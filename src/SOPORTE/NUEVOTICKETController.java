/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SOPORTE;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import CLASES.ClaseUtilidadesdeEmpleados;
import CLASES.ClaseVentanaPrincipal;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;

/**
 * *****
 */
/**
 * FXML Controller class
 *
  * @author cl
 */
public class NUEVOTICKETController implements Initializable {

    int idcl = 0;
    String correo = "";
    bas q = new bas();
    ClaseUtilidadesdeEmpleados objUtilidade;
    ClaseVentanaPrincipal obListaClaseVentanaPrincipal;
    @FXML
    TextField cliente1;
    @FXML
    TextField empleado;
    @FXML
    TextField sol;
    @FXML
    Button arc;
    @FXML
    ImageView image;

    @FXML
    TextField departamento;
    @FXML
    TextField asunto;
    @FXML
    private TextArea detalles;
    @FXML
    private Button gua;
    @FXML
    private Label DEPA;
    @FXML
    private DatePicker fechainicio;
    @FXML
    private DatePicker fechavisita;
    @FXML
    private Label TIPO;
    @FXML
    private CheckBox check;
    @FXML
    private ComboBox<String> idcliente;
    @FXML
    private Label direccionvisita;
    @FXML
    private CheckBox noasignar;
    @FXML
    private Label tamañodeltexto;

    /**
     * Initializes the controller class.
     */
    public void valores() {
        objUtilidade = new ClaseUtilidadesdeEmpleados();
        objUtilidade.PredictorListaEmpleados(empleado);

        q.CargarPropiedades();
        departamento.setText(q.properties.getProperty("TipoUsuario"));
    }

    @FXML
    public void Guardartodo(ActionEvent actionEvent) throws IOException, SQLException {

        String valores = null;
        q.consulta = q.tablas(" select MAX(numero)+1 as numero from [BDAirnet].[dbo].[tickets]");
        String err = "";

        String direcc;
        String sc ="";
        while (q.consulta.next()) {
            sc = q.consulta.getString("numero");
        }
        if (sc.length() < 9) {
            for (int i = 1; i <= 9; i++) {
                if (sc.length() == 9) {
                    break;
                } else {
                    sc = "0" + sc;
                }
            }
        }
        q.consulta = q.tablas("SELECT top (1)CODIGOERROR FROM [BDAirnet].[dbo].[TBCODIGOERROR] where [DESCRIPCION] LIKE'%" + asunto.getText() + "%'");
        while (q.consulta.next()) {
            err = q.consulta.getString("CODIGOERROR");
        }
        //  if(asuntol.contains(asunto.getText())){
        String sentencia = "INSERT INTO [BDAirnet].[dbo].[tickets]"
                + "([numero],[departamento],[usuario],[asunto],[tecnico],[fecha],[direccion],[abiertopor],[fechatrabajo],[codigo],[Detalleadicional],[evidencia],[fechainicio],[estado],[respuesta],[IDCLIENTE])";

        if (noasignar.isSelected()) {
            if (cliente1.getText().replace(" ", "").isEmpty() || sol.getText().replace(" ", "").isEmpty()
                    || departamento.getText().replace(" ", "").isEmpty() || detalles.getText().replace(" ", "").isEmpty() || direccionvisita.getText().replace(" ", "").isEmpty()) {
                q.notificaciones("Datos incompretos revice y vuelva a intentar", "");

            } else {
                if (direccionvisita.getText().length() >= 70) {
                    direcc = direccionvisita.getText().substring(0, 69);
                } else {
                    direcc = direccionvisita.getText();
                }

                if (check.isSelected()) {
                    File origen;
                    File destino;
                    File imgFile = null;
                    origen = new File(imgFile.getAbsolutePath());
                    destino = new File("\\\\S-AIRCONTROL\\bin\\evidencia\\" + imgFile.getName());
                    Files.copy(Paths.get(origen.getAbsolutePath()), Paths.get(destino.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                    valores = "VALUES('" + sc + "','" + departamento.getText() + "','" + cliente1.getText() + "','" + asunto.getText() + "','" + "SIN ASIGNAR" + "','" + fechavisita.getValue() + "','" + direcc + "','" + sol.getText() + "','" + fechainicio.getValue() + "','" + err + "','" + detalles.getText() + "','" + String.valueOf(destino) + "','" + fechainicio.getValue() + "','" + "Espera" + "',' '," + idcl + ")";
                } else {
                    valores = "VALUES('" + sc + "','" + departamento.getText() + "','" + cliente1.getText() + "','" + asunto.getText() + "','" + "SIN ASIGNAR" + "','" + fechavisita.getValue() + "','" + direcc + "','" + sol.getText() + "','" + fechainicio.getValue() + "','" + err + "','" + detalles.getText() + "','" + "No hay evidencia" + "','" + fechainicio.getValue() + "','" + "Espera" + "',' '," + idcl + ")";
                }
            }      //***************************
            try {

                q.InsertInsertar(sentencia + valores);
                if (asunto.getText().contains("RETIRO")) {
                    q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set ESTADO=0 where IDCLIENTE=" + idcl);
                }
                q.notificaciones("Exito al Guardar Datos", "I");
                //     q.email(correo, "Estimado Cliente: Airnet te informa que tu alta de servicio sera realizada en un lapso maximo de 48 horas entre las 08H40 y 17H50 , se recomienda estar atento a la llamada de los tecnicos desde los numeros 0989826789 0989826256 099352300 " + fechainicio.getValue() + "  -  " + fechainicio.getValue(), false, "");
                Node source = (Node) actionEvent.getSource();
                Stage stage1 = (Stage) source.getScene().getWindow();
                stage1.close();

            } catch (Exception e) {
                q.notificaciones("Se produjo un error de nivel : " + e.getMessage(), "");
            }
        } else {
            if (cliente1.getText().isEmpty() || empleado.getText().isEmpty() || sol.getText().isEmpty()
                    || departamento.getText().isEmpty() || detalles.getText().isEmpty() || direccionvisita.getText().isEmpty()) {
                q.notificaciones("Datos incompretos revice y vuelva a intentar", "");
            } else {
                if (direccionvisita.getText().length() >= 70) {
                    direcc = direccionvisita.getText().substring(0, 69);
                } else {
                    direcc = direccionvisita.getText();
                }
                if (check.isSelected()) {
                    File origen;
                    File destino;
                    File imgFile = null;
                    origen = new File(imgFile.getAbsolutePath());
                    destino = new File("\\\\S-AIRCONTROL\\bin\\evidencia\\" + imgFile.getName());
                    Files.copy(Paths.get(origen.getAbsolutePath()), Paths.get(destino.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

                    valores = "VALUES('" + sc + "','" + departamento.getText() + "','" + cliente1.getText() + "','" + asunto.getText() + "','" + empleado.getText() + "','" + fechavisita.getValue() + "','" + direcc + "','" + sol.getText() + "','" + fechainicio.getValue() + "','" + err + "','" + detalles.getText() + "','" + String.valueOf(destino) + "','" + fechainicio.getValue() + "','" + "Espera" + "',' '," + idcl + ")";
                } else {
                    valores = "VALUES('" + sc + "','" + departamento.getText() + "','" + cliente1.getText() + "','" + asunto.getText() + "','" + empleado.getText() + "','" + fechavisita.getValue() + "','" + direcc + "','" + sol.getText() + "','" + fechainicio.getValue() + "','" + err + "','" + detalles.getText() + "','" + "No hay evidencia" + "','" + fechainicio.getValue() + "','" + "Espera" + "',' '," + idcl + ")";
                }

                try {
                    q.InsertInsertar(sentencia + valores);
                    Node source = (Node) actionEvent.getSource();
                    Stage stage1 = (Stage) source.getScene().getWindow();
                    stage1.close();
                    q.notificaciones("Exito al Guardar Datos", "I");
                } catch (Exception e) {
                    q.notificaciones("Se produjo un error de nivel : " + e.getMessage(), "");
                }

            }

        }
        q.email(correo, "Estimado Cliente: Airnet te informa que tu alta de servicio sera realizada en un lapso maximo de 48 horas entre las 08H40 y 17H50 , se recomienda estar atento a la llamada de los tecnicos desde los numeros 0989826789 0989826256 099352300 " + fechainicio.getValue() + "  -  " + fechavisita.getValue(), false, "");

        //   }else {
        //       q.notificaciones("Debe seleccionar una item de la lista proporcionada", "I");
        //   }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fechainicio.setValue(LocalDate.now());
        fechavisita.setValue(LocalDate.now());
     

        noasignar.setOnAction((event) -> {
            if (noasignar.isSelected()) {
                empleado.setDisable(true);
            } else {
                empleado.setDisable(false);
            }
        });
        sol.setDisable(true);
        sol.setText(VariablesDeUso.nombreusuario);
        departamento.setDisable(true);
        departamento.setText(VariablesDeUso.tipouuario);

        idcliente.setOnAction((event) -> {
            try {
                funcionCombosplane(idcliente.getSelectionModel().getSelectedItem());
            } catch (SQLException ex) {
                Logger.getLogger(NUEVOTICKETController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        arc.setDisable(true);
        check.setOnAction((event) -> {
            if (check.isSelected()) {
                arc.setDisable(false);
            } else {
                arc.setDisable(true);
            }
        });
        arc.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File imgFile;
            Image image1;
            // Obtener la imagen seleccionada
            imgFile = fileChooser.showOpenDialog(null);
            // Mostar la imagen
            if (imgFile != null) {
                image1 = new Image("file:" + imgFile.getAbsolutePath());

                image.setImage(image1);
            }
        });
        valores();
        q.prediccion(cliente1, ClaseVentanaPrincipal.ListaCliente);
        // TextFields.bindAutoCompletion(departamento,carg);
        cliente1.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                cuentas();

            }
        });

    }

    public void cuentas() {
        int verificarordecuentas = 0;
        q.consulta = q.tablas("SELECT COUNT(*)AS CUANTOS FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] WHERE ([NOMBRES]+' '+[APELLIDOS]) like '%" + cliente1.getText() + "%'");
        try {
            while (q.consulta.next()) {
                if (q.consulta.getInt("CUANTOS") > 1) {
                    verificarordecuentas = 1;
                } else {
                    verificarordecuentas = 2;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NUEVOTICKETController.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (verificarordecuentas) {
            case 1:

                idcliente.getItems().clear();

                q.consulta = q.tablas("SELECT IDCLIENTE FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] WHERE ([NOMBRES]+' '+[APELLIDOS])like '%" + cliente1.getText() + "%'");
                 {
                    try {
                        while (q.consulta.next()) {
                            idcliente.getItems().addAll(q.consulta.getString("IDCLIENTE"));
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(NUEVOTICKETController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                idcliente.setVisible(true);

                break;

            case 2:
                idcliente.setVisible(false);

                q.consulta = q.tablas("SELECT  * FROM [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where ([NOMBRES]+' '+[APELLIDOS])=" + "'" + cliente1.getText() + "'");
                try {
                    while (q.consulta.next()) {
                        TIPO.setText("NET : " + q.consulta.getString("TIPOINSTALACION"));
                        idcl = q.consulta.getInt("IDCLIENTE");
                        correo = q.consulta.getString("EMAIL");
                        direccionvisita.setText(q.consulta.getString("DIRECCION"));
                        if (Hartikectesactivos()) {

                            break;
                        }
                        if (q.consulta.getString("TIPOINSTALACION").contains("Fibra Óptica")) {
                            codigoerrores("CODIGOERROR like  'FO%'  or    CODIGOERROR like  'G%'");

                        } else if (q.consulta.getString("TIPOINSTALACION").contains("Radio Enlace")) {
                            codigoerrores("CODIGOERROR like  'RE%'  or    CODIGOERROR like  'G%'");
                        }

                    }
                    // ...
                } catch (SQLException ex) {
                    Logger.getLogger(NUEVOTICKETController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            default:
                break;
        }

    }

    public void funcionCombosplane(String planes) throws SQLException {
        //   String consulta = "SELECT top(1) [IDCLIENTE],[DIRECCION],TIPOINSTALACION FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where [IDCLIENTE]= '" + planes + "'";
        q.consulta = q.tablas("SELECT top(1) [IDCLIENTE],[DIRECCION],TIPOINSTALACION FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where [IDCLIENTE]= '" + planes + "'");
        while (q.consulta.next()) {
            TIPO.setText("NET : " + q.consulta.getString("TIPOINSTALACION"));
            direccionvisita.setText(q.consulta.getString("DIRECCION"));
            idcl = q.consulta.getInt("IDCLIENTE");
            if (Hartikectesactivos()) {
                break;
            }
            if (q.consulta.getString("TIPOINSTALACION").contains("Fibra Óptica")) {
                codigoerrores("CODIGOERROR like  'FO%'  or    CODIGOERROR like  'G%'");
            } else if (q.consulta.getString("TIPOINSTALACION").contains("Radio Enlace")) {
                codigoerrores("CODIGOERROR like  'RE%'  or    CODIGOERROR like  'G%'");
            }
        }
    }

    public void codigoerrores(String filtro) {
        objUtilidade = new ClaseUtilidadesdeEmpleados();
        objUtilidade.PredictorListaErroresTickecs(filtro, asunto);

    }

    public boolean Hartikectesactivos() throws SQLException {

        q.auxConsulta = q.tablas("select count(*) as tik from [BDAirnet].[dbo].[tickets] where estado='Espera' and respuesta=' ' and IDCLIENTE=" + idcl);
        while (q.auxConsulta.next()) {
            if (q.auxConsulta.getInt("tik") > 0) {
                gua.setDisable(true);
                q.notificaciones("El Cliente  tiene Activo un Ticket", "I");
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
