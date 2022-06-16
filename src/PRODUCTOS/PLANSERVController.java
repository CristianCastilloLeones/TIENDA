/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PRODUCTOS;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import CLASES.PlanesYservicios;
import CLASES.UsoClasePlanesServicios;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class PLANSERVController implements Initializable {
    UsoClasePlanesServicios ob ;
    @FXML
    private TableView planes;
    @FXML
    private TableColumn<String, PlanesYservicios> id;
    @FXML
    private TableColumn<String, PlanesYservicios> plan;
    @FXML
    private TableColumn<String, PlanesYservicios> bajada;
    @FXML
    private TableColumn<String, PlanesYservicios> subida;
    @FXML
    private TableColumn<String, PlanesYservicios> ancho;
    @FXML
    private TableColumn<String, PlanesYservicios> conparticion;
    @FXML
    private TableColumn<Integer, PlanesYservicios> activos;
    @FXML
    private TableColumn<String, PlanesYservicios> cortados;

 
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        plan.setCellValueFactory(new PropertyValueFactory<>("Plan"));
        bajada.setCellValueFactory(new PropertyValueFactory<>("bajada"));
        subida.setCellValueFactory(new PropertyValueFactory<>("Subida"));
        ancho.setCellValueFactory(new PropertyValueFactory<>("AnchoB"));
        conparticion.setCellValueFactory(new PropertyValueFactory<>("comparticion"));
        activos.setCellValueFactory(new PropertyValueFactory<>("Activos"));
        cortados.setCellValueFactory(new PropertyValueFactory<>("Corte"));
        valores();
    }
    
    
    public void valores(){
         planes.getItems().clear();
        ob = new UsoClasePlanesServicios();
        ob.getPlanesT().clear();
        ob.TrabajoInterno();
        planes.setItems(ob.getPlanesT());
      
    }

}
