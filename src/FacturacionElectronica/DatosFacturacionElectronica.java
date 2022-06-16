/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacturacionElectronica;

import CLASES.bas;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.CertificateException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
  * @author cl
 */
public class DatosFacturacionElectronica {
    private String correofinal;
    private String valores;
    private String total;
    private String iva;
    private String subtotal;
    private String numerofactura;
    private String fechagenerada;
    private String claveacceso;
    private String cedula;
    private String Direccion;
    private String Cliente;
    private String noautorizadosjtext1;
    private String serieem;
    private String serieca;
    private String formapago;
    
    private int IDFACTURA;

    public DatosFacturacionElectronica(int IDFACTURA) {
        this.IDFACTURA = IDFACTURA;
    }

    public int getIDFACTURA() {
        return IDFACTURA;
    }

    public void setIDFACTURA(int IDFACTURA) {
        this.IDFACTURA = IDFACTURA;
    }
    
    bas q = new bas ();

    public String getCorreofinal() {
        return correofinal;
    }

    public void setCorreofinal(String correofinal) {
        this.correofinal = correofinal;
    }

    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public String getFechagenerada() {
        return fechagenerada;
    }

    public void setFechagenerada(String fechagenerada) {
        this.fechagenerada = fechagenerada;
    }

    public String getClaveacceso() {
        return claveacceso;
    }

