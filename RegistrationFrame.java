import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RegistrationFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationFrame window = new RegistrationFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistrationFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 606, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRegistration = new JLabel("REGISTRATION");
		lblRegistration.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegistration.setBounds(247, 11, 140, 42);
		frame.getContentPane().add(lblRegistration);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setBounds(110, 79, 90, 21);
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(110, 123, 64, 14);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblId = new JLabel("Gender");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(110, 169, 46, 14);
		frame.getContentPane().add(lblId);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAge.setBounds(110, 214, 46, 14);
		frame.getContentPane().add(lblAge);
		
		JLabel lblEmailId = new JLabel("E-mail ID");
		lblEmailId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmailId.setBounds(110, 256, 64, 14);
		frame.getContentPane().add(lblEmailId);
		
		textField = new JTextField();
		textField.setBounds(210, 80, 195, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(210, 123, 195, 21);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(210, 165, 195, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(210, 212, 195, 21);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(210, 254, 195, 21);
		frame.getContentPane().add(textField_4);
				
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String firstname = textField.getText();
					String lastname = textField_1.getText();
					String email = textField_4.getText();
					String age = textField_3.getText();
					String id = textField_2.getText();
					
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/sampledb", "root", "pass1234");// lol let me do it..its pid :( wait.. thats not the issue waitkk
					String query="INSERT INTO patients (gender, lastname, firstname, age, email) VALUES ( '" + id + "', '" + lastname + "', '" + firstname + "', " + age + ", '" + email + "' );";

					java.sql.Statement statement = connection.createStatement();
					statement.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Data saved");
				}
				catch(Exception exc)
				{
					exc.printStackTrace();
				}
							
			}
		});//your shift key.. :/
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSave.setBounds(265, 333, 89, 23);
		frame.getContentPane().add(btnSave);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
