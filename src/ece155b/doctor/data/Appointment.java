/*
 * Appointment.java
 */

package ece155b.doctor.data;

import java.util.Date;

public class Appointment {

	private String date;
	private String docName;
	private String patientName;
	private String note;
	private String prescription;
	private String med;
	private String presDate;
	private String expirDate;
	
	/** Creates a new instance of Appointment */
	public Appointment() {
	}
	
	

	public Appointment(String date, String docName, String patientName, String prescription, String med, String presDate,
			String expirDate) {
		this.date = date;
		this.docName = docName;
		this.patientName = patientName;
		this.prescription = prescription;
		this.med = med;
		this.presDate = presDate;
		this.expirDate = expirDate;
	}



	public String toXml() {
		String xml = "";

		xml += "<Appointment>";
		xml += "<Date>" + date + "</Date>";
		xml += "<DoctorName>" + docName + "</DoctorName>";
		xml += "<PatientName>" + patientName + "<PatientName>"; 
		xml += "<Notes>" + note + "</Notes>";
		xml += "<prescription>" + prescription + "</prescription>";
		xml += "<medicines>" + med + "</medicines>";
		xml += "<prescriptionDate>" + presDate + "</prescriptionDate>";
		xml += "<ExpirationDate>" + expirDate + "</ExpirationDate>";
		
		xml += "</Appointment>";

		return xml;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getDocName() {
		return docName;
	}

	


	public String getPatientName() {
		return patientName;
	}



	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}



	public void setDocName(String docName) {
		this.docName = docName;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public String getPrescription() {
		return prescription;
	}



	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}



	public String getMed() {
		return med;
	}



	public void setMed(String med) {
		this.med = med;
	}



	public String getPresDate() {
		return presDate;
	}



	public void setPresDate(String presDate) {
		this.presDate = presDate;
	}



	public String getExpirDate() {
		return expirDate;
	}



	public void setExpirDate(String expirDate) {
		this.expirDate = expirDate;
	}
	
	
}
