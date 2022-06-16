/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GESTION;

/**
 *
  * @author cl
 */
public class CLASEIP {
    
    private int ID;
    private String PLAN;
    private String IPACTUAL;
    private String IPGATE;
    private String IPBROAD;

    public CLASEIP(int ID, String PLAN, String IPACTUAL, String IPGATE, String IPBROAD) {
        this.ID = ID;
        this.PLAN = PLAN;
        this.IPACTUAL = IPACTUAL;
        this.IPGATE = IPGATE;
        this.IPBROAD = IPBROAD;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPLAN() {
        return PLAN;
    }

    public void setPLAN(String PLAN) {
        this.PLAN = PLAN;
    }

    public String getIPACTUAL() {
        return IPACTUAL;
    }

    public void setIPACTUAL(String IPACTUAL) {
        this.IPACTUAL = IPACTUAL;
    }

    public String getIPGATE() {
        return IPGATE;
    }

    public void setIPGATE(String IPGATE) {
        this.IPGATE = IPGATE;
    }

    public String getIPBROAD() {
        return IPBROAD;
    }

    public void setIPBROAD(String IPBROAD) {
        this.IPBROAD = IPBROAD;
    }
    
}
