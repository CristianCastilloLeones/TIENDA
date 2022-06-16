/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

/**
 *
  * @author cl
 */
import CLASES.bas;
import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExport<T> {
bas q = new bas ();
    public void export(TableView<T> tableView,String Nombre){

        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        HSSFSheet hssfSheet=  hssfWorkbook.createSheet("Sheet1");
        HSSFRow firstRow= hssfSheet.createRow(0);

        ///set titles of columns
        for (int i=0; i<tableView.getColumns().size();i++){

            firstRow.createCell(i).setCellValue(tableView.getColumns().get(i).getText());

        }


        for (int row=0; row<tableView.getItems().size();row++){

            HSSFRow hssfRow= hssfSheet.createRow(row+1);

            for (int col=0; col<tableView.getColumns().size(); col++){

                Object celValue = tableView.getColumns().get(col).getCellObservableValue(row).getValue();

                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell(col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (  NumberFormatException e ){

                    hssfRow.createCell(col).setCellValue(celValue.toString());
                }

            }

        }

        //save excel file and close the workbook
        try {
            q.CargarPropiedades();
            hssfWorkbook.write(new FileOutputStream(q.properties.get("RutaArchivos")+"\\ArchivosdeImpresion\\"+Nombre+"_"+q.FechaformatoCalendaerio().replace("/", "-")+".xls"));
            hssfWorkbook.close();
            q.abriarchivos(q.properties.get("RutaArchivos")+"\\ArchivosdeImpresion\\"+Nombre+"_"+q.FechaformatoCalendaerio().replace("/", "-")+".xls");
        } catch (IOException e) {
            System.out.println( e.getMessage());
        }




    }

}