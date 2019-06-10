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

public class DoctorToTopServerRW extends DefaultHandler{

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
	
	
	public DoctorToTopServerRW()
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

	public String write(Doctor newDoctor)
	{
		stringWriter = new StringWriter();
		String xml = "";
		
		try {
			xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(stringWriter);
			xmlStreamWriter.writeStartDocument();
			xmlStreamWriter.writeStartElement("Doctor");
			
			
			xmlStreamWriter.writeStartElement("port");
			xmlStreamWriter.writeCharacters("" + newDoctor.getPort());
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeStartElement("name");
			xmlStreamWriter.writeCharacters(newDoctor.getName());
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeStartElement("lastName");
			xmlStreamWriter.writeCharacters(newDoctor.getLastName());
			xmlStreamWriter.writeEndElement();
			
			xmlStreamWriter.writeStartElement("subject");
			xmlStreamWriter.writeCharacters(newDoctor.getSubject());
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
		
		if(qName.equals("Doctor"))
			doctor = new Doctor(name, lastName, port, subject);
		else if(qName.equals("port"))
			port = Integer.parseInt(str);
		else if(qName.equals("name"))
			name = str;
		else if(qName.equals("lastName"))
			lastName = str;	
		else if(qName.equals("subject"))
			subject = str;
	}
	
	
}
