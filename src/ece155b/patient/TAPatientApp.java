
package ece155b.patient;

import ece155b.common.Common;
import ece155b.common.Message;
import ece155b.patient.comm.ConnHandler;
import ece155b.patient.data.DoctorContact;
import ece155b.patient.data.Patient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TAPatientApp extends JFrame {
    public Patient             patient;
    private ConnHandler         handler;
    
    public static void main(String [] args) {
        new TAPatientApp();
    }
    
    public TAPatientApp() {
        handler = new ConnHandler(this);
        
        // you will read this information from XML file
        DoctorContact contact = new DoctorContact();
        contact.URL = "localhost";
        contact.PORT = 1111;
        contact.Name = "Yung-Ting Chuang"; //change to your name...
        handler.connectToDoctor(contact);
        
        GUI();
    }
    
    JTextArea texta;
    public void GUI() {
        /* GUI part of the application */
        texta = new JTextArea();
        JScrollPane scroll = new JScrollPane(texta);
        texta.setLineWrap(true);
        texta.setWrapStyleWord(true);
        
        JButton testme = new JButton("Send message");
        testme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Message m = new Message();
                m.type = Common.BROADCAST;
                m.content = "Test broadcast from Patient";
                m.from = "The Patient";
                
                handler.broadcast(m);
            }
        });
        
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(testme, BorderLayout.SOUTH);
        
        setTitle("Patient");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    
    public void append(String str) {
        texta.append("\n"+str);
    }
}
