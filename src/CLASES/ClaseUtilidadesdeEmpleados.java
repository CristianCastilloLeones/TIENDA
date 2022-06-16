/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;

/**
 *
  * @author cl
 */
public class ClaseUtilidadesdeEmpleados {
    bas q = new bas();
    List liste = new ArrayList();
    List asuntol = new ArrayList();
    
    private void ListaEmpleados(){
        liste.clear();
        q.consulta  = q.tablas("SELECT ( [NOMBRES]+' '+APELLIDOS) as nombres FROM [BDAirnet].[dbo].[View_Datos_Empleado] where TIPOUSUARIO ='Tecnico' and cargo = 'Empl. serv. en general' and ESTADO !=0");
        try {
            while (q.consulta.next()) {
                liste.add(q.consulta .getString("nombres"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClaseUtilidadesdeEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void PredictorListaEmpleados(TextField predictorTecnicos){
        ListaEmpleados();
        q.prediccion(predictorTecnicos, liste);
    }
    
    private void codigoerrores(String filtro)  {
        asuntol.clear();
        q.consulta = q.tablas("SELECT [DESCRIPCION] FROM [BDAirnet].[dbo].[TBCODIGOERROR] where " + filtro);
        try {
            while (q.consulta.next()) {
                asuntol.add(q.consulta.getString("DESCRIPCION"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClaseUtilidadesdeEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void PredictorListaErroresTickecs(String filtro ,TextField asunto){
       codigoerrores( filtro);
       q.prediccion(asunto, asuntol);
    }
}
