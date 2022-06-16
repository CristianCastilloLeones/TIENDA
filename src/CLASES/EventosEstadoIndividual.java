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
public class EventosEstadoIndividual extends Task{
    private String msg;
    private String fechinicio;
    private String fechafinal;
    private String CondicionCajero;

    public String getMsg() {
        return msg;
    }

    public String getCondicionCajero() {
        return CondicionCajero;
    }

    public void setCondicionCajero(String CondicionCajero) {
        this.CondicionCajero = CondicionCajero;
    }

    public String getFechinicio() {
        return fechinicio;
    }

    public void setFechinicio(String fechinicio) {
        this.fechinicio = fechinicio;
    }

    public String getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(String fechafinal) {
        this.fechafinal = fechafinal;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EventosEstadoIndividual(String msg, String fechinicio, String fechafinal,String CondicionCajero) {
        this.msg = msg;
        this.fechinicio = fechinicio;
        this.fechafinal = fechafinal;
        this.CondicionCajero = CondicionCajero;
    }

    
    
public ObservableList<Eventos> funciondeDeposito(){
    if(msg.equals("1")){
        Clase_Estado_Individual obClase_Estado_Individual=new Clase_Estado_Individual(fechinicio,fechafinal,CondicionCajero);
        obClase_Estado_Individual.salidadedinero();
        return obClase_Estado_Individual.getEventosvi();
    }else{
       Clase_Estado_Individual obClase_Estado_Individual=new Clase_Estado_Individual(msg);
        obClase_Estado_Individual.Facturas_Eventos();   
        return obClase_Estado_Individual.getEventosvi(); 
    }
        
    
    }
    @Override
    protected ObservableList<Eventos> call() throws Exception {
       return  funciondeDeposito();
               
    }
}
