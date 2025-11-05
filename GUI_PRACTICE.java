package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class GUI_PRACTICE extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_PRACTICE frame = new GUI_PRACTICE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_PRACTICE() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("FAQS");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(103, 111, 72, 12);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(175, 108, 96, 18);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(103, 158, 72, 12);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		passwordField.setBounds(175, 155, 96, 18);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("FAQS");
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setBounds(10, 233, 84, 20);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SIGN UP");
		btnNewButton_1.setForeground(new Color(0, 0, 255));
		btnNewButton_1.setBounds(163, 233, 84, 20);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("ABOUT US");
		btnNewButton_2.setForeground(new Color(0, 0, 255));
		btnNewButton_2.setBounds(342, 233, 84, 20);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2 = new JLabel("Sign In");
		lblNewLabel_2.setBounds(203, 30, 44, 33);
		contentPane.add(lblNewLabel_2);

	}
}
