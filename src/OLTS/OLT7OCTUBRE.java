/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OLTS;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
  * @author cl
 */
public class OLT7OCTUBRE {
  
     Channel channel;
    StringBuilder outputBuffer = new StringBuilder();
    private  final String ENTER_KEY = "\n";

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

    public OLT7OCTUBRE(String OLTNAME, String IPOLT, String RUTABASEOLT, int PUERTO, String USUARIOOLT, String PASSOLT) {
        this.OLTNAME = OLTNAME;
        this.IPOLT = IPOLT;
        this.RUTABASEOLT = RUTABASEOLT;
        this.PUERTO = PUERTO;
        this.USUARIOOLT = USUARIOOLT;
        this.PASSOLT = PASSOLT;
    }

    public void connect()
            throws JSchException, IllegalAccessException {
      
           if (this.session == null || !this.session.isConnected()) {
            JSch jsch = new JSch();
            this.session = jsch.getSession(this.USUARIOOLT, this.IPOLT, this.PUERTO);
            this.session.setPassword(this.PASSOLT);
            // Parametro para no validar key de conexion.
            this.session.setConfig("StrictHostKeyChecking", "no");
            this.session.connect(5000);
             channel = session.openChannel("exec");
        } else {
            throw new IllegalAccessException("Sesion SSH ya iniciada.");
        } 
        
        
    }

    public final void disconnect() {
        this.session.disconnect();
    }
    ChannelExec channelExec;
   public final String executeCommand(String command)
        throws IllegalAccessException, JSchException, IOException {
        if (this.session != null && this.session.isConnected()) {
            // Abrimos un canal SSH. Es como abrir una consola.
             channelExec = (ChannelExec) this.session.openChannel("exec");
            InputStream in = channelExec.getInputStream();
            channelExec.setCommand(command);
            channelExec.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                builder.append(linea);
                builder.append(ENTER_KEY);
            }
            disconnect();
            return builder.toString();
        } else {
            throw new IllegalAccessException("No existe sesion SSH iniciada.");
        }
    }

   
  
   
  
}
