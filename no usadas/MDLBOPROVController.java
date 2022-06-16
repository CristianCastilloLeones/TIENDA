/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Cristian 
 */
public class MDLBOPROVController implements Initializable {
        bas a = new  bas() ;
     ObservableList<datosproveerdor> clt = FXCollections.observableArrayList();
    @FXML
    private Button nuevobt;
    @FXML
    private TableView tablaproveedor;
    @FXML
    private TableColumn<String, datosproveerdor> ide;
    @FXML
    private TableColumn<String, datosproveerdor> proveedor;
    @FXML
    private TableColumn<String, datosproveerdor>  correo;
    @FXML
    private TableColumn<String, datosproveerdor>  telefono;
    @FXML
    private TableColumn<String, datosproveerdor>  direccion;
    @FXML
    private TableColumn<String, datosproveerdor> ruc;
    @FXML
    private void nuevopro(ActionEvent actionEvent) throws IOException
    {
      try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MDLBONOVAP.fxml"));
                   Parent root1 = (Parent) fxmlLoader.load();
                   Stage stage = new Stage();
                   stage.setScene(new Scene(root1));  
                   stage.resizableProperty().setValue(Boolean.FALSE);
                   stage.initModality(Modality.APPLICATION_MODAL);
                   stage.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenar();
    }    
    
    public void llenar (){
           tablaproveedor.setItems(clt);
           ide.setCellValueFactory(new PropertyValueFactory<>("ide"));
           proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
           ruc.setCellValueFactory(new PropertyValueFactory<>("ruc"));
           correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
           telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
           direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
           String consulta = "SELECT  [ide],[proveedor],[ruc],[correo],[telefono],[direccion] FROM [BDAirnet].[dbo].[tproveedordj]";
           tablaproveedor.getItems().clear();
         ResultSet st = a.tablas(consulta);
         try {
            
            while (st.next()){
                clt.add(new datosproveerdor(st.getString("ide"),st.getString("proveedor"),st.getString("ruc"),st.getString("correo"),st.getString("telefono"),st.getString("direccion")));
            }
     
    }catch(SQLException  exception){
        a.notificaciones("Se produjo un error de nivel : "+exception.getMessage(),"");
       
    }
    
    
}
}