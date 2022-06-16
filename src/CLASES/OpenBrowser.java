/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
  * @author cl
 */
public class OpenBrowser {

    private String direccion;

    public OpenBrowser(String direccion) {
        this.direccion = direccion;
    }

    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //NAVEGADOR POR DEFECTO POR SISTEMA
    public void abrirNavegadorPredeterminadorWindows(String url) throws IOException{
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
    }
    public void abrirNavegadorPredeterminadorLinux(String url) throws IOException{
        Runtime.getRuntime().exec("xdg-open " + url);
    }
    public void abrirNavegadorPredeterminadorMacOsx(String url) throws IOException{
        Runtime.getRuntime().exec("open " + url);
    }

    //NAVEGADOR POR DEFECTO GENERICO
    public void abrirNavegadorPorDefecto() {
        try {
            String osName = System.getProperty("os.name");
            if(osName.contains("Windows"))
                abrirNavegadorPredeterminadorWindows("http:////"+this.direccion);
            else if(osName.contains("Linux"))
                abrirNavegadorPredeterminadorLinux("http:////"+this.direccion);
            else if(osName.contains("Mac OS X"))
                abrirNavegadorPredeterminadorMacOsx("http:////"+this.direccion);
            else{ //INFORMAR SISTEMA NO SOPORTADO }
                System.out.println("INFORMAR SISTEMA NO SOPORTADO");
            }   } catch (IOException ex) {
            Logger.getLogger(OpenBrowser.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
