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
public class Operario {
   private String IDUSUARIO;
   private String   IDEMPLEADO;
   private String   IDTIPOUSUARIO;
  private String    USUARIO;
   private String   CLAVE;
  private byte ACTIVO;
   private String  TOKEN_DISPOSITIVO;

    public Operario(String IDUSUARIO, String IDEMPLEADO, String IDTIPOUSUARIO, String USUARIO, String CLAVE, byte ACTIVO, String TOKEN_DISPOSITIVO) {
        this.IDUSUARIO = IDUSUARIO;
        this.IDEMPLEADO = IDEMPLEADO;
        this.IDTIPOUSUARIO = IDTIPOUSUARIO;
        this.USUARIO = USUARIO;
        this.CLAVE = CLAVE;
        this.ACTIVO = ACTIVO;
        this.TOKEN_DISPOSITIVO = TOKEN_DISPOSITIVO;
    }

   
    public String getIDUSUARIO() {
        return IDUSUARIO;
    }

    public void setIDUSUARIO(String IDUSUARIO) {
        this.IDUSUARIO = IDUSUARIO;
    }

    public String getIDEMPLEADO() {
        return IDEMPLEADO;
    }

    public void setIDEMPLEADO(String IDEMPLEADO) {
        this.IDEMPLEADO = IDEMPLEADO;
    }

    public String getIDTIPOUSUARIO() {
        return IDTIPOUSUARIO;
    }

    public void setIDTIPOUSUARIO(String IDTIPOUSUARIO) {
        this.IDTIPOUSUARIO = IDTIPOUSUARIO;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getCLAVE() {
        return CLAVE;
    }

    public void setCLAVE(String CLAVE) {
        this.CLAVE = CLAVE;
    }

    public byte getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(byte ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public String getTOKEN_DISPOSITIVO() {
        return TOKEN_DISPOSITIVO;
    }

    public void setTOKEN_DISPOSITIVO(String TOKEN_DISPOSITIVO) {
        this.TOKEN_DISPOSITIVO = TOKEN_DISPOSITIVO;
    }
 

}
