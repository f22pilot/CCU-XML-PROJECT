package ece155b.top.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ece155b.doctor.data.Doctor;
import ece155b.patient.data.Patient;

public class TopServer implements Runnable{

	private ServerSocket 	  serverSocket   		= null;
	private static ArrayList<Doctor> doctorList     		= null;
	private static ArrayList<Patient> patientList     		= null;
	private static ArrayList<PatientRunnable> patients  	= null;
	private int 			  portForDoctors 		= 6125;
	private DoctorToTopServerRW doctorToTopServerRW;
	private PatientToTopServerRW patientToTopServerRW;
    public BufferedWriter bwrite;
    public BufferedReader bread;


	public TopServer()
	{
		doctorList = new ArrayList<>();
		doctorToTopServerRW = new DoctorToTopServerRW();
		patientList = new ArrayList<>();
		patientToTopServerRW = new PatientToTopServerRW();
	}
	
	@Override
	public void run() {	
		
		try {
			serverSocket = new ServerSocket(portForDoctors);
			
			while(true)
			{
				Socket doctorSocket = serverSocket.accept();
				
				bwrite = new BufferedWriter(new OutputStreamWriter(doctorSocket.getOutputStream(), "UTF-8"));
		        bread = new BufferedReader(new InputStreamReader(doctorSocket.getInputStream(), "UTF-8"));
		        
		        new Thread()
		        {
		        	public void run()
		        	{
						while (true) {
							try {
								String message = bread.readLine();
								if (message != null) {
									receiveDoctor(message);
//									System.out.println(message);
//									doctorSocket.close();
									showDoctor();
//									break;
								}
							} catch (IOException e) {
								e.printStackTrace();
							}

						}
		 		       
		        	}
		        }.start();
		      
				
				System.out.println("Register Successful!");
				
				// trigger function broadcast() to all patients
				this.broadcast();
				
			}
	
		} catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	private void receiveDoctor(String message)
	{
		doctorList.add(doctorToTopServerRW.read(message));
		this.broadcast();
//		System.out.println(message);
	}

	private void broadcast()
	{
		if(patients != null)
			for(PatientRunnable patient : patients)
				patient.sendMessage(doctorList);
	}
	
//	public static void addPatientRunnable(Socket socket)
//	{
//		patients.add(new PatientRunnable(socket));
//	}
	
	public void showDoctor()
	{
		System.out.println(doctorList.get(doctorList.size() - 1).getName());
	}
	
	public static void main(String[] args)
	{
		new Thread(new TopServer()).start();
		try {
			// the server use to connect patients
			ServerSocket patientServer = new ServerSocket(6120);
			patients = new ArrayList<>();
			while(true)
			{
				Socket socket = patientServer.accept();
				
				if(socket != null)
				{
					patients.add(new PatientRunnable(socket, doctorList));
				}
				
				for(PatientRunnable patient : patients)
				{
					patient.sendMessage(doctorList);
				}
			}

			
			
			
		} catch (IOException e) {
						e.printStackTrace();
		}
		
	}

	
}
