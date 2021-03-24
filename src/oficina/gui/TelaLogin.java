package oficina.gui;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextField;

import oficina.persistencia.PersistenciaEmBanco;
import oficina.types.StatusTypes;
import oficina.types.UserTypes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame{
	private JPasswordField pfSenha;
	
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
		
		JComboBox<UserTypes> cbUser = new JComboBox();
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
				
				//RETORNA ACESSO NEGADO OU PERMITIDO
				boolean permition = PersistenciaEmBanco.pegarInstancia().realizarLogin(cbUser.getSelectedItem().toString(), pfSenha.getText());
				
				//LIMPAR CAMPOS
				pfSenha.setText("");
				
				if(permition) {
					JOptionPane.showMessageDialog(null, "BEM-VINDO !!");
				}
				else {
					JOptionPane.showMessageDialog(null, "ACESSO NEGADO !!");
				}
				
				
				
			}
		});
		btAcessarSistema.setBounds(26, 132, 172, 50);
		getContentPane().add(btAcessarSistema);
		
		pfSenha = new JPasswordField();
		pfSenha.setForeground(Color.RED);
		pfSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		pfSenha.setBounds(10, 88, 204, 22);
		getContentPane().add(pfSenha);
		
		setVisible(true);
	}
}
