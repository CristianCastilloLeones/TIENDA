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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class MDLBOPRODUCTOSController implements Initializable {
     ObservableList<produ> clt = FXCollections.observableArrayList();
     bas q = new bas();
    @FXML
    private Button nuevo;
    @FXML
    private TextField buscarpro;
    @FXML
    private TableView tablaproducto;
    @FXML
    private TableColumn<String, produ> id;
    @FXML
    private TableColumn<String, produ>producto;
    @FXML
    private TableColumn<String, produ> proveedor;
    @FXML
    private TableColumn<String, produ> serie;
    @FXML
    private TableColumn<String, produ> macid;
    @FXML
    private TableColumn<String, produ> pon;
    @FXML
    private TableColumn<String, produ> precio;
    @FXML
    private TableColumn<String, produ> estado;
    @FXML
    private TableColumn<String, produ>cliente;
    @FXML
    private TableColumn<String, produ> ingreso;
    @FXML
    private TableColumn<String, produ> fecha;
    @FXML
    private Button subir;
    @FXML
    private TableColumn<String, produ>IP;
    @FXML
    private TableColumn<String, produ> activa;
    @FXML
    private TableColumn<String, produ>lote;
    @FXML
    private TableColumn<String, produ>metraje;
    @FXML
    private Button actualizar;
    @FXML
    private Button adigmaterial;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adigmaterial.setOnAction((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Asignaciondemateria.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MDLBOPRODUCTOSController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         try {
             // TODO
             cargar();
         } catch (SQLException ex) {
             Logger.getLogger(MDLBOPRODUCTOSController.class.getName()).log(Level.SEVERE, null, ex);
         }
         actualizar.setOnAction((event) -> {
             try {
                 cargar();
             } catch (SQLException ex) {
                 Logger.getLogger(MDLBOPRODUCTOSController.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
    }
    public void cargar() throws SQLException{
      
        tablaproducto.setItems(clt);
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
         producto.setCellValueFactory(new PropertyValueFactory<>("producto"));
         proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
         serie.setCellValueFactory(new PropertyValueFactory<>("serie"));
         macid.setCellValueFactory(new PropertyValueFactory<>("macid"));
         pon.setCellValueFactory(new PropertyValueFactory<>("pon"));
         precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
         cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
         ingreso.setCellValueFactory(new PropertyValueFactory<>("ingreso"));
         fecha.setCellValueFactory(new PropertyValueFactory<>("fechaactiva"));
        IP.setCellValueFactory(new PropertyValueFactory<>("ip"));
        activa.setCellValueFactory(new PropertyValueFactory<>("activa"));
        lote.setCellValueFactory(new PropertyValueFactory<>("lote"));
        metraje.setCellValueFactory(new PropertyValueFactory<>("metraje"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
         String con = "SELECT * FROM [BDAirnet].[dbo].[tvproductosdj]";
         tablaproducto.getItems().clear();
         ResultSet di = q.tablas(con);
         while (di.next()){
              clt.add(new produ(di.getString("id"),di.getString("producto"),di.getString("proveedor")
                      ,di.getString("serie"),di.getString("macid"), di.getString("pon"),di.getString("precio")
                      ,di.getString("cliente"),di.getString("ingreso"),di.getString("fechaactiva"),di.getString("ip")
                      ,di.getString("activa"),di.getString("lote"),di.getString("metraje"),di.getString("estado")));
         }
         
    }
    @FXML
     private void nuevo(){
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NEWPRO.fxml"));
                   Parent root1 = (Parent) fxmlLoader.load();
                   Stage stage = new Stage();
                   stage.setScene(new Scene(root1));  
                   stage.resizableProperty().setValue(Boolean.FALSE);
                   stage.initModality(Modality.APPLICATION_MODAL);
                   stage.showAndWait();
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }     }
    
}
