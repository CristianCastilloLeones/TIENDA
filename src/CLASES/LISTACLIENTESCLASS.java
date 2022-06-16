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
public class LISTACLIENTESCLASS {
    private String usuario;
    private String IP;
    private String red;

    public LISTACLIENTESCLASS(String usuario, String IP, String red) {
        this.usuario = usuario;
        this.IP = IP;
        this.red = red;
    }

    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }
    
    
}
