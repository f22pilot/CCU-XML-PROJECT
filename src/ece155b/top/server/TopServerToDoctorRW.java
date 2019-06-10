package ece155b.top.server;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ece155b.patient.data.Patient;

public class TopServerToDoctorRW {

	private SAXParserFactory saxParserFactory;
    private SAXParser saxParser;
    private StringWriter stringWriter;
    private XMLOutputFactory xmlOutputFactory;
    private XMLStreamWriter xmlStreamWriter;
	private ArrayList<Patient> patientList;
	private DoctorToTopServerRW patientToTopServerRW;
	
	public TopServerToDoctorRW()
	{
		saxParserFactory = SAXParserFactory.newInstance();
		patientToTopServerRW = new DoctorToTopServerRW();
		patientList = new ArrayList<>();
		
		try {
			saxParser = saxParserFactory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
   	 	xmlOutputFactory = XMLOutputFactory.newInstance();	
	}
	
	public  ArrayList<Patient> read(String message)
	{
		patientList.clear();
		
		try {
			
			DefaultHandler handler = new DefaultHandler() 
			{
				private String name = "";
				private String lastName = "";
				private int port = 0;
				private String address = "";
				private String birth = "";
				private String phone = "";
				private String str = "";
				
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					
				}

				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					str = new String(ch, start, length);
				}
				
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					
					if(qName.equals("Patient"))
						patientList.add(new Patient());
					else if(qName.equals("port"))
						port = Integer.parseInt(str);
					else if(qName.equals("name"))
						name = str;
					else if(qName.equals("lastName"))
						lastName = str;	
					else if(qName.equals("address"))
						address = str;
					else if(qName.equals("birth"))
						birth = str;
					else if(qName.equals("phone"))
						phone = str;
					
				}
			};
			saxParser.parse(new InputSource(new StringReader(message)), handler);

		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		return patientList;
	}

	public  String write(ArrayList<Patient> patients)
	{
		String xml = "";
		xml += "<Patients>";
		
		for(Patient patient : patients)
		{
			xml += "<Patient>";
			xml += "<port>" + patient.getPort() +"</port>";
			xml += "<name>" + patient.getName() + "</name>";
			xml += "<lastName>" + patient.getLastName() + "</lastName>";
			xml += "<address>" + patient.getAddress() + "</address>";
			xml += "<birth>" + patient.getBirth() + "</birth>";
			xml += "<phone>" + patient.getPhone() + "</phone>";
			xml += "</Patient>";
		}
		
		xml += "</Patient>";
		
		return xml;	
	}

	
	
}
