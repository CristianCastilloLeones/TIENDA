/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxapplication4.JavaFXApplication4;

/**
 *
  * @author cl
 */
public class NavegacionEntreVentanas {

    Image iconoprincipal = new Image(getClass().getResourceAsStream("/imagenes/bot2.png"));
    Scene scene = null;

    URL url = null;
    String css = null;
    FXMLLoader fxmlLoader;
    String Ruta;
    Stage stage;
    Parent root1;
    boolean valor;

    public void VentanaP() {
        if (valor) {
            stage.toFront();
        } else {
            ventanas();
        }
    }

    public boolean isValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public NavegacionEntreVentanas(String Ruta, Stage stage, boolean valor) {
        this.Ruta = Ruta;
        this.stage = stage;
        this.valor = valor;
        
    }

    public void ventanas() {

        fxmlLoader = new FXMLLoader(getClass().getResource(Ruta));
        try {
            root1 = (Parent) fxmlLoader.load();
            scene = new Scene(root1);
           
            Estilos(root1);
            stage.getIcons().add(iconoprincipal);
            stage.setScene(scene);
            stage.setMaximized(false);
           // stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Estilos(Parent we) {
        url = JavaFXApplication4.class.getResource("EstilosGenerales.css");
        css = url.toExternalForm();
        we.getStylesheets().clear();
        we.getStylesheets().add(css);
    }
}
