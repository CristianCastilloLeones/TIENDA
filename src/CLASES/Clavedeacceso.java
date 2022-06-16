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
public class Clavedeacceso {
    String clave,NumCualquiera = "12345678";;
    String[] vector;
    String clave1;
    int numeroVerificador;
 public String GenerarClaveAcceso(String fecha, String tipoComprobante, String serie,String Ruc,String Secuencial,String tipoEmision,String ambiente)
        {
            //16122017118021144290011001003000000064000000101
            
           // String clave = "", NumCualquiera = "12345678";
             vector = fecha.split("/");
            clave = vector[0] + vector[1] + vector[2] + tipoComprobante + Ruc + ambiente + serie + Secuencial + NumCualquiera +tipoEmision;
         //   ClaveAcceso ObjClave = new ClaveAcceso();
             clave1 =invertirCadena(clave);
           numeroVerificador =obtenerSumaPorDigitos(clave1);
          clave += "" + numeroVerificador;
            return clave;
        }
    public String invertirCadena(String cadena)
        {
            String cadenaInvertida = "";
            for (int x = cadena.length() - 1; x >= 0; x--)
            {
                cadenaInvertida = cadenaInvertida + cadena.charAt(x);
              
            }
            
            return cadenaInvertida;
        }

        public int obtenerSumaPorDigitos(String cadena)
        {
            int pivote = 2;
            int longitudCadena = cadena.length();
           
            int cantidadTotal = 0;
            int b = 1;
            for (int i = 0; i <longitudCadena; i++)
            {
                if (pivote == 8)
                {
                    pivote = 2;
                }
                int temporal =  Integer.parseInt(String.valueOf(cadena.charAt(i)));
                
                b++;
                temporal *= pivote;
                pivote++;
                cantidadTotal += temporal;
            }
            cantidadTotal = 11 - cantidadTotal % 11;
            switch (cantidadTotal)
            {
                case 11:
                    cantidadTotal = 0;
                    break;
                case 10:
                    cantidadTotal = 1;
                    break;
            }
            return cantidadTotal;
        }
}
