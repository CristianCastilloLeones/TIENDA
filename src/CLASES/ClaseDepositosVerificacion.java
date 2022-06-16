/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

public class ClaseDepositosVerificacion {
   
    private String  IDREGISTRO;
    private String  ndocumento;
    private String  cliente;
    private String  fecha;
    private float  valor;

    public ClaseDepositosVerificacion(String IDREGISTRO, String ndocumento, String cliente, String fecha, float valor) {
        this.IDREGISTRO = IDREGISTRO;
        this.ndocumento = ndocumento;
        this.cliente = cliente;
        this.fecha = fecha;
        this.valor = valor;
    }

    public String getIDREGISTRO() {
        return IDREGISTRO;
    }

    public void setIDREGISTRO(String IDREGISTRO) {
        this.IDREGISTRO = IDREGISTRO;
    }

    public String getNdocumento() {
        return ndocumento;
    }

    public void setNdocumento(String ndocumento) {
        this.ndocumento = ndocumento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
}
