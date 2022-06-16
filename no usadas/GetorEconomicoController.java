/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.concurrent.Task;
/**
 * FXML Controller class
 *
  * @author cl
 */
public class GetorEconomicoController implements Initializable {
    Calendar fecha1 = Calendar.getInstance();
    double vt2=0;
    Task copyWorker;
    bas q = new bas();
    ObservableList<cuentasporbanco> facturaspordepo = FXCollections.observableArrayList();
    ObservableList<FacturasCobradas> facturascobradasvi = FXCollections.observableArrayList();
    ObservableList<FacturasporCobrado> facturasporcobrarvi = FXCollections.observableArrayList();
    ObservableList<Eventos> Eventosvi = FXCollections.observableArrayList();
    @FXML
    private DatePicker iniciodebusqueda ;
    @FXML
    private DatePicker finaldebusqueda;
    @FXML
    private Label facturascobradas;
    @FXML
    private TableView tablafactura;
    @FXML
    private TableColumn<String, FacturasCobradas> cliente;
    @FXML
    private TableColumn<String, FacturasCobradas> fecha;
    @FXML
    private TableColumn<String, FacturasCobradas>  valor;
    @FXML
    private Label totaldeingreso;
    @FXML
    private Label totalegreso;
    @FXML
    private Label totalactual;
    @FXML
    private Label instalacionesefectuadas;
    @FXML
    private Label cuentasporcobrartotal;
    @FXML
    private Label eventosrealizados;
    @FXML
    private TableView  eventos;
    @FXML
    private TableColumn<String, Eventos> clienteeve;
    @FXML
    private TableColumn<String, Eventos> causa;
    @FXML
    private TableColumn<String, Eventos>estado;
 
