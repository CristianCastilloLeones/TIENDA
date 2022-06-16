/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
  * @author cl
 */
public class GeneradordecobroController implements Initializable {
    bas q = new bas();
    RecepciondepagoController cargar = new RecepciondepagoController();
    List list = new ArrayList();
    public static String nomCliente3;
    public static String cedu2;
    public static String disc1;
    int discapacidad=1;
    @FXML
    private Button añadir;
    @FXML
    private TextField valor;
    @FXML
    private Label nombrecliente;
    @FXML
    private Label contrato;
    @FXML
    private TextField intem;
    @FXML
    private TextField cantidad;
    @FXML
    private Label total;
    @FXML
    private CheckBox anticipo;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        valor.textProperty().addListener((obs, oldText, newText) -> {
            if(!newText.isEmpty()){
            
        if(!cantidad.getText().isEmpty()){
                DecimalFormat formato1 = new DecimalFormat("#.00");
                double total=Double.parseDouble(newText)*Double.parseDouble(cantidad.getText());
                this.total.setText(String.valueOf(formato1.format(total)));
            }else 
            {   cantidad.setText("1");
                DecimalFormat formato1 = new DecimalFormat("#.00");
                double total=Double.parseDouble(newText)*Double.parseDouble(cantidad.getText());
                this.total.setText(String.valueOf(formato1.format(total)));
            }
            }});
        anticipo.setOnAction((event) -> {
            if(anticipo.isSelected()){
                valor.setDisable(false);
                intem.setText("Anticipo");
                intem.setDisable(true);
            }else {
                valor.setDisable(true);
                intem.setText("Anticipo");
                 intem.setDisable(false);
            }
        });
        contrato.setText(cedu2);
        nombrecliente.setText(nomCliente3);
        if(disc1.isEmpty()){
            discapacidad=1;
        }else {
            discapacidad=2;
        }
        valor.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.ENTER){
               
            if(!cantidad.getText().isEmpty()){
                DecimalFormat formato1 = new DecimalFormat("#.00");
                double total=Double.parseDouble(valor.getText())*Double.parseDouble(cantidad.getText());
                this.total.setText(String.valueOf(formato1.format(total)));
            }else 
            {   cantidad.setText("1");
                DecimalFormat formato1 = new DecimalFormat("#.00");
                double total=Double.parseDouble(valor.getText())*Double.parseDouble(cantidad.getText());
                this.total.setText(String.valueOf(formato1.format(total)));
            }
            }});
        // busqueda de porductos 
        ResultSet cl =q.tablas("SELECT [producto] FROM [BDAirnet].[dbo].[tpcategoriadj] where tipo='Venta'");
        try {
            while (cl.next()){
                list.add( cl.getString("producto")) ;} } 
        catch (SQLException ex) {System.out.println(ex.getMessage()); }
       // llenado de productos como items
         q.prediccion(intem, list);
         intem.setOnKeyPressed((KeyEvent event) -> {
            if(event.getCode() == KeyCode.ENTER){
                try {
                    ResultSet precio = q.tablas("SELECT top(1) precio FROM [BDAirnet].[dbo].[tvproductosdj] where producto ='"+intem.getText()+"'");
                    while(precio.next()){
                        if(precio.getString("precio").replace(" ", "").equals("0")){
                            valor.setDisable(false);
                        }else {
                            valor.setText(precio.getString("precio").replace(" ", "")); 
                        }
                        }} catch (SQLException ex) {  Logger.getLogger(GeneradordecobroController.class.getName()).log(Level.SEVERE, null, ex);} 
            
            if(!cantidad.getText().isEmpty()){
                DecimalFormat formato1 = new DecimalFormat("#.00");
                double total=Double.parseDouble(valor.getText())*Double.parseDouble(cantidad.getText());
                this.total.setText(String.valueOf(formato1.format(total)));
            }else 
            {   cantidad.setText("1");
                DecimalFormat formato1 = new DecimalFormat("#.00");
                double total=Double.parseDouble(valor.getText())*Double.parseDouble(cantidad.getText());
                this.total.setText(String.valueOf(formato1.format(total)));
            }}});
         añadir.setOnAction((event) -> {
            try {
                guardar();
             Node source = (Node) event.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            q.notificaciones("Exito al generar item","I");
             
            } catch (SQLException ex) { 
                q.notificaciones("Error al Guardar", "");
        }});
       
    } 
    
    public void guardar() throws SQLException{
       q.Facturar(nombrecliente.getText(),valor.getText(),intem.getText(), discapacidad,cedu2,cantidad.getText(),"");
       
    }

    
}
