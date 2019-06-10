package ece155b.patient.comm;

import ece155b.common.Common;
import ece155b.common.Message;
import ece155b.patient.TAPatientApp;
import ece155b.patient.data.DoctorContact;

import java.net.*;
import java.util.Vector;

public class ConnHandler {
    Vector<ConnListener> doctors;   // vector that keeps track of connected clients
    TAPatientApp pApp;
    
    public ConnHandler(TAPatientApp Appl) {
        doctors = new Vector <ConnListener>();
        pApp = Appl;
    }
    
    public void connectToDoctor(DoctorContact doc) {
        try {
            Socket socket = new Socket(doc.URL, doc.PORT);
            doctors.addElement(new ConnListener(this, socket, doc));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void removeDocConn(ConnListener cl) {
        doctors.remove(cl);
    }
    
    public void broadcast(Message m) {
        System.out.println("BCAST #"+doctors.size());
        
        if(doctors.size() == 0)
            System.out.println("No peer to broadcast");
        else for (int i = 0; i<doctors.size(); i++) {
            ConnListener cl = (ConnListener) doctors.elementAt(i);
            cl.sendMessage(m);
        }
    }
    
    protected synchronized void processMessage(String xml, ConnListener listener) {
        Message msg = new Message(xml);
        
        if(msg.type.equals(Common.BROADCAST)) {
            pApp.append(msg.toString());
        } else if(msg.type.equals(Common.AUTHENTICATE_PATIENT_REPLY)) {
            // Patient is logged in, can request time
        } else if(msg.type.equals(Common.REQUEST_TIME_REPLY)) {
            // Action to take..
        } else if(msg.type.equals(Common.REQUEST_APPOINTMENT_REPLY)) {
            // Action to take..
        } else
            System.out.println("Unknown message type");
    }
}