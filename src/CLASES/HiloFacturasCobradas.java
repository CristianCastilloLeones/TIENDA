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
public class HiloFacturasCobradas extends Task{

    public HiloFacturasCobradas(String fechinicio, String fechafinal,String msg) {
        this.fechinicio = fechinicio;
        this.fechafinal = fechafinal;
        this.msg = msg;
    }
    private String msg;
    private String fechinicio;
    private String fechafinal;
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }   

    
    public String getFechinicio() {
        return fechinicio;
    }

    public void setFechinicio(String fechinicio) {
        this.fechinicio = fechinicio;
    }

    
    public  ObservableList<FacturasCobradas> Facturacion(){
         Clase_Estado_Individual obClase_Estado_Individual=new Clase_Estado_Individual(fechinicio,fechafinal,msg);
        obClase_Estado_Individual.Facturas_EfecREpor();
        return obClase_Estado_Individual.getEfectivo();
    }
    @Override
    protected ObservableList<FacturasCobradas> call() throws Exception {
       return Facturacion();
    }
    
}
