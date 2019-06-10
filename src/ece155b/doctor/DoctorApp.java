package ece155b.doctor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import ece155b.doctor.data.Doctor;
import ece155b.doctor.data.Patient;
import ece155b.top.server.DoctorToTopServerRW;

public class DoctorApp extends JFrame {
	private JTextField textField_name;
	private JTextField textField_lastName;
	private JTextField textField_subject;
	
	private Socket socket = null;
	private String topServerIp = "127.0.0.1";
	private int topServerPort = 6125;
	private DoctorToTopServerRW doctorToTopServerRW = null;
	
	private Doctor doctor;
	private ServerSocket doctorServer = null;
	private int doctorPort; 
    public BufferedWriter bwrite;
    public BufferedReader bread;
	
	public DoctorApp() {
		doctorToTopServerRW = new DoctorToTopServerRW();
		socket = new Socket();
		
		this.openDoctorServer();
		
		
		
		try {
			socket.connect(new InetSocketAddress(topServerIp, topServerPort));
			bwrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
	        bread = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		setFont(null);
		setResizable(false);
		setBounds(0, 0, 580, 630);
		setVisible(true);

		getContentPane().setBackground(new Color(240, 240, 240));
		getContentPane().setFont(new Font("新細明體", Font.PLAIN, 18));
		getContentPane().setLayout(null);

		JButton btnSave = new JButton("save");
		btnSave.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doctor = new Doctor(textField_name.getText(), textField_lastName.getText(), doctorPort, textField_subject.getText());
				
				try {
					DataOutputStream output = new DataOutputStream(socket.getOutputStream());
					bwrite.write(doctorToTopServerRW.write(doctor));
					bwrite.newLine();
					bwrite.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				new Thread() {
					
					public void run()
					{
						try {
							while(true)
							{
								Socket patientSocket = doctorServer.accept();
								
								if(patientSocket != null)
								{
									doctor = new Doctor(textField_name.getText(), textField_lastName.getText(), doctorPort, textField_subject.getText());
									doctor.addPatient(new Patient(patientSocket));
									
								}
								
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}.start();
				
			}
		});
		btnSave.setBounds(369, 95, 119, 23);
		getContentPane().add(btnSave);

		JButton btnCancel = new JButton("cancel");
		btnCancel.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		btnCancel.setBounds(369, 128, 119, 23);
		getContentPane().add(btnCancel);

		JLabel lblDoctor = new JLabel("Doctor ");
		lblDoctor.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 20));
		lblDoctor.setBounds(36, 28, 88, 47);
		getContentPane().add(lblDoctor);

		JLabel lblFirstName = new JLabel("First Name :");
		lblFirstName.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		lblFirstName.setBounds(36, 85, 119, 37);
		getContentPane().add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name :");
		lblLastName.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		lblLastName.setBounds(36, 121, 119, 37);
		getContentPane().add(lblLastName);

		JLabel lblSubject = new JLabel("Subject :");
		lblSubject.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		lblSubject.setBounds(36, 158, 119, 37);
		getContentPane().add(lblSubject);

		textField_name = new JTextField();
		textField_name.setBounds(151, 94, 186, 21);
		getContentPane().add(textField_name);
		textField_name.setColumns(10);

		textField_lastName = new JTextField();
		textField_lastName.setBounds(151, 130, 186, 21);
		getContentPane().add(textField_lastName);
		textField_lastName.setColumns(10);

		textField_subject = new JTextField();
		textField_subject.setBounds(151, 168, 186, 21);
		getContentPane().add(textField_subject);
		textField_subject.setColumns(10);

		JList list_patient = new JList();
		list_patient.setBounds(150, 218, 359, 117);
		getContentPane().add(list_patient);

		JLabel lblPatientList = new JLabel("Patient List :");
		lblPatientList.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		lblPatientList.setBounds(36, 205, 119, 37);
		getContentPane().add(lblPatientList);

		JLabel lblAppointmentList = new JLabel("Appointment List :");
		lblAppointmentList.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		lblAppointmentList.setBounds(37, 345, 174, 86);
		getContentPane().add(lblAppointmentList);

		JButton btnEnter = new JButton("enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEnter.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		btnEnter.setBounds(151, 345, 107, 23);
		getContentPane().add(btnEnter);

		JButton btnDelete = new JButton("delete");
		btnDelete.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		btnDelete.setBounds(268, 345, 107, 23);
		getContentPane().add(btnDelete);

		JList list_appointment = new JList();
		list_appointment.setBounds(151, 404, 359, 117);
		getContentPane().add(list_appointment);

		JButton btnAppointmentEnter = new JButton("enter");
		btnAppointmentEnter.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		btnAppointmentEnter.setBounds(151, 531, 107, 23);
		getContentPane().add(btnAppointmentEnter);

		JButton btnAppointmentDelete = new JButton("delete");
		btnAppointmentDelete.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 16));
		btnAppointmentDelete.setBounds(268, 531, 107, 23);
		getContentPane().add(btnAppointmentDelete);
	}
	
	public static void main(String[] args)
	{
		new DoctorApp();
	}
	
	private synchronized void openDoctorServer()
	{
		try {
			doctorServer = new ServerSocket(5000 + (int)(Math.random() * 1000 + 1));
			System.out.println("listening port: " + doctorServer.getLocalPort());
			doctorPort = doctorServer.getLocalPort();
			
		} catch(Exception exc) {
			this.openDoctorServer();
			exc.printStackTrace();
		}
	}
}