/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GESTION;

import CLASES.Clase_Estado_Individual;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafxapplication4.Clase_VistaCLientes;
import CLASES.ClientePromesaPago;
import CLASES.HiloClienteDeudas;
import javafxapplication4.VariablesDeUso;
import CLASES.VerificarIP;
import CLASES.bas;
import java.sql.ResultSet;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javax.net.SocketFactory;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;

public class CorteservicioController implements Initializable {

    String IpMicro = "";
    String usuario = "";
    String pass1 = "";
    Task task;
    Thread t;
    List luna = new ArrayList();
    String ip = "";
    bas q = new bas();
    final ToggleGroup group1 = new ToggleGroup();
     final ToggleGroup group2 = new ToggleGroup();
    @FXML
    private Pane nuevo;
    @FXML
    private TextField ipser4v;
    @FXML
    private TextField abonado;
    @FXML
    private Button guardarnuevoabon;

    @FXML
    private Button cortere;
    @FXML
    private RadioButton RE;
    @FXML
    private RadioButton FO;
    @FXML
    private TextArea criteriodecorte;
    @FXML
    private TextArea clientesdecorte;
    @FXML
    private Label actual;
    @FXML
    private Label totalcorte;
    @FXML
    private CheckBox activarCriterio;
    @FXML
    private ComboBox<String> mesaplicado;
    String consultaRE = "";
    @FXML
    private ComboBox<String> plan;
    String Lista = "no-pago";
    @FXML
    private Label ultimaipUsa;
    // TableColumn<?, ?> cliente = "";
    int c = 0;
    @FXML
    private Label posiblecobro;
    @FXML
    private TableColumn<String, ClientePromesaPago> Cleintepromesadepago;
    @FXML
    private TableColumn<String, ClientePromesaPago> ippromesapago;
    @FXML
    private TableColumn<String, ClientePromesaPago> fechadecortepromesapago;
    String Valor;
    @FXML
    private ImageView imagencarga;
    @FXML
    private TableView tdvjPromesadePago;
    @FXML
    private ProgressBar bar;
    @FXML
    private TableView LISTADOIP;
    @FXML
    private TableColumn<Integer, CLASEIP> IDIP;
    @FXML
    private TableColumn<String, CLASEIP> PLANIP;
    @FXML
    private TableColumn<String, CLASEIP> IPACTUA;
    @FXML
    private TableColumn<String, CLASEIP> GATE;
    @FXML
    private TableColumn<String, CLASEIP> BROAD;
    @FXML
    private Tab ListadeIP;
    @FXML
    private TableView listasdeipusadas;
    @FXML
    private TableColumn<Integer, ClaseListadoIP> idcliente;
    @FXML
    private TableColumn<String, ClaseListadoIP> iptablalistado;
    @FXML
    private TableColumn<String, ClaseListadoIP> clientelistado;
    @FXML
    private TableColumn<String, ClaseListadoIP> serial;
    @FXML
    private TextField serialip;
    @FXML
    private Button buscar;
    @FXML
    private RadioButton re;
    @FXML
    private RadioButton FOListado;
    Clase_Estado_Individual ob;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ContextMenu context3 = new ContextMenu();
        MenuItem cont3 = new MenuItem("IR VISTA CLIENTE");
        MenuItem Item3 = new MenuItem("Historial de Pago");
        context3.getItems().addAll(cont3,Item3);
        listasdeipusadas.setContextMenu(context3);
         cont3.setOnAction((event) -> {
             ob = new Clase_Estado_Individual(String.valueOf(clientelistado.getCellObservableValue(listasdeipusadas.getSelectionModel().getSelectedIndex()).getValue()));
            ob.cargarvalores2("D");
        });
         
         Item3.setOnAction((event) -> {
             Clase_Estado_Individual.clientetext=null;
             Clase_Estado_Individual.clientetext=String.valueOf(clientelistado.getCellObservableValue(listasdeipusadas.getSelectionModel().getSelectedIndex()).getValue());
            q.ventanasAxu("/INDICADORES/Reporte_de_Pagos.fxml", "Reporte de Pagos");
            
        });
         
        FOListado.setSelected(true);
        listasdeipusadas.setItems(LISTADOSIP);
        idcliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        iptablalistado.setCellValueFactory(new PropertyValueFactory<>("iptablalistado"));
        clientelistado.setCellValueFactory(new PropertyValueFactory<>("clientelistado"));
        serial.setCellValueFactory(new PropertyValueFactory<>("serial"));

