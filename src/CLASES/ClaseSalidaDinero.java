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
public class ClaseSalidaDinero {
  private String fecha;
  private String Solicitante;
  private String Detalle;
  private String Valor;

    public ClaseSalidaDinero(String fecha, String Solicitante, String Detalle, String Valor) {
        this.fecha = fecha;
        this.Solicitante = Solicitante;
        this.Detalle = Detalle;
        this.Valor = Valor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSolicitante() {
        return Solicitante;
    }

    public void setSolicitante(String Solicitante) {
        this.Solicitante = Solicitante;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String Detalle) {
        this.Detalle = Detalle;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }
  
  
}
