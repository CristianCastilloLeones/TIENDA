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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import CLASES.bas;


/**
 * FXML Controller class
 *
  * @author cl
 */
public class MDLBONOVAPController implements Initializable {
    bas q = new bas();
    @FXML
    private TextField telefono;
    @FXML
    private TextField direccion;
    @FXML
    private Button guardar;
    @FXML
    private Button cerrar;
    @FXML
    private TextField proveedor;
    @FXML
    private TextField correo;
    @FXML
    private TextField ruc;

    
    @FXML
    private void guardardatos(ActionEvent actionEvent){

        int acce=0;
        if (telefono.getText().isEmpty()|| telefono.getText().length()<3){
            q.notificaciones("Campo imcompleto de telefono", "");
            
            
        }else {
            acce++;
        }
         if (direccion.getText().isEmpty() || direccion.getText().length()<3){
              q.notificaciones("Campo imcompleto de Direccion", "");
                }else {
            acce++;
        }
          if (proveedor.getText().isEmpty()|| proveedor.getText().length()<3){
              q.notificaciones("Campo imcompleto de Proveedor", "");
           }else {
            acce++;
        }
           if (correo.getText().isEmpty() || correo.getText().length()<3){
               q.notificaciones("Campo imcompleto de Correo", "");
        }else {
            acce++;
        }
            if (ruc.getText().isEmpty() || ruc.getText().length()!=13){
                q.notificaciones("Campo imcompleto de Ruc", "");
           
        }else {
                
                if (q.VerificarCedula(ruc.getText())){
                     acce++;
                }else {
                    q.notificaciones("Numero de Ruc Invalido ", "");
                }
           
        }
            if(q.VerificarCedula(ruc.getText())){
                
            
            if (acce>4){
                q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tproveedordj] "
                        + "([proveedor],[ruc],[correo],[telefono],[direccion]) VALUES('"+proveedor.getText()+"','"+ruc.getText()+"','"+correo.getText()+"','"+telefono.getText()+"','"+direccion.getText()+"')");
               q.notificaciones("Datos Guardados Correctamente", "I");
            Node source = (Node) actionEvent.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close(); 
            }else {
                q.notificaciones("Se produjo un error ", "");
                }
            }else {
                q.notificaciones("Verificar RUC","I");
            }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    } 
    
    

}
