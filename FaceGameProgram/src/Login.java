import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Fanglin Lu and Xinxin Ma
 * This program provides Login and sign up interface for users.
 */
public class Login extends JFrame {

	private JButton jButton1;
	private JButton jButton2;

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;

	private JPanel jPanel1;
	private JPanel jPanel3;

	private JTextField jTextField1;
	private JPasswordField jTextField2;
	private JTextField nameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private UserDatabase ud = new UserDatabase();
	private Person user;

	// private GridBagConstraints gbc = new GridBagConstraints();

	/**
	 * Constructor -- initialize the panel with panels, labels and buttons.
	 */
	public Login() {
		setup();
	}

	/**
	 * This method set up all the panels, labels and buttons.
	 */
	private void setup() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JPanel1 is the light blue panel
		jPanel1 = new JPanel();
		jPanel1.setBackground(new Color(0.9f, 1.0f, 1.0f));

		// show welcome information
		// JLabel1 is the text label welcome info
		jLabel1 = new JLabel();
		jLabel1.setText("Welcome to FaceGame!");
		jLabel1.setFont(new Font("Superclarendon", Font.BOLD | Font.ITALIC, 27));
		jPanel1.add(jLabel1);

		getContentPane().add(jPanel1, BorderLayout.NORTH);

		// JLabel4 is the "sign up" info
		jLabel4 = new JLabel();
		jLabel4.setVerticalAlignment(SwingConstants.TOP);
		jLabel4.setText("Sign UP!");
		Font jLabel4Font = new Font("Tahoma", 2, 22);
		jLabel4.setFont(jLabel4Font);

		// textfields: for sign up, respectively
		nameField = new JTextField("Name", 20);
		nameField.setColumns(5);
		nameField.setBackground(Color.WHITE);
		emailField = new JTextField("Email", 20);
		passwordField = new JPasswordField("New Password", 20);

		// the sign up button is JButton2
		jButton2 = new JButton("Sign Up");
		jButton2.setVerticalAlignment(SwingConstants.TOP);
		jButton2.setFont(new Font("Lantinghei SC", Font.BOLD | Font.ITALIC, 15));
		jButton2.setHorizontalAlignment(SwingConstants.LEFT);
		jButton2.setBackground(new Color(0.9f, 1.0f, 1.0f));
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton2ActionPerformed(evt);

			}
		});

		// JPanel3 is the login panel
		jPanel3 = new JPanel();
		jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.PAGE_AXIS));

		// JLabel2 and JLabel3 are email and password for existing user
		// respectively
		jLabel2 = new JLabel();
		jLabel2.setText("Email");
		jLabel3 = new JLabel();
		jLabel3.setText("Password");

		// textfields are for users to input their email/password
		jTextField1 = new JTextField();
		jTextField2 = new JPasswordField();

		Box box1 = Box.createHorizontalBox();
		
		// email
		box1.add(jLabel2);
		box1.add(jTextField1);
		
		// password
		box1.add(jLabel3);
		box1.add(jTextField2);

		jPanel3.add(box1);

		// JButton is the login button
		jButton1 = new JButton("login");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evtButton1) {
				jButton1ActionPerformed(evtButton1);

			}
		});
		
		// put everything on the panel
		jPanel3.add(jButton1);
		jPanel3.add(jLabel4);
		jPanel3.add(nameField);
		jPanel3.add(emailField);
		jPanel3.add(passwordField);
		jPanel3.add(jButton2);

		getContentPane().add(jPanel3, BorderLayout.CENTER);
		pack();

	}

	/**
	 * @param evt
	 *            This method shows what will happen after click the "sign on"
	 *            button.
	 */
	private void jButton2ActionPerformed(ActionEvent evt) {
		String inputName = nameField.getText();
		String inputEmail = emailField.getText();
		char[] inputPw = passwordField.getPassword();

		if (inputName.equals("")) {
			JOptionPane.showMessageDialog(null,
					"Please try again and enter your name.", "Failed",
					JOptionPane.ERROR_MESSAGE);

		} else if (inputEmail.equals("")) {
			JOptionPane.showMessageDialog(null,
					"Please try again and enter your email.", "Failed",
					JOptionPane.ERROR_MESSAGE);

		} else if (inputPw.length == 0) {
			JOptionPane.showMessageDialog(null,
					"Please try again and enter your password.", "Failed",
					JOptionPane.ERROR_MESSAGE);

		} else {
			user = new Person(inputName, inputEmail, inputPw);
			UserDatabase.addProfile(user);

			UserProfileWriter fw = new UserProfileWriter();
			fw.write(user);

			Profile userProfile = new Profile(user);
		}

	}

	/**
	 * @param evt
	 *            This method shows what will happen after clicking the "login"
	 *            button.
	 */
	private void jButton1ActionPerformed(ActionEvent evt) {
		Boolean match = false;
		String inputEmail = jTextField1.getText();
		char[] inputPw = jTextField2.getPassword();
		ArrayList<Person> p = UserDatabase.getUsers();

		for (Person person : p) {
			if (Arrays.equals(person.getPassword(), inputPw)
					&& inputEmail.equals(person.getEmail())) {
				match = true;
				user = person;
			}
		}

		if (match == true) {
			Profile pf = new Profile(user);
			// FileReader fd = new FileReader();

		} else {
			JOptionPane
					.showMessageDialog(
							null,
							"Username or Password is not correct! Please sign up or type agin!",
							"Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @return user This method get a new person.
	 */
	public Person getPerson() {
		return user;
	}

	/**
	 * @param args
	 *            Main method -- a new Login and Signon panel and all related
	 *            functions are displayed.
	 */
	public static void main(String args[]) {
		Login newLogin = new Login();
	}

}
