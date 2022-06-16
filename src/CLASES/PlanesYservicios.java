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
public class PlanesYservicios {
   
  private String Id;
  private String Plan;
  private String bajada;
  private String Subida;
  private String AnchoB;
  private String comparticion;
  private int Activos;
  private String Corte;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getPlan() {
        return Plan;
    }

    public void setPlan(String Plan) {
        this.Plan = Plan;
    }

    public String getBajada() {
        return bajada;
    }

    public void setBajada(String bajada) {
        this.bajada = bajada;
    }

    public String getSubida() {
        return Subida;
    }

    public void setSubida(String Subida) {
        this.Subida = Subida;
    }

    public String getAnchoB() {
        return AnchoB;
    }

    public void setAnchoB(String AnchoB) {
        this.AnchoB = AnchoB;
    }

    public String getComparticion() {
        return comparticion;
    }

    public void setComparticion(String comparticion) {
        this.comparticion = comparticion;
    }

    public int getActivos() {
        return Activos;
    }

    public void setActivos(int Activos) {
        this.Activos = Activos;
    }

    public String getCorte() {
        return Corte;
    }

    public void setCorte(String Corte) {
        this.Corte = Corte;
    }

    public PlanesYservicios() {
    }

    public PlanesYservicios(String Id, String Plan, String bajada, String Subida, String AnchoB, String comparticion, int Activos, String Corte) {
        this.Id = Id;
        this.Plan = Plan;
        this.bajada = bajada;
        this.Subida = Subida;
        this.AnchoB = AnchoB;
        this.comparticion = comparticion;
        this.Activos = Activos;
        this.Corte = Corte;
    }

}
