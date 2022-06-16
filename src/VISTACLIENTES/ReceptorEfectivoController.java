/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTACLIENTES;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafxapplication4.Clase_VistaCLientes;
import CLASES.Ticket;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;
import CLASES.recepcionpago;
import javax.print.PrintException;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class ReceptorEfectivoController implements Initializable {

    String DetalleImpresion = "";
    bas q = new bas();
    ObservableList<recepcionpago> clt = FXCollections.observableArrayList();
    public static String cedulacliente = "";
    @FXML
    private TextField numerofact;
    @FXML
    private TextField nombrecliente;
    @FXML
    private TextField fechagenera;
    @FXML
    private TextField total;
    @FXML
    private TableView tabladetalle;
    @FXML
    private TableColumn<String, recepcionpago> canti;
    @FXML
    private TableColumn<String, recepcionpago> detalle;
    @FXML
    private TableColumn<String, recepcionpago> pvp;
    @FXML
    private TableColumn<String, recepcionpago> pvpt;
    @FXML
    private TableColumn<String, recepcionpago> idval;
    @FXML
    private TextField cedulatext;
    @FXML
    private Button AUTORIZARYENVIAR;
    @FXML
    private Label clavaccessri;
    VariablesDeUso onVariable = new VariablesDeUso();
    @FXML
    private Label totalselescionado;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numerofact.setText("0000" + (q.NumerRecibo()));
        AUTORIZARYENVIAR.setOnAction((event) -> {
            try {
                impresion();
                impresion();
                if (!tabladetalle.getSelectionModel().isEmpty()) {
                    ObservableList<Integer> selectedIndices = tabladetalle.getSelectionModel().getSelectedIndices();
                    for (Integer s : selectedIndices) {
                        q.InserDatosrecivo(Integer.parseInt(String.valueOf(idval.getCellObservableValue(s).getValue())),
                                 DetalleImpresion, Integer.parseInt(numerofact.getText()), Float.parseFloat(total.getText()),
                                 nombrecliente.getText(), VariablesDeUso.nombreusuario);
                    }
                    Node source = (Node) event.getSource();
                    Stage stage1 = (Stage) source.getScene().getWindow();
                    stage1.close();

                    //**************************      
                }

            } catch (IOException ex) {
                Logger.getLogger(ReceptorEfectivoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        try {
            // TODO
            tabladetalle.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            fechagenera.setText(q.FechaformatoCalendaerio());
            nombrecliente.setText(Clase_VistaCLientes.Factorbusqueda);
            cedulatext.setText(cedulacliente);
            busqueda();
        } catch (SQLException ex) {
            Logger.getLogger(ReceptorEfectivoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabladetalle.setOnMouseClicked((event) -> {
            onVariable.Encerar();
            onVariable.getValorid().clear();
            if (!tabladetalle.getSelectionModel().isEmpty()) {

                ObservableList<Integer> selectedIndices = tabladetalle.getSelectionModel().getSelectedIndices();
                for (Integer s : selectedIndices) {
                    onVariable.setValorTotal(Double.parseDouble(String.valueOf(pvpt.getCellObservableValue(s).getValue())));
                    onVariable.setValorListaId(String.valueOf(idval.getCellObservableValue(s).getValue()));
                }
                totalselescionado.setText(q.FormatoDecimal(onVariable.getValorTotal()));

                //**************************      
            }
        });
    }

    public void busqueda() throws SQLException {
        onVariable.Encerar();
        tabladetalle.setItems(clt);
        canti.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        detalle.setCellValueFactory(new PropertyValueFactory<>("Detalle"));
        pvp.setCellValueFactory(new PropertyValueFactory<>("pvp"));
        pvpt.setCellValueFactory(new PropertyValueFactory<>("pvpt"));
        idval.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabladetalle.getItems().clear();
        q.auxConsulta = q.tablas("SELECT * FROM [BDAirnet].[dbo].[detallesfacxtura] where Estado='1' and codigo='" + cedulatext.getText() + "' and numerofactura is null");
        while (q.auxConsulta.next()) {
            clt.add(new recepcionpago(q.auxConsulta.getString("cantidad").trim(),
                    q.auxConsulta.getString("detalle"),
                    q.auxConsulta.getString("valorunitario").trim(),
                    q.auxConsulta.getString("valortotal").trim(),
                    q.auxConsulta.getString("iddetalle")));
        }

        for (int i = 0; i < tabladetalle.getItems().size(); i++) {
            onVariable.setValorTotal(q.ConvertidorStringaDouble(String.valueOf(pvpt.getCellObservableValue(i).getValue())));
        }
        totalselescionado.setText(q.FormatoDecimal(onVariable.getValorTotal()));

    }

    public void impresion() throws IOException {
        DetalleImpresion = "";
        if (!tabladetalle.getSelectionModel().isEmpty()) {

            ObservableList<Integer> selectedIndices = tabladetalle.getSelectionModel().getSelectedIndices();
            for (Integer s : selectedIndices) {
                DetalleImpresion = (String.valueOf(detalle.getCellObservableValue(s).getValue()).trim()
                        + "\t\t " + String.valueOf(canti.getCellObservableValue(s).getValue()).trim()
                        + "  " + String.valueOf(pvp.getCellObservableValue(s).getValue()).trim()
                        + "  " + String.valueOf(pvpt.getCellObservableValue(s).getValue()).trim() + "\n");

            }
            totalselescionado.setText(q.FormatoDecimal(onVariable.getValorTotal()));

            //**************************      
        }
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t\tAIRNET ");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\tRUC: 1204112724001");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("Av. José Joaquín de Olmedo #220 y Colombia San Camilo \n \t Quevedo - Ecuador");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t\tRECIBO DE PAGO");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t" + q.serieCaja() + numerofact.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("\t\tInformacion del Cliente");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Cedula/Ruc" + cedulatext.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Cliente:" + nombrecliente.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Facturado por: " + VariablesDeUso.nombreusuario.trim().substring(0, 10));
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("" + q.Fechafacturas());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Tipo de pago:Efectivo");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera(Ticket.DibujarLinea(35));
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("" + "Detalle\t\t|CANT|P.Unit|Importe|");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddItem(DetalleImpresion);
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("", Ticket.DibujarLinea(35));
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("TOTAL", "\t\t" + totalselescionado.getText());
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("RECIBIDO:", "\t\t" + q.FormatoDecimal(q.ConvertidorStringaDouble(total.getText())));
       Ticket.AddTotal("",Ticket.DarEspacio());
        Ticket.AddTotal("",Ticket.DarEspacio());
         Ticket.AddTotal("Saldo:", "\t\t" + q.FormatoDecimal(q.ConvertidorStringaDouble(totalselescionado.getText())-q.ConvertidorStringaDouble(total.getText())));
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DibujarLinea(35));
        Ticket.AddPieLinea("\t\t\t Entregue Conforme");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DibujarLinea(35));
        Ticket.AddPieLinea("\t\t\t Recibi Conforme");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea("\t\t!Gracias por Preferirnos!");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());

        try {
            Ticket.ImprimirDocumento();
        } catch (PrintException ex) {
            Logger.getLogger(ReceptorEfectivoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
