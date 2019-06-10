
package ece155b.patient.data;

import java.util.Vector;

public class Patient {
    public String name;
    public String lastname;
    
    Vector <Appointment> appointments;
    Vector <DoctorContact> doctors;
    
    public Patient() {
        
    }
    
    public String toXML() {
        String xml="<Patient>";
        // other info
        for (int i = 0; i < doctors.size(); i++)
            xml += doctors.elementAt(i).toXML();
        
        for (int i = 0; i < appointments.size(); i++)
            xml += appointments.elementAt(i).toXML();
        
        return xml+"</Patient>";
    }

	public String getPort() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBirth() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPhone() {
		// TODO Auto-generated method stub
		return null;
	}
}
