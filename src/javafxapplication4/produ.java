/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

/**
 *
  * @author cl
 */
public class produ {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
   private String   producto;
    private String     proveedor;
    private String     serie;
    private String     macid;
    private String     pon;
    private String     precio;
    private String     cliente;
    private String     ingreso;
    private String     fechaactiva;
    private String     ip;
    private String     activa;
     private String    lote;
     private String    metraje;
       private String    estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public produ(String id, String producto, String proveedor, String serie, String macid, String pon, String precio ,String cliente, String ingreso, String fechaactiva, String ip, String activa, String lote, String metraje,String estado) {
       this.id=id;
        this.producto = producto;
        this.proveedor = proveedor;
        this.serie = serie;
        this.macid = macid;
        this.pon = pon;
        this.precio = precio;
        this.cliente = cliente;
        this.ingreso = ingreso;
        this.fechaactiva = fechaactiva;
        this.ip = ip;
        this.activa = activa;
        this.lote = lote;
        this.metraje = metraje;
        this.estado=estado;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMacid() {
        return macid;
    }

    public void setMacid(String macid) {
        this.macid = macid;
    }

    public String getPon() {
        return pon;
    }

    public void setPon(String pon) {
        this.pon = pon;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getFechaactiva() {
        return fechaactiva;
    }

    public void setFechaactiva(String fechaactiva) {
        this.fechaactiva = fechaactiva;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getActiva() {
        return activa;
    }

    public void setActiva(String activa) {
        this.activa = activa;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getMetraje() {
        return metraje;
    }

    public void setMetraje(String metraje) {
        this.metraje = metraje;
    }
    
    
}
