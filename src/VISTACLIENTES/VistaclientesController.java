/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTACLIENTES;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import CLASES.Clase_Estado_Individual;
import javafxapplication4.Clase_VistaCLientes;
import CLASES.OpenBrowser;
import javafxapplication4.VariablesDeUso;
import CLASES.VerificarIP;
import CLASES.bas;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import me.legrange.mikrotik.MikrotikApiException;

public class VistaclientesController implements Initializable {

    bas q = new bas();
    String serie = "";
    String AntiguaCI = "";
    @FXML
    private TextField nombre;
    @FXML
    private TextField ci;
    @FXML
    private TextField dire;
    @FXML
    private TextField tele1;
    @FXML
    private TextField tele2;
    @FXML
    private TextField correo;
    @FXML
    private TextField tipoins;
    @FXML
    private TextField plan;
    @FXML
    private TextField comparti;
    @FXML
    private TextField valorinst;
    @FXML
    private TextField valormens;
    @FXML
    private TextField fechadeing;
    @FXML
    private TextField latitud;
    @FXML
    private TextField longi;
    @FXML
    private TextField ip;
    @FXML
    private TextField cable;
    @FXML
    private TextField onuid;
    @FXML
    private TextField serviport;
    @FXML
    private TextField serial;
    @FXML
    private TextField ultimopago;
    @FXML
    private Button modificar;
    @FXML
    private Button guardar;
    @FXML
    private Button corte;
    @FXML
    private Button receptarpago;
    @FXML
    private Button imprimircontrato;
    @FXML
    private ComboBox<String> nombredecuent;
    @FXML
    private Button nuevoitem;
    @FXML
    TextField codigcontrato;
    @FXML
    private RadioButton cambiodomicion;
    @FXML
    private RadioButton migracionradio;
    final ToggleGroup group = new ToggleGroup();
    @FXML
    private Label codigocontra;
    @FXML
    private Label estadoservicio;
    @FXML
    private Label tikectsmodulo;
    @FXML
    private RadioButton cambioont;
    @FXML
    private Button realizarcambio;
    @FXML
    private Label titulotike;
    @FXML
    private CheckBox editdireccion;
    @FXML
    private CheckBox editelefono1;
    @FXML
    private CheckBox editelefo2;
    @FXML
    private CheckBox edemail;
    @FXML
    private CheckBox edip;
    @FXML
    private CheckBox edmensual;
    @FXML
    private Button promedadepago;
    @FXML
    private RadioButton cabiodeplan;
    @FXML
    private CheckBox EONUID;
    @FXML
    private CheckBox EdSP;
    @FXML
    private CheckBox Eserial;
    @FXML
    private Button historail;
    @FXML
    private Hyperlink iraip;
    @FXML
    private RadioButton CambioContrato;
    @FXML
    private Button ReceptarDinero;
    @FXML
    private CheckBox EstadoCliente;
    @FXML
    private TextField OLTPON;
    @FXML
    private TextField OLTTARJETA;
    @FXML
    private TextField nodoprincipal;
    @FXML
    private CheckBox EPON;
    @FXML
    private CheckBox ETAR;
    @FXML
    private CheckBox EOLT;
    @FXML
    private TextField datoslinea;
    @FXML
    private Button Guardar_Datos_olt;
    @FXML
    private Button btnguardarOlt;
    @FXML
    private CheckBox retiradoChech;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        retiradoChech.setOnAction((event) -> {
               
                if(retiradoChech.isSelected()){
                     guardar.setDisable(false);
                }else {
                     guardar.setDisable(true);
                }
        });

