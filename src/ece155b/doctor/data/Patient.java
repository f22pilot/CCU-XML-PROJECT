
package ece155b.doctor.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

public class Patient {
//    public String ssn;
//    public String name;
//    public String surname;
//    public String birthdate;
//    public String address;
//    public String phone;
//    public String sex;
    public Socket socket;
    public BufferedWriter bwrite;
    public BufferedReader bread;
        
    public Patient(Socket socket)
    {
    	this.socket = socket;
		try {
			bwrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
	        bread = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    public String toXML() {
        String xml;
        xml ="<Patient>";
        
        return xml+"</Patient>";
    }
    
    public void sendMessage(String message)
    {
    	while(true)
    	{
	    	try {
				bwrite.write(message);
				bwrite.newLine();
		    	bwrite.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    
    	}
    }
    
}
