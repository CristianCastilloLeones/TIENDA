/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import javafxapplication4.Clase_VistaCLientes;
import javafxapplication4.VariablesDeUso;
import INDICADORES.EstadodeCuentasIndividualController;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
  * @author cl
 */
public class Clase_Estado_Individual {
   DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
    bas q = new bas();
    ResultSet FacEfe;
    ResultSet st;
    ResultSet facturasdeposito;
    ResultSet Tickets;
    ResultSet ConsuxCobrar;

    public ObservableList<cuentasporbanco> getDepositos() {
        return depositos;
    }

    public void setDepositos(ObservableList<cuentasporbanco> depositos) {
        this.depositos = depositos;
    }
    ObservableList<cuentasporbanco> depositos = FXCollections.observableArrayList();
    ObservableList<FacturasCobradas> efectivo = FXCollections.observableArrayList();
    ObservableList<Eventos> Eventosvi = FXCollections.observableArrayList();
    ObservableList<FacturasporCobrado> Xcobrar = FXCollections.observableArrayList();
    DecimalFormat formato1 = new DecimalFormat("#.00");
    public static String clientetext;
    private String fechinicio;
    private String fechafinal;
    private String Considion;

    public String getConsidion() {
        return Considion;
    }

    public void setConsidion(String Considion) {
        this.Considion = Considion;
    }

    public String getFechinicio() {
        return fechinicio;
    }

    public void setFechinicio(String fechinicio) {
        this.fechinicio = fechinicio;
    }

    public String getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(String fechafinal) {
        this.fechafinal = fechafinal;
    }

    public String getClientetext() {
        return clientetext;
    }

    public void setClientetext(String clientetext) {
        this.clientetext = clientetext;
    }

    public Clase_Estado_Individual(String fechinicio, String fechafinal,String Considion ) {
        this.fechinicio = fechinicio;
        this.fechafinal = fechafinal;
         this.Considion = Considion;
    }