        btnguardarOlt.setOnAction((event) -> {
            if (EOLT.isSelected()) {
                if (!nodoprincipal.getText().trim().isEmpty()) {
                    q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set nodoprincipal='" 
                            + nodoprincipal.getText().trim() + "'" + "where IDENTIFICACION='" 
                            + ci.getText() + "'and IDCLIENTE='" 
                            + codigocontra.getText() + "'");
                    EOLT.setSelected(false);
                    q.notificaciones("Cambios Realizados con Exito", "I");
                } else {
                    q.notificaciones("Campos vacios", "I");
                }

            }
        });

        Guardar_Datos_olt.setOnAction((event) -> {
            GestionarLinea();
        });
        EPON.setOnAction((event) -> {
            if (EPON.isSelected()) {
                OLTPON.setEditable(true);
            }
        });
        ETAR.setOnAction((event) -> {
            if (ETAR.isSelected()) {
                OLTTARJETA.setEditable(true);
            }
        });
        EOLT.setOnAction((event) -> {
            if (EOLT.isSelected()) {
                nodoprincipal.setText("");
                nodoprincipal.setEditable(true);
                List<String> OLDIS = new ArrayList();
                ResultSet oltse = q.tablas("SELECT [NOMBRE]\n"
                        + "  FROM [BDAirnet].[dbo].[TablaOLTSCREDECIALES]");
                try {
                    while (oltse.next()) {
                        OLDIS.add(oltse.getString("NOMBRE"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(VistaclientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                q.prediccion(nodoprincipal, OLDIS);
            }
        });

        EstadoCliente.setDisable(true);
        CambioContrato.setOnAction((event) -> {
            if (CambioContrato.isSelected()) {
                guardar.setDisable(false);
                modificar.setDisable(true);
                nombre.setEditable(true);
                AntiguaCI = ci.getText().trim();
                ci.setEditable(true);
                q.notificaciones("Edite los campos de Nombres y numero de cedula y luego presione guardar", serie);
            } else {
                AntiguaCI = "";
                guardar.setDisable(true);
                modificar.setDisable(false);
                nombre.setEditable(false);
                ci.setEditable(false);
            }

        });

        iraip.setOnAction((event) -> {
            abrirNavegador();
        });
        historail.setOnAction((event) -> {
            Clase_Estado_Individual ob;
            ob = new Clase_Estado_Individual(nombre.getText());
            ob.cargarvalores();

        });

        try {
            llenar();
        } catch (SQLException ex) {
            q.notificaciones("Error al cargar Datos " + ex.getMessage(), "I");
        }
        ReceptarDinero.setOnAction((event) -> {
            ReceptorEfectivoController.cedulacliente = ci.getText();
            q.ventanas("/VISTACLIENTES/ReceptorEfectivo.fxml");

        });
        promedadepago.setOnAction((event) -> {
            q.ventanas("/CAJA/Promesadepago.fxml");

        });

        realizarcambio.setOnAction((event) -> {
            if (!serie.isEmpty()) {
                q.UpdateModificar("update [BDAirnet].[dbo].[tvproductosdj] set  estado='Activo',cliente='" + nombre.getText() + "',fechaactiva=" + "GETDATE()" + ",activa='SI' ,CONTRATO='" + codigcontrato.getText() + "' where pon='" + serial.getText().replace(" ", "") + "'");
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set ponmac='" + serial.getText().replace(" ", "") + "' where IDCLIENTE='" + codigocontra.getText() + "'");
                q.UpdateModificar("update [BDAirnet].[dbo].[tvproductosdj] set  estado='REEMPLAZO',cliente='" + nombre.getText() + "',activa='NO' ,CONTRATO='" + "REEMPLAZO" + "' where pon='" + serie.replace(" ", "") + "'");
                q.notificaciones("Datos Actualizados Imprima el nuevo Contrato", "I");

                q.CerrarVentanas(event);

            }
        });
        plan.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.isEmpty()) {
                if (newText.length() > 3) {
                    ip.setText(q.NuevaIP(plan.getText(), false));
                }
            }
        });
        cambioont.setOnAction((event) -> {
            dire.setDisable(true);
            tipoins.setDisable(true);
            ip.setDisable(true);
            plan.setDisable(true);
            corte.setDisable(true);
            modificar.setDisable(true);
            serial.setDisable(false);
            realizarcambio.setDisable(false);
            serie = serial.getText().replace(" ", "");
            try {
                valoresdecampos();
            } catch (SQLException ex) {
                Logger.getLogger(VistaclientesController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        CambioContrato.setOnAction((event) -> {
            if (CambioContrato.isSelected()) {
                CREARALERTAS();
            }
        });
        cambiodomicion.setToggleGroup(group);
        migracionradio.setToggleGroup(group);
        cambioont.setToggleGroup(group);
        cabiodeplan.setToggleGroup(group);
        CambioContrato.setToggleGroup(group);
        cabiodeplan.setOnAction((event) -> {
            valormens.setDisable(false);
            plan.setDisable(false);
            serial.setDisable(false);
            guardar.setDisable(false);
            ip.setDisable(false);
            ip.setText(q.NuevaIP(plan.getText().trim(), false));
            try {
                valoresdecampos();
            } catch (SQLException ex) {
                Logger.getLogger(VistaclientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cambiodomicion.setOnAction((event) -> {
            //  migracionradio.setDisable(true);
            guardar.setDisable(false);
            dire.setDisable(false);
            tipoins.setDisable(false);
            serial.setDisable(false);
            ip.setDisable(false);
            plan.setDisable(false);
            corte.setDisable(true);
            modificar.setDisable(true);
            imprimircontrato.setDisable(true);
            try {
                valoresdecampos();
            } catch (SQLException ex) {
                Logger.getLogger(VistaclientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        migracionradio.setOnAction((event) -> {
            guardar.setDisable(false);
            dire.setDisable(false);
            tipoins.setDisable(false);
            serial.setDisable(false);
            ip.setDisable(false);
            plan.setDisable(false);
            corte.setDisable(true);
            modificar.setDisable(true);
            imprimircontrato.setDisable(true);

            try {
                valoresdecampos();
            } catch (SQLException ex) {
                Logger.getLogger(VistaclientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ip.setText(q.NuevaIP(plan.getText(), false));
            if (!serial.getText().trim().isEmpty() && serial.getText().length() >= 10) {
                serie = serial.getText().replace(" ", "").trim();
            }
        });

        corte.setOnAction((event) -> {
            if (corte.getText().equals("Activar Servicio")) {
                ControlServicio(true, false);
            } else {
                ControlServicio(true, true);
            }

        });
        receptarpago.setOnAction((event) -> {

            receptarpago();

        });

        imprimircontrato.setOnAction((event) -> {
            try {
                if (!serial.getText().replace(" ", "").isEmpty()) {
                    imprimircontrato();
                } else {
                    q.notificaciones("Verifique la serial del cliente para Continuar", "I");
                }

            } catch (IOException | SQLException ex) {
                q.notificaciones("Se produjo un error de nivel : " + ex.getMessage(), "");
            }
        });

        nombredecuent.setOnAction((event) -> {
            try {
                funcionCombosplane();
            } catch (SQLException ex) {
                Logger.getLogger(VistaclientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void llenar() throws SQLException {

        Clase_VistaCLientes vistacliente = new Clase_VistaCLientes("", Clase_VistaCLientes.Factorbusqueda.trim());
        vistacliente.CargarDatos();
        if (vistacliente.getIDCLIENTES() == null ? ("N") != null : !vistacliente.getIDCLIENTES().equals("N")) {
            nombredecuent.getItems().addAll(FXCollections.observableArrayList(vistacliente.getIDCLIENTES().split(";")));
            ci.setText(vistacliente.getCedula());
            nombre.setText(vistacliente.getAbonado());
            codigcontrato.setText(vistacliente.getCODIGOCONTRATO());
            codigocontra.setText(vistacliente.getIDCLIENTE());
            nombredecuent.setVisible(true);
        } else {
            ci.setText(vistacliente.getCedula());
            nombre.setText(vistacliente.getAbonado().trim());
            codigcontrato.setText(vistacliente.getCODIGOCONTRATO().trim());
            codigocontra.setText(vistacliente.getIDCLIENTE().trim());
            tele1.setText(vistacliente.getCELULAR1().trim());
            tele2.setText(vistacliente.getCELULAR2().trim());
            correo.setText(vistacliente.getEMAIL().trim());
            dire.setText(vistacliente.getDIRECCION().trim());
            tipoins.setText(vistacliente.getTIPOINSTALACION().trim());
            plan.setText(vistacliente.getPlancontratado().trim());
            comparti.setText(vistacliente.getComparticion());
            valorinst.setText(vistacliente.getVALORINTALACION().trim());
            valormens.setText(vistacliente.getVALORMENSUAL().trim());
            fechadeing.setText(vistacliente.getFECHAINSTALACION().trim());
            latitud.setText(vistacliente.getLat());
            longi.setText(vistacliente.getLon());
            ip.setText(vistacliente.getIp().trim());
            variableip = ip.getText();
            cable.setText(vistacliente.getMTSCABLE());
            onuid.setText(vistacliente.getONUID());
            serviport.setText(vistacliente.getPUERTOSERVICIO());
            serial.setText(vistacliente.getPonmac().trim());
            OLTPON.setText(vistacliente.getOLTPON());
            OLTTARJETA.setText(vistacliente.getOLTTARJETA());
            nodoprincipal.setText(vistacliente.getNodoprincipal());
            EstadoCliente.setSelected(vistacliente.isEstadoCliente());
            if (EstadoCliente.isSelected()) {
                EstadoCliente.setText("Estado Activo");
            } else {

                EstadoCliente.setText("Estado Inactivo");
                q.notificaciones("El cliente esta en modo Inactivo", "I");
            }
            vistacliente.Tasaefeciva = createWorker(vistacliente);
            new Thread(vistacliente.Tasaefeciva).start();
        }

        if (!q.VerificarCedula(ci.getText().trim())) {
            q.notificaciones("Verifique el Numero de Cedula es Incorrecto", "I");
        }
        ControlServicio(false, false);

        if (!ipvg.isValidIPV4(ip.getText().replace(" ", ""))) {
            q.notificaciones("Verifique la direccion IP", "I");
        }

    }
    VerificarIP ipvg = new VerificarIP();

    public void funcionCombosplane() throws SQLException {
        Clase_VistaCLientes vistacliente = new Clase_VistaCLientes("", nombredecuent.getSelectionModel().getSelectedItem());
        vistacliente.funcionCombosplane();
        ci.setText(vistacliente.getCedula());
        nombre.setText(vistacliente.getAbonado().trim());
        codigcontrato.setText(vistacliente.getCODIGOCONTRATO().trim());
        codigocontra.setText(vistacliente.getIDCLIENTE());
        tele1.setText(vistacliente.getCELULAR1().trim());
        tele2.setText(vistacliente.getCELULAR2().trim());
        correo.setText(vistacliente.getEMAIL().trim());
        dire.setText(vistacliente.getDIRECCION().trim());
        tipoins.setText(vistacliente.getTIPOINSTALACION().trim());
        plan.setText(vistacliente.getPlancontratado().trim());
        comparti.setText(vistacliente.getComparticion());
        valorinst.setText(vistacliente.getVALORINTALACION().trim());
        valormens.setText(vistacliente.getVALORMENSUAL().trim());
        fechadeing.setText(vistacliente.getFECHAINSTALACION().trim());
        latitud.setText(vistacliente.getLat());
        longi.setText(vistacliente.getLon());
        ip.setText(vistacliente.getIp().trim());
        variableip = ip.getText();
        cable.setText(vistacliente.getMTSCABLE());
        onuid.setText(vistacliente.getONUID());
        serviport.setText(vistacliente.getPUERTOSERVICIO());
        serial.setText(vistacliente.getPonmac().trim());
        OLTPON.setText(vistacliente.getOLTPON());
        OLTTARJETA.setText(vistacliente.getOLTTARJETA());
        nodoprincipal.setText(vistacliente.getNodoprincipal().replace("", ""));
        EstadoCliente.setSelected(vistacliente.isEstadoCliente());
        if (EstadoCliente.isSelected()) {
            EstadoCliente.setText("Estado Activo");
        } else {

            EstadoCliente.setText("Estado Inactivo");
            q.notificaciones("El cliente esta en modo Inactivo", "I");
        }
        ControlServicio(false, false);
        vistacliente.Tasaefeciva = createWorker(vistacliente);
        new Thread(vistacliente.Tasaefeciva).start();
        if (!q.VerificarCedula(ci.getText().trim())) {
            q.notificaciones("Verifique el Numero de Cedula es Incorrecto", "I");
        }
        if (!ipvg.isValidIPV4(ip.getText().replace(" ", ""))) {
            q.notificaciones("Verifique la direccion IP", "I");
        }

    }

    @FXML
    public void activar() {
        editdireccion.setVisible(true);
        editelefono1.setVisible(true);
        editelefo2.setVisible(true);
        edemail.setVisible(true);
        edmensual.setVisible(true);
        edip.setVisible(true);
        tele1.setDisable(false);
        tele2.setDisable(false);
        correo.setDisable(false);
        dire.setDisable(false);
        ip.setDisable(false);
        guardar.setDisable(false);
        corte.setDisable(true);
        valormens.setDisable(false);
        EdSP.setVisible(true);
        Eserial.setVisible(true);
        EONUID.setVisible(true);
        onuid.setDisable(false);
        serviport.setDisable(false);
        serial.setDisable(false);
        EstadoCliente.setDisable(false);
    }
    String variableip;

    @FXML
    public void guardar(ActionEvent actionEvent) {

        if (migracionradio.isSelected() || cambiodomicion.isSelected()) {
            if (!serial.getText().trim().replace(" ", "").isEmpty()) {
                serie = serial.getText().trim().replace(" ", "");
            }
            try {
                guardasdatosnuevocontrato();
                imprimircontrato.setDisable(false);
            } catch (SQLException ex) {
                Logger.getLogger(VistaclientesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cabiodeplan.isSelected()) {
            if (serial.getText().trim().replace(" ", "").isEmpty() || plan.getText().replace(" ", "").isEmpty()) {
                q.notificaciones("Campos Incompletos", "I");
                return;
            } else {
                q.Existencia(serial.getText(), nombre.getText(), codigcontrato.getText());
                int año = LocalDate.now().getYear();
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE]  SET [plancontratado] ='" + plan.getText().trim() + "',[ponmac] = '" + serial.getText().trim().replace(" ", "") + "' where IDENTIFICACION='" + ci.getText() + "' and IDCLIENTE='" + codigocontra.getText() + "'");
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCONTRATO]  set VALORMENSUAL='" + valormens.getText().trim() + "'where IDCLIENTE=" + codigocontra.getText());
                q.UpdateModificar("update  [BDAirnet].[dbo].[detallesfacxtura] set claveacceso='" + VariablesDeUso.nombreusuario + "', valortotal='" + valormens.getText() + "' ,valorunitario='" + valormens.getText() + "' where Estado=1 and detalle  like '%" + año + "%' and codigo='" + ci.getText() + "'");
                if (!ipvg.isValidIPV4(ip.getText().replace(" ", ""))) {
                    q.notificaciones("Verifique la direccion IP", "I");
                    return;
                } else {
                    if (!variableip.equals(ip.getText().trim())) {
                        q.NuevaIP(plan.getText(), true);
                        q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set ipv4='" + ip.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");

                    }
                }
            }

        } else {
            if (editdireccion.isSelected()) {
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set [DIRECCION]='" + dire.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
            }
            if (editelefono1.isSelected()) {
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set [CELULAR1]='" + tele1.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
            }
            if (editelefo2.isSelected()) {
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set [CELULAR2]='" + tele2.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
            }
            if (edemail.isSelected()) {
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set [EMAIL]='" + correo.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
            }
            if (edip.isSelected()) {
                if (!ipvg.isValidIPV4(ip.getText().replace(" ", ""))) {
                    q.notificaciones("Verifique la direccion IP", "I");
                    return;
                } else {
                    q.NuevaIP(plan.getText().trim(), true);
                    q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set ipv4='" + ip.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");

                }

            }
            if (edmensual.isSelected()) {
                int año = LocalDate.now().getYear();
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCONTRATO]  set VALORMENSUAL='" + valormens.getText().trim() + "'where IDCLIENTE=" + codigocontra.getText());
                q.UpdateModificar("update  [BDAirnet].[dbo].[detallesfacxtura] set claveacceso='" + VariablesDeUso.nombreusuario + "', valortotal='" + valormens.getText() + "' ,valorunitario='" + valormens.getText() + "' where Estado=1 and detalle  like '%" + año + "%' and codigo='" + ci.getText() + "'");
            }
            if (EdSP.isSelected()) {

                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set PUERTOSERVICIO='" + serviport.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
            }
            if (Eserial.isSelected()) {
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set ponmac='" + serial.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
            }
            if (EONUID.isSelected()) {
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set ONUID='" + onuid.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
            }
            if (EPON.isSelected()) {
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set OLTPON='" + OLTPON.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
            }
            if (ETAR.isSelected()) {
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set OLTTARJETA='" + OLTTARJETA.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
            }
            if (EOLT.isSelected()) {
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set nodoprincipal='" + nodoprincipal.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
            }
        }
        q.UpdateModificar("update  [BDAirnet].[dbo].[TBCLIENTE] SET  ESTADO =" + (EstadoCliente.isSelected() ? "1" : "0") + "   WHERE IDCLIENTE=" + codigocontra.getText());

        if (!EstadoCliente.isSelected()) {
            q.UpdateModificar("update  [BDAirnet].[dbo].[TBCLIENTE] SET  ESTADORETIRO =" + (retiradoChech.isSelected() ? "1" : "0")
                    + ",nodoprincipal='" + "" + "'"
                    + ",OLTTARJETA='" + "" + "'"
                    + ",OLTPON='" +"" + "'"
                    + ",ONUID='" +"" + "'"
                    + ",ponmac='" + "" + "'"
                    + ",ipv4='" + "" + "'"
                    + "   WHERE IDCLIENTE=" + codigocontra.getText());
        }
        q.notificaciones("Exito al Guardar Datos", "I");
        q.CerrarVentanas(actionEvent);
    }

    private void guardasdatosnuevocontrato() throws SQLException {
        q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE]  SET [DIRECCION] = '" + dire.getText().trim() + "' ,[plancontratado] ='" + plan.getText() + "',[ipv4] = '" + ip.getText() + "',[ponmac] = '" + serial.getText().replace(" ", "") + "',[fechainstalacion] = '" + fechadeing.getText() + "'" + "where IDENTIFICACION='" + ci.getText() + "' and IDCLIENTE='" + codigocontra.getText() + "'");
        q.UpdateModificar("update [BDAirnet].[dbo].[TBCONTRATO]  set TIPOINSTALACION='" + tipoins.getText().trim() + "'where IDCLIENTE=" + codigocontra.getText());
        String detalle = "", valor = "0";
        if (migracionradio.isSelected()) {
            detalle = "Migracion";
            if (tipoins.getText().contentEquals("Fibra Óptica")) {
                q.UpdateModificar("update [BDAirnet].[dbo].[tvproductosdj] set  estado='Activo',cliente='"
                        + nombre.getText()
                        + "',fechaactiva="
                        + "GETDATE()"
                        + ",activa='SI' ,CONTRATO='"
                        + codigcontrato.getText() + "' where pon='"
                        + serial.getText().replace(" ", "") + "'");
                q.NuevaIP(plan.getText(), true);
            }

            valor = "30";
        } else if (cambiodomicion.isSelected()) {
            detalle = "Cambio de Domicilio";
            valor = "30";
        }
        q.Facturar(nombre.getText(), valor.replace(" ", ""), detalle, 1, ci.getText().replace(" ", ""), "1", "E");
        q.notificaciones("Exito al Guardar Datos", "I");
        tele1.setDisable(true);
        tele2.setDisable(true);
        correo.setDisable(true);
        dire.setDisable(true);
        ip.setDisable(true);

    }

    private void valoresdecampos() throws SQLException {
        Clase_VistaCLientes vistacliente = new Clase_VistaCLientes("", Clase_VistaCLientes.Factorbusqueda);
        if (plan.getText().contains("100")) {
            vistacliente.valoresdecampos(serial, tipoins, plan, true);
        } else {
            vistacliente.valoresdecampos(serial, tipoins, plan, migracionradio.isSelected());
        }

    }

    private void receptarpago() {
        q.abrirRide(nombre.getText());
        // Clase_RecepciondePago objetoinicial = new Clase_RecepciondePago();
        // objetoinicial.ValoresIniciales(ci.getText(), nombre.getText());
    }
    File objetofile;

    private void imprimircontrato() throws IOException, SQLException {
        File veri = new File(q.properties.getProperty("RutaArchivos") + "\\Clientes\\" + nombre.getText().replace(" ", "") + "\\" + "Facturas");
        if (!veri.exists()) {
            veri.mkdirs();
        }
        q.Existencia(serial.getText(), nombre.getText(), codigcontrato.getText());
        q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE]  SET [ponmac] = '"
                + serial.getText().replace(" ", "")
                + "' where IDCLIENTE='" + codigocontra.getText() + "'");
        q.Contrato(codigocontra.getText(), codigcontrato.getText(),
                nombre.getText(),
                ci.getText(),
                tele1.getText(),
                plan.getText(),
                fechadeing.getText(),
                dire.getText());
        q.cargarvaloresPropiedades();

        objetofile = new File(q.properties.getProperty("RutaArchivos") + "\\Clientes\\" + nombre.getText().replace(" ", "") + "\\" + nombre.getText().replace(" ", "") + ".pdf");
        q.abriarchivos(objetofile.toString());
        q.notificaciones("Contrato Generado", "I");

    }

    private void ControlServicio(boolean datos, boolean CORTAR) {

        if (ip.getText() == (null)) {
        } else {
            Clase_VistaCLientes ob = new Clase_VistaCLientes(ip.getText().replace(" ", ""), nombre.getText());

            if (ip.getText().contains("172.16")) {
                try {
                    if (ob.ActivaServicioRE(datos)) {
                        ob.AsignacionServicio(corte, estadoservicio, "I");
                    } else {
                        ob.AsignacionServicio(corte, estadoservicio, "E");
                    }
                } catch (MikrotikApiException ex) {
                    Logger.getLogger(VistaclientesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (ip.getText().contains("172.")) {
                if (datos) {
                    if (CORTAR) {
                        ob.DesActivarServicioFO(datos, codigocontra.getText());
                        ob.AsignacionServicio(corte, estadoservicio, "I");
                    } else {
                        ob.ActivaServicioFO(datos, codigocontra.getText());
                        ob.AsignacionServicio(corte, estadoservicio, "E");
                    }
                } else {
                    String valor = "";
                    ResultSet estado = q.tablas("SELECT ESTADOSERVICIO  FROM [BDAirnet].[dbo].[TBCLIENTE] where IDCLIENTE=" + codigocontra.getText());
                    try {
                        while (estado.next()) {
                            valor = estado.getString("ESTADOSERVICIO");
                        }
                        if (valor.equals("0")) {
                            ob.AsignacionServicio(corte, estadoservicio, "I");
                        } else {
                            ob.AsignacionServicio(corte, estadoservicio, "E");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(VistaclientesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (ip.getText().contains("131.")) {
                ob.AsignacionServicio(corte, estadoservicio, "P");
            } else {
                ob.AsignacionServicio(corte, estadoservicio, "H");
            }
        }
    }

    public Task createWorker(Clase_VistaCLientes vistacliente) {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                if (vistacliente.Hartikectesactivos()) {
                    tikectsmodulo.setVisible(true);
                    titulotike.setVisible(true);
                    tikectsmodulo.setText(vistacliente.getTikectsmodulo());
                    q.notificaciones("El Cliente  tiene Activo un Ticket", "I");
                } else {
                    tikectsmodulo.setVisible(false);
                    titulotike.setVisible(false);
                }
                //   ControlServicio(false);
                vistacliente.FacturaCacelado();
                ultimopago.setText(vistacliente.getUltimopago());

                return true;
            }
        };
    }

    public void abrirNavegador() {
        OpenBrowser abri = new OpenBrowser(ip.getText());
        abri.abrirNavegadorPorDefecto();
    }

    public void CREARALERTAS() {
        // Create the custom 
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("MODULO ACTUALIZACION DE DATOS");
        dialog.setHeaderText("INGRESE LOS NUEVOS DATOS");

// Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
// Set the button types.
        ButtonType loginButtonType = new ButtonType("GUARDAR", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Nuevos Nombres");
        TextField Apellidos = new TextField();
        Apellidos.setPromptText("Nuevos Apellidos");
        TextField cedula = new TextField();
        cedula.setPromptText("Numero de Cedula");

        grid.add(new Label("Nuevos Nombres :"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Nuevos Apellidos :"), 0, 1);
        grid.add(Apellidos, 1, 1);
        grid.add(new Label("Nuevo N° de Cedula :"), 0, 2);
        grid.add(cedula, 1, 2);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), Apellidos.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            if (!username.getText().isEmpty() && !Apellidos.getText().isEmpty() && !cedula.getText().isEmpty() && q.VerificarCedula(cedula.getText().trim())) {
                String TipoCedula = ci.getText().trim().length() == 13 ? "RUC" : "Cédula";
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE]  set NOMBRES='" + username.getText().trim() + "'"
                        + ",APELLIDOS='" + Apellidos.getText().trim() + "'"
                        + ",TIPOIDENTIFICACION='" + TipoCedula + "'"
                        + ",IDENTIFICACION='" + cedula.getText().trim() + "' "
                        + "where IDCLIENTE='" + codigocontra.getText() + "'");
                q.UpdateModificar("update  [BDAirnet].[dbo].[detallesfacxtura] set claveacceso='" + VariablesDeUso.nombreusuario + "' "
                        + ",cliente='" + nombre.getText().trim() + "', "
                        + "codigo ='" + ci.getText().trim() + "' where Estado=1 and codigo='" + AntiguaCI + "'");
                q.Facturar(nombre.getText().trim(), "30.00", "Cambio de Contrato", 1, ci.getText().trim(), "1", "E");
                q.cargarvaloresPropiedades();
                File veri = new File(q.properties.getProperty("RutaArchivos") + "\\Clientes\\" + nombre.getText().replace(" ", "") + "\\" + "Facturas");
                if (!veri.exists()) {
                    if (veri.mkdirs()) {
                        q.notificaciones("Datos Actualizados Actualice la lista de Clientes e Imprima el Contrato", "I");

                    }
                }

            } else {
                q.notificaciones("Campos vacios o nulos no se realizo cambios , Verifique el N° de Cedula", "I");
            }

        });
    }

    public void GestionarLinea() {
        if (!datoslinea.getText().isEmpty()) {
            try {

                String a = datoslinea.getText().trim() + "-";
                a = a.replace(" ", "-");
                //  System.out.println(a);
                String[] parts = a.split("-");
                serviport.setText(parts[2]);
                // System.out.println(parts[2]);//serviport  serviport.
                //  System.out.println(parts[6]+"/");
                String tar = parts[6] + "/";
                String[] parts2 = tar.split("/");
                OLTTARJETA.setText(parts2[1]);
                // System.out.println(parts2[1]);//tarjeta OLTTARJETA.
                OLTPON.setText(parts2[2]);
                // System.out.println(parts2[2]);//pon OLTPON.
                onuid.setText(parts[8]);
                //System.out.println(parts[8]);// ounid onuid.

                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set PUERTOSERVICIO='" + serviport.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
                //q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set ponmac='" + serial.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set ONUID='" + onuid.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set OLTPON='" + OLTPON.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
                q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set OLTTARJETA='" + OLTTARJETA.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");
                q.notificaciones("Datos Actualizados Correctamente", "I");
            } catch (Exception e) {
                q.notificaciones("Se produjo un error " + e.getMessage(), "I");
            }
//   q.UpdateModificar("update [BDAirnet].[dbo].[TBCLIENTE] set nodoprincipal='" + nodoprincipal.getText().trim() + "'" + "where IDENTIFICACION='" + ci.getText() + "'and IDCLIENTE='" + codigocontra.getText() + "'");

        }

    }
}
