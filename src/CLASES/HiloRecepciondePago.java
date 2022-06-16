/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.concurrent.Task;

/**
 *
  * @author cl
 */
public class HiloRecepciondePago extends Task {
  //  bas q = new bas();
    Clase_RecepciondePago recep ;
    private String msg;
    private String  facutartodoaño;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFacutartodoaño() {
        return facutartodoaño;
    }

    public void setFacutartodoaño(String facutartodoaño) {
        this.facutartodoaño = facutartodoaño;
    }

    
    public HiloRecepciondePago(String msg ,String facutartodoaño) {
        this.msg = msg;
        this.facutartodoaño =facutartodoaño;
    }
    
            
    
    public ResultSet datos() throws SQLException{
        recep = new Clase_RecepciondePago(msg, facutartodoaño);
        return recep.detalle();
    }
    
    
    @Override
    protected ResultSet call() throws Exception {
        return datos();
    }
    
}
