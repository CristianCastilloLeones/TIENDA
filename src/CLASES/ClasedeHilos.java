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
public class ClasedeHilos implements Runnable {
   public Thread hilo;
    Clase_Uso_Tickets objeto;
    String Valor;

    ClasedeHilos(Clase_Uso_Tickets objeto, String Valor) {
      this.objeto=objeto;
      this.Valor=Valor;
      hilo= new Thread(this,Valor);
    }

    public static ClasedeHilos creayempieza(Clase_Uso_Tickets objeto, String Valor){
        ClasedeHilos mihilo = new ClasedeHilos( objeto, Valor);
        mihilo.hilo.start();
        return mihilo;
    }
    public String getValor() {
        return Valor;
    }

    public void setValor(String Valor) {
        this.Valor = Valor;
    }

    public Clase_Uso_Tickets getObjeto() {
        return objeto;
    }

    public void setObjeto(Clase_Uso_Tickets objeto) {
        this.objeto = objeto;
    }

    @Override
    public void run() {
        switch (this.Valor) {
            case "A":
                this.objeto.Busqueda_general();
                break;
            case "B":
                this.objeto.Busqueda_general_RMAR();
                break;
            case "C":
                this.objeto.Busqueda_RMAC();
                break;
        }

    }

}
