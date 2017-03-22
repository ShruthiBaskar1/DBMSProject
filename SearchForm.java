import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class SearchForm {
    private JFrame frame;
    JFrame frame1;
    JTextField textbox,textbox1,textbox2,textbox3,textbox4,textbox5,textbox6,textbox7,textbox8;
    JLabel label,label1,label2,label3,label4,label5,label6,label7,label8;
    JButton btnSearch;
    JPanel panel;
    static JTable table;

    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3307/sampledb";
    String username = "root";
    String password = "pass1234";
    String[] columnNames = {"pid", "firstname", "lastname","age", "gender"};
    private JTextField textField;
    private JLabel lblLastName;

    public void createUI()
    {
        frame = new JFrame("Database Search Result");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        textbox = new JTextField();
        textbox.setBounds(120,58,150,20); 
        label=new JLabel("Patient ID");
        label.setBounds(23, 58, 100, 20);
        textbox1 = new JTextField();
        textbox1.setBounds(120,89,150,20); 
        label1=new JLabel("First Name");
        label1.setBounds(23, 89, 100, 20);
        textbox2 = new JTextField();
        textbox2.setBounds(120,151,150,20); 
        label2=new JLabel("Age");
        label2.setBounds(23, 151, 100, 20);
        textbox3 = new JTextField();
        textbox3.setBounds(120,188,150,20); 
        label3=new JLabel("Gender");
        label3.setBounds(23, 188, 100, 20);
        btnSearch = new JButton("Search");
        btnSearch.setBounds(120,285,150,20);
        btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) 
			{
				 btnSearch = (JButton)e2.getSource();
			        System.out.println("Showing Table Data.......");
			            showTableData();
			}
		});
       
        frame.getContentPane().add(textbox);
        frame.getContentPane().add(label);
        frame.getContentPane().add(textbox1);
        frame.getContentPane().add(label1);
        frame.getContentPane().add(textbox2);
        frame.getContentPane().add(label2);
        frame.getContentPane().add(textbox3);
        frame.getContentPane().add(label3);
        frame.getContentPane().add(btnSearch);
        
        textField = new JTextField();
        textField.setBounds(120, 120, 150, 20);
        frame.getContentPane().add(textField);
        
        lblLastName = new JLabel("Last Name");
        lblLastName.setBounds(22, 120, 62, 14);
        frame.getContentPane().add(lblLastName);
        frame.setVisible(true);
        frame.setSize(500, 400);        
    }      

    public void showTableData()
    {
        frame1 = new JFrame("Database Search Result");
        frame1.getContentPane().setLayout(new BorderLayout());       
        final DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model); 
        model.addTableModelListener(new TableModelListener() {
            int row = table.getSelectedRow();
            @Override
            public void tableChanged(TableModelEvent arg0) {
                table.convertRowIndexToModel(row);
            }
        });
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);      
        textbox.getText();
        String id= "";
        String name= "";
        String age = "";
        String gen = "";
        String lname = "";
        try
        {           
             Class.forName(driverName);      
            Connection con = DriverManager.getConnection(url, username, password);
            String sql="select * from patients";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                id = rs.getString("pid");
                name = rs.getString("firstname");
                lname = rs.getString("lastname");
                age = rs.getString("age");
                gen = rs.getString("gender");                  
               
                String searchID = textbox.getText();
                String searchFN = textbox1.getText();
                String searchLN = textField.getText();
                String searchAge = textbox2.getText();
                String searchGender = textbox3.getText();
                
                String[] str1 = {id, name, lname, age, gen};
                String[] str2 = {searchID, searchFN, searchLN, searchAge, searchGender};
                if(checkForSearch(str1, str2))
                	model.addRow(new Object[]{id, name, lname, age, gen});
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        frame1.getContentPane().add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400,300);
    }

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 SearchForm sr = new SearchForm();
					 sr.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public SearchForm() {
		createUI();
	}

    public static boolean checkForSearch(String[] str1, String[] str2)
    {
    	for (int i = 0; i < str2.length; i++) {
			String string1 = str1[i];
			String string2 = str2[i];
			if(string2.length() > 0)
			{
				if(!string2.equals(string1))
					return false;
			}
		}
    	return true;
    }
    
    public void setVisible(boolean b) {
		// TODO Auto-generated method stub 
		frame.setVisible(b);
	}
}