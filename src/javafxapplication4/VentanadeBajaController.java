/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import CLASES.bas;
import CLASES.Clase_Estado_Individual;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
  * @author cl
 */
public class VentanadeBajaController implements Initializable {
    Clase_Estado_Individual ob1;
    VariablesDeUso ob = new VariablesDeUso();
    bas q = new bas();
    @FXML
    private Label cliente;
    @FXML
    private Label detalle;
    @FXML
    private Button Guardar;
    @FXML
    private TextField valoraregistrar;
    @FXML
    private TextField saldo;
    @FXML
    private TextField TOTALDEUDA;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[]valor=Clase_Estado_Individual.clientetext.split("-");
        cliente.setText(valor[0]);
        detalle.setText(valor[1]);
        TOTALDEUDA.setText(valor[2]);
        
        valoraregistrar.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.isEmpty()) {
               ob.ValorTotal2=q.ConvertidorStringaDouble(TOTALDEUDA.getText())-q.ConvertidorStringaDouble(newText);
               saldo.setText(q.FormatoDecimal(ob.ValorTotal2));
            }

        });
        valoraregistrar.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {

                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");
                }

            } catch (Exception ex) {
            }
        });
        Guardar.setOnAction((event) -> {
             if(DardeBaja()){
                 q.CerrarVentanas(event);
             }
        });
    }    
    
     public boolean DardeBaja(){
         if(!valoraregistrar.getText().isEmpty()){
             
         
         if(q.ConvertidorStringaDouble(saldo.getText())<0){
             q.notificaciones("El valor para cubrir la deuda debe ser igual o menor que el valor Total de la deuda", "I");
         }else {
             if(q.ConvertidorStringaDouble(saldo.getText())>0){
                  q.UpdateModificar("UPDATE  [BDAirnet].[dbo].[detallesfacxtura]  "
                          + "SET numerofactura='" + "---"+ "'"
                          + ",valortotal='" + valoraregistrar.getText() + 
                          "',valorunitario='" + valoraregistrar.getText() + "' ,Estado=0 "
                                  + "claveacceso='" + VariablesDeUso.nombreusuario.trim() + "'"
                                  + "' where  detalle like '%"+detalle.getText()+"%' and codigo like '%"+q.BusquedaCedula(cliente.getText())+"%'");
              q.Facturar(cliente.getText(), saldo.getText(), detalle.getText(), 1, q.BusquedaCedula(cliente.getText()), "1", "E");
             }else {
                 q.DeleteEliminar(" update  [BDAirnet].[dbo].[detallesfacxtura] set Estado=0"
                + ",numerofactura='---',"
                + "claveacceso='" + VariablesDeUso.nombreusuario.trim() + "' where  detalle like '%"+detalle.getText()+"%' and codigo like '%"+q.BusquedaCedula(cliente.getText())+"%'");
        q.notificaciones("Actualizacion Correcta", "I");
        
             }
             return true;
         }
        }else {
             q.notificaciones("Ingrese valor correcto ", "I");
              return false;
         }
           return false;
    }
     
}
