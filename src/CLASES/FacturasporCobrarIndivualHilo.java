/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 *
  * @author cl
 */
public class FacturasporCobrarIndivualHilo extends Task{
 private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }   

    public FacturasporCobrarIndivualHilo(String msg) {
        this.msg = msg;
    }
     public ObservableList<FacturasporCobrado> funciondeDeposito(){
        Clase_Estado_Individual obClase_Estado_Individual=new Clase_Estado_Individual(msg);
        obClase_Estado_Individual.Facturas_x_Cobrar();   
                return obClase_Estado_Individual.getXcobrar();
    
    }
    @Override
    protected ObservableList<FacturasporCobrado> call() throws Exception {
        return funciondeDeposito();
    }
}
