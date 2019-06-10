package ece155b.doctor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Prescription extends JFrame{
	private JTextField textField;
	public Prescription() {
		getContentPane().setLayout(null);
		setResizable(false);
		setBounds(0, 0, 580, 630);
		JLabel lblPrescription = new JLabel("Prescription");
		lblPrescription.setFont(new Font("UD Digi Kyokasho NP-B", Font.PLAIN, 19));
		lblPrescription.setBounds(38, 36, 141, 65);
		getContentPane().add(lblPrescription);
		
		textField = new JTextField();
		textField.setBounds(60, 111, 449, 301);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("save");
		button.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		button.setBounds(60, 433, 119, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("cancel");
		button_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		button_1.setBounds(203, 434, 119, 23);
		getContentPane().add(button_1);
}
	}
