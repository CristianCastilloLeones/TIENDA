/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

public class facturaprin {
    private String fechagenerada;
    private String numerofactura;
    private String clave;
    private String clientefa;

    public String getFechagenerada() {
        return fechagenerada;
    }

    public void setFechagenerada(String fechagenerada) {
        this.fechagenerada = fechagenerada;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClientefa() {
        return clientefa;
    }

    public void setClientefa(String clientefa) {
        this.clientefa = clientefa;
    }

    public facturaprin(String fechagenerada, String numerofactura, String clave, String clientefa) {
        this.fechagenerada = fechagenerada;
        this.numerofactura = numerofactura;
        this.clave = clave;
        this.clientefa = clientefa;
    }
    
}

