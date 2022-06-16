/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGIN;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import CLASES.Clase_Login;
import CLASES.ClasedePropiedades;
import CLASES.bas;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafxapplication4.JavaFXApplication4;


/**
 * FXML Controller class
 *
  * @author cl
 */
public class LoginController implements Initializable {
    Clase_Login ob;
   
    @FXML
    private TextField pass1;
    @FXML
    private TextField usi;
    bas q = new bas();
    @FXML
    private Button entrar;
    Image iconoprincipal = new Image(getClass().getResourceAsStream("/imagenes/bot2.png"));
    @FXML
    private ImageView mostrar;
    @FXML
    private TextField mostrartexto;
    @FXML
    private Pane panelprincipal;
    @FXML
    private ImageView imagenvisor;
    @FXML
    private Label etiqu3;
    @FXML
    private Separator separador1;
    @FXML
    private Separator separador2;
    @FXML
    private Label etiqu2;
    @FXML
    private AnchorPane principal;
    @FXML
    private ProgressIndicator pcind;

   
    public boolean login() throws SQLException, IOException {
        ob = new Clase_Login(usi.getText(), pass1.getText());
        
        if(ob.login()){
           // q.ventanasAxu("/javafxapplication4/Ventana_Principal.fxml","Aircontrol");
            q.animateMessage(principal);
                   
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafxapplication4/MODULOPRINCIPAL.fxml"));

        try {
         Parent   root1 = (Parent) fxmlLoader.load();
         Scene   scene = new Scene(root1);
          Stage  stage1 = new Stage();
            q.Estilos(root1);
            stage1.getIcons().add(iconoprincipal);
            stage1.setScene(scene);
            stage1.setMaximized(false);
            stage1.setOnCloseRequest((event) -> {
               
             try {
                 Reabri();
                 //***********************
                 
                 
                 
                 
                 
                 //***********************
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
            });
            stage1.setTitle("Aircontrol");
            stage1.initModality(Modality.WINDOW_MODAL);
            stage1.show();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
             return false;
                         
 
        }else {
                     q.ventanas("/LOGIN/login.fxml");
                      usi.setText("");
                    pass1.setText("");
                    q.notificaciones("Contraseñas y/o Usuario incorrectos ", "");
                    
                    return false;
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        usi.requestFocus();
        entrar.setDisable(true);
        mostrartexto.toBack();
        mostrar.setOnMousePressed((event) -> {
            mostrartexto.setText(pass1.getText());
            pass1.setVisible(false);
            mostrartexto.toFront();
        });
        mostrar.setOnMouseReleased((event) -> {
            mostrartexto.clear();
            pass1.setVisible(true);
            mostrar.toFront();
            mostrartexto.toBack();
        });
        pass1.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                q.CerrarVentanas(event);
                try {
                     q.CerrarVentanas(event);
                         login();
                        q.animateMessage(principal);
                        
                      
                    
                } catch (SQLException ex) {
                    q.notificaciones(ex.getMessage(), "I");
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        entrar.setOnAction((event) -> {
            try {
                q.CerrarVentanas(event);
                login();    
            } catch (SQLException ex) {
                q.notificaciones(ex.getMessage(), "");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        usi.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
                pass1.requestFocus();
            }
        });
        pass1.textProperty().addListener((obs, oldText, newText) -> {
            if (pass1.getText().isEmpty()) {
                entrar.setDisable(true);
            } else {
                if (newText.isEmpty()) {
                    entrar.setDisable(true);
                } else {
                    entrar.setDisable(false);
                }
            }
        });
    }

    
    public void Reabri() throws IOException{
        Stage stage = new Stage();
        ClasedePropiedades ob = new ClasedePropiedades();
        ob.CreacionArchivo(false);
        
        Parent root = FXMLLoader.load(getClass().getResource("/LOGIN/login.fxml"));
        Scene scene2 = new Scene(root);
        URL url = JavaFXApplication4.class.getResource("EstilosGenerales.css");
        if (url == null) {
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene2.getStylesheets().clear();
        scene2.getStylesheets().add(css);
        stage.getIcons().add(iconoprincipal);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Aircontrol ISP ");
        stage.setScene(scene2);
        stage.initStyle(StageStyle.DECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
