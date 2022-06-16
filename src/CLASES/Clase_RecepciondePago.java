/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import javafxapplication4.VariablesDeUso;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
  * @author cl
 */
public class Clase_RecepciondePago {

    public static String cedulaCliente;
    public static String Nombreabonado;
    // public boolean facturaac;
    ObservableList<recepcionpago> clt = FXCollections.observableArrayList();
    ResultSet valores;
    bas q = new bas();
    private double t;
    private String direccion;
    private String tipoidenficacion;
    private String codicotipoidentificacion;
    private String nombrecliente;
    private String numerofact;
    private String cedulatext;
    private String total;
    private String Subtotal;
    private String Iva;
    private String fechagenera;
    private String claveacceso;
    private String SerieCaja;
    private String email;
    private boolean abono;
    private double valq;
    private String detalle;
    private String[] Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private Month mes = LocalDate.now().getMonth();
    private int año = LocalDate.now().getYear();
    ResultSet deuda;

    public static String getCedulaCliente() {
        return cedulaCliente;
    }

    public ObservableList<recepcionpago> getClt() {
        return clt;
    }

    public void setClt(recepcionpago clt) {
        this.clt.add(clt);
    }

    public static void setCedulaCliente(String cedulaCliente) {
        Clase_RecepciondePago.cedulaCliente = cedulaCliente;
    }

    public static String getNombreabonado() {
        return Nombreabonado;
    }

    public static void setNombreabonado(String Nombreabonado) {
        Clase_RecepciondePago.Nombreabonado = Nombreabonado;
    }

    public double getValq() {
        return valq;
    }

