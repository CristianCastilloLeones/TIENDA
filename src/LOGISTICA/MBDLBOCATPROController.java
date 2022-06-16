/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGISTICA;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import CLASES.bas;


/**
 * FXML Controller class
 *
  * @author cl
 */
public class MBDLBOCATPROController implements Initializable {
    bas q = new bas();
    @FXML
    private ComboBox <String>iva;
    @FXML
    private TextArea detalle;
    @FXML
    private TextField codigointerno;
    @FXML
    private Button guardar;
    @FXML
    private Button cerrar;
    @FXML
    private TextField nombrep;
    @FXML
    private ComboBox<String> unidadmedida;
    @FXML
    private ComboBox<String> categoria;
    @FXML
    public void guartar(ActionEvent actionEvent){
        int vg =0;
        if (iva.getSelectionModel().isEmpty()){ System.out.println("Error iva ");}else {vg++;}
        if (detalle.getText().isEmpty()){System.out.println("Error detalle ");}else {vg++;}
        if (nombrep.getText().isEmpty()){System.out.println("Error nombre ");}else {vg++;}
        if (codigointerno.getText().isEmpty()){System.out.println("Error codigo ");}else {vg++;}
        if (unidadmedida.getSelectionModel().isEmpty()){System.out.println("Error unidad ");}else {vg++;}
        if (categoria.getSelectionModel().isEmpty()){System.out.println("error categoria");}else {vg++;}
        if (vg==6){
             
            q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tpcategoriadj]([tipo],[producto],[detalle],[cantidad],[impuesto],[unidadmedida]) VALUES('"+categoria.getSelectionModel().getSelectedItem().toString()+"','"+nombrep.getText()+"','"+detalle.getText()+"','"+"0"+"','"+iva.getSelectionModel().getSelectedItem().toString()+"','"+unidadmedida.getSelectionModel().getSelectedItem().toString()+"')");
            Node source = (Node) actionEvent.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            q.notificaciones("Datos Guardados Correctamente","I");

        }else{
            q.notificaciones("Se produjo un error ","");
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        unidadmedida.getItems().addAll("Metros","Unidades");
        categoria.getItems().addAll("Venta","Equipo de Computo","Suministro de Oficina","Utensilios & Herramientas");
        iva.getItems().addAll("0","12");
    }    
    
}
