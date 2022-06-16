/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import CLASES.ClasedePropiedades;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 *
 */
public class JavaFXApplication4 extends Application {

    Image iconoprincipal = new Image(getClass().getResourceAsStream("/imagenes/bot2.png"));

    @Override
    public void start(Stage primaryStage) throws IOException {
       
        
        Stage stage = new Stage();
        ClasedePropiedades ob = new ClasedePropiedades();
        ob.CreacionArchivo(false);
        
        Parent root = FXMLLoader.load(getClass().getResource("/LOGIN/login.fxml"));
        Scene scene = new Scene(root);
        URL url = JavaFXApplication4.class.getResource("EstilosGenerales.css");
        if (url == null) {
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(css);
        stage.getIcons().add(iconoprincipal);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Aircontrol ISP ");
        stage.initOwner(primaryStage);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
