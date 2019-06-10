package ece155b.patient;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import ece155b.common.DoctorInfoParser;
import ece155b.doctor.data.Appointment;
import ece155b.doctor.data.Doctor;

public class DoctorPage extends JFrame {
	
	private Socket socket;
	private String doctorIp = "127.0.0.1";
	private int doctorPort;
	private Doctor doctor;
	
	private DoctorInfoParser doctorInfoParser;
    public BufferedWriter bwrite;
    public BufferedReader bread;
    
   
	
	public DoctorPage(int port) {
		
		doctorInfoParser = new DoctorInfoParser();
		doctorPort = port;
		socket = new Socket();
		
		try {
			socket.connect(new InetSocketAddress(doctorIp, doctorPort));
			bwrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
	        bread = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
	        
	        while(true)
	        {
	        	String message = bread.readLine();
	        	
	        	if(message != null)
	        	{
	        		doctor = doctorInfoParser.read(message);
	        		break;
	        	}
	        		
	        }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		setResizable(false);
		setBounds(0, 0, 580, 630);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(240, 240, 240));
		getContentPane().setFont(new Font("新細明體", Font.PLAIN, 18));
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("New button");
		getContentPane().add(btnNewButton);

		JLabel lblDoctor = new JLabel("Doctor ");
		lblDoctor.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDoctor.setBounds(36, 28, 88, 47);
		getContentPane().add(lblDoctor);

		JLabel lblFirstName = new JLabel("Name :");
		lblFirstName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblFirstName.setBounds(36, 85, 119, 37);
		getContentPane().add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name :");
		lblLastName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblLastName.setBounds(36, 121, 119, 37);
		getContentPane().add(lblLastName);

		JLabel lblSubject = new JLabel("Subject :");
		lblSubject.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblSubject.setBounds(36, 158, 119, 37);
		getContentPane().add(lblSubject);

		JLabel lblAppointmentList = new JLabel("Appointment List :");
		lblAppointmentList.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAppointmentList.setBounds(36, 170, 174, 86);
		getContentPane().add(lblAppointmentList);

		DefaultListModel model = new DefaultListModel();
		JList list_1 = new JList(model);
		list_1.setBounds(36, 240, 468, 171);
		getContentPane().add(list_1);

		JLabel label_name = new JLabel("Kevin");
		label_name.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label_name.setBounds(150, 98, 61, 16);
		getContentPane().add(label_name);

		JLabel label_lastName = new JLabel("Smith");
		label_lastName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label_lastName.setBounds(150, 131, 61, 16);
		getContentPane().add(label_lastName);

		JLabel label_subject = new JLabel("Lang");
		label_subject.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label_subject.setBounds(150, 170, 61, 16);
		getContentPane().add(label_subject);

		JButton btnAdd = new JButton("+New Appointment ");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnAdd.setBounds(185, 426, 225, 29);
		getContentPane().add(btnAdd);

		JButton btnFinish = new JButton("finish");
		btnFinish.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnFinish.setBounds(368, 473, 117, 29);
		getContentPane().add(btnFinish);
		
		JButton btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					while (true) {
						String message;

						message = bread.readLine();

						if (message != null) {
							doctor = doctorInfoParser.read(message);
							break;
						}

					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				int count = 0;
				if(doctor.getAppointments().size() != 0)
				for(Appointment app : doctor.getAppointments())
					model.add(count++, "D: " + app.getDocName() + "; P: " + app.getPatientName() + " - " + app.getDate());
			}
			
		});
		btnUpdate.setBounds(36, 426, 117, 29);
		getContentPane().add(btnUpdate);
	}
}
