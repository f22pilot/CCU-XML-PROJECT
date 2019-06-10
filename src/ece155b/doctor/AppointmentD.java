package ece155b.doctor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppointmentD extends JFrame{
	private JTextField note_textField;
public AppointmentD() {
	setResizable(false);
	setBounds(0, 0, 580, 630);
	getContentPane().setLayout(null);
	
	JLabel lblAppointment = new JLabel("Appointment");
	lblAppointment.setFont(new Font("UD Digi Kyokasho NP-B", Font.PLAIN, 19));
	lblAppointment.setBounds(48, 35, 141, 65);
	getContentPane().add(lblAppointment);
	
	JLabel lblDate = new JLabel("Date :");
	lblDate.setFont(new Font("UD Digi Kyokasho NP-R", Font.PLAIN, 16));
	lblDate.setBounds(58, 110, 79, 25);
	getContentPane().add(lblDate);
	
	JLabel lblDoctorName = new JLabel("Patient:");
	lblDoctorName.setFont(new Font("UD Digi Kyokasho NP-R", Font.PLAIN, 16));
	lblDoctorName.setBounds(58, 145, 79, 25);
	getContentPane().add(lblDoctorName);
	
	JLabel lblNote = new JLabel("Note :");
	lblNote.setFont(new Font("UD Digi Kyokasho NP-R", Font.PLAIN, 16));
	lblNote.setBounds(58, 180, 63, 25);
	getContentPane().add(lblNote);
	
	note_textField = new JTextField();
	note_textField.setBounds(142, 183, 264, 132);
	getContentPane().add(note_textField);
	note_textField.setColumns(10);
	
	JLabel lblPrescription = new JLabel("Prescription :");
	lblPrescription.setFont(new Font("UD Digi Kyokasho NP-R", Font.PLAIN, 16));
	lblPrescription.setBounds(58, 329, 107, 25);
	getContentPane().add(lblPrescription);
	
	JList prescriptionList = new JList();
	prescriptionList.setBounds(178, 334, 228, 166);
	getContentPane().add(prescriptionList);
	
	JButton btnAdd = new JButton("add");
	btnAdd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	btnAdd.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	btnAdd.setBounds(178, 510, 107, 23);
	getContentPane().add(btnAdd);
	
	JButton button_1 = new JButton("delete");
	button_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	button_1.setBounds(299, 510, 107, 23);
	getContentPane().add(button_1);
	
	JButton button_2 = new JButton("save");
	button_2.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	button_2.setBounds(411, 112, 119, 23);
	getContentPane().add(button_2);
	
	JButton button_3 = new JButton("cancel");
	button_3.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	button_3.setBounds(411, 147, 119, 23);
	getContentPane().add(button_3);
	
	JButton button = new JButton("enter");
	button.setFont(new Font("Dialog", Font.PLAIN, 16));
	button.setBounds(418, 396, 107, 23);
	getContentPane().add(button);
	
	JLabel label_date = new JLabel("");
	label_date.setBounds(163, 115, 134, 16);
	getContentPane().add(label_date);
	
	JLabel label_patient = new JLabel("");
	label_patient.setBounds(163, 150, 134, 16);
	getContentPane().add(label_patient);
	
}
}
