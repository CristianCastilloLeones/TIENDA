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
public class Clase_clientes {
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Clase_clientes(int ID, String Clientes, String identificacion, float valores) {
        this.ID = ID;
        this.Clientes = Clientes;
        this.identificacion = identificacion;
        this.valores = valores;
    }
    private String Clientes;
    private String ip;
    private String Direccion;
    private String identificacion;
    private String NumO1;
    private String Num02;
    private float valores;

    public float getValores() {
        return valores;
    }

    public void setValores(float valores) {
        this.valores = valores;
    }

    Clase_clientes(String Clientes, String identificacion, float valores) {
        this.Clientes = Clientes;
        this.identificacion = identificacion;
        this.valores = valores;
    }

    public String getNumO1() {
        return NumO1;
    }

    public void setNumO1(String NumO1) {
        this.NumO1 = NumO1;
    }

    public String getNum02() {
        return Num02;
    }

    public void setNum02(String Num02) {
        this.Num02 = Num02;
    }
    private String deuda;
   

  

    public Clase_clientes(String Clientes, String ip, String Direccion, String identificacion,String deuda,String NumO1,String Num02 ) {
        this.Clientes = Clientes;
        this.ip = ip;
        this.Direccion = Direccion;
        this.identificacion = identificacion;
        this.deuda=deuda;
        this.NumO1=NumO1;
        this.Num02=Num02;
    }

    public Clase_clientes(String Clientes, String identificacion, String deuda) {
        this.Clientes = Clientes;
        this.identificacion = identificacion;
        this.deuda = deuda;
    }

    
    public Clase_clientes(String Clientes, String ip, String Direccion, String identificacion) {
        this.Clientes = Clientes;
        this.ip = ip;
        this.Direccion = Direccion;
        this.identificacion = identificacion;
    }
    
public String getDeuda() {
        return deuda;
    }

    public void setDeuda(String deuda) {
        this.deuda = deuda;
    }
    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getClientes() {
        return Clientes;
    }

    public void setClientes(String Clientes) {
        this.Clientes = Clientes;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
}
