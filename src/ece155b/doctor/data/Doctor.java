package ece155b.doctor.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Doctor {
    
    public String name = "MyName";
    public String lastName = "MyLastName";
    private int port;
    private String subject;
    
    public File file;
    ArrayList<Appointment> appointments; 
    ArrayList<Patient> patients;
    
    public Doctor()
    {
    }
      
    public Doctor(String name, String lastName, int port, String subject) {
    	patients = new ArrayList<>();
    	appointments = new ArrayList<>();
		this.name = name;
		this.lastName = lastName;
		this.port = port;
		this.subject = subject;
	}

	public void addPatient(Patient patient)
    {
        patients.add(patient);
        patient.sendMessage(this.toXML());
    }
    
    public void save()
    {
        
    }
    
    public String toXML()
    {
    	String xml = "<Doctor>";
		xml += "<port>" + port +"</port>";
		xml += "<name>" + name + "</name>";
		xml += "<lastName>" + lastName + "</lastName>";
		xml += "<subject>" + subject + "</subject>";
		xml += "<Appointments>";
		for (Appointment app : appointments) {
			xml += app.toXml();
		}
		xml += "</Appointments>";
		xml += "</Doctor>";

		return xml;
    }
    
        
    public boolean loginCheck(String ssn)
    {
        // Check if patient exists
        return true;
    }
    
    public String requestTimeOnDate(Date d)
    {
        return null;
    }
    
    public void requestAppointment(Date datetime)
    {
        
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

    
}