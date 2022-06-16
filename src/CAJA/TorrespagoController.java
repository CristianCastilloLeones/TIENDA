/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CAJA;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 * FXML Controller class
 *
  * @author cl
 */
public class TorrespagoController implements Initializable {

    bas q = new bas();
    String nombre;
    int idprovee = 0;
    List proeveerodrestorre = new ArrayList();
    List usuarios = new ArrayList();
    String cedula = "";
    @FXML
    private Button btoregistrarsalida;
    @FXML
    private TextField autorizadopor;
    @FXML
    private TextField solicitante;
    @FXML
    private TextField quienentrega;
    @FXML
    private TextArea detallemotivo;
    @FXML
    private TextField motivos;
    @FXML
    private TextField cantidad;
    @FXML
    private TextField numerosalidad;
    @FXML
    private Button editar;
    @FXML
    private Button buscar;
    @FXML
    private CheckBox Editar;
    @FXML
    private TextField LuZ;
    @FXML
    private TextField totalaapagar;
    @FXML
    private DatePicker mesacancelar;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btoregistrarsalida.setOnAction((event) -> {
            guardardatos();
        });

        autorizadopor.setText("GALO ALFREDO ALVA MACAS");
        autorizadopor.setDisable(true);
        quienentrega.setText(VariablesDeUso.nombreusuario);
        quienentrega.setDisable(true);
        LuZ.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                LuZ.getText();
            }
            totalaapagar.setText(String.valueOf(Float.parseFloat(newText) + Float.parseFloat(cantidad.getText())));
        });
        cantidad.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                cantidad.getText();
            }
            totalaapagar.setText(String.valueOf(Float.parseFloat(newText) + Float.parseFloat(LuZ.getText())));
        });
        mesacancelar.setOnAction((event) -> {
            if(!solicitante.getText().trim().isEmpty()){
               try {
                datosdeproveedor(solicitante.getText());
            } catch (SQLException ex) {
                Logger.getLogger(TorrespagoController.class.getName()).log(Level.SEVERE, null, ex);
            }  
            }else {
                q.notificaciones("Seleccione el Solicitante para continuar..", "I");
            }
        });
        solicitante.textProperty().addListener((obs, oldText, newText) -> {
            try {
                datosdeproveedor(newText);
            } catch (SQLException ex) {
                Logger.getLogger(TorrespagoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        try {
            // TODO
            valoresiniciales();
        } catch (SQLException ex) {
            Logger.getLogger(TorrespagoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        q.prediccion(solicitante, proeveerodrestorre);
    }

    public void valoresiniciales() throws SQLException {
        String proveedoresdetorres = "Select IDPROVEEDORTORRE as ID, NOMBRE as TEXTO from TBProveedorTorre";
        String nnumero = q.ObtenerValorCampo4(" Top(1) NUMERORECIBO as TEXTO ", " [BDAirnet].[dbo].[TBPAGOTORRE] ", " order by NUMERORECIBO desc");
        String sc = String.valueOf(Integer.parseInt(nnumero) + 1);
        if (sc.length() <= 9) {
            for (int i = 1; i <= 9; i++) {
                if (sc.length() == 9) {
                } else {
                    sc = "0" + sc;
                }
            }
        }
        numerosalidad.setText(sc);
        ResultSet provee = q.tablas(proveedoresdetorres);

        while (provee.next()) {
            proeveerodrestorre.add(provee.getString("TEXTO"));
        }

    }

    public void datosdeproveedor(String x) throws SQLException {
        Month mes =  mesacancelar.getValue().getMonth();
           
// Obtienes el nombre del mes
        nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String valorconsulta = "SELECT TOP (1)[IDENTIFICACION],[IDPROVEEDORTORRE],[CONCEPTO] ,[DIRECCION_TORRE],[PAGOTORRE],[PAGOLUZ] FROM [BDAirnet].[dbo].[TBPROVEEDORTORRE] where NOMBRE='" + x + "'";
        ResultSet datosproevee = q.tablas(valorconsulta);
        String mensaje = "Recibí del Sr. GALO ALFREDO ALAVA MACAS , \n con Cédula de Ciudadanía #1204112724 \n la Cantidad de $ ";
        float total = 0;
        while (datosproevee.next()) {
            cedula = datosproevee.getString("IDENTIFICACION");
            motivos.setText(datosproevee.getString("CONCEPTO") + "de mes "+nombre );
            idprovee = datosproevee.getInt("IDPROVEEDORTORRE");
            cantidad.setText(String.valueOf(datosproevee.getFloat("PAGOTORRE")));
            LuZ.setText(String.valueOf(datosproevee.getFloat("PAGOLUZ")));
            total = datosproevee.getFloat("PAGOTORRE") + datosproevee.getFloat("PAGOLUZ");
            mensaje += total;
            if (datosproevee.getFloat("PAGOTORRE") > 0) {
                mensaje += " \n por concepto de Pago de Alquiler de una Terraza ";
            }
            if (datosproevee.getFloat("PAGOLUZ") > 0) {
                mensaje += "y Electricidad ";
            }
            mensaje = mensaje + " en " + datosproevee.getString("DIRECCION_TORRE") + " \n donde está instalada una torre de transmisión para internet, \n correspondiente al mes de " + nombre;
        }
        detallemotivo.setText(mensaje);
        totalaapagar.setText(String.valueOf(total));
    }

    public void guardardatos() {
        if(!solicitante.getText().trim().isEmpty()){
           try {
            q.EjecutarPROCEDUREPagoTorre(0, idprovee, detallemotivo.getText(), Float.parseFloat(cantidad.getText()), Float.parseFloat(LuZ.getText()), nombre, " ", numerosalidad.getText());
            imprimir();
        } catch (SQLException | JRException | IOException ex) {
            Logger.getLogger(TorrespagoController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }else {
             q.notificaciones("Seleccione el Solicitante para continuar..", "I");
        }
        

    }

    public void imprimir() throws JRException, IOException {
        q.CargarPropiedades();
        String jr = null;
        try {
            Map parametro = new HashMap();
            parametro.put("NOMBRE", solicitante.getText());
            parametro.put("CEDULA", cedula);
            parametro.put("NUMERO", numerosalidad.getText());
            jr = q.properties.getProperty("RutaArchivos") + "\\ArchivosdeImpresion\\Pagodetorresreporte.jasper";
            String printFileName;
            printFileName = JasperFillManager.fillReportToFile(jr, parametro, q.cone());
            q.pagotorres(printFileName, numerosalidad.getText());
        } catch (SQLServerException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
