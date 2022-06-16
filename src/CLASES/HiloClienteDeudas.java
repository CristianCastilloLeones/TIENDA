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
public class HiloClienteDeudas {
     Clase_RecepciondePago ob;
 private String celuda;
    public HiloClienteDeudas(String celuda) {
        this.celuda = celuda;
    }
  

    public String getCeluda() {
        return celuda;
    }

    public void setCeluda(String celuda) {
        this.celuda = celuda;
    }

  
   

  public float busquedaTotalDeuda() {
        ob = new Clase_RecepciondePago(celuda, "");
        return ob.Valordeudante();
    }
    
   
    
}
