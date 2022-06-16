/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAJA;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import CLASES.Clavedeacceso;
import javafxapplication4.VariablesDeUso;
import CLASES.XML;
import CLASES.bas;
import CLASES.recepcionpago;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class Generador_de_FacturasController implements Initializable {
    
    VariablesDeUso onVariable = new VariablesDeUso();
    ObservableList<recepcionpago> clt = FXCollections.observableArrayList();
    bas q = new bas();
    //List valorid = new ArrayList();
    String claveacceso;
    @FXML
    private TextField numerofact;
    @FXML
    private TextField nombrecliente;

    @FXML
    private TextField fechagenera;
    @FXML
    private TextField fechapago;
    @FXML
    private TextField subto;
    @FXML
    private TextField iva;
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
    private Button aunlarrece;

    @FXML
    private TextField cedulatext;

    @FXML
    private Button AUTORIZARYENVIAR;
    @FXML
    private Label clavaccessri;
    String email = "";

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        q.listaclient(onVariable.getListadeClientes());
        q.prediccion(nombrecliente, onVariable.getListadeClientes());
        ValorReinicio();
        aunlarrece.setOnMouseClicked((event) -> {
            CREARALERTAS();
        }
        );
        nombrecliente.setOnKeyPressed((KeyEvent event) -> {
            if (!nombrecliente.getText().replace(" ", "").isEmpty()) {
                if (event.getCode() == KeyCode.ENTER) {

                    cedulatext.setText(q.BusquedaCedula(nombrecliente.getText()));
                    valores();

                }
            }
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

        onVariable.getList().clear();
        tabladetalle.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        AUTORIZARYENVIAR.setOnAction((event) -> {
            try {

                sologenerar(numerofact.getText().replace(" ", ""));
                q.UpdateModificar("UPDATE [BDAirnet].[dbo].[tdvjSeriesCaja] SET  [VALORACTUAL] ='" + numerofact.getText() + "'  where NOMBREUSUARIO='" + VariablesDeUso.nombreusuario + "'");
                q.notificaciones("Factura generada con Exito", "I");
                ValorReinicio();
            } catch (SQLException | ParserConfigurationException | TransformerException ex) {
                // Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void sologenerar(String numerofacuta) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SQLException {

        List<XML> empList = new ArrayList<>();
        double t = 0;

        String direccion = "";
        String tipoidenficacion = "", codicotipoidentificacion = "";
        ResultSet cliente = q.tablas("SELECT  NOMBRES,APELLIDOS,DIRECCION,IDENTIFICACION,TIPOIDENTIFICACION,EMAIL FROM [BDAirnet].[dbo].[TBCLIENTE] where  IDENTIFICACION='" + cedulatext.getText().replace(" ", "") + "'");
        while (cliente.next()) {
            if (cliente.getString("DIRECCION").replace(" ", "").isEmpty()) {
                direccion = "Los Rios Ecuador";
            } else {
                direccion = cliente.getString("DIRECCION");
            }

            tipoidenficacion = cliente.getString("TIPOIDENTIFICACION");
            email = cliente.getString("EMAIL");
        }

        ObservableList<Integer> selectedIndices = tabladetalle.getSelectionModel().getSelectedIndices();

        for (Integer s : selectedIndices) {
            empList.add(new XML(String.valueOf(detalle.getCellObservableValue(s).getValue()), String.valueOf(pvp.getCellObservableValue(s).getValue()).replace(" ", ""), String.valueOf(pvpt.getCellObservableValue(s).getValue()), String.valueOf(canti.getCellObservableValue(s).getValue()).replace(" ", "")));
            t = t + q.ConvertidorStringaDouble(String.valueOf(pvpt.getCellObservableValue(s).getValue()));
        }
        //    datosclienteride = fechagenera.getText().replace(" ", "").replace("-", "/") + "-" + nombrecliente.getText() + "-" + claveacceso + "-" + direccion + "-" + cedulatext.getText() + "-" + numerofact.getText().replace(" ", "") + "-";
        //     datosclienteride = datosclienteride + String.valueOf(formatou.format(t / 1.12)).replace(",", ".") + "-" + String.valueOf(formatou.format((t / 1.12) * 0.12)).replace(",", ".") + "-" + String.valueOf(formatou.format(t)).replace(",", ".");
        if (tipoidenficacion.contains("RUC")) {
            codicotipoidentificacion = "04";
        } else if (tipoidenficacion.contains("Cédula")) {
            codicotipoidentificacion = "05";
        } else if (tipoidenficacion.contains("Pasaporte")) {
            codicotipoidentificacion = "06";
        } else {
            codicotipoidentificacion = "07";
        }
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        org.w3c.dom.Document doc = dBuilder.newDocument();
        doc.setXmlStandalone(true);
        org.w3c.dom.Element rootElement = doc.createElement("factura");
        rootElement.setAttribute("id", "comprobante");
        rootElement.setAttribute("version", "1.0.0");
        doc.appendChild(rootElement);
        org.w3c.dom.Element supercar = doc.createElement("infoTributaria");
        rootElement.appendChild(supercar);
        org.w3c.dom.Element ambiente = doc.createElement("ambiente");
        ambiente.appendChild(doc.createTextNode("2"));
        supercar.appendChild(ambiente);
        org.w3c.dom.Element tipoEmision = doc.createElement("tipoEmision");
        tipoEmision.appendChild(doc.createTextNode("1"));
        supercar.appendChild(tipoEmision);
        org.w3c.dom.Element razonSocial = doc.createElement("razonSocial");
        razonSocial.appendChild(doc.createTextNode("GALO ALFREDO ALAVA MACAS"));
        supercar.appendChild(razonSocial);
        org.w3c.dom.Element nombreComercial = doc.createElement("nombreComercial");
        nombreComercial.appendChild(doc.createTextNode("Airnet Agente de Retencion: Nro. NAC-DNCRASC20-00000001"));
        supercar.appendChild(nombreComercial);
        org.w3c.dom.Element ruc = doc.createElement("ruc");
        ruc.appendChild(doc.createTextNode("1204112724001"));
        supercar.appendChild(ruc);
        org.w3c.dom.Element claveAcceso = doc.createElement("claveAcceso");
        claveAcceso.appendChild(doc.createTextNode(claveacceso));
        supercar.appendChild(claveAcceso);
        org.w3c.dom.Element codDoc = doc.createElement("codDoc");
        codDoc.appendChild(doc.createTextNode("01"));
        supercar.appendChild(codDoc);
        org.w3c.dom.Element estab = doc.createElement("estab");
        estab.appendChild(doc.createTextNode("001"));
        supercar.appendChild(estab);
        org.w3c.dom.Element ptoEmi = doc.createElement("ptoEmi");
        ptoEmi.appendChild(doc.createTextNode(q.serieCaja().substring(3)));
        supercar.appendChild(ptoEmi);
        org.w3c.dom.Element secuencial = doc.createElement("secuencial");
        secuencial.appendChild(doc.createTextNode(numerofact.getText().replace(" ", "")));
        supercar.appendChild(secuencial);
        org.w3c.dom.Element dirMatriz = doc.createElement("dirMatriz");
        dirMatriz.appendChild(doc.createTextNode("Av. José Joaquín de Olmedo #220 y Colombia San Camilo - Quevedo - Ecuador"));
        supercar.appendChild(dirMatriz);
        //****************** fin membrete principal general

        //******************** inicio de datos clientes 
        org.w3c.dom.Element cuerpo2 = doc.createElement("infoFactura");
        rootElement.appendChild(cuerpo2);

        org.w3c.dom.Element fechaEmision = doc.createElement("fechaEmision");
        fechaEmision.appendChild(doc.createTextNode(fechagenera.getText().replace(" ", "")));
        cuerpo2.appendChild(fechaEmision);
        org.w3c.dom.Element dirEstablecimiento = doc.createElement("dirEstablecimiento");
        dirEstablecimiento.appendChild(doc.createTextNode("Av. José Joaquín de Olmedo #220 y Colombia San Camilo - Quevedo - Ecuador"));
        cuerpo2.appendChild(dirEstablecimiento);

        org.w3c.dom.Element obligadoContabilidad = doc.createElement("obligadoContabilidad");
        obligadoContabilidad.appendChild(doc.createTextNode("SI"));
        cuerpo2.appendChild(obligadoContabilidad);
        org.w3c.dom.Element tipoIdentificacionProveedor = doc.createElement("tipoIdentificacionComprador");
        tipoIdentificacionProveedor.appendChild(doc.createTextNode(codicotipoidentificacion));
        cuerpo2.appendChild(tipoIdentificacionProveedor);
        org.w3c.dom.Element razonSocialProveedor = doc.createElement("razonSocialComprador");
        razonSocialProveedor.appendChild(doc.createTextNode(nombrecliente.getText()));
        cuerpo2.appendChild(razonSocialProveedor);
        org.w3c.dom.Element identificacionProveedor = doc.createElement("identificacionComprador");
        identificacionProveedor.appendChild(doc.createTextNode(cedulatext.getText()));
        cuerpo2.appendChild(identificacionProveedor);
        org.w3c.dom.Element direccionProveedor = doc.createElement("direccionComprador");
        direccionProveedor.appendChild(doc.createTextNode(direccion));
        cuerpo2.appendChild(direccionProveedor);
        org.w3c.dom.Element totalSinImpuestos = doc.createElement("totalSinImpuestos");
        totalSinImpuestos.appendChild(doc.createTextNode(q.FormatoDecimal(t / 1.12)));
        cuerpo2.appendChild(totalSinImpuestos);
        org.w3c.dom.Element totalDescuento = doc.createElement("totalDescuento");
        totalDescuento.appendChild(doc.createTextNode("0"));
        cuerpo2.appendChild(totalDescuento);
        org.w3c.dom.Element totalConImpuestos = doc.createElement("totalConImpuestos");
        cuerpo2.appendChild(totalConImpuestos);
        //totalImpuesto
        org.w3c.dom.Element totalImpuesto = doc.createElement("totalImpuesto");
        totalConImpuestos.appendChild(totalImpuesto);
        //codigo
        org.w3c.dom.Element codigo = doc.createElement("codigo");
        codigo.appendChild(doc.createTextNode("2"));
        totalImpuesto.appendChild(codigo);
        //codigoPorcentaje
        org.w3c.dom.Element codigoPorcentaje = doc.createElement("codigoPorcentaje");
        codigoPorcentaje.appendChild(doc.createTextNode("2"));
        totalImpuesto.appendChild(codigoPorcentaje);

        // baseImponible
        org.w3c.dom.Element baseImponible = doc.createElement("baseImponible");
        baseImponible.appendChild(doc.createTextNode(q.FormatoDecimal(t / 1.12)));
        totalImpuesto.appendChild(baseImponible);
        //tarifa
        org.w3c.dom.Element tarifa = doc.createElement("tarifa");
        tarifa.appendChild(doc.createTextNode("12"));
        totalImpuesto.appendChild(tarifa);
        //valor
        org.w3c.dom.Element valor = doc.createElement("valor");
        valor.appendChild(doc.createTextNode(q.FormatoDecimal((t / 1.12) * 0.12)));
        totalImpuesto.appendChild(valor);

        //importeTotal
        org.w3c.dom.Element importeTotal = doc.createElement("importeTotal");
        importeTotal.appendChild(doc.createTextNode(q.FormatoDecimal(t)));
        cuerpo2.appendChild(importeTotal);
        //moneda
        org.w3c.dom.Element moneda = doc.createElement("moneda");
        moneda.appendChild(doc.createTextNode("DOLAR"));
        cuerpo2.appendChild(moneda);

        org.w3c.dom.Element pagos = doc.createElement("pagos");
        cuerpo2.appendChild(pagos);
        //moneda
        org.w3c.dom.Element pago = doc.createElement("pago");
        pagos.appendChild(pago);
        org.w3c.dom.Element formaPago = doc.createElement("formaPago");
        formaPago.appendChild(doc.createTextNode("01"));
        pago.appendChild(formaPago);
        org.w3c.dom.Element total = doc.createElement("total");
        total.appendChild(doc.createTextNode(q.FormatoDecimal(t)));
        pago.appendChild(total);

        org.w3c.dom.Element detalles = doc.createElement("detalles");
        rootElement.appendChild(detalles);
        for (XML xml : empList) {

            String valorni = String.valueOf(q.FormatoDecimal(q.ConvertidorStringaDouble(xml.getValorunitario().replace(",", ".")) / 1.12));
            String base = String.valueOf(q.FormatoDecimal((q.ConvertidorStringaDouble(xml.getValorunitario().replace(",", ".")) / 1.12) * q.ConvertidorStringaDouble(xml.getCantidad())));
            String ivabaseimponible = String.valueOf(q.FormatoDecimal(q.ConvertidorStringaDouble(base.replace(",", ".")) * 0.12));
            org.w3c.dom.Element detalle = doc.createElement("detalle");
            detalles.appendChild(detalle);
            org.w3c.dom.Element codigoPrincipal = doc.createElement("codigoPrincipal");
            codigoPrincipal.appendChild(doc.createTextNode(xml.getDetalle()));
            detalle.appendChild(codigoPrincipal);
            org.w3c.dom.Element codigoAuxiliar = doc.createElement("codigoAuxiliar");
            codigoAuxiliar.appendChild(doc.createTextNode(xml.getDetalle()));
            detalle.appendChild(codigoAuxiliar);
            org.w3c.dom.Element descripcion = doc.createElement("descripcion");
            descripcion.appendChild(doc.createTextNode(xml.getDetalle().replace(" ", "")));
            detalle.appendChild(descripcion);
            org.w3c.dom.Element cantidad = doc.createElement("cantidad");
            cantidad.appendChild(doc.createTextNode(xml.getCantidad()));
            detalle.appendChild(cantidad);
            org.w3c.dom.Element precioUnitario = doc.createElement("precioUnitario");
            precioUnitario.appendChild(doc.createTextNode(valorni.replace(",", ".")));
            detalle.appendChild(precioUnitario);
            org.w3c.dom.Element descuento = doc.createElement("descuento");
            descuento.appendChild(doc.createTextNode("0"));
            detalle.appendChild(descuento);
            org.w3c.dom.Element precioTotalSinImpuesto = doc.createElement("precioTotalSinImpuesto");
            precioTotalSinImpuesto.appendChild(doc.createTextNode(base.replace(",", ".")));
            detalle.appendChild(precioTotalSinImpuesto);

            org.w3c.dom.Element impuestos = doc.createElement("impuestos");
            detalle.appendChild(impuestos);
            org.w3c.dom.Element impuesto = doc.createElement("impuesto");
            impuestos.appendChild(impuesto);
            org.w3c.dom.Element codigoim = doc.createElement("codigo");
            codigoim.appendChild(doc.createTextNode("2"));
            impuesto.appendChild(codigoim);
            org.w3c.dom.Element codigoPorcentaje1 = doc.createElement("codigoPorcentaje");
            codigoPorcentaje1.appendChild(doc.createTextNode("2"));
            impuesto.appendChild(codigoPorcentaje1);
            org.w3c.dom.Element tarifa1 = doc.createElement("tarifa");
            tarifa1.appendChild(doc.createTextNode("12"));
            impuesto.appendChild(tarifa1);
            org.w3c.dom.Element baseImponible1 = doc.createElement("baseImponible");
            baseImponible1.appendChild(doc.createTextNode(base.replace(",", ".")));
            impuesto.appendChild(baseImponible1);
            org.w3c.dom.Element valor1 = doc.createElement("valor");
            valor1.appendChild(doc.createTextNode(ivabaseimponible.replace(",", ".")));
            impuesto.appendChild(valor1);

        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\GENERADOS\\" + claveacceso + ".xml"));
        transformer.transform(source, result);
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);

        //q.bas1();
        // q.UpdateModificar("update [BDAirnet].[dbo].[tvdjFacurascliente] set SRI='NO' , Datoscliente='"+datosclienteride+"', correo='"+email+"' where clave='"+claveacceso+"'");
        String fpad = "SIN PAGO", evi;

        q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjFacurascliente]\n"
                + "([nuemrofactura],[cliente],[clave] ,[fechagenerada],[fechadepago],[formapago],[estado],[evidencia],[codigocontrato]\n"
                + ",[SRI] ,[Envio]  ,[TipoFactu])\n"
                + "VALUES\n"
                + "('" + numerofact.getText() + "','" + nombrecliente.getText() + "','" + claveacceso + "','" + fechagenera.getText() + "','" + "NO PAGADO" + "','" + fpad + "','" + "ACTIVA" + "','" + "sin evi" + "','" + cedulatext.getText() + "','" + "NO" + "','" + "NO" + "','" + "NO" + "')");

        onVariable.getValorid().forEach((idclientedetalle) -> {
            q.UpdateModificar("UPDATE  [BDAirnet].[dbo].[detallesfacxtura] set  numerofactura='" + numerofact.getText() + "',Estado=1 where iddetalle=" + idclientedetalle);
        });

        q.UpdateModificar("update [BDAirnet].[dbo].[tvdjFacurascliente] set SRI='NO',direccion='" + direccion + "',total='" + this.total.getText() + "',iva='" + iva.getText() + "',subtotal='" + subto.getText() + "' , Datoscliente='" + "---" + "', correo='" + email + "', seriem ='001',serieca='" + q.serieCaja().substring(3) + "' where nuemrofactura='" + numerofact.getText() + "'");

    }

    public void busqueda() throws SQLException {

        double vt = 0, viva = 0, sut = 0;

        tabladetalle.setItems(clt);
        canti.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        detalle.setCellValueFactory(new PropertyValueFactory<>("Detalle"));
        pvp.setCellValueFactory(new PropertyValueFactory<>("pvp"));
        pvpt.setCellValueFactory(new PropertyValueFactory<>("pvpt"));
        idval.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabladetalle.getItems().clear();
        // String cons="SELECT  [iddetalle],[detalle],[valorunitario],[valortotal],[cantidad]FROM [BDAirnet].[dbo].[detallesfacxtura] where Estado='1' and codigo='"+cedulatext.getText()+"'";
        ResultSet sw = q.tablas("SELECT * FROM [BDAirnet].[dbo].[detallesfacxtura] where Estado='1' and codigo='" + cedulatext.getText() + "' and numerofactura is null");
        // String cons="SELECT  [iddetalle],[numerofactura],[detalle],[valorunitario],[valortotal],[claveacceso],[cliente],[cantidad] FROM [BDAirnet].[dbo].[detallesfacxtura] where numerofactura ='"+numerofact.getText()+"'";      
        //ResultSet sw = q.tablas(cons);
        while (sw.next()) {
        clt.add(new recepcionpago(sw.getString("cantidad").trim()
                , sw.getString("detalle").trim()
                , q.FormatoDecimal(q.ConvertidorStringaDouble(sw.getString("valorunitario")))
                , q.FormatoDecimal(q.ConvertidorStringaDouble(sw.getString("valortotal")))
                , sw.getString("iddetalle")));
        }
        /**
         * **************
         */
        for (int i = 0; i < tabladetalle.getItems().size(); i++) {
            vt = vt + q.ConvertidorStringaDouble(String.valueOf(pvpt.getCellObservableValue(i).getValue()));
        }

        sut = vt / 1.12;
        viva = (sut * 12) / 100;
        total.setText(q.FormatoDecimal(vt));
        subto.setText(q.FormatoDecimal(sut));
        iva.setText(q.FormatoDecimal(viva));
        /**
         * *******************
         */
    }

    public void valores() {
        // llenado de productos como items

        try {
            busqueda();
        } catch (SQLException ex) {
        }
        Clavedeacceso clave = new Clavedeacceso();
        claveacceso = clave.GenerarClaveAcceso(fechagenera.getText(), "01", q.serieCaja(), "1204112724001", numerofact.getText(), "1", "2");
        clavaccessri.setText(claveacceso);
    }

    public void CREARALERTAS() {
        // Create the custom 
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Credenciales de Anulacion de Facturas");
        dialog.setHeaderText("Solicite al supervisor las credenciales para proceder con el proceso \n "
                + "");

// Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
// Set the button types.
        ButtonType loginButtonType = new ButtonType("Anular", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

       
     
        
        PasswordField password = new PasswordField();
        password.setPromptText("Ingrese las Credenciales de supervisor");

        grid.add(new Label("Proceder a anular la/s Factura/s Seleccionada/s"), 0, 0);
      
        grid.add(new Label("KEY SUPERVISOR :"), 0, 1);
        grid.add(password, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> password.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>("1", password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            if (usernamePassword.getValue().equals("1725746513")) {
                if (!onVariable.getValorid().isEmpty()) {

                    try {

                        for (Object s : onVariable.getValorid()) {
                            q.DeleteEliminar(" update [BDAirnet].[dbo].[detallesfacxtura] set Estado=0,numerofactura='ANULADA',claveacceso='" + VariablesDeUso.nombreusuario.replace(" ", "") + "' where iddetalle='" + s + "'");
                            tabladetalle.getItems().clear();
                        }
                        busqueda();
                        q.notificaciones("Se Anulo correctamente el item", "I");
                    } catch (SQLException ex) {
                        //  Logger.getLogger(RecepciondepagoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    q.notificaciones("Seleccione un Item para poder ANULARLA", "I");
                }

            }

        });

    }

    public void ValorReinicio() {

        nombrecliente.setText("");
        cedulatext.setText("");
        numerofact.setText(q.facturamaxima());
        fechagenera.setText(q.FechaformatoCalendaerio());
        fechapago.setText(q.FechaformatoCalendaerio());
        tabladetalle.getItems().clear();

        //  valorabono.setText("0");
        // valorreceptado.setText("0");
        total.setText("0");
        subto.setText("0");
        iva.setText("0");
        nombrecliente.requestFocus();

    }
}
