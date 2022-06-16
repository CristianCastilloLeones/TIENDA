/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class AircontrolController implements Initializable {
    
    
     bas a = new  bas() ;
     Task copyWorker;
     public static String tipouuario="";
   // Image iconoprincipal = new Image(getClass().getResourceAsStream("/imagenes/bot2.png"));
    Image icon1 = new Image(getClass().getResourceAsStream("/imagenes/icons8-página-principal-48.png"));
    Image icon2= new Image(getClass().getResourceAsStream("/imagenes/icons8-tecnología-blockchain-48.png"));
    Image icon3 = new Image(getClass().getResourceAsStream("/imagenes/icons8-estrella-relleno-48.png"));
    Image icon4 = new Image(getClass().getResourceAsStream("/imagenes/icons8-connected-people-48.png"));
    Image icon5 = new Image(getClass().getResourceAsStream("/imagenes/icons8-sin-red-48.png"));
    Image icon6 = new Image(getClass().getResourceAsStream("/imagenes/icons8-mover-por-carretilla-48.png"));
    Image icon7 = new Image(getClass().getResourceAsStream("/imagenes/icons8-vieja-caja-registradora-48.png"));
    Image icon8 = new Image(getClass().getResourceAsStream("/imagenes/icons8-eye-checked-48.png"));
    Image icon9 = new Image(getClass().getResourceAsStream("/imagenes/icons8-contabilidad-48.png"));
    Image icon10 = new Image(getClass().getResourceAsStream("/imagenes/icons8-engranaje-48.png"));
    static List list = new ArrayList();
   
  //  public static String val;
   // public static String nombreusuario;

    @FXML
    private AnchorPane content;
    private Label tie;
    @FXML
    private TextField cliente3;
    @FXML
    private Label activos;
    @FXML
    private Label instalaciones;
    @FXML
    private Label reparaciones;   
    @FXML
    private Label retiros;
    @FXML
    private TreeView<String> arbol;
    @FXML
    private ImageView botonactualizar;
    @FXML
    private  Label usuario;
    @FXML
    private Label registrados;
    @FXML
    private Hyperlink vertodtasinstalaciones;
    @FXML
    private Hyperlink vertikecks;
        private void valor () throws SQLException{
            Calendar fecha1 = Calendar.getInstance();
             String x = "SELECT count(ESTADO) as clientes FROM [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG]  WHERE ESTADO=1";
            
            tie.setText("Quevedo,"+fecha1.getTime());
             int ins=0,rep=0,re3=0,ac=0;

        String instala = " SELECT count(*) as ins FROM [BDAirnet].[dbo].[tickets] where estado='Espera' and respuesta='' and (codigo='FO020INS' or codigo='RE021INS') ";
           ResultSet re1 =a.tablas(instala);
            while (re1.next()){
              ins=  re1.getInt("ins");
            }
             ResultSet act =a.tablas(x);
            while (act.next()){
              ac=  act.getInt("clientes");
            }
         String rer = "  SELECT count(*) as ins FROM [BDAirnet].[dbo].[tickets] where estado='Espera' and respuesta='' and (codigo !='FO020INS' or codigo!='RE021INS' or codigo !='RE016FINAL' or codigo !='FO016FINAL') ";
           ResultSet para =a.tablas(rer);
            while (para.next()){
              rep=  para.getInt("ins");
            }
         String ret = "SELECT count(*) as ins FROM [BDAirnet].[dbo].[tickets] where estado='Espera' and respuesta='' and (codigo ='RE016FINAL' or codigo ='FO016FINAL') ";
           ResultSet para2 =a.tablas(ret);
            while (para2.next()){
              re3=  para2.getInt("ins");
            }   
        reparaciones.setText(String.valueOf(rep));
        activos.setText(String.valueOf(ac));
        instalaciones.setText(String.valueOf(ins));
        retiros.setText(String.valueOf(re3));
        
        }
    
    
    private void login(ActionEvent event){
        a.ventanas("login.fxml");
       Node source = (Node) event.getSource();
       Stage stage1 = (Stage) source.getScene().getWindow();
       stage1.close();
      
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
     cliente3.setOnMouseReleased((event) -> {
         list.clear();
         a.listaclient(list);

     });
    
      
        menu(tipouuario);
        copyWorker = createWorker();
        new Thread(copyWorker).start();
      //  usuario.setText(nombreusuario);
        cliente3.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
               VistaclientesController.nombresvistacliente=cliente3.getText();
               a.ventanas("vistaclientes.fxml");
             }}});
       
      arbol.setOnKeyPressed((event) -> { seleci();});
      a.listaclient(list);
      a.prediccion(cliente3, list);
     
    }    
  
    
    @FXML
    public void seleci() {
         Parent  we;
        TreeItem<String> item = arbol.getSelectionModel().getSelectedItem();
         
        switch (item.getValue()){
            case "Inicio":   
                break;
            case "Administracion de Servicio":
                 a.ventanas("corteservicio.fxml");
          
                break;
            case "Planes y Servicios":
                break;
            case "Status Clientes":
            {
                try {
                    we  = FXMLLoader.load(getClass().getResource("clientes.fxml"));
                    content.getChildren().add(we);
                } catch (IOException ex) {
                    Logger.getLogger(AircontrolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    
                break;

            case "Tickets de Servicio":
                break;
            case "Esperando Respuesta":
            {
                try {
                    we  = FXMLLoader.load(getClass().getResource("rma.fxml"));
                    we.getStylesheets().add(getClass().getResource("HojadeEstilo.css").toExternalForm());
                     content.getChildren().add(we);
                } catch (IOException ex) {
                    Logger.getLogger(AircontrolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                   
                    break;

            case "Contestados":
            {
                try {
                    we  = FXMLLoader.load(getClass().getResource("rmar.fxml"));
                    we.getStylesheets().add(getClass().getResource("Formatocondinalesdetablas.css").toExternalForm());
                     content.getChildren().add(we);
                } catch (IOException ex) {
                    Logger.getLogger(AircontrolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                   
                break;

            case "Cerrados":
            {
                try {
                    we  = FXMLLoader.load(getClass().getResource("MDLTSCERRADO.fxml"));
                    we.getStylesheets().add(getClass().getResource("Formatocondinalesdetablas.css").toExternalForm());
                     content.getChildren().add(we);
                } catch (IOException ex) {
                    Logger.getLogger(AircontrolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                   
                break;

            case "Inventario":
                break;
                case "Tipos de Productos":
            {
                try {
                    we  = FXMLLoader.load(getClass().getResource("MDLBOTIPOP.fxml"));
                    we.getStylesheets().add(getClass().getResource("Formatocondinalesdetablas.css").toExternalForm());
                    content.getChildren().add(we);
                } catch (IOException ex) {
                    Logger.getLogger(AircontrolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                   
                    break;

            case "Proveedores":
            {
                try {
                    we  = FXMLLoader.load(getClass().getResource("MDLBOPROV.fxml"));
                    we.getStylesheets().add(getClass().getResource("Formatocondinalesdetablas.css").toExternalForm());
                    content.getChildren().add(we);
                } catch (IOException ex) {
                    Logger.getLogger(AircontrolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                   
                break;

            case "Productos":
            {
                try {
                    we  = FXMLLoader.load(getClass().getResource("MDLBOPRODUCTOS.fxml"));
                    we.getStylesheets().add(getClass().getResource("Formatocondinalesdetablas.css").toExternalForm());
                    content.getChildren().add(we);
                } catch (IOException ex) {
                    Logger.getLogger(AircontrolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 
                break;

                
            case "Salida de Dinero":
                a.ventanas("registrosalidaddedinero.fxml");
                
                break;
            case "Reportes":
            {
                try {
                    we  = FXMLLoader.load(getClass().getResource("VistaReportes.fxml"));
                    we.getStylesheets().add(getClass().getResource("Formatocondinalesdetablas.css").toExternalForm());
                    content.getChildren().add(we);
                    
                } catch (IOException ex) {
                    Logger.getLogger(AircontrolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
               
                 
                break;

                case "Nuevo Personal":
                     a.ventanas("Nuevo personal.fxml");
                
                break;
            case "Nuevo Cliente":
                a.ventanas("NUEVOCLIENTE.fxml"); 
             
                break;
            case "Reportes General":
            {
                try {
                    we  = FXMLLoader.load(getClass().getResource("VistaReportes.fxml"));
                    we.getStylesheets().add(getClass().getResource("Formatocondinalesdetablas.css").toExternalForm());
                     content.getChildren().add(we);
                } catch (IOException ex) {
                    Logger.getLogger(AircontrolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
                break;
            case "Pago de Torres":
                 a.ventanas("torrespago.fxml"); 
                           
                break;
            case "Promesa de Pago":
                  a.ventanas("Promesadepago.fxml"); 
                
                break ;
            case "Estado de cuentas Individual":
                  {
                try {
                    we  = FXMLLoader.load(getClass().getResource("EstadodeCuentasIndividual.fxml"));
                    content.getChildren().add(we);
                } catch (IOException ex) {
                    Logger.getLogger(AircontrolController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 
                break;
            default:
                break;
        }
    }
    public void menu(String usuaario){
       TreeItem<String> root1 = new TreeItem<>("Inicio",new ImageView(icon1));
      root1.setExpanded(true);
        // aqui se va  generar un switch para el control de usuario
        TreeItem<String> root2 = new TreeItem<>("Gestion de Redes",new ImageView(icon2));
                      //  TreeItem<String> r2ite1 = new TreeItem<>("Red de Fibra");
                      //  TreeItem<String> r2ite2 = new TreeItem<>("Red de Radio Enlace"); 
                         TreeItem<String> r2ite3 = new TreeItem<>("Administracion de Servicio");
                        root2.getChildren().addAll(r2ite3);
          TreeItem<String> root3 = new TreeItem<>("Planes y Servicios",new ImageView(icon3));
          TreeItem<String> root4 = new TreeItem<>("Clientes",new ImageView(icon4));
           TreeItem<String> r4ite1 = new TreeItem<>("Status Clientes");
           TreeItem<String> r4ite2 = new TreeItem<>("Nuevo Cliente");
                    root4.getChildren().addAll(r4ite1,r4ite2);
          TreeItem<String> root5 = new TreeItem<>("Tickets de Servicio",new ImageView(icon5));
                TreeItem<String> r5ite1 = new TreeItem<>("Esperando Respuesta");
                TreeItem<String> r5ite2 = new TreeItem<>("Contestados"); 
                TreeItem<String> r5ite3 = new TreeItem<>("Cerrados");
                root5.getChildren().addAll(r5ite1,r5ite2,r5ite3);
          TreeItem<String> root6 = new TreeItem<>("Inventario",new ImageView(icon6));
                 TreeItem<String> r6ite1 = new TreeItem<>("Tipos de Productos");
                TreeItem<String> r6ite2 = new TreeItem<>("Proveedores"); 
                TreeItem<String> r6ite3 = new TreeItem<>("Productos");
                root6.getChildren().addAll(r6ite1,r6ite2,r6ite3);
          TreeItem<String> root7 = new TreeItem<>("Caja",new ImageView(icon7));
                TreeItem<String> r7ite1 = new TreeItem<>("Salida de Dinero"); 
                 TreeItem<String> r7ite2 = new TreeItem<>("Reportes");
                 TreeItem<String> r7ite3 = new TreeItem<>("Pago de Torres");
                  TreeItem<String> r7ite4 = new TreeItem<>("Promesa de Pago");
              //  TreeItem<String> r7ite2 = new TreeItem<>("Gestor de Cobranzas");
                root7.getChildren().addAll(r7ite1,r7ite2,r7ite3,r7ite4);
         TreeItem<String> root8 = new TreeItem<>("Reportes y Control",new ImageView(icon8));
               TreeItem<String> r8ite3 = new TreeItem<>("Reportes General");
               TreeItem<String> r8ite4 = new TreeItem<>("Estado de cuentas Individual");
                root8.getChildren().addAll(r8ite3,r8ite4);
          TreeItem<String> root9 = new TreeItem<>("Contabilidad",new ImageView(icon9));
           TreeItem<String> r9ite2 = new TreeItem<>("Nuevo Personal"); 
            root9.getChildren().addAll(r9ite2);
          TreeItem<String> root10 = new TreeItem<>("Ajustes",new ImageView(icon10));
         
      switch(usuaario){
            case "SuperUsuario":
                   root1.getChildren().addAll(root2,root3,root4,root5,root6,root7,root8,root9,root10);  
                break ;
            case "Secretario":
                 root1.getChildren().addAll(root4,root5,root7);  
                break ;
                case "Administrador":
                       root1.getChildren().addAll(root2,root3,root4,root5,root6,root7,root8,root9,root10);  
                break ;
                case "Tecnico":
                       root1.getChildren().addAll(root2,root3,root4,root5,root6,root7,root8,root9,root10);  
                     cliente3.setDisable(true);
                break ;
                case "Contabilidad":
                     root1.getChildren().addAll(root2,root3,root4,root5,root6,root7,root8,root9,root10);  
                    cliente3.setDisable(true);
                    break;
                case "Programador":
                     root1.getChildren().addAll(root2,root3,root4,root5,root6,root7,root8,root9,root10);  
                    
                    break;
                default :
                    cliente3.setDisable(true);
                    break;
                }

          arbol.setRoot(root1) ;
          
          
    }

    

    public Task createWorker() {
        return new Task() {
            @Override
    protected Object call() throws Exception {
                valor();
        
        return null;
}};}

   
    
    
}
