/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import CLASES.bas;

import OLTS.*;
import com.jcraft.jsch.JSchException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javax.net.SocketFactory;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;

/**
 *
  * @author cl
 */
public class Clase_VistaCLientes {

    public Task Tasaefeciva;

    public Task getTasaefeciva() {
        return Tasaefeciva;
    }

    public void setTasaefeciva(Task Tasaefeciva) {
        this.Tasaefeciva = Tasaefeciva;
    }
    bas q = new bas();
    ResultSet st;
    private String IDCLIENTES;
    public static String Factorbusqueda;

    List<String> Planesutilisables = new ArrayList();
    List<String> TipodeinstalacionDisponibles = new ArrayList();
    List<String> Serialdiponible = new ArrayList();
    private String ultimopago;
    private String cedula;
    private String usuario = "";
    private String pass1 = "";
    private String IpMicro = "";
    private String ip;
    private String Lista;
    private String CODIGOCONTRATO;
    private String IDCLIENTE;
    private String CELULAR1;
    private String CELULAR2;
    private String EMAIL;
    private String DIRECCION;
    private String TIPOINSTALACION;
    private String plancontratado;
    private String Comparticion;
    private String VALORINTALACION;
    private String VALORMENSUAL;
    private String FECHAINSTALACION;
    private String lat;
    private String lon;
    private String MTSCABLE;
    private String ONUID;
    private String PUERTOSERVICIO;
    private String ponmac;
    private String tikectsmodulo;
    private boolean EstadoCliente;
    private String OLTTARJETA;
    private String OLTPON;
    private String nodoprincipal;

    public String getOLTTARJETA() {
        return OLTTARJETA;
    }

    public void setOLTTARJETA(String OLTTARJETA) {
        this.OLTTARJETA = OLTTARJETA;
    }

    public String getOLTPON() {
        return OLTPON;
    }

    public void setOLTPON(String OLTPON) {
        this.OLTPON = OLTPON;
    }

    public String getNodoprincipal() {
        return nodoprincipal;
    }

    public void setNodoprincipal(String nodoprincipal) {
        this.nodoprincipal = nodoprincipal;
    }

    public boolean isEstadoCliente() {
        return EstadoCliente;
    }

    public void setEstadoCliente(boolean EstadoCliente) {
        this.EstadoCliente = EstadoCliente;
    }

    public String getTikectsmodulo() {
        return tikectsmodulo;
    }

    public String getUltimopago() {
        return ultimopago;
    }

    public void setUltimopago(String ultimopago) {
        this.ultimopago = ultimopago;
    }

    public void setTikectsmodulo(String tikectsmodulo) {
        this.tikectsmodulo = tikectsmodulo;
    }
    public List listaip;
    private String Abonado;

    public String getCODIGOCONTRATO() {
        return CODIGOCONTRATO;
    }

    public void setCODIGOCONTRATO(String CODIGOCONTRATO) {
        this.CODIGOCONTRATO = CODIGOCONTRATO;
    }

    public String getIDCLIENTE() {
        return IDCLIENTE;
    }

    public String getIDCLIENTES() {
        return IDCLIENTES;
    }

    public void setIDCLIENTES(String IDCLIENTES) {
        this.IDCLIENTES = IDCLIENTES;
    }

