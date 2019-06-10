package ece155b.doctor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class PatientD extends JFrame  {
public PatientD() {
	setResizable(false);
	setBounds(0, 0, 580, 630);
	
	getContentPane().setLayout(null);
	JLabel lblPatient = new JLabel("Patient");
	lblPatient.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 20));
	lblPatient.setBounds(29, 25, 88, 47);
	getContentPane().add(lblPatient);
	
	JLabel label = new JLabel("First Name :");
	label.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	label.setBounds(39, 76, 119, 37);
	getContentPane().add(label);
	
	JLabel label_1 = new JLabel("Last Name :");
	label_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	label_1.setBounds(39, 107, 119, 37);
	getContentPane().add(label_1);
	
	JList list = new JList();
	list.setBounds(104, 265, 359, 117);
	getContentPane().add(list);
	
	JLabel lblSex = new JLabel("Address :");
	lblSex.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	lblSex.setBounds(39, 138, 119, 37);
	getContentPane().add(lblSex);
	
	JLabel label_2 = new JLabel("Appointment List :");
	label_2.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	label_2.setBounds(39, 235, 119, 23);
	getContentPane().add(label_2);
	
	JButton button = new JButton("enter");
	button.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	button.setBounds(104, 397, 107, 23);
	getContentPane().add(button);
	
	JButton button_2 = new JButton("delete");
	button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	button_2.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	button_2.setBounds(221, 397, 107, 23);
	getContentPane().add(button_2);
	
	JButton button_4 = new JButton("save");
	button_4.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	button_4.setBounds(320, 84, 119, 23);
	getContentPane().add(button_4);
	
	JButton button_5 = new JButton("cancel");
	button_5.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	button_5.setBounds(320, 124, 119, 23);
	getContentPane().add(button_5);
	
	JLabel lblBirthDate = new JLabel("Birth Date :");
	lblBirthDate.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	lblBirthDate.setBounds(39, 166, 119, 37);
	getContentPane().add(lblBirthDate);
	
	JLabel lblPhone = new JLabel("Phone :");
	lblPhone.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
	lblPhone.setBounds(39, 198, 82, 37);
	getContentPane().add(lblPhone);
	
	JLabel label_name = new JLabel("New label");
	label_name.setBounds(153, 88, 119, 15);
	getContentPane().add(label_name);
	
	JLabel label_lastName = new JLabel("New label");
	label_lastName.setBounds(153, 119, 107, 15);
	getContentPane().add(label_lastName);
	
	JLabel label_address = new JLabel("New label");
	label_address.setBounds(153, 150, 119, 15);
	getContentPane().add(label_address);
	
	JLabel label_birth = new JLabel("New label");
	label_birth.setBounds(153, 178, 119, 15);
	getContentPane().add(label_birth);
	
	JLabel label_phone = new JLabel("New label");
	label_phone.setBounds(153, 208, 88, 15);
	getContentPane().add(label_phone);
	
}


}

