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
public class HistorialdePagoTorre {

    private String NUMERORECIBO;
    private String FECHA;
    private String NOMBRE;
    private float VALORTOTAL;
    private String EMPLEADOEMISOR;
    private boolean PAGADO;
    private boolean IMPRESO;

    public HistorialdePagoTorre(String NUMERORECIBO, String FECHA, String NOMBRE, float VALORTOTAL, String EMPLEADOEMISOR, boolean PAGADO, boolean IMPRESO) {
        this.NUMERORECIBO = NUMERORECIBO;
        this.FECHA = FECHA;
        this.NOMBRE = NOMBRE;
        this.VALORTOTAL = VALORTOTAL;
        this.EMPLEADOEMISOR = EMPLEADOEMISOR;
        this.PAGADO = PAGADO;
        this.IMPRESO = IMPRESO;
    }

    public String getNUMERORECIBO() {
        return NUMERORECIBO;
    }

    public void setNUMERORECIBO(String NUMERORECIBO) {
        this.NUMERORECIBO = NUMERORECIBO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public float getVALORTOTAL() {
        return VALORTOTAL;
    }

    public void setVALORTOTAL(float VALORTOTAL) {
        this.VALORTOTAL = VALORTOTAL;
    }

    public String getEMPLEADOEMISOR() {
        return EMPLEADOEMISOR;
    }

    public void setEMPLEADOEMISOR(String EMPLEADOEMISOR) {
        this.EMPLEADOEMISOR = EMPLEADOEMISOR;
    }

    public boolean isPAGADO() {
        return PAGADO;
    }

    public void setPAGADO(boolean PAGADO) {
        this.PAGADO = PAGADO;
    }

    public boolean isIMPRESO() {
        return IMPRESO;
    }

    public void setIMPRESO(boolean IMPRESO) {
        this.IMPRESO = IMPRESO;
    }
    
    
}
