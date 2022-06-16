/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAJA;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import CLASES.Clase_PromesaPago;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class PromesadepagoController implements Initializable {
   
    List cliente = new ArrayList();
    bas q = new bas();
    @FXML
    private Button btoregistrarsalida;
    @FXML
    private TextField autorizadopor;
    @FXML
    private TextField solicitante;
    @FXML
    private TextField numerosalidad;
    @FXML
    private Button editar;
    @FXML
    private Button buscar;
    @FXML
    private DatePicker fechadecorte;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autorizadopor.setText(VariablesDeUso.nombreusuario);
        btoregistrarsalida.setOnAction((event) -> {
            ValoresparaGuardar();
            Node source = (Node) event.getSource();
   Stage stage1 = (Stage) source.getScene().getWindow();
   stage1.close();
   q.notificaciones("Exito al Generar Ptomesa de pago", "I");
        });
       
        q.listaclient(cliente);
        q.prediccion(solicitante, cliente);
    }    
    public void ValoresparaGuardar(){
        Clase_PromesaPago pro = new Clase_PromesaPago(solicitante.getText(), fechadecorte.getValue(), autorizadopor.getText());
        pro.buscar();}
       
  
}
