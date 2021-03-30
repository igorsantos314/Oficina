package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;

import oficina.exception.ClienteJaCadastradoException;
import oficina.facade.Conexao;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.awt.Color;

public class TelaCadastrarCliente extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField tfNome;
	private JTextField tfEmail;
	private JFormattedTextField tfCPF;
	private JFormattedTextField tfTelefone;
	
	public TelaCadastrarCliente() throws java.text.ParseException{
		setResizable(false);
		
		setSize(636,287);
		setTitle("CADASTRAR CLIENTE");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNome.setBounds(60, 46, 83, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCpf.setBounds(60, 79, 83, 14);
		getContentPane().add(lblCpf);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTelefone.setBounds(60, 110, 83, 14);
		getContentPane().add(lblTelefone);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(60, 141, 83, 14);
		getContentPane().add(lblEmail);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Arial", Font.PLAIN, 14));
		tfNome.setBounds(171, 40, 374, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		tfEmail.setBounds(171, 135, 374, 20);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		tfCPF = new JFormattedTextField();
		tfCPF.setFont(new Font("Arial", Font.PLAIN, 14));
		tfCPF.setBounds(171, 73, 181, 20);
		getContentPane().add(tfCPF);
		
		tfTelefone = new JFormattedTextField();
		tfTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
		tfTelefone.setBounds(171, 104, 181, 20);
		getContentPane().add(tfTelefone);
		
		//SETAR MASCARAS
		MaskFormatter mfCpf = new MaskFormatter("###.###.###-##");
		mfCpf.install(tfCPF);
		
		MaskFormatter mfTel = new MaskFormatter("(##)#####-####");
		mfTel.install(tfTelefone);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(Color.LIGHT_GRAY);
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSalvar.setBounds(171, 180, 181, 33);
		getContentPane().add(btnSalvar);
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBackground(Color.LIGHT_GRAY);
		btnFechar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnFechar.setBounds(364, 180, 181, 33);
		getContentPane().add(btnFechar);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = tfNome.getText().toUpperCase();
				String cpf = tfCPF.getText();
				String telefone = tfTelefone.getText();
				String email = tfEmail.getText().toUpperCase();
				
				//CHAMAR CONEXAO
				try {
					
					Conexao.pegarInstancia().salvarCliente(cpf, nome, telefone, email);
					
					//MENSAGEM DE SUCESSO
					JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
					
					//LIMPAR CAMPOS
					tfNome.setText("");
					tfCPF.setText("");
					tfTelefone.setText("");
					tfEmail.setText("");
					
				} catch (ClienteJaCadastradoException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		
		setVisible(true);
	}
}