    public void setClaveacceso(String claveacceso) {
        this.claveacceso = claveacceso;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getNoautorizadosjtext1() {
        return noautorizadosjtext1;
    }

    public void setNoautorizadosjtext1(String noautorizadosjtext1) {
        this.noautorizadosjtext1 = noautorizadosjtext1;
    }

    public String getSerieem() {
        return serieem;
    }

    public void setSerieem(String serieem) {
        this.serieem = serieem;
    }

    public String getSerieca() {
        return serieca;
    }

    public void setSerieca(String serieca) {
        this.serieca = serieca;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }
    
     public boolean principal() throws IOException, JRException {

      
        String valoraenviar = "";
        valoraenviar = "SELECT [serieca],[seriem],[nuemrofactura] ,[cliente],[clave],[fechagenerada],[codigocontrato],[correo] ,[total],[iva] ,[subtotal],[direccion] FROM [BDAirnet].[dbo].[tvdjFacurascliente]  where   iva>0 and SRI='NO' and idfact=" + this.IDFACTURA;
        q.bas1();
        ResultSet facturas = q.tablas(valoraenviar);
        try {
            while (facturas.next()) {
                correofinal = facturas.getString("correo").replace(" ", "");
                total = facturas.getString("total").replace(" ", "");
                iva = facturas.getString("iva").replace(" ", "");
                subtotal = facturas.getString("subtotal").replace(" ", "");
                numerofactura = facturas.getString("nuemrofactura").replace(" ", "");
                fechagenerada = facturas.getString("fechagenerada").replace(" ", "");
                claveacceso = facturas.getString("clave").replace(" ", "");
                cedula = facturas.getString("codigocontrato").replace(" ", "");
                Direccion = facturas.getString("direccion");
                Cliente = facturas.getString("cliente");
                serieca = facturas.getString("serieca");
                serieem = facturas.getString("seriem");
            }
             firmaryenvio("\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\GENERADOS\\" + claveacceso + ".xml",
                             "\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\galo_alfredo_alava_macas.p12",
                             "Staticpony39", "\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\FIRMADOS", claveacceso + ".xml");
                    valores = "";
                    if (leerxml(claveacceso).contains("Error")) {
       
                        q.UpdateModificar("update [BDAirnet].[dbo].[tvdjFacurascliente] set SRI='NO' where clave='" + claveacceso + "'");
                        return false ;
                    } else {
                        valores = valores + leerxml(claveacceso);
                        ridefacturav2(valores, claveacceso);
                        q.UpdateModificar("update [BDAirnet].[dbo].[tvdjFacurascliente] set SRI='SI' where clave='" + claveacceso + "'");
                        Thread.sleep(200);
                        return true;
                    }
               

            //  System.exit(0);
        } catch (SQLException | InterruptedException  ex) {
           return false;
        }
    }
     public void firmaryenvio(String xmlPath, String pathSignature, String passSignature, String pathOut, String nameFileOut)  {
        try {
            Autorizacion autorizado = new Autorizacion();
            // System.out.println("Ruta del XML de entrada: " + xmlPath);
            // System.out.println("Ruta Certificado: " + pathSignature);
            // System.out.println("Clave del Certificado: " + passSignature);
            // System.out.println("Ruta de salida del XML: " + pathOut);
            // System.out.println("Nombre del archivo salido: " + nameFileOut);
            // String claveac[]=nameFileOut.split(".");
            
            // proceso de firmaelectronica//
            XAdESBESSignature.firmar(xmlPath, pathSignature, passSignature, pathOut, nameFileOut);
            String filePath = pathOut + "\\" + nameFileOut;
            
            String[] claveacceso = nameFileOut.split(".xml");
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            System.setProperty("javax.net.ssl.keyStore", "\\\\s-aircontrol\\security\\cacerts");
            System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
            RespuestaSolicitud respuesta = validarComprobante(bytes);
            String estado = respuesta.getEstado();
            Thread.sleep(2500);
            if (estado.equals("DEVUELTA")) {
                // System.out.println(respuesta.getEstado());
            } else {
                if (estado.equals("RECIBIDA")) {
                    autorizado.main(claveacceso[0]);
                }
                
            }
        } catch (CertificateException | IOException | InterruptedException ex) {
            
        }

    }
     private  RespuestaSolicitud validarComprobante(byte[] xml) {
        RecepcionComprobantesOfflineService service = new RecepcionComprobantesOfflineService();
        RecepcionComprobantesOffline port = service.getRecepcionComprobantesOfflinePort();
        return port.validarComprobante(xml);

    }
      public  String leerxml(String clave)  {
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
            if (estado.getValue().contains("NO AUTORIZADO")) {

                return "Error";

            } else {
                Element fecha = art.getChild("fechaAutorizacion");
                return numero.getValue() + "-" + estado.getValue() + "-" + fecha.getValue().replace("-", "/") + "-";
            }
             } catch (IOException | JDOMException ex) {
           
        }
        return "Error";

       
    }
      public void ridefacturav2(String datos, String claveacceso) throws IOException, JRException, SQLServerException {
        String jr = "";
        String[] param = null;
        if (datos.contains("-")) {
            param = datos.split("-");
        }
        String numeroau = param[0].replace("-", "").replace(" ", "");
        String fechaautorizacion =  param[2].replace("-", "").replace(" ", "");
        String estado = param[1].replace("-", "");
        Map parametro = new HashMap();
        parametro.put("cliente", Cliente);
        parametro.put("direccion", Direccion);
        parametro.put("fechaemision", fechagenerada);
        parametro.put("cedula", cedula);
        parametro.put("fechaautorizacion", fechaautorizacion);
        parametro.put("numerofactura", numerofactura);
        parametro.put("subtotal", subtotal);
        parametro.put("iva", iva);
        parametro.put("total", total);
        parametro.put("numeroauto", numeroau);
        parametro.put("estado", estado);
        parametro.put("SERIEEM", serieem);
        parametro.put("SERIECA", serieca);
        parametro.put("correo", correofinal);
        jr = "\\\\S-AIRCONTROL\\Nuevo Systema\\ArchivosdeImpresion\\RIDE.jasper";
        String printFileName;
        printFileName = JasperFillManager.fillReportToFile(jr, parametro, q.cone());
        pdfgV2(printFileName, Cliente, numerofactura, correofinal);

    }
      public void pdfgV2(String printFileName, String cliente, String numero, String email) throws IOException, JRException {
 
        File diretorio = new File("\\\\S-AIRCONTROL\\Nuevo Systema\\Clientes\\" + cliente.replace(" ", "") + "\\Facturas");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        if (printFileName != null) {
            JasperExportManager.exportReportToPdfFile(printFileName,
                    "\\\\S-AIRCONTROL\\Nuevo Systema\\Clientes\\" + cliente.replace(" ", "") + "\\Facturas\\" + numero + ".pdf");
            File objetofile = new File("\\\\S-AIRCONTROL\\Nuevo Systema\\Clientes\\" + cliente.replace(" ", "") + "\\Facturas\\" + numero + ".pdf");

         
                if (email.contains("@")) {
                    q.emailFacturacion(email, "Factura generada por AIRNET ISP", true, objetofile.toString());
                } else {
                    q.emailVerficaciondeDatos(cliente);
                    //  q.email("","El cliente "+cliente+" no posee un email valido actualizar datos \n ", true, numero);
                }
                
                // Desktop.getDesktop().open(objetofile);
            

        }

    }
}
