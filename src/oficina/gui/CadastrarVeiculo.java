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

public class CadastrarVeiculo extends JFrame{
	private JTextField tfModelo;
	private JTextField tfCor;
	
	private JComboBox comboBoxVeiculo;
	private JTextField tfPlaca;
	
	public CadastrarVeiculo() throws ParseException{
		setResizable(false);
		
		setSize(400,300);
		setTitle("CADASTRAR VEICULO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(10, 11, 46, 14);
		getContentPane().add(lblModelo);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(10, 70, 46, 14);
		getContentPane().add(lblPlaca);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setBounds(10, 128, 46, 14);
		getContentPane().add(lblCor);
		
		tfModelo = new JTextField();
		tfModelo.setBounds(10, 24, 145, 20);
		getContentPane().add(tfModelo);
		tfModelo.setColumns(10);
		
		tfCor = new JTextField();
		tfCor.setBounds(10, 142, 145, 20);
		getContentPane().add(tfCor);
		tfCor.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String veiculo = comboBoxVeiculo.getSelectedItem().toString().toUpperCase();
				String modelo = tfModelo.getText().toUpperCase();
				String placa = tfPlaca.getText().toUpperCase();
				String cor = tfCor.getText().toUpperCase();
				
				try {
					Conexao.pegarInstancia().salvarVeiculo(placa, modelo, cor, veiculo);
					
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
				
			}
		});
		
		btnSalvar.setBounds(272, 224, 89, 23);
		getContentPane().add(btnSalvar);
		
		JLabel lblVeiculo = new JLabel("Veiculo:");
		lblVeiculo.setBounds(202, 11, 46, 14);
		getContentPane().add(lblVeiculo);
		
		comboBoxVeiculo = new JComboBox();
		comboBoxVeiculo.setModel(new DefaultComboBoxModel<>(VeiculosTypes.values()));
		comboBoxVeiculo.setBounds(202, 23, 159, 22);
		getContentPane().add(comboBoxVeiculo);
		
		tfPlaca = new JTextField();
		tfPlaca.setBounds(10, 85, 145, 20);
		getContentPane().add(tfPlaca);
		tfPlaca.setColumns(10);
		
		setVisible(true);
	}
}
