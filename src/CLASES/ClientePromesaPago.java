/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;


public class ClientePromesaPago {
    private String Cleintepromesadepago;
    private String ippromesapago;
    private String fechadecortepromesapago;

    public ClientePromesaPago(String Cleintepromesadepago, String ippromesapago, String fechadecortepromesapago) {
        this.Cleintepromesadepago = Cleintepromesadepago;
        this.ippromesapago = ippromesapago;
        this.fechadecortepromesapago = fechadecortepromesapago;
    }

    public String getCleintepromesadepago() {
        return Cleintepromesadepago;
    }

    public void setCleintepromesadepago(String Cleintepromesadepago) {
        this.Cleintepromesadepago = Cleintepromesadepago;
    }

    public String getIppromesapago() {
        return ippromesapago;
    }

    public void setIppromesapago(String ippromesapago) {
        this.ippromesapago = ippromesapago;
    }

    public String getFechadecortepromesapago() {
        return fechadecortepromesapago;
    }

    public void setFechadecortepromesapago(String fechadecortepromesapago) {
        this.fechadecortepromesapago = fechadecortepromesapago;
    }
    
    
}
