/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


/**
 *
  * @author cl
 */
public class VerificarIP {
    bas q = new bas();
     private final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
      private Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
       public  boolean isValidIPV4(final String s)
   {          
      return IPV4_PATTERN.matcher(s).matches();
   }
       
       public boolean buscar(String x){
           if(isValidIPV4(x)){
                 q.auxConsulta = q.tablas("SELECT count (ipv4) as valor  FROM [BDAirnet].[dbo].[TBCLIENTE] where ESTADO=1  AND ipv4 LIKE '%"+x+"%'");
     
                 try {
            while (q.auxConsulta.next()) {
                if(q.auxConsulta.getInt("valor")==0){
                    return true;
                }else return false;
            }} catch (SQLException ex) {
            Logger.getLogger(VerificarIP.class.getName()).log(Level.SEVERE, null, ex);
            }
           }else {
               return false;
           }
         
        return  false;
       }
       
       
}
