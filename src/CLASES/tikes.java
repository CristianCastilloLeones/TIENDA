/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;


import java.util.Date;





/**
 *
  * @author cl
 */
public class tikes {
    
     private String numero;
   private String   departamento;
   private String   usuario;
  private String    asunto;
   private String   tecnicoA;
  private Date fecha;
   private String  ubicacion;
   private String  abierto;
   private String UltimaR;
   public tikes(String numero, String departamento, String usuario, String asunto, String tecnicoA, Date fecha, String ubicacion, String abierto, String UltimaR) {
        
        this.numero = numero;
        this.departamento = departamento;
        this.usuario = usuario;
        this.asunto = asunto;
        this.tecnicoA = tecnicoA;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.abierto = abierto;
        this.UltimaR=UltimaR;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTecnicoA() {
        return tecnicoA;
    }

    public void setTecnicoA(String tecnicoA) {
        this.tecnicoA = tecnicoA;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getAbierto() {
        return abierto;
    }

    public void setAbierto(String abierto) {
        this.abierto = abierto;
    }

    public String getUltimaR() {
        return UltimaR;
    }

    public void setUltimaR(String UltimaR) {
        this.UltimaR = UltimaR;
    }
   
}
