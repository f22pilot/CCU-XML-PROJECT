package ece155b.doctor.comm;

import ece155b.common.Common;
import ece155b.common.Message;
import ece155b.doctor.DoctorApp;
import java.io.IOException;
import java.net.*;
import java.util.Vector;

public class ConnHandler extends Thread {
    private ServerSocket servers;
    Vector<ConnListener> handles;   // vector that keeps track of connected clients
    
    DoctorApp server;   // reference to main application 
                        // ** you need to access doctor information
    
    public ConnHandler(DoctorApp docApp, int PortNo) {
        try {
            server = docApp;
            servers = new ServerSocket(PortNo);
            handles = new Vector <ConnListener>();
            
            start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void run() {
        try {
            while(true) {
                System.out.println("Waiting connections...");
                Socket socket = servers.accept();
                System.out.println("Got one connection :) ");
                addUser(socket);
                System.out.println("Processed..");
            }
        } catch (Exception ex) {}
    }
    
    // add new client user
    /**
     * Here you might assign unique session id to client listener
     */
    public void addUser(Socket socket) {
        handles.addElement(new ConnListener(this, socket));
    }
    
    /*
     *  You need a find criteria. Might be patient name,
     *  something unique to that patient (like ssn). Or you might assign
     *  session_ids to each connecting client and keep this in
     *  ClientListener object. Think that session id as your
     *  search criteria
     **/
    public ConnListener findUser(String patientName) {
        return null;
    }
    
    // Find user and remove..
    /**
     * When client disconnects, or closes the connection
     *  the client listener will catch an exception.
     *  You can use that and inform the connHandler to
     *  remove respective client listener
     */
    public void removeUser(ConnListener client) {
        handles.remove(client);
    }
    
    /**
     * Send message to all clients,
     *  you might also write function to send specific client
     *  just go over the vector, find matching ClientListener object
     *
     *  You might need to add another data field to ClientListener
     *  so that you will distingush between different clients.
     */
    public void broadcast(Message m) {
        System.out.println("BCAST #"+handles.size());
        
        if(handles.size() == 0)
            System.out.println("No peer to broadcast");
        else for (int i = 0; i<handles.size(); i++) {
            ConnListener cl = (ConnListener) handles.elementAt(i);
            cl.sendMessage(m);
        }
    }
    /**
     * YOu will need to modify this method, include all the
     * cases you might have as message type.
     *
     * This method is synchronized for the following reasons:
     * 1. Two clients might send message at the same time, we want to process
     * one of the client and then process second. Otherwise, this might cause
     * data incosistencies
     *
     * When you make a method synchronized, only one execution of the
     * method exists, since all client handlers calls this method to
     * process received messages, we are sure, two message calls will
     * not be executed at the same time. One of them will be picked by the OS,
     * and other will be pending until executing thread finishes its job.
     *
     * This is important to understand, otherwise, you might end up
     * with inconsistent data.
     *
     * Wow.. so long :) take it serious.
     * /**/
    public synchronized void processMessage(String xml, ConnListener listener) {
        
        Message msg = new Message(xml);
        
        /*
            You will need to define message types,
            you might consider to have a Common.java class,
            which you keep all the constants that will be used
            by other java classes.
         
            Most time I do that way, and it is really helpful.
         
            For example: Message type will be used by both
            Doctor and Patient. Why not keep them in Common.java file.
         
            First parse the received message, which is in XML format.
            Having message properties, like type, etc.
         */
            if(msg.type.equals(Common.BROADCAST))
            {
//                server.append(msg.toString());
            }
            else if(msg.type.equals(Common.AUTHENTICATE_PATIENT))
            {
                // Access patients information, and see if patient exists
            }
            else if(msg.type.equals(Common.REQUEST_TIME))
            {
                // Action to take..
            }
            else
                System.out.println("Unknown message type");
    }
}