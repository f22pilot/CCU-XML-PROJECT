/*
 * Note.java
 *
 */

package ece155b.doctor.data;

public class Note {
    public String date;
    public String type;
    public String content;
    public String author;
    
    /** Creates a new instance of Note */
    public String toXML() {
        String xml="<Note>";
        xml += "</Note>";
        return xml;
    }
    
    public String toString()
    {
        return date+" : "+type;
    }
}
