package ece155b.top.server;

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

import ece155b.doctor.data.Doctor;

public class TopServerToPatientRW {

    private SAXParserFactory saxParserFactory;
    private SAXParser saxParser;
    private StringWriter stringWriter;
    private XMLOutputFactory xmlOutputFactory;
    private XMLStreamWriter xmlStreamWriter;
	private ArrayList<Doctor> doctorList;
	private DoctorToTopServerRW doctorToTopServerRW;
	
	
	
	
	
	public TopServerToPatientRW()
	{
		saxParserFactory = SAXParserFactory.newInstance();
		doctorToTopServerRW = new DoctorToTopServerRW();
		doctorList = new ArrayList<>();
		
		try {
			saxParser = saxParserFactory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
   	 	xmlOutputFactory = XMLOutputFactory.newInstance();	
	}
	
	public  ArrayList<Doctor> read(String message)
	{
		doctorList.clear();
		
		try {
			
			DefaultHandler handler = new DefaultHandler() 
			{
				private String name = "";
				private String lastName = "";
				private int port = 0;
				private String subject = "";
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
					
					if(qName.equals("Doctor"))
						doctorList.add(new Doctor(name, lastName, port, subject));
					else if(qName.equals("port"))
						port = Integer.parseInt(str);
					else if(qName.equals("name"))
						name = str;
					else if(qName.equals("lastName"))
						lastName = str;	
					else if(qName.equals("subject"))
						subject = str;
				}
			};
			saxParser.parse(new InputSource(new StringReader(message)), handler);

		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		return doctorList;
	}

	public  String write(ArrayList<Doctor> doctors)
	{
		String xml = "";
		xml += "<Doctors>";
		
		for(Doctor doctor : doctors)
		{
			xml += "<Doctor>";
			xml += "<port>" + doctor.getPort() +"</port>";
			xml += "<name>" + doctor.getName() + "</name>";
			xml += "<lastName>" + doctor.getLastName() + "</lastName>";
			xml += "<subject>" + doctor.getSubject() + "</subject>";
			xml += "</Doctor>";
		}
		
		xml += "</Doctors>";
		
		return xml;	
	}

	
	
}
