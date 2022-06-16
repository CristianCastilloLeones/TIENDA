/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTABLE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import INDICADORES.CLIENTES_BController;
import CLASES.ClaseArchivoBanco;
import javafxapplication4.ExcelExport;
import CLASES.ValidarIdentificacion;
import CLASES.bas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafxapplication4.VariablesDeUso;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class ArchivoBancoController implements Initializable {

    List list = new ArrayList();
    ObservableList<ClaseArchivoBanco> clt = FXCollections.observableArrayList();
    bas q = new bas();
    @FXML
    private TableView tikeprincipal;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> TIPO;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> CONTRAPARTIDA;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> MONEDA;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> VALOR;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> FORMADECOBRO;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> REFERENCIA;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> TIPOID;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> NUMEROID;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> NOMBREBENEFICIARIO;
    @FXML
    private Button buscar;
    @FXML
    private ImageView cargando;
    @FXML
    private Button imprimir;
    @FXML
    private TextField TOTALPORCOBRAR;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> B1;
    @FXML
    private TableColumn<String, ClaseArchivoBanco> B2;
int c=0;
    @FXML
    private RadioButton DeudaTotal;
    @FXML
    private RadioButton DeudadelPlan;
    @FXML
    private ComboBox mesagenerar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         mesagenerar.getItems().addAll("Enero",
                 "Febrero",
                 "Marzo",
                 "Abril",
                 "Mayo",
                 "Junio",
                 "Julio",
                 "Agosto",
                 "Septiembre",
                 "Octubre",
                 "Noviembre"
                ,"Diciembre");
        cargando.setVisible(false);
        buscar.setOnAction((event) -> {
            HilodeDatos();
        });

        // TODO
        valoresTabla();
        tikeprincipal.setPlaceholder(new Label("Presione en Buscar para cargar Datos...."));
        imprimir.setOnAction((event) -> {
            try {
                showReport();
            } catch (IOException ex) {
                Logger.getLogger(CLIENTES_BController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    public void showReport() throws FileNotFoundException, IOException {
        if (!tikeprincipal.getItems().isEmpty()) {
            ExcelExport valor = new ExcelExport();
            valor.export(tikeprincipal, "Archivo_Bancario");

        } else {
            q.notificaciones("Tabla vacia, no se puede realizar la Exportacion", "I");
        }

    }

    public void valoresTabla() {
        TIPO.setCellValueFactory(new PropertyValueFactory<>("TIPO"));
        CONTRAPARTIDA.setCellValueFactory(new PropertyValueFactory<>("CONTRAPARTIDA"));
        MONEDA.setCellValueFactory(new PropertyValueFactory<>("MONEDA"));
        VALOR.setCellValueFactory(new PropertyValueFactory<>("VALOR"));
        FORMADECOBRO.setCellValueFactory(new PropertyValueFactory<>("FORMADECOBRO"));
        B1.setCellValueFactory(new PropertyValueFactory<>("B1"));
        B2.setCellValueFactory(new PropertyValueFactory<>("B2"));
        REFERENCIA.setCellValueFactory(new PropertyValueFactory<>("REFERENCIA"));
        TIPOID.setCellValueFactory(new PropertyValueFactory<>("TIPOID"));
        NUMEROID.setCellValueFactory(new PropertyValueFactory<>("NUMEROID"));
        NOMBREBENEFICIARIO.setCellValueFactory(new PropertyValueFactory<>("NOMBREBENEFICIARIO"));
        tikeprincipal.getItems().clear();
    }
    VariablesDeUso variables = new VariablesDeUso();
    public void CargarInformacion() {
        clt.clear();
         c=0;
        ValidarIdentificacion ob = new ValidarIdentificacion();
       // String Valor = "";
       
        ResultSet valores = q.tablas("   SELECT TIPOIDENTIFICACION, [IDENTIFICACION] FROM [BDAirnet].[dbo].[VistaparaCorte] where detalle like '%"+mesagenerar.getSelectionModel().getSelectedItem()+"%' and  Estado=1  " + "and IDCLIENTE != 11775 and IDCLIENTE  != 11213  and IDCLIENTE  != 116  and IDCLIENTE  != 755  and IDCLIENTE  != 11260"
                + " and IDCLIENTE != 11213\n"
                + " and IDCLIENTE != 11175\n"
                + " and IDCLIENTE != 10859\n"
                + " and IDCLIENTE != 10653\n"
                + " and IDCLIENTE != 1572\n"
                + " and IDCLIENTE != 1561\n"
                + " and IDCLIENTE != 1498\n"
                + " and IDCLIENTE != 1451\n"
                + " and IDCLIENTE != 564\n"
                + " and IDCLIENTE != 563\n"
                + " and IDCLIENTE != 555\n"
                + " and IDCLIENTE != 369\n"
                + " and IDCLIENTE != 359\n"
                + " and IDCLIENTE != 296\n"
                + " and IDCLIENTE != 281\n"
                + " and IDCLIENTE != 137\n"
                + " and IDCLIENTE != 132\n"
                + " and IDCLIENTE != 128\n"
                + " and IDCLIENTE != 5\n"
                + " and IDCLIENTE != 1"
                + " group by [IDENTIFICACION] ,TIPOIDENTIFICACION");
        try {
            while (valores.next()) {
                q.auxConsulta = q.tablas("SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where  detalle like '%"+mesagenerar.getSelectionModel().getSelectedItem()+"%' and  Estado=1 and codigo='" + valores.getString("IDENTIFICACION") + "'");//recepciondePago.detalle();  
                variables.Encerar();
                while (q.auxConsulta.next()) {
                        variables.setValorTotal(q.ConvertidorStringaDouble(q.auxConsulta.getString("valortotal")));
                    }
                
                    if (!list.contains(valores.getString("IDENTIFICACION"))) {
                        if (variables.getValorTotal() > 0) {
                            list.add(valores.getString("IDENTIFICACION"));
                            switch (valores.getString("TIPOIDENTIFICACION")) {
                                case "Cédula":
                                    if (ob.validarCedula(valores.getString("IDENTIFICACION"))) {
                                        clt.add(new ClaseArchivoBanco("CO",
                                                valores.getString("IDENTIFICACION"),
                                                "USD", q.FormatoDecimal(variables.getValorTotal()).replace(".", "").replace(",", ""),
                                                "REC", "", "",
                                                "PAGO", "C", valores.getString("IDENTIFICACION"),
                                                q.BusquedaNombreporCedula(valores.getString("IDENTIFICACION"))));
                             }else {
                                        clt.add(new ClaseArchivoBanco("CO",
                                             valores.getString("IDENTIFICACION"),
                                             "USD", q.FormatoDecimal(variables.getValorTotal()).replace(".", "").replace(",", ""),
                                             "REC", "", "",
                                             "PAGO", "N", valores.getString("IDENTIFICACION"),
                                            q.BusquedaNombreporCedula(valores.getString("IDENTIFICACION"))));
                                    }

                                    break;
                                case "Pasaporte":
                                    if (q.VerificarCedula(valores.getString("IDENTIFICACION"))) {
                                        clt.add(new ClaseArchivoBanco("CO",
                                                valores.getString("IDENTIFICACION"),
                                                "USD", q.FormatoDecimal(variables.getValorTotal()).replace(".", "").replace(",", ""),
                                                "REC", "", "",
                                                "PAGO", "P", valores.getString("IDENTIFICACION"),
                                              q.BusquedaNombreporCedula(valores.getString("IDENTIFICACION"))));
                                    }else {
                                        clt.add(new ClaseArchivoBanco("CO",
                                             valores.getString("IDENTIFICACION"),
                                             "USD", q.FormatoDecimal(variables.getValorTotal()).replace(".", "").replace(",", ""),
                                             "REC", "", "",
                                             "PAGO", "N", valores.getString("IDENTIFICACION"),
                                            q.BusquedaNombreporCedula(valores.getString("IDENTIFICACION"))));
                                    }

                                    break;
                                case "RUC":
                                    if (ob.validarRucPersonaNatural(valores.getString("IDENTIFICACION")) && ob.validarRucSociedadPrivada(valores.getString("IDENTIFICACION"))) {
                                        clt.add(new ClaseArchivoBanco("CO",
                                                valores.getString("IDENTIFICACION"),
                                                "USD", q.FormatoDecimal(variables.getValorTotal()).replace(".", "").replace(",", ""),
                                                "REC", "", "",
                                                "PAGO", "R", valores.getString("IDENTIFICACION"),
                                                q.BusquedaNombreporCedula(valores.getString("IDENTIFICACION"))));
                                    }else {
                                        clt.add(new ClaseArchivoBanco("CO",
                                             valores.getString("IDENTIFICACION"),
                                             "USD", q.FormatoDecimal(variables.getValorTotal()).replace(".", "").replace(",", ""),
                                             "REC", "", "",
                                             "PAGO", "N", valores.getString("IDENTIFICACION"),
                                            q.BusquedaNombreporCedula(valores.getString("IDENTIFICACION"))));
                                    }

                                    break;
                                default:
                                            clt.add(new ClaseArchivoBanco("CO",
                                             valores.getString("IDENTIFICACION"),
                                             "USD", q.FormatoDecimal(variables.getValorTotal()).replace(".", "").replace(",", ""),
                                             "REC", "", "",
                                             "PAGO", "N", valores.getString("IDENTIFICACION"),
                                            q.BusquedaNombreporCedula(valores.getString("IDENTIFICACION"))));
                                    break;
                            }

                        }
                    
                    tikeprincipal.setItems(clt);
                    c++;
                }else {
                        System.out.println(valores.getString("IDENTIFICACION")+"\t"+variables.getValorTotal());
                    }
                        
                    
                // tikeprincipal.getItems().clear();

            }
        } catch (SQLException ex) {
            Logger.getLogger(CLIENTES_BController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ArchivoBancoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void HilodeDatos() {
        tikeprincipal.setPlaceholder(new Label("Estamos Cargando sus Datos...."));
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                cargando.setVisible(true);
                CargarInformacion();
                //updateMessage(String.valueOf(getProgress()));
                // aqui va todo el proceso a ejecutarse 
                return null;}
        };
        task.setOnSucceeded(event -> {
            // aqui va que hacer cuando ya acabe de ejecutarse el hilo
            cargando.setVisible(false);
            TOTALPORCOBRAR.setText(String.valueOf(c));
            if (tikeprincipal.getItems().isEmpty()) {tikeprincipal.setPlaceholder(new Label("No hay Valores en su Busqueda")); } });
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

}
