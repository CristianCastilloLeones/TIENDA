/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAJA;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Modality;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import CLASES.Clase_RecepciondePago;
import CLASES.LevenshteinDistance;
import SOPORTE.NUEVOTICKETController;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;
import CLASES.recepcionpago;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class MODULODEFACTURARController implements Initializable {

    ObservableList<recepcionpago> clt = FXCollections.observableArrayList();
     VariablesDeUso onVariable = new VariablesDeUso();
     bas q = new bas();
    @FXML
    private TextField cedulatext;
    @FXML
    private TextField totalitem;
    @FXML
    private TextField pvptext;
    @FXML
    private TextField cantidad;
    @FXML
    private TableView tabladetalle;
    @FXML
    private TableColumn<String, recepcionpago>canti;
    @FXML
    private TableColumn<String, recepcionpago> detalle;
    @FXML
    private TableColumn<String, recepcionpago> pvp;
    @FXML
    private TableColumn<String, recepcionpago> pvpt;
    @FXML
    private TextField total;
    @FXML
    private TextField iva;
    @FXML
    private TextField subto;
    @FXML
    private TextField fechagenera;
    @FXML
    private TextField detalle1;
    @FXML
    private TextField nombrecliente;
    @FXML
    private Button GUARDAR;
    @FXML
    private ComboBox idcuenta;
    @FXML
    private Label coloractiva;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private Label Direccion;
    @FXML
    private CheckBox libre;
    @FXML
    private Button buscarlista;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // TODO
      buscarlista.setOnAction((event) -> {
          q.ventanasAxu("/CAJA/Listado_Clientes_General.fxml","Lista de Facturacion");
      });
        idcuenta.setDisable(true);
        idcuenta.setOnAction((event) -> {
            buscarValorMensual();
            Historial();
        });
        
        q.listaclient(onVariable.getListadeClientes());
        q.prediccion(nombrecliente, onVariable.getListadeClientes());
        
         tabladetalle.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        GUARDAR.setOnAction((event) -> {
            guardaritem();
        });
        
        
        
        
        fechagenera.setText(q.FechaformatoCalendaerio());
        tabladetalle.setItems(clt);

        canti.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        detalle.setCellValueFactory(new PropertyValueFactory<>("Detalle"));
        pvp.setCellValueFactory(new PropertyValueFactory<>("pvp"));
        pvpt.setCellValueFactory(new PropertyValueFactory<>("pvpt"));
       
         nombrecliente.setOnKeyPressed((KeyEvent event) -> {
            if (!nombrecliente.getText().replace(" ", "").isEmpty()) {
                if (event.getCode() == KeyCode.ENTER) {
                    idcuenta.setDisable(true);
                    cedulatext.setText(q.BusquedaCedula(nombrecliente.getText()));
                    detalle1.requestFocus();
                    idS();
                }
            }
        });
        
        
         q.prediccion(detalle1, onVariable.items());
          
         pvptext.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.isEmpty()) {

                if (!cantidad.getText().isEmpty()) {
                     this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(pvptext.getText())*q.ConvertidorStringaDouble(cantidad.getText())));
                } else {
                    cantidad.setText("1");
                      this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(pvptext.getText())*q.ConvertidorStringaDouble(cantidad.getText())));
                }
            }
        });
        pvptext.setOnKeyPressed((KeyEvent event) -> {
            if (!pvptext.isDisable()) {
                if (event.getCode() == KeyCode.ENTER) {

                    {
                        
                       this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(pvptext.getText())*q.ConvertidorStringaDouble(cantidad.getText())));
                       Guardary_Borrar_Casillero_Tabla();
                    }
                }
            }else if (event.getCode() == KeyCode.F4) {
                     guardaritem();
                }
        });
        cantidad.setOnKeyPressed((KeyEvent event) -> {
           
                if (event.getCode() == KeyCode.ENTER) {
                         pvptext.requestFocus();
                    if (!cantidad.getText().isEmpty()) {
                       this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(pvptext.getText())*q.ConvertidorStringaDouble(cantidad.getText())));
                    } else {
                        cantidad.setText("1");
                       this.totalitem.setText(q.FormatoDecimal(q.ConvertidorStringaDouble(pvptext.getText())*q.ConvertidorStringaDouble(cantidad.getText())));
                    }
                   
                }else if (event.getCode() == KeyCode.F4) {
                     guardaritem();
                } });
        
        
        
         detalle1.setOnKeyPressed((KeyEvent event) -> {

            if (event.getCode() == KeyCode.ENTER) {
                if (onVariable.getList().contains(detalle1.getText()) || libre .isSelected()) {
                        BuscarFacturacionExistente();
                   
                    cantidad.requestFocus();
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

                        this.totalitem.setText(q.FormatoDecimal(Double.parseDouble(pvptext.getText()) * Double.parseDouble(cantidad.getText())));
                    } else {
                        cantidad.setText("1");

                        this.totalitem.setText(q.FormatoDecimal(Double.parseDouble(pvptext.getText()) * Double.parseDouble(cantidad.getText())));
                    }
                } else {
                    
                    q.notificaciones("Debe seleccionar un Item de la lista predeterminada", "I");
                }
            }else if (event.getCode() == KeyCode.F4) {
                     guardaritem();
                }
        });
         
    }    
    
    public void Guardary_Borrar_Casillero_Tabla(){
        if(!cantidad.getText().isEmpty() && !detalle1.getText().trim().isEmpty() && !pvptext.getText().isEmpty() && !totalitem.getText().isEmpty() ){
            clt.add(new recepcionpago(cantidad.getText(), detalle1.getText().trim(), pvptext.getText(), totalitem.getText()));
        tabladetalle.refresh();
        SumasdeValoresTabladetalle();
        detalle1.setText("");
        cantidad.setText("");
        pvptext.setText("");
        totalitem.setText("");
        detalle1.requestFocus();
        }else {
            q.notificaciones("Campos vacios, Complete los valores para continuar", "I");
        }
        
    }
    public void guardaritem() {
    
        if(!tabladetalle.getItems().isEmpty()){
            for (int i=0;i<tabladetalle.getItems().size();i++){
            q.Facturar(nombrecliente.getText(),
                    String.valueOf(pvp.getCellObservableValue(i).getValue()), 
                    String.valueOf(detalle.getCellObservableValue(i).getValue()), 
                    1, cedulatext.getText().trim(), 
                    String.valueOf(canti.getCellObservableValue(i).getValue()), "E"); 
        }
       
        detalle1.setText("");
        cantidad.setText("");
        pvptext.setText("");
        totalitem.setText("");
        idcuenta.setDisable(true);
        nombrecliente.requestFocus();
        nombrecliente.setText("");
        cedulatext.setText("");
         total.setText("0");
        subto.setText("0");
        iva.setText("0");
        clt.clear();
        idcuenta.getItems().clear();
        }else {
            q.notificaciones("Tabla vacia no hubo cambios", "I");
        }
      
        
        

    }
    public void SumasdeValoresTabladetalle(){
        onVariable.Encerar();
        for (int i = 0; i < tabladetalle.getItems().size(); i++) {
            onVariable.setValorTotal(q.ConvertidorStringaDouble(String.valueOf(pvpt.getCellObservableValue(i).getValue())));
            }
        total.setText(q.FormatoDecimal(onVariable.getValorTotal() ));
        subto.setText(q.FormatoDecimal(( onVariable.getValorTotal() / 1.12)));
        iva.setText(q.FormatoDecimal((onVariable.getValorTotal() * 12) / 100));
    }

   /* public void alerta(){
         val alert = new Alert(AlertType.CONFIRMATION);
    }*/
    int nctas=0;
    public void idS()
    {
        idcuenta.getItems().clear();
        q.consulta = q.tablas("SELECT IDCLIENTE FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] WHERE IDENTIFICACION = '" + cedulatext.getText().trim() + "'");
        {
            try {
                while (q.consulta.next()) {
                    idcuenta.getItems().addAll(q.consulta.getString("IDCLIENTE"));
                    nctas++;
                }
                if(nctas>1){
                   idcuenta.setDisable(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(NUEVOTICKETController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        idcuenta.getSelectionModel().selectFirst();
       buscarValorMensual();
     ///  Historial();
    }
    
    public void buscarValorMensual(){
        q.auxConsulta = q.tablas("select ESTADO , VALORMENSUAL from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG]  where IDCLIENTE="+idcuenta.getSelectionModel().getSelectedItem());
         try {
             while(q.auxConsulta.next()){
               if(q.auxConsulta.getString("ESTADO").contains("1")){
                        coloractiva.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                         pvptext.setText(q.auxConsulta.getString("VALORMENSUAL"));
                    }else {
                       coloractiva.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY))); 
                       Alestas("Posee la Cuenta N° :"+idcuenta.getSelectionModel().getSelectedItem()+" Inhabilita ");
                    }
                   
             }} catch (SQLException ex) {
             Logger.getLogger(MODULODEFACTURARController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
   
    public void BuscarFacturacionExistente(){
        q.auxConsulta = q.tablas(" select detalle,QUIENGENERA,FECHADEORIGEN from [BDAirnet].[dbo].[detallesfacxtura] where codigo ='"+cedulatext.getText()+"'");
         try {
             while(q.auxConsulta.next()){
                 if( Verificador(detalle1.getText().replace(" ", "").toUpperCase(), q.auxConsulta.getString("detalle").replace(" ", "").toUpperCase())){
                     Alestas(" Ya cuenta con Item "+q.auxConsulta.getString("detalle")+
                             "\n Generado por : "+q.auxConsulta.getString("QUIENGENERA")+
                             "\n en Fecha : "+q.auxConsulta.getString("FECHADEORIGEN"));
                 } else {
                     System.out.println(q.auxConsulta.getString("detalle").replace(" ", "").toUpperCase());
                 }}
         } catch (SQLException ex) {
             Logger.getLogger(MODULODEFACTURARController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
        Alert alert;
   LevenshteinDistance ld = new LevenshteinDistance();
    public void Alestas(String MS){
        alert = new Alert(AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
	alert.setTitle("AVISO DE FACTURACION");
	alert.setHeaderText("El CLIENTE "+nombrecliente.getText()+" :");
	alert.setContentText(MS);
        alert.showAndWait();
	
    }
    
    public  boolean Verificador(String valor1, String valor2) {
        ld.setWords(valor1, valor2);
        ld.getDistancia();
        if((ld.getAfinidad() * 100) >= 65) {
            return true;
            }else {
            return  false;
        }

        

    }
    String cd="";
    public void Historial(){
        cd="";
        Clase_RecepciondePago obj = new Clase_RecepciondePago(cedulatext.getText().replace(" ", ""), "");
        if(obj.Valordeudante()>0){
            q.auxConsulta=obj.detalle();
            try {
                //SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad
                while(q.auxConsulta.next()){
                    cd=cd+q.auxConsulta.getString("detalle").trim()+" "+q.auxConsulta.getString("valortotal").trim()+"\n";
                }
            } catch (SQLException ex) {
                Logger.getLogger(MODULODEFACTURARController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Alestas( "Posee una deuda Total de :"+obj.Valordeudante()+"\n"
            +"Detalle : \n"
            +cd +"\n"+
            "Estado Conexión : "+EstadoMiK());
        }
    }
    
    public String EstadoMiK(){
        
       if(q.VerificadorEstadoMicroGeneradorFactura(idcuenta.getSelectionModel().getSelectedItem().toString())){
           return "INACTIVO";
       }else {
           return "ACTIVO";
       }
    }
}
