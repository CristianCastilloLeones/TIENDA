/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

public class clientefact {
    private String nombres;
    private String apellidos;
    private String direccion;
    private String cedula;
    
    
    public clientefact(){
        
    }
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public clientefact(String nombres, String apellidos, String direccion, String cedula) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.cedula = cedula;
    }
    
}
