import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;


public class librarianframe extends JFrame {
	DefaultTableModel dtm;	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String username;
	private int ID;
	JList list=null;
	

	DefaultListModel listModel= new DefaultListModel();
	private JTextField textField_2;
	private JTextField textField_3;
	

	/*void _FillExchanges()
	{
		
		for(int i=0;i<dtm.getRowCount();i++)
		{
			dtm.removeRow(0);
		}
		
		MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
		sql.openConnection();
		ArrayList<clsExchange> L= sql.getExchanges();
		sql.closeConnection();
		for(int i = 0; i < L.size(); i++) {
			String[] veriler=new String[4];

			veriler[0]=String.valueOf(L.get(i).getUserID());
			dtm.addRow(veriler);
		}
		
		
	}
	*/
	
	public librarianframe(String username, int ID) {
		this.username = username;
		this.ID = ID;
		
		setTitle("LIBRARIAN PANEL-" + String.valueOf(username) + "(" + ID + ")");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1093, 480);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Update", null, panel, null);
		panel.setLayout(null);
		
		JButton btnChange = new JButton("Change");
		btnChange.setVisible(false);
		
		JButton btnNewButton_1 = new JButton("Add Track Number");
		btnNewButton_1.setVisible(false);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setVisible(false);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				try{
					sql.openConnection();
					if (sql.deleteTrackNumber(textField.getText(), textField_1.getText()) == 1) {
						clsGeneral.infoBox("Succesfully deleted!");
						return;
					}
					else {
						clsGeneral.infoBox("There is no such track number!");
					}
					sql.closeConnection();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					sql.closeConnection();
					clsGeneral.infoBox(e.toString());
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(399, 280, 276, 23);
		panel.add(btnNewButton_2);
		btnNewButton_2.setVisible(false);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Books ID");
		lblNewLabel_2.setBounds(288, 138, 88, 14);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setVisible(false);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Track Number");
		lblNewLabel_3.setBounds(288, 169, 133, 14);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		
		
		
		textField = new JTextField();
		textField.setBounds(424, 135, 205, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		
		JLabel lblNewLabel = new JLabel("Enter Username");
		lblNewLabel.setBounds(288, 169, 99, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("Track Number");
		lblNewLabel_1.setBounds(288, 234, 99, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		
		JLabel lblPublishDate = new JLabel("Publish Date");
		lblPublishDate.setBounds(288, 203, 115, 14);
		lblPublishDate.setVisible(false);
		panel.add(lblPublishDate);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(424, 200, 205, 20);
		comboBox_1.setVisible(false);
		panel.add(comboBox_1);
		
		
		textField_2 = new JTextField();
		textField_2.setBounds(424, 200, 205, 20);
		panel.add(textField_2);
		textField_2.setVisible(false);
		textField_2.setColumns(10);
		
	
		textField_3 = new JTextField();
		textField_3.setBounds(424, 231, 205, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setVisible(false);
		
		
		JLabel lblSelectTheOperation = new JLabel("Select the operation");
		lblSelectTheOperation.setBounds(469, 68, 109, 14);
		panel.add(lblSelectTheOperation);
		
		JLabel lblEnterNewUsername = new JLabel("Book's Name");
		lblEnterNewUsername.setBounds(288, 138, 141, 14);
		panel.add(lblEnterNewUsername);
		lblEnterNewUsername.setVisible(false);
	
		JLabel lblEnterNewUsertype = new JLabel("Enter New UserType");
		lblEnterNewUsertype.setBounds(288, 203, 115, 14);
		lblEnterNewUsertype.setVisible(false);
		panel.add(lblEnterNewUsertype);
		
	
		JLabel lblEnterNewUsername_1 = new JLabel("Author's Name");
		lblEnterNewUsername_1.setBounds(288, 169, 121, 14);
		lblEnterNewUsername_1.setVisible(false);
		panel.add(lblEnterNewUsername_1);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(424, 166, 205, 20);
		textField_1.setVisible(false);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex() == 1) {
					comboBox_1.setVisible(false);
					btnNewButton_2.setVisible(false);
					btnNewButton.setVisible(true);
					btnChange.setVisible(false);
					lblNewLabel_3.setVisible(false);
					lblNewLabel_2.setVisible(false);
					btnNewButton_1.setVisible(true);
					textField_2.setVisible(true);
					textField_3.setVisible(true);
					lblNewLabel.setVisible(false);
					lblNewLabel_1.setVisible(true);
					lblPublishDate.setVisible(true);
					lblEnterNewUsertype.setVisible(false);
					textField.setVisible(true);
					lblEnterNewUsername_1.setVisible(true);
					lblEnterNewUsertype.setVisible(false);
					lblEnterNewUsername.setVisible(true);
					textField_1.setVisible(true);
				}
				else if(comboBox.getSelectedIndex() == 2) {
					lblNewLabel.setVisible(false);
					btnNewButton.setVisible(false);
					textField_2.setVisible(false);
					textField_1.setVisible(true);
					lblNewLabel_3.setVisible(true);
					lblNewLabel_2.setVisible(true);
					btnNewButton_2.setVisible(true);
					btnNewButton_1.setVisible(false);
					btnChange.setVisible(false);
					textField_3.setVisible(false);
					lblNewLabel_1.setVisible(false);
					lblEnterNewUsername_1.setVisible(false);
					comboBox_1.setVisible(false);
					lblPublishDate.setVisible(false);
					textField.setVisible(true);
					lblEnterNewUsertype.setVisible(false);
					lblEnterNewUsername.setVisible(false);
					
				}
				else if(comboBox.getSelectedIndex() == 3) {
					textField.setVisible(false);
					btnNewButton_2.setVisible(false);
					btnNewButton.setVisible(false);
					lblNewLabel_3.setVisible(false);
					lblNewLabel_2.setVisible(false);
					btnNewButton_1.setVisible(false);
					textField_2.setVisible(false);
					btnChange.setVisible(true);
					lblEnterNewUsername.setVisible(false);
					lblPublishDate.setVisible(false);
					lblNewLabel_1.setVisible(false);
					textField_3.setVisible(false);
					lblNewLabel.setVisible(true);
					textField_1.setVisible(true);
					comboBox_1.setVisible(false);
					lblEnterNewUsername_1.setVisible(false);
					lblEnterNewUsertype.setVisible(true);
					MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
					try {
						sql.openConnection();
						ArrayList<String> a =null; 
						a =sql.GetUserTypes();
						comboBox_1.removeAllItems();
						comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Please Select The UserType"}));
						for(String b: a) {
							comboBox_1.addItem(b);
						}
						comboBox_1.setVisible(true);
						sql.closeConnection();
					}
					catch (SQLException d) {
						// TODO Auto-generated catch block
						sql.closeConnection();
						d.printStackTrace();
					}
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Please Select Operation", "Add Book", "Delete Book", "Change Membertype"}));
		comboBox.setBounds(424, 93, 205, 20);
		panel.add(comboBox);
		

		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				 if (comboBox.getSelectedIndex() == 3) {
						MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
						try{
							sql.openConnection();
							clsResult result = sql.UpdateUsertype(textField_1.getText(), comboBox_1.getSelectedIndex());
							if (!result.getResult()) {
								clsGeneral.infoBox(result.getMessage());
								return;
							}
							clsGeneral.infoBox(result.message);
							sql.closeConnection();
						}catch (SQLException e) {
							// TODO Auto-generated catch block
							sql.closeConnection();
							e.printStackTrace();
						}
					}
		}});
		btnChange.setBounds(399, 280, 276, 23);
		panel.add(btnChange);

	
		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				try{
					sql.openConnection();
					boolean result = sql.addBook(textField.getText(), textField_1.getText(), textField_2.getText());
					if (!result) {
						clsGeneral.infoBox("An error occured!");
						return;
					}
					clsGeneral.infoBox("Succesfully Added!");
					sql.closeConnection();
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					sql.closeConnection();
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(399, 280, 276, 23);
		panel.add(btnNewButton);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
				try{
					sql.openConnection();
					String ID = sql.getID(textField.getText(), textField_2.getText());
					System.out.println("ID:" + ID);
					if(sql.addTrackNumber(ID, textField_3.getText())) {
						clsGeneral.infoBox("Succesfully Added!");
					}
					else {
						clsGeneral.infoBox("An Error Occured!");
					}
					sql.closeConnection();
				}catch (SQLException e1) {
					// TODO Auto-generated catch block
					sql.closeConnection();
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(399, 314, 276, 23);
		panel.add(btnNewButton_1);
		
	

		

		MicrosoftSQLConnection SQL = new MicrosoftSQLConnection();
		
	
		String[] colum= {"Ad","Soyad","Yas","Sehir"};
		
		dtm=new DefaultTableModel();
		dtm.setColumnIdentifiers(colum);

		String[] veriler=new String[4];

		veriler[0]="REHA";
		dtm.addRow(veriler);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("All Reservations", null, panel_2, null);
		panel_2.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 37, 1062, 366);
		panel_2.add(textArea);
			MicrosoftSQLConnection sql = new MicrosoftSQLConnection();
		try {
			sql.openConnection();
			ArrayList<String> a =null; 
			/*a =sql.GetCustomers();
			for(String b: a) {
				comboBox_2.addItem(b);
			}*/
			
			String e;
			e = sql.getReservations();
			e.toString();
			textArea.setText(e);
			JLabel lblAllFeedbacksFrom = new JLabel("ALL ACTIVE RESERVATIONS");
			lblAllFeedbacksFrom.setBounds(244, 1, 236, 25);
			panel_2.add(lblAllFeedbacksFrom);
			sql.closeConnection();
		}
		catch (SQLException d) {
			// TODO Auto-generated catch block
			sql.closeConnection();
			d.printStackTrace();
		}
		
		//_FillExchanges();
		
		
	}
}

