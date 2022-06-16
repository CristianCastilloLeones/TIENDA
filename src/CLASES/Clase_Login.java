/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import javafxapplication4.VariablesDeUso;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
  * @author cl
 */
public class Clase_Login extends bas {

    private String Texto1U;
    private String Texto2C;

    public String getTexto1U() {
        return Texto1U;
    }

    public void setTexto1U(String Texto1U) {
        this.Texto1U = Texto1U;
    }

    public String getTexto2C() {
        return Texto2C;
    }

    public void setTexto2C(String Texto2C) {
        this.Texto2C = Texto2C;
    }

    public Clase_Login(String Texto1U, String Texto2C) {
        this.Texto1U = Texto1U;
        this.Texto2C = Texto2C;
    }

    public boolean login() throws SQLException {
        boolean t = false;
        if (this.Texto1U.equals("admin") && Texto2C.equals("admin")) {
            VariablesDeUso.nombreusuario = ("CONFIGURACIONES");
            CargarPropiedades();
            properties.setProperty("TipoUsuario", "CONFIGURACIONES");
            GuardarCambiosPropiedades();
            return true;
        } else {

            ResultSet rt = tablas("SELECT top(1) [USUARIO],[CLAVE],([NOMBRES]+' '+[APELLIDOS]) as USUA ,[TIPO] FROM [BDAirnet].[dbo].[VistaEmpleadosLogin] where USUARIO = '" + this.Texto1U + "' and CLAVE = '" + getMD5(Texto2C) + "'");
            while (rt.next()) {
                VariablesDeUso.nombreusuario = (rt.getString("USUA"));
                CargarPropiedades();
                properties.setProperty("TipoUsuario", rt.getString("TIPO"));
                GuardarCambiosPropiedades();
                t = true;

            }

            return t;
        }
    }

}
