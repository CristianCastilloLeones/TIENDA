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
public class cuentasporbanco {
    private int item;
    private String Clientecobrado;
    private String fechacobrada;
    private String Valorcobrado;
    private String hyperlink;

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

   

    public String getClientecobrado() {
        return Clientecobrado;
    }

    public void setClientecobrado(String Clientecobrado) {
        this.Clientecobrado = Clientecobrado;
    }

    public String getFechacobrada() {
        return fechacobrada;
    }

    public void setFechacobrada(String fechacobrada) {
        this.fechacobrada = fechacobrada;
    }

    public String getValorcobrado() {
        return Valorcobrado;
    }

    public void setValorcobrado(String Valorcobrado) {
        this.Valorcobrado = Valorcobrado;
    }


    public cuentasporbanco(int item,String Clientecobrado, String fechacobrada, String Valorcobrado, String hyperlink) {
        this.Clientecobrado = Clientecobrado;
        this.fechacobrada = fechacobrada;
        this.Valorcobrado = Valorcobrado;
        this.hyperlink = hyperlink;
        this.item=item;
    }
 
}
