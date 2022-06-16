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
public class FacturasporCobrado {
    private String clienteporcobrar;
    private String Fechaporcobrar;
    private String Valorporcobrar;

    public String getClienteporcobrar() {
        return clienteporcobrar;
    }

    public void setClienteporcobrar(String clienteporcobrar) {
        this.clienteporcobrar = clienteporcobrar;
    }

    public String getFechaporcobrar() {
        return Fechaporcobrar;
    }

    public void setFechaporcobrar(String Fechaporcobrar) {
        this.Fechaporcobrar = Fechaporcobrar;
    }

    public String getValorporcobrar() {
        return Valorporcobrar;
    }

    public void setValorporcobrar(String Valorporcobrar) {
        this.Valorporcobrar = Valorporcobrar;
    }

    public FacturasporCobrado(String clienteporcobrar, String Fechaporcobrar, String Valorporcobrar) {
        this.clienteporcobrar = clienteporcobrar;
        this.Fechaporcobrar = Fechaporcobrar;
        this.Valorporcobrar = Valorporcobrar;
    }
    
}
