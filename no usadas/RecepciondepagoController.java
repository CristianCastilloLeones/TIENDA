/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import CLASES.bas;
import CLASES.ClasedePropiedades;
import CLASES.Ticket;
import CLASES.Clavedeacceso;
import CLASES.HiloRecepciondePago;
import CLASES.ProcesoVistaClientes;
import CLASES.Clase_RecepciondePago;
import CLASES.FacturasCobradas;
import CLASES.recepcionpago;
import SOPORTE.NUEVOTICKETController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.print.PrintException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class RecepciondepagoController implements Initializable {

    boolean facturaac = false;
    
    VariablesDeUso onVariable = new VariablesDeUso();
    Clase_RecepciondePago objetoinicial = new Clase_RecepciondePago();
    ClasedePropiedades propie = new ClasedePropiedades();
    String fpad = null, evi;
    // boolean facturaac = false;
    private Month mes = LocalDate.now().getMonth();
    bas q = new bas();
    @FXML
    private TextField numerofact;
    @FXML
    public TextField nombrecliente;
    @FXML
    public TextField fechagenera;
    @FXML
    private TextField fechapago;
    @FXML
    private TextField subto;
    @FXML
    private TextField iva;
    @FXML
    private TextField total;
    @FXML
    private Button guardar;
    @FXML
    private Pane eviden;
    @FXML
    private Button btonsubirevi;
    @FXML
    private ImageView imagenevide;
    @FXML
    private CheckBox subireviden;
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
    public TextField detalle1;
    @FXML
    private TableView tabladetalle;
    @FXML
    private TextField cantidad;
    @FXML
    private ImageView nuevalinea;
    @FXML
    private ImageView eliminarlinea;
    @FXML
    private TextField pvptext;
    @FXML
    private TextField valorreceptado;
    @FXML
    private Label vuelto;
    @FXML
    private TextField totalitem;
    String claveacceso;
    @FXML
    private TextField cedulatext;
    @FXML
    private CheckBox abonar;
    @FXML
    private TextField valorabono;
    @FXML
    private CheckBox items;
    @FXML
    private Label clavaccessri;
    @FXML
    private TableColumn<String, FacturasCobradas> Fechatrasaccion;
    @FXML
    private TableColumn<String, FacturasCobradas> numerofactura;
    @FXML
    private TableColumn<String, FacturasCobradas> detalleFacturaPagadas;
    @FXML
    private TableColumn<String, FacturasCobradas> totalfacturaspagadas;
    @FXML
    private TableColumn<String, FacturasCobradas> DEBE;
    @FXML
    private TableColumn<String, FacturasCobradas> SALDO;
    @FXML
    private TableView historialpago;
    @FXML
    private Button SalidaDinero;
    @FXML
    private Button PagoTorres;
    @FXML
    private Button Promesdepago;
    @FXML
    private Label texto;
    @FXML
    private ImageView imagendecarga;
    @FXML
    private CheckBox chechFacturartoelaño;
    @FXML
    private TextField numerodocumentodeposito;
    @FXML
    private DatePicker fechadepsoitodocumento;
    @FXML
    private TextArea detalleComentario;
    @FXML
    private TextField valordocumento;
    boolean verif;
    @FXML
    private Label Direccion;
    @FXML
    private ComboBox<String> idcuenta;
    @FXML
    private Button exportar;
    @FXML
    private Button imprimirreporte;
    @FXML
    private TextField ABONO;
     FileChooser fileChooser = new FileChooser();
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if( Clase_VistaCLientes.Factorbusqueda ==null){
            
             ValorReinicio();
        }else {
            nombrecliente.setText(Clase_VistaCLientes.Factorbusqueda);
            nombrecliente.textProperty().addListener((obs, oldText, newText) -> {
           objetoinicial.ValoresIniciales(q.BusquedaCedula(nombrecliente.getText()), nombrecliente.getText());
                    cedulatext.setText(Clase_RecepciondePago.getCedulaCliente());
                    valordebusqueda();
                    valorreceptado.requestFocus();
        });
        }
        
        
        
valordocumento.setOnKeyPressed((KeyEvent event) -> {

            if (event.getCode() == KeyCode.ENTER) {
                valorreceptado.requestFocus();
            }

        });
        
        valorreceptado.setOnKeyPressed((KeyEvent event) -> {

            if (event.getCode() == KeyCode.ENTER) {
                PARAMETROG();
            }

        });

        valorabono.setOnKeyPressed((KeyEvent event) -> {

            if (event.getCode() == KeyCode.ENTER) {
                PARAMETROG();
            }

        });
        imprimirreporte.setOnAction((event) -> {
            q.notificaciones("Archivo aun no elaborado", "I");
        });
        exportar.setOnAction((event) -> {
            try {
                showReport();
            } catch (IOException ex) {
                Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        q.prediccion(detalle1, onVariable.items());
        ValoresIiniciale();
        canti.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        detalle.setCellValueFactory(new PropertyValueFactory<>("Detalle"));
        pvp.setCellValueFactory(new PropertyValueFactory<>("pvp"));
        pvpt.setCellValueFactory(new PropertyValueFactory<>("pvpt"));
        idval.setCellValueFactory(new PropertyValueFactory<>("id"));
        guardar.setDisable(true);
       
        SalidaDinero.setOnAction((event) -> {
            q.ventanas("registrosalidaddedinero.fxml");
        });
        PagoTorres.setOnAction((event) -> {
            q.ventanas("torrespago.fxml");
        });
        Promesdepago.setOnAction((event) -> {
            q.ventanas("Promesadepago.fxml");
        });
        onVariable.getList().clear();
        q.listaclient(onVariable.getListadeClientes());
        q.prediccion(nombrecliente, onVariable.getListadeClientes());
        
        nombrecliente.requestFocus();
        nombrecliente.setOnKeyPressed((KeyEvent event) -> {
            if (!nombrecliente.getText().replace(" ", "").isEmpty()) {
                if (event.getCode() == KeyCode.ENTER) {
                    imagendecarga.setVisible(true);
                    objetoinicial.ValoresIniciales(q.BusquedaCedula(nombrecliente.getText()), nombrecliente.getText());
                    cedulatext.setText(Clase_RecepciondePago.getCedulaCliente());
                    valordebusqueda();
                    valorreceptado.requestFocus();
                }
            }
        });
        onVariable.getValorid().clear();
        //   valorid.clear();
        tabladetalle.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        cantidad.setDisable(true);
        detalle1.setDisable(true);
        items.setOnAction((event) -> {
            if (items.isSelected()) {
                cantidad.setDisable(false);
                detalle1.setDisable(false);
                pvptext.setDisable(false);
            } else {
                cantidad.setDisable(true);
                detalle1.setDisable(true);
                pvptext.setDisable(true);
            }

        });

        vuelto.setText("0");
        valorabono.setDisable(true);
        valorabono.setText("0");
        cedulatext.setText(Clase_RecepciondePago.getCedulaCliente());
        //nombrecliente.setText(Clase_RecepciondePago.getNombreabonado());
        numerodocumentodeposito.setTooltip(new Tooltip("Presione Enter para cotinuar"));
        numerodocumentodeposito.setOnKeyPressed((KeyEvent event) -> {

            if (event.getCode() == KeyCode.ENTER) {
                NombredelHilo(numerodocumentodeposito.getText());

            }

        });
        valorabono.textProperty().addListener((obs, oldText, newText) -> {
            valorreceptado.setText(newText);
            /* if (newText.isEmpty()) {
                guardar.setDisable(true);
            } else {
                guardar.setDisable(false);
                double vueltos = (Double.parseDouble(newText)) - Double.parseDouble(total.getText());
                vuelto.setText(q.FormatoDecimal(vueltos));
            }
             */
        });
        abonar.setOnAction((event) -> {
            if (abonar.isSelected()) {
                valorabono.setDisable(false);
                valorreceptado.setText("0");
                valorreceptado.setDisable(true);
            } else {
                valorreceptado.setDisable(false);
                valorabono.setDisable(true);
                valorabono.setText("0");

            }
        });

        pvptext.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.isEmpty()) {

                if (!cantidad.getText().isEmpty()) {
                    this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(newText) * q.ConvertidorStringaDouble(cantidad.getText())));
                } else {
                    cantidad.setText("1");
                    this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(newText) * q.ConvertidorStringaDouble(cantidad.getText())));
                }
            }
        });
        pvptext.setOnKeyPressed((KeyEvent event) -> {
            if (!pvptext.isDisable()) {
                if (event.getCode() == KeyCode.ENTER) {

                    if (!cantidad.getText().isEmpty()) {

                        this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(pvptext.getText()) * q.ConvertidorStringaDouble(cantidad.getText())));
                    } else {
                        cantidad.setText("1");

                        this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(pvptext.getText()) * q.ConvertidorStringaDouble(cantidad.getText())));
                    }
                }
            }
        });
        detalle1.setOnKeyPressed((KeyEvent event) -> {

            if (event.getCode() == KeyCode.ENTER) {
                if (onVariable.getList().contains(detalle1.getText())) {

                    nuevalinea.setDisable(false);
                    detalle1.requestFocus();
                    try {
                        onVariable.consulta = q.tablas("SELECT top(1) precio FROM [BDAirnet].[dbo].[tvproductosdj] where producto ='" + detalle1.getText() + "'");
                        while (onVariable.consulta.next()) {
                            if (onVariable.consulta.getString("precio").replace(" ", "").equals("0")) {
                                pvptext.setDisable(false);
                            } else {
                                pvptext.setText(onVariable.consulta.getString("precio").replace(" ", ""));
                            }

                        }
                    } catch (SQLException ex) {
                    }

                    if (!cantidad.getText().isEmpty()) {

                        this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(pvptext.getText()) * q.ConvertidorStringaDouble(cantidad.getText())));
                    } else {
                        cantidad.setText("1");

                        this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(pvptext.getText()) * q.ConvertidorStringaDouble(cantidad.getText())));
                    }
                } else {
                    //  nuevalinea.setDisable(true);
                    //  q.notificaciones("Debe seleccionar un Item de la lista predeterminada", "I");
                }
            }
        });

        nuevalinea.setOnMouseClicked((event) -> {
            if (!detalle1.getText().replace(" ", "").isEmpty() && !pvptext.getText().replace(" ", "").isEmpty() && !totalitem.getText().replace(" ", "").isEmpty() && !cantidad.getText().replace(" ", "").isEmpty()) {
                try {

                    if (!tabladetalle.getItems().isEmpty()) {
                        tabladetalle.getItems().clear();
                    }

                    guardaritem();
                } catch (SQLException ex) {
                    Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                q.notificaciones("Complete todos los campos antes de continuar", "I");
            }

        }
        );

        valorreceptado.textProperty().addListener((obs, oldText, newText) -> {
            //  valorRe= newText;

            if (newText.isEmpty()) {
                guardar.setDisable(true);
            } else {
                guardar.setDisable(false);
                double vueltos = (q.ConvertidorStringaDouble(newText)) - q.ConvertidorStringaDouble(total.getText());
                vuelto.setText(q.FormatoDecimal(vueltos));
            }

        });
        valordocumento.textProperty().addListener((obs, oldText, newText) -> {
            //  valorRe= newText;

            if (newText.isEmpty()) {
                guardar.setDisable(true);
            } else {
                guardar.setDisable(false);
                valorreceptado.setText(newText);
                double vueltos = (q.ConvertidorStringaDouble(newText)) - q.ConvertidorStringaDouble(total.getText());
                vuelto.setText(q.FormatoDecimal(vueltos));
            }

        });
        valorreceptado.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {

                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");
                }

            } catch (Exception ex) {
            }
        });
        valordocumento.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {
                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");
                }

            } catch (Exception ex) {
            }
        });
        valorabono.setOnKeyTyped((event) -> {
            try {
                char key = event.getCharacter().charAt(0);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {
                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9]", "");
                }

            } catch (Exception ex) {
            }
        });
        subireviden.setOnAction((event) -> {
            if (subireviden.isSelected()) {
                eviden.setDisable(false);
                numerodocumentodeposito.setDisable(false);
                fechadepsoitodocumento.setDisable(false);
                detalleComentario.setDisable(false);
                valordocumento.setDisable(false);
            } else {
                eviden.setDisable(true);
                numerodocumentodeposito.setDisable(true);
                fechadepsoitodocumento.setDisable(true);
                detalleComentario.setDisable(true);
                valordocumento.setDisable(true);
            }
        });
        btonsubirevi.setOnAction(event -> {
           
            fileChooser.setTitle("Buscar Imagen");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*")
            );
            onVariable.imgFile = fileChooser.showOpenDialog(null);
            if (onVariable.getImgFile() != null) {
                onVariable.setImage1(new Image("file:" + onVariable.getImgFile().getAbsolutePath()));
                imagenevide.setImage(onVariable.getImage1());
            }
        });
        guardar.setOnAction((event) -> {

            PARAMETROG();
        });

        tabladetalle.setOnMouseClicked((event) -> {
            onVariable.Encerar();
            onVariable.getValorid().clear();
            if (!tabladetalle.getSelectionModel().isEmpty()) {

                ObservableList<Integer> selectedIndices = tabladetalle.getSelectionModel().getSelectedIndices();
                for (Integer s : selectedIndices) {
                    onVariable.setValorTotal(q.ConvertidorStringaDouble(String.valueOf(pvpt.getCellObservableValue(s).getValue())));
                    onVariable.setValorListaId(String.valueOf(idval.getCellObservableValue(s).getValue()));
                }
                total.setText(q.FormatoDecimal(onVariable.getValorTotal()));
                subto.setText(q.FormatoDecimal(onVariable.getValorTotal() / 1.12));
                iva.setText(q.FormatoDecimal(((onVariable.getValorTotal() / 1.12) * 12) / 100));

                //**************************      
            }
        });
        eliminarlinea.setOnMouseClicked((MouseEvent event) -> {

            try {
                onVariable.getValorid().stream().map(s -> {
                    q.DeleteEliminar(" update  [BDAirnet].[dbo].[detallesfacxtura] set Estado=0,numerofactura='ANULADA',claveacceso='" + VariablesDeUso.nombreusuario.replace(" ", "") + "' where iddetalle='" + s + "'");
                    return s;
                }).forEachOrdered(_item -> {
                    tabladetalle.getItems().clear();
                });
                busqueda();
                q.notificaciones("Se elimino correctamente el item", "I");
            } catch (SQLException ex) {
                Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        );
        chechFacturartoelaño.setOnAction((event) -> {
            try {
                busqueda();
            } catch (SQLException ex) {
                Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        idcuenta.setOnAction((event) -> {
            BusquedadeDirecion();
        });
    }

    public void PARAMETROG() {
        if (valorreceptado.getText().isEmpty()) {
            q.notificaciones("VALOR RECEPTADO  ES INVALIDO", "I");
        } else {
            propie.CargarPropiedades();
            if (propie.properties.getProperty("Ambiente").contains("PRODUCCION")) {
                imagendecarga.setVisible(true);
                guardar.setDisable(true);
                //  if (abonar.isSelected()) {
                // q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjAbonos] ([numerofactura] ,[cedula],[fecha],[estado],[valor]) VALUES('" + numerofact.getText().replace(" ", "") + "','" + cedulatext.getText().replace(" ", "") + "' ," + "GETDATE()" + ",'" + "A" + "'," + valorabono.getText() + ")");
                // }
                guardar();
                if (!facturaac) {
                    q.UpdateModificar("UPDATE [BDAirnet].[dbo].[tdvjSeriesCaja] SET  [VALORACTUAL] ='" + numerofact.getText() + "'  where NOMBREUSUARIO='" + VariablesDeUso.nombreusuario + "'");
                }
            } else {
                q.notificaciones("Esta en modo Prueba no se Realizaron Cambios Notifique al Administrador del Sistema", "I");
            }

            ValorReinicio();

        }
    }

    public void valordebusqueda() {
        valores();
        cuentas();
        facturasCancelada();
        if (!facturaac) {
            propie.CargarPropiedades();
            Clavedeacceso clave = new Clavedeacceso();
            claveacceso = clave.GenerarClaveAcceso(q.FechaformatoCalendaerio(), "01", q.serieCaja(), "1204112724001", numerofact.getText(), "1", (propie.properties.getProperty("Ambiente")).contains("PRODUCCION") ? "2" : "1");
            clavaccessri.setText(claveacceso);
        } else {
            busquedadeDatos(numerofact.getText(), cedulatext.getText());
        }

    }

    public void cuentas() {
        idcuenta.getItems().clear();
        q.consulta = q.tablas("SELECT IDCLIENTE FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] WHERE ([NOMBRES]+' '+[APELLIDOS])like '%" + nombrecliente.getText().trim() + "%'");
        {
            try {
                while (q.consulta.next()) {
                    idcuenta.getItems().addAll(q.consulta.getString("IDCLIENTE"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(NUEVOTICKETController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        idcuenta.getSelectionModel().selectFirst();
        BusquedadeDirecion();
    }

    public void BusquedadeDirecion() {
        q.consulta = q.tablas("SELECT DIRECCION FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] WHERE  IDCLIENTE=" + idcuenta.getSelectionModel().getSelectedItem());
        {
            try {
                while (q.consulta.next()) {
                    Direccion.setText(q.consulta.getString("DIRECCION"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(NUEVOTICKETController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void busqueda() throws SQLException {

        onVariable.Encerar();
        objetoinicial.getClt().clear();
        tabladetalle.setItems(objetoinicial.getClt());

        tabladetalle.getItems().clear();
        String valor = (chechFacturartoelaño.isSelected()) ? "T" : "E";
        HiloRecepciondePago task = new HiloRecepciondePago(cedulatext.getText(), valor);
        task.setOnSucceeded((succeededEvent) -> {
            imagendecarga.setVisible(false);
            onVariable.consulta = (ResultSet) task.getValue();
            try {
                while (onVariable.consulta.next()) {
                    if (onVariable.consulta.getString("numerofactura") == null) {
                        facturaac = false;
                       
                    } else {
                        facturaac = true;
                        q.notificaciones("Esta es una factura activa", "I");
                        numerofact.setText(onVariable.consulta.getString("numerofactura").trim());
                    }
                    objetoinicial.setClt(new recepcionpago(onVariable.consulta.getString("cantidad"), onVariable.consulta.getString("detalle"), onVariable.consulta.getString("valorunitario").replace(",", "."), onVariable.consulta.getString("valortotal").replace(",", "."), onVariable.consulta.getString("iddetalle")));
                    q.auxConsulta = q.tablas("SELECT  [idabono],[valor] FROM [BDAirnet].[dbo].[tvdjAbonos] WHERE [cedula] ='" + cedulatext.getText() + "' AND [estado] ='A'");
                    while (q.auxConsulta.next()) {
                        idabono = q.auxConsulta.getString("idabono");
                        ABONO.setText(q.auxConsulta.getString("valor").length() > 0 ? q.auxConsulta.getString("valor") : "0");
                    }
                    ABONO.setText(ABONO.getText().isEmpty() ? "0" : ABONO.getText());
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (int i = 0; i < tabladetalle.getItems().size(); i++) {
                onVariable.setValorTotal(q.ConvertidorStringaDouble(String.valueOf(pvpt.getCellObservableValue(i).getValue())));
            }

            total.setText(q.FormatoDecimal(onVariable.getValorTotal() - q.ConvertidorStringaDouble(ABONO.getText())));
            subto.setText(q.FormatoDecimal((onVariable.getValorTotal() - q.ConvertidorStringaDouble(ABONO.getText())) / 1.12));
            iva.setText(q.FormatoDecimal(((onVariable.getValorTotal() - q.ConvertidorStringaDouble(ABONO.getText())) / 1.12) / 100));

        });
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(task);

    }
    String idabono;

    public void valores() {
        // llenado de productos como items
         numerofact.setText(q.facturamaxima());
        fechagenera.setText(q.FechaformatoCalendaerio());
        fechapago.setText(q.FechaformatoCalendaerio());

        try {
            busqueda();
        } catch (SQLException ex) {
            Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ValorReinicio() {
        facturaac = false;
        nombrecliente.setText("");
        cedulatext.setText("");
       // numerofact.setText(q.facturamaxima());
        fechagenera.setText(q.FechaformatoCalendaerio());
        fechapago.setText(q.FechaformatoCalendaerio());
        tabladetalle.getItems().clear();
        historialpago.getItems().clear();
        //  valorabono.setText("0");
        // valorreceptado.setText("0");
        total.setText("0");
        subto.setText("0");
        iva.setText("0");
        vuelto.setText("0");
        abonar.setSelected(false);
        subireviden.setSelected(false);
        valorreceptado.setText("");
        imagendecarga.setVisible(false);
        items.setSelected(false);
        abonar.setSelected(false);
        subireviden.setSelected(false);
        imagenevide.setImage(null);
        chechFacturartoelaño.setSelected(false);

        numerodocumentodeposito.setText("");
        fechadepsoitodocumento.setValue(q.FechaActual());
        detalleComentario.setText("");
        valordocumento.setText("");
        idcuenta.getItems().clear();
        Direccion.setText("");
        nombrecliente.requestFocus();
        ABONO.setText("");
    }

    public void guardar() {

        if (subireviden.isSelected()) {
            if (!numerodocumentodeposito.getText().trim().isEmpty() && !fechadepsoitodocumento.getValue().toString().isEmpty() && !valordocumento.getText().trim().isEmpty()) {
                AuxGuardar();
            } else {
                q.notificaciones("Complete Todos los Campos para continuar", "I");
            }
        } else {
            AuxGuardar();
        }
        if (!ABONO.getText().isEmpty()) {
            q.UpdateModificar("update [BDAirnet].[dbo].[tvdjAbonos]  set estado='E' where [idabono]=" + idabono);
        }
    }

    public void AuxGuardar() {
        try {
            Actualizar_Items();
            Thread.sleep(5);
            insertar_Factura_Completa();
            Thread.sleep(5);
            if (abonar.isSelected()) {
                q.notificaciones("Retire el comprobante ", "I");
                impresiondeabono(numerofact.getText());
                impresiondeabono(numerofact.getText());
            } else {
                if (!subireviden.isSelected()) {
                    q.notificaciones("Retire el comprobante ", "I");
                    impresion(numerofact.getText());
                    impresion(numerofact.getText());
                }
            }

            if (!facturaac) {
                Thread.sleep(5);
                generar(numerofact.getText());
            }

        } catch (SQLException | TransformerException | ParserConfigurationException | IOException | InterruptedException ex) {
            q.notificaciones(ex.getMessage(), "I");
        }
    }

    public void Actualizar_Items() {
        if (onVariable.getValorid().isEmpty()) {
           
            porvalor();
        } else {
            if (abonar.isSelected()) {
                String nuevovalor = q.FormatoDecimal(q.ConvertidorStringaDouble(total.getText()) - q.ConvertidorStringaDouble(valorabono.getText()));

                if (q.ConvertidorStringaDouble(nuevovalor) > 0) {
                    NiveldeCorte(true);
                    onVariable.getValorid().forEach((idclientedetalle) -> {
                        q.UpdateModificar("UPDATE  [BDAirnet].[dbo].[detallesfacxtura]  SET numerofactura='" + numerofact.getText() + "',valortotal='" + valorabono.getText() + "',valorunitario='" + valorabono.getText() + "' ,Estado=0 where iddetalle=" + idclientedetalle);
                    });
                    // tabladetalle.getSelectionModel().getSelectedIndex();
                    q.Facturar(nombrecliente.getText(), nuevovalor, String.valueOf(detalle.getCellObservableValue(tabladetalle.getSelectionModel().getSelectedIndex()).getValue()), 1, cedulatext.getText(), "1", "E");
                } else {
                    NiveldeCorte(false);
                    onVariable.getValorid().forEach((idclientedetalle) -> {
                        q.UpdateModificar("UPDATE  [BDAirnet].[dbo].[detallesfacxtura]  SET Estado=0 , numerofactura='" + numerofact.getText() + "' where iddetalle=" + idclientedetalle);
                    });
                }
            } else {
                String nuevovalor = q.FormatoDecimal(q.ConvertidorStringaDouble(total.getText()) - q.ConvertidorStringaDouble(valorreceptado.getText()));

                if (q.ConvertidorStringaDouble(nuevovalor) > 0) {
                    NiveldeCorte(true);
                    onVariable.getValorid().forEach((idclientedetalle) -> {
                        q.UpdateModificar("UPDATE  [BDAirnet].[dbo].[detallesfacxtura]  SET numerofactura='" + numerofact.getText() + "',valortotal='" + valorreceptado.getText() + "',valorunitario='" + valorreceptado.getText() + "' ,Estado=0 where iddetalle=" + idclientedetalle);
                    });
                    //    tabladetalle.getSelectionModel().getSelectedIndex();
                    q.Facturar(nombrecliente.getText(), nuevovalor, String.valueOf(detalle.getCellObservableValue(tabladetalle.getSelectionModel().getSelectedIndex()).getValue()), 1, cedulatext.getText(), "1", "E");
                } else {
                    NiveldeCorte(false);
                    onVariable.getValorid().forEach((idclientedetalle) -> {
                        q.UpdateModificar("UPDATE  [BDAirnet].[dbo].[detallesfacxtura]  SET Estado=0 , numerofactura='" + numerofact.getText() + "' where iddetalle=" + idclientedetalle);
                    });
                }
            }
        }
    }

    public void generar(String numerofacuta) {
        Clase_RecepciondePago recepciondePago = new Clase_RecepciondePago(nombrecliente.getText(), numerofacuta,
                 cedulatext.getText(),
                 valorreceptado.getText(),
                 q.FormatoDecimal(q.ConvertidorStringaDouble(valorreceptado.getText()) / 1.12),
                 q.FormatoDecimal((q.ConvertidorStringaDouble(valorreceptado.getText()) / 1.12) / 100),
                 fechagenera.getText().replace(" ", ""), claveacceso, q.serieCaja().substring(3), abonar.isSelected());
        recepciondePago.Generar();
    }

    public void insertar_Factura_Completa() throws IOException, ParserConfigurationException, TransformerException, TransformerConfigurationException, SQLException {
        q.bas1();

        if (subireviden.isSelected()) {

            fpad = "Otros";
            if (onVariable.getImgFile() != null) {
                onVariable.setOrigen(new File(onVariable.getImgFile().getAbsolutePath()));
                q.CargarPropiedades();
                onVariable.setDestino(new File(q.properties.get("RutaArchivos") + "\\evidencia\\" + mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase() + onVariable.getImgFile().getName()));
                Files.copy(Paths.get(onVariable.getOrigen().getAbsolutePath()), Paths.get(onVariable.getDestino().getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            }
            evi = String.valueOf(onVariable.getDestino());
            ObservableList<Integer> selectedIndices = tabladetalle.getSelectionModel().getSelectedIndices();
            selectedIndices.forEach(s -> {
                onVariable.setDetalledepsosito(String.valueOf(detalle.getCellObservableValue(s).getValue()) + " ");
            });
            onVariable.coment = (detalleComentario.getText().trim().isEmpty()) ? "No hay Comentarios" : detalleComentario.getText();

            q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjDepositos]\n"
                    + "           ([NUMEROFACTURA],[CEDULACLIENTE]\n"
                    + "           ,[NUMERODECOMPROBANTE],[FECHADEPAGO]\n"
                    + "           ,[FECHADEDOCUMENTO] ,[VALORDEPOSITODOCUMENTO]\n"
                    + "           ,[VALORCOBRADO],[DETALLEDEFACTURA]\n"
                    + "           ,[COMENTARIO],[EVIDENCIADERESPALDO],[GUARDADOPOR])\n"
                    + "     VALUES\n"
                    + "           ('" + numerofact.getText() + "','" + cedulatext.getText() + "'\n"
                    + "           ,'" + numerodocumentodeposito.getText() + "' ,'" + fechapago.getText() + "'\n"
                    + "           ,'" + fechadepsoitodocumento.getValue().toString() + "'," + valordocumento.getText() + "\n"
                    + "           ," + valorreceptado.getText() + ",'" + onVariable.getDetalledepsosito() + "\n"
                    + "           ','" + onVariable.coment + "','" + evi + "','" + VariablesDeUso.nombreusuario + "')");

        } else {
            fpad = "Efectivo";
            evi = "";
        }
        if (facturaac) {

            q.UpdateModificar("update [BDAirnet].[dbo].[tvdjFacurascliente] \n"
                    + "set\n"
                    + "formapago='" + fpad + "'"
                    + ",fechadepago='" + fechapago.getText() + "'"
                    + ",evidencia='" + evi + "'"
                    + ",estado='INACTIVA', Facturadopor='" + VariablesDeUso.nombreusuario + "',Pagado= getDate()"
                    + "where nuemrofactura='" + numerofact.getText() + "' and  codigocontrato='" + cedulatext.getText() + "'");
        } else {
            if (abonar.isSelected()) {
                generar(numerofact.getText());
                q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjFacurascliente]\n"
                        + "([nuemrofactura],[cliente],[clave] ,[fechagenerada],[fechadepago],[formapago],[estado],[evidencia],[codigocontrato]\n"
                        + ",[SRI] ,[Envio]  ,[TipoFactu],[total])\n"
                        + "VALUES\n"
                        + "('" + numerofact.getText() + "','" + nombrecliente.getText() + "','" + claveacceso + "','" + fechagenera.getText() + "','" + fechapago.getText() + "','" + fpad + "','" + "INACTIVA" + "','" + evi + "','" + cedulatext.getText() + "','" + "NO" + "','" + "NO" + "','" + "NO" + "'," + valorabono.getText() + ")");
            } else {
                generar(numerofact.getText());
                q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjFacurascliente]\n"
                        + "([nuemrofactura],[cliente],[clave] ,[fechagenerada],[fechadepago],[formapago],[estado],[evidencia],[codigocontrato]\n"
                        + ",[SRI] ,[Envio]  ,[TipoFactu])\n"
                        + "VALUES\n"
                        + "('" + numerofact.getText() + "','" + nombrecliente.getText() + "','" + claveacceso + "','" + fechagenera.getText() + "','" + fechapago.getText() + "','" + fpad + "','" + "INACTIVA" + "','" + evi + "','" + cedulatext.getText() + "','" + "NO" + "','" + "NO" + "','" + "NO" + "')");
            }
        }
    }

    public void impresion(String numerofacuta) throws IOException {
        Clase_RecepciondePago obj = new Clase_RecepciondePago(cedulatext.getText().replace(" ", ""), numerofact.getText().replace(" ", ""));
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t\tAIRNET");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\tRUC: 1204112724001");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("Av. José Joaquín de Olmedo #220 y Colombia San Camilo - Quevedo - Ecuador");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t\tFACTURA");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t" + q.serieCaja() + numerofact.getText());
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("CLAVE: " + claveacceso);
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("\t\tInformacion del Cliente");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Cedula/Ruc" + cedulatext.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Cliente:" + nombrecliente.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Facturado por: " + VariablesDeUso.nombreusuario.replace(" ", "").substring(0, 10));
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("" + q.Fechafacturas());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Tipo de pago:Efectivo");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera(Ticket.DibujarLinea(35));
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("" + "Detalle\t\t|CANT|P.Unit|Importe|");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddItem(obj.DetallePagado());
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("", Ticket.DibujarLinea(35));
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("SUBTOTAL", "\t\t" + q.FormatoDecimal(obj.getT() / 1.12));
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("IVA", "\t\t" + q.FormatoDecimal((obj.getT() / 1.12) * 0.12));
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("TOTAL", "\t\t" + q.FormatoDecimal(obj.getT()));
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("RECIBIDO:", "\t\t" + valorreceptado.getText());
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("CAMBIO", "\t\t" + q.FormatoDecimal(q.ConvertidorStringaDouble(valorreceptado.getText()) - obj.getT()));
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("DEUDA PENDIENTE:", "\t" + obj.Valordeudante());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea("\t\tAgente de Retencion");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea("\t\tNro. NAC-DNCRASC20-00000001");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea("\t\t!Gracias por Preferirnos!");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());

        try {
            Ticket.ImprimirDocumento();
        } catch (PrintException ex) {
            Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void guardaritem() throws SQLException {
       q.Facturar(nombrecliente.getText(), pvptext.getText(), detalle1.getText().trim(), 1, cedulatext.getText().trim(), cantidad.getText().trim(), "E");
        detalle1.setText("");
        cantidad.setText("");
        pvptext.setText("");
        totalitem.setText("");
        detalle1.setDisable(true);
        cantidad.setDisable(true);
        pvptext.setDisable(true);
        items.setSelected(false);
        busqueda();

    }

    public void impresiondeabono(String numerofacuta) throws IOException {
        Clase_RecepciondePago obj = new Clase_RecepciondePago(cedulatext.getText().trim(), numerofact.getText().trim());
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t\tAIRNET ");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\tRUC: 1204112724001");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("Av. José Joaquín de Olmedo #220 y Colombia San Camilo - Quevedo - Ecuador");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t\t Comprobante de Abono");
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddCabecera("\t\t" + q.serieCaja() + numerofact.getText());
        Ticket.AddCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("\t\tInformacion del Cliente");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Cedula/Ruc" + cedulatext.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Cliente:" + nombrecliente.getText());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Generado por: " + VariablesDeUso.nombreusuario.trim().substring(0, 10));
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("" + q.Fechafacturas());
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("Tipo de Abono: Efectivo");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera(Ticket.DibujarLinea(35));
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddSubCabecera("" + "Detalle\t\t|CANT|P.Unit|Importe|");
        Ticket.AddSubCabecera(Ticket.DarEspacio());
        Ticket.AddItem("Abono a deuda :" + valorabono.getText());
        Ticket.AddTotal("", Ticket.DarEspacio());
        Ticket.AddTotal("DEUDA PENDIENTE:", "\t" + obj.Valordeudante());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea("\t\tAgente de Retencion");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea("\t\tNro. NAC-DNCRASC20-00000001");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea("\t\t!Gracias por Preferirnos!");
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        Ticket.AddPieLinea(Ticket.DarEspacio());
        try {
            Ticket.ImprimirDocumento();
        } catch (PrintException ex) {
            Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void busquedadeDatos(String numerfac, String cedula) {
        onVariable.consulta = q.tablas("SELECT top(1) clave  FROM [BDAirnet].[dbo].[tvdjFacurascliente]where nuemrofactura='" + numerfac + "' and codigocontrato='" + cedula + "'");
        try {
            while (onVariable.consulta.next()) {
                claveacceso = onVariable.consulta.getString("clave").trim();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void facturasCancelada() {
        historialpago.getItems().clear();
        ProcesoVistaClientes task = new ProcesoVistaClientes("2", nombrecliente.getText());
        task.setOnRunning((succeesesEvent) -> {
        });
        task.setOnSucceeded((succeededEvent) -> {
            historialpago.setItems((ObservableList) task.getValue());

        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
    }

    public void ValoresIiniciale() {
        Fechatrasaccion.setCellValueFactory(new PropertyValueFactory<>("FechadeRegistro"));
        numerofactura.setCellValueFactory(new PropertyValueFactory<>("Clientecobrado"));
        detalleFacturaPagadas.setCellValueFactory(new PropertyValueFactory<>("fechacobrada"));
        totalfacturaspagadas.setCellValueFactory(new PropertyValueFactory<>("Valorcobrado"));
        DEBE.setCellValueFactory(new PropertyValueFactory<>("ValorDeuda"));
        SALDO.setCellValueFactory(new PropertyValueFactory<>("Saldo"));
    }

    public synchronized void NombredelHilo(String numerodeposito) {

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {

                // aqui va todo el proceso a ejecutarse 
                verif = q.BuscarDepositoRepetido(numerodeposito);

                return null;
            }
        };
        task.setOnSucceeded(event -> {
            // aqui va que hacer cuando ya acabe de ejecutarse el hilo
            if (!verif) {
                q.notificaciones("El comprobante ya fue registrado", "I");
            } else {
                valordocumento.requestFocus();
            }

        });
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    public void NiveldeCorte(boolean valor) {
        if (valor) {
            q.ConseguiriACTIVARPORID(idcuenta.getSelectionModel().getSelectedItem());
        } else {
            q.Conseguiri(cedulatext.getText().trim());
        }

    }

    public void showReport() throws FileNotFoundException, IOException {
        if (!historialpago.getItems().isEmpty()) {
            ExcelExport valor = new ExcelExport();
            valor.export(historialpago, "Reporte de " + nombrecliente.getText());

        } else {
            q.notificaciones("Tabla vacia, no se puede realizar la Exportacion", "I");
        }

    }

    public void porvalor() {
        if (!valorreceptado.getText().isEmpty()) {
            double aux = q.ConvertidorStringaDouble(valorreceptado.getText());
            for (int i = 0; i < tabladetalle.getItems().size(); i++) {

                System.out.println(aux);
                if (aux >= q.ConvertidorStringaDouble(String.valueOf(pvpt.getCellObservableValue(i).getValue()))) {
                    q.DeleteEliminar(" update  [BDAirnet].[dbo].[detallesfacxtura] set Estado=0,numerofactura='" + numerofact.getText() + "'"
                            + ",claveacceso='" + VariablesDeUso.nombreusuario.trim()
                            + "' where iddetalle=" + String.valueOf(idval.getCellObservableValue(i).getValue()));
                    aux = aux - q.ConvertidorStringaDouble(String.valueOf(pvpt.getCellObservableValue(i).getValue()));
                } else if (aux > 0 || String.valueOf(aux).contains("-")) {

                    q.UpdateModificar("UPDATE  [BDAirnet].[dbo].[detallesfacxtura]  SET numerofactura='"
                            + "" + numerofact.getText() + ""
                            + "',valortotal='"
                            + aux
                            + "',valorunitario='"
                            + aux
                            + "' ,Estado=0 " + " where  iddetalle=" + String.valueOf(idval.getCellObservableValue(i).getValue()));
                    aux = q.ConvertidorStringaDouble(String.valueOf(pvpt.getCellObservableValue(i).getValue())) - aux;
                    q.Facturar(nombrecliente.getText(),
                             q.FormatoDecimal(aux),
                             String.valueOf(detalle.getCellObservableValue(i).getValue()),
                             1, cedulatext.getText(), "1", "E");
                    aux = 0;
                }

            }

            if (aux > 0) {
                q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjAbonos] ([numerofactura] ,[cedula],[fecha],[estado],[valor]) VALUES('" + numerofact.getText().replace(" ", "") + "','" + cedulatext.getText().replace(" ", "") + "' ," + "GETDATE()" + ",'" + "A" + "'," + aux + ")");
 }
        } else {
            q.notificaciones("Debe Ingresar un valor Correcto o Valido para continuar", "I");
        }

    }

}
