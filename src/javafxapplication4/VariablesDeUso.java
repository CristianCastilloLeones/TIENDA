/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import CLASES.bas;
import CLASES.Clase_clientes;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
  * @author cl
 */
public class VariablesDeUso {

    bas q = new bas();
    private double ValorTotal;
    public  double ValorTotal2;
    private int CONTEOS;
    public ResultSet consulta;
    public static String tipouuario = "";
    public static String val = null;
    public static String nombreusuario;
    private List valorid = new ArrayList();
    private List valorid2 = new ArrayList();

    public int getCONTEOS() {
        return CONTEOS;
    }

    public void setCONTEOS(String CONTEOS) {
        
        this.CONTEOS = Integer.parseInt(CONTEOS)+1;
    }

    public String Datos(){
        return String.valueOf(CONTEOS);
    }
    
    public void EncerarConteor(){
        this.CONTEOS=0;
    }
    public List getValorid2() {
        return valorid2;
    }

    public void setValorid2(List valorid2) {
        this.valorid2 = valorid2;
    }
    public void setValorListaId2(String Valor) {
        this.valorid2.add(Valor);
    }
    private List list = new ArrayList();
    private List listadeClientes = new ArrayList(); 
    private File origen;
    private File destino;
    public File imgFile;
    private Image image1;
    private String deudaCliente;
    private String Detalledepsosito;
    public String coment;
     String [] Meses={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
      Month mes = LocalDate.now().getMonth();
       int año=LocalDate.now().getYear();

    public double getValorTotal2() {
        return ValorTotal2;
    }

    public void setValorTotal2(double ValorTotal2) {
        this.ValorTotal2 = ValorTotal2;
    }
    
       
    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getDetalledepsosito() {
        return Detalledepsosito;
    }

    public void setDetalledepsosito(String Detalledepsosito) {
        this.Detalledepsosito =""+ Detalledepsosito;
    }
     
    ObservableList<Clase_clientes> Observable_Contenedor = FXCollections.observableArrayList();

    public ObservableList<Clase_clientes> getObservable_Contenedor() {
        return Observable_Contenedor;
    }

    public void setObservable_Contenedor(ObservableList<Clase_clientes> Observable_Contenedor) {
        this.Observable_Contenedor = Observable_Contenedor;
    }
     
    public String getDeudaCliente() {
        return deudaCliente;
    }

    public void setDeudaCliente(String deudaCliente) {
        this.deudaCliente = deudaCliente;
    }
    
    public File getOrigen() {
        return origen;
    }

    public List getListadeClientes() {
        return listadeClientes;
    }

    public void setListadeClientes(List listadeClientes) {
        this.listadeClientes = listadeClientes;
    }

    public static String getTipouuario() {
        return tipouuario;
    }

    public static void setTipouuario(String tipouuario) {
        VariablesDeUso.tipouuario = tipouuario;
    }

    public static String getVal() {
        return val;
    }

    public static void setVal(String val) {
        VariablesDeUso.val = val;
    }

    public static String getNombreusuario() {
        return nombreusuario;
    }

    public static void setNombreusuario(String nombreusuario) {
        VariablesDeUso.nombreusuario = nombreusuario;
    }

    public void setOrigen(File origen) {
        this.origen = origen;
    }

    public File getDestino() {
        return destino;
    }

    public void setDestino(File destino) {
        this.destino = destino;
    }

    public File getImgFile() {
        return imgFile;
    }

    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }

    public Image getImage1() {
        return image1;
    }

    public void setImage1(Image image1) {
        this.image1 = image1;
    }

    public List getValorid() {
        return valorid;
    }

    public void setValorid(List valorid) {

        this.valorid = valorid;
    }

    public void setValorListaId(String Valor) {
        this.valorid.add(Valor);
    }

    public double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(double ValorTotal) {
        this.ValorTotal = this.ValorTotal + ValorTotal;
    }

    public void Encerar() {
        this.ValorTotal = 0;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public List items() {
        consulta = q.tablas("SELECT [producto] FROM [BDAirnet].[dbo].[tpcategoriadj] where tipo='Venta'");
        try {
            while (consulta.next()) {
                list.add(consulta.getString("producto"));
            }
            list.add("Instalacion");
            list.add("Migracion");
            list.add("Cambio de Domicilio");
            list.add("Cambio de ONT");
            list.add("ONT");
            list.add("ROUTERS");
            list.add("Cambio de Contrato");
            Facturarmeses();
            
            return list;
        } catch (SQLException ex) {
            return null;
        }

    }
     public void Facturarmeses(){
       
    
    switch( mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase()){
            case "ENERO":
                for (int i=0;i<Meses.length;i++){
                  list.add("Mes "+Meses[i]+" "+año);
                }
                    break;
            case "FEBRERO":
                 for (int i=1;i<Meses.length;i++){
                   list.add("Mes "+Meses[i]+" "+año);
                }
                    break;
            case "MARZO":
                 for (int i=2;i<Meses.length;i++){
                   list.add("Mes "+Meses[i]+" "+año);
                }
                    break;
            case "ABRIL":
                 for (int i=3;i<Meses.length;i++){
                   list.add("Proporcional Mes "+Meses[i]+" "+año);
                }
                    break;
            case "MAYO":
                 for (int i=4;i<Meses.length;i++){
                   list.add("Mes "+Meses[i]+" "+año);
                }
                    break;
            case "JUNIO":
                 for (int i=5;i<Meses.length;i++){
                   list.add("Mes "+Meses[i]+" "+año);
                }
                    break;
            case "JULIO":
                 for (int i=6;i<Meses.length;i++){
                   list.add("Mes "+Meses[i]+" "+año);
                }
                    break;
            case "AGOSTO":
                 for (int i=7;i<Meses.length;i++){
                   list.add("Mes "+Meses[i]+" "+año);
                }
                    break;
            case "SEPTIEMBRE":
                 for (int i=8;i<Meses.length;i++){
                    list.add("Mes "+Meses[i]+" "+año);
                }
                    break;
            case "OCTUBRE":
                 for (int i=9;i<Meses.length;i++){
                 list.add("Mes "+Meses[i]+" "+año);
                 
                }
                 list.add("Mes "+Meses[0]+" "+(año+1));
                 list.add("Mes "+Meses[1]+" "+(año+1));
                 list.add("Mes "+Meses[2]+" "+(año+1));
                    break;
            case "NOVIEMBRE":
                 for (int i=10;i<Meses.length;i++){
                   list.add("Mes "+Meses[i]+" "+año);
                   
                }
                 list.add("Mes "+Meses[0]+" "+(año+1));
                 list.add("Mes "+Meses[1]+" "+(año+1));
                 list.add("Mes "+Meses[2]+" "+(año+1));
                    break;
            case "DICIEMBRE":
                 for (int i=11;i<Meses.length;i++){
                   
                     list.add("Mes "+Meses[i]+" "+año);
                }
                 list.add("Mes "+Meses[0]+" "+(año+1));
                 list.add("Mes "+Meses[1]+" "+(año+1));
                 list.add("Mes "+Meses[2]+" "+(año+1));
                    break;
        }}
}
