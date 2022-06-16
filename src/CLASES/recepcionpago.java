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
public class recepcionpago {
    private String cantidad;
    private String Detalle;
    private String pvp;
    private String pvpt;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public String getPvp() {
        return pvp;
    }

    public void setPvp(String pvp) {
        this.pvp = pvp;
    }

    public String getPvpt() {
        return pvpt;
    }

    public void setPvpt(String pvpt) {
        this.pvpt = pvpt;
    }

    public recepcionpago(String cantidad, String Detalle, String pvp, String pvpt,String id) {
        this.cantidad = cantidad;
        this.Detalle = Detalle;
        this.pvp = pvp;
        this.pvpt = pvpt;
        this.id=id;
    }

    public recepcionpago(String cantidad, String Detalle, String pvp, String pvpt) {
        this.cantidad = cantidad;
        this.Detalle = Detalle;
        this.pvp = pvp;
        this.pvpt = pvpt;
    }
    
    
}
