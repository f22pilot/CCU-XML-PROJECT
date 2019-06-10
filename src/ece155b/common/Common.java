
package ece155b.common;

/**
 *  You might have Message class similar to XMLParser class,
 *  When you receive a message you will parse it and assign
 *  necesary values to Message object
 */
public class Common {
    public static String
            BROADCAST                   = "Broadcast",
            AUTHENTICATE_PATIENT        = "Authenticate",
            AUTHENTICATE_PATIENT_REPLY  = "AuthenticateR",
            REQUEST_TIME                = "RequestTime",
            REQUEST_TIME_REPLY          = "RequestTimeR",
            REQUEST_APPOINTMENT         = "RequestApp",
            REQUEST_APPOINTMENT_REPLY   = "RequestAppR",
            TERMINATE                   = "Terminate";
}