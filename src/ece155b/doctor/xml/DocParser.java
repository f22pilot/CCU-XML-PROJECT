package ece155b.doctor.xml;

import ece155b.doctor.data.Appointment;
import ece155b.doctor.data.Doctor;
import ece155b.doctor.data.Note;
import ece155b.doctor.data.Patient;

import java.io.File;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class DocParser extends DefaultHandler
{
    private StringBuffer accumulator = new StringBuffer();
    
    public Doctor doctor;
    private Patient patient;
    private Appointment appointment;
    private Note note;
    
    public DocParser(File file) 
    {
        
    }
    
    public void characters(char[] buffer, int start, int length)
    {
        accumulator.append(buffer, start, length);
    }
    
    public void startElement(String uri,String lname, String name, Attributes attributes)
    {
        
    }
    
    public void endElement(String uri,String lname,String name)
    {
        String value = accumulator.toString();
        
    }
    
}
