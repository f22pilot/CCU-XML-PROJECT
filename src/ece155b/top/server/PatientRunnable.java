package ece155b.top.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ece155b.doctor.data.Doctor;

public class PatientRunnable implements Runnable{

	private Socket 	             socket   				= null;
	private DoctorToTopServerRW  doctorToTopServerRW;
	private TopServerToPatientRW topServerToPatientRW;
    public BufferedWriter bwrite;
    public BufferedReader bread;


	public PatientRunnable(Socket socket, ArrayList<Doctor> doctorList)
	{
		this.socket = socket;
		doctorToTopServerRW = new DoctorToTopServerRW();
		topServerToPatientRW = new TopServerToPatientRW();
		
		System.out.println("patient in");
		
		try {
			bwrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
	        bread = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
	        
	        while(true)
	        {
	        	bwrite.write(topServerToPatientRW.write(doctorList));
	        	bwrite.newLine();
	        	bwrite.flush();
	        	break;
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {	
		
		
	}
	
	public void sendMessage(ArrayList<Doctor> doctors)
	{
		try {
			bwrite.write(topServerToPatientRW.write(doctors));
			bwrite.flush();
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}
	
	public boolean receiveMessage()
	{
		try {
			if(bread.readLine() != null)
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
}
