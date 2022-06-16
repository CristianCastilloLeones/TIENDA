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
public class FacturasCobradas {
    private int numero;
    private String Clientecobrado;
    private String fechacobrada;
    private String Valorcobrado;
    private String ValorDeuda;
    private String Saldo;
    private String FechadeRegistro;

    public FacturasCobradas(String FechadeRegistro,String Clientecobrado, String fechacobrada, String ValorDeuda, String Valorcobrado, String Saldo) {
        this.FechadeRegistro=FechadeRegistro;
        this.Clientecobrado = Clientecobrado;
        this.fechacobrada = fechacobrada;
        this.Valorcobrado = Valorcobrado;
        this.ValorDeuda = ValorDeuda;
        this.Saldo = Saldo;
    }

    public String getFechadeRegistro() {
        return FechadeRegistro;
    }

    public void setFechadeRegistro(String FechadeRegistro) {
        this.FechadeRegistro = FechadeRegistro;
    }

    
    public String getValorDeuda() {
        return ValorDeuda;
    }

    public void setValorDeuda(String ValorDeuda) {
        this.ValorDeuda = ValorDeuda;
    }

    public String getSaldo() {
        return Saldo;
    }

    public void setSaldo(String Saldo) {
        this.Saldo = Saldo;
    }
    

    public FacturasCobradas(int numero, String Clientecobrado, String fechacobrada, String Valorcobrado) {
        this.numero = numero;
        this.Clientecobrado = Clientecobrado;
        this.fechacobrada = fechacobrada;
        this.Valorcobrado = Valorcobrado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public FacturasCobradas(String Clientecobrado, String fechacobrada, String Valorcobrado) {
        this.Clientecobrado = Clientecobrado;
        this.fechacobrada = fechacobrada;
        this.Valorcobrado = Valorcobrado;
    }
    
   
    
}
