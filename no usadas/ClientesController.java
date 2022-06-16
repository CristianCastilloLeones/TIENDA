/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;




/**
 * FXML Controller class
 *
  * @author cl
 */
public class ClientesController implements Initializable {
    bas q = new bas();
    CategoryAxis xAxis = new CategoryAxis();    
    NumberAxis yAxis = new NumberAxis(); 
    CategoryAxis xAxis1 = new CategoryAxis();    
    NumberAxis yAxis1 = new NumberAxis(); 
     Image iconoprincipal = new Image(getClass().getResourceAsStream("/imagenes/bot2.png"));
    @FXML
    private TextField buscarcliente;
    @FXML
    private BarChart<String, Number> tasaclientes =new BarChart<>(xAxis, yAxis);
    @FXML
    private BarChart<?, ?> crecimiento;
    @FXML
    private Button empezar;
    @FXML
    private TextArea resultadomonitoreo;
    @FXML
    private Label cliente;
    @FXML
    private Button imprmir;
    @FXML
    private Label ipv;
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empezar.setDisable(true); 
       List clien = new ArrayList();
        q.listaclient(clien);
        q.prediccion(buscarcliente, clien);
        buscarcliente.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
                
               cliente.setText(buscarcliente.getText());
                System.out.println(q.Conseguirip(buscarcliente.getText()).replace(" ", ""));
               ipv.setText(q.Conseguirip(buscarcliente.getText()).replace(" ", ""));
            }
        }
    });
        ipv.textProperty().addListener((obs, oldText, newText) -> {
        if(newText.length()>13){
          empezar.setDisable(false);
        }else {
          empezar.setDisable(true);  
        }
        });
        empezar.setOnAction((event) -> {
            String datos="";
            datos=datos+"\n"+q.runSystemCommand("ping "+ipv.getText());
            System.out.println(datos);
            resultadomonitoreo.setText(datos);
             resultadomonitoreo.setEditable(false);
        });
        
          try {
              
              graficos();
          } catch (SQLException ex) {
              Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
          } }
    @FXML
    private void NUEVOCLIENTE(ActionEvent event){
        
         q.ventanas("NUEVOCLIENTE.fxml");
        }
    
   
    
    
    
    public void graficos() throws SQLException{
        yAxis.setLabel("Numero de Registros");
        xAxis.setTickMarkVisible(true);
 
// Rotate the label of Tick Marks 90 degrees
            xAxis.setTickLabelRotation(90);
 
// Set color for lable of Tick marks
        xAxis.setTickLabelFill(Color.RED);
 
       int enero=0,febre=0,marzo=0,abril=0,mayo=0,junio=0,julio=0,agsto=0,septiembre=0,octubre=0,noviembre=0,diciembre=0;
       ResultSet ins = q.tablas("select fecha from [BDAirnet].[dbo].[tickets] where  (codigo='FO020INS' or codigo='RE021INS')");
       ResultSet rep = q.tablas("select fecha from [BDAirnet].[dbo].[tickets] where (codigo !='FO020INS' or codigo!='RE021INS' or codigo !='RE016FINAL' or codigo !='FO016FINAL')");
       ResultSet ret = q.tablas("select fecha from [BDAirnet].[dbo].[tickets] where (codigo ='RE016FINAL' or codigo ='FO016FINAL') ");
       while(ins.next()){
            switch(ins.getDate("fecha").getMonth()){
            case 0:enero++; break;
            case 1:febre++;break;
            case 2:marzo++;break;
            case 3:abril++;break;
            case 4:mayo++;break;
            case 5:junio++;break;
            case 6:julio++;break;
            case 7:agsto++;break;
            case 8:septiembre++;break;
            case 9:octubre++;break;
            case 10:noviembre++;break;
            case 11:diciembre++;break;
                          
          }}
         xAxis1.setCategories(FXCollections.<String>observableArrayList(Arrays.asList
         ("Enero", "Feb.", "Marzo.", "Abril", "Mayo","Junio","Julio","Agos.","Sept","Octu","Nov","Dic")));
         xAxis1.setLabel("Meses");
        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList
         ("Enero", "Feb.", "Marzo.", "Abril", "Mayo","Junio","Julio","Agos.","Sept","Octu","Nov","Dic")));
        xAxis.setLabel("Meses");
        
      XYChart.Series<String, Number> series1 = new XYChart.Series<>();  
      series1.setName("Instalaciones"); 
      series1.getData().add(new XYChart.Data<>("Enero", enero)); 
      series1.getData().add(new XYChart.Data<>("Feb.", febre));  
      series1.getData().add(new XYChart.Data<>("Marzo", marzo)); 
      series1.getData().add(new XYChart.Data<>("Abril", abril)); 
      series1.getData().add(new XYChart.Data<>("Mayo", mayo)); 
      series1.getData().add(new XYChart.Data<>("Junio", junio)); 
      series1.getData().add(new XYChart.Data<>("Julio", julio)); 
      series1.getData().add(new XYChart.Data<>("Agosto", agsto)); 
      series1.getData().add(new XYChart.Data<>("Sept.", septiembre)); 
      series1.getData().add(new XYChart.Data<>("Octu.", octubre)); 
      series1.getData().add(new XYChart.Data<>("Nov.", noviembre)); 
      series1.getData().add(new XYChart.Data<>("Dic.", diciembre)); 
      enero=0;febre=0;marzo=0;abril=0;mayo=0;junio=0;julio=0;agsto=0;septiembre=0;octubre=0;noviembre=0;diciembre=0;
      while(rep.next()){
            switch(rep.getDate("fecha").getMonth()){
              case 0:enero++; break;
             case 1:febre++;break;
             case 2:marzo++;break;
             case 3:abril++;break;
             case 4:mayo++;break;
             case 5:junio++;break;
             case 6:julio++;break;
             case 7:agsto++;break;
            case 8:septiembre++;break;
            case 9:octubre++;break;
            case 10:noviembre++;break;
            case 11:diciembre++;break;
                          
          }
      }  
      XYChart.Series<String, Number> series2 = new XYChart.Series<>(); 
      series2.setName("Reparaciones"); 
      series2.getData().add(new XYChart.Data<>("Enero", enero)); 
      series2.getData().add(new XYChart.Data<>("Feb.", febre));  
      series2.getData().add(new XYChart.Data<>("Marzo", marzo)); 
      series2.getData().add(new XYChart.Data<>("Abril", abril)); 
      series2.getData().add(new XYChart.Data<>("Mayo", mayo)); 
      series2.getData().add(new XYChart.Data<>("Junio", junio)); 
      series2.getData().add(new XYChart.Data<>("Julio", julio)); 
      series2.getData().add(new XYChart.Data<>("Agosto", agsto)); 
      series2.getData().add(new XYChart.Data<>("Sept.", septiembre)); 
      series2.getData().add(new XYChart.Data<>("Octu.", octubre)); 
      series2.getData().add(new XYChart.Data<>("Nov.", noviembre)); 
      series2.getData().add(new XYChart.Data<>("Dic.", diciembre)); 
      enero=0;febre=0;marzo=0;abril=0;mayo=0;junio=0;julio=0;agsto=0;septiembre=0;octubre=0;noviembre=0;diciembre=0;
      while(ret.next()){
            switch(ret.getDate("fecha").getMonth()){
            case 0:enero++; break;
            case 1:febre++;break;
            case 2:marzo++;break;
            case 3:abril++;break;
            case 4:mayo++;break;
            case 5:junio++;break;
            case 6:julio++;break;
            case 7:agsto++;break;
            case 8:septiembre++;break;
            case 9:octubre++;break;
            case 10:noviembre++;break;
            case 11:diciembre++;break;
                          
          }}
      XYChart.Series<String, Number> series3 = new XYChart.Series<>(); 
      series3.setName("Retiros"); 
      series3.getData().add(new XYChart.Data<>("Enero", enero)); 
      series3.getData().add(new XYChart.Data<>("Feb.", febre));  
      series3.getData().add(new XYChart.Data<>("Marzo", marzo)); 
      series3.getData().add(new XYChart.Data<>("Abril", abril)); 
      series3.getData().add(new XYChart.Data<>("Mayo", mayo)); 
      series3.getData().add(new XYChart.Data<>("Junio", junio)); 
      series3.getData().add(new XYChart.Data<>("Julio", julio)); 
      series3.getData().add(new XYChart.Data<>("Agosto", agsto)); 
      series3.getData().add(new XYChart.Data<>("Sept.", septiembre)); 
      series3.getData().add(new XYChart.Data<>("Octu.", octubre)); 
      series3.getData().add(new XYChart.Data<>("Nov.", noviembre)); 
      series3.getData().add(new XYChart.Data<>("Dic.", diciembre)); 
         
      //Setting the data to bar chart
      tasaclientes.getData().addAll(series1, series2,series3);
   
    }
}
