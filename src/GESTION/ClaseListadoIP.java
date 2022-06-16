/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GESTION;

/**
 *
  * @author cl
 */
public class ClaseListadoIP {
  
   private int     idcliente;
   private String  iptablalistado;
   private String  clientelistado;
   private String  serial;

    public ClaseListadoIP(int idcliente, String iptablalistado, String clientelistado, String serial) {
        this.idcliente = idcliente;
        this.iptablalistado = iptablalistado;
        this.clientelistado = clientelistado;
        this.serial = serial;
    }
   
    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getIptablalistado() {
        return iptablalistado;
    }

    public void setIptablalistado(String iptablalistado) {
        this.iptablalistado = iptablalistado;
    }

    public String getClientelistado() {
        return clientelistado;
    }

    public void setClientelistado(String clientelistado) {
        this.clientelistado = clientelistado;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
   
   
   
}
