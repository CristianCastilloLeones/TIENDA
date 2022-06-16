/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SOPORTE;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class VERTICKETController implements Initializable {
    bas q = new bas();
     Image image1;
     File imgFile;
    @FXML
    private Label numero;
    @FXML
    private Label cliente;
    @FXML
    private Label detalle;
    @FXML
    private ImageView foto;
    @FXML
    private Label cdt;
    @FXML
    private Button imprimir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            inicio();
        } catch (SQLException ex) {
            Logger.getLogger(VERTICKETController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void inicio() throws SQLException{
         numero.setText(VariablesDeUso.val);
        ResultSet valor = q.tablas("SELECT * FROM [BDAirnet].[dbo].[tickets]where numero = '"+numero.getText()+"'"); 
      
        while (valor.next()){
            cliente.setText(valor.getString("usuario"));
            String sp="";
            if(valor.getString("materialesusados")==null){
            sp="Sin Datos aun";}
            else {
            if(valor.getString("materialesusados").contains("-")){
                
                String [] datos=valor.getString("materialesusados").split("-");
                for(int i =0;i<datos.length;i++){
                    sp=sp+"\n"+datos[i];
                }
            }else
                sp=valor.getString("materialesusados");}
            detalle.setText(valor.getString("asunto")+ " " + valor.getString("codigo") +"\n Comentario : "+sp );
            imgFile = new File(valor.getString("evidencia"));
        }
        image1 = new Image("file:" + imgFile.getAbsolutePath());
        foto.setImage(image1);
    }
}
