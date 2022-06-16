/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import CLASES.Clase_clientes;
import javafxapplication4.VariablesDeUso;
import INDICADORES.CLIENTES_BController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
  * @author cl
 */
public class ClaseFuncinClientes {

    Clase_RecepciondePago ob;
    bas q = new bas();
    String Nombre = null;
    ResultSet con = null;

    ObservableList<Clase_clientes> Observable_Contenedor = FXCollections.observableArrayList();

    public ObservableList<Clase_clientes> getObservable_Contenedor() {
        return Observable_Contenedor;
    }

    public void setObservable_Contenedor(ObservableList<Clase_clientes> Observable_Contenedor) {
        this.Observable_Contenedor = Observable_Contenedor;
    }
    float deudaaxu;

    public void valoresInicales() {

        int c = 0;
        Observable_Contenedor.clear();
        con = Consulta_General(ObConsulta());
        try {
            while (con.next()) {
                deudaaxu = busquedaTotalDeuda(con.getString("IDENTIFICACION"));
                if (deudaaxu > 0) {
                    c++;
                    Nombre = con.getString("NOMBRES") + " " + con.getString("APELLIDOS");
                    Observable_Contenedor.add(new Clase_clientes(Nombre.trim(), con.getString("plancontratado").trim(), con.getString("DIRECCION").trim(), con.getString("IDENTIFICACION").trim(), "", con.getString("CELULAR1"), con.getString("CELULAR2")));

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CLIENTES_BController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void valoresInicales2(TableView tikeprincipal) {
        Observable_Contenedor.clear();
        con = Consulta_General(ObConsulta());
        try {
            while (con.next()) {
                long tiempoInicio = System.currentTimeMillis();
                deudaaxu = busquedaTotalDeuda(con.getString("IDENTIFICACION"));

                if (deudaaxu > 0) {

                    //  Observable_Contenedor.add(new Clase_clientes(Nombre.trim(), con.getString("plancontratado").trim(), con.getString("DIRECCION").trim(), con.getString("IDENTIFICACION").trim(), deudaaxu,con.getString("CELULAR1"),con.getString("CELULAR2")));
                    Observable_Contenedor.add(new Clase_clientes(con.getString("Cliente"), con.getString("IDENTIFICACION"), deudaaxu));
                    tikeprincipal.setItems(Observable_Contenedor);
                    long totalTiempo = System.currentTimeMillis() - tiempoInicio;
                    System.out.println("El tiempo de demora es :" + totalTiempo + " miliseg");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CLIENTES_BController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet Consulta_General(String x) {

        return q.tablas("SELECT  ([NOMBRES]+' '+[APELLIDOS]) as Cliente ,[IDENTIFICACION] FROM [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG] " + x);
        // return q.tablas("SELECT  CELULAR1, CELULAR2,[plancontratado],[DIRECCION],[NOMBRES],[APELLIDOS],[IDENTIFICACION] FROM [BDAirnet].[dbo].[VISTACLIENTENUEVOPROG]" + x);
    }

    public String ObConsulta() {
        return "where IDCLIENTE != 11775 and IDCLIENTE  != 11213  and IDCLIENTE  != 116  and IDCLIENTE  != 755  and IDCLIENTE  != 11260 and IDCLIENTE  != 1459 and IDCLIENTE != 11213\n"
                + " and IDCLIENTE != 11175\n"
                + " and IDCLIENTE != 10859\n"
                + " and IDCLIENTE != 10653\n"
                + " and IDCLIENTE != 1572\n"
                + " and IDCLIENTE != 1561\n"
                + " and IDCLIENTE != 1498\n"
                + " and IDCLIENTE != 1451\n"
                + " and IDCLIENTE != 564\n"
                + " and IDCLIENTE != 563\n"
                + " and IDCLIENTE != 555\n"
                + " and IDCLIENTE != 369\n"
                + " and IDCLIENTE != 359\n"
                + " and IDCLIENTE != 296\n"
                + " and IDCLIENTE != 281\n"
                + " and IDCLIENTE != 137\n"
                + " and IDCLIENTE != 132\n"
                + " and IDCLIENTE != 128\n"
                + " and IDCLIENTE != 5\n"
                + " and IDCLIENTE != 1  ";

    }

    public float busquedaTotalDeuda(String cedula) {
        ob = new Clase_RecepciondePago(cedula, "E");
        return ob.Valordeudante();
    }
    VariablesDeUso obj = new VariablesDeUso();
    double total = 0;

    public void NuevaEstilodBusqueda(TableView tikeprincipal, TableColumn<String, Clase_clientes> usuario, TextField TOTALDEUDA) {
        float totaqlplanes = 0, totalporplan = 0;
        ob = new Clase_RecepciondePago();
        Map<String, Float> FORMATODEUDAS = ob.DEUDASGENERALES();

        q.auxConsulta = q.tablas("SELECT [NOMBRE] FROM [BDAirnet].[dbo].[View_ListaPlanes]");
        String Cedula, NombrePLAN;
        try {
            while (q.auxConsulta.next()) {
                NombrePLAN = q.auxConsulta.getString("NOMBRE");
                Observable_Contenedor.add(new Clase_clientes("Plan", NombrePLAN, "------"));
                tikeprincipal.setItems(Observable_Contenedor);
                ob = new Clase_RecepciondePago(NombrePLAN, "E");
                con = ob.Valordeudante2();

                totalporplan = 0;
                while (con.next()) {
                    Cedula = con.getString("IDENTIFICACION");
                    Observable_Contenedor.add(new Clase_clientes(
                            con.getString("clientes"),
                             Cedula,
                             FORMATODEUDAS.get(Cedula)));
                    tikeprincipal.setItems(Observable_Contenedor);
                    totalporplan = totalporplan + FORMATODEUDAS.get(Cedula);
                }
                System.out.println(NombrePLAN);
                Observable_Contenedor.add(new Clase_clientes("Total de Deuda Plan", NombrePLAN, totalporplan));
                tikeprincipal.setItems(Observable_Contenedor);
                totaqlplanes = totaqlplanes + totalporplan;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClaseFuncinClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        TOTALDEUDA.setText(q.FormatoDecimal(totaqlplanes));

    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void Color(TableView tikeprincipal, TableColumn<String, Clase_clientes> usuario) {
        tikeprincipal.setRowFactory(TableView -> {
            TableRow<Clase_clientes> fila = new TableRow<>();
            fila.itemProperty().addListener((observable, oldValue, newValue) -> {
                PseudoClass higlighted3 = PseudoClass.getPseudoClass("highlighted4");
                fila.pseudoClassStateChanged(higlighted3, observable.getValue().getClientes().contains("Plan"));
                fila.pseudoClassStateChanged(higlighted3, observable.getValue().getClientes().contains("Total de Deuda Plan"));
            });
            return fila;
        });
    }

    // }
    public void DeudasporNumerocedulaAgrupados(TableView tikeprincipal, TableColumn<String, Clase_clientes> usuario, TextField TOTALDEUDA) throws SQLException {
        float A=0;
        ob = new Clase_RecepciondePago();
        Map<String, Float> FORMATODEUDAS = ob.DEUDASGENERALES();
        Iterator it = FORMATODEUDAS.keySet().iterator();
        while (it.hasNext()) {
            String Cedula = (String) it.next();
            float deuda = FORMATODEUDAS.get(Cedula);
            
            if(deuda>50){
                
                con = q.tablas("  SELECT TOP(1) IDCLIENTE,(NOMBRES+' '+ APELLIDOS) as clientes FROM [BDAirnet].[dbo].[TBCLIENTE] where IDENTIFICACION='"+Cedula+"' order by IDCLIENTE asc");
            while (con.next()) {
 
                Observable_Contenedor.add(new Clase_clientes(con.getInt("IDCLIENTE"),
                        con.getString("clientes"),
                         Cedula,
                         deuda));
                tikeprincipal.setItems(Observable_Contenedor);
                A = A + deuda;
            }
            }
            
          
        }
        TOTALDEUDA.setText(String.valueOf(A));
       

    }

}
