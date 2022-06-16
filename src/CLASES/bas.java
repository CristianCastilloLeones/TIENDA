/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import javafxapplication4.Clase_VistaCLientes;
import javafxapplication4.VariablesDeUso;
import CLIENTES.NUEVOCLIENTEController;
import INDICADORES.EstadodeCuentasIndividualController;
import com.guigarage.responsive.ResponsiveHandler;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.jdom2.Document;
import org.jdom2.Element;
/**
 * ***
 */
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafxapplication4.JavaFXApplication4;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import me.legrange.mikrotik.MikrotikApiException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.jdom2.Content;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
  * @author cl
 */
public class bas extends ClasedePropiedades {

    Image iconoprincipal = new Image(getClass().getResourceAsStream("/imagenes/bot2.png"));
    private String NombreBD = "";
    List lista = new ArrayList();
    /*"BDAirnet";*/
    public ResultSet consulta;
    public ResultSet auxConsulta;
    private String DireccionBD = "";/*"131.196.15.242";*/
    private String UsuarioBD = "";/*"ISP";*/
    private String ClaveBD = "";/*"server@aircontrol@1";*/
    String SentenciaSQL;
    public Connection CanalBD;
    private Statement Instruccion;
    private ResultSet Resultado;
    private DecimalFormat formato1;
    Calendar fecha1 = Calendar.getInstance();
    java.util.Date fECHAfACTURA = new java.util.Date();
    SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss aa");
    SQLServerDataSource ds = new SQLServerDataSource();

    public void cargarvaloresPropiedades() {

        CreacionArchivo(false);
        DireccionBD = (properties.getProperty("RutaServidor"));
        NombreBD = (properties.getProperty("NombreBD"));
        UsuarioBD = (properties.getProperty("UsuarioBD"));
        ClaveBD = (properties.getProperty("ClaveBD"));
    }

    public ResultSet getResultado() {
        return Resultado;
    }

    public void setResultado(ResultSet Resultado) {
        this.Resultado = Resultado;
    }

    public boolean bas1() {
        //   CanalBD=null;
        try {
            nuevo();
            //     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //     this.CanalBD = DriverManager.getConnection(this.DireccionBD,UsuarioBD,ClaveBD);
            //     this.Instruccion = this.CanalBD.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            return true;
        } catch (SQLException SQLE) {

            return false; //    JOptionPane.showMessageDialog(null,"ERROR EN LA CONEXION CON BD\nERROR : " + SQLE.getMessage());
        }

    }

