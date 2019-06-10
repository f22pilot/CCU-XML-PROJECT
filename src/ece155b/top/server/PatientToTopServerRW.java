package ece155b.top.server;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

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
import ece155b.patient.data.Patient;

public class PatientToTopServerRW extends DefaultHandler{
	private SAXParserFactory saxParserFactory;
    private SAXParser saxParser;
    private StringWriter stringWriter;
    private XMLOutputFactory xmlOutputFactory;
    private XMLStreamWriter xmlStreamWriter;
	private Patient patient;
	private int port;
	private String name;
	private String lastName;
	private String address;
	private String birth = "";
	private String phone = "";
	private String str;
	
	public PatientToTopServerRW()
	{
		saxParserFactory = SAXParserFactory.newInstance();
		
		try {
			saxParser = saxParserFactory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
   	 	xmlOutputFactory = XMLOutputFactory.newInstance();	
	}
	public Patient read(String message)
	{
		name = "";
		lastName = "";
		port = 0;
		address = "";
		birth = "";
		phone = "";
		str = "";
	
		try {
			saxParser.parse(new InputSource(new StringReader(message)), this);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		return patient;
	}

	public String write(Patient newPatient)
	{
		stringWriter = new StringWriter();
		String xml = "";
		
		try {
			xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(stringWriter);
			xmlStreamWriter.writeStartDocument();
			xmlStreamWriter.writeStartElement("Patient");
			
			
			xmlStreamWriter.writeStartElement("port");
			xmlStreamWriter.writeCharacters("" + newPatient.getPort());
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeStartElement("name");
			xmlStreamWriter.writeCharacters(newPatient.getName());
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeStartElement("lastName");
			xmlStreamWriter.writeCharacters(newPatient.getLastName());
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeStartElement("address");
			xmlStreamWriter.writeCharacters(newPatient.getAddress());
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeStartElement("birth");
			xmlStreamWriter.writeCharacters(newPatient.getBirth());
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeStartElement("phone");
			xmlStreamWriter.writeCharacters(newPatient.getPhone());
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeEndElement();
			xmlStreamWriter.writeEndDocument();	
			
			xml = stringWriter.getBuffer().toString();
			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return xml;	
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
		
		if(qName.equals("Patient"))
			patient = new Patient();
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
	
}
