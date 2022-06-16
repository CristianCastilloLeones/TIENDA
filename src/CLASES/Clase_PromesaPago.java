/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import CAJA.PromesadepagoController;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
  * @author cl
 */
public class Clase_PromesaPago {
    private  List ip =new ArrayList();
   private  float valordededuda;
   private  String cedula;
   private String Cliente;
   bas q = new bas();
    private LocalDate iniciobusq;
    private String autorizadopor;

    public Clase_PromesaPago(String Cliente, LocalDate iniciobusq, String autorizadopor) {
        this.Cliente = Cliente;
        this.iniciobusq = iniciobusq;
        this.autorizadopor = autorizadopor;
    }

    public String getAutorizadopor() {
        return autorizadopor;
    }

    public void setAutorizadopor(String autorizadopor) {
        this.autorizadopor = autorizadopor;
    }
   

    public LocalDate getIniciobusq() {
        return iniciobusq;
    }

    public void setIniciobusq(LocalDate iniciobusq) {
        this.iniciobusq = iniciobusq;
    }

    public List getIp() {
        return ip;
    }

    public void setIp(List ip) {
        this.ip = ip;
    }

    public float getValordededuda() {
        return valordededuda;
    }

    public void setValordededuda(float valordededuda) {
        this.valordededuda = valordededuda;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }
   
   
   
    public void buscar(){
       
        ResultSet valorsdecleinte=q.tablas("SELECT IDENTIFICACION,ipv4 FROM [BDAirnet].[dbo].[TBCLIENTE] where (NOMBRES+' '+APELLIDOS)  like '%"+this.Cliente+"%'");
        try {
            while(valorsdecleinte.next()){
                this.cedula=valorsdecleinte.getString("IDENTIFICACION");
                ip.add(valorsdecleinte.getString("ipv4").replace(" ", ""));
            }
             float valq=0;
     
         ResultSet deuda=q.tablas("SELECT  [valortotal] FROM [BDAirnet].[dbo].[detallesfacxtura] where Estado='1' and codigo='"+this.cedula+"'");
         while(deuda.next()){
             if(!deuda.getString("valortotal").replace(" ", "").isEmpty()){
                 valq=valq+Float.parseFloat(deuda.getString("valortotal").replace(",", "."));} }
         this.valordededuda=valq;
         
         guardar();
        } catch (SQLException ex) {
            Logger.getLogger(PromesadepagoController.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    private void guardar(){
  
        
        for (Object ips:ip){
           String valoresainsertar="INSERT INTO [BDAirnet].[dbo].[tdvjPromesadePago]\n" +
"           ([Clientes] ,[Cedula] ,[Fechadepago] ,[Valordedeuda] ,[Estado] ,[Autorizadopor],[IP])\n" +
"     VALUES\n" +
"           ('"+this.Cliente+"' ,'"+this.cedula+"',CAST('"+this.iniciobusq.toString()+" 00:00:00' AS DATE),'"+this.valordededuda+"',1,'"+this.autorizadopor+"','"+ips+"')";
            System.out.println(valoresainsertar);
           q.InsertInsertar(valoresainsertar);
     q.Conseguiri(this.cedula.replace(" ", ""));
       } 
    }
}
