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
public class ClasereporteCierredeCaja {

    private String CAJERO;
    private String FECHAAPERTURACAJA;
    private String FECHACIERRECAJA;
    private String CERRADAPOR;
    private String MONTOINICLA;
    private String TOTALEFECTIVO;
    private String TOTOALCHEQUES;

    public ClasereporteCierredeCaja(String CAJERO, String FECHAAPERTURACAJA, String FECHACIERRECAJA, String CERRADAPOR, String MONTOINICLA, String TOTALEFECTIVO, String TOTOALCHEQUES) {
        this.CAJERO = CAJERO;
        this.FECHAAPERTURACAJA = FECHAAPERTURACAJA;
        this.FECHACIERRECAJA = FECHACIERRECAJA;
        this.CERRADAPOR = CERRADAPOR;
        this.MONTOINICLA = MONTOINICLA;
        this.TOTALEFECTIVO = TOTALEFECTIVO;
        this.TOTOALCHEQUES = TOTOALCHEQUES;
    }

    public String getCAJERO() {
        return CAJERO;
    }

    public void setCAJERO(String CAJERO) {
        this.CAJERO = CAJERO;
    }

    public String getFECHAAPERTURACAJA() {
        return FECHAAPERTURACAJA;
    }

    public void setFECHAAPERTURACAJA(String FECHAAPERTURACAJA) {
        this.FECHAAPERTURACAJA = FECHAAPERTURACAJA;
    }

    public String getFECHACIERRECAJA() {
        return FECHACIERRECAJA;
    }

    public void setFECHACIERRECAJA(String FECHACIERRECAJA) {
        this.FECHACIERRECAJA = FECHACIERRECAJA;
    }

    public String getCERRADAPOR() {
        return CERRADAPOR;
    }

    public void setCERRADAPOR(String CERRADAPOR) {
        this.CERRADAPOR = CERRADAPOR;
    }

    public String getMONTOINICLA() {
        return MONTOINICLA;
    }

    public void setMONTOINICLA(String MONTOINICLA) {
        this.MONTOINICLA = MONTOINICLA;
    }

    public String getTOTALEFECTIVO() {
        return TOTALEFECTIVO;
    }

    public void setTOTALEFECTIVO(String TOTALEFECTIVO) {
        this.TOTALEFECTIVO = TOTALEFECTIVO;
    }

    public String getTOTOALCHEQUES() {
        return TOTOALCHEQUES;
    }

    public void setTOTOALCHEQUES(String TOTOALCHEQUES) {
        this.TOTOALCHEQUES = TOTOALCHEQUES;
    }
    
    public void Consulta(){
        
    }
}
