/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INDICADORES;

/**
 *
  * @author cl
 */
public class facturasinstalacion {
    private String cliente;
    private String  fecha;
    private String valor;
    private String  estadoIns;
    private String Tipo;

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getEstadoIns() {
        return estadoIns;
    }

    public void setEstadoIns(String estadoIns) {
        this.estadoIns = estadoIns;
    }

    public facturasinstalacion(String cliente, String fecha, String valor, String estadoIns,String Tipo) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.valor = valor;
        this.estadoIns = estadoIns;
         this.Tipo = Tipo;
    }
    
}
