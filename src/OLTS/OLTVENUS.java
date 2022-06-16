/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OLTS;

import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;

/**
 *
  * @author cl
 */
public class OLTVENUS {
 
      private TelnetClient telnet = new TelnetClient();
    private BufferedReader reader;
    private InputStream in;
    private PrintStream out;
    private String serverName;
    private String user;
    private String password;
    public static String promptComplete;

    private String OLTNAME;
    private String IPOLT;
    private String RUTABASEOLT;
    private int PUERTO;
    private String USUARIOOLT;
    private String PASSOLT;
    private Session session;

    public String getOLTNAME() {
        return OLTNAME;
    }

    public void setOLTNAME(String OLTNAME) {
        this.OLTNAME = OLTNAME;
    }

    public String getIPOLT() {
        return IPOLT;
    }

    public void setIPOLT(String IPOLT) {
        this.IPOLT = IPOLT;
    }

    public String getRUTABASEOLT() {
        return RUTABASEOLT;
    }

    public void setRUTABASEOLT(String RUTABASEOLT) {
        this.RUTABASEOLT = RUTABASEOLT;
    }

    public int getPUERTO() {
        return PUERTO;
    }

    public void setPUERTO(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    public String getUSUARIOOLT() {
        return USUARIOOLT;
    }

    public void setUSUARIOOLT(String USUARIOOLT) {
        this.USUARIOOLT = USUARIOOLT;
    }

    public String getPASSOLT() {
        return PASSOLT;
    }

    public void setPASSOLT(String PASSOLT) {
        this.PASSOLT = PASSOLT;
    }

    public OLTVENUS(String OLTNAME, String IPOLT, String RUTABASEOLT, int PUERTO, String USUARIOOLT, String PASSOLT) {
        this.OLTNAME = OLTNAME;
        this.IPOLT = IPOLT;
        this.RUTABASEOLT = RUTABASEOLT;
        this.PUERTO = PUERTO;
        this.USUARIOOLT = USUARIOOLT;
        this.PASSOLT = PASSOLT;
    }

    public void connect() {

        try {
            serverName = "10.1.60.3";
            user = "crisles";
            password = "0bilisam0-";
            //Esta cadena es el prompt, en este caso nuestro servidor
            //muestra el prompt de la siguiente forma
            //[servidor][miuser =>
            promptComplete = "[" + serverName + "][" + user + " =>";

            //Abro la conexión al telnet por el puerto 23
            telnet.connect(serverName, 23);
           
            //Ahora necesito una forma de leer las respuestas que
            //me envía el telnet, para esto obtenemos un InputStream
            //del objeto telnet
            in = telnet.getInputStream();

            //Ahora necesito una forma de enviarle los comandos al telnet
            //para esto obtengo un OutputStream desde el objeto telnet
            out = new PrintStream(telnet.getOutputStream());

            //Ahora envuelvo el InputStream dentro de un BufferedReader
            //para que la lectura de las respuestas del telnet sean mucho
            //mas sencillas y mejor gestionadas
            reader = new BufferedReader(new InputStreamReader(in));

            //Ahora leemos de la consola a través de nuestro método
            //readUntil el cual lee de la consola hasta que el último
            //caracter (un char) sea -1 y que el prompt sea igual
            //al patron que le enviamos como argumento, en este caso
            //es hasta que el prompt despliegue el patron login:
            readUntil("User name:");

            //cuando el readUntil de login finaliza, procedemos a ingresar el user
            //a través del método write, el cual escribe en la consola
            write(user);

            //esperamos hasta que el prompt muestre la palabra password:
            //La palabra tiene que ser exacta a la que sale en el prompt
            readUntil("User password:");

            //Ahora ingresamos el password
            write(password);
            
            

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public String readUntil(String pattern) {
        StringBuffer sb = new StringBuffer();

        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            boolean found = false;
            int check = in.read();
            char ch = (char) check;
            while (check != -1) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {

                        return sb.toString();
                    }
                }
                check = in.read();
                ch = (char) check;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void write(String value) {
        try {
            out.println(value);
            out.flush();
          

        } catch (Exception e) {
        }

    }
    
  public void Desconectat(){
          try {
              telnet.disconnect();
          } catch (IOException ex) {
              Logger.getLogger(OLTVENUS.class.getName()).log(Level.SEVERE, null, ex);
          }
  }
}
