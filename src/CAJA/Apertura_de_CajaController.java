/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAJA;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class Apertura_de_CajaController implements Initializable {
    bas q = new bas();
    @FXML
    private Button guardar;
    @FXML
    private Button cancelar;
    @FXML
    private DatePicker fechadeapertura;
    @FXML
    private ComboBox<String> cajeros;
    @FXML
    private TextField valordeapertura;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cancelar.setOnAction((event) -> {
            q.notificaciones("Servicio Cancelado", "I");
            Node source = (Node) event.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
        });
        guardar.setOnAction((event) -> {
            Guardar();
            Node source = (Node) event.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            q.notificaciones("Exito al Guardar Datos", "I");
        });
        fechadeapertura.setValue(q.FechaActual());
        q.Cajerosdisponibles(cajeros);
        valordeapertura.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        valordeapertura.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                guardar.setDisable(true);
            } else {
                guardar.setDisable(false);
            }
        });

    }

    public void SoloDijitos(KeyEvent event) {
        try {
            char key = event.getCharacter().charAt(0);
            if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0') {
            } else {
                event.consume();
                q.notificaciones("Ingresar solo Digitos [0-9] ", "I");
            }
        } catch (Exception ex) {

        }
    }

    public void Guardar() {
        if (!valordeapertura.getText().trim().isEmpty() || !cajeros.getSelectionModel().isEmpty()) {
            q.GuardarAperturaCaja(Float.parseFloat(valordeapertura.getText()), cajeros.getSelectionModel().getSelectedItem(), fechadeapertura.getValue().toString(), VariablesDeUso.nombreusuario);
        } else {
            q.notificaciones("Complete todos los Datos", "I");
        }

    }

}
