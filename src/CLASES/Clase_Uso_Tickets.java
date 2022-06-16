/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 *
  * @author cl
 */
public class Clase_Uso_Tickets {

    bas objeto_bas = new bas();
    private List Error = new ArrayList();
    private List ValoresError = new ArrayList();
    public ObservableList<tikes> Observable_Contenedor = FXCollections.observableArrayList();
    public ObservableList<tikes> Observable_Contenedor_Constestado = FXCollections.observableArrayList();
    public ObservableList<tikes> Observable_Contenedor_Cerrados = FXCollections.observableArrayList();
    public ObservableList<tikes> Observable_Contenedor_CerradosReporte = FXCollections.observableArrayList();
    private final List Lista_clientes_Tickets = new ArrayList();
    private final List Lista_clientes_TicketsC = new ArrayList();
    private final List Lista_clientes_TicketsCE = new ArrayList();
  public  String fecha24 = String.valueOf(objeto_bas.FechaActualCalendario().getTime().getYear() + 1900) + "/" + String.valueOf(objeto_bas.FechaActualCalendario().getTime().getMonth() + 1) + "/" + String.valueOf(objeto_bas.FechaActualCalendario().getTime().getDate() - 1);
  public  String fecha48 = String.valueOf(objeto_bas.FechaActualCalendario().getTime().getYear() + 1900) + "/" + String.valueOf(objeto_bas.FechaActualCalendario().getTime().getMonth() + 1) + "/" + String.valueOf(objeto_bas.FechaActualCalendario().getTime().getDate() - 1);
  public  PseudoClass higlighted2 = PseudoClass.getPseudoClass("highlighted1");
  public  PseudoClass higlighted3 = PseudoClass.getPseudoClass("highlighted2");
    private ResultSet Consulta_Resultante = null;

    public ObservableList<tikes> getObservable_Contenedor_CerradosReporte() {
        return Observable_Contenedor_CerradosReporte;
    }

    public void setObservable_Contenedor_CerradosReporte(ObservableList<tikes> Observable_Contenedor_CerradosReporte) {
        this.Observable_Contenedor_CerradosReporte = Observable_Contenedor_CerradosReporte;
    }

