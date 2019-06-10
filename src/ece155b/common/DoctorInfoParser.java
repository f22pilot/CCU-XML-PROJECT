package ece155b.common;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ece155b.doctor.data.Appointment;
import ece155b.doctor.data.Doctor;

public class DoctorInfoParser extends DefaultHandler{

    private SAXParserFactory saxParserFactory;
    private SAXParser saxParser;
    private StringWriter stringWriter;
    private XMLOutputFactory xmlOutputFactory;
    private XMLStreamWriter xmlStreamWriter;
	private Doctor doctor;
	private int port;
	private String name;
	private String lastName;
	private String subject;
	private String str;
	private ArrayList<Appointment> appointments;
	
	private String date;
	private String docName;
	private String patientName;
	private String note;
	private String prescription;
	private String med;
	private String presDate;
	private String expirDate;
	
	
	public DoctorInfoParser()
	{
		saxParserFactory = SAXParserFactory.newInstance();
		
		try {
			saxParser = saxParserFactory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
   	 	xmlOutputFactory = XMLOutputFactory.newInstance();	
	}
	public Doctor read(String message)
	{
		name = "";
		lastName = "";
		port = 0;
		subject = "";
		str = "";
	
		try {
			saxParser.parse(new InputSource(new StringReader(message)), this);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		return doctor;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		str = new String(ch, start, length);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
		if(qName.equals("Doctor"))
			doctor = new Doctor(name, lastName, port, subject);
		else if(qName.equals("name"))
			name = str;
		else if(qName.equals("lastName"))
			lastName = str;	
		else if(qName.equals(subject))
			subject = str;
		else if(qName.equals("Appointment")) // Appointment(String date, String docName, String prescription, String med, String presDate,String expirDate)
			appointments.add(new Appointment(date, docName, patientName, prescription, med, presDate, expirDate));
		else if(qName.equals("Notes"))
			note = str;
		else if(qName.equals("prescription"))
			prescription = str;
		else if(qName.equals("medicines"))
			med = str;
		else if(qName.equals("prescriptionDate"))
			presDate = str;
		else if(qName.equals("ExpirationDate"))
			expirDate = str;
		else if(qName.equals("DoctorName"))
			docName = str;
		else if(qName.equals("PatientName"))
			patientName = str;
	}
	
	
}
