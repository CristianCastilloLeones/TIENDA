/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;




public class enviodedocumentos  extends Thread {
 String xmlPath;
 String pathSignature;
 String passSignature;String pathOut;
 String nameFileOut;
    
    
    @Override
    public void  run(){
    
           
    }
   

    public enviodedocumentos(String xmlPath, String pathSignature, String passSignature, String pathOut, String nameFileOut) {
        this.xmlPath = xmlPath;
        this.pathSignature = pathSignature;
        this.passSignature = passSignature;
        this.pathOut = pathOut;
        this.nameFileOut = nameFileOut;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getPathSignature() {
        return pathSignature;
    }

    public void setPathSignature(String pathSignature) {
        this.pathSignature = pathSignature;
    }

    public String getPassSignature() {
        return passSignature;
    }

    public void setPassSignature(String passSignature) {
        this.passSignature = passSignature;
    }

    public String getPathOut() {
        return pathOut;
    }

    public void setPathOut(String pathOut) {
        this.pathOut = pathOut;
    }

    public String getNameFileOut() {
        return nameFileOut;
    }

    public void setNameFileOut(String nameFileOut) {
        this.nameFileOut = nameFileOut;
    }
    

    
    
    }

