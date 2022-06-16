/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

/**
 *
  * @author cl
 */
public class ListadoClientesGeneral {
   private int IDCLIENTE;
    
    private String CLIENTES;
    private String IDENTIFICACION;
    private String PLAN_CONTRATADO;
    private float VALOR_MENSUAL;
    private String IP;
    private String ESTADO_SERVICIO;
    private String ESTADO_CLIENTES;
    private String ESTADOFACTURADO; 

    public ListadoClientesGeneral(int IDCLIENTE, String CLIENTES, String IDENTIFICACION, String PLAN_CONTRATADO, float VALOR_MENSUAL, String IP, String ESTADO_SERVICIO, String ESTADO_CLIENTES, String ESTADOFACTURADO) {
        this.IDCLIENTE = IDCLIENTE;
        this.CLIENTES = CLIENTES;
        this.IDENTIFICACION = IDENTIFICACION;
        this.PLAN_CONTRATADO = PLAN_CONTRATADO;
        this.VALOR_MENSUAL = VALOR_MENSUAL;
        this.IP = IP;
        this.ESTADO_SERVICIO = ESTADO_SERVICIO;
        this.ESTADO_CLIENTES = ESTADO_CLIENTES;
        this.ESTADOFACTURADO = ESTADOFACTURADO;
    }

    
    public int getIDCLIENTE() {
        return IDCLIENTE;
    }

    public void setIDCLIENTE(int IDCLIENTE) {
        this.IDCLIENTE = IDCLIENTE;
    }

    public String getCLIENTES() {
        return CLIENTES;
    }

    public void setCLIENTES(String CLIENTES) {
        this.CLIENTES = CLIENTES;
    }

    public String getIDENTIFICACION() {
        return IDENTIFICACION;
    }

    public void setIDENTIFICACION(String IDENTIFICACION) {
        this.IDENTIFICACION = IDENTIFICACION;
    }

    public String getPLAN_CONTRATADO() {
        return PLAN_CONTRATADO;
    }

    public void setPLAN_CONTRATADO(String PLAN_CONTRATADO) {
        this.PLAN_CONTRATADO = PLAN_CONTRATADO;
    }

    public float getVALOR_MENSUAL() {
        return VALOR_MENSUAL;
    }

    public void setVALOR_MENSUAL(float VALOR_MENSUAL) {
        this.VALOR_MENSUAL = VALOR_MENSUAL;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getESTADO_SERVICIO() {
        return ESTADO_SERVICIO;
    }

    public void setESTADO_SERVICIO(String ESTADO_SERVICIO) {
        this.ESTADO_SERVICIO = ESTADO_SERVICIO;
    }

    public String getESTADO_CLIENTES() {
        return ESTADO_CLIENTES;
    }

    public void setESTADO_CLIENTES(String ESTADO_CLIENTES) {
        this.ESTADO_CLIENTES = ESTADO_CLIENTES;
    }

    public String getESTADOFACTURADO() {
        return ESTADOFACTURADO;
    }

    public void setESTADOFACTURADO(String ESTADOFACTURADO) {
        this.ESTADOFACTURADO = ESTADOFACTURADO;
    }
    
}
