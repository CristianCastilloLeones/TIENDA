/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;
import javafx.scene.control.TextField;

/**
 *
  * @author cl
 */
public class ClaseVentanaPrincipal extends bas {
  public static  List ListaCliente = new ArrayList();

   

    public synchronized void NombredelHilo(TextField cliente3) {

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                // aqui va todo el proceso a ejecutarse 
                listaclient(ListaCliente);
                prediccion(cliente3, ListaCliente);
                return null;
            }
        };
        task.setOnSucceeded(event -> {
            // aqui va que hacer cuando ya acabe de ejecutarse el hilo
         notificaciones("Lista de Cliente Actualizada", "I");

        });
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
    
    
}