    public void InsertInsertar(String SentenciaSQL) {
        if (bas1()) {
            try {
                con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall(SentenciaSQL);
                cstmt.executeQuery();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            notificaciones("No existe conexion con la base de datos", "I");
        }
    }

    public void UpdateModificar(String SentenciaSQL) {
        if (bas1()) {
            try {
                con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall(SentenciaSQL);
                cstmt.executeQuery();
            } catch (SQLException e) {
               // System.out.println(e.getMessage());
            }
        } else {
            notificaciones("No existe conexion con la base de datos", "I");
        }
    }

    public void DeleteEliminar(String SentenciaSQL) {
        if (bas1()) {
            try {
                con = ds.getConnection();

                CallableStatement cstmt = con.prepareCall(SentenciaSQL);
                cstmt.executeQuery();
            } catch (SQLException e) {
            }
        } else {
            notificaciones("No existe conexion con la base de datos", "I");
        }
    }

    //-- ESTE METODO DEVUELVE UNA TABLA PARA MOSTRAR
    //-- PERO SI QUIERES LO PUEDES MODIFICAR PARA QUE
    //-- TE DEVUELVA UNA MATRIZ, O LO QUE QUIERAS
    public ResultSet tablas(String x) {

        Resultado = null;
        if (bas1()) {
            try {
                con = ds.getConnection();

                CallableStatement cstmt = con.prepareCall(x);
                Resultado = cstmt.executeQuery();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return Resultado;
        }
        return null;
    }

    public void nuevo() throws SQLServerException {
        cargarvaloresPropiedades();
        ds.setUser(UsuarioBD);
        ds.setPassword(ClaveBD);
        ds.setServerName(DireccionBD);
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName(NombreBD);
        ds.getConnection();

    }

    public Connection cone() throws SQLServerException {
        cargarvaloresPropiedades();
        ds.setUser(UsuarioBD);
        ds.setPassword(ClaveBD);
        ds.setServerName(DireccionBD);
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName(NombreBD);
        return ds.getConnection();
    }

    public void nuevocliente(String codigo, String cedula, String tipodocumento, String nombres, String apellidos, String direccion,
            String Celular1, String Celular2, boolean estado, String correo, Date fecha, boolean Discapacidad, boolean tarjeta, boolean Efectivo, boolean Deposito, boolean Cheque,
            Integer parroquia, String tipo) throws SQLServerException, SQLException, ParseException {

        if (bas1()) {

            con = ds.getConnection();

            //fromDate = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss a").parse(fecha);
            CallableStatement cstmt = con.prepareCall("{call procedure_tbCliente (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setInt("@IDCLIENTE", 0);
            cstmt.setInt("@IDSUCURSAL", 1);
            cstmt.setInt("IDCANTON", 2);
            cstmt.setString("@CODIGOCLIENTE", codigo);
            cstmt.setString("@TIPOIDENTIFICACION", tipodocumento);
            cstmt.setString("@IDENTIFICACION", cedula);
            cstmt.setString("@NOMBRES", nombres);
            cstmt.setString("@APELLIDOS", apellidos);
            cstmt.setString("@DIRECCION", direccion);
            cstmt.setString("@CELULAR1", Celular1);
            cstmt.setString("@CELULAR2", Celular2);
            cstmt.setString("@TELEFONO", Celular1);
            cstmt.setBoolean("@ESTADO", estado);
            cstmt.setString("@EMAIL", correo);
            cstmt.setString("@TIPOCLIENTE", "Paga");
            cstmt.setBoolean("@INTERNET", true);
            cstmt.setDate("@FECHAINGRESO", fecha);
            cstmt.setBoolean("@DISCAPACIDAD", Discapacidad);
            cstmt.setString("@PORCENTAJEDISCAPACIDAD", "0");
            cstmt.setBoolean("@TARJETA", tarjeta);
            cstmt.setBoolean("@EFECTIVO", Efectivo);
            cstmt.setBoolean("@CHEQUE", Cheque);
            cstmt.setString("@COMENTARIO", "");
            cstmt.setInt("@IDPARROQUIA", 1);
            cstmt.setDate("@FECHANACIMIENTO", fecha);
            cstmt.setString("@CLIENTEGLOBAL", "");
            cstmt.setString("@TIPO", "I");
            cstmt.execute();
        } else {
            notificaciones("No existe conexion con la base de datos", "I");
        }
    }

    public void nuevopersonal(int idempleado, int idsucursal, String tipoidentificacion, String identificacion, String Nombre, String Apellidos, boolean Estado, String Direccion, String Email, String Celular1,
            String Celular2, String fechanacimiento, String licencia, String sangre, boolean discapacidad, String porcentajediscapa, String Genero, String Estadocivil, int cargo, String tipohorario, String horainicio1, String horafin1, String horaini2, String horafin2,
            String tipo) {
        if (bas1()) {

            try {
                con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall("{call procedure_tbEmpleado (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                cstmt.setInt("@IDEMPLEADO", idempleado);
                cstmt.setInt("@IDSUCURSAL", idsucursal);
                cstmt.setString("@TIPOIDENTIFICACION", tipoidentificacion);
                cstmt.setString("@IDENTIFICACION", identificacion);
                cstmt.setString("@NOMBRES", Nombre);
                cstmt.setString("@APELLIDOS", Apellidos);
                cstmt.setBoolean("@ESTADO", Estado);
                cstmt.setString("@DIRECCION", Direccion);
                cstmt.setString("@EMAIL", Email);
                cstmt.setString("@CELULAR1", Celular1);
                cstmt.setString("@CELULAR2", Celular2);
                cstmt.setString("@TELEFONO", Celular1);
                cstmt.setDate("@FECHANACIMIENTO", Date.valueOf(fechanacimiento));
                cstmt.setString("@TIPOLICENCIA", licencia);
                cstmt.setString("@TIPOSANGRE", sangre);
                cstmt.setBoolean("@DISCAPACIDAD", discapacidad);
                cstmt.setString("@PORCENTAJEDISCAPACIDAD", porcentajediscapa);
                cstmt.setString("@GENERO", Genero);
                cstmt.setString("@ESTADOCIVIL", Estadocivil);
                cstmt.setInt("@IDCARGO", cargo);
                cstmt.setString("@TIPOHORARIO", tipohorario);
                cstmt.setString("@HORAINICIO1", horainicio1);
                cstmt.setString("@HORAFIN1", horafin1);
                cstmt.setString("@HORAINICIO2", horaini2);
                cstmt.setString("@HORAFIN2", horafin2);
                cstmt.setString("@TIPO", "I");
                cstmt.execute();

            } catch (SQLServerException ex) {
                Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            notificaciones("No existe conexion con la base de datos", "I");
        }
    }

    public boolean EjecutarPROCEDUREUsuario(int idusuario, String idempleado, String idtipousuario, String usuario, String clave, String Activo, String Factura, String tipo) {
        if (bas1()) {
            try {
                con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall("{call procedure_tbUsuario (?,?,?,?,?,?,?,?)}");

                if ("I".equals(tipo)) {
                    cstmt.setInt("@IDUSUARIO", 0);
                } else {
                    cstmt.setInt("@IDUSUARIO", idusuario);
                }
                cstmt.setString("@IDEMPLEADO", idempleado);
                cstmt.setString("@IDTIPOUSUARIO", idtipousuario);
                cstmt.setString("@USUARIO", usuario);
                cstmt.setString("@CLAVE", clave);
                cstmt.setString("@ACTIVO", Activo);
                cstmt.setString("@FACTURA", Factura);
                cstmt.setString("@TIPO", tipo);
                cstmt.execute();
                return true;
            } catch (SQLServerException ex) {
                return false;
            } catch (SQLException ex) {
                return false;
            }
        }
        return false;

    }

    public String ObtenerValorCampo(String campo, String tabla, String condicion) {

        if (bas1()) {
            try {

                String valor = "";

                String sentencia = "SELECT  " + campo + " from " + tabla + " " + condicion;
                Resultado = null;
                con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall(sentencia);
                Resultado = cstmt.executeQuery();

                while (Resultado.next()) {
                    valor = Resultado.getString("CODIGOCLIENTE");
                }
                return valor;
            } catch (SQLException ex) {
                return ex.getMessage();

            }
        }
        return "";
    }

    public String ObtenerValorCampo1(String campo, String tabla, String condicion) {
        if (bas1()) {
            try {

                String valor = "";

                String sentencia = "SELECT  " + campo + " from " + tabla + " " + condicion;
                Resultado = null;
                con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall(sentencia);
                Resultado = cstmt.executeQuery();

                while (Resultado.next()) {
                    valor = Resultado.getString("CODIGOCONTRATO");
                }
                return valor;
            } catch (SQLException ex) {
                return ex.getMessage();

            }
        }
        return "";
    }

    public String ObtenerValorCampo3(String campo, String tabla, String condicion) {
        if (bas1()) {
            try {

                String valor = "";

                String sentencia = "SELECT  " + campo + " from " + tabla + " " + condicion;
                Resultado = null;
                con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall(sentencia);
                Resultado = cstmt.executeQuery();

                while (Resultado.next()) {
                    valor = Resultado.getString(campo);
                }
                return valor;
            } catch (SQLException ex) {
                return ex.getMessage();

            }
        }
        return "";
    }

    public String ObtenerValorCampo4(String campo, String tabla, String condicion) {
        if (bas1()) {
            try {

                String valor = "";

                String sentencia = "SELECT  " + campo + " from " + tabla + " " + condicion;
                Resultado = null;
                con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall(sentencia);
                Resultado = cstmt.executeQuery();

                while (Resultado.next()) {
                    valor = Resultado.getString("TEXTO");
                }
                return valor;
            } catch (SQLException ex) {
                return ex.getMessage();

            }
        }
        return "";
    }

    public void Nuevocontrato(int nodo, int idcliente, String codigocontrato, String tipoinstalacion, Date fechaingreso, Float costoinstalacion, Float costomensual, boolean ippublica, int producto, String nuevocontra, String tipo) throws SQLServerException, SQLException {
        if (bas1()) {

            String año = (String.valueOf(fecha1.getTime().getYear() + 1901));
            String dia = (String.valueOf(fecha1.getTime().getDate()));
            String mes = (String.valueOf(fecha1.getTime().getMonth() + 1));
            con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("{call procedure_tbContrato (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setInt("@IDCONTRATO", 0);
            cstmt.setInt("@IDNODO", nodo);
            cstmt.setInt("@IDCLIENTE", idcliente);
            cstmt.setString("@CODIGOCONTRATO", codigocontrato);
            cstmt.setString("@TIPOINSTALACION", tipoinstalacion);
            cstmt.setDate("@FECHAINGRESO", fechaingreso);
            cstmt.setDate("@FECHAFIN", Date.valueOf(año + "-" + mes + "-" + dia + ""));
            cstmt.setString("@COMENTARIO", "");
            cstmt.setFloat("@VALORINSTALACION", costoinstalacion);
            cstmt.setFloat("@VALORMENSUAL", costomensual);
            cstmt.setBoolean("@IPPUBLICA", ippublica);
            cstmt.setInt("@IDPRODUCTO", producto);
            cstmt.setString("@NUMEROCONTRATONUEVO", nuevocontra);
            cstmt.setString("@TIPO", tipo);
            cstmt.execute();
        } else {
            notificaciones("No existe conexion con la base de datos", "I");
        }
    }

    public void EjecutarPROCEDUREPagoTorre(int idpagotorre, int pagorpoveedor, String detalle, float valortorre, float valorluz, String mes, String observacion, String numerorcibo) throws SQLException {

        if (bas1()) {
            String año = (String.valueOf(fecha1.getTime().getYear() + 1900));
            String dia = (String.valueOf(fecha1.getTime().getDate()));
            String mes1 = (String.valueOf(fecha1.getTime().getMonth() + 1));
            con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("{call procedure_tbPagoTorre (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cstmt.setInt("@IDPAGOTORRE", idpagotorre);
            cstmt.setInt("@IDPROVEEDOR", pagorpoveedor);
            cstmt.setString("@DETALLE", detalle);
            cstmt.setDate("@FECHA", Date.valueOf(año + "-" + mes1 + "-" + dia + ""));
            cstmt.setFloat("@VALORTORRE", valortorre);
            cstmt.setFloat("@VALORLUZ", valorluz);
            cstmt.setString("@MES", mes);
            cstmt.setBoolean("@PAGADO", true);
            cstmt.setInt("@IDEMPLEADO", ObtenerIdEmpleado());
            cstmt.setString("@OBSERVACION", observacion);
            cstmt.setBoolean("@IMPRESO", true);
            cstmt.setString("@NUMERORECIBO", numerorcibo);
            cstmt.setBoolean("@DINEROFUERACAJA", true);
            cstmt.setString("@OBSERVACIONENTREGADINERO", "");
            cstmt.setString("@SERIECAJA", serieCaja());
            cstmt.setString("@TIPO", "I");
            cstmt.execute();
        } else {
            notificaciones("No existe conexion con la base de datos", "I");
        }
    }

    public int ObtenerIdEmpleado() {
        Resultado = tablas("SELECT  [IDEMPLEADO]  FROM [BDAirnet].[dbo].[TBEMPLEADO] where ([NOMBRES]+' '+[APELLIDOS]) = '" + VariablesDeUso.nombreusuario + "'");
        try {
            while (Resultado.next()) {
                return Resultado.getInt("IDEMPLEADO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);

        }
        return 0;
    }

    public int ObtenerID(String campoID, String tabla, String condicion) {
        if (bas1()) {
            try {
                int ID = 0;
                String Sentencia;
                Sentencia = ("SELECT  max(" + campoID + ") AS id from " + tabla + "" + condicion);
                //Sentencia = new SqlCommand("SELECT IDENT_CURRENT ('"+ tabla + "')as "+ campoID + "" + condicion, ConexionBD.connection);
                Resultado = null;
                con = ds.getConnection();

                CallableStatement cstmt = con.prepareCall(Sentencia);

                Resultado = cstmt.executeQuery();

                while (Resultado.next()) {
                    ID = Integer.parseInt(Resultado.getString("id"));
                }
                return ID;
            } catch (NumberFormatException | SQLException ex) {
                return 0;

            }
        }
        return 0;
    }

    public void email(String receptor, String detalle, boolean fact, String archivo) {
        // final String username = "serviairnet.net@gmail.com";
        // final String password = "servicioairnet";
        CargarPropiedades();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", properties.getProperty("MailHost")/*"smtp.gmail.com"*/);
        props.put("mail.smtp.port", properties.getProperty("Puerto")/*"587"*/);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("CorreoEmisor"), properties.getProperty("ClaveCorreoEmisor"));
            }
        });

        try {

            // Define message
            if (receptor.replace(" ", "").contains("@") || !receptor.replace(" ", "").isEmpty()) {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(properties.getProperty("CorreoEmisor")));
                message.setSubject("Aviso de Visita Tecnica Airnet");
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
                message.setText(detalle);

                // Envia el mensaje
                Transport.send(message);
            } else {
                notificaciones("Fallo sendEmail al enviar Correo: ", "I");
            }
        } catch (MessagingException e) {
            notificaciones("Fallo sendEmail al enviar Correo: ", "I");
        }

    }

    public String leerxml(String clave) throws InterruptedException {
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\AUTORIZADOS\\" + clave + ".xml");
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Content> soapbody = rootNode.getContent();
            Element sp = (Element) soapbody.get(0);
            Element ns2 = sp.getChildren().get(0);
            Element resouesta = ns2.getChild("RespuestaAutorizacionComprobante");
            Element autori = resouesta.getChild("autorizaciones");
            Element art = autori.getChild("autorizacion");
            Element numero = art.getChild("numeroAutorizacion");
            Element estado = art.getChild("estado");
            Element fecha = art.getChild("fechaAutorizacion");
            return numero.getValue() + "-" + estado.getValue() + "-" + fecha.getValue().replace("-", "/") + "-";
        } catch (IOException | JDOMException io) {

            return "Error";
        }

    }

    public void Contrato(String IDCLIENTE,String codigocontrato, String Nombre, String identificacion, String telefono, String plancontratado, String fechainstalacion, String direccion) {
        String jr = null;
        Map parametro36 = new HashMap();
        Resultado = tablas("SELECT [IDEMPRESA],[CODIGO] ,[NOMBRE] ,[COMPARTICION] ,[DESCRIPCION] ,[CODIGOMIKROTIK],[ANCHOBANDA]"
                + " ,[DESCARGA] ,[SUBIDA] ,[CANTDISPOSITIVOS]FROM [BDAirnet].[dbo].[TBPLAN] where NOMBRE='" + plancontratado + "'");
        try {
            while (Resultado.next()) {
                float min = Resultado.getFloat("DESCARGA") / Float.parseFloat(Resultado.getString("COMPARTICION").replace(":1", ""));
                parametro36.put("CONTRATO", String.valueOf(codigocontrato));
                parametro36.put("VelocidadEfectiva", Resultado.getString("DESCARGA"));
                parametro36.put("Vminimacliente", min);
                parametro36.put("anchodebanda", Resultado.getString("DESCARGA"));//comparticion
                parametro36.put("comparticion", Resultado.getString("COMPARTICION"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
        auxConsulta = tablas("SELECT  count ([IDCLIENTE]) as cuentas  \n"
                + "FROM [BDAirnet].[dbo].[TablaPromociones] where [IDCLIENTE] ="+IDCLIENTE);
        String APLICAPROMO="";
        try {
            while(auxConsulta.next()){
               APLICAPROMO= auxConsulta.getInt("cuentas") >0?"_PROMO":"";
            }} catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }

        String jr3 = null;
        String Anexo1 = null;
        String Anexo2 = null;
        String Anexo3 = null;
        String Ademun = null;
        String Anexo4 = null;
        Map parametro = new HashMap();
        parametro.put("CONTRATO", String.valueOf(codigocontrato));
        Map parametro2 = new HashMap();
        parametro2.put("CONTRATO", String.valueOf(codigocontrato));
        Map parametro1 = new HashMap();
        parametro1.put("CONTRATO", String.valueOf(codigocontrato));
        parametro1.put("NOMBRES", Nombre);
        parametro1.put("IDENTIFICACION", identificacion);
        parametro1.put("CELULAR1", telefono);
        parametro1.put("PLANCONTRATADO", plancontratado);
        parametro1.put("FECHAINSTALACION", fechainstalacion);
        parametro1.put("DIRECCION", direccion);
        CargarPropiedades();

        try {
            //*****************************inpresion de contrato 1***********************************
            //  jr=getClass().getClassLoader().getResource("/javafxapplication4/contratocliente.jasper").toString();
            jr = properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\contratoclientePRUEBA"+APLICAPROMO+".jasper";
            String printFileName = JasperFillManager.fillReportToFile(jr, parametro, cone());
            pdfg(printFileName, 1, Nombre.replace(" ", ""));

            //  jr3=getClass().getClassLoader().getResource("/javafxapplication4/contratoparte3.jasper").toString();
            //   jr3 = "\\\\S-AIRCONTROL\\Nuevo Systema\\ArchivosdeImpresion\\contratoparte3.jasper";
            //   String parte3 = JasperFillManager.fillReportToFile(jr3, parametro, cone());
            //   pdfg(parte3, 2, Nombre.replace(" ", ""));
            //*****************************inpresion ANEXO1***********************************
            Anexo1 = properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\Anexo1"+APLICAPROMO+".jasper";
            String a1 = JasperFillManager.fillReportToFile(Anexo1, parametro36, cone());
            pdfg(a1, 3, Nombre.replace(" ", ""));
            //*****************************inpresion ANEXO2***********************************

            Anexo2 = properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\Anexo2.jasper";
            String a2 = JasperFillManager.fillReportToFile(Anexo2, parametro2, cone());
            pdfg(a2, 4, Nombre.replace(" ", ""));
            //*****************************inpresion ANEXO3***********************************
            Anexo3 = properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\Anexo3.jasper";
            String a3 = JasperFillManager.fillReportToFile(Anexo3, parametro, cone());
            pdfg(a3, 5, Nombre.replace(" ", ""));
            //*****************************inpresion ADENDUM***********************************
            Ademun = properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\Adendum.jasper";
            String a4 = JasperFillManager.fillReportToFile(Ademun, parametro2, cone());
            pdfg(a4, 6, Nombre.replace(" ", ""));
            //*****************************inpresion ANEXO4***********************************
            Anexo4 = properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\Anexo4.jasper";
            String a5 = JasperFillManager.fillReportToFile(Anexo4, parametro1, cone());
            pdfg(a5, 7, Nombre.replace(" ", ""));
            List<InputStream> pdfs = new ArrayList<InputStream>();
            pdfs.add(new FileInputStream(properties.getProperty("RutaArchivos") + "\\Clientes\\" + Nombre.replace(" ", "") + "\\" + Nombre.replace(" ", "") + "1.pdf"));
            //  pdfs.add(new FileInputStream("\\\\S-AIRCONTROL\\Nuevo Systema\\ArchivosdeImpresion\\parte2G.pdf"));
            //  pdfs.add(new FileInputStream("\\\\S-AIRCONTROL\\Nuevo Systema\\Clientes\\" + Nombre.replace(" ", "") + "\\" + Nombre.replace(" ", "") + "3.pdf"));
            pdfs.add(new FileInputStream(properties.getProperty("RutaArchivos") + "\\Clientes\\" + Nombre.replace(" ", "") + "\\" + Nombre.replace(" ", "") + "Anex01.pdf"));
            pdfs.add(new FileInputStream(properties.getProperty("RutaArchivos") + "\\Clientes\\" + Nombre.replace(" ", "") + "\\" + Nombre.replace(" ", "") + "Anex02.pdf"));
            pdfs.add(new FileInputStream(properties.getProperty("RutaArchivos") + "\\Clientes\\" + Nombre.replace(" ", "") + "\\" + Nombre.replace(" ", "") + "Anex03.pdf"));
            pdfs.add(new FileInputStream(properties.getProperty("RutaArchivos") + "\\Clientes\\" + Nombre.replace(" ", "") + "\\" + Nombre.replace(" ", "") + "Adem.pdf"));
            pdfs.add(new FileInputStream(properties.getProperty("RutaArchivos") + "\\Clientes\\" + Nombre.replace(" ", "") + "\\" + Nombre.replace(" ", "") + "Anex04.pdf"));
            FileOutputStream output = new FileOutputStream(properties.getProperty("RutaArchivos") + "\\Clientes\\" + Nombre.replace(" ", "") + "\\" + Nombre.replace(" ", "") + ".pdf");

            concatPDFs(pdfs, output, true);
        } catch (JRException | SQLServerException | IOException ex) {
            Logger.getLogger(NUEVOCLIENTEController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pdfg(String printFileName, int c, String Cliente) throws IOException {

        try {

            if (printFileName != null) {

                switch (c) {
                    case 1:
                        JasperExportManager.exportReportToPdfFile(printFileName, properties.getProperty("RutaArchivos") + "\\Clientes\\" + Cliente + "\\" + Cliente + "1.pdf");
                        break;
                    case 2:
                        JasperExportManager.exportReportToPdfFile(printFileName, properties.getProperty("RutaArchivos") + "\\Clientes\\" + Cliente + "\\" + Cliente + "3.pdf");
                        break;
                    case 3:
                        JasperExportManager.exportReportToPdfFile(printFileName, properties.getProperty("RutaArchivos") + "\\Clientes\\" + Cliente + "\\" + Cliente + "Anex01.pdf");
                        break;
                    case 4:
                        JasperExportManager.exportReportToPdfFile(printFileName, properties.getProperty("RutaArchivos") + "\\Clientes\\" + Cliente + "\\" + Cliente + "Anex02.pdf");
                        break;
                    case 5:
                        JasperExportManager.exportReportToPdfFile(printFileName, properties.getProperty("RutaArchivos") + "\\Clientes\\" + Cliente + "\\" + Cliente + "Anex03.pdf");
                        break;
                    case 6:
                        JasperExportManager.exportReportToPdfFile(printFileName, properties.getProperty("RutaArchivos") + "\\Clientes\\" + Cliente + "\\" + Cliente + "Adem.pdf");
                        break;
                    case 7:
                        JasperExportManager.exportReportToPdfFile(printFileName, properties.getProperty("RutaArchivos") + "\\Clientes\\" + Cliente + "\\" + Cliente + "Anex04.pdf");
                        break;

                }
            }

        } catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }

    public void concatPDFs(List<InputStream> streamOfPDFFiles,
            OutputStream outputStream, boolean paginate) {

        com.lowagie.text.Document document = new com.lowagie.text.Document();
        try {
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();

            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }

            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            PdfContentByte cb = writer.getDirectContent();

            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();

                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {

                    Rectangle rectangle = pdfReader.getPageSizeWithRotation(1);
                    document.setPageSize(rectangle);
                    document.newPage();

                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader,
                            pageOfCurrentReaderPDF);
                    switch (rectangle.getRotation()) {
                        case 0:
                            cb.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
                            break;
                        case 90:
                            cb.addTemplate(page, 0, -1f, 1f, 0, 0, pdfReader
                                    .getPageSizeWithRotation(1).getHeight());
                            break;
                        case 180:
                            cb.addTemplate(page, -1f, 0, 0, -1f, 0, 0);
                            break;
                        case 270:
                            cb.addTemplate(page, 0, 1.0F, -1.0F, 0, pdfReader
                                    .getPageSizeWithRotation(1).getWidth(), 0);
                            break;
                        default:
                            break;
                    }
                    if (paginate) {
                        cb.beginText();
                        cb.getPdfDocument().getPageSize();
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (DocumentException | IOException e) {
        } finally {
            if (document.isOpen()) {
                document.close();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ioe) {
            }
        }
    }

    public void Facturar(String ciente, String costomensual, String detalle, int discayter, String condigontrato, String cantidad, String tipo) {

        double total = 0;
        if ("I".equals(tipo)) {
            total = (Double.parseDouble(costomensual)) * Integer.parseInt(cantidad);
        } else {
            total = ((Double.parseDouble(costomensual)) / discayter) * Integer.parseInt(cantidad);
        }
        if (bas1()) {

            InsertInsertar("INSERT INTO [BDAirnet].[dbo].[detallesfacxtura] ([detalle] ,[valorunitario],[valortotal],[cliente],[cantidad],[Estado],[codigo],FECHADEORIGEN,QUIENGENERA)"
                    + "VALUES('"
                    + detalle + "','"
                    + costomensual + "','"
                    + FormatoDecimal(total) + "','"
                    + ciente + "','"
                    + cantidad + "','" + "1" + "','"
                    + condigontrato + "',getdate(),'" + VariablesDeUso.nombreusuario + "')");
        } else {
            notificaciones("No existe conexion con la base de datos", "I");
        }
    }

    public boolean VerificarCedula(String identificacion) {
        boolean estado = false;

        char[] valced = new char[13];
        try {
            int provincia;
            if (identificacion.length() >= 10) {
                valced = identificacion.trim().toCharArray();
                provincia = Integer.parseInt((String.valueOf(valced[0]) + String.valueOf(valced[1])));
                if (provincia > 0 && provincia < 25) {
                    if (Integer.parseInt(String.valueOf(valced[2])) < 6) {
                        estado = VerificaCedula(valced);
                    } else if (Integer.parseInt(String.valueOf(valced[2])) == 6) {
                        estado = VerificaSectorPublico(valced);
                    } else if (Integer.parseInt(String.valueOf(valced[2])) == 9) {
                        estado = VerificaPersonaJuridica(valced);

                    }
                }
            }
        } catch (NumberFormatException e) {
            return estado;
        }
        return estado;
    }

    public boolean VerificaPersonaJuridica(char[] validarCedula) {
        int aux = 0, prod, veri;
        try {
            veri = Integer.parseInt(String.valueOf(validarCedula[10])) + Integer.parseInt(String.valueOf(validarCedula[11])) + Integer.parseInt(String.valueOf(validarCedula[12]));
            if (veri > 0) {
                int[] coeficiente = new int[]{4, 3, 2, 7, 6, 5, 4, 3, 2};
                for (int i = 0; i < 9; i++) {
                    prod = Integer.parseInt(String.valueOf(validarCedula[i])) * coeficiente[i];
                    aux += prod;
                }
                if (aux % 11 == 0) {
                    veri = 0;
                } else if (aux % 11 == 1) {
                    return false;
                } else {
                    aux = aux % 11;
                    veri = 11 - aux;
                }

                if (veri == Integer.parseInt(String.valueOf(validarCedula[9]))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean VerificaSectorPublico(char[] validarCedula) {
        int aux = 0, prod, veri;
        try {
            veri = Integer.parseInt(String.valueOf(validarCedula[9])) + Integer.parseInt(String.valueOf(validarCedula[10])) + Integer.parseInt(String.valueOf(validarCedula[11])) + Integer.parseInt(String.valueOf(validarCedula[12]));
            if (veri > 0) {
                int[] coeficiente = new int[]{3, 2, 7, 6, 5, 4, 3, 2};

                for (int i = 0; i < 8; i++) {
                    prod = Integer.parseInt(String.valueOf(validarCedula[i])) * coeficiente[i];
                    aux += prod;
                }

                if (aux % 11 == 0) {
                    veri = 0;
                } else if (aux % 11 == 1) {
                    return false;
                } else {
                    aux = aux % 11;
                    veri = 11 - aux;
                }

                if (veri == Integer.parseInt(String.valueOf(validarCedula[8]))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean VerificaCedula(char[] validarCedula) {
        int aux = 0, par = 0, impar = 0, verifi;
        for (int i = 0; i < 9; i += 2) {
            aux = 2 * Integer.parseInt(String.valueOf(validarCedula[i]));
            if (aux > 9) {
                aux -= 9;
            }
            par += aux;
        }
        for (int i = 1; i < 9; i += 2) {
            impar += Integer.parseInt(String.valueOf(validarCedula[i]));
        }

        aux = par + impar;
        if (aux % 10 != 0) {
            verifi = 10 - (aux % 10);
        } else {
            verifi = 0;
        }
        if (verifi == Integer.parseInt(String.valueOf(validarCedula[9]))) {
            return true;
        } else {
            return false;
        }
    }

    public String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "%1$.2x" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    Notifications notifications;
    ImageView imageView;

    public void notificaciones(String aviso, String tipo) {
        // ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/imagenes/bot2.png")));
        imageView = new ImageView(new Image(getClass().getResourceAsStream("/imagenes/bot2.png")));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        if (tipo.equals("I")) {
            notifications = Notifications.create()
                    .title("Informacion AIRNET")
                    .text(aviso)
                    .graphic(imageView)
                    .hideAfter(Duration.seconds(8))
                    .position(Pos.BASELINE_RIGHT);
            notifications.darkStyle();
            notifications.show();
        } else {
            notifications = Notifications.create()
                    .title("Informacion AIRNET")
                    .text(aviso)
                    .graphic(imageView)
                    .hideAfter(Duration.seconds(8))
                    .position(Pos.BASELINE_RIGHT);

            notifications.darkStyle();
            notifications.show();
        }
    }

    public void listaclient(List list) {
        list.clear();

        if (bas1()) {
            try {
                Resultado = tablas(" SELECT  ([NOMBRES]+' '+[APELLIDOS]) as NOMBRES FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] group by ([NOMBRES]+' '+[APELLIDOS]) ");
                while (Resultado.next()) {
                    list.add(Resultado.getString("NOMBRES").trim());
                }
                Resultado = tablas("SELECT ipv4 FROM  [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where ipv4!=' ' ");
                while (Resultado.next()) {
                    // parts = cl.getString("NOMBRE").split(" ");
                    list.add(Resultado.getString("ipv4").trim());
                }

                //  notificaciones("Lista Actualizada", "I");
            } catch (SQLException ex) {
                notificaciones(ex.getMessage(), "");
            }
        }
    }

    public void Conseguiri(String cliente) {
        //  String consulta = "SELECT top(1) [ipv4] from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where IDENTIFICACION = '" + cliente + "'";
        if (bas1()) {
            Resultado = tablas("SELECT top(10)IDCLIENTE, [ipv4] from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where IDENTIFICACION = '" + cliente + "'");
            try {
                while (Resultado.next()) {
                    if (Resultado.getString("ipv4") == null) {
                    } else {
                        //    return consegui.getString("ipv4");  
                        ControlServicio(true, Resultado.getString("ipv4").replace(" ", ""),Resultado.getString("IDCLIENTE"));
                    }
                }
            } catch (SQLException ex) {
                notificaciones("Error de Motor de Base de Datos", "I");
            }
        }
    }

    public void ConseguiriACTIVARPORID(String IDCLIENTE) {
        //  String consulta = "SELECT top(1) [ipv4] from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where IDENTIFICACION = '" + cliente + "'";
        if (bas1()) {
            Resultado = tablas("SELECT top(1) [ipv4] from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where IDCLIENTE = " + IDCLIENTE);
            try {
                while (Resultado.next()) {
                    if (Resultado.getString("ipv4") == null) {
                    } else {
                        //    return consegui.getString("ipv4");  
                        ControlServicio(true, Resultado.getString("ipv4").replace(" ", ""),IDCLIENTE);
                    }
                }
            } catch (SQLException ex) {
                notificaciones("Error de Motor de Base de Datos", "I");
            }
        }
    }

    public boolean VerificadorEstadoMicroGeneradorFactura(String IDCLIENTE) {
        //  String consulta = "SELECT top(1) [ipv4] from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where IDENTIFICACION = '" + cliente + "'";
        String ip = null;
        bas1();
        Resultado = tablas("SELECT top(1) [ipv4] from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where IDCLIENTE = " + IDCLIENTE);
        try {
            while (Resultado.next()) {
                if (Resultado.getString("ipv4") == null) {
                } else {
                    ip = Resultado.getString("ipv4");
                    //    return consegui.getString("ipv4");  
                }
            }
        } catch (SQLException ex) {
            notificaciones("Error de Motor de Base de Datos", "I");
        }

        if (ip == (null)) {
            return false;
        } else {
            Clase_VistaCLientes ob = new Clase_VistaCLientes(ip, "");

            if (ip.contains("172.16")) {
                try {
                    ob.ActivarFacturaRE(false);
                    if (ob.listaip.contains(ip)) {
                        return true;
                    } else {
                        return false;
                    }

                } catch (MikrotikApiException ex) {
                    notificaciones("Error al Conectar el MikrotikApi " + ex, "I");
                }
            } else if (ip.contains("172.")) {
              //  ob.ActivaServicioFO(false,IDCLIENTE,false);
                if (ob.listaip.contains(ip)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

    }

    public void ConseguiriCorte(String cliente) {
        //  String consulta = "SELECT top(1) [ipv4] from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where IDENTIFICACION = '" + cliente + "'";

        if (bas1()) {
            Resultado = tablas("SELECT top(10) [ipv4] from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where IDENTIFICACION = '" + BusquedaCedula(cliente) + "'");
            try {
                while (Resultado.next()) {
                    if (Resultado.getString("ipv4") == null) {
                    } else {
                        //    return consegui.getString("ipv4");  
                        ControlServicioAF(true, Resultado.getString("ipv4").replace(" ", ""), cliente);
                    }
                }
            } catch (SQLException ex) {
                notificaciones("Error de Motor de Base de Datos", "I");
            }
        }
    }

    private void ControlServicioAF(boolean datos, String ip, String Abonado) {

        if (ip == (null)) {

        } else {
            Clase_VistaCLientes ob = new Clase_VistaCLientes(ip, Abonado);

            if (ip.contains("172.16")) {
                try {
                    ob.CorteRadio();
                } catch (MikrotikApiException ex) {
                    notificaciones("Error al Conectar el MikrotikApi " + ex, "I");
                }
            }/* else if (ip.contains("172.")) {
                try {
                    ob.CorteFibra();
                } catch (MikrotikApiException ex) {
                    notificaciones("Error al Conectar el MikrotikApi " + ex, "I");
                }
            }*/
        }

    }

    private void ControlServicio(boolean datos, String ip,String IDC) {

        if (ip == (null)) {

        } else {
            Clase_VistaCLientes ob = new Clase_VistaCLientes(ip, "");

            if (ip.contains("172.16")) {
                try {
                    ob.ActivarFacturaRE(datos);
                } catch (MikrotikApiException ex) {
                    notificaciones("Error al Conectar el MikrotikApi " + ex, "I");
                }
            } else {
                ob.ActivaServicioFO(datos,IDC);
            }
        }

    }

    public void pdfg(String printFileName) throws IOException {
        try {
            CargarPropiedades();
            if (printFileName != null) {
                /**
                 * 1- export to PDF
                 */
                JasperExportManager.exportReportToPdfFile(printFileName,
                        properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\RMA.pdf");

                File objetofile = new File(properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\RMA.pdf");

                Desktop.getDesktop().open(objetofile);
            }
        } catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }

    public void pagotorres(String printFileName, String Numero) throws IOException {
        CargarPropiedades();
        try {

            if (printFileName != null) {
                /**
                 * 1- export to PDF
                 */
                JasperExportManager.exportReportToPdfFile(printFileName,
                        properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\pago_" + Numero + ".pdf");

                File objetofile = new File(properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\pago_" + Numero + ".pdf");

                Desktop.getDesktop().open(objetofile);
            }
        } catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }

    public String facturamaxima() {
        int valor = 0;
        if (bas1()) {
            try {
                String sentencia = "SELECT [VALORACTUAL] FROM [BDAirnet].[dbo].[tdvjSeriesCaja] where NOMBREUSUARIO='" + VariablesDeUso.nombreusuario + "'";
                Resultado = null;
                con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall(sentencia);
                Resultado = cstmt.executeQuery();

                while (Resultado.next()) {
                    valor = Resultado.getInt("VALORACTUAL") + 1;
                }
                String sc = String.valueOf(valor);
                if (sc.length() <= 9) {
                    for (int i = 1; i <= 9; i++) {
                        if (sc.length() == 9) {
                        } else {
                            sc = "0" + sc;
                        }
                    }
                }
                return String.valueOf(sc);
            } catch (SQLException ex) {
                return "0";

            }
        }
        return "0";
    }

    public String serieCaja() {
        String serie = null;
        try {
            if (bas1()) {
                Resultado = null;
                con = ds.getConnection();
                CallableStatement cstmt = con.prepareCall("SELECT SERIE1,SERIE2 FROM [BDAirnet].[dbo].[tdvjSeriesCaja] where NOMBREUSUARIO='" + VariablesDeUso.nombreusuario + "'");
                Resultado = cstmt.executeQuery();
                while (Resultado.next()) {
                    serie = Resultado.getString("SERIE1").replace(" ", "") + Resultado.getString("SERIE2").replace(" ", "");

                }
            }
            return serie;
        } catch (SQLServerException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serie;
    }
    Connection con;

    public ResultSet prueba(String Cedula) throws SQLServerException, SQLException {
        if (bas1()) {
            con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("{call CONSULTASDEFACTURAS (?)}");
            cstmt.setString("@CEDULA", Cedula);
            cstmt.execute();
            return cstmt.getResultSet();
        }
        return null;

    }

    public void prediccion(TextField cajadetexto, List lista) {
        TextFields.bindAutoCompletion(cajadetexto, lista);
    }

    public ResultSet Tikec(String Tipo) throws SQLServerException, SQLException {
        if (bas1()) {
            con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("{call Consulta_Ticktes (?)}");
            cstmt.setString("@TIPO", Tipo);
            cstmt.execute();
            return cstmt.getResultSet();
        }
        return null;

    }
    Stage stage;
    Stage stage1;

    public void ventanas(String FXML) {
        // iconoprincipal = new Image(getClass().getResourceAsStream("/imagenes/bot2.png"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));

        try {
            root1 = (Parent) fxmlLoader.load();
            scene = new Scene(root1);
            //          scene = new Scene(root1, java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - 10,
            //                  java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - 80);
            stage1 = new Stage();
            Estilos(root1);
            stage1.getIcons().add(iconoprincipal);
            stage1.setScene(scene);
            stage1.setMaximized(false);

            //stage1.resizableProperty().setValue(Boolean.FALSE);
            stage1.initModality(Modality.APPLICATION_MODAL);
            stage1.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Parent root1;
    Scene scene = null;

    public void ventanasAxu(String FXML, String Titulo) {

        //  iconoprincipal = new Image(getClass().getResourceAsStream("/imagenes/bot2.png"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        try {
            root1 = (Parent) fxmlLoader.load();
            scene = new Scene(root1, java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - 10,
                    java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - 60);
            Estilos(root1);

            stage = new Stage();
            stage.getIcons().add(iconoprincipal);
            stage.setScene(scene);
            stage.setTitle(Titulo);
            ResponsiveHandler.addResponsiveToWindow(stage);
            stage.resizableProperty().setValue(Boolean.TRUE);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setMaxHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
            stage.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
            stage.requestFocus();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double ConvertidorStringaDouble(String valor) {
        return Double.parseDouble(valor.replace(",", "."));
    }

    public String FormatoDecimal(double valor) {
        formato1 = new DecimalFormat("#0.00");
        return formato1.format(valor).replace(",", ".");
    }

    public String FormatoDecimal1(double valor) {
        formato1 = new DecimalFormat("#0.00");
        return formato1.format(valor).replace(",", ".");
    }

    public String Fechafacturas() {

        return fecha.format(fECHAfACTURA) + " " + hora.format(fECHAfACTURA);
    }

    public String FechaformatoCalendaerio() {
        return fecha.format(fecha1.getTime());
    }

    public String FechaformatoPromesa(String fechaentrante) {
        fecha = new SimpleDateFormat("yyyy-MM-dd");
        return fecha.format(fechaentrante);
    }

    public Calendar FechaActualCalendario() {
        fecha1 = Calendar.getInstance();
        return fecha1;
    }

    public String FechaformatCierredeCaja(String fechaentrante) {
        String[] fechaY = fechaentrante.split("-");
        return fechaY[2] + "-" + fechaY[1] + "-" + fechaY[0];
    }

    public void Existencia(String serie, String Cliente, String Contrato) {
        if (bas1()) {
            Resultado = tablas("SELECT COUNT (pon) AS VALOR  FROM [BDAirnet].[dbo].[tvproductosdj] where  pon='" + serie + "'");
            try {
                while (Resultado.next()) {
                    String consu1 = "INSERT INTO [BDAirnet].[dbo].[tvproductosdj]([producto],[proveedor],[precio],[lote],[ingreso],[estado],serie,macid,[pon],CONTRATO,cliente)VALUES(";
                    consu1 = consu1 + "'ONT FIBRA OPTICA','AIRNET by AIRFIBER','75.00','en uso'," + "getdate()" + ",'" + "Activo','" + serie + "','" + serie + "','" + serie + "','" + Contrato + "','" + Cliente + "')";
                    if (Resultado.getInt("VALOR") == 0) {
                        InsertInsertar(consu1);
                        //  return true;
                    } else {
                        //  return false;
                    }
                }
            } catch (SQLException ex) {
                //return false;
            }
        } else {
            notificaciones("No existe conexion con la Base de Datos", "I");
        }
        //  return false;
    }

    public String BusquedaCedula(String Nombre) {

        if (bas1()) {
            Resultado = tablas("SELECT top(1) IDENTIFICACION from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where ([NOMBRES]+' '+[APELLIDOS]) like '%" + Nombre + "%'");
            try {
                while (Resultado.next()) {
                    if (Resultado.getString("IDENTIFICACION") == null) {
                        return "0";
                    } else {
                        return Resultado.getString("IDENTIFICACION");
                    }
                }
            } catch (SQLException ex) {
                return "0";
            }
            return "0";
        }
        return "0";
    }

    public String BusquedaNombreporCedula(String Cedula) {

        if (bas1()) {
            Resultado = tablas("SELECT top(1) ([NOMBRES]+' '+[APELLIDOS]) as Cliente from [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] where  IDENTIFICACION = '" + Cedula + "'");
            try {
                while (Resultado.next()) {
                    if (Resultado.getString("Cliente") == null) {
                        return "0";
                    } else {
                        return Resultado.getString("Cliente");
                    }
                }
            } catch (SQLException ex) {
                return "0";
            }
            return "0";
        }
        return "0";
    }

    public void abrirRide(String clientetext) {
        CargarPropiedades();

        if (!clientetext.replace(" ", "").isEmpty()) {
            File objetofile = new File(properties.getProperty("RutaArchivos") + "\\Clientes\\" + clientetext.replace(" ", "") + "\\" + "Facturas");
            try {
                Desktop.getDesktop().open(objetofile);
            } catch (IOException ex) {
                Logger.getLogger(EstadodeCuentasIndividualController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Cajerosdisponibles(ComboBox<String> receptoremisor) {
        if (bas1()) {
            Resultado = tablas("SELECT [NOMBREUSUARIO] FROM [BDAirnet].[dbo].[tdvjSeriesCaja]");
            try {
                while (Resultado.next()) {
                    receptoremisor.getItems().add(Resultado.getString("NOMBREUSUARIO"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            notificaciones("No existe conexion con la Base de Datos", "I");
        }
    }

    public LocalDate FechaActual() {
        return LocalDate.now();
    }

    public void GuardarAperturaCaja(float montoinicial, String cajero, String fechalabor, String abiertopor) {
        InsertInsertar("INSERT INTO  [BDAirnet].[dbo].[tvdjCierre/aperturadeCaja]\n"
                + "           ( [montoinicial] ,[cajero],[fechadelabor],[Estado],[abiertopor])\n"
                + "     VALUES\n"
                + "           ( " + montoinicial + ",'" + cajero + "','" + fechalabor + "'," + 1 + ",'" + abiertopor + "')");
    }

    public String CargarValoresCerrar(String Fecha, String Cajero) {

        Resultado = tablas("select top(1) IDCIERRECAJA,montoinicial from  [BDAirnet].[dbo].[tvdjCierre/aperturadeCaja] where Estado=1 and  fechadelabor= '" + Fecha + "' and cajero like '%" + Cajero + "%'");
        try {
            while (Resultado.next()) {
                return Resultado.getString("IDCIERRECAJA") + ";" + Resultado.getString("montoinicial");
            }
        } catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return "";
    }

    public void CerrarCaja(int Ccvt1, int Ccvt5, int Ccvt10, int Ccvt25, int Ccvt50, int Ccvt1D, int Cbille1, int Cbille5, int Cbille10, int Cbille25, int Cbille50,
            int Cbille100, float totalctv1, float totalctv5, float totalctv10, float totalctv25, float totalctv50, int totalctv1Dola, int TotalBille1, int TotalBille5,
            int TotalBille10, int TotalBille20, int TotalBille50, int TotalBille100, float totalefectivo, int cantceq, float valorcheqes, float totalmoneda, float billetes, String idcierre, int cantidadvouche, float totalvoucher) {
        UpdateModificar("UPDATE [BDAirnet].[dbo].[tvdjCierre/aperturadeCaja]\n"
                + "   SET [Cantctv1] = " + Ccvt1 + "\n"
                + "      ,[Cantctv5] = " + Ccvt5 + "\n"
                + "      ,[Cantctv10] = " + Ccvt10 + "\n"
                + "      ,[Cantctv25] = " + Ccvt25 + "\n"
                + "      ,[Cantctv50] = " + Ccvt50 + "\n"
                + "      ,[Cantdolar1] = " + Ccvt1D + "\n"
                + "      ,[totacantidadmonedas] = " + (Ccvt1 + Ccvt5 + Ccvt10 + Ccvt25 + Ccvt50 + Ccvt1D) + "\n"
                + "      ,[Cantbillete1] =" + Cbille1 + "\n"
                + "      ,[Cantbillete5] = " + Cbille5 + "\n"
                + "      ,[Cantbillete10] = " + Cbille10 + "\n"
                + "      ,[Cantbillete20] = " + Cbille25 + "\n"
                + "      ,[Cantbillete50] = " + Cbille50 + "\n"
                + "      ,[Cantbillete100] = " + Cbille100 + "\n"
                + "      ,[totalcantidadbilletes] = " + (Cbille100 + Cbille50 + Cbille25 + Cbille10 + Cbille5 + Cbille1) + "\n"
                + "      ,[totalctv1] = " + totalctv1 + "\n"
                + "      ,[totalctv5] = " + totalctv5 + "\n"
                + "      ,[totalctv10] =" + totalctv10 + "\n"
                + "      ,[totalctv25] = " + totalctv25 + "\n"
                + "      ,[totalctv50] = " + totalctv50 + "\n"
                + "      ,[totalctv1dolar] = " + totalctv1Dola + "\n"
                + "      ,[totalbillete1] =" + TotalBille1 + "\n"
                + "      ,[totalbillete5] = " + TotalBille5 + "\n"
                + "      ,[totalbillete10] = " + TotalBille10 + "\n"
                + "      ,[totalbillete20] = " + TotalBille20 + "\n"
                + "      ,[totalbillete50] = " + TotalBille50 + "\n"
                + "      ,[totalbillete100] = " + TotalBille100 + "\n"
                + "      ,[totalmonedad] = " + totalmoneda + "\n"
                + "      ,[totalbilletes] = " + billetes + "\n"
                + "      ,[totalefectivo] = " + totalefectivo + "\n"
                + "      ,[cantidadCheques] = " + cantceq + "\n"
                + "      ,[totalcheques] = " + valorcheqes + "\n"
                + "      ,[cantidadvouche] = " + cantidadvouche + "\n"
                + "      ,[totalvoucher] = " + totalvoucher + "\n"
                + "      ,[fechadecierre] = GETDATE()\n"
                + "      ,[Estado] = 0 \n"
                + "      ,[Cerradopor] = '" + VariablesDeUso.nombreusuario + "'\n"
                + "\n"
                + " WHERE [IDCIERRECAJA]='" + idcierre + "'");
    }

    public String IDEMPLEADO() {
        Resultado = tablas("SELECT top(1) [IDEMPLEADO] FROM [BDAirnet].[dbo].[TBEMPLEADO] where ([NOMBRES]+' '+[APELLIDOS])= '" + VariablesDeUso.nombreusuario + "'");
        try {
            while (Resultado.next()) {
                return Resultado.getString("IDEMPLEADO");
            }

        } catch (SQLException ex) {
            return "";
        }
        return "";
    }

    public void ActualizacionEmpledado(boolean editDireccion, String direccion, boolean edittelefono, String telefono, boolean ediemail, String email) {
        if (editDireccion && !direccion.isEmpty()) {
            //    direccion;
            UpdateModificar("update [BDAirnet].[dbo].[TBEMPLEADO] set  [DIRECCION] ='" + direccion + "' where ([NOMBRES]+' '+[APELLIDOS])= '" + VariablesDeUso.nombreusuario + "'");

        }
        if (edittelefono && !telefono.isEmpty()) {
            //    telefono;
            UpdateModificar("update [BDAirnet].[dbo].[TBEMPLEADO] set  [CELULAR1] ='" + telefono + "' where ([NOMBRES]+' '+[APELLIDOS])= '" + VariablesDeUso.nombreusuario + "'");
        }
        if (ediemail && !email.isEmpty()) {
            //    email;
            UpdateModificar("update [BDAirnet].[dbo].[TBEMPLEADO] set  [EMAIL] ='" + email + "' where ([NOMBRES]+' '+[APELLIDOS])= '" + VariablesDeUso.nombreusuario + "'");
        }
    }

    public String UpdateModificarDATOSAVISO(String SentenciaSQL) {
        if (bas1()) {
            try {
                con = ds.getConnection();

                try (CallableStatement cstmt = con.prepareCall(SentenciaSQL)) {
                    cstmt.executeQuery();
                    cstmt.close();
                }
                return "Edicion Existosa";
            } catch (SQLException e) {
                return e.getMessage();

            }
        }
        return "Error de Server";
    }

    public void abriarchivos(String NombreArchivos) {
        File objetofile = new File(NombreArchivos);
        if (objetofile.exists()) {
            try {
                Desktop.getDesktop().open(objetofile);

            } catch (IOException ex) {
                notificaciones("Se produjo un error de nivel : " + ex.getMessage(), "I");
            }
        } else {
            notificaciones("No se encuentra el archivo o Directorio", "I");
        }
    }

    public boolean BuscarDepositoRepetido(String numerodeposito) {
        SentenciaSQL = "";
        Resultado = tablas(" select [NUMEROFACTURA] FROM [BDAirnet].[dbo].[tvdjDepositos] WHERE [NUMERODECOMPROBANTE] like '%" + numerodeposito + "%'");
        try {
            while (Resultado.next()) {
                SentenciaSQL = Resultado.getString("NUMEROFACTURA") + " " + SentenciaSQL;
            }
        } catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (SentenciaSQL.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public List BuscarCajeros() {
        Resultado = tablas(" select  NOMBREUSUARIO FROM [BDAirnet].[dbo].[tdvjSeriesCaja]");
        try {
            while (Resultado.next()) {
                lista.add(Resultado.getString("NOMBREUSUARIO"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public String SerieCajeros(String Cajero) {
        String Serie = "";
        Resultado = tablas("  select [SERIE2] FROM [BDAirnet].[dbo].[tdvjSeriesCaja] where NOMBREUSUARIO ='" + Cajero + "'");
        try {
            while (Resultado.next()) {
                Serie = Resultado.getString("SERIE2");
            }
        } catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Serie;
    }

    public String BusquedadeEvidenciadeposito(String IDDEPOSITO) {
        Resultado = tablas("SELECT EVIDENCIADERESPALDO  FROM [BDAirnet].[dbo].[tvdjDepositos] where IDDEPOSITO=" + IDDEPOSITO);
        try {
            while (Resultado.next()) {
                SentenciaSQL = Resultado.getString("EVIDENCIADERESPALDO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SentenciaSQL;
    }
    DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    public String FechanuevoCliente() {
        return ft.format(fecha1.getTime());
    }

    public int NumerRecibo() {
        Resultado = tablas("SELECT (max([NUMERORECIBO])) as "
                + "SIGUIENTE  FROM [BDAirnet].[dbo].[tvdjReceptorDinero] where  [CAJERO] like '%" + VariablesDeUso.nombreusuario + "%'");
        try {
            while (Resultado.next()) {
                return Resultado.getInt("SIGUIENTE") + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean ExistenciadeCliente(String cedula) {
        auxConsulta = tablas("select count(IDENTIFICACION) as numero from [BDAirnet].[dbo].[TBCLIENTE] where ESTADO=0 and IDENTIFICACION='" + cedula + "'");
        try {
            while (auxConsulta.next()) {
                if (auxConsulta.getInt("numero") > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void InserDatosrecivo(int IDDETALLE, String DETALLE, int NUMERORECIBO, float VALORRECEPTADO, String CLIENTE, String CAJERO) {
        InsertInsertar("INSERT INTO  [BDAirnet].[dbo].[tvdjReceptorDinero]\n"
                + "           ([IDDETALLE]\n"
                + "           ,[DETALLE]\n"
                + "           ,[NUMERORECIBO]\n"
                + "           ,[VALORRECEPTADO]\n"
                + "           ,[FECHARECEPTADO]\n"
                + "           ,[CLIENTE]\n"
                + "           ,[CAJERO])\n"
                + "     VALUES\n"
                + "           (" + IDDETALLE + "\n"
                + "           ,'" + DETALLE + "'\n"
                + "           ," + NUMERORECIBO + "\n"
                + "           ," + VALORRECEPTADO + "\n"
                + "           ,getDate()\n"
                + "           ,'" + CLIENTE + "'\n"
                + "           ,'" + CAJERO + "')");
    }
    Node source;
    Stage stage2;

    public void CerrarVentanas(ActionEvent actionEvent) {
        source = (Node) actionEvent.getSource();
        stage2 = (Stage) source.getScene().getWindow();
        stage2.close();
    }

    public void Reponsive(Node node, String Tamaño) {
        switch (Tamaño) {
            case "P":
                node.getStyleClass().addAll("visible-lg", "visible-md");
                break;
            case "M":
                node.getStyleClass().addAll("visible-xs", "visible-sm");
                break;

        }
    }
    URL url = null;
    String css = null;

    public void Estilos(Parent we) {

        url = JavaFXApplication4.class.getResource("EstilosGenerales.css");

        if (url == null) {
            notificaciones("NO ENCUENTRA EL ARCHIVO DE ESTILO", "I'");

        }
        css = url.toExternalForm();
        we.getStylesheets().clear();
        we.getStylesheets().add(css);
    }

    public void CerrarVentanas(KeyEvent event) {
        source = (Node) event.getSource();
        stage1 = (Stage) source.getScene().getWindow();
        stage1.close();
    }

    public void animateMessage(Node success) {
        FadeTransition ft1 = new FadeTransition(Duration.millis(1000), success);
        ft1.setFromValue(0.0);
        ft1.setToValue(1);
        ft1.play();
    }
    CallableStatement cstmt1;

    public ResultSet Dedudas(int mes, String Cedula) {
        if (bas1()) {
            try {
                con = ds.getConnection();
                cstmt1 = con.prepareCall("{call Consultadevalores (?,?)}");
                cstmt1.setInt("@meses", mes);
                cstmt1.setString("@cedula", Cedula);
                cstmt1.execute();
                return cstmt1.getResultSet();
            } catch (SQLServerException ex) {
                Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(bas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

    private final Month MesActual = LocalDate.now().getMonth();
    private final int AñoActual = LocalDate.now().getYear();
    private final Locale tipo = new Locale("es", "ES");

    public String MesActualEspañol() {
        return MesActual.getDisplayName(TextStyle.FULL, tipo).toUpperCase();
    }

    public void CerrarVentanas(WindowEvent event) {
        source = (Node) event.getSource();
        stage1 = (Stage) source.getScene().getWindow();
        stage1.close();
    }

    public void escribirLog(String mensaje) {

        String rutaArchivo = "C:\\ARCHIVOSSISTEMA\\archivo.log";
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {
            fh = new FileHandler(rutaArchivo, true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.info(mensaje);
            fh.close();

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String NuevaIP(String tipo, boolean Action) {
        String ULTIMA = null, PRIMER = null, ACTUALIP = null;
        Resultado = tablas("SELECT [IPINICIAL],[IPFINAL] \n"
                + " ,[IPACTUAL],[FECHADEREGISTROL] \n"
                + "FROM [BDAirnet].[dbo].[Tabla_ListaIP]"
                + " WHERE PLANIPFIBRA like '%" + tipo + "%'");
        try {
            while (Resultado.next()) {
                ULTIMA = Resultado.getString("IPFINAL").replace(".", "-").trim() + "-A";
                PRIMER = Resultado.getString("IPINICIAL").replace(".", "-").trim() + "-A";
                ACTUALIP = Resultado.getString("IPACTUAL").replace(".", "-").trim() + "-A";
            }
        } catch (SQLException ex) {
            escribirLog(ex.getMessage());
        }
        if (ULTIMA == null || PRIMER == null || ACTUALIP == null) {
            return "0";
        }
        String[] IPINICIALDES = PRIMER.split("-");
        String[] IPULTIMA = ULTIMA.split("-");
        String[] IPACTUAL = ACTUALIP.split("-");
        if (Action) {
            if ((Integer.parseInt(IPACTUAL[3]) + 1) == Integer.parseInt(IPULTIMA[3])) {
                ACTUALIZARRANGO(IPINICIALDES, IPULTIMA, IPACTUAL, tipo);
            } else {
                UpdateModificar("UPDATE [BDAirnet].[dbo].[Tabla_ListaIP]\n"
                        + "   SET "
                        + "       [IPACTUAL] = '" + IPACTUAL[0] + "." + IPACTUAL[1] + "." + IPACTUAL[2] + "." + (Integer.parseInt(IPACTUAL[3]) + 1) + "'\n"
                        + "      ,[FECHADEREGISTROL] = getdate()\n"
                        + "      ,[QUIENREGISTRO] = '" + VariablesDeUso.nombreusuario + "'\n"
                        + " WHERE [PLANIPFIBRA] like '%" + tipo + "%'");
            }
        }

        return IPACTUAL[0] + "." + IPACTUAL[1] + "." + IPACTUAL[2] + "." + (Integer.parseInt(IPACTUAL[3]) + 1);
    }

    private void ACTUALIZARRANGO(String[] IPINICIALDES, String[] IPULTIMA, String[] IPACTUAL, String TIPO) {

        UpdateModificar("UPDATE [BDAirnet].[dbo].[Tabla_ListaIP]\n"
                + "   SET "
                + "      ,[IPINICIAL] = '" + IPINICIALDES[0] + "." + IPINICIALDES[1] + "." + (Integer.parseInt(IPINICIALDES[2]) + 1) + ".1'\n"
                + "      ,[IPFINAL] = '" + IPULTIMA[0] + "." + IPULTIMA[1] + "." + (Integer.parseInt(IPULTIMA[2]) + 1) + ".253'\n"
                + "      ,[IPACTUAL] = '" + IPACTUAL[0] + "." + IPACTUAL[1] + "." + (Integer.parseInt(IPACTUAL[2]) + 1) + ".1'\n"
                + "      ,[FECHADEREGISTROL] = getdate()\n"
                + "      ,[QUIENREGISTRO] = '" + VariablesDeUso.nombreusuario + "'\n"
                + " WHERE [PLANIPFIBRA] like '%" + TIPO + "'%");
    }

    public void IngresarSoloNumeros(char key, KeyEvent event) {
        try {
            if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0'|| key == '.') {

            } else {
                event.consume();
                notificaciones("Ingresar solo Digitos [0-9] ", "I");
            }

        } catch (Exception ex) {
            escribirLog(ex.getMessage());
        }
    }
    public void IngresarSoloLetras(char key, KeyEvent event) {
        try {
            if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0'|| key == '.'|| key == '-' || key == '*') {
               event.consume();
                notificaciones("Ingresar solo Letras [Aa-Zz] ", "I");
            } 

        } catch (Exception ex) {
            escribirLog(ex.getMessage());
        }
    }
    public int  ObtnerAñoActual(){
        java.util.Date date = new java.util.Date();

        ZoneId timeZone = ZoneId.systemDefault();
        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
     return getLocalDate.getYear();
        
    }
    public void emailVerficaciondeDatos(String detalle) {
        final String username = "facturacion@airfiber.ec";
        final String password = "pagofacil@2021@mail";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.airfiber.ec");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setSubject("Aviso de Facturacion");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("operaciones@airfiber.ec"));
            message.setText("El Cliente " + detalle + " no presenta una direccion correo porfavor condine con el cliente para actualizar los datos.");

            Transport.send(message);
        } catch (Exception e) {

        }
    }
    public void emailFacturacion(String receptor, String detalle, boolean fact, String archivo) {
        final String username = "facturacion@airfiber.ec";
        final String password = "pagofacil@2021@mail";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.airfiber.ec");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setSubject("Aviso de Facturacion");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            message.setText(detalle);
            if (fact) {
                BodyPart parteAdjunto = new MimeBodyPart();
                String nomArch = archivo;
                DataSource arch = new FileDataSource(archivo);
                parteAdjunto.setDataHandler(new DataHandler(arch));
                parteAdjunto.setFileName(nomArch);
                Multipart multipart = new MimeMultipart();
                message.setContent(multipart);
                multipart.addBodyPart(parteAdjunto);
            }
            Transport.send(message);
        } catch (Exception e) {

        }
    }

}
