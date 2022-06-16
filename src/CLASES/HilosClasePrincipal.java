/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
/**
 *
  * @author cl
 */
public class HilosClasePrincipal implements Runnable{
    public Thread hilo;
      String Valor;
      Parent we;

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    public Parent getWe() {
        return we;
    }

    public void setWe(Parent we) {
        this.we = we;
    }
      
     HilosClasePrincipal( Parent we ,String Valor) {
         this.Valor=Valor;
          this.we = we;
          hilo= new Thread(this,Valor.replace(".fxml", ""));
    }
    public static HilosClasePrincipal creayempieza( Parent we, String Valor){
        HilosClasePrincipal mihilo = new HilosClasePrincipal(   we, Valor);
        mihilo.hilo.start();
        return mihilo;
    }
      
      
    @Override
    public void run() {
       CargarVentanas();
    }
    
    
    public void CargarVentanas(){
         try {
            this.we = FXMLLoader.load(getClass().getResource(this.Valor));
            we.scaleXProperty().add(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - 10);
            we.scaleYProperty().add(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - 60);
            } catch (IOException ex) {
             Logger.getLogger(HilosClasePrincipal.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
