/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacturacionElectronica;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import javax.swing.JOptionPane;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;


import org.w3c.dom.NodeList;

public class Autorizacion {
    private static final String WSDL = "https://cel.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl";
   public String valor="";
    
//https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl
//https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl
    
    
    public void main(String clave) {
       
        String numero =clave;
        if( numero.equals("") ){
            System.err.println("Numero de autorización no válido....");
            return;
        }
        String params = "<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ec=\"http://ec.gob.sri.ws.autorizacion\">" +
            "    <x:Header/>" +
            "    <x:Body>" +
            "        <ec:autorizacionComprobante>" +
            "            <claveAccesoComprobante>"+numero+"</claveAccesoComprobante>" +
            "        </ec:autorizacionComprobante>" +
            "    </x:Body>" +
            "</x:Envelope>";
        
        HttpURLConnection conn = null;
        URL uriLogin;
        try {
            uriLogin = new URL(WSDL);
            conn = (HttpURLConnection) uriLogin.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");       
        //Sender POST
            DataOutputStream wr = new DataOutputStream ( conn.getOutputStream () );
            wr.writeBytes (params);
            wr.flush ();
            wr.close ();
            
            if (conn.getResponseCode() == conn.HTTP_OK) {
                StringBuilder responseStrBuilder = new StringBuilder();
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null) {
                    responseStrBuilder.append(inputStr);
                }
               // System.out.println("Respuesta: " + responseStrBuilder.toString());
                valor= responseStrBuilder.toString();
                String xml = responseStrBuilder.toString();
                
                MessageFactory factory;
                try {
                    factory = MessageFactory.newInstance();
                    SOAPMessage message = factory.createMessage( new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
                    SOAPBody body = message.getSOAPBody();
                  
                    NodeList list = body.getElementsByTagName("autorizaciones");
                     Writer out = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\ENVIADOS\\"+numero+".xml"), "UTF-8"));
                     Writer out1 = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\AUTORIZADOS\\"+numero+".xml"), "UTF-8"));
                    
      //  Writer out = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("\\\\S-AIRCONTROL\\ArchivosXmlPrueba\\SRI\\AUTORIZADOS\\"+numero+".xml"), "UTF-8")) ;
                    try {
                        out1.write(xml);
                        out.write( body.getElementsByTagName("autorizacion").item(0).getTextContent().replaceAll("\\p{Cntrl}", "\n").replaceAll("\t\r", "")  );
                    }finally{ 
                        out1.close();
                        out.close();
                    }
                        
                } catch (SOAPException ex) {
                    System.out.println(ex.getMessage());
                     JOptionPane.showMessageDialog(null,ex.getMessage() +"   \t"+valor);
                }
                
                
            }else{
                System.err.println("HTTP: " + conn.getResponseMessage());
                 JOptionPane.showMessageDialog(null,conn.getResponseMessage() +valor);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage() +"   \t"+valor);
        }
        
    }
    
}
