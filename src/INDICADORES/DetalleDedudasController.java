/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import CLASES.Clase_Estado_Individual;
import CLASES.FacturasporCobrado;
import CLASES.FacturasporCobrarIndivualHilo;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class DetalleDedudasController implements Initializable {
    bas q = new bas();
    @FXML
    private Label NOMBREDEUSURAIO;
    @FXML
    private TableView facturasporcobrartablacliente;
    @FXML
    private TableColumn<String, FacturasporCobrado>  clienteporcobrar;
    @FXML
    private TableColumn<String, FacturasporCobrado>  Valorporcobrar;
    @FXML
    private Label TOTALDEDUDADETALLE;
    @FXML
    private Button anulartodo;
    @FXML
    private Button dardebaja;
    @FXML
    private TextField valoaregistrar;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dardebaja.setOnAction((event) -> {
            porvalor();
        });
        anulartodo.setOnAction((event) -> {
             Eliminaritem();
        });
        valoaregistrar.setOnKeyTyped((event) -> {
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
        
        NOMBREDEUSURAIO.setText(Clase_Estado_Individual.clientetext);
        clienteporcobrar.setCellValueFactory(new PropertyValueFactory<>("clienteporcobrar"));
        Valorporcobrar.setCellValueFactory(new PropertyValueFactory<>("Valorporcobrar"));
        Facturas_x_Cobrar();
        ContextMenu context = new ContextMenu();
        MenuItem Item1 = new MenuItem("Dar de Baja Item");
         MenuItem Item2 = new MenuItem("Dar de Baja Todos los Items");
           context.getItems().addAll(Item1,Item2);
        Item1.setOnAction((event) -> {
             valor= String.valueOf(clienteporcobrar.getCellObservableValue(facturasporcobrartablacliente.getSelectionModel().getSelectedIndex()).getValue());
             VALOR2=String.valueOf(Valorporcobrar.getCellObservableValue(facturasporcobrartablacliente.getSelectionModel().getSelectedIndex()).getValue());
             ob1 = new Clase_Estado_Individual(NOMBREDEUSURAIO.getText()+"-"+valor+"-"+VALOR2);
            ob1.cargarvalores2("E");
  
        });
        Item2.setOnAction((event) -> {
            
                Eliminaritem();
        });
      
        facturasporcobrartablacliente.setContextMenu(context);
    }    
    
    String valor,VALOR2;
    
    VariablesDeUso ob;
     Clase_Estado_Individual ob1;
    public void Facturas_x_Cobrar() {
        ob=new VariablesDeUso();
        ob.Encerar();
        facturasporcobrartablacliente.getItems().clear();
       FacturasporCobrarIndivualHilo task = new FacturasporCobrarIndivualHilo(NOMBREDEUSURAIO.getText());
       task.setOnSucceeded((succeededEvent) -> {
        facturasporcobrartablacliente.setItems((ObservableList) task.getValue());
        for (int i = 0; i < facturasporcobrartablacliente.getItems().size(); i++) {
         ob.setValorTotal(q.ConvertidorStringaDouble(String.valueOf(Valorporcobrar.getCellObservableValue(i).getValue())));
           
        }
        TOTALDEDUDADETALLE.setText(q.FormatoDecimal(ob.getValorTotal()));
        if(facturasporcobrartablacliente.getItems().isEmpty()){
                facturasporcobrartablacliente.setPlaceholder(new Label("El Cliente no presenta deudas"));
            }
        
        });
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(task);
    }
    
    public void Eliminaritem(){
        for(int i= 0 ;i<facturasporcobrartablacliente.getItems().size();i++){
             q.DeleteEliminar(" update  [BDAirnet].[dbo].[detallesfacxtura] set Estado=0,numerofactura='*****'"
                     + ",claveacceso='" + VariablesDeUso.nombreusuario.trim()
                     + "' where  detalle like '%"+String.valueOf(clienteporcobrar.getCellObservableValue(i).getValue())+"%' and codigo like '%"+q.BusquedaCedula(NOMBREDEUSURAIO.getText())+"%'");
        }
        Facturas_x_Cobrar();
           
    }
    public void porvalor(){
        if(!valoaregistrar.getText().isEmpty())
        {
             double aux = q.ConvertidorStringaDouble(valoaregistrar.getText());
            for(int i= 0 ;i<facturasporcobrartablacliente.getItems().size();i++){
            
               
               if(aux>=q.ConvertidorStringaDouble(String.valueOf(Valorporcobrar.getCellObservableValue(i).getValue()))){
                   q.DeleteEliminar(" update  [BDAirnet].[dbo].[detallesfacxtura] set Estado=0,numerofactura='*****'"
                     + ",claveacceso='" + VariablesDeUso.nombreusuario.trim()
                     + "' where  detalle like '%"
                     +String.valueOf(clienteporcobrar.getCellObservableValue(i).getValue())+
                     "%' and codigo like '%"+q.BusquedaCedula(NOMBREDEUSURAIO.getText())+"%'");
                   
                    q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjFacurascliente]\n"
                        + "([nuemrofactura],[cliente],[clave] ,[fechagenerada],[fechadepago],[formapago],[estado],[evidencia],[codigocontrato]\n"
                        + ",[SRI] ,[Envio]  ,[TipoFactu],[total])\n"
                        + "VALUES\n"
                        + "('" + "*****" + "','" + NOMBREDEUSURAIO.getText() + "','" + "****" + "','" + "****" + "','" + q.FechaformatoCalendaerio()+ "','" + "NINGUNO" + "','" + "INACTIVA" + "','" + "" + "','" + q.BusquedaCedula(NOMBREDEUSURAIO.getText()) + "','" + "NO" + "','" + "NO" + "','" + "NO" + "'," + aux + ")");
                   
                   
                   
                   
                aux=aux-q.ConvertidorStringaDouble(String.valueOf(Valorporcobrar.getCellObservableValue(i).getValue()));    
               }else if(aux>0 || String.valueOf(aux).contains("-")){
                   
                      q.UpdateModificar("UPDATE  [BDAirnet].[dbo].[detallesfacxtura]  SET numerofactura='" 
                              + "*****" 
                              + "',valortotal='" 
                              + aux
                              + "',valorunitario='" 
                              + aux 
                              + "' ,Estado=0 " + "' where  detalle like '%"
                     +String.valueOf(clienteporcobrar.getCellObservableValue(i).getValue())+
                     "%' and codigo like '%"+q.BusquedaCedula(NOMBREDEUSURAIO.getText())+"%'");
                      q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjFacurascliente]\n"
                        + "([nuemrofactura],[cliente],[clave] ,[fechagenerada],[fechadepago],[formapago],[estado],[evidencia],[codigocontrato]\n"
                        + ",[SRI] ,[Envio]  ,[TipoFactu],[total])\n"
                        + "VALUES\n"
                        + "('" + "*****" + "','" + NOMBREDEUSURAIO.getText() + "','" + "****" + "','" + "****" + "','" + q.FechaformatoCalendaerio()+ "','" + "NINGUNO" + "','" + "INACTIVA" + "','" + "" + "','" + q.BusquedaCedula(NOMBREDEUSURAIO.getText()) + "','" + "NO" + "','" + "NO" + "','" + "NO" + "'," + aux + ")");
                      aux =q.ConvertidorStringaDouble(String.valueOf(Valorporcobrar.getCellObservableValue(i).getValue()))-aux;
                        q.Facturar(NOMBREDEUSURAIO.getText()
                                , q.FormatoDecimal(aux)
                                , String.valueOf(clienteporcobrar.getCellObservableValue(i).getValue())
                                , 1, q.BusquedaCedula(NOMBREDEUSURAIO.getText()), "1", "E");
                        aux=0;
               }
                   
           
             
        }
             if(aux>0){
                  q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjAbonos] ([numerofactura] ,[cedula],[fecha],[estado],[valor]) VALUES('" + "***" + "','" + q.BusquedaCedula(NOMBREDEUSURAIO.getText()) + "' ," + "GETDATE()" + ",'" + "A" + "'," + aux + ")"); 
             
            q.InsertInsertar("INSERT INTO [BDAirnet].[dbo].[tvdjFacurascliente]\n"
                        + "([nuemrofactura],[cliente],[clave] ,[fechagenerada],[fechadepago],[formapago],[estado],[evidencia],[codigocontrato]\n"
                        + ",[SRI] ,[Envio]  ,[TipoFactu],[total])\n"
                        + "VALUES\n"
                        + "('" + "*****" + "','" + NOMBREDEUSURAIO.getText() + "','" + "****" + "','" + "****" + "','" + q.FechaformatoCalendaerio()+ "','" + "NINGUNO" + "','" + "INACTIVA" + "','" + "" + "','" + q.BusquedaCedula(NOMBREDEUSURAIO.getText()) + "','" + "NO" + "','" + "NO" + "','" + "NO" + "'," + aux + ")");
             }
           Facturas_x_Cobrar();  
        }else {
            q.notificaciones("Debe Ingresar un valor Correcto o Valido para continuar", "I");
        }
       
        
                     
        
    }
    
}
