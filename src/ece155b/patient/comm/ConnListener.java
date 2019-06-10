package ece155b.patient.comm;

import ece155b.common.Message;
import ece155b.doctor.data.Patient;
import ece155b.patient.data.DoctorContact;
import java.io.*;
import java.net.*;

class ConnListener extends Thread{
    public BufferedWriter bwrite;
    public BufferedReader bread;
    private ConnHandler PARENT;
    private DoctorContact contact;
    
    public Patient patient; // Once patient is authenticated, set this value
    
    ConnListener(ConnHandler p, Socket socket, DoctorContact doc) {
        PARENT 	= p;
        contact = doc;
        try 
        {            
            bwrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bread = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            start();
        } catch (Exception ex) {
            System.out.println("Error with socket creation!");
        }
    }
    
    public void run() {
        try {
            while(true) {
                System.out.println("waiting");
                String xml 	= (String) bread.readLine();
                if(xml == null) break;
                PARENT.processMessage(xml, this);
            }
        } catch(Exception e) {
            // This may happen because of the fact that client
            // application is closed.
            System.out.println("Error reading message!");
        }
        PARENT.removeDocConn(this);
        System.out.println("Socket closed!");
    }
    
    public void sendMessage(Message msg) {
        try {
            System.out.println("Sending to "+contact.Name+" \n"+msg);
            bwrite.write(msg.toXML());
            bwrite.newLine();
            bwrite.flush();
        } catch (Exception ex) {
            System.out.println("Error sending message");
        }
    }
    
    
}