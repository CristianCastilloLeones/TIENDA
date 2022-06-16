/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class CierredeCajaController implements Initializable {

    String idcaja;
    bas q = new bas();
    @FXML
    private TextField ctvcant1;
    @FXML
    private TextField ctvcant5;
    @FXML
    private TextField ctvcant10;
    @FXML
    private TextField billetecan1;
    @FXML
    private TextField billetecan5;
    @FXML
    private TextField ctvcant25;
    @FXML
    private TextField ctvcant50;
    @FXML
    private TextField ctvcant1dolar;
    @FXML
    private TextField billetecan10;
    @FXML
    private TextField billetecan20;
    @FXML
    private TextField billetecan50;
    @FXML
    private TextField billetecan100;
    @FXML
    private Label ctvtotal1;
    @FXML
    private Label ctvtotal5;
    @FXML
    private Label billetetotal1;
    @FXML
    private Label billetetotal5;
    @FXML
    private Label billetetotal10;
    @FXML
    private Label billetetotal20;
    @FXML
    private Label billetetotal50;
    @FXML
    private Label billetetotal100;
    @FXML
    private Label ctvtotal10;
    @FXML
    private Label ctvtotal20;
    @FXML
    private Label ctvtotal50;
    @FXML
    private Label ctvtotal1dolar;
    @FXML
    private Label TotalMonedas;
    @FXML
    private Label TotalBilletes;
    @FXML
    private Label totalgeneral;
    @FXML
    private ComboBox<String> receptoremisor;
    @FXML
    private Button GuadarCierreCaja;
    @FXML
    private Button ver_detalle;
    @FXML
    private Label valorinicial;
    @FXML
    private DatePicker fechadecierre;
    @FXML
    private TextField cantche;
    @FXML
    private TextField totaltalcheq;
    @FXML
    private TextField totalcantvoucher;
    @FXML
    private TextField valortotalvoucher;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ver_detalle.setOnAction((event) -> {
            q.ventanasAxu("VentanaAUXMOVIMIENTOS.fxml","Reporte de Movimientos");
        });
        GuadarCierreCaja.setOnAction((event) -> {
            Guardar();
            Node source = (Node) event.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
        });
        fechadecierre.setValue(q.FechaActual());
        receptoremisor.setOnAction((event) -> {
            String[] valore = q.CargarValoresCerrar(fechadecierre.getValue().toString(), receptoremisor.getSelectionModel().getSelectedItem()).split(";");
            if (valore.length == 2) {
                valorinicial.setText(valore[1]);
                idcaja = valore[0];
            } else {
                valorinicial.setText("0.0");
                idcaja = "";
                q.notificaciones("El cajero no tiene aperturado saldos", "I");
                q.ventanas("Apertura_de_Caja.fxml");
            }

        });

        fechadecierre.setOnAction((event) -> {
            if (!receptoremisor.getSelectionModel().isEmpty()) {
                String[] valore = q.CargarValoresCerrar(fechadecierre.getValue().toString(), receptoremisor.getSelectionModel().getSelectedItem()).split(";");
                if (valore.length == 2) {
                    valorinicial.setText(valore[1]);
                    idcaja = valore[0];
                } else {
                    
                    valorinicial.setText("0.0");
                    idcaja = "";
                    q.notificaciones("El cajero no tiene aperturado saldos", "I");
                      q.ventanas("Apertura_de_Caja.fxml");
                      //q.CerrarVentanas(event);
                }
            } else {
                q.notificaciones("Debe Seleccionar un Cajero", "I");
            }
        });

        q.Cajerosdisponibles(receptoremisor);

        valortotalvoucher.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {
                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9] ", "I");
                }
            } catch (Exception ex) {
            }
        });
        totaltalcheq.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {
                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9] ", "I");
                }
            } catch (Exception ex) {
            }
        });
        totalcantvoucher.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        cantche.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        ctvcant1.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        ctvcant5.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        ctvcant10.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        ctvcant25.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        ctvcant50.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        ctvcant1dolar.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        billetecan1.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        billetecan5.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        billetecan10.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        billetecan20.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        billetecan50.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });
        billetecan100.setOnKeyTyped((event) -> {
            SoloDijitos(event);
        });

        ctvcant1.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                ctvcant1.setText("0");

            } else {
                ValorTotal(0.01, Double.parseDouble(newText), ctvtotal1);
            }
        });
        ctvcant5.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                ctvcant5.setText("0");
            } else {
                ValorTotal(0.05, Double.parseDouble(newText), ctvtotal5);
            }
        });
        ctvcant10.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                ctvcant10.setText("0");
            } else {
                ValorTotal(0.10, Double.parseDouble(newText), ctvtotal10);
            }
        });
        ctvcant25.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                ctvcant25.setText("0");
            } else {
                ValorTotal(0.25, Double.parseDouble(newText), ctvtotal20);
            }
        });
        ctvcant50.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                ctvcant50.setText("0");
            } else {
                ValorTotal(0.50, Double.parseDouble(newText), ctvtotal50);
            }
        });
        ctvcant1dolar.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                ctvcant1dolar.setText("0");
            } else {
                ValorTotal(1, Double.parseDouble(newText), ctvtotal1dolar);
            }
        });
        billetecan1.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                billetecan1.setText("0");
            } else {
                ValorTotal(1, Double.parseDouble(newText), billetetotal1);
            }
        });
        billetecan5.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                billetecan5.setText("0");
            } else {
                ValorTotal(5, Double.parseDouble(newText), billetetotal5);
            }
        });
        billetecan10.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                billetecan10.setText("0");
            } else {
                ValorTotal(10, Double.parseDouble(newText), billetetotal10);
            }
        });
        billetecan20.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                billetecan20.setText("0");
            } else {
                ValorTotal(20, Double.parseDouble(newText), billetetotal20);
            }
        });
        billetecan50.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                billetecan50.setText("0");
            } else {
                ValorTotal(50, Double.parseDouble(newText), billetetotal50);
            }
        });
        billetecan100.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty() || Double.parseDouble(newText) < 0) {
                billetecan100.setText("0");
            } else {
                ValorTotal(100, Double.parseDouble(newText), billetetotal100);
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

    public void ValorTotal(double valor, double cantidad, Label asignado) {
        asignado.setText(q.FormatoDecimal1(valor * cantidad));
        Sumatoria();
    }

    public void Sumatoria() {
        TotalMonedas.setText(q.FormatoDecimal(conversion(ctvtotal1.getText()) + conversion(ctvtotal5.getText()) + conversion(ctvtotal10.getText()) + conversion(ctvtotal20.getText()) + conversion(ctvtotal50.getText()) + conversion(ctvtotal1dolar.getText())));
        TotalBilletes.setText(q.FormatoDecimal(conversion(billetetotal1.getText()) + conversion(billetetotal5.getText()) + conversion(billetetotal10.getText()) + conversion(billetetotal20.getText()) + conversion(billetetotal50.getText()) + conversion(billetetotal100.getText())));
        totalgeneral.setText(q.FormatoDecimal(conversion(TotalMonedas.getText()) + conversion(TotalBilletes.getText())));
    }

    public double conversion(String valor) {
        return Double.parseDouble(valor.replace(",", "."));
    }

    public void Guardar() {
       

        if (!receptoremisor.getSelectionModel().isEmpty()) {
            VerificarValores();
            q.CerrarCaja(ConvercionInt(ctvcant1.getText()),
                    ConvercionInt(ctvcant5.getText()),
                    ConvercionInt(ctvcant10.getText()),
                    ConvercionInt(ctvcant25.getText()),
                    ConvercionInt(ctvcant50.getText()),
                    ConvercionInt(ctvcant1dolar.getText()),
                    ConvercionInt(billetecan1.getText()),
                    ConvercionInt(billetecan5.getText()),
                    ConvercionInt(billetecan10.getText()),
                    ConvercionInt(billetecan1.getText()),
                    ConvercionInt(billetecan50.getText()),
                    ConvercionInt(billetecan100.getText()),
                    ConversionFloat(ctvtotal1.getText()),
                    ConversionFloat(ctvtotal5.getText()),
                    ConversionFloat(ctvtotal10.getText()),
                    ConversionFloat(ctvtotal20.getText()),
                    ConversionFloat(ctvtotal50.getText()),
                    ConvercionInt(ctvtotal1dolar.getText()),
                    ConvercionInt(billetetotal1.getText()),
                    ConvercionInt(billetetotal5.getText()),
                    ConvercionInt(billetetotal10.getText()),
                    ConvercionInt(billetetotal20.getText()),
                    ConvercionInt(billetetotal50.getText()),
                    ConvercionInt(billetetotal100.getText()),
                    ConversionFloat(totalgeneral.getText()),
                    ConvercionInt(cantche.getText()),
                    ConvercionInt(totaltalcheq.getText()),
                    ConversionFloat(TotalMonedas.getText()),
                    ConvercionInt(TotalBilletes.getText()),
                    idcaja,
                    ConvercionInt(totalcantvoucher.getText()),
                    ConversionFloat(valortotalvoucher.getText()));
            

        }
    }

    public int ConvercionInt(String x) {
        return Integer.parseInt(x.replace(".00", ""));
    }

    public float ConversionFloat(String x) {
        return Float.parseFloat(x);
    }

    public void VerificarValores() {
        if (ctvcant1.getText().isEmpty()) {
            ctvcant1.setText("0");
        }
        if (ctvcant5.getText().isEmpty()) {
            ctvcant5.setText("0");
        }
        if (ctvcant10.getText().isEmpty()) {
            ctvcant10.setText("0");
        }
        if (billetecan1.getText().isEmpty()) {
            billetecan1.setText("0");
        }
        if (billetecan5.getText().isEmpty()) {
            billetecan5.setText("0");
        }
        if (ctvcant25.getText().isEmpty()) {
            ctvcant25.setText("0");
        }
        if (ctvcant50.getText().isEmpty()) {
            ctvcant50.setText("0");
        }
        if (ctvcant1dolar.getText().isEmpty()) {
            ctvcant1dolar.setText("0");
        }
        if (billetecan10.getText().isEmpty()) {
            billetecan10.setText("0");
        }
        if (billetecan20.getText().isEmpty()) {
            billetecan20.setText("0");
        }
        if (billetecan50.getText().isEmpty()) {
            billetecan50.setText("0");
        }
        if (billetecan100.getText().isEmpty()) {
            billetecan100.setText("0");
        }
        if (ctvtotal1.getText().isEmpty()) {
            ctvtotal1.setText("0");
        }
        if (ctvtotal5.getText().isEmpty()) {
            ctvtotal5.setText("0");
        }
        if (billetetotal1.getText().isEmpty()) {
            billetetotal1.setText("0");
        }
        if (billetetotal5.getText().isEmpty()) {
            billetetotal5.setText("0");
        }
        if (billetetotal10.getText().isEmpty()) {
            billetetotal10.setText("0");
        }
        if (billetetotal20.getText().isEmpty()) {
            billetetotal20.setText("0");
        }
        if (billetetotal50.getText().isEmpty()) {
            billetetotal50.setText("0");
        }
        if (billetetotal100.getText().isEmpty()) {
            billetetotal100.setText("0");
        }
        if (ctvtotal10.getText().isEmpty()) {
            ctvtotal10.setText("0");
        }
        if (ctvtotal20.getText().isEmpty()) {
            ctvtotal20.setText("0");
        }
        if (ctvtotal50.getText().isEmpty()) {
            ctvtotal50.setText("0");
        }
        if (ctvtotal1dolar.getText().isEmpty()) {
            ctvtotal1dolar.setText("0");
        }
        if (TotalMonedas.getText().isEmpty()) {
            TotalMonedas.setText("0");
        }
        if (TotalBilletes.getText().isEmpty()) {
            TotalBilletes.setText("0");
        }
        if (totalgeneral.getText().isEmpty()) {
            totalgeneral.setText("0");
        }
        if (cantche.getText().isEmpty()) {
            cantche.setText("0");
        }
        if (totaltalcheq.getText().isEmpty()) {
            totaltalcheq.setText("0");
        }
        if (totalcantvoucher.getText().isEmpty()) {
            totalcantvoucher.setText("0");
        }
        if (valortotalvoucher.getText().isEmpty()) {
            valortotalvoucher.setText("0");
        }
       
    }
}
