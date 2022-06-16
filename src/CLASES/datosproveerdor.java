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
public class datosproveerdor {
    //SELECT  [ide],[proveedor],[ruc],[correo],[telefono],[direccion] FROM [BDAirnet].[dbo].[tproveedordj]
    private String ide;
    private String proveedor;
    private String ruc;
    private String correo;
    private String telefono;
    private String direccion;

    public datosproveerdor(String ide, String proveedor, String ruc, String correo, String telefono, String direccion) {
        this.ide = ide;
        this.proveedor = proveedor;
        this.ruc = ruc;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
            
}
