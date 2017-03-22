import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class InitializeFrame 
{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitializeFrame window = new InitializeFrame();
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
	public InitializeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 549, 320);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{85, 112, 186, 0};
		gridBagLayout.rowHeights = new int[]{15, 45, 43, 0, 0, 43, 0, 30, 43, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblWelcome = new JLabel("Welcome...!!");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.anchor = GridBagConstraints.NORTH;
		gbc_lblWelcome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWelcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcome.gridx = 1;
		gbc_lblWelcome.gridy = 1;
		frame.getContentPane().add(lblWelcome, gbc_lblWelcome);
		
		JButton btnLogin = new JButton("SEARCH ");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) 
			{
				SearchForm page=new SearchForm();
				   page.setVisible(true);
			}
		}); 
		
		JButton btnRegistration = new JButton("REGISTRATION");
		btnRegistration.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegistration.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistrationFrame reg=new RegistrationFrame();
				reg.setVisible(true);
			}
		});
		
		JButton btnInitializeDatabase = new JButton("INITIALIZE DATABASE");
		btnInitializeDatabase.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInitializeDatabase.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{	
				try
				{
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/sampledb", "root", "pass1234");

					String query = "CREATE DATABASE IF NOT EXISTS sampledb;";
					java.sql.Statement statement = connection.createStatement();
					statement.executeUpdate(query);
					
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/sampledb","root","pass1234");
								  
					DatabaseMetaData database = connection.getMetaData();
					ResultSet tableList = database.getTables(null, null, "messages", null);
					
					if(tableList.next())
					{
						query = "DROP TABLE messages;";
						statement = connection.createStatement();
						statement.executeUpdate(query);
					}
					
								
					tableList = database.getTables(null, null, "treatment", null);
					
					if(tableList.next())
					{
						query = "DROP TABLE treatment;";
						statement = connection.createStatement();
						statement.executeUpdate(query);
					}
					
					tableList = database.getTables(null, null, "symptom", null);
					
					if(tableList.next())
					{
						query = "DROP TABLE symptom;";
						statement = connection.createStatement();
						statement.executeUpdate(query);
					}
					
					tableList = database.getTables(null, null, "patients", null);
					
					if(tableList.next())
					{
						query = "DROP TABLE patients;";
						statement = connection.createStatement();
						statement.executeUpdate(query);
					}
					
								
					tableList = database.getTables(null, null, "conditions", null);
					
					if(tableList.next())
					{
						query = "DROP TABLE conditions;";
						statement = connection.createStatement();
						statement.executeUpdate(query);
					}			
					query = "CREATE TABLE patients(pid int(3) AUTO_INCREMENT, firstname varchar(50), lastname varchar(50), age int, gender char(1), email varchar(50) NOT NULL, PRIMARY KEY(pid), UNIQUE(email) );";
					statement = connection.createStatement();
					statement.executeUpdate(query);
					
					query = "ALTER TABLE patients AUTO_INCREMENT = 100;";
					statement = connection.createStatement();
					statement.executeUpdate(query);
					
					query = "CREATE TABLE symptom(sympid int AUTO_INCREMENT, symname varchar(50), sympstatus varchar(200), PRIMARY KEY(sympid));";
					statement = connection.createStatement();
					statement.executeUpdate(query);
					
					query = "CREATE TABLE conditions(pconid int AUTO_INCREMENT, pconname varchar(50), pconstatus varchar(200), PRIMARY KEY(pconid));";
					statement = connection.createStatement();
					statement.executeUpdate(query);
					
					query = "CREATE TABLE treatment(treatid int AUTO_INCREMENT, treatname varchar(50), treatdescrip varchar(200), PRIMARY KEY(treatid));";
					statement = connection.createStatement();
					statement.executeUpdate(query);
													
					query = "INSERT INTO patients (firstname, lastname, age, gender, email) VALUES('Smith', 'Jackson', 55, 'M', 'smith@microsoft.com');";
					statement = connection.createStatement();
					statement.executeUpdate(query);	
							  
					query = "INSERT INTO patients (firstname, lastname, age, gender, email) VALUES('Alex', 'Perish', 30, 'M', 'mark.zuckerberg@facebook.com');";
					statement = connection.createStatement();
					statement.executeUpdate(query);	
					
					query = "INSERT INTO patients (firstname, lastname, age, gender, email) VALUES('Larry', 'Page', 45, 'M', 'larry.page@gmail.com');";
					statement = connection.createStatement();
					statement.executeUpdate(query);	
							  
					query = "INSERT INTO patients (firstname, lastname, age, gender, email) VALUES('Lee', 'Wilkins', 42, 'F', 'wilkins@gmail.com');";
					statement = connection.createStatement();
					statement.executeUpdate(query);	
							  
					query = "INSERT INTO patients (firstname, lastname, age, gender, email) VALUES('Chris', 'Garcia', 48, 'M', 'chris@apple.com');";
					statement = connection.createStatement();
					statement.executeUpdate(query);	
					
					query = "INSERT INTO patients (firstname, lastname, age, gender, email) VALUES('Shafia', 'Lopez', 57, 'F', 'lopezj@outlook.com');";
					statement = connection.createStatement();
					statement.executeUpdate(query);	

					query = "INSERT INTO patients (firstname, lastname, age, gender, email) VALUES('James', 'Rodriguez', 101, 'M', 'rodriguez@heaven.com');";
					statement = connection.createStatement();
					statement.executeUpdate(query);	
					
					query = "INSERT INTO patients (firstname, lastname, age, gender, email) VALUES('Maria', 'Williams', 40, 'F', 'maria.williams@yahoo.com');";
					statement = connection.createStatement();
					statement.executeUpdate(query);	
					
					query = "INSERT INTO patients (firstname, lastname, age, gender, email) VALUES('Richard', 'Brown', 53, 'F', 'brownr@yahoo.com');";
					statement = connection.createStatement();
					statement.executeUpdate(query);	

					query = "INSERT INTO patients (firstname, lastname, age, gender, email) VALUES('Shruthi', 'Baskar', 53, 'F', 'shruthibaskar@outlook.com');";
					statement = connection.createStatement();
					statement.executeUpdate(query);
				}
				catch(Exception E)
				{
					System.out.println(""+E);
				}
			}
		});
		GridBagConstraints gbc_btnInitializeDatabase = new GridBagConstraints();
		gbc_btnInitializeDatabase.fill = GridBagConstraints.BOTH;
		gbc_btnInitializeDatabase.insets = new Insets(0, 0, 5, 0);
		gbc_btnInitializeDatabase.gridx = 2;
		gbc_btnInitializeDatabase.gridy = 2;
		frame.getContentPane().add(btnInitializeDatabase, gbc_btnInitializeDatabase);
		GridBagConstraints gbc_btnRegistration = new GridBagConstraints();
		gbc_btnRegistration.fill = GridBagConstraints.BOTH;
		gbc_btnRegistration.insets = new Insets(0, 0, 5, 0);
		gbc_btnRegistration.gridx = 2;
		gbc_btnRegistration.gridy = 4;
		frame.getContentPane().add(btnRegistration, gbc_btnRegistration);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 5, 0);
		gbc_btnLogin.fill = GridBagConstraints.BOTH;
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 6;
		frame.getContentPane().add(btnLogin, gbc_btnLogin);
	}

	protected void dispose() {
		// TODO Auto-generated method stub
		
	}
}
