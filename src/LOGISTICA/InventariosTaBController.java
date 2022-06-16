/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGISTICA;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafxapplication4.ExcelExport;
import CLASES.bas;
import CLASES.datosproveerdor;
import javafxapplication4.produ;
import CLASES.tipopro;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class InventariosTaBController implements Initializable {

    bas a = new bas();
    ObservableList<tipopro> clt = FXCollections.observableArrayList();
    ObservableList<datosproveerdor> proveedorOB = FXCollections.observableArrayList();
    ObservableList<produ> productos = FXCollections.observableArrayList();
    List list = new ArrayList();
    List list2 = new ArrayList();
    @FXML
    private Tab TabGenerados;
    @FXML
    private TableView bodegatipo;
    @FXML
    private TableColumn<String, tipopro> id;
    @FXML
    private TableColumn<String, tipopro> producto;
    @FXML
    private TableColumn<String, tipopro> descripcion;
    @FXML
    private TableColumn<String, tipopro> tipo;
    @FXML
    private TableColumn<String, tipopro> impuesto;
    @FXML
    private TableColumn<String, tipopro> disponibles;
    @FXML
    private Button actualizar;
    @FXML
    private Tab TabContestados;
    @FXML
    private Button nuevobt;
    @FXML
    private TableView tablaproveedor;
    @FXML
    private TableColumn<String, datosproveerdor> ide;
    @FXML
    private TableColumn<String, datosproveerdor> proveedor;
    @FXML
    private TableColumn<String, datosproveerdor> ruc;
    @FXML
    private TableColumn<String, datosproveerdor> correo;
    @FXML
    private TableColumn<String, datosproveerdor> telefono;
    @FXML
    private TableColumn<String, datosproveerdor> direccion;
    @FXML
    private Tab TabCerrados;
    @FXML
    private Button nuevo;
    @FXML
    private TextField buscarpro;
    @FXML
    private TableView tablaproducto;
    @FXML
    private TableColumn<String, produ> idpructo;
    @FXML
    private TableColumn<String, produ> productoont;
    @FXML
    private TableColumn<String, produ> proveedoront;
    @FXML
    private TableColumn<String, produ> serie;
    @FXML
    private TableColumn<String, produ> macid;
    @FXML
    private TableColumn<String, produ> pon;
    @FXML
    private TableColumn<String, produ> precio;
    @FXML
    private TableColumn<String, produ> cliente;
    @FXML
    private TableColumn<String, produ> ingreso;
    @FXML
    private TableColumn<String, produ> fecha;
    @FXML
    private TableColumn<String, produ> IP;
    @FXML
    private TableColumn<String, produ> activa;
    @FXML
    private TableColumn<String, produ> lote;
    @FXML
    private TableColumn<String, produ> metraje;
    @FXML
    private TableColumn<String, produ> estado;

    @FXML
    private Button adigmaterial;
    @FXML
    private Button BuscarFECHAS;
    @FXML
    private DatePicker inicioreporte;
    @FXML
    private DatePicker finbusqueda;
    @FXML
    private Button reporte;
    @FXML
    private TextField buscar;
    @FXML
    private TextField buscarproveedor;
    @FXML
    private ComboBox<String> seleccionarproducto;
    @FXML
    private Button exportarExel;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ContextMenu context = new ContextMenu();
        MenuItem Item1 = new MenuItem("Desasignar Item");
        Item1.setOnAction((event) -> {
            if (!tablaproducto.getSelectionModel().isEmpty()) {
                String IDPRODUCTO = String.valueOf(idpructo.getCellObservableValue(tablaproducto.getSelectionModel().getSelectedIndex()).getValue());
                a.UpdateModificar("update [BDAirnet].[dbo].[tvproductosdj]set cliente=NULL,CONTRATO=NULL,estado='Inactivo',activa='NO' where id=" + IDPRODUCTO);
                a.notificaciones("Datos Actualizados", "I");

            }

        });
        context.getItems().addAll(Item1);
        tablaproducto.setContextMenu(context);

        exportarExel.setOnAction((event) -> {
            if (!tablaproducto.getItems().isEmpty()) {
                ExcelExport valor = new ExcelExport();
                valor.export(tablaproducto, "Reporte_Inventario");

            } else {
                a.notificaciones("Tabla vacia no se puede realizar la Exportacion", "I");
            }
        });

        tablaproducto.setPlaceholder(new Label("Ejecute un parametro de Busqueda..."));

        valores();
        bodegatipo.setItems(clt);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        producto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        impuesto.setCellValueFactory(new PropertyValueFactory<>("impuesto"));
        disponibles.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tablaproveedor.setItems(proveedorOB);
        ide.setCellValueFactory(new PropertyValueFactory<>("ide"));
        proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        ruc.setCellValueFactory(new PropertyValueFactory<>("ruc"));
        correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        // TODO
        reporte.setOnAction((event) -> {
            ReporteServicioTecnicoImpre(inicioreporte.getValue().toString(), finbusqueda.getValue().toString());
        });
        inicioreporte.setValue(LocalDate.now());
        finbusqueda.setValue(LocalDate.now());
        bodegatipo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        cargar();
        actualizar.setOnAction((event) -> {
            cargar();
        });
        llenar();

        adigmaterial.setOnAction((event) -> {
            a.ventanas("/LOGISTICA/Asignaciondemateria.fxml");

        });

        buscarproveedor.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                llenar();
            }
        });
        buscar.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                cargar();
            }
        });
        buscarpro.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    cargarproducto();
                } catch (SQLException ex) {

                }
            }
        });
        BuscarFECHAS.setOnAction((event) -> {
            try {
                cargarproducto();
            } catch (SQLException ex) {

            }
        });
        valorescargaproducto();
    }

    @FXML
    private void nuevoCategorias(ActionEvent event) {
        a.ventanas("/LOGISTICA/MBDLBOCATPRO.fxml");

    }

    @FXML
    private void nuevopro(ActionEvent event) {
        a.ventanas("/LOGISTICA/MDLBONOVAP.fxml");
    }

    @FXML
    private void nuevoont(ActionEvent event) {
        a.ventanas("/LOGISTICA/NEWPRO.fxml");
    }

    public void valores() {
        ResultSet sd = a.tablas("SELECT  [producto] FROM [BDAirnet].[dbo].[tpcategoriadj]");
        try {
            while (sd.next()) {
                seleccionarproducto.getItems().addAll(sd.getString("producto").trim());
                list2.add(sd.getString("producto").trim());
            }
        } catch (SQLException ex) {
            Logger.getLogger(InventariosTaBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.prediccion(buscar, list2);
    }

    public String BusquedaCategoriapoProducto() {
        String valorretorno = "";
        if (!buscar.getText().trim().isEmpty()) {
            valorretorno = "where producto like '%" + buscar.getText() + "%' ";
        }
        return valorretorno;
    }

    public void cargar() {

        bodegatipo.getItems().clear();
        ResultSet st = a.tablas("SELECT  [ide],[tipo],[producto],[detalle],[cantidad],[impuesto],[unidadmedida]FROM [BDAirnet].[dbo].[tpcategoriadj] " + BusquedaCategoriapoProducto());
        String cuantos = "";
        try {

            while (st.next()) {
                String can = "SELECT COUNT (*) as cantidad  FROM [BDAirnet].[dbo].[tvproductosdj] where producto = '" + st.getString("producto") + "' and estado ='Inactivo'";
                ResultSet ct = a.tablas(can);
                while (ct.next()) {
                    cuantos = String.valueOf(ct.getInt("cantidad"));
                }
                clt.add(new tipopro(st.getString("ide"), st.getString("tipo"), st.getString("producto"), st.getString("detalle"), st.getString("impuesto"), cuantos));

            }
        } catch (SQLException exception) {
            a.notificaciones("Se produjo un error de nivel : " + exception.getMessage(), "");

        }
    }

    public String BusquedaporProveedor() {
        String Valorretorno = "";
        if (!buscarproveedor.getText().trim().isEmpty()) {
            Valorretorno = "where proveedor like '%" + buscarproveedor.getText() + "%'";
        }
        return Valorretorno;
    }

    public void llenar() {
        list.clear();
        tablaproveedor.getItems().clear();
        ResultSet st = a.tablas("SELECT  [ide],[proveedor],[ruc],[correo],[telefono],[direccion] FROM [BDAirnet].[dbo].[tproveedordj] " + BusquedaporProveedor());
        try {

            while (st.next()) {
                proveedorOB.add(new datosproveerdor(st.getString("ide"), st.getString("proveedor"), st.getString("ruc"), st.getString("correo"), st.getString("telefono"), st.getString("direccion")));
                list.add(st.getString("proveedor"));
            }
        } catch (SQLException exception) {
            a.notificaciones("Se produjo un error de nivel : " + exception.getMessage(), "");

        }
        a.prediccion(buscarproveedor, list);
    }

    public void cargarproducto() throws SQLException {

        String con = "";
        if (buscarpro.getText().replace(" ", "").isEmpty()) {
            con = "SELECT * FROM [BDAirnet].[dbo].[tvproductosdj]  where convert(date,[ingreso]) between convert(date,'" + inicioreporte.getValue() + "')  and  convert(date,'" + finbusqueda.getValue() + "') ";
        } else {
            con = "SELECT * FROM [BDAirnet].[dbo].[tvproductosdj]  where pon like '%" + buscarpro.getText() + "%' ";
        }
        tablaproducto.getItems().clear();
        ResultSet di = a.tablas(con);
        while (di.next()) {
            productos.add(new produ(di.getString("id"), di.getString("producto"), di.getString("proveedor"),
                    di.getString("serie"), di.getString("macid"), di.getString("pon"), di.getString("precio"),
                    di.getString("cliente"), di.getString("ingreso"), di.getString("fechaactiva"), di.getString("ip"),
                    di.getString("activa"), di.getString("lote"), di.getString("metraje"), di.getString("estado")));
        }
        if (tablaproducto.getItems().isEmpty()) {
            tablaproducto.setPlaceholder(new Label("No se ha Encontrado datos bajo esos parametros de busqueda"));
        }

    }

    public void valorescargaproducto() {
        tablaproducto.setItems(productos);
        idpructo.setCellValueFactory(new PropertyValueFactory<>("id"));
        productoont.setCellValueFactory(new PropertyValueFactory<>("producto"));
        proveedoront.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        serie.setCellValueFactory(new PropertyValueFactory<>("serie"));
        macid.setCellValueFactory(new PropertyValueFactory<>("macid"));
        pon.setCellValueFactory(new PropertyValueFactory<>("pon"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        ingreso.setCellValueFactory(new PropertyValueFactory<>("ingreso"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fechaactiva"));
        IP.setCellValueFactory(new PropertyValueFactory<>("ip"));
        activa.setCellValueFactory(new PropertyValueFactory<>("activa"));
        lote.setCellValueFactory(new PropertyValueFactory<>("lote"));
        metraje.setCellValueFactory(new PropertyValueFactory<>("metraje"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    public void ReporteServicioTecnicoImpre(String fechainicial, String FechaFinal) {
        //SELECT COUNT(*) as total  FROM [BDAirnet].[dbo].[tvproductosdj]  where (convert(date,[ingreso]) between convert(date,'01/01/2021')  and  convert(date,'03/03/2021') ) AND producto='ONT FIBRA OPTICA' and lote like'%LOT%'
        // SELECT count(*)as dañadas FROM [BDAirnet].[dbo].[tvproductosdj]  where (convert(date,[ingreso]) between convert(date,'01/01/2021')  and  convert(date,'03/03/2021') ) AND producto='ONT FIBRA OPTICA' and lote like'%LOT%'	and estado !='Activo' and estado !='Inactivo' and estado !='Activa'	
        String producto12 = seleccionarproducto.getSelectionModel().getSelectedItem();
        if (producto12 == null) {
            a.notificaciones("Seleccione el tipo de producto para realizar la busqueda", "I");
        } else {
            a.notificaciones("Recuerde Actualizar el rango de fechas de Busqueda", "I");
            int Total = 0, dañadas = 0;

            ResultSet total = a.tablas("SELECT COUNT(*) as total  FROM [BDAirnet].[dbo].[tvproductosdj]  where (convert(date,[ingreso]) between convert(date,'" + fechainicial + "')  and  convert(date,'" + FechaFinal + "') ) AND producto='ONT FIBRA OPTICA' and lote like'%LOT%'");
            ResultSet Dañadas = a.tablas("SELECT count(*)as dañadas FROM [BDAirnet].[dbo].[tvproductosdj]  where (convert(date,[ingreso]) between convert(date,'" + fechainicial + "')  and  convert(date,'" + FechaFinal + "') ) AND producto='ONT FIBRA OPTICA' and lote like'%LOT%' and estado !='Activo' and estado !='Inactivo' and estado !='Activa'");
            try {
                while (total.next()) {
                    Total = total.getInt("total");
                }
                while (Dañadas.next()) {
                    dañadas = Dañadas.getInt("dañadas");
                }
            } catch (SQLException ex) {
                Logger.getLogger(InventariosTaBController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String jr = null;
            String nombredoc = "Reporte" + fechainicial + FechaFinal;
            String valores = "Total: " + Total + " Defectuosas: " + dañadas + " Activas:" + (Total - dañadas);
            try {
                Map parametro = new HashMap();
                parametro.put("CONDICION", seleccionarproducto.getSelectionModel().getSelectedItem());
                parametro.put("fechainical", fechainicial);
                parametro.put("fechafinal", FechaFinal);
                parametro.put("EVENTOS", valores);
                a.CargarPropiedades();
                jr = a.properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\ReporteONT.jasper";
                String printFileName;
                printFileName = JasperFillManager.fillReportToFile(jr, parametro, a.cone());
                pdf1(printFileName, nombredoc.replace(" ", ""));
            } catch (SQLServerException | JRException | IOException ex) {
                a.notificaciones("Error de lectura " + ex.getMessage(), jr);
            }
        }
    }

    public void pdf1(String printFileName, String numero) throws IOException {
        a.CargarPropiedades();
        try {

            if (printFileName != null) {
                JasperExportManager.exportReportToPdfFile(printFileName,
                        a.properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\" + numero + ".pdf");
                File objetofile = new File(a.properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\" + numero + ".pdf");

                Desktop.getDesktop().open(objetofile);
            }
        } catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }
}
