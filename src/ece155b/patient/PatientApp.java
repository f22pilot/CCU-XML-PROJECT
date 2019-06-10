package ece155b.patient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import ece155b.doctor.data.Doctor;
import ece155b.patient.data.Patient;
import ece155b.top.server.TopServerToPatientRW;

public class PatientApp extends JFrame {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	private Patient patient;
	private String topServerIp = "127.0.0.1";
	private int topServerPort = 6120;

	private ArrayList<Doctor> doctors;
	private TopServerToPatientRW topServerToPatientRW;

	private Socket socket;
	public BufferedWriter bwrite;
	public BufferedReader bread;

	public static void main(String[] args) {
		new PatientApp();
	}

	public PatientApp() {

		System.out.println("patientApp start");
		topServerToPatientRW = new TopServerToPatientRW();
		socket = new Socket();

		try {
			socket.connect(new InetSocketAddress(topServerIp, topServerPort));
			bwrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			bread = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		getContentPane().setLayout(null);
		setResizable(false);
		setBounds(0, 0, 580, 630);

		setVisible(true);

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

		textField = new JTextField();
		textField.setBounds(152, 85, 96, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(152, 116, 96, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		DefaultListModel model = new DefaultListModel();
		JList list = new JList(model);
		list.setBounds(104, 265, 359, 117);
		getContentPane().add(list);

		JList list_1 = new JList();
		list_1.setBounds(104, 448, 359, 117);
		getContentPane().add(list_1);

		JLabel lblList = new JLabel(" Doctor List :");
		lblList.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		lblList.setBounds(39, 232, 119, 37);
		getContentPane().add(lblList);

		JLabel lblSex = new JLabel("Address :");
		lblSex.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		lblSex.setBounds(39, 138, 119, 37);
		getContentPane().add(lblSex);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(152, 147, 96, 21);
		getContentPane().add(textField_2);

		JLabel label_2 = new JLabel("Appointment List :");
		label_2.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		label_2.setBounds(29, 392, 174, 86);
		getContentPane().add(label_2);

		JButton btnEnter = new JButton("enter");
		btnEnter.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		btnEnter.setBounds(473, 265, 107, 23);
		getContentPane().add(btnEnter);

		JButton button_1 = new JButton("enter");
		button_1.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		button_1.setBounds(104, 575, 107, 23);
		getContentPane().add(button_1);

		JButton button_3 = new JButton("delete");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_3.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		button_3.setBounds(221, 575, 107, 23);
		getContentPane().add(button_3);

		JButton button_4 = new JButton("save");
		button_4.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		button_4.setBounds(320, 84, 119, 23);
		getContentPane().add(button_4);

		JButton button_5 = new JButton("cancel");
		button_5.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		button_5.setBounds(320, 124, 119, 23);
		getContentPane().add(button_5);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(152, 178, 96, 21);
		getContentPane().add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(152, 209, 96, 21);
		getContentPane().add(textField_4);

		JLabel lblBirthDate = new JLabel("Birth Date :");
		lblBirthDate.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		lblBirthDate.setBounds(39, 166, 119, 37);
		getContentPane().add(lblBirthDate);

		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		lblPhone.setBounds(39, 198, 119, 37);
		getContentPane().add(lblPhone);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					while (true) {

						String message = bread.readLine();
						System.out.println(message);

						doctors = topServerToPatientRW.read(message);
//						bwrite.write("I receive message.");
//						bwrite.newLine();
//						bwrite.flush();
						
						if (doctors.size() != 0) {
							System.out.println(doctors.get(0).getLastName());
							break;
						}
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				model.removeAllElements();	
				int count = 0;
				for(Doctor doctor : doctors)
				{
					model.add(count++, doctor.getName() + " " + doctor.getLastName() + " : " + doctor.getSubject());
				}

			}
		});
		btnUpdate.setBounds(457, 349, 117, 29);
		getContentPane().add(btnUpdate);
		
		list.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent mouseEvent) 
					{
						if(mouseEvent.getClickCount() == 2)
						{
							int index = list.locationToIndex(mouseEvent.getPoint());
							
							btnEnter.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									Doctor doctor = doctors.get(index);
									new DoctorPage(doctor.getPort());
											
								}
								
							});
						}
					}
				});

	}
}