    public List getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error.add(Error);
    }

    public List getValoresError() {
        return ValoresError;
    }

    public void setValoresError(int ValoresError) {
        this.ValoresError.add(ValoresError);
    }

    public ObservableList<tikes> getObservable_Contenedor() {
        return Observable_Contenedor;
    }

    public ObservableList<tikes> getObservable_Contenedor_Constestado() {
        return Observable_Contenedor_Constestado;
    }

    public ObservableList<tikes> getObservable_Contenedor_Cerrados() {
        return Observable_Contenedor_Cerrados;
    }

    public List getLista_clientes_TicketsC() {
        return Lista_clientes_TicketsC;
    }

    public List getLista_clientes_TicketsCE() {
        return Lista_clientes_TicketsCE;
    }

    public List getLista_clientes_Tickets() {
        return Lista_clientes_Tickets;
    }

    public void setLista_clientes_Tickets(String Valores) {
        this.Lista_clientes_Tickets.add(Valores);
    }
    // Procedimiento para RMA

    public void Busqueda_general() {
        Lista_clientes_Tickets.clear();
        Observable_Contenedor.clear();
        try {
            objeto_bas.auxConsulta = objeto_bas.Tikec("E");
            while (objeto_bas.auxConsulta.next()) {
                Lista_clientes_Tickets.add(objeto_bas.auxConsulta.getString("usuario"));
                Observable_Contenedor.add(new tikes(objeto_bas.auxConsulta.getString("numero").trim()
                        , objeto_bas.auxConsulta.getString("departamento").trim()
                                , objeto_bas.auxConsulta.getString("usuario").trim()
                        , objeto_bas.auxConsulta.getString("asunto").trim()
                        , objeto_bas.auxConsulta.getString("tecnico"), objeto_bas.auxConsulta.getDate("fechainicio")
                        , objeto_bas.auxConsulta.getString("direccion").trim()
                        , objeto_bas.auxConsulta.getString("abiertopor").trim()
                        , objeto_bas.auxConsulta.getString("detalle").trim())); }
        } catch (SQLException ex) {
         objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }

    }

    public void Busqueda_nombre(String Nombre) {
        Observable_Contenedor.clear();
        try {
            Consulta_Resultante = objeto_bas.tablas("SELECT numero,departamento,usuario,asunto,tecnico,direccion,abiertopor,Detalleadicional,fechainicio FROM [BDAirnet].[dbo].[tickets] where estado = 'Espera' and respuesta=' ' and usuario ='" + Nombre + "'");
            while (Consulta_Resultante.next()) {

                Observable_Contenedor.add(new tikes(Consulta_Resultante.getString("numero").trim()
                        , Consulta_Resultante.getString("departamento").trim()
                                , Consulta_Resultante.getString("usuario").trim()
                        , Consulta_Resultante.getString("asunto").trim()
                        , Consulta_Resultante.getString("tecnico"), Consulta_Resultante.getDate("fechainicio")
                        , Consulta_Resultante.getString("direccion").trim()
                        , Consulta_Resultante.getString("abiertopor").trim()
                        , Consulta_Resultante.getString("Detalleadicional").trim())); }
        } catch (SQLException ex) {
               objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }

    }

    public void Busqueda_Fecha(String fci, String fci3) {
        Observable_Contenedor.clear();
        try {
            Consulta_Resultante = objeto_bas.tablas("select numero,departamento,usuario,asunto,tecnico,direccion,abiertopor,Detalleadicional,fechainicio from [BDAirnet].[dbo].[tickets] where estado = 'Espera' and respuesta=' ' and (fecha between '" + fci + "' and '" + fci3 + "')");
            while (Consulta_Resultante.next()) {

                Observable_Contenedor.add(new tikes(Consulta_Resultante.getString("numero").trim()
                        , Consulta_Resultante.getString("departamento").trim()
                                , Consulta_Resultante.getString("usuario").trim()
                        , Consulta_Resultante.getString("asunto").trim()
                        , Consulta_Resultante.getString("tecnico"), Consulta_Resultante.getDate("fechainicio")
                        , Consulta_Resultante.getString("direccion").trim()
                        , Consulta_Resultante.getString("abiertopor").trim()
                        , Consulta_Resultante.getString("Detalleadicional").trim())); }
        } catch (SQLException ex) {
              objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }

    }
    // Procedimiento para RMAR

    public void Busqueda_general_RMAR() {
        Lista_clientes_Tickets.clear();
        Observable_Contenedor_Constestado.clear();
        try {
            objeto_bas.consulta = objeto_bas.Tikec("C");
            while (objeto_bas.consulta.next()) {
                Lista_clientes_TicketsC.add(objeto_bas.consulta.getString("usuario").trim());
                Observable_Contenedor_Constestado.add(new tikes(objeto_bas.consulta.getString("numero").trim()
                        , objeto_bas.consulta.getString("departamento").trim()
                                , objeto_bas.consulta.getString("usuario").trim()
                        , objeto_bas.consulta.getString("asunto").trim()
                        , objeto_bas.consulta.getString("tecnico"), objeto_bas.consulta.getDate("fechainicio")
                        , objeto_bas.consulta.getString("direccion").trim()
                        , objeto_bas.consulta.getString("abiertopor").trim()
                        , objeto_bas.consulta.getString("detalle").trim())); }
        } catch (SQLException ex) {
              objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }

    }

    public void Busqueda_Nombre_RMAR(String Nombre) {
        Observable_Contenedor_Constestado.clear();
        try {
            Consulta_Resultante = objeto_bas.tablas("SELECT numero,departamento,usuario,asunto,tecnico,direccion,abiertopor,Detalleadicional,fechainicio FROM [BDAirnet].[dbo].[tickets] where estado = 'Espera' and respuesta='Contestada' and usuario ='" + Nombre + "'");
            while (Consulta_Resultante.next()) {
                Observable_Contenedor_Constestado.add(new tikes(Consulta_Resultante.getString("numero").trim()
                        , Consulta_Resultante.getString("departamento").trim()
                                , Consulta_Resultante.getString("usuario").trim()
                        , Consulta_Resultante.getString("asunto").trim()
                        , Consulta_Resultante.getString("tecnico"), Consulta_Resultante.getDate("fechainicio")
                        , Consulta_Resultante.getString("direccion").trim()
                        , Consulta_Resultante.getString("abiertopor").trim()
                        , Consulta_Resultante.getString("Detalleadicional").trim()));
            
            }
        } catch (SQLException ex) {
              objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }

    }

    public void Busqueda_Fecha_RMAR(String fci, String fci3) {
        Observable_Contenedor_Constestado.clear();
        try {
            Consulta_Resultante = objeto_bas.tablas("select numero,departamento,usuario,asunto,tecnico,direccion,abiertopor,Detalleadicional,fechainicio from [BDAirnet].[dbo].[tickets] where estado = 'Espera' and respuesta='Contestada' and (fecha between '" + fci + "' and '" + fci3 + "')");
            while (Consulta_Resultante.next()) {

                Observable_Contenedor_Constestado.add(new tikes(Consulta_Resultante.getString("numero").trim()
                        , Consulta_Resultante.getString("departamento").trim()
                                , Consulta_Resultante.getString("usuario").trim()
                        , Consulta_Resultante.getString("asunto").trim()
                        , Consulta_Resultante.getString("tecnico"), Consulta_Resultante.getDate("fechainicio")
                        , Consulta_Resultante.getString("direccion").trim()
                        , Consulta_Resultante.getString("abiertopor").trim()
                        , Consulta_Resultante.getString("Detalleadicional").trim()));
            }
        } catch (SQLException ex) {
              objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }

    }
    // Clase RMA CERRADOS

    public void Busqueda_RMAC() {
        Lista_clientes_Tickets.clear();
        Observable_Contenedor_Cerrados.clear();
        try {
            Consulta_Resultante = objeto_bas.Tikec("S");
            while (Consulta_Resultante.next()) {
                Lista_clientes_TicketsCE.add(Consulta_Resultante.getString("usuario"));
                //   Observable_Contenedor_Cerrados.add(new tikes(Consulta_Resultante.getString("numero"),Consulta_Resultante.getString("departamento"),Consulta_Resultante.getString("usuario"),Consulta_Resultante.getString("asunto"),Consulta_Resultante.getString("tecnico"),Consulta_Resultante.getDate("fechainicio"),Consulta_Resultante.getString("direccion"),Consulta_Resultante.getString("abiertopor"),Consulta_Resultante.getString("detalle")));
            }
        } catch (SQLException ex) {
             objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }
    }

    public void Busqueda_Nombre_RMAC(String Nombre) {
        Observable_Contenedor_Cerrados.clear();

        Consulta_Resultante = objeto_bas.tablas("select numero,departamento,usuario,asunto,tecnico,direccion,abiertopor,Detalleadicional,fechainicio from [BDAirnet].[dbo].[tickets] where usuario ='" + Nombre + "' and estado = 'Cerrada' ");
        try {
            while (Consulta_Resultante.next()) {

                Observable_Contenedor_Cerrados.add(new tikes(Consulta_Resultante.getString("numero").trim()
                        , Consulta_Resultante.getString("departamento").trim()
                                , Consulta_Resultante.getString("usuario").trim()
                        , Consulta_Resultante.getString("asunto").trim()
                        , Consulta_Resultante.getString("tecnico"), Consulta_Resultante.getDate("fechainicio")
                        , Consulta_Resultante.getString("direccion").trim()
                        , Consulta_Resultante.getString("abiertopor").trim()
                        , Consulta_Resultante.getString("Detalleadicional").trim()));
            }
        } catch (SQLException ex) {
              objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }
    }

    public void Busqueda_Fecha_RMAC(String fci, String fci3) {

        Observable_Contenedor_Cerrados.clear();
        Consulta_Resultante = objeto_bas.tablas("select numero,departamento,usuario,asunto,tecnico,direccion,abiertopor,Detalleadicional,fechainicio from [BDAirnet].[dbo].[tickets] where estado = 'Espera' and respuesta='Contestada' and (fecha between '" + fci + "' and '" + fci3 + "')");
        try {
            while (Consulta_Resultante.next()) {

                Observable_Contenedor_Cerrados.add(new tikes(Consulta_Resultante.getString("numero").trim()
                        , Consulta_Resultante.getString("departamento").trim()
                                , Consulta_Resultante.getString("usuario").trim()
                        , Consulta_Resultante.getString("asunto").trim()
                        , Consulta_Resultante.getString("tecnico"), Consulta_Resultante.getDate("fechainicio")
                        , Consulta_Resultante.getString("direccion").trim()
                        , Consulta_Resultante.getString("abiertopor").trim()
                        , Consulta_Resultante.getString("Detalleadicional").trim()));
            }
        } catch (SQLException ex) {
              objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }
    }

    public void Inicial_Reporte() {
        Lista_clientes_Tickets.clear();

        Consulta_Resultante = objeto_bas.tablas("SELECT  [DESCRIPCION] FROM [BDAirnet].[dbo].[TBCODIGOERROR]");
        try {
            while (Consulta_Resultante.next()) {
                Lista_clientes_Tickets.add(Consulta_Resultante.getString("DESCRIPCION"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clase_Uso_Tickets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Funciones_Reportes(String tipo, String fci, String fci3) {
        int con = 0;
        Observable_Contenedor.clear();
        Consulta_Resultante = objeto_bas.tablas("select numero,departamento,usuario,asunto,tecnico,direccion,abiertopor,Detalleadicional,fechainicio from [BDAirnet].[dbo].[tickets] where  asunto='" + tipo + "' and  estado = 'Cerrada' and (CONVERT (datetime, fecha, 111) >= CONVERT (datetime,'" + fci + "', 111) and CONVERT (datetime, fecha, 111) <=CONVERT (datetime,'" + fci3 + "', 111))");
        // datos.getColumns ().add (usuario1);
        try {
            while (Consulta_Resultante.next()) {

                Observable_Contenedor.add(new tikes(Consulta_Resultante.getString("numero").trim()
                        , Consulta_Resultante.getString("departamento").trim()
                                , Consulta_Resultante.getString("usuario").trim()
                        , Consulta_Resultante.getString("asunto").trim()
                        , Consulta_Resultante.getString("tecnico"), Consulta_Resultante.getDate("fechainicio")
                        , Consulta_Resultante.getString("direccion").trim()
                        , Consulta_Resultante.getString("abiertopor").trim()
                        , Consulta_Resultante.getString("Detalleadicional").trim()));con++;
            }
        } catch (SQLException ex) {
            objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }
        objeto_bas.notificaciones("Total de " + tipo + " : " + con, "I");
    }

    public void Reportes_impreciones(String num) {

        String jr = null;
        try {
            Map parametro = new HashMap();
            parametro.put("numero", num);
            objeto_bas.CargarPropiedades();
            jr = objeto_bas.properties.get("RutaArchivos") + "\\ArchivosdeImpresion\\DAtostikects.jasper";
            String printFileName;
            printFileName = JasperFillManager.fillReportToFile(jr, parametro, objeto_bas.cone());
            pdfg(printFileName, num);
        } catch (SQLServerException | JRException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Reportes_impreciones_General(String tipo, String fci, String fci3) {

        String jr = null;
        try {
            Map parametro = new HashMap();
            parametro.put("CONDICION", tipo);
            parametro.put("fechainical", fci);
            parametro.put("fechafinal", fci3);
            objeto_bas.CargarPropiedades();
            jr = objeto_bas.properties.get("RutaArchivos") + "\\ArchivosdeImpresion\\ReportedeTickets.jasper";
            String printFileName;
            printFileName = JasperFillManager.fillReportToFile(jr, parametro, objeto_bas.cone());
            pdfg(printFileName, tipo);
        } catch (SQLServerException | JRException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void pdf1(String printFileName, String numero) throws IOException {
        try {

            if (printFileName != null) {
                objeto_bas.CargarPropiedades();

                JasperExportManager.exportReportToPdfFile(printFileName, objeto_bas.properties.get("RutaArchivos") + "\\ArchivosdeImpresion\\" + numero + ".pdf");
                File objetofile = new File(objeto_bas.properties.get("RutaArchivos") + "\\ArchivosdeImpresion\\" + numero + ".pdf");

                Desktop.getDesktop().open(objetofile);
            }
        } catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }

    public void pdfg(String printFileName, String numero) throws IOException {
        try {

            if (printFileName != null) {
                objeto_bas.CargarPropiedades();
                JasperExportManager.exportReportToPdfFile(printFileName,
                        objeto_bas.properties.get("RutaArchivos") + "\\ArchivosdeImpresion\\" + numero + ".pdf");
                File objetofile = new File(objeto_bas.properties.get("RutaArchivos") + "\\ArchivosdeImpresion\\" + numero + ".pdf");

                Desktop.getDesktop().open(objetofile);
            }
        } catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CodigoErrores(String parametro) throws SQLException {

        this.Error.clear();
        this.ValoresError.clear();

        ResultSet ListaCodigo = objeto_bas.tablas("SELECT [codigo]  FROM [BDAirnet].[dbo].[tickets] where   estado='Cerrada' and   [codigo] !=' ' and fecha !='null' " + parametro + " group by [codigo]");
        while (ListaCodigo.next()) {
            setError(ListaCodigo.getString("codigo").trim());
            ResultSet resulterror = objeto_bas.tablas(" select count(codigo) as codigo FROM [BDAirnet].[dbo].[tickets]  where estado = 'Cerrada' and fecha !='null' and codigo='" + ListaCodigo.getString("codigo").replace(" ", "") + "'" + parametro);
            while (resulterror.next()) {
                setValoresError(resulterror.getInt("codigo"));
            }
        }

    }

    public void FuncoReporteG(String parametro) {

        Observable_Contenedor_CerradosReporte.clear();

        Consulta_Resultante = objeto_bas.tablas("SELECT numero,departamento,usuario,asunto,tecnico,direccion,abiertopor,Detalleadicional,fechainicio  FROM [BDAirnet].[dbo].[tickets] where  estado='Cerrada' and    [codigo] !=' ' and fecha !='null' " + parametro);
        try {
            while (Consulta_Resultante.next()) {

                Observable_Contenedor_CerradosReporte.add(new tikes(Consulta_Resultante.getString("numero").trim()
                        , Consulta_Resultante.getString("departamento").trim()
                                , Consulta_Resultante.getString("usuario").trim()
                        , Consulta_Resultante.getString("asunto").trim()
                        , Consulta_Resultante.getString("tecnico"), Consulta_Resultante.getDate("fechainicio")
                        , Consulta_Resultante.getString("direccion").trim()
                        , Consulta_Resultante.getString("abiertopor").trim()
                        , Consulta_Resultante.getString("Detalleadicional").trim()));}
        } catch (SQLException ex) {
            objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }
    }

    public void ReporteServicioTecnicoImpre(String Tecnico, String fechainicial, String FechaFinal, String valores) {

        String jr = null;
        String nombredoc = Tecnico + fechainicial + FechaFinal;
        try {
            Map parametro = new HashMap();
            parametro.put("CONDICION", Tecnico);
            parametro.put("fechainical", fechainicial);
            parametro.put("fechafinal", FechaFinal);
            parametro.put("EVENTOS", valores);
            objeto_bas.CargarPropiedades();
            jr = objeto_bas.properties.get("RutaArchivos") + "\\ArchivosdeImpresion\\ReporteServicoTectico.jasper";
            String printFileName;
            printFileName = JasperFillManager.fillReportToFile(jr, parametro, objeto_bas.cone());
            pdf1(printFileName, nombredoc.replace(" ", ""));
        } catch (SQLServerException | JRException | IOException ex) {
             objeto_bas.notificaciones("Error nivel: "+ex.getMessage(), "I");
        }
    }
}
