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
  * @author cl
 */
public class MDLBOTIPOPController implements Initializable {
        bas a = new  bas() ;
     ObservableList<tipopro> clt = FXCollections.observableArrayList();
    @FXML
    private TableView bodegatipo;
    @FXML
    private TableColumn<String, tipopro> id;
    @FXML
    private TableColumn<String, tipopro> producto;
    @FXML
    private TableColumn<String, tipopro> descripcion;
    @FXML
    private TableColumn<String, tipopro> tipo;
    @FXML
    private TableColumn<String, tipopro> impuesto;
    @FXML
    private TableColumn<String, tipopro> disponibles;
    @FXML
    private Button actualizar;
    
    @FXML
    private void nuevo(ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MBDLBOCATPRO.fxml"));
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
        bodegatipo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        cargar();
        actualizar.setOnAction((event) -> {
            cargar();
        });
    }    
    public void cargar (){
        bodegatipo.setItems(clt);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        producto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        impuesto.setCellValueFactory(new PropertyValueFactory<>("impuesto"));
        disponibles.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        String consulta ="SELECT  [ide],[tipo],[producto],[detalle],[cantidad],[impuesto],[unidadmedida]FROM [BDAirnet].[dbo].[tpcategoriadj]";
        bodegatipo.getItems().clear();
         ResultSet st = a.tablas(consulta);
         String cuantos="";
         try {
            
            while (st.next()){
                String can ="SELECT COUNT (*) as cantidad  FROM [BDAirnet].[dbo].[tvproductosdj] where producto = '"+st.getString("producto")+"' and estado ='Inactivo'";
                ResultSet ct=a.tablas(can);
                while(ct.next()){
                    cuantos=String.valueOf(ct.getInt("cantidad"));
                }
                clt.add(new tipopro(st.getString("ide"), st.getString("tipo"), st.getString("producto"),st.getString("detalle"),st.getString("impuesto"),cuantos));
            }
    }catch(SQLException exception){
        a.notificaciones("Se produjo un error de nivel : "+exception.getMessage(), "");
    
    }
}

}
