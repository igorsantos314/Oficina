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
import oficina.modelo.Cliente;
import util.util;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class TelaCadastrarCliente extends JFrame{
	private JTextField tfNome;
	private JTextField tfEmail;
	private JFormattedTextField tfCPF;
	private JFormattedTextField tfTelefone;
	
	public TelaCadastrarCliente() throws java.text.ParseException{
		setResizable(false);
		
		setSize(413,284);
		setTitle("CADASTRAR CLIENTE");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNome);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 165, 374, 64);
		getContentPane().add(panel);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = tfNome.getText().toUpperCase();
				String cpf = tfCPF.getText().replace(".", "").replace("-", "");
				String telefone = tfTelefone.getText();
				String email = tfEmail.getText().toUpperCase();
				
				//CHAMAR CONEXAO
				try {
					
					//VALIDAR CPF
					if(util.isCPF(cpf)) {
						Conexao.pegarInstancia().salvarCliente(cpf, nome, telefone, email);
						
						//MENSAGEM DE SUCESSO
						JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
						
						//LIMPAR CAMPOS
						tfNome.setText("");
						tfCPF.setText("");
						tfTelefone.setText("");
						tfEmail.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "POR FAVOR, DIGITE UM CPF VÁLIDO!");
					}
					
					
				} catch (ClienteJaCadastradoException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		
		panel.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panel.add(btnCancelar);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(10, 57, 46, 14);
		getContentPane().add(lblCpf);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(201, 57, 61, 14);
		getContentPane().add(lblTelefone);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 103, 46, 14);
		getContentPane().add(lblEmail);
		
		tfNome = new JTextField();
		tfNome.setBounds(10, 26, 374, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(10, 118, 374, 20);
		getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		tfCPF = new JFormattedTextField();
		tfCPF.setBounds(10, 72, 181, 20);
		getContentPane().add(tfCPF);
		
		tfTelefone = new JFormattedTextField();
		tfTelefone.setBounds(201, 72, 183, 20);
		getContentPane().add(tfTelefone);
		
		//SETAR MASCARAS
		MaskFormatter mfCpf = new MaskFormatter("###.###.###-##");
		mfCpf.install(tfCPF);
		
		MaskFormatter mfTel = new MaskFormatter("(##)#####-####");
		mfTel.install(tfTelefone);
		
		setVisible(true);
	}
}
