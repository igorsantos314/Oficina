package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import oficina.exception.VeiculoJaCadastradoException;
import oficina.facade.Conexao;
import oficina.modelo.IVeiculo;
import oficina.modelo.VeiculoCarro;
import oficina.modelo.VeiculoMoto;
import oficina.types.VeiculosTypes;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class TelaCadastrarVeiculo extends JFrame{
	private JTextField tfModelo;
	private JTextField tfCor;
	private JTextField tfPlaca;
	private JTextField tfAno;
	private JTextField tfKmAtual;
	
	public TelaCadastrarVeiculo() throws ParseException{
		setResizable(false);
		
		setSize(400,335);
		setTitle("CADASTRAR VEICULO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(64, 27, 74, 14);
		getContentPane().add(lblModelo);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(64, 70, 74, 14);
		getContentPane().add(lblPlaca);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setBounds(64, 113, 74, 14);
		getContentPane().add(lblCor);
		
		tfModelo = new JTextField();
		tfModelo.setBounds(148, 24, 145, 20);
		getContentPane().add(tfModelo);
		tfModelo.setColumns(10);
		
		tfCor = new JTextField();
		tfCor.setBounds(148, 110, 145, 20);
		getContentPane().add(tfCor);
		tfCor.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String modelo = tfModelo.getText().toUpperCase();
				String placa = tfPlaca.getText().toUpperCase();
				String cor = tfCor.getText().toUpperCase();
				String ano = tfAno.getText();
				int km_atual = Integer.parseInt(tfKmAtual.getText());
				
				try {
					Conexao.pegarInstancia().salvarVeiculo(placa, modelo, cor, ano, km_atual);
					
					//MENSAGEM DE SUCESSO
					JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
					
				} catch (VeiculoJaCadastradoException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				//LIMPAR CAMPOS
				tfModelo.setText("");
				tfPlaca.setText("");
				tfCor.setText("");
				tfAno.setText("");
				tfKmAtual.setText("");
				
			}
		});
		
		btnSalvar.setBounds(204, 245, 89, 23);
		getContentPane().add(btnSalvar);
		
		tfPlaca = new JTextField();
		tfPlaca.setBounds(148, 67, 145, 20);
		getContentPane().add(tfPlaca);
		tfPlaca.setColumns(10);
		
		tfAno = new JTextField();
		tfAno.setBounds(148, 152, 145, 20);
		getContentPane().add(tfAno);
		tfAno.setColumns(10);
		
		tfKmAtual = new JTextField();
		tfKmAtual.setBounds(148, 194, 145, 20);
		getContentPane().add(tfKmAtual);
		tfKmAtual.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ano:");
		lblNewLabel.setBounds(64, 155, 74, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("KM Atual:");
		lblNewLabel_1.setBounds(64, 197, 74, 14);
		getContentPane().add(lblNewLabel_1);
		
		setVisible(true);
	}
}
