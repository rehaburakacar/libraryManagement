import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class teacherframe extends JFrame {
	String username;
	int ID;
	JList list=null;
	

	DefaultListModel listModel= new DefaultListModel();

	private JPanel contentPane;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public teacherframe(String username, int ID) {
		this.username = username;
		this.ID = ID;
		String bookID;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.DD");
		LocalDateTime now = LocalDateTime.now();
		
		
		setTitle("TEACHER PANEL-" + String.valueOf(username) + "(" + ID + ")");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		contentPane.add(tabbedPane);
		
		JPanel Administration = new JPanel();
		Administration.setToolTipText("");
		tabbedPane.addTab("Main Page", null, Administration, null);
		Administration.setLayout(null);
		
		
		JLabel lblPleaseSelectThe = new JLabel("Please type the book's name");
		lblPleaseSelectThe.setBounds(124, 31, 239, 14);
		Administration.add(lblPleaseSelectThe);
		lblPleaseSelectThe.setVisible(false);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText("Please enter the track number");
		lblNewLabel.setBounds(124, 31, 239, 14);
		Administration.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(124, 87, 288, 20);
		Administration.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Please select the book"}));
		comboBox_1.setVisible(false);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setToolTipText("");
		comboBox_2.setBounds(123, 118, 289, 20);
		Administration.add(comboBox_2);
		comboBox_2.setVisible(false);
		
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(124, 56, 239, 20);
		Administration.add(comboBox_3);
		comboBox_3.setVisible(false);
		
		
		JButton btnGiveBack = new JButton("Give Back");
		btnGiveBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				try {
					sql.openConnection();
					
					String id = sql.getIDFromTrackNum(comboBox_3.getSelectedItem().toString().substring(0,5));
					System.out.println("Final Track: " + comboBox_3.getSelectedItem().toString().substring(0, 5));
					if(sql.deleteReservation(ID, id)) {
						clsGeneral.infoBox("Thanks for giving!");
					}
					else {
						clsGeneral.infoBox("An error occured!");
					}
				}catch(SQLException e1){
					e1.printStackTrace();
					clsGeneral.infoBox(e1.getMessage());
					sql.closeConnection();
				}
			}
		});
		btnGiveBack.setBounds(124, 86, 140, 23);
		Administration.add(btnGiveBack);
		btnGiveBack.setVisible(false);
		
		JButton btnTrackBook = new JButton("Track Book");
		btnTrackBook.setVisible(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(124, 56, 151, 20);
		Administration.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);
		
		JButton btnGetTrackNumbers = new JButton("Get Track Numbers");
		btnGetTrackNumbers.setVisible(false);
		
		
		
		btnGetTrackNumbers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				try {
					sql.openConnection();
					ArrayList<String> a =null; 
					String varName = (String)comboBox_1.getSelectedItem();
					String value = comboBox_1.getSelectedItem().toString();
					//int i = Integer.parseInt(value.substring(0, 2));
					//System.out.println("Last ID: " + i);
					a =sql.getNotReserved(value.substring(0, 2));
					comboBox_2.removeAllItems();
					for(String b: a) {
						comboBox_2.addItem(b);
					}
					comboBox_2.setVisible(true);
					sql.closeConnection();
					btnGetTrackNumbers.setVisible(false);
					btnTrackBook.setVisible(true);
				}catch(SQLException e1){
					e1.printStackTrace();
					clsGeneral.infoBox(e1.getMessage());
					sql.closeConnection();
				}
			}
		});
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				try {
					sql.openConnection();
					ArrayList<String> a =null; 
					a =sql.getBooks(textField_1.getText().toString());
					comboBox_1.removeAllItems();
					for(String b: a) {
						comboBox_1.addItem(b);
					}
					comboBox_1.setVisible(true);
					sql.closeConnection();
					btnSearch.setVisible(false);
					btnGetTrackNumbers.setVisible(true);
				}catch(SQLException e1){
					e1.printStackTrace();
					clsGeneral.infoBox(e1.getMessage());
					sql.closeConnection();
				}
			}
		});
		btnTrackBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				try {
					sql.openConnection();
					ArrayList<String> a =null; 
					String varName = (String)comboBox_2.getSelectedItem();
					System.out.println(varName);
					String id = sql.getIDFromTrackNum(comboBox_2.getSelectedItem().toString());
					if (sql.takeBook(id, 7, ID)) {
						System.out.print("Success!");
						clsGeneral.infoBox("Please Give Back In Time!");
					}
					else {
						System.out.println("asd");
					};
					sql.closeConnection();
					btnGetTrackNumbers.setVisible(false);
				}catch(SQLException e1){
					e1.printStackTrace();
					clsGeneral.infoBox(e1.getMessage());
					sql.closeConnection();
				}
			}
		});
		btnTrackBook.setBounds(124, 165, 140, 23);
		Administration.add(btnTrackBook);
		
		
		btnSearch.setBounds(124, 165, 89, 23);
		Administration.add(btnSearch);
		btnSearch.setVisible(false);
		

	
	
		
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.removeAllItems();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select The Operation", "Take Book", "Give Back Book"}));
			/*for(String b: a) {
				comboBox.addItem(b);
			}*/
		comboBox.setVisible(true);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() == 1) {
					comboBox_3.setVisible(false);
					btnGiveBack.setVisible(false);
					lblNewLabel.setVisible(false);
					lblPleaseSelectThe.setVisible(true);
					btnSearch.setVisible(true);
					textField_1.setVisible(true);
				}
				else if(comboBox.getSelectedIndex() == 2) {
					btnGiveBack.setVisible(true);
					MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
					comboBox_3.setVisible(true);
					comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Select The Track Number"}));
					try {
						sql.openConnection();
						ArrayList<String> trackNumbers = new ArrayList<String>();
						trackNumbers = sql.getIDandName(ID);
						for(String b: trackNumbers) {
							comboBox_3.addItem(b);
						}
						sql.closeConnection();
					}catch(SQLException e1){
						e1.printStackTrace();
						clsGeneral.infoBox(e1.getMessage());
						sql.closeConnection();
					}
					lblNewLabel.setVisible(false);
					comboBox_1.setVisible(false);
					comboBox_2.setVisible(false);
					btnTrackBook.setVisible(false);
					textField_1.setVisible(false);
					btnSearch.setVisible(false);
					btnGetTrackNumbers.setVisible(false);
					lblPleaseSelectThe.setVisible(false);
					lblNewLabel.setVisible(true);
				}
			}
		});
		
		comboBox.setBounds(124, 3, 140, 20);
		Administration.add(comboBox);
		
		
		/*comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				String date = now.format(dtf).toString();
				Double price = 0.0;
				try{
					sql.openConnection();
					//clsResult result = sql.getCurrentPrice(comboBox.getSelectedItem().toString(), date);
					clsResult result = sql.getAvailability();
					sql.closeConnection();

					price = result.getPrice();
					System.out.println("price: " + price);
					String currentPrice = price.toString();
					lblNewLabel.setVisible(false);
					lblNewLabel.setText(currentPrice);
					lblNewLabel.setVisible(true);
					}catch (SQLException f) {
						// TODO Auto-generated catch block
						f.printStackTrace();
						clsGeneral.infoBox(f.getMessage());
						sql.closeConnection();
					}
			}
		});
		*/
		
		/*JButton btnChange = new JButton("PROCEED");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				try {
					sql.openConnection();
					sql.getBooks(lblPleaseSelectThe.getText());
					
					sql.closeConnection();
				}catch(SQLException e){
					e.printStackTrace();
					clsGeneral.infoBox(e.getMessage());
					sql.closeConnection();
				}
			}
		});
		btnChange.setBounds(124, 165, 89, 23);
		Administration.add(btnChange);
		*/
		String date = now.format(dtf).toString();
		JLabel lblNewLabel_1 = new JLabel(date);
		lblNewLabel_1.setBounds(317, 31, 95, 14);
		Administration.add(lblNewLabel_1);
		
		JLabel lblCurrentDate = new JLabel("CURRENT DATE");
		lblCurrentDate.setBounds(317, 6, 102, 14);
		Administration.add(lblCurrentDate);
		
		
		btnGetTrackNumbers.setBounds(124, 165, 151, 23);
		Administration.add(btnGetTrackNumbers);
		
		
	}
}
