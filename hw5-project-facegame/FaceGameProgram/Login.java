import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.*;

public class Login extends JFrame{
	private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JTextField jTextField1;
    private JPasswordField jTextField2;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private UserDatabase ud = new UserDatabase();
//    private GridBagConstraints gbc = new GridBagConstraints();
    
	public Login () {
		setup();
	}
	
	private void setup() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jPanel1 = new JPanel();
		jPanel1.setBackground(new Color (0.9f, 1.0f, 1.0f));
		
		jLabel1 = new JLabel();
		jLabel1.setText("Welcome to FaceGame!");
		jLabel1.setFont(new Font("Superclarendon", Font.BOLD | Font.ITALIC, 27));
		jPanel1.add(jLabel1);
		
		getContentPane().add(jPanel1, BorderLayout.NORTH);
		
		
		jLabel4 = new JLabel();
		jLabel4.setVerticalAlignment(SwingConstants.TOP);
		jLabel4.setText("Sign UP!");
		
		Font jLabel4Font = new Font("Tahoma", 2, 22);
		jLabel4.setFont(jLabel4Font);
		
		nameField = new JTextField ("Name", 1);
		nameField.setColumns(5);
		nameField.setBackground(Color.WHITE);
		
		emailField = new JTextField ("Email", 20);
		passwordField = new JPasswordField ("New Password", 20);
		
		jButton2 = new JButton("Sign Up");
		jButton2.setVerticalAlignment(SwingConstants.TOP);
		jButton2.setFont(new Font("Lantinghei SC", Font.BOLD | Font.ITALIC, 15));
		jButton2.setHorizontalAlignment(SwingConstants.LEFT);
		jButton2.setBackground(new Color (0.9f, 1.0f, 1.0f));
		jButton2.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent evt) {
					jButton2ActionPerformed (evt);
			
			}
		});
		
		jPanel3 = new JPanel();
		jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.PAGE_AXIS));

/*		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		jPanel3.add(jLabel4, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		jPanel3.add(nameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		jPanel3.add(emailField);

		gbc.gridx = 0;
		gbc.gridy = 3;
		jPanel3.add(passwordField);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		jPanel3.add(jButton2);
*/	
		
		jLabel2 = new JLabel();
		jLabel2.setText("Email");
		
		jLabel3 = new JLabel();
		jLabel3.setText("Password");
		
		jTextField1 = new JTextField();
		jTextField2 = new JPasswordField();
		
		
		Box box1 = Box.createHorizontalBox();
		
		box1.add(jLabel2);
		box1.add(jTextField1);
		
		box1.add(jLabel3);
		box1.add(jTextField2);
		

/*		gbc_box1.weighty = 1.0;
		gbc_box1.weightx = 1.0;
		gbc_box1.ipady = 6;
		gbc_box1.ipadx = 4;
		gbc_box1.gridwidth = 8;
		gbc_box1.fill = GridBagConstraints.HORIZONTAL;
		gbc_box1.anchor = GridBagConstraints.NORTH;
		gbc_box1.insets = new Insets(5, 5, 5, 5);
*/
/*		gbc.gridx = 0;
		gbc.gridy = 0;
		GridBagConstraints gbc_box1 = new GridBagConstraints();
		gbc_box1.weighty = 5.0;
		gbc_box1.weightx = 4.0;
		gbc_box1.fill = GridBagConstraints.HORIZONTAL;
		gbc_box1.anchor = GridBagConstraints.NORTH;
		gbc_box1.ipady = 4;
		gbc_box1.ipadx = 6;
		gbc_box1.gridwidth = 6;
		gbc_box1.insets = new Insets(5, 5, 5, 5);
		gbc_box1.gridy = 0;
		gbc_box1.gridx = 0;
*/
		jPanel3.add(box1);
		
		jButton1 = new JButton("login");
		jButton1.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent evtButton1) {
					jButton2ActionPerformed (evtButton1);
			
			}
		});


		jPanel3.add(jButton1);

		jPanel3.add(jLabel4);

		jPanel3.add(nameField);

		jPanel3.add(emailField);

		jPanel3.add(passwordField);
		
		jPanel3.add(jButton2);
		

		
		
		getContentPane().add(jPanel3, BorderLayout.CENTER);
		pack();

		
	}
	
	private void jButton2ActionPerformed (ActionEvent evt) {
		String inputName = nameField.getText();
		String inputEmail = emailField.getText();
		char[] inputPw = passwordField.getPassword();
		
		Person user = new Person(inputName, inputEmail, inputPw);
		
		UserDatabase.addProfile(user);
		Profile userProfile = new Profile();
	}
	
	
	public static void main (String args[]) {
		Login newLogin = new Login();
	}
	

}
