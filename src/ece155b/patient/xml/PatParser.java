package ece155b.patient.xml;

import ece155b.patient.data.Appointment;
import ece155b.patient.data.DoctorContact;
import ece155b.patient.data.Patient;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class PatParser extends DefaultHandler
{
    private StringBuffer accumulator = new StringBuffer();
    public Patient patient;
    private DoctorContact doctor;
    private Appointment appointment;
    
    /**
     * Parse patient information..
     *
     * Patient name, and lastname
     * Vector of fDoctor contact(s)
     * Vector of Patient appointment(s)
     */
    public PatParser(File file) 
    {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance(  );
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void characters(char[] buffer, int start, int length)
    {
        accumulator.append(buffer, start, length);
    }
    
    public void startElement(String uri,String lname, String name, Attributes attributes)
    {
        accumulator.setLength(0);
    }
    
    public void endElement(String uri,String lname,String name)
    {
        String value = accumulator.toString();
    }
    
}
