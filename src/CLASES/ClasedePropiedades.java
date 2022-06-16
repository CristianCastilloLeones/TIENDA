/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import AJUSTE.AJUSTESController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
  * @author cl
 */
public class ClasedePropiedades {

    final String PROPERTIES_FILE = "C:\\ARCHIVOSSISTEMA\\Propiedades.properties";
  public  Properties properties = new Properties();

    public void CargarPropiedades() {
        try {
            properties.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClasedePropiedades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClasedePropiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CreacionArchivo(boolean valor) {
        CrearcionCarper();
        File archivo = new File(PROPERTIES_FILE);
        if (!archivo.exists()) {

            properties.setProperty("Ambiente", "PRODUCCION");
            properties.setProperty("RutaServidor", "131.196.15.242");
            properties.setProperty("RutaArchivos", "\\\\S-AIRCONTROL\\Nuevo Systema");
            properties.setProperty("NombreBD", "BDAirnet");
            properties.setProperty("UsuarioBD", "ISP");
            properties.setProperty("ClaveBD", "server@aircontrol@1");
            properties.setProperty("CorreoEmisor", "serviairnet.net@gmail.com");
            properties.setProperty("Puerto", "587");
            properties.setProperty("ClaveCorreoEmisor", "servicioairnet");
            properties.setProperty("MailHost", "smtp.gmail.com");
            properties.setProperty("TipoUsuario", "CONFIGURACIONES");
             properties.setProperty("IPMIKFIBRA", "12.12.12.2");
              properties.setProperty("IPMIKAIRE", "10.10.10.1");
              properties.setProperty("USUARIOMIKRO", "activacion");
              properties.setProperty("PASSMIKRO", "activacion@1");

            GuardarCambiosPropiedades();
        } else {
            CargarPropiedades();
            if (valor) {
                properties.setProperty("Ambiente", "PRODUCCION");
                properties.setProperty("RutaServidor", "131.196.15.242");
                properties.setProperty("RutaArchivos", "\\\\S-AIRCONTROL\\Nuevo Systema");
                properties.setProperty("NombreBD", "BDAirnet");
                properties.setProperty("UsuarioBD", "ISP");
                properties.setProperty("ClaveBD", "server@aircontrol@1");
                properties.setProperty("CorreoEmisor", "serviairnet.net@gmail.com");
                properties.setProperty("Puerto", "587");
                properties.setProperty("ClaveCorreoEmisor", "servicioairnet");
                properties.setProperty("MailHost", "smtp.gmail.com");
                properties.setProperty("TipoUsuario", "CONFIGURACIONES");
               properties.setProperty("IPMIKFIBRA", "12.12.12.2");
              properties.setProperty("IPMIKAIRE", "10.10.10.1");
              properties.setProperty("USUARIOMIKRO", "activacion");
              properties.setProperty("PASSMIKRO", "activacion@1");
                GuardarCambiosPropiedades();

            }

        }
    }

    public void GuardarCambiosPropiedades() {
        try {
            properties.store(new FileWriter(PROPERTIES_FILE), "Archivo de Configuraciones");
        } catch (IOException ex) {
            Logger.getLogger(AJUSTESController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void CrearcionCarper() {
        File directorio = new File("C:\\ARCHIVOSSISTEMA");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Creado Carpeta Principal");
            } else {
                System.out.println(" NO se Creo Carpeta Principal");
            }
        }
    }

    public boolean DesahabilitarNiveles(String NivelUsuario) {

        switch (NivelUsuario) {
            case "SuperUsuario":
                return false;

            case "Secretario":

                return true;
            case "Administrador":

                return false;
            case "Tecnico":

                return true;
            case "Contabilidad":

                return false;
            case "Programador":
                return false;
            default:
                return true;
        }

    }
}
