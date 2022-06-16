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
public class EstadoIndivudaulBancosHILO  extends Task{
    private String msg;
    private String fechinicio;
    private String fechafinal;
    private String Condicion;

    public String getCondicion() {
        return Condicion;
    }

    public void setCondicion(String Condicion) {
        this.Condicion = Condicion;
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

    public EstadoIndivudaulBancosHILO(String msg,String fechinicio, String fechafinal,String Condicion) {
        this.fechinicio = fechinicio;
        this.fechafinal = fechafinal;
        this.msg=msg;
        this.Condicion = Condicion;
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
   
 public ObservableList<cuentasporbanco> funciondeDeposito(){
     if(msg.equals("1")){
         Clase_Estado_Individual obClase_Estado_Individual=new Clase_Estado_Individual(fechinicio,fechafinal,Condicion);
         obClase_Estado_Individual.facturas_Depositos();   
            return obClase_Estado_Individual.getDepositos();
     }else {
         Clase_Estado_Individual obClase_Estado_Individual=new Clase_Estado_Individual(msg);
         obClase_Estado_Individual.facturas_Depositos(); 
            return obClase_Estado_Individual.getDepositos();
     }
    }
    @Override
    protected ObservableList<cuentasporbanco> call() throws Exception {
        return funciondeDeposito();
    }
    
}
