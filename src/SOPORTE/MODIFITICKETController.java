/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SOPORTE;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import CLASES.ClaseUtilidadesdeEmpleados;
import javafxapplication4.VariablesDeUso;
import CLASES.bas;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
  * @author cl
 */
public class MODIFITICKETController implements Initializable {

    
    bas q = new bas();
   ClaseUtilidadesdeEmpleados objUtilidade;
    String detalletabla = "";
    String comentario = "";
    File origen;
    File destino;
    File imgFile;
    Image image1;
    @FXML
    private Label cdt;
    @FXML
    private Label cient;
    @FXML
    private Label detalle;
    @FXML
    private TextArea respuest;
    @FXML
    private Button adjuntar;
    @FXML
    private Button respon;
    @FXML
    private Button solores;
    @FXML
    private ImageView evidencia;
    @FXML
    private Button actualizartecnico;
    @FXML
    private CheckBox habilitartecnico;
    @FXML
    private TextField tecnoccambiante;

    public void general() {
        cdt.setText(VariablesDeUso.val);

        ResultSet fg = q.tablas("SELECT EvidenciaImagen,[numero],[departamento],[usuario],[asunto],[tecnico],[fecha],[direccion],[abiertopor],[fechatrabajo][codigo] ,[Detalleadicional],[evidencia] ,[fechainicio],[estado],[materialesusados],[comentario],[respuesta] FROM [BDAirnet].[dbo].[tickets] where numero = '" + cdt.getText() + "'");
        try {
            while (fg.next()) {
                cient.setText("Cliente : " + fg.getString("usuario").trim());
                detalle.setText("Asunto: \t " + fg.getString("asunto") + "\n Comentario del Tecnico: " + fg.getString("comentario") + "\n Detalle: \n" + fg.getString("Detalleadicional"));
                detalletabla = fg.getString("Detalleadicional");
                comentario = fg.getString("comentario");
                cargarImagen(fg.getBlob("EvidenciaImagen"));
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MODIFITICKETController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        
    }
public void cargarImagen(Blob blob) throws IOException, SQLException{
    if(blob==null){
        q.notificaciones("No Hay Evidencia", "I");
    }else {
      BufferedImage foto = javax.imageio.ImageIO.read(blob.getBinaryStream());
      q.CargarPropiedades();
      ImageIO.write(foto, "png", new File(q.properties.get("RutaArchivos")+"\\evidencia\\" +cdt.getText()+"EVI.png")); // Write the Buffered Image into an output file
      image1 = new Image("file:" + q.properties.get("RutaArchivos")+"\\evidencia\\" +cdt.getText()+"EVI.png");
      evidencia.setImage(image1);
    }
   
   
    
}
    @FXML
    public void guardarycerrar(ActionEvent actionEvent) throws IOException {
        String actual = "";
        java.util.Date fecha1 = new Date();
        if (respuest.getText().isEmpty()) {
            q.notificaciones("Campos vacios", "");
        } else {
            if (imgFile != null) {
                origen = new File(imgFile.getAbsolutePath());
                q.CargarPropiedades();
               
                destino = new File(q.properties.get("RutaArchivos")+"\\evidencia\\" + imgFile.getName());
                Files.copy(Paths.get(origen.getAbsolutePath()), Paths.get(destino.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

                actual = ("UPDATE  [BDAirnet].[dbo].[tickets] set Detalleadicional = '" + detalletabla + "\n" + respuest.getText() + "',respuesta = 'Contestada',estado='Cerrada',comentario = 'Ultima Modificacion :" + fecha1.toGMTString() + " - " + comentario + "', evidencia = '" + String.valueOf(destino) + "' where numero = '" + cdt.getText() + "'");
            } else {
                actual = ("UPDATE  [BDAirnet].[dbo].[tickets] set Detalleadicional = '" + detalletabla + "\n" + respuest.getText() + "',respuesta = 'Contestada',estado='Cerrada',comentario ='Ultima Modificacion :" + fecha1.toGMTString() + " - " + comentario + "', evidencia = '" + "No subio Evidencia" + "' where numero = '" + cdt.getText() + "'");
            }

            try {

                q.UpdateModificar(actual);
                q.notificaciones("Datos actualizados Correctamente", "I");
                q.CerrarVentanas(actionEvent);
            } catch (Exception e) {
                q.notificaciones("Se produjo un error de nivel :" + e.getMessage(), "");
            }
        }

    }

    @FXML
    public void guardar(ActionEvent actionEvent) throws IOException {
        java.util.Date fecha1 = new Date();
        String actual ;
        if (respuest.getText().isEmpty()) {
            q.notificaciones("Campos vacios", "");
        } else {
            if (imgFile != null) {
                origen = new File(imgFile.getAbsolutePath());
                q.CargarPropiedades();
               destino = new File(q.properties.get("RutaArchivos")+"\\evidencia\\" + imgFile.getName());
                Files.copy(Paths.get(origen.getAbsolutePath()), Paths.get(destino.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

                actual = ("UPDATE  [BDAirnet].[dbo].[tickets] set Detalleadicional = '" + detalletabla + "\n" + respuest.getText() + "',respuesta = 'Contestada',estado='Espera',comentario ='" + respuest.getText() + " Ultima Modificacion :" + fecha1.toGMTString() + "', evidencia = '" + String.valueOf(destino) + "' where numero = '" + cdt.getText() + "'");
            } else {
                actual = ("UPDATE  [BDAirnet].[dbo].[tickets] set Detalleadicional = '" + detalletabla + "\n" + respuest.getText() + "',respuesta = 'Contestada',estado='Espera',comentario ='" + respuest.getText() + "Ultima Modificacion :" + fecha1.toGMTString() + "', evidencia = '" + "No hay Evidencia" + "' where numero = '" + cdt.getText() + "'");

            }

            try {

                q.UpdateModificar(actual);
                q.notificaciones("Datos actualizados Correctamente", "I");
                q.CerrarVentanas(actionEvent);
            } catch (Exception e) {
                q.notificaciones("Se produjo un error de nivel :" + e.getMessage(), "");
            }
        }

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (VariablesDeUso.tipouuario.contains("Secretario") || VariablesDeUso.tipouuario.contains("Tecnico")) {
            respon.setDisable(true);
            solores.setDisable(true);
        } else {
            respon.setDisable(false);
            solores.setDisable(false);
        }

        try {
            // TODO
            nuevotec();
            general();
        } catch (SQLException ex) {
            Logger.getLogger(MODIFITICKETController.class.getName()).log(Level.SEVERE, null, ex);
        }

        adjuntar.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");
            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );

            // Obtener la imagen seleccionada
            imgFile = fileChooser.showOpenDialog(null);
            System.out.println(imgFile.getAbsolutePath());
            // Mostar la imagen
            if (imgFile != null) {
                image1 = new Image("file:" + imgFile.getAbsolutePath());
                evidencia.setImage(image1);
            }
        });
        habilitartecnico.setOnMouseClicked((event) -> {
            if (habilitartecnico.isSelected()) {
                tecnoccambiante.setDisable(false);
                actualizartecnico.setDisable(false);
            } else {
                tecnoccambiante.setDisable(true);
                actualizartecnico.setDisable(true);
            }
        });
        actualizartecnico.setOnAction((event) -> {
            q.UpdateModificar("update [BDAirnet].[dbo].[tickets] set tecnico='" + tecnoccambiante.getText() + "' where numero = '" + cdt.getText().replace(" ", "") + "'");
            q.notificaciones("Datos actualizados Correctamente", "I");
           q.CerrarVentanas(event);
        });

    }

    public void nuevotec() throws SQLException {
         objUtilidade = new ClaseUtilidadesdeEmpleados();
        objUtilidade.PredictorListaEmpleados(tecnoccambiante);
      
    }
 
   
}
