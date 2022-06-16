/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAJA;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import CLASES.Ticket;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;
import javax.print.PrintException;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class RegistrosalidaddedineroController implements Initializable {

    bas q = new bas();
    List list = new ArrayList();
    List motivoslista = new ArrayList();
    @FXML
    private Button btoregistrarsalida;
    @FXML
    private TextField autorizadopor;
    @FXML
    private TextField solicitante;
    @FXML
    private TextField quienentrega;
    @FXML
    private TextField detallemotivo;
    @FXML
    private TextField motivos;
    @FXML
    private TextField cantidad;
    @FXML
    private TextField numerosalidad;
    @FXML
    private Button editar;
    @FXML
    private Button buscar;
    @FXML
    private CheckBox Editar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        autorizadopor.setDisable(true);
        quienentrega.setDisable(true);
        try {
            buscardatos();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrosalidaddedineroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cantidad.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                System.out.println(key);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {

                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");
                }
            } catch (Exception ex) {
            }
        });

        q.prediccion(solicitante, list);
        q.prediccion(motivos, motivoslista);

        btoregistrarsalida.setOnAction((event) -> {
            guardar(event);
        });

        Editar.setOnAction((event) -> {
            if (Editar.isSelected()) {
                autorizadopor.setDisable(true);
                solicitante.setDisable(true);
                quienentrega.setDisable(true);
                detallemotivo.setDisable(true);
                motivos.setDisable(true);
                numerosalidad.setDisable(false);
                numerosalidad.setText("");
                numerosalidad.setTooltip(new Tooltip("Al Terminar Presione Enter"));
                editar.setVisible(true);
                buscar.setVisible(true);
            } else {
                autorizadopor.setDisable(true);
                solicitante.setDisable(false);
                quienentrega.setDisable(true);
                detallemotivo.setDisable(false);
                motivos.setDisable(false);
                numerosalidad.setDisable(true);
                editar.setVisible(false);
                buscar.setVisible(false);
                try {
                    buscardatos();
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrosalidaddedineroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        numerosalidad.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        String rrellenar = "SELECT  [autorizacion],[entregadopor] ,[solitadopor],[motivo],[detalle],[cantidad] FROM [BDAirnet].[dbo].[tvdjSalidadedinero] where [numero]=" + numerosalidad.getText();
                        ResultSet datos = q.tablas(rrellenar);
                        while (datos.next()) {
                            autorizadopor.setText(datos.getString("autorizacion"));
                            quienentrega.setText(datos.getString("entregadopor"));
                            solicitante.setText(datos.getString("solitadopor"));
                            motivos.setText(datos.getString("motivo"));
                            detallemotivo.setText(datos.getString("detalle"));
                            cantidad.setText(datos.getString("cantidad"));

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(RegistrosalidaddedineroController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        editar.setOnAction((event) -> {
            q.UpdateModificar("UPDATE  [BDAirnet].[dbo].[tvdjSalidadedinero]  SET [cantidad] = '" + cantidad.getText() + "',[fechasalida] = GETDATE()  WHERE [numero] = " + numerosalidad.getText());
            q.notificaciones("Datos Actualizados Correctamente", "I");

            Node source = (Node) event.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
        });
        // TODO
    }

    public void buscardatos() throws SQLException {
        list.clear();
        motivoslista.clear();
        ResultSet cant = q.tablas("SELECT MAX(numero) as cantidad FROM [BDAirnet].[dbo].[tvdjSalidadedinero]");
        while (cant.next()) {
            numerosalidad.setText(String.valueOf(cant.getInt("cantidad") + 1));
        }
        autorizadopor.setText("Galo Alfredo Alava Macas");
        quienentrega.setText(VariablesDeUso.nombreusuario);
        ResultSet solicitantes = q.tablas("SELECT ([NOMBRES]+' '+[APELLIDOS]) as nombre FROM [BDAirnet].[dbo].[TBEMPLEADO]");
        while (solicitantes.next()) {
            list.add(solicitantes.getString("nombre"));
        }
        motivoslista.add("Anticipo de Sueldo");
        motivoslista.add("Gastos Administrativos");
        motivoslista.add("Gastos Biaticos");
        motivoslista.add("Gastos Generales");
        motivoslista.add("Gastos Movilizacion");
        motivoslista.add("Gastos de Comunicacion");

    }

    public void guardar(ActionEvent event) {
        //SELECT [autorizacion],[entregadopor],[solitadopor],[motivo],[detalle],[cantidad],[fechasalida] FROM [BDAirnet].[dbo].[tvdjSalidadedinero]
      if (motivoslista.contains(motivos.getText())) {
               if (!solicitante.getText().isEmpty() && !motivos.getText().isEmpty() && !detallemotivo.getText().isEmpty() && !cantidad.getText().isEmpty()) {
               
                q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjSalidadedinero]([autorizacion],[entregadopor] ,[solitadopor],[motivo],[detalle] "
                        + ",[cantidad],[fechasalida],[numero]) VALUES ('" + autorizadopor.getText() + "','" + quienentrega.getText() + "','" + solicitante.getText() + "','" + motivos.getText() + "' ,'" + detallemotivo.getText() + "','" + cantidad.getText() + "'," + "GETDATE() ," + numerosalidad.getText() + ")");
                 impresion();
                 impresion();
                Node source = (Node) event.getSource();
                Stage stage1 = (Stage) source.getScene().getWindow();
                stage1.close();
                q.notificaciones("Datos guardados Correctamente", "I");
            } else {
                q.notificaciones("Campo incompletos revice e intente nuevamente", "I");

            }
    }else {
          q.notificaciones("Seleccione un item de la lista proporcionada", "I");
          
      }

    }

    public void impresion() {
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t\tAIRNET ");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\tRUC: 1204112724001");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("Av. José Joaquín de Olmedo #220 y Colombia San Camilo - Quevedo - Ecuador");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t\t Comprobante de Salida de Efectivo");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("\t\t\tInformacion de Solicitante");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Solicitante: " + solicitante.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Nivel de Salida: " + motivos.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Detalle de uso: " + detallemotivo.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera(Ticket.DibujarLinea(35));
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("\t\t\t Informacion de Entrega");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Autorizado por: " + autorizadopor.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Entregado por: " + quienentrega.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Fecha de Entrega: " + q.Fechafacturas());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera(Ticket.DibujarLinea(35));
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("" + "Detalle\t");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddItem( detallemotivo.getText()+" : " + cantidad.getText());
        Ticket.AddPieLinea(Ticket.DarEspacio());    
        Ticket.AddPieLinea(Ticket.DibujarLinea(35));
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea("\t\t !RECEPTADO CONFORME!");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea("\t\t !Firma!");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DibujarLinea(35));
        Ticket.AddPieLinea(Ticket.DarEspacio());
         Ticket.AddPieLinea("\t\t !Firma!");
        Ticket.AddPieLinea(Ticket.DarEspacio());

        try {
            Ticket.ImprimirDocumento();
        } catch (PrintException ex) {
            Logger.getLogger(RegistrosalidaddedineroController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegistrosalidaddedineroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
