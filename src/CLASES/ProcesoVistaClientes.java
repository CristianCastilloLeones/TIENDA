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
public class ProcesoVistaClientes extends Task{
    private String msg;
    private String condicion;

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public ProcesoVistaClientes (String condicion,String msg){
        this.msg=msg;
        this.condicion = condicion;
      
    }
    
    public ObservableList<FacturasCobradas> funciondeCARGA(){
        Clase_Estado_Individual obClase_Estado_Individual=new Clase_Estado_Individual(msg);
        if(condicion.equals("1")){
            obClase_Estado_Individual.Facturas_enfectivo();   
                return obClase_Estado_Individual.getEfectivo(); 
        }else{
           obClase_Estado_Individual.facturasRecepcionPago();   
                return obClase_Estado_Individual.getEfectivo();  
        }
       
    
    }

   
    @Override
    protected ObservableList<FacturasCobradas> call() throws Exception {
       
            return funciondeCARGA();
        
           
        
       
    }
    
}
