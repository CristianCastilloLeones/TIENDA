/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

public class XML {
    private String detalle ;
    private String valorunitario;
    private String valortotal;
    private String cantidad;
  
    
    public XML(){}

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(String valorunitario) {
        this.valorunitario = valorunitario;
    }

    public String getValortotal() {
        return valortotal;
    }

    public void setValortotal(String valortotal) {
        this.valortotal = valortotal;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public XML(String detalle, String valorunitario, String valortotal,String cantidad) {
        this.detalle = detalle;
        this.valorunitario = valorunitario;
        this.valortotal = valortotal;
        this.cantidad=cantidad;
    }
    
    
    
}
