/*
 * DoctorContact.java
 *
 */

package ece155b.patient.data;

public class DoctorContact {
    public String URL;
    public int PORT;
    public String Name;
    
    /** Creates a new instance of DoctorContact */
    public DoctorContact() {
    }
    
    public String toXML()
    {
        return "<DoctorContact>"+
                "</DoctorContact>";
    }
}