    public Clase_Estado_Individual(String clientetext) {
        this.clientetext = clientetext;
    }
    VariablesDeUso ob = new VariablesDeUso();
    public void facturasRecepcionPago(){
         efectivo.clear();
         ob.getValorid2().clear();
       
         st = q.tablas("SELECT   iddetalle,numerofactura,detalle,valortotal,FECHADEORIGEN FROM [BDAirnet].[dbo].[detallesfacxtura]"
                 + " where cliente like '%" + clientetext + "%'  order by iddetalle asc");
        
       try {  while(st.next()){
           if(!ob.getValorid2().contains( st.getString("iddetalle"))){
              if(st.getString("numerofactura")==null){
                efectivo.add(new FacturasCobradas(
                        st.getString("FECHADEORIGEN")==null?"01/12/2020":st.getString("FECHADEORIGEN").trim(),
                            "-",
                            st.getString("detalle"), 
                            st.getString("valortotal"),"-",st.getString("valortotal")));  
                ob.setValorListaId2( st.getString("iddetalle"));
            }else {
                  
                q.consulta=q.tablas("SELECT   iddetalle,numerofactura,detalle,valortotal,FECHADEORIGEN FROM "
                                    + "[BDAirnet].[dbo].[detallesfacxtura] where codigo like '%"+q.BusquedaCedula(clientetext)+
                                    "%'  and numerofactura='"+st.getString("numerofactura")+"' order by iddetalle asc");
                while(q.consulta.next()){
                    efectivo.add(new FacturasCobradas(
                            q.consulta.getString("FECHADEORIGEN")==null?"01/12/2020":q.consulta.getString("FECHADEORIGEN").trim(),
                            q.consulta.getString("numerofactura").trim(),
                            q.consulta.getString("detalle").trim(), 
                            q.consulta.getString("valortotal").trim(),"-",q.consulta.getString("valortotal").trim()));
                   
                    ob.setValorListaId2( q.consulta.getString("iddetalle"));
                }
                 q.auxConsulta=q.tablas("SELECT total,nuemrofactura,fechadepago,formapago FROM [BDAirnet].[dbo].[tvdjFacurascliente] "
                 + "where   codigocontrato like '%"+q.BusquedaCedula(clientetext)+"%' and nuemrofactura like '%"+st.getString("numerofactura").trim()+"%'");
             
                 while(q.auxConsulta.next()){
                       efectivo.add(new FacturasCobradas(
                            q.auxConsulta.getString("fechadepago").trim(),
                            q.auxConsulta.getString("nuemrofactura").trim(),
                            q.auxConsulta.getString("fechadepago").trim()+" "+q.auxConsulta.getString("formapago").trim(), 
                            "-",q.auxConsulta.getString("total")==null ? st.getString("valortotal").trim():q.auxConsulta.getString("total").trim(),
                            q.FormatoDecimal
        (q.ConvertidorStringaDouble
        (q.auxConsulta.getString("total")==null ? 
                st.getString("valortotal"):q.auxConsulta.getString("total"))-q.ConvertidorStringaDouble(st.getString("valortotal")))));
                }
            }
        }}
        } catch (SQLException ex) {
            Logger.getLogger(EstadodeCuentasIndividualController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void Facturas_enfectivo() {
        efectivo.clear();
        double totalantiguao;

        st = q.tablas("SELECT [FECHACOBRO],[PRECIO] ,[NUMEROFACTURA] FROM [BDAirnet].[dbo].[FACTURAS_ANTIGUA]  where  PAGADO=1 AND (NOMBRES +' '+APELLIDOS) LIKE '%" + clientetext + "%' ");
        try {
            while (st.next()) {
                totalantiguao = (st.getFloat("PRECIO") + (st.getFloat("PRECIO") * 0.12));

                efectivo.add(new FacturasCobradas(st.getString("NUMEROFACTURA"), f.format(st.getDate("FECHACOBRO")), formato1.format(totalantiguao)));
            }
        } catch (SQLException ex) {

        }

        st = q.tablas("SELECT [nuemrofactura],[cliente],[fechadepago],[total] FROM [BDAirnet].[dbo].[tvdjFacurascliente] where cliente like '%" + clientetext + "%' and  formapago='Efectivo' ");
        try {
            while (st.next()) {
                efectivo.add(new FacturasCobradas(st.getString("nuemrofactura"), st.getString("fechadepago"), st.getString("total")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadodeCuentasIndividualController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Facturas_EfecREpor(){
       int numeroq=0;
        FacEfe = q.tablas("SELECT [nuemrofactura],[cliente],[Pagado],[total] FROM [BDAirnet].[dbo].[tvdjFacurascliente] where  formapago='Efectivo' and estado='INACTIVA' and (CONVERT (datetime, fechadepago, 103) between '" + fechinicio + "' and '" + fechafinal + "')"+Considion);
        try {
            while (FacEfe.next()) {
                 numeroq++;
                if (FacEfe.getString("total") == null) {
                    
                } else {
                    efectivo.add(new FacturasCobradas(numeroq, FacEfe.getString("cliente").trim(), FacEfe.getString("Pagado"), FacEfe.getString("total").replace(" ", "").replace(",", ".")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clase_Estado_Individual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void FacturadoTotal(){
       double numeroq=0;
       double numeroq1=0;
        FacEfe = q.tablas("SELECT [total] FROM [BDAirnet].[dbo].[tvdjFacurascliente] where  formapago='Efectivo' and estado='INACTIVA' and (CONVERT (datetime, fechadepago, 103) between '" + fechinicio + "' and '" + fechafinal + "')"+ "And serieca ='"+q.SerieCajeros(Considion)+"'");
        try {
            while (FacEfe.next()) {
                
                if (FacEfe.getString("total") == null) {
                    
                } else {
                numeroq =numeroq+q.ConvertidorStringaDouble(FacEfe.getString("total"));
                }
            }
            st = q.tablas("SELECT sum([cantidad]) as totalsalida FROM [BDAirnet].[dbo].[tvdjSalidadedinero] where (fechasalida between ' " + fechinicio + " 00:00:00:000' and '" + fechafinal + "  23:59:00:000') and entregadopor ='"+Considion+"'");
        try {
            while (st.next()) {
                numeroq1 =st.getFloat("totalsalida");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clase_Estado_Individual.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            q.notificaciones(numeroq+" - "+numeroq1+"= "+q.FormatoDecimal(numeroq-numeroq1), "I");
        } catch (SQLException ex) {
            Logger.getLogger(Clase_Estado_Individual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void facturas_Depositos() {
        depositos.clear();
        if (clientetext==null) {
            facturasdeposito = q.tablas("SELECT evidencia,[nuemrofactura],[cliente],[fechadepago] ,total FROM [BDAirnet].[dbo].[tvdjFacurascliente] where  formapago='Otros' and estado='INACTIVA' and (CONVERT (datetime, fechadepago, 103) between '" + fechinicio + "' and '" + fechafinal + "') "+Considion);
        } else {
            facturasdeposito = q.tablas("SELECT evidencia,[nuemrofactura],[cliente],[fechadepago] ,total FROM [BDAirnet].[dbo].[tvdjFacurascliente] where  cliente like '%" + clientetext + "%' and formapago='Otros' and estado='INACTIVA' ");
        }
int i=0;
        try {
            while (facturasdeposito.next()) {
                
                 depositos.add(new cuentasporbanco(++i,facturasdeposito.getString("cliente").trim()
                         , facturasdeposito.getString("fechadepago").trim()
                         , facturasdeposito.getString("total").trim()==null ? "0.0":facturasdeposito.getString("total").trim().replace(",", ".")
                         ,facturasdeposito.getString("evidencia").trim()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadodeCuentasIndividualController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Facturas_Eventos() {
        Eventosvi.clear();
        Tickets = q.tablas("SELECT  [numero] ,[asunto],[fecha]  ,[detalle] ,[evidencia],[estado] FROM [BDAirnet].[dbo].[tickets] where usuario like '%" + clientetext + "%'");
        try {
            while (Tickets.next()) {
                Eventosvi.add(new Eventos(Tickets.getString("numero"), Tickets.getString("asunto"), Tickets.getString("estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadodeCuentasIndividualController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Facturas_x_Cobrar() {
        Xcobrar.clear();
        Clase_RecepciondePago recepciondePago = new Clase_RecepciondePago(q.BusquedaCedula(clientetext),"E");
         ConsuxCobrar =recepciondePago.detalle(); //q.tablas("SELECT [numerofactura],[detalle],[valortotal] FROM [BDAirnet].[dbo].[detallesfacxtura] where cliente like '%" + clientetext + "%' and Estado=1");
        try {
            while (ConsuxCobrar.next()) {
                Xcobrar.add(new FacturasporCobrado(ConsuxCobrar.getString("detalle"), ConsuxCobrar.getString("numerofactura"), ConsuxCobrar.getString("valortotal")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstadodeCuentasIndividualController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salidadedinero()  {
        Eventosvi.clear();
       
       st = q.tablas("SELECT [solitadopor],[detalle],[cantidad] FROM [BDAirnet].[dbo].[tvdjSalidadedinero] where (fechasalida between ' " + fechinicio + " 00:00:00:000' and '" + fechafinal + "  23:59:00:000') "+Considion);
        try {
            while (st.next()) {
                Eventosvi.add(new Eventos(st.getString("solitadopor"), st.getString("detalle"), st.getString("cantidad")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clase_Estado_Individual.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }
    
    public void cargarvalores(){
        
        q.ventanasAxu("/INDICADORES/EstadodeCuentasIndividual.fxml","Estado de Movimientos del Cliente");
        
    }
    public void cargarvalores2(String x ){
        switch(x){
            case "A":
                 Clase_VistaCLientes.Factorbusqueda=null;
                 Clase_VistaCLientes.Factorbusqueda = clientetext;
                q.ventanasAxu("/CAJA/MODULOCAJA.fxml","Detalles de Deudas");
                break;
            case "B":
                 q.ventanasAxu("/javafxapplication4/facturasEfectivo.fxml","Valores Recaudados Efectivos");
                break;
            case "C":
                 q.ventanasAxu("/javafxapplication4/facturasDeposito.fxml","Valores Recaudados Depositos");
                break;
            case "D":
                 Clase_VistaCLientes.Factorbusqueda = clientetext;
                    q.ventanasAxu("/VISTACLIENTES/vistaclientes.fxml","Vista General de Cliente");
             case "E":
                 Clase_VistaCLientes.Factorbusqueda = clientetext;
                    q.ventanasAxu("VentanadeBaja.fxml","SALDOS");
                break;
        }
        
        
    }
    public ObservableList<FacturasporCobrado> getXcobrar() {
        return Xcobrar;
    }

    public void setXcobrar(ObservableList<FacturasporCobrado> Xcobrar) {
        this.Xcobrar = Xcobrar;
    }

    public ObservableList<Eventos> getEventosvi() {
        return Eventosvi;
    }

    public void setEventosvi(ObservableList<Eventos> Eventosvi) {
        this.Eventosvi = Eventosvi;
    }

    public ObservableList<FacturasCobradas> getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(ObservableList<FacturasCobradas> efectivo) {
        this.efectivo = efectivo;
    }
}
