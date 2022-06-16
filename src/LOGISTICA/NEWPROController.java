/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOGISTICA;

import CLASES.bas;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class NEWPROController implements Initializable {
    Calendar fecha1 = Calendar.getInstance();
     ResultSet cuantos;
    String [][] multiple=new String[30][2];
    boolean veri;
    int i=0,j=0;
     bas q = new bas();
    @FXML
    private Button guard;
    @FXML
    private Button cerr;
    @FXML
    private TextField mac;
    @FXML
    private ComboBox<String> cat;
    @FXML
    private ComboBox<String> pro;
    @FXML
    private TextField precio;
    @FXML
    private TextField serie;
    @FXML
    private TextField pon;
    @FXML
    private TextField Lote;
    @FXML
    private TextField metraje;
    @FXML
    private CheckBox cheecknuevabobina;
    @FXML
    private CheckBox incgresomultiple;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        incgresomultiple.setOnAction((event) -> {
            if(incgresomultiple.isSelected()){
                cerr.setVisible(true);
                 guard.setVisible(false);
            }else {
                cerr.setVisible(false);
                guard.setVisible(true);
            }
        });
        cerr.setOnAction((event) -> {
            for(i=0;i<30;i++){
                      String consu="INSERT INTO [BDAirnet].[dbo].[tvproductosdj]([producto],[proveedor],[serie],[macid],[pon],[precio],[ingreso],[activa],[lote],[estado])VALUES(";
                                consu=consu+"'"+cat.getSelectionModel().getSelectedItem()+"','"+pro.getSelectionModel().getSelectedItem()+"','"+ multiple[i][0]+"','"+ multiple[i][1]+ "','"+multiple[i][0]+"','"+precio.getText()+"',"+"GETDATE()"+",'"+"NO"+"','"+Lote.getText().replace("'", "-")+"','"+"Inactivo')";
                                q.InsertInsertar(consu);
            }
             Node source = (Node) event.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            q.notificaciones("Exito al Guardar Datos", "I");
        });
        serie.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
              verificar(serie.getText().trim());
            
                 mac.requestFocus();
              
            }
        }
    });
        mac.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
               multiple[i][0]=serie.getText();
            multiple[i][1]=mac.getText();
           serie.requestFocus();
            if(incgresomultiple.isSelected()){
                serie.setText("");
            mac.setText("");
            q.notificaciones("Producto N°"+(i+1), "I");
            i=i+1;
            
            if(i==29)
            q.notificaciones("Ingrese todos los campos estes el ultimo de la serie", "I");
            }
            
            }
        }
    });
        
        
        incgresomultiple.setOnAction((event) -> {
            if(incgresomultiple.isSelected()){
                guard.setDisable(true);
                cerr.setText("Guardar Todo");
            }else {
                guard.setDisable(false);
            }
        });
        precio.setText("75");
        pon.setDisable(true);
        serie.textProperty().addListener((obs, oldText, newText) -> {
          /*  verificar(newText);
            if(veri){
                 pon.setText(newText);
            }else {
                 q.notificaciones("Serie ya Existente", "I");
            }
             
               */
            });
        Lote.textProperty().addListener((obs, oldText, newText) -> {
              Lote.setText(newText.replace("'", "-"));
               
            });
        // TODO
         metraje.setDisable(true);
         try {
            // TODO
            valores();
        } catch (SQLException ex) {
            Logger.getLogger(LOGISTICA.NEWPROController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       guard.setOnAction((event) -> {
           try{
              guardar();
           Node source = (Node) event.getSource();
            Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
            q.notificaciones("Exito al Guardar Datos", "I");
   
           }catch(Exception e){
               
           }
           
       });
       cheecknuevabobina.setOnMouseClicked((event) -> {
           if(cheecknuevabobina.isSelected()){
               serie.setDisable(false);
               pon.setDisable(true);
               mac.setDisable(true);
               metraje.setDisable(false);
           }else {
                metraje.setDisable(true );
                serie.setDisable(false);
               pon.setDisable(false);
               mac.setDisable(false);
           }
       });
    } 
    public void valores () throws SQLException{
     
        ResultSet sd = q.tablas("SELECT  [producto] FROM [BDAirnet].[dbo].[tpcategoriadj]");
        while (sd.next()){
            cat.getItems().addAll(sd.getString("producto"));
        }
         ResultSet sd1 = q.tablas("SELECT  * FROM [BDAirnet].[dbo].[tproveedordj]");
         while (sd1.next()){
            pro.getItems().addAll(sd1.getString("proveedor"));
        }
    }
     public void guardar(){
         DateFormat f=new SimpleDateFormat("dd/MM/yyyy");
         // String fecha21=f.format(fecha1.getTime());
   
          if (cheecknuevabobina.isSelected()){
  
              if(!cat.getSelectionModel().isEmpty() && !pro.getSelectionModel().isEmpty()){
              if(!metraje.getText().isEmpty()){
  
                  if(!Lote.getText().isEmpty()){
                      String consu1="INSERT INTO [BDAirnet].[dbo].[tvproductosdj]([producto],[proveedor],[precio],[lote],[metraje],[ingreso],[estado],[serie])VALUES(";
                        consu1=consu1+"'"+cat.getSelectionModel().getSelectedItem()+"','"+pro.getSelectionModel().getSelectedItem()+"','"+precio.getText()+"','"+Lote.getText()+"','"+metraje.getText()+"',"+"getdate()"+",'"+"Inactivo','"+serie.getText()+"')";
                q.InsertInsertar(consu1);
                  }else {
                      q.notificaciones("Campo de Lote vacio", "");
                     
                  }
                  
              }else {
                   q.notificaciones("Campo de Metraje vacio", "");
                      
                  }
              }else {
                   q.notificaciones("Error al seleccionar Categoria o/y Proveedor", "");
                    
                  } 
              }else{
              if(!cat.getSelectionModel().isEmpty() && !pro.getSelectionModel().isEmpty()){
                  System.out.println("5");
              if(!serie.getText().isEmpty()){
                  if(!mac.getText().isEmpty()){
                      if(!pon.getText().isEmpty()){
                          if(!Lote.getText().isEmpty()){
                              String consu="INSERT INTO [BDAirnet].[dbo].[tvproductosdj]([producto],[proveedor],[serie],[macid],[pon],[precio],[ingreso],[activa],[lote],[estado])VALUES(";
                                consu=consu+"'"+cat.getSelectionModel().getSelectedItem()+"','"+pro.getSelectionModel().getSelectedItem()+"','"+serie.getText()+"','"+mac.getText()+ "','"+pon.getText()+"','"+precio.getText()+"',"+"GETDATE()"+",'"+"NO"+"','"+Lote.getText().replace("'", "-")+"','"+"Inactivo')";
                              
                                 q.InsertInsertar(consu);}else 
                           { }}
                      else {
                           q.notificaciones("Campo de Pon vacio", "");
                  }
                      }else {
                       q.notificaciones("Campo de MAC vacio", "");
                  }
                  }
              else {
                   q.notificaciones("Campo de Serie Vacio", "");
                     
                  }
              }else {
                   q.notificaciones("Error al seleccionar Categoria o/y Proveedor", "");
                     
                  }
          } 
     }
     
    /* public boolean  verificar(String texto){
         
       cuantos= q.tablas("SELECT COUNT (serie) AS VALOR FROM [BDAirnet].[dbo].[tvproductosdj] WHERE serie='"+texto+"'");
        try {
            while (cuantos.next()){
                if(cuantos.getInt("VALOR")>0){
                    guard.setDisable(true);
                     q.notificaciones("Serie ya Existente", "I");
                    
                    return false;
                }else 
                    return  true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NEWPROController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
     }*/
    public synchronized  void verificar(String texto) {
        
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                
   // aqui va todo el proceso a ejecutarse 
   cuantos= q.tablas("SELECT COUNT (serie) AS VALOR FROM [BDAirnet].[dbo].[tvproductosdj] WHERE serie='"+texto+"'");
        try {
            while (cuantos.next()){
                if(cuantos.getInt("VALOR")>0){
                    guard.setDisable(true);
                    
                    veri=false;
                  
                    
                    
                }else 
                     
                    veri=true;
                   
            }
        } catch (SQLException ex) {
            Logger.getLogger(NEWPROController.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
          
            }
        };
      task.setOnSucceeded(event -> {
         // aqui va que hacer cuando ya acabe de ejecutarse el hilo
         if(veri){
            pon.setText(serie.getText().trim()); 
                 
            }else {
                q.notificaciones("La serie del articulo ya esta registrada", "I");
            }
    });
    Thread t = new Thread(task);
    t.setDaemon(true);
    t.start();
    }
}
