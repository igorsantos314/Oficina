package oficina.gui;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;

import oficina.types.StatusTypes;
import oficina.types.UserTypes;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame{
	private JPasswordField passwordField;
	
	public TelaLogin() {
		
		setResizable(false);
		
		setSize(243,245);
		setTitle("Login");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usu\u00E1rio:");
		lblNewLabel.setBounds(10, 21, 172, 14);
		getContentPane().add(lblNewLabel);
		
		JComboBox cbUser = new JComboBox();
		cbUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbUser.setForeground(Color.RED);
		cbUser.setModel(new DefaultComboBoxModel<>(UserTypes.values()));
		cbUser.setBounds(10, 36, 204, 22);
		getContentPane().add(cbUser);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setBounds(10, 69, 172, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton btAcessarSistema = new JButton("Acessar Sistema");
		btAcessarSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btAcessarSistema.setBounds(26, 132, 172, 50);
		getContentPane().add(btAcessarSistema);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.RED);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordField.setBounds(10, 88, 204, 22);
		getContentPane().add(passwordField);
		
		
		
		setVisible(true);
	}
}
