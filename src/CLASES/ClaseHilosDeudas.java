/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

/**
 *
  * @author cl
 */
public class ClaseHilosDeudas extends ClaseFuncinClientes implements Runnable{
        Thread hilo;
        String Valor;
    public ClaseHilosDeudas(String Valor) {
        super();
         this.Valor=Valor;  
        hilo= new Thread(this,Valor);
         
    }

    public static ClaseHilosDeudas creayempieza(String Valor){
        ClaseHilosDeudas mihilo = new ClaseHilosDeudas(Valor);
        mihilo.hilo.start();
        return mihilo;
    }
    @Override
    public void run() {
       valoresInicales();
    }
    
}
