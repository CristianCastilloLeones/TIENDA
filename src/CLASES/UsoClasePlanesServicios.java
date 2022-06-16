/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import javafxapplication4.Clase_VistaCLientes;
import javafxapplication4.VariablesDeUso;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
  * @author cl
 */
public class UsoClasePlanesServicios {
    
    Clase_VistaCLientes mic=new Clase_VistaCLientes("", "");
     VariablesDeUso ob = new VariablesDeUso();
    bas q = new bas ();
 ObservableList<PlanesYservicios> planesT = FXCollections.observableArrayList();

    public ObservableList<PlanesYservicios> getPlanesT() {
        return planesT;
    }

    public void setPlanesT(ObservableList<PlanesYservicios> planesT) {
        this.planesT = planesT;
    }
      public void TrabajoInterno(){
      ob.consulta=q.tablas("SELECT [CODIGOMIKROTIK], [IDPLAN],[NOMBRE] ,[COMPARTICION],[ANCHOBANDA] ,[DESCARGA] ,[SUBIDA],[CANTDISPOSITIVOS] FROM [BDAirnet].[dbo].[TBPLAN]");
        try {
            while(ob.consulta.next()){
              planesT.add(new PlanesYservicios("\t\t"+ob.consulta.getString("IDPLAN"), "\t\t"+ob.consulta.getString("NOMBRE"), "\t\t"+ob.consulta.getString("DESCARGA")
                      , "\t\t"+ob.consulta.getString("SUBIDA"), "\t\t"+ob.consulta.getString("COMPARTICION"), "\t\t"+ob.consulta.getString("CANTDISPOSITIVOS")
                      , mic.VerificarListasMi(ob.consulta.getString("CODIGOMIKROTIK")), ""));  
            } } catch (SQLException ex) {
            Logger.getLogger(UsoClasePlanesServicios.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
      
  
}
