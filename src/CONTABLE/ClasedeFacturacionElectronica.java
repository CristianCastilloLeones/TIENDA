
package CONTABLE;

public class ClasedeFacturacionElectronica {
    private String numerofacelectronica;

    private String nuremobasefacturaelectronica;
    
    private String clientebasefactura;
   
    private float valorfacturadoV;
    
    private String seriecaja;
    
    private String fechageneradaFactura;
    
    private String estadoSRI;
    
    private int IDFACTURA;

    public ClasedeFacturacionElectronica(String numerofacelectronica, String nuremobasefacturaelectronica, String clientebasefactura, float valorfacturadoV, String seriecaja, String fechageneradaFactura, String estadoSRI, int IDFACTURA) {
        this.numerofacelectronica = numerofacelectronica;
        this.nuremobasefacturaelectronica = nuremobasefacturaelectronica;
        this.clientebasefactura = clientebasefactura;
        this.valorfacturadoV = valorfacturadoV;
        this.seriecaja = seriecaja;
        this.fechageneradaFactura = fechageneradaFactura;
        this.estadoSRI = estadoSRI;
        this.IDFACTURA=IDFACTURA;
    }

    public String getNumerofacelectronica() {
        return numerofacelectronica;
    }

    public void setNumerofacelectronica(String numerofacelectronica) {
        this.numerofacelectronica = numerofacelectronica;
    }

    public String getNuremobasefacturaelectronica() {
        return nuremobasefacturaelectronica;
    }

    public void setNuremobasefacturaelectronica(String nuremobasefacturaelectronica) {
        this.nuremobasefacturaelectronica = nuremobasefacturaelectronica;
    }

    public String getClientebasefactura() {
        return clientebasefactura;
    }

    public void setClientebasefactura(String clientebasefactura) {
        this.clientebasefactura = clientebasefactura;
    }

    public float getValorfacturadoV() {
        return valorfacturadoV;
    }

    public void setValorfacturadoV(float valorfacturadoV) {
        this.valorfacturadoV = valorfacturadoV;
    }

    public String getSeriecaja() {
        return seriecaja;
    }

    public void setSeriecaja(String seriecaja) {
        this.seriecaja = seriecaja;
    }

    public String getFechageneradaFactura() {
        return fechageneradaFactura;
    }

    public void setFechageneradaFactura(String fechageneradaFactura) {
        this.fechageneradaFactura = fechageneradaFactura;
    }

    public String getEstadoSRI() {
        return estadoSRI;
    }

    public void setEstadoSRI(String estadoSRI) {
        this.estadoSRI = estadoSRI;
    }

    public int getIDFACTURA() {
        return IDFACTURA;
    }

    public void setIDFACTURA(int IDFACTURA) {
        this.IDFACTURA = IDFACTURA;
    }
    
    
}
