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
public class ClaseArchivoBanco {
     private  String  TIPO;
     private String CONTRAPARTIDA;
     private String MONEDA;
     private String VALOR;
     private String FORMADECOBRO;
     private String B1;
     private String B2;
     private String REFERENCIA;
     private String TIPOID;
     private String NUMEROID;
     private String NOMBREBENEFICIARIO;

    public String getB1() {
        return B1;
    }

    public void setB1(String B1) {
        this.B1 = B1;
    }

    public String getB2() {
        return B2;
    }

    public void setB2(String B2) {
        this.B2 = B2;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public String getCONTRAPARTIDA() {
        return CONTRAPARTIDA;
    }

    public void setCONTRAPARTIDA(String CONTRAPARTIDA) {
        this.CONTRAPARTIDA = CONTRAPARTIDA;
    }

    public String getMONEDA() {
        return MONEDA;
    }

    public void setMONEDA(String MONEDA) {
        this.MONEDA = MONEDA;
    }

    public String getVALOR() {
        return VALOR;
    }

    public void setVALOR(String VALOR) {
        this.VALOR = VALOR;
    }

    public String getFORMADECOBRO() {
        return FORMADECOBRO;
    }

    public void setFORMADECOBRO(String FORMADECOBRO) {
        this.FORMADECOBRO = FORMADECOBRO;
    }

   

   

    public String getTIPOID() {
        return TIPOID;
    }

    public void setTIPOID(String TIPOID) {
        this.TIPOID = TIPOID;
    }

    public String getNUMEROID() {
        return NUMEROID;
    }

    public void setNUMEROID(String NUMEROID) {
        this.NUMEROID = NUMEROID;
    }

    public String getNOMBREBENEFICIARIO() {
        return NOMBREBENEFICIARIO;
    }

    public void setNOMBREBENEFICIARIO(String NOMBREBENEFICIARIO) {
        this.NOMBREBENEFICIARIO = NOMBREBENEFICIARIO;
    }

    public ClaseArchivoBanco(String TIPO, String CONTRAPARTIDA, String MONEDA, String VALOR, String FORMADECOBRO,String B1 ,String B2,String REFERENCIA, String TIPOID, String NUMEROID, String NOMBREBENEFICIARIO) {
        this.TIPO = TIPO;
        this.CONTRAPARTIDA = CONTRAPARTIDA;
        this.MONEDA = MONEDA;
        this.VALOR = VALOR;
        this.FORMADECOBRO = FORMADECOBRO;
        this.REFERENCIA = REFERENCIA;
        this.B1 = B1;
        this.B2 = B2;
        this.TIPOID = TIPOID;
        this.NUMEROID = NUMEROID;
        this.NOMBREBENEFICIARIO = NOMBREBENEFICIARIO;
    }

    public String getREFERENCIA() {
        return REFERENCIA;
    }

    public void setREFERENCIA(String REFERENCIA) {
        this.REFERENCIA = REFERENCIA;
    }
     
}
