/*
 * Appointment.java
 *
 */

package ece155b.patient.data;

public class Appointment {
    
    String doctorName;
    String dateTime;
    
    /** Creates a new instance of Appointment */
    public Appointment() {
    }
    
    public String toXML()
    {
        return "<Appointment>"+
                "</Appointment>";
    }
    
}