    public void setIDCLIENTE(String IDCLIENTE) {
        this.IDCLIENTE = IDCLIENTE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getTIPOINSTALACION() {
        return TIPOINSTALACION;
    }

    public void setTIPOINSTALACION(String TIPOINSTALACION) {
        this.TIPOINSTALACION = TIPOINSTALACION;
    }

    public String getPlancontratado() {
        return plancontratado;
    }

    public void setPlancontratado(String plancontratado) {
        this.plancontratado = plancontratado;
    }

    public String getComparticion() {
        return Comparticion;
    }

    public void setComparticion(String Comparticion) {
        this.Comparticion = Comparticion;
    }

    public String getVALORINTALACION() {
        return VALORINTALACION;
    }

    public String getCELULAR1() {
        return CELULAR1;
    }

    public void setCELULAR1(String CELULAR1) {
        this.CELULAR1 = CELULAR1;
    }

    public String getCELULAR2() {
        return CELULAR2;
    }

    public void setCELULAR2(String CELULAR2) {
        this.CELULAR2 = CELULAR2;
    }

    public void setVALORINTALACION(String VALORINTALACION) {
        this.VALORINTALACION = VALORINTALACION;
    }

    public String getVALORMENSUAL() {
        return VALORMENSUAL;
    }

    public void setVALORMENSUAL(String VALORMENSUAL) {
        this.VALORMENSUAL = VALORMENSUAL;
    }

    public String getFECHAINSTALACION() {
        return FECHAINSTALACION;
    }

    public void setFECHAINSTALACION(String FECHAINSTALACION) {
        this.FECHAINSTALACION = FECHAINSTALACION;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getMTSCABLE() {
        return MTSCABLE;
    }

    public void setMTSCABLE(String MTSCABLE) {
        this.MTSCABLE = MTSCABLE;
    }

    public String getONUID() {
        return ONUID;
    }

    public void setONUID(String ONUID) {
        this.ONUID = ONUID;
    }

    public String getPUERTOSERVICIO() {
        return PUERTOSERVICIO;
    }

    public void setPUERTOSERVICIO(String PUERTOSERVICIO) {
        this.PUERTOSERVICIO = PUERTOSERVICIO;
    }

    public String getPonmac() {
        return ponmac;
    }

    public void setPonmac(String ponmac) {
        this.ponmac = ponmac;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getAbonado() {
        return Abonado;
    }

    public void setAbonado(String Abonado) {
        this.Abonado = Abonado;
    }

    public Clase_VistaCLientes(String ip, String Abonado) {
        this.ip = ip;
        this.Abonado = Abonado;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    List<Map<String, String>> rs2FIBRA;

    /*public boolean CorteFibra() throws MikrotikApiException {
        crearFO();
        Lista = "no-pago";
        try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
            con.login(usuario, pass1);
            if (con.isConnected()) {

                con.execute("/ip/firewall/address-list/add" + " list=" + Lista + "  address=" + ip);
                Thread.sleep(50);
                rs2FIBRA = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);
                rs2FIBRA.forEach((r1) -> {
                    if (r1.get("address").equals(ip)) {

                        try {
                            con.execute("/ip/firewall/address-list/set .id=" + r1.get(".id") + " comment=\"" + Abonado + "\"");
                        } catch (MikrotikApiException ex) {

                            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                return true;
            } else {
                return false;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }*/
    List<Map<String, String>> rsRADIO;

    public boolean CorteRadio() throws MikrotikApiException {
        crearRE();
        Lista = "no-pago";
        try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
            con.login(usuario, pass1);
            if (con.isConnected()) {
                con.execute("/ip/firewall/address-list/add" + " list=" + Lista + "  address=" + ip);
                Thread.sleep(50);
                rsRADIO = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);
                rsRADIO.forEach((r1) -> {

                    if (r1.get("address").equals(ip)) {
                        try {
                            con.execute("/ip/firewall/address-list/set .id=" + r1.get(".id") + " comment=\"" + Abonado + "\"");
                        } catch (MikrotikApiException ex) {
                            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                return true;
            } else {

                return false;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void AsignacionServicio(Button corte, Label estadoservicio, String Valor) {
        switch (Valor) {
            case "I":
                corte.setText("Activar Servicio");
                estadoservicio.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case "E":
                corte.setText("Deshabilitar Servicio");
                estadoservicio.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
            case "P":
                corte.setText("Deshabilitar Servicio");
                corte.setDisable(true);
                estadoservicio.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                break;

            default:
                corte.setText("Servicio no Encontrado");
                estadoservicio.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                break;
        }
    }

    public boolean ActivaServicioRE(boolean accion) throws MikrotikApiException {
        crearRE();
        Lista = "no-pago";
        listaip = new ArrayList();
        try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
            con.login(usuario, pass1);
            if (con.isConnected()) {
                List<Map<String, String>> rs1 = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);
                listaip.clear();
                rs1.forEach((r1) -> {
                    listaip.add(r1.get("address"));
                });
                if (listaip.contains(ip)) {

                    if (accion) {
                        for (Map<String, String> r1 : rs1) {
                            if (r1.get("address").equals(ip)) {
                                con.execute("/ip/firewall/address-list/remove  .id=" + r1.get(".id"));
                                return false;
                            }
                        }
                    }
                    return true;
                } else {
                    if (accion) {
                        con.execute("/ip/firewall/address-list/add" + " list=" + Lista + "  address=" + ip);
                        List<Map<String, String>> rs2 = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);
                        rs2.forEach((r1) -> {
                            if (r1.get("address").equals(ip)) {

                                try {
                                    con.execute("/ip/firewall/address-list/set .id=" + r1.get(".id") + " comment=\"" + Abonado + "\"");
                                } catch (MikrotikApiException ex) {
                                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        });
                        return true;
                    }
                    return false;
                }
            } else {
                System.out.println("no conectado");
                return false;

            }
        }
    }
    String EsTADOCOne = "", ULTIMA = "";
    ResultSet olstr;

    public boolean ActivaServicioFO(boolean accion, String IDCLIENTE) {

        /*   crearFO();
        Lista = "no-pago";
        listaip = new ArrayList();
        try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
            con.login(usuario, pass1);
            if (con.isConnected()) {
                List<Map<String, String>> rs1 = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);
                listaip.clear();
                rs1.forEach((r1) -> {
                    listaip.add(r1.get("address"));
                });
                if (listaip.contains(ip)) {
                    if (accion) {
                        for (Map<String, String> r1 : rs1) {
                            if (r1.get("address").equals(ip)) {
                                con.execute("/ip/firewall/address-list/remove  .id=" + r1.get(".id"));
                                return false;
                            }
                        }
                    }
                    return true;
                } else {
                    if (accion) {
                        con.execute("/ip/firewall/address-list/add" + " list=" + Lista + "  address=" + ip);
                        List<Map<String, String>> rs2 = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);
                        rs2.forEach((r1) -> {
                            if (r1.get("address").equals(ip)) {

                                try {
                                    con.execute("/ip/firewall/address-list/set .id=" + r1.get(".id") + " comment=\"" + Abonado + "\"");
                                } catch (MikrotikApiException ex) {
                                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        });
                        return true;
                    }
                    return false;
                }
            } else {
                System.out.println("no conectado");
                return false;
            }
        }
         */
        olstr = q.tablas("select  nodoprincipal,"
                + "OLTTARJETA,"
                + "OLTPON,"
                + "ONUID,"
                + "ESTADOSERVICIO,"
                + "ULTIMOCAMBIOESTADOSERVI from  [BDAirnet].[dbo].[TBCLIENTE]  where IDCLIENTE = '" + IDCLIENTE + "'");
        try {
            while (olstr.next()) {
                OLTTARJETA = (olstr.getString("OLTTARJETA"));
                OLTPON = (olstr.getString("OLTPON"));
                nodoprincipal = (olstr.getString("nodoprincipal"));
                ONUID = (olstr.getString("ONUID"));
                EsTADOCOne = (olstr.getString("ESTADOSERVICIO"));
                ULTIMA = (olstr.getString("ULTIMOCAMBIOESTADOSERVI"));
            }
            System.out.println(OLTTARJETA + "\t" + OLTPON + "\t" + nodoprincipal + "\t" + ONUID);
            if (OLTTARJETA != null && OLTPON != null && nodoprincipal != null && ONUID != null) {
                if (!OLTTARJETA.trim().isEmpty() && !OLTPON.trim().isEmpty() && !nodoprincipal.trim().isEmpty() && !ONUID.trim().isEmpty()) {
                    if (accion) {
                        try {
                            SelectorOLT(nodoprincipal, "C");
                            q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set ESTADOSERVICIO=1 ,ULTIMOCAMBIOESTADOSERVI=getdate()  where IDCLIENTE = '" + IDCLIENTE + "'");
                        return false;
                        } catch (InterruptedException ex) {
                           return true;
                        }
                        
                    }

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException ex) {

            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;

    }

    public boolean DesActivarServicioFO(boolean accion, String IDCLIENTE) {
        olstr = q.tablas("select  nodoprincipal,"
                + "OLTTARJETA,"
                + "OLTPON,"
                + "ONUID,"
                + "ESTADOSERVICIO,"
                + "ULTIMOCAMBIOESTADOSERVI from  [BDAirnet].[dbo].[TBCLIENTE]  where IDCLIENTE = '" + IDCLIENTE + "'");
        try {
            while (olstr.next()) {
                OLTTARJETA = (olstr.getString("OLTTARJETA"));
                OLTPON = (olstr.getString("OLTPON"));
                nodoprincipal = (olstr.getString("nodoprincipal"));
                ONUID = (olstr.getString("ONUID"));
                EsTADOCOne = (olstr.getString("ESTADOSERVICIO"));
                ULTIMA = (olstr.getString("ULTIMOCAMBIOESTADOSERVI"));
            }
          
            if (OLTTARJETA != null && OLTPON != null && nodoprincipal != null && ONUID != null) {
                if (!OLTTARJETA.trim().isEmpty() && !OLTPON.trim().isEmpty() && !nodoprincipal.trim().isEmpty() && !ONUID.trim().isEmpty()) {

                    if (accion) {

                        try {
                            SelectorOLT(nodoprincipal, "DC");
                            q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set ESTADOSERVICIO=0 ,ULTIMOCAMBIOESTADOSERVI=getdate()  where IDCLIENTE = '" + IDCLIENTE + "'");
                        return true;
                        } catch (InterruptedException ex) {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException ex) {

            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    //OLTS olt;
    OLT7OCTUBRE OLT7OC;
    OLTEMPLAME oltemplame;
    OLTGUAYABO oltguayabo;
    OLTQUEVEDO oltquevedo;
    OLTVALENCIA oltvalencia;
    OLTVENUS oltvenus;

    public void SelectorOLT(String OLTBASE, String MODO) throws InterruptedException {
        if (OLTBASE.contains("OLT OFICINA")) {
            Thread.sleep(20);
            oltquevedo = new OLTQUEVEDO(OLTBASE, "10.1.60.1", "", 22, "crisles", "0bilisam0-");
            {
                try {
                    oltquevedo.connect();
                    if (MODO.equals("C")) {
                        oltquevedo.executeCommand("enable \n "
                                + "config \n "
                                + "interface gpon 0/" + this.OLTTARJETA + " \n "
                                + "ont activate " + this.OLTPON + " " + this.ONUID);
                    } else {
                        oltquevedo.executeCommand("enable \n "
                                + "config \n "
                                + "interface gpon 0/" + this.OLTTARJETA + " \n "
                                + "ont deactivate " + this.OLTPON + " " + this.ONUID);
                    }

                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (OLTBASE.contains("OLT 7-OCTUBRE")) {
            Thread.sleep(20);
            OLT7OC = new OLT7OCTUBRE(OLTBASE, "10.1.61.2", "", 22, "crisles", "0bilisam0-");
            {
                try {
                    OLT7OC.connect();
                    if (MODO.equals("C")) {
                        OLT7OC.executeCommand("enable \n "
                                + "config \n "
                                + "interface gpon 0/" + this.OLTTARJETA + " \n "
                                + "ont activate " + this.OLTPON + " " + this.ONUID);
                    } else {
                        OLT7OC.executeCommand("enable \n "
                                + "config \n "
                                + "interface gpon 0/" + this.OLTTARJETA + " \n "
                                + "ont deactivate " + this.OLTPON + " " + this.ONUID);
                    }

                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (OLTBASE.contains("OLT VENUS")) {
            Thread.sleep(20);
            oltvenus = new OLTVENUS(OLTBASE, "10.1.60.3", "", 23, "crisles", "0bilisam0-");
            {
                oltvenus.connect();
                if (MODO.equals("C")) {
                    oltvenus.readUntil("MA5680T-VENUS>");
                    oltvenus.write("enable \n");
                    oltvenus.readUntil("MA5680T-VENUS#");
                    oltvenus.write("config \n");
                    oltvenus.readUntil("MA5680T-VENUS(config)#");
                    oltvenus.write("interface gpon 0/" + this.OLTTARJETA + " \n");
                    oltvenus.readUntil("MA5680T-VENUS(config-if-gpon-0/" + this.OLTTARJETA + ")#");
                    oltvenus.write("ont activate " + this.OLTPON + " " + this.ONUID + "\n");

                } else {
                    oltvenus.readUntil("MA5680T-VENUS>");
                    oltvenus.write("enable \n");
                    oltvenus.readUntil("MA5680T-VENUS#");
                    oltvenus.write("config \n");
                    oltvenus.readUntil("MA5680T-VENUS(config)#");
                    oltvenus.write("interface gpon 0/" + this.OLTTARJETA + " \n");
                    oltvenus.readUntil("MA5680T-VENUS(config-if-gpon-0/" + this.OLTTARJETA + ")#");
                    oltvenus.write("ont deactivate " + this.OLTPON + " " + this.ONUID + "\n");

                }
            }

        } else if (OLTBASE.contains("OLT VALENCIA")) {
            Thread.sleep(20);
            oltvalencia = new OLTVALENCIA(OLTBASE, "10.1.60.4", "", 22, "crisles", "0bilisam0-");
            {
                try {
                    oltvalencia.connect();
                    if (MODO.equals("C")) {
                        oltvalencia.executeCommand("enable \n "
                                + "config \n "
                                + "interface gpon 0/" + this.OLTTARJETA + " \n "
                                + "ont activate " + this.OLTPON + " " + this.ONUID);
                    } else {
                        oltvalencia.executeCommand("enable \n "
                                + "config \n "
                                + "interface gpon 0/" + this.OLTTARJETA + " \n "
                                + "ont deactivate " + this.OLTPON + " " + this.ONUID);
                    }

                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (OLTBASE.contains("OLT GUAYABO")) {
            Thread.sleep(20);
            oltguayabo = new OLTGUAYABO(OLTBASE, "10.1.60.5", "", 22, "crisles", "0bilisam0-");
            {
                try {
                    oltguayabo.connect();
                    if (MODO.equals("C")) {
                        oltguayabo.executeCommand("enable \n "
                                + "config \n "
                                + "interface gpon 0/" + this.OLTTARJETA + " \n "
                                + "ont activate " + this.OLTPON + " " + this.ONUID);
                    } else {
                        oltguayabo.executeCommand("enable \n "
                                + "config \n "
                                + "interface gpon 0/" + this.OLTTARJETA + " \n "
                                + "ont deactivate " + this.OLTPON + " " + this.ONUID);
                    }

                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (OLTBASE.contains("OLT EMPLAME")) {
            Thread.sleep(20);
            oltemplame = new OLTEMPLAME(OLTBASE, "10.1.60.6", "", 22, "crisles", "0bilisam0-");
            {
                try {
                    oltemplame.connect();
                    if (MODO.equals("C")) {
                        oltemplame.executeCommand("enable \n "
                                + "config \n "
                                + "interface gpon 0/" + this.OLTTARJETA + " \n "
                                + "ont activate " + this.OLTPON + " " + this.ONUID);
                    } else {
                        oltemplame.executeCommand("enable \n "
                                + "config \n "
                                + "interface gpon 0/" + this.OLTTARJETA + " \n "
                                + "ont deactivate " + this.OLTPON + " " + this.ONUID);
                    }

                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        /*
        switch (OLTBASE) {
            case "OLT OFICINA":
                olt = new OLTS(OLTBASE, "10.1.60.1", "", 22, "crisles", "0bilisam0-");
            {
                try {
                    olt.connect();
                    if(MODO.equals("C")){
                        olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont activate "+this.OLTPON+" "+this.ONUID);
                    }else {
                       olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont deactivate "+this.OLTPON+" "+this.ONUID); 
                    }
                    
                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
                break;

            case "OLT 7-OCTUBRE":
                 olt = new OLTS(OLTBASE, "10.1.60.2", "", 22, "crisles", "0bilisam0-");
            {
                try {
                    olt.connect();
                    if(MODO.equals("C")){
                        olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont activate "+this.OLTPON+" "+this.ONUID);
                    }else {
                       olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont deactivate "+this.OLTPON+" "+this.ONUID); 
                    }
                    
                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;
            case "OLT VENUS":
                   olt = new OLTS(OLTBASE, "10.1.60.3", "", 22, "crisles", "0bilisam0-");
            {
                try {
                    olt.connect();
                    if(MODO.equals("C")){
                        olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont activate "+this.OLTPON+" "+this.ONUID);
                    }else {
                       olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont deactivate "+this.OLTPON+" "+this.ONUID); 
                    }
                    
                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;
            case "OLT VALENCIA":
                   olt = new OLTS(OLTBASE, "10.1.60.4", "", 22, "crisles", "0bilisam0-");
            {
                try {
                    olt.connect();
                    if(MODO.equals("C")){
                        olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont activate "+this.OLTPON+" "+this.ONUID);
                    }else {
                       olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont deactivate "+this.OLTPON+" "+this.ONUID); 
                    }
                    
                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;
            case "4":
                
                break;
            case "OLT GUAYABO":
                   olt = new OLTS(OLTBASE, "10.1.60.5", "", 22, "crisles", "0bilisam0-");
            {
                try {
                    olt.connect();
                    if(MODO.equals("C")){
                        olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont activate "+this.OLTPON+" "+this.ONUID);
                    }else {
                       olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont deactivate "+this.OLTPON+" "+this.ONUID); 
                    }
                    
                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                break;
            case "OLT EMPLAME":
                    olt = new OLTS(OLTBASE, "10.1.60.6", "", 22, "crisles", "0bilisam0-");
                try {
                    olt.connect();
                    if(MODO.equals("C")){
                        olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont activate "+this.OLTPON+" "+this.ONUID);
                    }else {
                       olt.executeCommand("enable \n "
                    + "config \n "
                    + "interface gpon 0/"+this.OLTTARJETA+" \n "
                            + "ont deactivate "+this.OLTPON+" "+this.ONUID); 
                    }
                    
                } catch (JSchException | IllegalAccessException | IOException ex) {
                    Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }*/
    }

    /* public void ActivarFacturaFO(boolean accion) throws MikrotikApiException {
        crearFO();
        Lista = "no-pago";
        listaip = new ArrayList();
        try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
            con.login(usuario, pass1);
            if (con.isConnected()) {
                List<Map<String, String>> rs1 = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);
                listaip.clear();
                rs1.forEach((r1) -> {
                    listaip.add(r1.get("address"));
                });
                if (listaip.contains(ip)) {
                    if (accion) {
                        for (Map<String, String> r1 : rs1) {
                            if (r1.get("address").equals(ip)) {
                                con.execute("/ip/firewall/address-list/remove  .id=" + r1.get(".id"));

                            }
                        }
                    }

                }
            }
        }
    }*/
    public void ActivarFacturaRE(boolean accion) throws MikrotikApiException {
        crearRE();
        Lista = "no-pago";
        listaip = new ArrayList();
        try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
            con.login(usuario, pass1);
            if (con.isConnected()) {
                List<Map<String, String>> rs1 = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);
                listaip.clear();
                rs1.forEach((r1) -> {
                    listaip.add(r1.get("address"));
                });
                if (listaip.contains(ip)) {
                    if (accion) {
                        for (Map<String, String> r1 : rs1) {
                            if (r1.get("address").equals(ip)) {
                                con.execute("/ip/firewall/address-list/remove  .id=" + r1.get(".id"));

                            }
                        }
                    }

                }
            }
        }
    }

    /* public void obtenertlistaencorteFP(List obj) {
        crearFO();
        Lista = "no-pago";
        try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
            con.login(usuario, pass1);
            if (con.isConnected()) {
                List<Map<String, String>> rs1 = con.execute("/ip/firewall/address-list/print where" + " list=" + Lista);

                rs1.forEach((r1) -> {
                    obj.add(r1.get("address"));
                });
            }
        } catch (MikrotikApiException ex) {
            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
    int verificarordecuentas;

    public void CargarDatos() throws SQLException {
        verificarordecuentas = 0;
        if (Factorbusqueda.contains(".")) {
            verificarordecuentas = 3;
        } else {
            st = q.tablas("SELECT COUNT(*)AS CUANTOS FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] WHERE ([NOMBRES]+' '+[APELLIDOS]) like '%" + Factorbusqueda + "%'");
            while (st.next()) {
                if (st.getInt("CUANTOS") > 1) {
                    verificarordecuentas = 1;
                } else {
                    verificarordecuentas = 2;
                }
            }
        }
        BusquedaDatos();
    }

    public void BusquedaDatos() throws SQLException {
        IDCLIENTES = "N";
        switch (verificarordecuentas) {

            case 1:
                IDCLIENTES = "";
                st = q.tablas("SELECT IDCLIENTE FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] WHERE ([NOMBRES]+' '+[APELLIDOS]) like '%" + Factorbusqueda + "%'");
                while (st.next()) {

                    IDCLIENTES = (st.getString("IDCLIENTE")) + ";" + IDCLIENTES;
                }

                st = q.tablas("SELECT top(1) ESTADO, IDCLIENTE,[dis], [Comparticion],"
                        + "[CODIGOCLIENTE],[IDENTIFICACION],([NOMBRES]+' '+[APELLIDOS]) as NOMBRES "
                        + ",[CELULAR1],[CELULAR2],[EMAIL],[DIRECCION],[TIPOINSTALACION],[plancontratado],"
                        + "[VALORINTALACION],[VALORMENSUAL],[FECHAINSTALACION],[CODIGOCONTRATO],[lat],[lon],"
                        + "[ipv4],[MTSCABLE],[ONUID],[PUERTOSERVICIO],[PONMAC] FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG]  "
                        + "  where  ([NOMBRES]+' '+[APELLIDOS]) like '%" + Factorbusqueda + "%'");

                while (st.next()) {
                    cedula = (st.getString("IDENTIFICACION"));
                    Abonado = (st.getString("NOMBRES"));
                    CODIGOCONTRATO = st.getString("CODIGOCONTRATO").replace(" ", "");
                    IDCLIENTE = (st.getString("IDCLIENTE"));
                    EstadoCliente = (st.getBoolean("ESTADO"));
                }

                break;
            case 2:
                System.out.println("SELECT top(1) ESTADO, IDCLIENTE,[dis], [Comparticion],"
                        + "[CODIGOCLIENTE],[IDENTIFICACION],([NOMBRES]+' '+[APELLIDOS]) as NOMBRES "
                        + ",[CELULAR1],[CELULAR2],[EMAIL],[DIRECCION],[TIPOINSTALACION],[plancontratado],"
                        + "[VALORINTALACION],[VALORMENSUAL],[FECHAINSTALACION],[CODIGOCONTRATO],[lat],[lon],"
                        + "[ipv4],[MTSCABLE],[ONUID],[PUERTOSERVICIO],[PONMAC] FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG]  "
                        + "  where  ([NOMBRES]+' '+[APELLIDOS]) like '%" + Factorbusqueda + "%'");

                st = q.tablas("SELECT top(1) ESTADO,[IDCLIENTE]"
                        + ",[dis]"
                        + ",[Comparticion]"
                        + ",[CODIGOCLIENTE]"
                        + ",[IDENTIFICACION]"
                        + ",([NOMBRES]+' '+[APELLIDOS]) as NOMBRES"
                        + ",[CELULAR1]"
                        + ",[CELULAR2]"
                        + ",[EMAIL]"
                        + ",[DIRECCION]"
                        + ",[TIPOINSTALACION]"
                        + ",[plancontratado]"
                        + ",[VALORINTALACION]"
                        + ",[VALORMENSUAL]"
                        + ",[FECHAINSTALACION]"
                        + ",[CODIGOCONTRATO]"
                        + ",[lat]"
                        + ",[lon]"
                        + ",[ipv4]"
                        + ",[MTSCABLE]"
                        + ",[ONUID]"
                        + ",[PUERTOSERVICIO]"
                        + ",[PONMAC] ,[OLTTARJETA],[OLTPON],nodoprincipal FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG]   "
                        + " where  ([NOMBRES]+' '+[APELLIDOS]) like '%" + Factorbusqueda + "%'");

                while (st.next()) {
                    System.out.println(cedula = (st.getString("IDENTIFICACION")) + "\t"
                            + (st.getString("NOMBRES")) + "\t"
                            + st.getString("CODIGOCONTRATO").replace(" ", "") + "\t"
                            + (st.getString("IDCLIENTE")) + "\t"
                            + (st.getString("CELULAR1")) + "\t"
                            + (st.getString("CELULAR2")) + "\t"
                            + (st.getString("EMAIL")) + "\t"
                            + (st.getString("DIRECCION")) + "\t"
                            + (st.getString("TIPOINSTALACION")) + "\t"
                            + (st.getString("plancontratado")) + "\t"
                            + (st.getString("Comparticion")) + "\t"
                            + (st.getString("VALORINTALACION")) + "\t"
                            + (st.getString("VALORMENSUAL")) + "\t"
                            + (st.getString("FECHAINSTALACION")) + "\t"
                            + (st.getString("lat")) + "\t"
                            + (st.getString("lon")) + "\t"
                            + (st.getString("ipv4").replace(" ", "")) + "\t"
                            + (st.getString("MTSCABLE")) + "\t"
                            + (st.getString("ONUID")) + "\t"
                            + (st.getString("PUERTOSERVICIO")) + "\t"
                            + (st.getString("ponmac").replace(" ", "")) + "\t"
                            + (st.getBoolean("ESTADO")) + "\t"
                            + (st.getString("OLTTARJETA")) + "\t"
                            + (st.getString("OLTPON")) + "\t"
                            + (st.getString("nodoprincipal")));
                    cedula = (st.getString("IDENTIFICACION"));
                    Abonado = (st.getString("NOMBRES"));
                    CODIGOCONTRATO = st.getString("CODIGOCONTRATO");
                    IDCLIENTE = (st.getString("IDCLIENTE"));
                    CELULAR1 = (st.getString("CELULAR1"));
                    CELULAR2 = (st.getString("CELULAR2"));
                    EMAIL = (st.getString("EMAIL"));
                    DIRECCION = (st.getString("DIRECCION"));
                    TIPOINSTALACION = (st.getString("TIPOINSTALACION"));
                    plancontratado = (st.getString("plancontratado"));
                    Comparticion = (st.getString("Comparticion"));
                    VALORINTALACION = (st.getString("VALORINTALACION"));
                    VALORMENSUAL = (st.getString("VALORMENSUAL"));
                    FECHAINSTALACION = (st.getString("FECHAINSTALACION"));
                    lat = (st.getString("lat"));
                    lon = (st.getString("lon"));
                    ip = (st.getString("ipv4").replace(" ", ""));
                    MTSCABLE = (st.getString("MTSCABLE"));
                    ONUID = (st.getString("ONUID"));
                    PUERTOSERVICIO = (st.getString("PUERTOSERVICIO"));
                    ponmac = (st.getString("ponmac").replace(" ", ""));
                    EstadoCliente = (st.getBoolean("ESTADO"));
                    OLTTARJETA = (st.getString("OLTTARJETA"));
                    OLTPON = (st.getString("OLTPON"));
                    nodoprincipal = (st.getString("nodoprincipal"));

                    // Hartikectesactivos();
                }

                //   ControlServicio(false);
                //   FacturaCacelado();
                break;
            case 3:
                System.out.println("SELECT top(1) ESTADO, IDCLIENTE,[dis], [Comparticion],"
                        + "[CODIGOCLIENTE],[IDENTIFICACION],([NOMBRES]+' '+[APELLIDOS]) as NOMBRES "
                        + ",[CELULAR1],[CELULAR2],[EMAIL],[DIRECCION],[TIPOINSTALACION],[plancontratado],"
                        + "[VALORINTALACION],[VALORMENSUAL],[FECHAINSTALACION],[CODIGOCONTRATO],[lat],[lon],"
                        + "[ipv4],[MTSCABLE],[ONUID],[PUERTOSERVICIO],[PONMAC] FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG]  "
                        + "  where  ([NOMBRES]+' '+[APELLIDOS]) like '%" + Factorbusqueda + "%'");
                st = q.tablas("SELECT top(1) ESTADO,[IDCLIENTE]"
                        + ",[dis]"
                        + ",[Comparticion]"
                        + ",[CODIGOCLIENTE]"
                        + ",[IDENTIFICACION]"
                        + ",([NOMBRES]+' '+[APELLIDOS]) as NOMBRES "
                        + ",[CELULAR1]"
                        + ",[CELULAR2]"
                        + ",[EMAIL]"
                        + ",[DIRECCION]"
                        + ",[TIPOINSTALACION]"
                        + ",[plancontratado]"
                        + ",[VALORINTALACION]"
                        + ",[VALORMENSUAL]"
                        + ",[FECHAINSTALACION]"
                        + ",[CODIGOCONTRATO]"
                        + ",[lat]"
                        + ",[lon]"
                        + ",[ipv4]"
                        + ",[MTSCABLE]"
                        + ",[ONUID]"
                        + ",[PUERTOSERVICIO]"
                        + ",[PONMAC] ,[OLTTARJETA],[OLTPON] ,nodoprincipal FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG]    where [ipv4] like '%" + Factorbusqueda + "%'");

                while (st.next()) {
                    System.out.println(cedula = (st.getString("IDENTIFICACION")) + "\t"
                            + (st.getString("NOMBRES")) + "\t"
                            + st.getString("CODIGOCONTRATO").replace(" ", "") + "\t"
                            + (st.getString("IDCLIENTE")) + "\t"
                            + (st.getString("CELULAR1")) + "\t"
                            + (st.getString("CELULAR2")) + "\t"
                            + (st.getString("EMAIL")) + "\t"
                            + (st.getString("DIRECCION")) + "\t"
                            + (st.getString("TIPOINSTALACION")) + "\t"
                            + (st.getString("plancontratado")) + "\t"
                            + (st.getString("Comparticion")) + "\t"
                            + (st.getString("VALORINTALACION")) + "\t"
                            + (st.getString("VALORMENSUAL")) + "\t"
                            + (st.getString("FECHAINSTALACION")) + "\t"
                            + (st.getString("lat")) + "\t"
                            + (st.getString("lon")) + "\t"
                            + (st.getString("ipv4").replace(" ", "")) + "\t"
                            + (st.getString("MTSCABLE")) + "\t"
                            + (st.getString("ONUID")) + "\t"
                            + (st.getString("PUERTOSERVICIO")) + "\t"
                            + (st.getString("ponmac").replace(" ", "")) + "\t"
                            + (st.getBoolean("ESTADO")) + "\t"
                            + (st.getString("OLTTARJETA")) + "\t"
                            + (st.getString("OLTPON")) + "\t"
                            + (st.getString("nodoprincipal")));
                    cedula = (st.getString("IDENTIFICACION"));
                    Abonado = (st.getString("NOMBRES"));
                    CODIGOCONTRATO = st.getString("CODIGOCONTRATO").replace(" ", "");
                    IDCLIENTE = (st.getString("IDCLIENTE"));
                    CELULAR1 = (st.getString("CELULAR1"));
                    CELULAR2 = (st.getString("CELULAR2"));
                    EMAIL = (st.getString("EMAIL"));
                    DIRECCION = (st.getString("DIRECCION"));
                    TIPOINSTALACION = (st.getString("TIPOINSTALACION"));
                    plancontratado = (st.getString("plancontratado"));
                    Comparticion = (st.getString("Comparticion"));
                    VALORINTALACION = (st.getString("VALORINTALACION"));
                    VALORMENSUAL = (st.getString("VALORMENSUAL"));
                    FECHAINSTALACION = (st.getString("FECHAINSTALACION"));
                    lat = (st.getString("lat"));
                    lon = (st.getString("lon"));
                    ip = (st.getString("ipv4").replace(" ", ""));
                    MTSCABLE = (st.getString("MTSCABLE"));
                    ONUID = (st.getString("ONUID"));
                    PUERTOSERVICIO = (st.getString("PUERTOSERVICIO"));
                    ponmac = (st.getString("ponmac").replace(" ", ""));
                    EstadoCliente = (st.getBoolean("ESTADO"));
                    OLTTARJETA = (st.getString("OLTTARJETA"));
                    OLTPON = (st.getString("OLTPON"));
                    nodoprincipal = (st.getString("nodoprincipal"));

                    //    Hartikectesactivos();
                }

                //      ControlServicio(false);
                //      FacturaCacelado();
                break;

            default:
                break;
        }
    }

    public void funcionCombosplane() throws SQLException {
        st = q.tablas("SELECT top(1) ESTADO,[IDCLIENTE]"
                + ",[dis]"
                + ",[Comparticion]"
                + ",[CODIGOCLIENTE]"
                + ",[IDENTIFICACION]"
                + ",([NOMBRES]+' '+[APELLIDOS]) as NOMBRES "
                + ",[CELULAR1]"
                + ",[CELULAR2]"
                + ",[EMAIL]"
                + ",[DIRECCION]"
                + ",[TIPOINSTALACION]"
                + ",[plancontratado]"
                + ",[VALORINTALACION]"
                + ",[VALORMENSUAL]"
                + ",[FECHAINSTALACION]"
                + ",[CODIGOCONTRATO]"
                + ",[lat]"
                + ",[lon]"
                + ",[ipv4]"
                + ",[MTSCABLE]"
                + ",[ONUID]"
                + ",[PUERTOSERVICIO]"
                + ",[PONMAC] ,[OLTTARJETA],[OLTPON],nodoprincipal FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG]    where [IDCLIENTE]= '" + this.Abonado + "'");
        while (st.next()) {
            cedula = (st.getString("IDENTIFICACION"));
            Abonado = (st.getString("NOMBRES"));
            CODIGOCONTRATO = st.getString("CODIGOCONTRATO").replace(" ", "");
            IDCLIENTE = (st.getString("IDCLIENTE"));
            CELULAR1 = (st.getString("CELULAR1"));
            CELULAR2 = (st.getString("CELULAR2"));
            EMAIL = (st.getString("EMAIL"));
            DIRECCION = (st.getString("DIRECCION"));
            TIPOINSTALACION = (st.getString("TIPOINSTALACION"));
            plancontratado = (st.getString("plancontratado"));
            Comparticion = (st.getString("Comparticion"));
            VALORINTALACION = (st.getString("VALORINTALACION"));
            VALORMENSUAL = (st.getString("VALORMENSUAL"));
            FECHAINSTALACION = (st.getString("FECHAINSTALACION"));
            lat = (st.getString("lat"));
            lon = (st.getString("lon"));
            ip = (st.getString("ipv4").replace(" ", ""));
            MTSCABLE = (st.getString("MTSCABLE"));
            ONUID = (st.getString("ONUID"));
            PUERTOSERVICIO = (st.getString("PUERTOSERVICIO"));
            ponmac = (st.getString("ponmac").replace(" ", ""));
            EstadoCliente = (st.getBoolean("ESTADO"));
            OLTTARJETA = (st.getString("OLTTARJETA"));
            OLTPON = (st.getString("OLTPON"));
            nodoprincipal = (st.getString("nodoprincipal"));

        }

    }

    public boolean Hartikectesactivos() {
        st = q.tablas("select count(*) as tik from [BDAirnet].[dbo].[tickets] where estado='Espera' and respuesta=' ' and IDCLIENTE=" + this.IDCLIENTE);
        try {
            while (st.next()) {
                if (st.getInt("tik") > 0) {
                    ResultSet tike = q.tablas("select numero ,codigo from [BDAirnet].[dbo].[tickets] where estado='Espera' and respuesta=' ' and IDCLIENTE=" + this.IDCLIENTE);
                    while (tike.next()) {
                        tikectsmodulo = (tike.getString("numero").trim() + " - " + tike.getString("codigo").trim());
                        return true;

                    }
                } else {
                    return (false);
                }

            }
        } catch (SQLException ex) {
            return false;
        }
        return false;

    }

    public void FacturaCacelado() {

        st = q.tablas("SELECT nuemrofactura FROM [BDAirnet].[dbo].[tvdjFacurascliente] where codigocontrato like '%" + this.cedula + "%'");
        try {
            while (st.next()) {
                ultimopago = (st.getString("nuemrofactura").trim());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clase_VistaCLientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void valoresdecampos(TextField serial, TextField tipoins, TextField plan, boolean migracion) throws SQLException {

        Planesutilisables.clear();
        Serialdiponible.clear();
        TipodeinstalacionDisponibles.clear();

        st = q.tablas("SELECT [NOMBRE] FROM [BDAirnet].[dbo].[View_ListaPlanes]");
        while (st.next()) {
            Planesutilisables.add(st.getString("NOMBRE").trim());
        }
        if (migracion) {
            st = q.tablas("SELECT top (1) pon FROM [BDAirnet].[dbo].[tvproductosdj] where producto = 'ONT FIBRA OPTICA MIGRACION' and estado ='Inactivo'");
        } else {
            st = q.tablas("SELECT top (1) pon FROM [BDAirnet].[dbo].[tvproductosdj] where producto = 'ONT FIBRA OPTICA' and estado ='Inactivo'");
        }
        while (st.next()) {
            Serialdiponible.add(st.getString("pon").trim());
        }
        TipodeinstalacionDisponibles.add("Fibra Óptica".trim());
        TipodeinstalacionDisponibles.add("Radio Enlace".trim());
        q.prediccion(serial, Serialdiponible);
        q.prediccion(tipoins, TipodeinstalacionDisponibles);
        q.prediccion(plan, Planesutilisables);
    }

    /*
    public void crearFO() {
        q.cargarvaloresPropiedades();
        if (!q.properties.containsKey("IPMIKFIBRA")) {
            q.CreacionArchivo(true);
        }
        IpMicro = q.properties.getProperty("IPMIKFIBRA");
        usuario = q.properties.getProperty("USUARIOMIKRO");
        pass1 = q.properties.getProperty("PASSMIKRO");
    }
     */
    public void crearRE() {
        q.cargarvaloresPropiedades();
        if (!q.properties.containsKey("IPMIKAIRE")) {
            q.CreacionArchivo(true);
        }
        IpMicro = q.properties.getProperty("IPMIKAIRE");
        usuario = q.properties.getProperty("USUARIOMIKRO");
        pass1 = q.properties.getProperty("PASSMIKRO");
    }

    public int VerificarListasMi(String CodigoMikro) {
        if (CodigoMikro.equals("-")) {
            return 0;
        } else {
            if (CodigoMikro.contains("*")) {
                crearRE();
                try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
                    con.login(usuario, pass1);
                    if (con.isConnected()) {
                        List<Map<String, String>> rs1 = con.execute("/ip/firewall/address-list/print where" + " list=" + CodigoMikro.replace("*", ""));

                        return rs1.size();
                    }
                } catch (MikrotikApiException ex) {
                    return 0;
                }

            }/* else {
                crearFO();
                try (ApiConnection con = ApiConnection.connect(SocketFactory.getDefault(), IpMicro, ApiConnection.DEFAULT_PORT, 2000)) {
                    con.login(usuario, pass1);
                    if (con.isConnected()) {
                        List<Map<String, String>> rs1 = con.execute("/ip/firewall/address-list/print where" + " list=" + CodigoMikro);
                        return rs1.size();

                    }
                } catch (MikrotikApiException ex) {
                    return 0;
                }
            }*/
        }
        return 0;
    }
}
