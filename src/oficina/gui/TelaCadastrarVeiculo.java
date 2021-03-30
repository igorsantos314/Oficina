package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import oficina.exception.VeiculoJaCadastradoException;
import oficina.facade.Conexao;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class TelaCadastrarVeiculo extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField tfModelo;
	private JTextField tfCor;
	private JTextField tfPlaca;
	private JTextField tfAno;
	private JTextField tfKmAtual;
	private JTextField tfChassi;
	
	public TelaCadastrarVeiculo() throws ParseException{
		setResizable(false);
		
		setSize(400,381);
		setTitle("CADASTRAR VEICULO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblModelo.setBounds(64, 27, 74, 14);
		getContentPane().add(lblModelo);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPlaca.setBounds(64, 70, 74, 14);
		getContentPane().add(lblPlaca);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCor.setBounds(64, 153, 74, 14);
		getContentPane().add(lblCor);
		
		tfModelo = new JTextField();
		tfModelo.setFont(new Font("Arial", Font.PLAIN, 14));
		tfModelo.setBounds(148, 24, 145, 20);
		getContentPane().add(tfModelo);
		tfModelo.setColumns(10);
		
		tfCor = new JTextField();
		tfCor.setFont(new Font("Arial", Font.PLAIN, 14));
		tfCor.setBounds(148, 150, 145, 20);
		getContentPane().add(tfCor);
		tfCor.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(Color.LIGHT_GRAY);
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String modelo = tfModelo.getText().toUpperCase();
				String placa = tfPlaca.getText().toUpperCase();
				String chassi = tfChassi.getText().toUpperCase();
				String cor = tfCor.getText().toUpperCase();
				String ano = tfAno.getText();
				int km_atual = Integer.parseInt(tfKmAtual.getText());
				
				try {
					Conexao.pegarInstancia().salvarVeiculo(placa, modelo, chassi, cor, ano, km_atual);
					
					//MENSAGEM DE SUCESSO
					JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
					
				} catch (VeiculoJaCadastradoException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				//LIMPAR CAMPOS
				tfModelo.setText("");
				tfPlaca.setText("");
				tfChassi.setText("");
				tfCor.setText("");
				tfAno.setText("");
				tfKmAtual.setText("");
				
			}
		});
		
		btnSalvar.setBounds(204, 285, 89, 23);
		getContentPane().add(btnSalvar);
		
		tfPlaca = new JTextField();
		tfPlaca.setFont(new Font("Arial", Font.PLAIN, 14));
		tfPlaca.setBounds(148, 67, 145, 20);
		getContentPane().add(tfPlaca);
		tfPlaca.setColumns(10);
		
		tfAno = new JTextField();
		tfAno.setFont(new Font("Arial", Font.PLAIN, 14));
		tfAno.setBounds(148, 192, 145, 20);
		getContentPane().add(tfAno);
		tfAno.setColumns(10);
		
		tfKmAtual = new JTextField();
		tfKmAtual.setFont(new Font("Arial", Font.PLAIN, 14));
		tfKmAtual.setBounds(148, 234, 145, 20);
		getContentPane().add(tfKmAtual);
		tfKmAtual.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ano:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(64, 195, 74, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("KM Atual:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(64, 237, 74, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblChassi = new JLabel("Chassi:");
		lblChassi.setFont(new Font("Arial", Font.PLAIN, 14));
		lblChassi.setBounds(64, 111, 74, 14);
		getContentPane().add(lblChassi);
		
		tfChassi = new JTextField();
		tfChassi.setFont(new Font("Arial", Font.PLAIN, 14));
		tfChassi.setColumns(10);
		tfChassi.setBounds(148, 108, 145, 20);
		getContentPane().add(tfChassi);
		
		setVisible(true);
	}
}
