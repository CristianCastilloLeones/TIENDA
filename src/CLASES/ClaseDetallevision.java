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
public class ClaseDetallevision {
    private int IDDETALLE;
    private String Detalle;
    private String ValorTotal;
    private String Cliente;
    private String NfacSRI;

    public ClaseDetallevision(int IDDETALLE, String Detalle, String ValorTotal, String Cliente, String NfacSRI) {
        this.IDDETALLE = IDDETALLE;
        this.Detalle = Detalle;
        this.ValorTotal = ValorTotal;
        this.Cliente = Cliente;
        this.NfacSRI = NfacSRI;
    }

    
    public int getIDDETALLE() {
        return IDDETALLE;
    }

    public void setIDDETALLE(int IDDETALLE) {
        this.IDDETALLE = IDDETALLE;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public String getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(String ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getNfacSRI() {
        return NfacSRI;
    }

    public void setNfacSRI(String NfacSRI) {
        this.NfacSRI = NfacSRI;
    }
    
}
