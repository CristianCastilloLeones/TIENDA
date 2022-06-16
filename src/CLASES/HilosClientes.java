/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import CLASES.Clase_clientes;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 *
  * @author cl
 */
public class HilosClientes extends Task{
private boolean tipoinsta;
private boolean        sfo;
private boolean        sRe;
private boolean        porfecha;
private boolean        porplan;
private String       rangoinicial ;
private String       rangofinal  ;
private String planncontratado;



    public HilosClientes(boolean tipoinsta, boolean sfo, boolean sRe, boolean porfecha, boolean porplan, String rangoinicial, String rangofinal,String planncontratado) {
        this.tipoinsta = tipoinsta;
        this.sfo = sfo;
        this.sRe = sRe;
        this.porfecha = porfecha;
        this.porplan = porplan;
        this.rangoinicial = rangoinicial;
        this.rangofinal = rangofinal;
        this.planncontratado = planncontratado;
    }
    public boolean isTipoinsta() {
        return tipoinsta;
    }

    public String getPlanncontratado() {
        return planncontratado;
    }

    public void setPlanncontratado(String planncontratado) {
        this.planncontratado = planncontratado;
    }

    public void setTipoinsta(boolean tipoinsta) {
        this.tipoinsta = tipoinsta;
    }

    public boolean isSfo() {
        return sfo;
    }

    public void setSfo(boolean sfo) {
        this.sfo = sfo;
    }

    public boolean issRe() {
        return sRe;
    }

    public void setsRe(boolean sRe) {
        this.sRe = sRe;
    }

    public boolean isPorfecha() {
        return porfecha;
    }

    public void setPorfecha(boolean porfecha) {
        this.porfecha = porfecha;
    }

    public boolean isPorplan() {
        return porplan;
    }

    public void setPorplan(boolean porplan) {
        this.porplan = porplan;
    }

    public String getRangoinicial() {
        return rangoinicial;
    }

    public void setRangoinicial(String rangoinicial) {
        this.rangoinicial = rangoinicial;
    }

    public String getRangofinal() {
        return rangofinal;
    }

    public void setRangofinal(String rangofinal) {
        this.rangofinal = rangofinal;
    }
       public  ObservableList<Clase_clientes>  Clientes(){
            ClaseFuncinClientes obj =new ClaseFuncinClientes();
        obj.valoresInicales();
        return obj.getObservable_Contenedor();
    }
    @Override
    protected ObservableList<Clase_clientes> call() throws Exception {
       return Clientes();
    }
    
}