        buscar.setOnAction((event) -> {
            LISTADEIP();
        });
        re.setToggleGroup(group2);
        FOListado.setToggleGroup(group2);
        serialip.setOnKeyPressed((KeyEvent event) -> {

            if (event.getCode() == KeyCode.ENTER) {
                LISTADEIP();
            }

        });
        
        // TODO
        ContextMenu context2 = new ContextMenu();
        MenuItem context2Item1 = new MenuItem("Actualizar IP ACTUAL");
        MenuItem context2Item2 = new MenuItem("Actualizar RANGO IP");
        context2.getItems().addAll(context2Item1, context2Item2);
        LISTADOIP.setContextMenu(context2);
        context2Item1.setOnAction((event) -> {

            CREARALERTASECambiarValor();
        });
        actual.setText("0");
        plan.setOnAction((event) -> {
            AnalisisdeUltimaIp();
        });

        clientesdecorte.textProperty().addListener((observable, oldValue, newValue) -> {
            //clientesdecorte.setText(task.getMessage());
        });
        clientesdecorte.setEditable(false);
        String[] Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        activarCriterio.setOnAction((event) -> {
            if (activarCriterio.isSelected()) {
                Lista = "no-pago";
                q.notificaciones("RECUERDE QUE AL ACTIVAR ESTE ITEM SE APLIACARA EL CORTE", "I");
            } else {
                Lista = "no-pago";
                q.notificaciones("RECUERDE QUE AL DESACTIVAR ESTE ITEM NO SE APLIACARA EL CORTE", "I");
            }
        });
        mesaplicado.getItems().addAll(Meses);
        consultaRE = " and IDCLIENTE != 11775 and IDCLIENTE  != 11213  and IDCLIENTE  != 116  and IDCLIENTE  != 755  and IDCLIENTE  != 11260 and IDCLIENTE  != 1459"
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
                + " and IDCLIENTE != 1\n"
                + "and IDCLIENTE !=1560\n"
                + "and IDCLIENTE !=  13545\n"
                + "and IDCLIENTE != 380";
        mesaplicado.setOnAction((event) -> {
            Lista = "no-pago";
        });
        criteriodecorte.setText(consultaRE);
        plan.getItems().addAll("CORP01", "CORP02", "CORP03", "PROMO01", "PROMO02", "PROMO020", "PROMO30", "PROMO40", "PROMO50", "PROMO60", "PROMO70", "PROMO80", "PROMO90", "PROMO100", "RESID01", "RESID02");
        FO.setToggleGroup(group1);
        RE.setToggleGroup(group1);
        ipser4v.setOnKeyTyped((event) -> {

            try {
                char key = event.getCharacter().charAt(0);
                if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0' || key == '.') {

                } else {
                    event.consume();
                    q.notificaciones("Ingresar solo Digitos [0-9] y/o Punto [.]", "");
                }
            } catch (Exception ex) {
            }
        });

        guardarnuevoabon.setOnAction((event) -> {
            crearnuevo();
        });

        cortere.setOnAction((event) -> {
            NuevoEstilodeCorte(criteriodecorte.getText().trim());

        });

        ValoresIniciales();
        cargar_Valores();
        CARGARLISTADO();
    }

    public void coectar() throws MikrotikApiException {

        if (FO.isSelected()) {
            crearFO();

        } else {
            crearRE();

        }
        try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
            con.login(usuario, pass1);
            if (con.isConnected()) {
                con.execute("/ip/firewall/address-list/add" + " list=" + Lista + "  address=" + ipser4v.getText().trim());
                Thread.sleep(50);
                List<Map<String, String>> rs2 = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);
                System.out.println(rs2);
                rs2.forEach((r1) -> {
                    if (r1.get("address").equals(ipser4v.getText().trim())) {

                        try {
                            con.execute("/ip/firewall/address-list/set .id=" + r1.get(".id") + " comment=\"" + abonado.getText().trim() + "\"");
                            con.execute("/ip/firewall/address-list/set .id=" + r1.get(".id") + " disabled=true");
                        } catch (MikrotikApiException ex) {
                            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
            } else {
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ObservableList<ClientePromesaPago> productos = FXCollections.observableArrayList();

    public void ValoresIniciales() {
        tdvjPromesadePago.setItems(productos);
        Cleintepromesadepago.setCellValueFactory(new PropertyValueFactory<>("Cleintepromesadepago"));
        ippromesapago.setCellValueFactory(new PropertyValueFactory<>("ippromesapago"));
        fechadecortepromesapago.setCellValueFactory(new PropertyValueFactory<>("fechadecortepromesapago"));

        LISTADOIP.setItems(ITEMIP);
        IDIP.setCellValueFactory(new PropertyValueFactory<>("ID"));
        PLANIP.setCellValueFactory(new PropertyValueFactory<>("PLAN"));
        IPACTUA.setCellValueFactory(new PropertyValueFactory<>("IPACTUAL"));
        GATE.setCellValueFactory(new PropertyValueFactory<>("IPGATE"));
        BROAD.setCellValueFactory(new PropertyValueFactory<>("IPBROAD"));

    }

    public void cargar_Valores() {
        System.out.println("  SELECT  "
                + "[Clientes], [IDPromesadepago] "
                + ",[Fechadepago],[Cedula]"
                + ",[IP]"
                + " FROM [BDAirnet].[dbo].[tdvjPromesadePago] where "
                + "Estado=1 and Fechadepago between '" + q.FechaformatoCalendaerio() + "' and '" + q.FechaformatoCalendaerio() + "'");
        q.auxConsulta = q.tablas("  SELECT  "
                + "[Clientes], [IDPromesadepago] "
                + ",[Fechadepago],[Cedula]"
                + ",[IP]"
                + " FROM [BDAirnet].[dbo].[tdvjPromesadePago] where "
                + "Estado=1 and Fechadepago between '" + q.FechaformatoCalendaerio() + "' and '" + q.FechaformatoCalendaerio() + "'");
        try {
            while (q.auxConsulta.next()) {
                HiloClienteDeudas HiloDeudas = new HiloClienteDeudas(q.auxConsulta.getString("Cedula"));
                // Valor = HiloDeudas.busquedaTotalDeuda;
                if (HiloDeudas.busquedaTotalDeuda() > 0) {
                    productos.add(new ClientePromesaPago(
                            q.auxConsulta.getString("Clientes"),
                            q.auxConsulta.getString("IP"),
                            q.auxConsulta.getString("Fechadepago")));
                } else {
                    q.UpdateModificar("update [BDAirnet].[dbo].[tdvjPromesadePago] set Estado=0 where IDPromesadepago=" + q.auxConsulta.getString("IDPromesadepago"));
                    q.notificaciones("El Cliente " + q.auxConsulta.getString("Clientes").trim() + " salio Promesa de Pago", "I");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CorteservicioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (tdvjPromesadePago.getItems().isEmpty()) {
            tdvjPromesadePago.setPlaceholder(new Label("No hay Clientes en promesa de Pago a la Fecha"));
        }

    }

    public void crearnuevo() {
        if (!ipser4v.getText().trim().isEmpty() && !abonado.getText().trim().isEmpty()) {
            try {
                coectar();
                LimpiarCampos();
            } catch (MikrotikApiException ex) {
                Logger.getLogger(CorteservicioController.class.getName()).log(Level.SEVERE, null, ex);
                q.notificaciones("Error: " + ex, ip);
            }
        } else {
            q.notificaciones("Campos incompletos Verificar/Completar y luego Continuar", "I");
        }

    }

    public void LimpiarCampos() {
        ipser4v.setText("");
        abonado.setText("");
        luna.clear();
        RE.setSelected(false);
        FO.setSelected(false);

    }
    Clase_VistaCLientes obcl;

    public void Funcion_Corte() {
        totalcorte.setText(String.valueOf(map.size()));
        String IP = null;
        String Cliente = null;
        obj.EncerarConteor();
        Iterator<String> it = map.keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            IP = it.next();
            Cliente = (String) map.get(IP);
            obj.setCONTEOS(actual.getText());
            actual.setText(obj.Datos());
            clientesdecorte.setText(clientesdecorte.getText() + "\n" + Cliente + "\t" + IP);
            if (!luna.contains(IP)) {
                luna.add(IP);
                HiloIP(IP, Cliente);
                /*
               obcl = new Clase_VistaCLientes(IP, Cliente);
            if (IP.contains("172.16")) {
                   if (activarCriterio.isSelected()) {
                    try {
                        obcl.CorteRadio();
                    } catch (MikrotikApiException ex) {
                        Logger.getLogger(CorteservicioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (IP.contains("172.")) {

                if (activarCriterio.isSelected()) {
                    try {
                        obcl.CorteFibra();
                    } catch (MikrotikApiException ex) {
                        Logger.getLogger(CorteservicioController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }*/
            }
            try {

                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CorteservicioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    String valorespo = "";

    public void HiloIP(String IP, String Cliente) {
        obcl = new Clase_VistaCLientes(IP, Cliente);
         System.out.println("\n" + Cliente + "\t" + IP);
        if (IP.contains("172.16")) {
            if (activarCriterio.isSelected()) {
                try {
                    obcl.CorteRadio();
                    valorespo = valorespo + "\n" + Cliente + "\t" + IP;
                   
                    //  clientesdecorte.setText(clientesdecorte.getText() + "\n" + Cliente + "\t" + IP);
                } catch (MikrotikApiException ex) {
                    Logger.getLogger(CorteservicioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (IP.contains("172.")) {
            if (activarCriterio.isSelected()) {
                if (!obcl.DesActivarServicioFO(activarCriterio.isSelected(), Cliente)) {
                    valorespo = valorespo + "\n" + Cliente + "\t" + IP;
                }
            }
        }

    }

    public void crearFO() {
        q.cargarvaloresPropiedades();
        if (!q.properties.containsKey("IPMIKFIBRA")) {
            q.CreacionArchivo(true);
        }
        IpMicro = q.properties.getProperty("IPMIKFIBRA");
        usuario = q.properties.getProperty("USUARIOMIKRO");
        pass1 = q.properties.getProperty("PASSMIKRO");
    }

    public void crearRE() {
        q.cargarvaloresPropiedades();
        if (!q.properties.containsKey("IPMIKAIRE")) {
            q.CreacionArchivo(true);
        }
        IpMicro = q.properties.getProperty("IPMIKAIRE");
        usuario = q.properties.getProperty("USUARIOMIKRO");
        pass1 = q.properties.getProperty("PASSMIKRO");
    }

    public void obtenertlistaencorteFP() {
        crearFO();
        try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
            con.login(usuario, pass1);
            if (con.isConnected()) {
                List<Map<String, String>> rs1 = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);

                for (Map<String, String> r1 : rs1) {
                    luna.add(r1.get("address"));

                }
            }
        } catch (MikrotikApiException ex) {
            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ObterloistaRe() {
        crearRE();
        try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
            con.login(usuario, pass1);
            if (con.isConnected()) {
                List<Map<String, String>> rs1 = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);
                for (Map<String, String> r1 : rs1) {
                    luna.add(r1.get("address"));
                }
            }
        } catch (MikrotikApiException ex) {
            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AnalisisdeUltimaIp() {
        if (RE.isSelected()) {
            Lista = plan.getSelectionModel().getSelectedItem();
            ObterloistaRe();
            ultimaipUsa.setText("Ultima Ip Usada en el Plan " + Lista + " " + luna.get(luna.size() - 1).toString());
        } else {
            Lista = plan.getSelectionModel().getSelectedItem();
            obtenertlistaencorteFP();
            ultimaipUsa.setText("Ultima Ip Usada en el Plan " + Lista + " " + luna.get(luna.size() - 1).toString());
        }
    }
    HashMap<String, Object> map = new HashMap<String, Object>();
    VerificarIP verificadorip = new VerificarIP();
    VariablesDeUso obj = new VariablesDeUso();

    public void NuevoEstilodeCorte(String x) {

        obj.Encerar();
        // HiloClienteDeudas HiloDeudas;
        q.auxConsulta = q.tablas("SELECT  [valortotal]\n"
                + ",[ipv4]\n"
                + ",([NOMBRES]+' '+[APELLIDOS]) as Cliente\n"
                + ",[IDCLIENTE]\n"
                + ",[nodoprincipal]\n"
                + ",[OLTTARJETA]\n"
                + ",[OLTPON]\n"
                + ",[ONUID]\n"
                + ",[ponmac]\n"
                + "  FROM [BDAirnet].[dbo].[VistaparaCorte]  where detalle like '%" + mesaplicado.getSelectionModel().getSelectedItem() + "%' "
                + " and [EstadoCliente]=1 and ESTADO=1 " + x + " order by ipv4 asc");

        try {
            ObterloistaRe();
            Thread.sleep(5);
            //  obtenertlistaencorteFP();
            //  Thread.sleep(5);
            while (q.auxConsulta.next()) {
                if (verificadorip.isValidIPV4(q.auxConsulta.getString("ipv4").trim())) {
                    if (q.auxConsulta.getString("ipv4").trim().contains("172.16.")) {
                        map.put(q.auxConsulta.getString("ipv4").trim(), q.auxConsulta.getString("Cliente").trim());

                    } else if (q.auxConsulta.getString("ipv4").trim().contains("172.")) {
                        map.put(q.auxConsulta.getString("ipv4").trim(), q.auxConsulta.getString("IDCLIENTE").trim());
                    }
                    obj.setValorTotal(q.ConvertidorStringaDouble(q.auxConsulta.getString("valortotal").trim()));

                }
            }
            posiblecobro.setText(q.FormatoDecimal(obj.getValorTotal()));
            totalcorte.setText(String.valueOf(map.size()));
        } catch (SQLException | InterruptedException ex) {

            Logger.getLogger(CorteservicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HilodeCorte();

    }

    public void HilodeCorte() {

        task = new Task() {
            @Override
            protected Object call() throws Exception {

                totalcorte.setText(String.valueOf(map.size()));
                String IP = "";
                String Cliente = "";
                obj.EncerarConteor();
                Iterator<String> it = map.keySet().iterator();
                int i = 0;
                while (it.hasNext()) {
                    IP = it.next();
                    Cliente = (String) map.get(IP);
                    // System.out.println(IP+"\t"+Cliente);
                    if (!luna.contains(IP)) {
                        luna.add(IP);
                        HiloIP(IP, Cliente);
                        //System.out.println(IP+"\t"+Cliente);
                        i++;
                    } else {
                        i++;
                    }
                    updateProgress(i, map.size() - 1);
                }

                return null;
            }
        };

        task.setOnRunning((event) -> {
            imagencarga.setVisible(true);
            //  clientesdecorte.setText(task.getMessage());
        });

        task.setOnSucceeded(event -> {
            imagencarga.setVisible(false);
            System.err.println(valorespo);
            // aqui va que hacer cuando ya acabe de ejecutarse el hilo
        });

        new Thread(task).start();

        bar.progressProperty().bind(task.progressProperty());

    }
    ObservableList<CLASEIP> ITEMIP = FXCollections.observableArrayList();

    public void CARGARLISTADO() {
        LISTADOIP.getItems().clear();
        ResultSet LISTADO = q.tablas("SELECT  [IDPLAN]\n"
                + "      ,[PLANIPFIBRA]\n"
                + "      ,[IPINICIAL]\n"
                + "      ,[IPFINAL]\n"
                + "      ,[IPACTUAL]\n"
                + "  FROM [BDAirnet].[dbo].[Tabla_ListaIP]");
        try {
            while (LISTADO.next()) {
                ITEMIP.add(new CLASEIP(LISTADO.getInt("IDPLAN"),
                        LISTADO.getString("PLANIPFIBRA").trim(),
                        LISTADO.getString("IPACTUAL"),
                        LISTADO.getString("IPINICIAL"),
                        LISTADO.getString("IPFINAL")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CorteservicioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        LISTADOIP.setItems(ITEMIP);
    }

    public void CREARALERTASECambiarValor() {
        // Create the custom 
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Actualizacion de IP ACTUAL");
        dialog.setHeaderText("Ingrese las credenciales para proceder con el proceso");

// Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
// Set the button types.
        ButtonType loginButtonType = new ButtonType("Actualizar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setText(String.valueOf(ITEMIP.get(LISTADOIP.getSelectionModel().getSelectedIndex()).getID()));
        username.setEditable(false);
        TextField ValorActualizar = new TextField();
        ValorActualizar.setPromptText("NUEVA IP");
        PasswordField password = new PasswordField();
        password.setPromptText("Ingrese las Credenciales de supervisor");

        grid.add(new Label("ID PLAN :"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("NUEVA IP :"), 0, 1);
        grid.add(ValorActualizar, 1, 1);
        grid.add(new Label("Credencial :"), 0, 2);
        grid.add(password, 1, 2);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            if (usernamePassword.getValue().equals("7887")) {
                q.DeleteEliminar("update [BDAirnet].[dbo].[Tabla_ListaIP] set"
                        + " [IPACTUAL]='" + ValorActualizar.getText() + "'"
                        + ",[QUIENREGISTRO]='" + VariablesDeUso.nombreusuario.replace(" ", "")
                        + "' where  [IDPLAN]=" + username.getText());
                CARGARLISTADO();
            }

        });
    }

    public void CREARALERTACTUALIZARRANGO() {
        // Create the custom 
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Actualizacion de RANGO DE IP");
        dialog.setHeaderText("Ingrese las credenciales para proceder con el proceso");

// Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
// Set the button types.
        ButtonType loginButtonType = new ButtonType("Actualizar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField username = new TextField();
        username.setText(String.valueOf(ITEMIP.get(LISTADOIP.getSelectionModel().getSelectedIndex()).getID()));
        username.setEditable(false);
        TextField ValorActualizar = new TextField();
        ValorActualizar.setPromptText("NUEVA IP");
        TextField gate = new TextField();
        ValorActualizar.setPromptText("NUEVA GATEWAY");
        TextField broad = new TextField();
        ValorActualizar.setPromptText("NUEVA BROADCAST");
        PasswordField password = new PasswordField();
        password.setPromptText("Ingrese las Credenciales de supervisor");
        grid.add(new Label("ID PLAN :"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("NUEVA IP :"), 0, 1);
        grid.add(ValorActualizar, 1, 1);
        grid.add(new Label("NUEVA GATEWAY :"), 0, 2);
        grid.add(gate, 1, 2);
        grid.add(new Label("NUEVA BROADCAST :"), 0, 3);
        grid.add(broad, 1, 3);
        grid.add(new Label("Credencial :"), 0, 4);
        grid.add(password, 1, 4);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            if (usernamePassword.getValue().equals("7887")) {
                q.DeleteEliminar("update [BDAirnet].[dbo].[Tabla_ListaIP] set"
                        + " [IPACTUAL]='" + ValorActualizar.getText() + "'"
                        + ",[QUIENREGISTRO]='" + VariablesDeUso.nombreusuario.replace(" ", "")
                        + "'," + " [IPINICIAL]='" + gate.getText() + "'"
                        + ", [IPFINAL]='" + broad.getText() + "'"
                        + " where  [IDPLAN]=" + username.getText());
                CARGARLISTADO();
            }

        });
    }

    ObservableList<ClaseListadoIP> LISTADOSIP = FXCollections.observableArrayList();

    public void LISTADEIP() {
        listasdeipusadas.getItems().clear();
         String ValoresLIstado;
        if(!serialip.getText().trim().isEmpty()){
            if(serialip.getText().trim().contains(".")){
                 ValoresLIstado = ("SELECT  IDCLIENTE "
                + ",([NOMBRES]+' '+[APELLIDOS]) as NOMBRES"
                + ",ipv4 "
                + ",[PONMAC]"
                + " FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] "
                + "where  ipv4='"+serialip.getText().trim()+"'");
            }else {
                 ValoresLIstado = ("SELECT  IDCLIENTE "
                + ",([NOMBRES]+' '+[APELLIDOS]) as NOMBRES"
                + ",ipv4 "
                + ",[PONMAC]"
                + " FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] "
                + "where  PONMAC='"+serialip.getText().trim()+"'");
            }
        }else {
           ValoresLIstado = ("SELECT  IDCLIENTE "
                + ",([NOMBRES]+' '+[APELLIDOS]) as NOMBRES"
                + ",ipv4 "
                + ",[PONMAC]"
                + " FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] "
                + "where  ESTADO=1");

        if (FOListado.isSelected()) {
            ValoresLIstado = ValoresLIstado+" and TIPOINSTALACION='Fibra Óptica'";
            serial.setVisible(true);
        } else {
            ValoresLIstado = ValoresLIstado+" and TIPOINSTALACION='Radio Enlace'";
            serial.setVisible(false);
        } 
        }
       
        
        ValoresLIstado = ValoresLIstado +" order by ipv4 asc";
        ResultSet USOIP = q.tablas(ValoresLIstado);
        try {
            while (USOIP.next()){
                LISTADOSIP.add(new ClaseListadoIP(USOIP.getInt("IDCLIENTE")
                        , USOIP.getString("ipv4")
                        , USOIP.getString("NOMBRES")
                        , USOIP.getString("PONMAC")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CorteservicioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