    @FXML
    private Label facturasporcobrar;
     @FXML
    private TableView  facturasporcobrartabla;
    @FXML
    private TableColumn<String, FacturasporCobrado> clientefactxcobrar;
    @FXML
    private TableColumn<String, FacturasporCobrado> fechaporcobrar;
    @FXML
    private TableColumn<String, FacturasporCobrado> valorporcobrar;
   final ToggleGroup group = new ToggleGroup();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @FXML
    private Button mostrar;
    @FXML
    private TableView facturasdeposito;
    @FXML
    private TableColumn<String, cuentasporbanco>clientedepsoito;
    @FXML
    private TableColumn<String, cuentasporbanco>fechadeposito;
    @FXML
    private TableColumn<String, cuentasporbanco> valordeposito;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
         copyWorker = createWorker();
         new Thread(copyWorker).start();
        cuentasporcobrartotal.textProperty().bind(copyWorker.messageProperty());*/
        try {
            // TODO
             DateFormat f=new SimpleDateFormat("dd/MM/yyyy");
           String fechinicio=f.format(fecha1.getTime());
            String fechafinal=f.format(fecha1.getTime());
            cargarvalores(fechinicio,fechafinal);
        } catch (SQLException ex) {
            Logger.getLogger(GetorEconomicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrar.setOnAction((event) -> { 
           
           LocalDate value2 =  iniciodebusqueda.getValue();
           String inicio = String.valueOf(value2.format(formatter));
           LocalDate value3 = finaldebusqueda.getValue();
           String fin = String.valueOf(value3.format(formatter));
            try {
                cargarvalores(inicio, fin);
            } catch (SQLException ex) {
                Logger.getLogger(GetorEconomicoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    } 
    public void cargarvalores(String fechainicio,String fechafinal) throws SQLException{
        
        tablafactura.setItems(facturascobradasvi);
        cliente.setCellValueFactory(new PropertyValueFactory<>("Clientecobrado"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fechacobrada"));
        valor.setCellValueFactory(new PropertyValueFactory<>("Valorcobrado"));
        DecimalFormat formato1 = new DecimalFormat("#.00");
        facturasdeposito.setItems(facturaspordepo);
        clientedepsoito.setCellValueFactory(new PropertyValueFactory<>("Clientecobrado"));
        fechadeposito.setCellValueFactory(new PropertyValueFactory<>("fechacobrada"));
        valordeposito.setCellValueFactory(new PropertyValueFactory<>("Valorcobrado"));
        //*****************************
        tablafactura.getItems().clear();
        facturasdeposito.getItems().clear();
       
        String consultafacturacobradas="SELECT [nuemrofactura],[cliente],[fechadepago]  FROM [BDAirnet].[dbo].[tvdjFacurascliente] where  formapago='Efectivo' and estado='INACTIVA' and fechadepago between '"+fechainicio+"' and '"+fechafinal+"'";
        facturascobradas.setText(canidad("FROM [BDAirnet].[dbo].[tvdjFacurascliente] where formapago ='Efectivo' and estado='INACTIVA' and fechadepago between '"+fechainicio+"' and '"+fechafinal+"'"));
        ResultSet facturasc=q.tablas(consultafacturacobradas);
        
        while(facturasc.next()){
          //  if(facturasc.getFloat("total")<=0) {
              double valor=0.0;
            String cons="SELECT  * FROM [BDAirnet].[dbo].[detallesfacxtura] where numerofactura ='"+facturasc.getString("nuemrofactura").replace(" ","")+"'";
            ResultSet valortotal = q.tablas(cons);
            while(valortotal.next()){
            valor=valor+Double.parseDouble(valortotal.getString("valortotal").replace(" ","").replace(",","."));
              
            }
              facturascobradasvi.add(new FacturasCobradas(facturasc.getString("cliente"), facturasc.getString("fechadepago"),String.valueOf(formato1.format(valor)))); 
          //  }else {
         //  facturascobradasvi.add(new FacturasCobradas(facturasc.getString("cliente"), facturasc.getString("fechadepago"),facturasc.getString("total").replace(" ","").replace(",","."))); 
          //  }
        }
       
        //count(*)as fact
       
        
        
         ResultSet facturasdeposito=q.tablas("SELECT [nuemrofactura],[cliente],[fechadepago] FROM [BDAirnet].[dbo].[tvdjFacurascliente] where  formapago='Otros' and estado='INACTIVA' and fechadepago between '"+fechainicio+"' and '"+fechafinal+"'");
      
        while(facturasdeposito.next()){
          //   if(facturasc.getFloat("total")<=0) {
                double valor=0.0;
             
            String cons="SELECT  * FROM [BDAirnet].[dbo].[detallesfacxtura] where numerofactura ='"+facturasdeposito.getString("nuemrofactura").replace(" ","")+"'";
            ResultSet valortotal = q.tablas(cons);
            while(valortotal.next()){
            valor=valor+Double.parseDouble(valortotal.getString("valortotal").replace(" ","").replace(",","."));
              
            } 
             facturaspordepo.add(new cuentasporbanco(facturasdeposito.getString("cliente"), facturasdeposito.getString("fechadepago"),String.valueOf(formato1.format(valor))));
         //    }else {
          //     facturaspordepo.add(new cuentasporbanco(facturasdeposito.getString("cliente"), facturasdeposito.getString("fechadepago"),facturasdeposito.getString("total")));  
          //   }
                 
          
          }
        double vt=0;
        for (int i = 0; i < tablafactura.getItems().size(); i++)
       {vt=vt+Double.parseDouble(String.valueOf(valor.getCellObservableValue(i).getValue()).replace(",","."));  }
         totaldeingreso.setText(String.valueOf(formato1.format(vt)).replace(",", "."));
        
        eventos(" fechasalida between ' "+fechainicio+ " 00:00:00:000' and '"+fechafinal+"  23:59:00:000'");
          ResultSet totae= q.tablas("SELECT sum(cantidad) as suma FROM [BDAirnet].[dbo].[tvdjSalidadedinero] where fechasalida between '"+fechainicio+" 00:00:00:000' and '"+fechafinal+" 23:59:59:000'");
         while(totae.next()){
             if(totae.getFloat("suma")<=0)
             {
                  totalegreso.setText("0.0");
             }else {
                 totalegreso.setText(totae.getString("suma"));
             }
         }
         facturasporcobrar.setText(canidad("FROM [BDAirnet].[dbo].[tvdjFacurascliente] where estado='ACTIVA'"));
         instalacionesefectuadas.setText(canidad("FROM [BDAirnet].[dbo].[TBCLIENTE] where FECHAINGRESO between '"+fechainicio+" 00:00:00:000'and '"+fechafinal+" 23:59:59:000'"));
         totalactual.setText(String.valueOf(formato1.format(Double.parseDouble(totaldeingreso.getText().replace(",","."))-Double.parseDouble(totalegreso.getText().replace(",", ".")))));
           
        
    }
    public String canidad(String tablacondicion){
        
        String can="";
        ResultSet cantidad = q.tablas("select count(*)as fact "+tablacondicion);
        try {
            while(cantidad.next()){
                can=cantidad.getString("fact");
            }
            return can;
        } catch (SQLException ex) {
            return "0";
        }
    }
    public void eventos(String condicion){
   
        String consultaeventos="SELECT [solitadopor],[motivo],[cantidad] FROM [BDAirnet].[dbo].[tvdjSalidadedinero] where "+condicion ;
        eventos.setItems(Eventosvi);
        clienteeve.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        causa.setCellValueFactory(new PropertyValueFactory<>("causa"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        eventos.getItems().clear();
       
        ResultSet eventos=q.tablas(consultaeventos);
        try {
            while(eventos.next()){
                Eventosvi.add(new Eventos(eventos.getString("solitadopor"), eventos.getString("motivo"), eventos.getString("cantidad")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetorEconomicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
public Task createWorker() {
        return new Task() {
            @Override
    protected Object call() throws Exception {
       
         facturasporcobrartabla.setItems(facturasporcobrarvi);
        clientefactxcobrar.setCellValueFactory(new PropertyValueFactory<>("clienteporcobrar"));
        fechaporcobrar.setCellValueFactory(new PropertyValueFactory<>("Fechaporcobrar"));
        valorporcobrar.setCellValueFactory(new PropertyValueFactory<>("Valorporcobrar"));
         DecimalFormat formato1 = new DecimalFormat("#.00");
        String consultasfacturasporcobrar="SELECT [nuemrofactura],[cliente],[fechagenerada] FROM [BDAirnet].[dbo].[tvdjFacurascliente] where estado='ACTIVA' ";
        
         ResultSet facturasporc =q.tablas(consultasfacturasporcobrar);
         while(facturasporc.next()){
            double valor=0.0;
            String cons="SELECT  [valortotal] FROM [BDAirnet].[dbo].[detallesfacxtura] where numerofactura ='"+facturasporc.getString("nuemrofactura").replace(" ","")+"'";
            ResultSet valortotal = q.tablas(cons);
            while(valortotal.next()){
            valor=valor+Double.parseDouble(valortotal.getString("valortotal").replace(" ","").replace(",","."));
             
            }
            vt2=vt2+valor;
             facturasporcobrarvi.add(new FacturasporCobrado(facturasporc.getString("cliente"),facturasporc.getString("fechagenerada"),String.valueOf(formato1.format(valor))));
              updateMessage(formato1.format(vt2));
        }
         
     
     
      
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        };
    }
    
    
}
