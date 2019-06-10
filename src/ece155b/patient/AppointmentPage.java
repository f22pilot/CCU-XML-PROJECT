package ece155b.patient;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AppointmentPage extends JFrame{
public AppointmentPage() {
getContentPane().setLayout(null);
setResizable(false);
setBounds(0, 0, 580, 630);
	JLabel lblAppointment = new JLabel("Appointment");
	lblAppointment.setFont(new Font("UD Digi Kyokasho NP-B", Font.PLAIN, 19));
	lblAppointment.setBounds(48, 35, 141, 65);
	getContentPane().add(lblAppointment);
	
	JLabel lblDate = new JLabel("Date :");
	lblDate.setFont(new Font("UD Digi Kyokasho NP-R", Font.PLAIN, 16));
	lblDate.setBounds(58, 145, 63, 25);
	getContentPane().add(lblDate);
	
	JLabel lblDoctorName = new JLabel("Doctor:");
	lblDoctorName.setFont(new Font("UD Digi Kyokasho NP-R", Font.PLAIN, 16));
	lblDoctorName.setBounds(58, 110, 118, 25);
	getContentPane().add(lblDoctorName);
	
	JLabel lblNote = new JLabel("Note :");
	lblNote.setFont(new Font("UD Digi Kyokasho NP-R", Font.PLAIN, 16));
	lblNote.setBounds(58, 258, 63, 25);
	getContentPane().add(lblNote);
	
	JLabel lblPrescription = new JLabel("Prescription :");
	lblPrescription.setFont(new Font("UD Digi Kyokasho NP-R", Font.PLAIN, 16));
	lblPrescription.setBounds(48, 361, 118, 25);
	getContentPane().add(lblPrescription);
	
	JButton btnSave = new JButton("save");
	btnSave.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	btnSave.setBounds(159, 468, 119, 23);
	getContentPane().add(btnSave);
	
	JButton brnCancel = new JButton("cancel");
	brnCancel.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	brnCancel.setBounds(222, 543, 119, 23);
	getContentPane().add(brnCancel);
	
	JLabel lblNewLabel_2 = new JLabel("New label");
	lblNewLabel_2.setBounds(178, 342, 163, 65);
	getContentPane().add(lblNewLabel_2);
	
	JLabel label_doctor = new JLabel("New label");
	label_doctor.setBounds(159, 112, 118, 16);
	getContentPane().add(label_doctor);
	
	JLabel label_date = new JLabel("New label");
	label_date.setBounds(159, 150, 103, 16);
	getContentPane().add(label_date);
	
	JLabel lblNewLabel = new JLabel("New label");
	lblNewLabel.setBounds(159, 263, 173, 58);
	getContentPane().add(lblNewLabel);
	
}
}
