/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import LOGISTICA.InventariosTaBController;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import CLASES.bas;
import javafxapplication4.produ;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class Ventana_AUXReportONTController implements Initializable {
 bas a = new  bas() ;
 ObservableList<produ> productos = FXCollections.observableArrayList();
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
    private Button BuscarFECHAS;
    @FXML
    private Button reporte;
    @FXML
    private Button adigmaterial;
    @FXML
    private DatePicker inicioreporte;
    @FXML
    private DatePicker finbusqueda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        valorescargaproducto();
        
        reporte.setOnAction((event) -> {
            ReporteServicioTecnicoImpre(inicioreporte.getValue().toString(),finbusqueda.getValue().toString());
        });
        try {
             // TODO
             cargarproducto();
         } catch (SQLException ex) {
            
         }
         buscarpro.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
               try {
                 cargarproducto();
             } catch (SQLException ex) {
                
             }
            }
        }
    });
        inicioreporte.setValue(LocalDate.now());
        finbusqueda.setValue(LocalDate.now());
         adigmaterial.setOnAction((event) -> {
           a.ventanas("Asignaciondemateria.fxml");
           
        });
         BuscarFECHAS.setOnAction((event) -> {
             try {
                 cargarproducto();
             } catch (SQLException ex) {
                
             }
         }); 
    }    
 public void cargarproducto() throws SQLException{
      
         
       String con="";
        if(buscarpro.getText().replace(" ", "").isEmpty()){
           con = "SELECT * FROM [BDAirnet].[dbo].[tvproductosdj]  where convert(date,[ingreso]) between convert(date,'"+inicioreporte.getValue()+"')  and  convert(date,'"+finbusqueda.getValue()+"') ";
          
        }else {
             con = "SELECT * FROM [BDAirnet].[dbo].[tvproductosdj]  where pon like '%"+buscarpro.getText()+"%' "; 
        }
         tablaproducto.getItems().clear();
         ResultSet di = a.tablas(con);
         while (di.next()){
              productos.add(new produ(di.getString("id"),di.getString("producto"),di.getString("proveedor")
                      ,di.getString("serie"),di.getString("macid"), di.getString("pon"),di.getString("precio")
                      ,di.getString("cliente"),di.getString("ingreso"),di.getString("fechaactiva"),di.getString("ip")
                      ,di.getString("activa"),di.getString("lote"),di.getString("metraje"),di.getString("estado")));
         }
         
    }
  public void valorescargaproducto(){
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
  public void ReporteServicioTecnicoImpre(String fechainicial,String FechaFinal){
     //SELECT COUNT(*) as total  FROM [BDAirnet].[dbo].[tvproductosdj]  where (convert(date,[ingreso]) between convert(date,'01/01/2021')  and  convert(date,'03/03/2021') ) AND producto='ONT FIBRA OPTICA' and lote like'%LOT%'
     // SELECT count(*)as dañadas FROM [BDAirnet].[dbo].[tvproductosdj]  where (convert(date,[ingreso]) between convert(date,'01/01/2021')  and  convert(date,'03/03/2021') ) AND producto='ONT FIBRA OPTICA' and lote like'%LOT%'	and estado !='Activo' and estado !='Inactivo' and estado !='Activa'	
     int Total=0,dañadas=0;  
     bas q = new bas();
        ResultSet total =q.tablas("SELECT COUNT(*) as total  FROM [BDAirnet].[dbo].[tvproductosdj]  where (convert(date,[ingreso]) between convert(date,'"+fechainicial+"')  and  convert(date,'"+FechaFinal+"') ) AND producto='ONT FIBRA OPTICA' and lote like'%LOT%'");
        ResultSet Dañadas=q.tablas("SELECT count(*)as dañadas FROM [BDAirnet].[dbo].[tvproductosdj]  where (convert(date,[ingreso]) between convert(date,'"+fechainicial+"')  and  convert(date,'"+FechaFinal+"') ) AND producto='ONT FIBRA OPTICA' and lote like'%LOT%' and estado !='Activo' and estado !='Inactivo' and estado !='Activa'");
          try {
              while(total.next()){
                Total=total.getInt("total");
              } 
          while(Dañadas.next()){
              dañadas=Dañadas.getInt("dañadas");
          }
          } catch (SQLException ex) {
              Logger.getLogger(InventariosTaBController.class.getName()).log(Level.SEVERE, null, ex);
          }
        String jr = null;
        String nombredoc="Reporte"+fechainicial+FechaFinal;
        String valores="Total: "+Total+" Defectuosas: "+dañadas+" Activas:"+(Total-dañadas);
        try {
            Map parametro = new HashMap();
            parametro.put("CONDICION", "ONT FIBRA OPTICA");
            parametro.put("fechainical", fechainicial);
            parametro.put("fechafinal", FechaFinal);
             parametro.put("EVENTOS", valores);
         
            jr = "\\\\S-AIRCONTROL\\Nuevo Systema\\ArchivosdeImpresion\\ReporteONT.jasper";
            String printFileName;
            printFileName = JasperFillManager.fillReportToFile(jr, parametro, q.cone());
            pdf1(printFileName, nombredoc.replace(" ", ""));
        } catch (SQLServerException | JRException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
   public void pdf1(String printFileName, String numero)throws IOException {
        try {

            if (printFileName != null) {
                JasperExportManager.exportReportToPdfFile(printFileName,
                        "\\\\S-AIRCONTROL\\Nuevo Systema\\ArchivosdeImpresion\\" + numero + ".pdf");
                File objetofile = new File("\\\\S-AIRCONTROL\\Nuevo Systema\\ArchivosdeImpresion\\" + numero + ".pdf");

                Desktop.getDesktop().open(objetofile);
            }
        } catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void nuevoont(ActionEvent event) {
         a.ventanas("NEWPRO.fxml");
    }
    
}
