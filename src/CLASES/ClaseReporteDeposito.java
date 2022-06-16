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
public class ClaseReporteDeposito {

    private String NUMEROFACTURA;
    private String FECHAPAGODEPSITO;
    private String FECHADEPOSITO;
    private String CAJERODEPOSITO;
    private String VALORCOBRADODEPOSITO;
    private String IDENTIFICACIONDEPOSITO;
    private String DETALLEDEPOSITO;
    private String COMENTARIODEPOSITO;
    private String IDDEPOSITO;
    private String NumeroDeposito;
    private String VALORCOBRADODEPOSITODOC;

    public String getNumeroDeposito() {
        return NumeroDeposito;
    }

    public void setNumeroDeposito(String NumeroDeposito) {
        this.NumeroDeposito = NumeroDeposito;
    }

    public String getNUMEROFACTURA() {
        return NUMEROFACTURA;
    }

    public String getVALORCOBRADODEPOSITODOC() {
        return VALORCOBRADODEPOSITODOC;
    }

    public void setVALORCOBRADODEPOSITODOC(String VALORCOBRADODEPOSITODOC) {
        this.VALORCOBRADODEPOSITODOC = VALORCOBRADODEPOSITODOC;
    }

    public void setNUMEROFACTURA(String NUMEROFACTURA) {
        this.NUMEROFACTURA = NUMEROFACTURA;
    }

    public String getFECHAPAGODEPSITO() {
        return FECHAPAGODEPSITO;
    }

    public void setFECHAPAGODEPSITO(String FECHAPAGODEPSITO) {
        this.FECHAPAGODEPSITO = FECHAPAGODEPSITO;
    }

    public String getFECHADEPOSITO() {
        return FECHADEPOSITO;
    }

    public void setFECHADEPOSITO(String FECHADEPOSITO) {
        this.FECHADEPOSITO = FECHADEPOSITO;
    }

    public String getCAJERODEPOSITO() {
        return CAJERODEPOSITO;
    }

    public void setCAJERODEPOSITO(String CAJERODEPOSITO) {
        this.CAJERODEPOSITO = CAJERODEPOSITO;
    }

    public String getVALORCOBRADODEPOSITO() {
        return VALORCOBRADODEPOSITO;
    }

    public void setVALORCOBRADODEPOSITO(String VALORCOBRADODEPOSITO) {
        this.VALORCOBRADODEPOSITO = VALORCOBRADODEPOSITO;
    }

    public String getIDENTIFICACIONDEPOSITO() {
        return IDENTIFICACIONDEPOSITO;
    }

    public void setIDENTIFICACIONDEPOSITO(String IDENTIFICACIONDEPOSITO) {
        this.IDENTIFICACIONDEPOSITO = IDENTIFICACIONDEPOSITO;
    }

    public String getDETALLEDEPOSITO() {
        return DETALLEDEPOSITO;
    }

    public void setDETALLEDEPOSITO(String DETALLEDEPOSITO) {
        this.DETALLEDEPOSITO = DETALLEDEPOSITO;
    }

    public String getCOMENTARIODEPOSITO() {
        return COMENTARIODEPOSITO;
    }

    public void setCOMENTARIODEPOSITO(String COMENTARIODEPOSITO) {
        this.COMENTARIODEPOSITO = COMENTARIODEPOSITO;
    }

    public String getIDDEPOSITO() {
        return IDDEPOSITO;
    }

    public void setIDDEPOSITO(String IDDEPOSITO) {
        this.IDDEPOSITO = IDDEPOSITO;
    }

    public ClaseReporteDeposito(String NUMEROFACTURA,String NumeroDeposito,String FECHAPAGODEPSITO, String FECHADEPOSITO, String CAJERODEPOSITO,String VALORCOBRADODEPOSITODOC ,String VALORCOBRADODEPOSITO, String IDENTIFICACIONDEPOSITO, String DETALLEDEPOSITO, String COMENTARIODEPOSITO, String IDDEPOSITO) {
        this.NUMEROFACTURA = NUMEROFACTURA;
        this.FECHAPAGODEPSITO = FECHAPAGODEPSITO;
        this.FECHADEPOSITO = FECHADEPOSITO;
        this.CAJERODEPOSITO = CAJERODEPOSITO;
        this.VALORCOBRADODEPOSITO = VALORCOBRADODEPOSITO;
        this.IDENTIFICACIONDEPOSITO = IDENTIFICACIONDEPOSITO;
        this.DETALLEDEPOSITO = DETALLEDEPOSITO;
        this.COMENTARIODEPOSITO = COMENTARIODEPOSITO;
        this.IDDEPOSITO = IDDEPOSITO;
         this.NumeroDeposito = NumeroDeposito;
          this.VALORCOBRADODEPOSITODOC = VALORCOBRADODEPOSITODOC;
    }
    
}
