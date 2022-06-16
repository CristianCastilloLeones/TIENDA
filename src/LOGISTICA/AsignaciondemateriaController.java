/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGISTICA;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class AsignaciondemateriaController implements Initializable {

    
    List listcli = new ArrayList();
    bas q = new bas();
    @FXML
    private ComboBox<String> materiaasignar;
    @FXML
    private TextField cantidadasignar;
    @FXML
    private Button BTONASIGNAR;
    @FXML
    private TextField tecnigoasignar;
    @FXML
    private Label temasignacion;
    @FXML
    private Label codigo;
    @FXML
    private RadioButton ont;
    @FXML
    private RadioButton otros;
    ToggleGroup group = new ToggleGroup();
    //getSelectionModel().getSelectedItem()

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ont.setToggleGroup(group);
        otros.setToggleGroup(group);
        tecnigoasignar.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
              if(ont.isSelected()){
                  Codigocontrato();
              }
            }
        }
    });
        
        ont.setOnAction((event) -> {
            temasignacion.setText("Cliente a Asisgnar");
            codigo.setText("Codigo Contrato");
            listcli.clear();
            q.listaclient(listcli);
            //SELECT  pon FROM [BDAirnet].[dbo].[tvproductosdj] where producto = 'ONT FIBRA OPTICA' and estado ='Inactivo'
            ResultSet va  = q.tablas("SELECT  pon FROM [BDAirnet].[dbo].[tvproductosdj] where (producto = 'ONT FIBRA OPTICA' or producto = 'ONT FIBRA OPTICA MIGRACION' or producto = 'Router TPLINK' ) and estado ='Inactivo'");
            try {
                while (va.next()) {
                    materiaasignar.getItems().add(va .getString("pon").trim().replace(" ", ""));
                }
            } catch (SQLException ex) {
                Logger.getLogger(AsignaciondemateriaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            q.prediccion(tecnigoasignar, listcli);

        });

        otros.setOnAction((event) -> {
            temasignacion.setText("Tecnico a Asisgnar");
            codigo.setText("Cantidad del material a Entregar");
            listcli.clear();
            try {
                // TODO
                ResultSet material = q.tablas("SELECT [producto]  FROM [BDAirnet].[dbo].[tpcategoriadj]");
                while (material.next()) {
                    materiaasignar.getItems().add(material.getString("producto"));
                }
                ResultSet va  = q.tablas("SELECT ( [NOMBRES]+' ' +APELLIDOS) as nombres FROM [BDAirnet].[dbo].[View_Datos_Empleado] where  ESTADO !=0");
                while (va.next()) {
                    listcli.add(va .getString("nombres").trim());
                }
            } catch (SQLException ex) {
                Logger.getLogger(AsignaciondemateriaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            q.prediccion(tecnigoasignar, listcli);
        });

        BTONASIGNAR.setOnAction((event) -> {
            try {
                guardar();
                Node source = (Node) event.getSource();
                Stage stage1 = (Stage) source.getScene().getWindow();
                stage1.close();
            } catch (SQLException ex) {
                Logger.getLogger(AsignaciondemateriaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void guardar() throws SQLException {
        if (ont.isSelected()) {
            q.UpdateModificar(" UPDATE  [BDAirnet].[dbo].[tvproductosdj]\n"
                    + "  SET cliente ='" + tecnigoasignar.getText() + "'\n"
                    + "  ,estado='Activo'\n"
                    + "  ,CONTRATO='" + cantidadasignar.getText() + "'\n"
                    + "  where pon='" + materiaasignar.getSelectionModel().getSelectedItem() + "'");
            q.notificaciones("Se registro la ONT "+materiaasignar.getSelectionModel().getSelectedItem()+" correctamente", "I");
        } else if (otros.isSelected()) {

            try {
                String serieutilizable = "";
                int idpro = 0;
                String seriepro = "SELECT top (1)[id], [serie] FROM [BDAirnet].[dbo].[tvproductosdj] where producto ='" + materiaasignar.getSelectionModel().getSelectedItem() + "' and estado='Inactivo'";

                ResultSet serproducto = q.tablas(seriepro);
                while (serproducto.next()) {
                    serieutilizable = serproducto.getString("serie");
                    idpro = serproducto.getInt("id");
                }
                String actualizar = "UPDATE [BDAirnet].[dbo].[tvproductosdj]"
                        + "SET "
                        + "[cliente] ='" + tecnigoasignar.getText() + "'"
                        + ",[fechaactiva] = GETDATE()"
                        + ",[activa] ='SI'"
                        + ",[estado] ='Activo'"
                        + "WHERE [id]='" + idpro + "'";

                q.UpdateModificar(actualizar);
                String Consulta = "INSERT INTO [dbo].[tvKardex]([detalle],[fechaingreso],[cantidadactual],[cantidadsaliente],[tecnico],[estado],[serieproducto]) VALUES(";
                Consulta = Consulta + "'Ingreso de material',";
                Consulta = Consulta + "GETDATE() ,";
                Consulta = Consulta + "'" + cantidadasignar.getText() + "','0',";
                Consulta = Consulta + "'" + tecnigoasignar.getText() + "',";
                Consulta = Consulta + "'1',";
                Consulta = Consulta + "'" + serieutilizable + "')";

                q.InsertInsertar(Consulta);
                 q.notificaciones("Se registro  "+serieutilizable+" correctamente ", "I");

            } catch (Exception e) {
                q.notificaciones("Error" + e.getMessage(), "");

            }
        }
    }

    public void Codigocontrato() {
        ResultSet st = q.tablas("SELECT top(1) [CODIGOCONTRATO] FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG]    where  ESTADO=1 and ([NOMBRES]+' '+[APELLIDOS]) like '%" + tecnigoasignar.getText() + "%'");

        try {
            while (st.next()) {

                cantidadasignar.setText(st.getString("CODIGOCONTRATO"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignaciondemateriaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