    public void setValq(double valq) {
        this.valq = valq;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isAbono() {
        return abono;
    }

    public void setAbono(boolean abono) {
        this.abono = abono;
    }
    String datosride = "", datosclienteride = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Clase_RecepciondePago(String nombrecliente, String numerofact, String cedulatext, String total, String Subtotal, String Iva, String fechagenera, String claveacceso, String SerieCaja, boolean abono) {
        this.nombrecliente = nombrecliente;
        this.numerofact = numerofact;
        this.cedulatext = cedulatext;
        this.total = total;
        this.Subtotal = Subtotal;
        this.Iva = Iva;
        this.fechagenera = fechagenera;
        this.claveacceso = claveacceso;
        this.SerieCaja = SerieCaja;
        this.abono = abono;
    }

    public void Generar() {
        try {

            List<XML> empList = new ArrayList<>();

            valores = q.tablas("SELECT  NOMBRES,APELLIDOS,DIRECCION,IDENTIFICACION,TIPOIDENTIFICACION,EMAIL FROM [BDAirnet].[dbo].[TBCLIENTE] where  IDENTIFICACION='" + cedulatext.replace(" ", "") + "'");
            while (valores.next()) {
                direccion = valores.getString("DIRECCION");
                tipoidenficacion = valores.getString("TIPOIDENTIFICACION");
                email = valores.getString("EMAIL");
            }
            valores = q.tablas("select * from  [BDAirnet].[dbo].[detallesfacxtura] where Estado='0' and numerofactura='" + numerofact.replace(" ", "") + "' and codigo='" + cedulatext.replace(" ", "") + "'");

            while (valores.next()) {
                empList.add(new XML(valores.getString("detalle").substring(0, 40), valores.getString("valorunitario").replace(" ", ""), valores.getString("valortotal").replace(" ", ""), valores.getString("cantidad").replace(" ", "")));
                t = t + Double.parseDouble(valores.getString("valortotal").substring(0, 7).replace(",", "."));
            }
            datosclienteride = fechagenera.replace("-", "/") + "-" + nombrecliente + "-" + claveacceso + "-" + direccion + "-" + cedulatext + "-" + numerofact.replace(" ", "") + "-";
            datosclienteride = datosclienteride + q.FormatoDecimal(t / 1.12) + "-" + q.FormatoDecimal((t / 1.12) * 0.12) + "-" + q.FormatoDecimal(t);
            if (tipoidenficacion.contains("RUC")) {
                codicotipoidentificacion = "04";
            } else if (tipoidenficacion.contains("Cédula")) {
                codicotipoidentificacion = "05";
            } else if (tipoidenficacion.contains("Pasaporte")) {
                codicotipoidentificacion = "06";
            } else {
                codicotipoidentificacion = "07";
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.newDocument();
            doc.setXmlStandalone(true);
            org.w3c.dom.Element rootElement = doc.createElement("factura");
            rootElement.setAttribute("id", "comprobante");
            rootElement.setAttribute("version", "1.0.0");
            doc.appendChild(rootElement);
            org.w3c.dom.Element supercar = doc.createElement("infoTributaria");
            rootElement.appendChild(supercar);
            org.w3c.dom.Element ambiente = doc.createElement("ambiente");
            ambiente.appendChild(doc.createTextNode("2"));
            supercar.appendChild(ambiente);
            org.w3c.dom.Element tipoEmision = doc.createElement("tipoEmision");
            tipoEmision.appendChild(doc.createTextNode("1"));
            supercar.appendChild(tipoEmision);
            org.w3c.dom.Element razonSocial = doc.createElement("razonSocial");
            razonSocial.appendChild(doc.createTextNode("GALO ALFREDO ALAVA MACAS"));
            supercar.appendChild(razonSocial);
            org.w3c.dom.Element nombreComercial = doc.createElement("nombreComercial");
            nombreComercial.appendChild(doc.createTextNode("Airnet Agente de Retencion: Nro. NAC-DNCRASC20-00000001"));
            supercar.appendChild(nombreComercial);
            org.w3c.dom.Element ruc = doc.createElement("ruc");
            ruc.appendChild(doc.createTextNode("1204112724001"));
            supercar.appendChild(ruc);
            org.w3c.dom.Element claveAcceso = doc.createElement("claveAcceso");
            claveAcceso.appendChild(doc.createTextNode(claveacceso));
            supercar.appendChild(claveAcceso);
            org.w3c.dom.Element codDoc = doc.createElement("codDoc");
            codDoc.appendChild(doc.createTextNode("01"));
            supercar.appendChild(codDoc);
            org.w3c.dom.Element estab = doc.createElement("estab");
            estab.appendChild(doc.createTextNode("001"));
            supercar.appendChild(estab);
            org.w3c.dom.Element ptoEmi = doc.createElement("ptoEmi");
            ptoEmi.appendChild(doc.createTextNode(SerieCaja));
            supercar.appendChild(ptoEmi);
            org.w3c.dom.Element secuencial = doc.createElement("secuencial");
            secuencial.appendChild(doc.createTextNode(numerofact));
            supercar.appendChild(secuencial);
            org.w3c.dom.Element dirMatriz = doc.createElement("dirMatriz");
            dirMatriz.appendChild(doc.createTextNode("Av. José Joaquín de Olmedo #220 y Colombia San Camilo - Quevedo - Ecuador"));
            supercar.appendChild(dirMatriz);
            //****************** fin membrete principal general

            //******************** inicio de datos clientes
            org.w3c.dom.Element cuerpo2 = doc.createElement("infoFactura");
            rootElement.appendChild(cuerpo2);

            org.w3c.dom.Element fechaEmision = doc.createElement("fechaEmision");
            fechaEmision.appendChild(doc.createTextNode(fechagenera.replace(" ", "")));
            cuerpo2.appendChild(fechaEmision);
            org.w3c.dom.Element dirEstablecimiento = doc.createElement("dirEstablecimiento");
            dirEstablecimiento.appendChild(doc.createTextNode("Av. José Joaquín de Olmedo #220 y Colombia San Camilo - Quevedo - Ecuador"));
            cuerpo2.appendChild(dirEstablecimiento);

            org.w3c.dom.Element obligadoContabilidad = doc.createElement("obligadoContabilidad");
            obligadoContabilidad.appendChild(doc.createTextNode("SI"));
            cuerpo2.appendChild(obligadoContabilidad);
            org.w3c.dom.Element tipoIdentificacionProveedor = doc.createElement("tipoIdentificacionComprador");
            tipoIdentificacionProveedor.appendChild(doc.createTextNode(codicotipoidentificacion));
            cuerpo2.appendChild(tipoIdentificacionProveedor);
            org.w3c.dom.Element razonSocialProveedor = doc.createElement("razonSocialComprador");
            razonSocialProveedor.appendChild(doc.createTextNode(nombrecliente));
            cuerpo2.appendChild(razonSocialProveedor);
            org.w3c.dom.Element identificacionProveedor = doc.createElement("identificacionComprador");
            identificacionProveedor.appendChild(doc.createTextNode(cedulatext));
            cuerpo2.appendChild(identificacionProveedor);
            org.w3c.dom.Element direccionProveedor = doc.createElement("direccionComprador");
            direccionProveedor.appendChild(doc.createTextNode(direccion));
            cuerpo2.appendChild(direccionProveedor);
            org.w3c.dom.Element totalSinImpuestos = doc.createElement("totalSinImpuestos");
            totalSinImpuestos.appendChild(doc.createTextNode(q.FormatoDecimal(t / 1.12)));
            cuerpo2.appendChild(totalSinImpuestos);
            org.w3c.dom.Element totalDescuento = doc.createElement("totalDescuento");
            totalDescuento.appendChild(doc.createTextNode("0"));
            cuerpo2.appendChild(totalDescuento);
            org.w3c.dom.Element totalConImpuestos = doc.createElement("totalConImpuestos");
            cuerpo2.appendChild(totalConImpuestos);
            //totalImpuesto
            org.w3c.dom.Element totalImpuesto = doc.createElement("totalImpuesto");
            totalConImpuestos.appendChild(totalImpuesto);
            //codigo
            org.w3c.dom.Element codigo = doc.createElement("codigo");
            codigo.appendChild(doc.createTextNode("2"));
            totalImpuesto.appendChild(codigo);
            //codigoPorcentaje
            org.w3c.dom.Element codigoPorcentaje = doc.createElement("codigoPorcentaje");
            codigoPorcentaje.appendChild(doc.createTextNode("2"));
            totalImpuesto.appendChild(codigoPorcentaje);

            // baseImponible
            org.w3c.dom.Element baseImponible = doc.createElement("baseImponible");
            baseImponible.appendChild(doc.createTextNode(q.FormatoDecimal(t / 1.12)));
            totalImpuesto.appendChild(baseImponible);
            //tarifa
            org.w3c.dom.Element tarifa = doc.createElement("tarifa");
            tarifa.appendChild(doc.createTextNode("12"));
            totalImpuesto.appendChild(tarifa);
            //valor
            org.w3c.dom.Element valor = doc.createElement("valor");
            valor.appendChild(doc.createTextNode(q.FormatoDecimal((t / 1.12) * 0.12)));
            totalImpuesto.appendChild(valor);

            //importeTotal
            org.w3c.dom.Element importeTotal = doc.createElement("importeTotal");
            importeTotal.appendChild(doc.createTextNode(q.FormatoDecimal(t)));
            cuerpo2.appendChild(importeTotal);
            //moneda
            org.w3c.dom.Element moneda = doc.createElement("moneda");
            moneda.appendChild(doc.createTextNode("DOLAR"));
            cuerpo2.appendChild(moneda);

            org.w3c.dom.Element pagos = doc.createElement("pagos");
            cuerpo2.appendChild(pagos);
            //moneda
            org.w3c.dom.Element pago = doc.createElement("pago");
            pagos.appendChild(pago);
            org.w3c.dom.Element formaPago = doc.createElement("formaPago");
            formaPago.appendChild(doc.createTextNode("01"));
            pago.appendChild(formaPago);
            org.w3c.dom.Element total = doc.createElement("total");
            total.appendChild(doc.createTextNode(q.FormatoDecimal(t)));
            pago.appendChild(total);

            org.w3c.dom.Element detalles = doc.createElement("detalles");
            rootElement.appendChild(detalles);
            for (XML xml : empList) {
                DecimalFormat formato1 = new DecimalFormat("#.00");
                String valorni = String.valueOf(formato1.format(Double.parseDouble(xml.getValorunitario().replace(",", ".")) / 1.12));
                String base = String.valueOf(formato1.format((Double.parseDouble(xml.getValorunitario().replace(",", ".")) / 1.12) * Double.parseDouble(xml.getCantidad())));
                String ivabaseimponible = String.valueOf(formato1.format(Double.parseDouble(base.replace(",", ".")) * 0.12));
                org.w3c.dom.Element detallexml = doc.createElement("detalle");
                detalles.appendChild(detallexml);
                org.w3c.dom.Element codigoPrincipal = doc.createElement("codigoPrincipal");
                codigoPrincipal.appendChild(doc.createTextNode(xml.getDetalle().substring(0, 24)));
                detallexml.appendChild(codigoPrincipal);
                org.w3c.dom.Element codigoAuxiliar = doc.createElement("codigoAuxiliar");
                codigoAuxiliar.appendChild(doc.createTextNode(xml.getDetalle().substring(0, 24)));
                detallexml.appendChild(codigoAuxiliar);
                org.w3c.dom.Element descripcion = doc.createElement("descripcion");
                descripcion.appendChild(doc.createTextNode(xml.getDetalle().replace(" ", "")));
                detallexml.appendChild(descripcion);
                org.w3c.dom.Element cantidadxml = doc.createElement("cantidad");
                cantidadxml.appendChild(doc.createTextNode(xml.getCantidad()));
                detallexml.appendChild(cantidadxml);
                org.w3c.dom.Element precioUnitario = doc.createElement("precioUnitario");
                precioUnitario.appendChild(doc.createTextNode(valorni.replace(",", ".")));
                detallexml.appendChild(precioUnitario);
                org.w3c.dom.Element descuento = doc.createElement("descuento");
                descuento.appendChild(doc.createTextNode("0"));
                detallexml.appendChild(descuento);
                org.w3c.dom.Element precioTotalSinImpuesto = doc.createElement("precioTotalSinImpuesto");
                precioTotalSinImpuesto.appendChild(doc.createTextNode(base.replace(",", ".")));
                detallexml.appendChild(precioTotalSinImpuesto);

                org.w3c.dom.Element impuestos = doc.createElement("impuestos");
                detallexml.appendChild(impuestos);
                org.w3c.dom.Element impuesto = doc.createElement("impuesto");
                impuestos.appendChild(impuesto);
                org.w3c.dom.Element codigoim = doc.createElement("codigo");
                codigoim.appendChild(doc.createTextNode("2"));
                impuesto.appendChild(codigoim);
                org.w3c.dom.Element codigoPorcentaje1 = doc.createElement("codigoPorcentaje");
                codigoPorcentaje1.appendChild(doc.createTextNode("2"));
                impuesto.appendChild(codigoPorcentaje1);
                org.w3c.dom.Element tarifa1 = doc.createElement("tarifa");
                tarifa1.appendChild(doc.createTextNode("12"));
                impuesto.appendChild(tarifa1);
                org.w3c.dom.Element baseImponible1 = doc.createElement("baseImponible");
                baseImponible1.appendChild(doc.createTextNode(base.replace(",", ".")));
                impuesto.appendChild(baseImponible1);
                org.w3c.dom.Element valor1 = doc.createElement("valor");
                valor1.appendChild(doc.createTextNode(ivabaseimponible.replace(",", ".")));
                impuesto.appendChild(valor1);

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\GENERADOS\\" + claveacceso + ".xml"));
            transformer.transform(source, result);
            if (abono) {
                q.UpdateModificar("update [BDAirnet].[dbo].[tvdjFacurascliente] set Facturadopor='" + VariablesDeUso.nombreusuario + "',Pagado= getDate() ,SRI='NO',direccion='" + direccion + "',total='" + t + "',iva='" + q.FormatoDecimal((t / 1.12) * 0.12) + "',subtotal='" + q.FormatoDecimal(t / 1.12) + "', Datoscliente='" + datosclienteride + "', correo='" + email + "', seriem ='001',serieca='" + SerieCaja + "' where nuemrofactura='" + numerofact + "' and codigocontrato='" + cedulaCliente + "'");
            } else {
                q.UpdateModificar("update [BDAirnet].[dbo].[tvdjFacurascliente] set Facturadopor='" + VariablesDeUso.nombreusuario + "',Pagado= getDate() ,SRI='NO',direccion='" + direccion + "',total='" + t + "',iva='" + q.FormatoDecimal((t / 1.12) * 0.12) + "',subtotal='" + q.FormatoDecimal(t / 1.12) + "', Datoscliente='" + datosclienteride + "', correo='" + email + "', seriem ='001',serieca='" + SerieCaja + "' where nuemrofactura='" + numerofact + "' and codigocontrato='" + cedulaCliente + "'");
            }

        } catch (SQLException | ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoidenficacion() {
        return tipoidenficacion;
    }

    public void setTipoidenficacion(String tipoidenficacion) {
        this.tipoidenficacion = tipoidenficacion;
    }

    public String getCodicotipoidentificacion() {
        return codicotipoidentificacion;
    }

    public void setCodicotipoidentificacion(String codicotipoidentificacion) {
        this.codicotipoidentificacion = codicotipoidentificacion;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getNumerofact() {
        return numerofact;
    }

    public void setNumerofact(String numerofact) {
        this.numerofact = numerofact;
    }

    public String getCedulatext() {
        return cedulatext;
    }

    public void setCedulatext(String cedulatext) {
        this.cedulatext = cedulatext;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(String Subtotal) {
        this.Subtotal = Subtotal;
    }

    public String getIva() {
        return Iva;
    }

    public void setIva(String Iva) {
        this.Iva = Iva;
    }

    public String getFechagenera() {
        return fechagenera;
    }

    public void setFechagenera(String fechagenera) {
        this.fechagenera = fechagenera;
    }

    public String getClaveacceso() {
        return claveacceso;
    }

    public void setClaveacceso(String claveacceso) {
        this.claveacceso = claveacceso;
    }

    public String getSerieCaja() {
        return SerieCaja;
    }

    public void setSerieCaja(String SerieCaja) {
        this.SerieCaja = SerieCaja;
    }

    public Clase_RecepciondePago(String cedulatext, String numerofact) {
        this.cedulatext = cedulatext;
        this.numerofact = numerofact;
    }
    VariablesDeUso ob = new VariablesDeUso();

    public float Valordeudante() {
        ob.Encerar();
        switch (q.MesActualEspañol()) {
            case "ENERO":
                ob.setComent("detalle not like '%Mes " + Meses[1] + " " + año + "%' and  detalle not like '%Mes " + Meses[2] + " " + año + "%' and  detalle not like '%Mes " + Meses[3] + " " + año + "%'and  detalle not like '%Mes " + Meses[4] + " " + año + "%'and  detalle not like '%Mes " + Meses[5] + " " + año + "%'and  detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'");
                // deuda = q.Dedudas(1, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "FEBRERO":
                ob.setComent(" detalle not like '%Mes " + Meses[2] + " " + año + "%' and  detalle not like '%Mes " + Meses[3] + " " + año + "%'and  detalle not like '%Mes " + Meses[4] + " " + año + "%'and  detalle not like '%Mes " + Meses[5] + " " + año + "%'and  detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'");

                // deuda = q.Dedudas(2, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "MARZO":
                ob.setComent("detalle not like '%Mes " + Meses[3] + " " + año + "%'and  detalle not like '%Mes " + Meses[4] + " " + año + "%'and  detalle not like '%Mes " + Meses[5] + " " + año + "%'and  detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'");

                //deuda = q.Dedudas(3, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "ABRIL":
                ob.setComent(" detalle not like '%Mes " + Meses[4] + " " + año + "%'and  detalle not like '%Mes " + Meses[5] + " " + año + "%'and  detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'");

                //deuda = q.Dedudas(4, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "MAYO":
                ob.setComent(" detalle not like '%Mes " + Meses[5] + " " + año + "%'and  detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'");

                // deuda = q.Dedudas(5, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "JUNIO":
                ob.setComent(" detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'");

                // deuda = q.Dedudas(6, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "JULIO":
                ob.setComent(" detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'");

                //deuda = q.Dedudas(7, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "AGOSTO":
                ob.setComent(" detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'");

                // deuda = q.Dedudas(8, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "SEPTIEMBRE":
                ob.setComent("detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'");

                // deuda = q.Dedudas(9, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "OCTUBRE":
                ob.setComent("detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'");

                //deuda = q.Dedudas(10, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "NOVIEMBRE":
                ob.setComent(" detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                //deuda = q.Dedudas(11, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }

                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {

                }

            case "DICIEMBRE":

                // deuda = q.Dedudas(12, cedulatext);
                deuda = q.tablas(" SELECT  valortotal FROM [BDAirnet].[dbo].[detallesfacxtura] where  Estado='1' and codigo='" + cedulatext + "'");
                try {
                    
                    while (deuda.next()) {
                        if (!deuda.getString("valortotal").replace(" ", "").isEmpty()) {
                            ob.setValorTotal(q.ConvertidorStringaDouble(deuda.getString("valortotal")));

                        }
                    }
                    return (float) ob.getValorTotal();
                } catch (SQLException ex) {
                        return 0;
                }

        }
        return 0;

    }

    public String DetallePagado() {
        t = 0;
        detalle = "";
        valores = q.tablas("select * from  [BDAirnet].[dbo].[detallesfacxtura] where Estado='0' and numerofactura='" + numerofact + "' and codigo ='" + cedulatext + "'");

        try {
            while (valores.next()) {
                t = t + Double.parseDouble(valores.getString("valortotal").substring(0, 7).replace(",", "."));
                detalle = detalle + valores.getString("detalle").substring(0, 20) + "\t |" + valores.getString("cantidad").substring(0, 6).replace(" ", " ") + "|" + q.FormatoDecimal(Double.parseDouble(valores.getString("valorunitario").substring(0, 6).replace(",", ".")) / 1.12) + "|" + q.FormatoDecimal(Double.parseDouble(valores.getString("valortotal").substring(0, 6).replace(",", ".")) / 1.12) + "|\n";
            }
            return detalle;
        } catch (SQLException ex) {
            return "Exepcion no prevista";
        }
    }

    public void ValoresIniciales(String cedulaCliente, String Nombreabonado) {
        Clase_RecepciondePago.Nombreabonado = Nombreabonado;
        Clase_RecepciondePago.cedulaCliente = cedulaCliente;
        // q.ventanas("recepciondepago.fxml");

    }

    public Clase_RecepciondePago() {
    }

    public ResultSet detalle() {

        /* return  q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura]"
               + " where  Estado='1' and codigo='"+cedulatext+"' order by iddetalle");
         */
        valq = 0;
        String aux;
        String valor = (this.numerofact.contains("T")) ? "TODO" : mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
        switch (valor) {
            case "ENERO":
                aux = "detalle not like '%Mes " + Meses[1] + " " + año + "%' and  detalle not like '%Mes " + Meses[2] + " " + año + "%' and  detalle not like '%Mes " + Meses[3] + " " + año + "%'and  detalle not like '%Mes " + Meses[4] + " " + año + "%'and  detalle not like '%Mes " + Meses[5] + " " + año + "%'and  detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'" +" ORDER BY iddetalle ASC ");

            case "FEBRERO":
                aux = " detalle not like '%Mes " + Meses[2] + " " + año + "%' and  detalle not like '%Mes " + Meses[3] + " " + año + "%'and  detalle not like '%Mes " + Meses[4] + " " + año + "%'and  detalle not like '%Mes " + Meses[5] + " " + año + "%'and  detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

            case "MARZO":
                aux = "detalle not like '%Mes " + Meses[3] + " " + año + "%'and  detalle not like '%Mes " + Meses[4] + " " + año + "%'and  detalle not like '%Mes " + Meses[5] + " " + año + "%'and  detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

            case "ABRIL":
                aux = " detalle not like '%Mes " + Meses[4] + " " + año + "%'and  detalle not like '%Mes " + Meses[5] + " " + año + "%'and  detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");
            case "MAYO":
                aux = " detalle not like '%Mes " + Meses[5] + " " + año + "%'and  detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

            case "JUNIO":
                aux = " detalle not like '%Mes " + Meses[6] + " " + año + "%'and  detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

            case "JULIO":
                aux = " detalle not like '%Mes " + Meses[7] + " " + año + "%'and  detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

            case "AGOSTO":
                aux = " detalle not like '%Mes " + Meses[8] + " " + año + "%' and  detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

            case "SEPTIEMBRE":
                aux = "detalle not like '%Mes " + Meses[9] + " " + año + "%' and  detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

            case "OCTUBRE":
                aux = "detalle not like '%Mes " + Meses[10] + " " + año + "%' and  detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

            case "NOVIEMBRE":
                aux = " detalle not like '%Mes " + Meses[11] + " " + año + "%'";

                return q.tablas("SELECT iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura] where " + aux + " and  Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

            case "DICIEMBRE":

                return q.tablas("SELECT  iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura]  where    Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

            default:
                return q.tablas("SELECT  iddetalle, Estado,numerofactura,detalle,valorunitario,valortotal,cantidad FROM [BDAirnet].[dbo].[detallesfacxtura]  where    Estado='1' and codigo='" + cedulatext + "'"+" ORDER BY iddetalle ASC ");

        }

    }

    public ResultSet Valordeudante2() {

        return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                + "      ,[IDENTIFICACION]\n"
              //  + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where  Estado='1' "
                + "and plancontratado='" + cedulatext + "' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");
        /*
        switch (q.MesActualEspañol()) {
            
            
            
            case "ENERO":
                ob.setComent("detalle not like '%Mes " + Meses[1] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[2] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[3] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[4] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[5] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");
                // deuda = q.Dedudas(1, cedulatext);
               return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");
               

            case "FEBRERO":
                ob.setComent(" detalle not like '%Mes " + Meses[2] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[3] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[4] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[5] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                // deuda = q.Dedudas(2, cedulatext);
                 return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            case "MARZO":
                ob.setComent("detalle not like '%Mes " + Meses[3] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[4] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[5] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                //deuda = q.Dedudas(3, cedulatext);
                 return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            case "ABRIL":
                ob.setComent(" detalle not like '%Mes " + Meses[4] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[5] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                //deuda = q.Dedudas(4, cedulatext);
                  return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            case "MAYO":
                ob.setComent(" detalle not like '%Mes " + Meses[5] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                // deuda = q.Dedudas(5, cedulatext);
                 return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            case "JUNIO":
                ob.setComent(" detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                // deuda = q.Dedudas(6, cedulatext);
                 return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            case "JULIO":
                ob.setComent(" detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");
                System.out.println(" SELECT  sum(valortotal) as VALORTAL FROM [BDAirnet].[dbo].[TablaDetalleAuxiliar] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                //deuda = q.Dedudas(7, cedulatext);
               return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            case "AGOSTO":
                ob.setComent(" detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                // deuda = q.Dedudas(8, cedulatext);
               return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            case "SEPTIEMBRE":
                ob.setComent("detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                // deuda = q.Dedudas(9, cedulatext);
                return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            case "OCTUBRE":
                ob.setComent("detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                //deuda = q.Dedudas(10, cedulatext);
                return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            case "NOVIEMBRE":
                ob.setComent(" detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                //deuda = q.Dedudas(11, cedulatext);
               return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            case "DICIEMBRE":

                // deuda = q.Dedudas(12, cedulatext);
                 return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

        }

        return q.tablas("SELECT  (NOMBRES+' '+APELLIDOS) as clientes\n"
                        + "      ,[IDENTIFICACION]\n"
                        + "	  , sum (TRY_CONVERT (float, valortotal)) as VALORTAL\n"
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"' group by (NOMBRES+' '+APELLIDOS) ,IDENTIFICACION");

            //return null;*/

    }

    public ResultSet TotalPoPlan() {

        return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where  Estado='1' and plancontratado='" + cedulatext + "'");
        /*
        ob.Encerar();
        ob.setComent(total);
        valq = 0;
        switch (q.MesActualEspañol()) {
            case "ENERO":
                ob.setComent("detalle not like '%Mes " + Meses[1] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[2] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[3] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[4] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[5] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");
                // deuda = q.Dedudas(1, cedulatext);
               return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
               

            case "FEBRERO":
                ob.setComent(" detalle not like '%Mes " + Meses[2] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[3] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[4] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[5] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                // deuda = q.Dedudas(2, cedulatext);
                    return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
            case "MARZO":
                ob.setComent("detalle not like '%Mes " + Meses[3] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[4] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[5] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                //deuda = q.Dedudas(3, cedulatext);
                    return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
            case "ABRIL":
                ob.setComent(" detalle not like '%Mes " + Meses[4] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[5] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                //deuda = q.Dedudas(4, cedulatext);
                    return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
            case "MAYO":
                ob.setComent(" detalle not like '%Mes " + Meses[5] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                // deuda = q.Dedudas(5, cedulatext);
                    return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
            case "JUNIO":
                ob.setComent(" detalle not like '%Mes " + Meses[6] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                // deuda = q.Dedudas(6, cedulatext);
                    return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
            case "JULIO":
                ob.setComent(" detalle not like '%Mes " + Meses[7] + "\t" + año + "%'and  detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");
                System.out.println(" SELECT  sum(valortotal) as VALORTAL FROM [BDAirnet].[dbo].[TablaDetalleAuxiliar] where " + ob.getComent() + " and  Estado='1' and codigo='" + cedulatext + "'");
                //deuda = q.Dedudas(7, cedulatext);
                 return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
            case "AGOSTO":
                ob.setComent(" detalle not like '%Mes " + Meses[8] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                // deuda = q.Dedudas(8, cedulatext);
                 return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
            case "SEPTIEMBRE":
                ob.setComent("detalle not like '%Mes " + Meses[9] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                // deuda = q.Dedudas(9, cedulatext);
                   return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
            case "OCTUBRE":
                ob.setComent("detalle not like '%Mes " + Meses[10] + "\t" + año + "%' and  detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                //deuda = q.Dedudas(10, cedulatext);
                   return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
            case "NOVIEMBRE":
                ob.setComent(" detalle not like '%Mes " + Meses[11] + "\t" + año + "%'");

                //deuda = q.Dedudas(11, cedulatext);
                 return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
            case "DICIEMBRE":

                // deuda = q.Dedudas(12, cedulatext);
                   return q.tablas("SELECT  sum (TRY_CONVERT (float, valortotal)) as VALORTAL "
                        + "  FROM [BDAirnet].[dbo].[VistaDeudasVersion3] where " + ob.getComent() + " and  Estado='1' and plancontratado='"+cedulatext+"'");
        }

            return null;
         */
    }

    public Map DEUDASGENERALES() {
      
        Map<String, Float> FORMATODEUDAS = new HashMap<>();
        ResultSet IDENTIFIC = q.tablas("SELECT IDENTIFICACION ,(SELECT sum (TRY_CONVERT (float, ITEM.valortotal)) as VALORTAL \n"
                + "FROM [BDAirnet].[dbo].[detallesfacxtura] AS ITEM where ITEM.Estado =1 and"
                + " ITEM.codigo=CEDULA.IDENTIFICACION and ITEM.numerofactura is null ) as VALOR FROM [BDAirnet].[dbo].[TBCLIENTE] as CEDULA group by IDENTIFICACION");
        String CEDULA;
        try {
            while (IDENTIFIC.next()) {
                    float VAlorFinal=(IDENTIFIC.getString("VALOR") == null) ? 0:IDENTIFIC.getFloat("VALOR");
                CEDULA = IDENTIFIC.getString("IDENTIFICACION");
                FORMATODEUDAS.put(CEDULA, VAlorFinal);       
            }

        } catch (SQLException ex) {
            Logger.getLogger(Clase_RecepciondePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FORMATODEUDAS;
    }

    public int CONTEO() {
        ResultSet IDENTIFIC = q.tablas("SELECT count(IDENTIFICACION) as valor FROM [dbo].[TBCLIENTE] group by IDENTIFICACION");
        try {
            while (IDENTIFIC.next()) {
                return IDENTIFIC.getInt("valor");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clase_RecepciondePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
 /*   Task task;

    public void HilodeCorte() {

        task = new Task() {
            @Override
            protected Object call() throws Exception {

                int i = CONTEO();
                int c = 1;
                while (c <= i) {

                    updateProgress(i, map.size() - 1);
                }

                return null;
            }
        };

        task.setOnRunning((event) -> {
            imagencarga.setVisible(true);
            //  clientesdecorte.setText(task.getMessage());
        });

        task.setOnSucceeded(event -> {
            imagencarga.setVisible(false);
            // aqui va que hacer cuando ya acabe de ejecutarse el hilo
        });

        new Thread(task).start();

        bar.progressProperty().bind(task.progressProperty());

    }*/
}
