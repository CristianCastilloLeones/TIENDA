/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;


public class Ticket{
static ArrayList<String> CabezaLineas=new ArrayList<String>();
static ArrayList<String> subCabezaLineas=new ArrayList<String>();
static ArrayList<String> items=new ArrayList<String>();
static ArrayList<String> totales=new ArrayList<String>();
static ArrayList<String> LineasPie=new ArrayList<String>();
static String cadena;
public static void AddCabecera(String line){
    CabezaLineas.add(line);}
public static void AddSubCabecera(String line){subCabezaLineas.add(line);}
public static void AddItem(String cantidad/*,String item,String price,String Importe*/){
OrderItem newItem = new OrderItem(' ');
items.add(newItem.GeneraItem(cantidad/*,item, price,Importe*/));
}
public static void AddTotal(String name,String price){
OrderTotal newTotal = new OrderTotal(' ');
totales.add(newTotal.GeneraTotal(name, price));
}
public static void AddPieLinea(String line){LineasPie.add(line);}
public static String DibujarLinea(int valor){
String raya="";for(int x=0;x<valor;x++){raya+="-";}return raya;
}
public static String DarEspacio(){return "\n";}
public static void ImprimirDocumento() throws IOException, PrintException{
    
cadena="";
for(int cabecera=0;cabecera<CabezaLineas.size();cabecera++ ){cadena+=CabezaLineas.get(cabecera);}
for(int subcabecera=0;subcabecera<subCabezaLineas.size();subcabecera++){cadena+=subCabezaLineas.get(subcabecera);}
for(int ITEM=0;ITEM<items.size();ITEM++){cadena+=items.get (ITEM);}
for(int total=0;total<totales.size();total++){cadena+=totales.get(total);}
for(int pie=0;pie<LineasPie.size();pie++){cadena+=LineasPie.get(pie);}
 
DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
PrintService service = PrintServiceLookup.lookupDefaultPrintService();
DocPrintJob pj = service.createPrintJob();



byte[]bytes =cadena.getBytes();

CrearArchivos(cadena);
Doc doc = new SimpleDoc(bytes, flavor,null);
File f = new File("C:\\a\\c.txt");
     FileWriter file = new FileWriter(f);
                BufferedWriter buffer = new BufferedWriter(file);

 try (PrintWriter ps = new PrintWriter(buffer)) {
             setFormato(1, ps);
             ps.println(cadena);
 
 cortar(ps);
             abrircajon(ps);
             ps.close();
 }
 try (FileInputStream inputStream = new FileInputStream("C:\\a\\c.txt")) {
             //Formato de Documento
             DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
             
             //Lectura de Documento
             Doc document = new SimpleDoc(inputStream, docFormat, null);
             String printerName = "LR2000";
             //Inclusion del nombre de impresora y sus atributos
             
             PrintService service1 = PrintServiceLookup.lookupDefaultPrintService();             
             System.out.println("Imprimiendo en : " + service.getName());
             DocPrintJob printJob = service1.createPrintJob();
             //Envio a la impresora
             printJob.print(document, null);}

//try{



//pj.print(doc,attributeSet);




CabezaLineas.clear();
subCabezaLineas.clear();
items.clear();
totales.clear();
LineasPie.clear();
//}catch(Exception e){ }
}

 private static void Dibuja_Linea(PrintWriter ps) {
        try {
            ps.println("----------------------------------------");
        } catch (Exception e) {
            System.out.print(e);
        }
    }
//para cortar el papel de mi ticketera
 private static void cortar(PrintWriter ps) {

        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'm'};
            ps.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

 private static void abrircajon(PrintWriter ps){
     try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'p',0x00,0x0F,0x96};
            ps.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
     
 }
    private static void correr(int fin, PrintWriter pw) {
        try {
            int i = 0;
            for (i = 1; i <= fin; i++) {
                pw.println("");
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private static void setFormato(double formato, PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, '!', (char) formato};
            pw.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

// para el color de mi ticketera
private static void setRojo(PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'r', 1};
            pw.write(ESC_CUT_PAPER);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

private void setNegro(PrintWriter pw) {
        try {
            char[] ESC_CUT_PAPER = new char[]{0x1B, 'r', 0};
            pw.write(ESC_CUT_PAPER);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

public static void CrearArchivos(String archivo){
    bas q =new bas();
    try {
          
          
            File file = new File("\\\\S-AIRCONTROL\\Nuevo Systema\\RecibosdePago\\"+q.Fechafacturas().replace(":", "_").replace("/", "_")+".txt");
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(archivo);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}


 

