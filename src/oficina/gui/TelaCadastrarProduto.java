package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import oficina.facade.Conexao;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastrarProduto extends JFrame{
	private JTextField tfNome;
	private JTextField tfValorCompra;
	private JTextField tfQuantidade;
	
	public TelaCadastrarProduto() {
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 16));
		// TODO Auto-generated constructor stub
		setResizable(false);
		
		setSize(647,343);
		setTitle("CADASTRAR PRODUTO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome do Produto:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(52, 79, 148, 19);
		getContentPane().add(lblNewLabel);
		
		JLabel lblValorDeCompra = new JLabel("Valor de Compra:");
		lblValorDeCompra.setFont(new Font("Arial", Font.PLAIN, 16));
		lblValorDeCompra.setBounds(52, 118, 148, 19);
		getContentPane().add(lblValorDeCompra);
		
		JLabel lblValorDeVenda = new JLabel("Valor de Venda:");
		lblValorDeVenda.setFont(new Font("Arial", Font.PLAIN, 16));
		lblValorDeVenda.setBounds(52, 159, 148, 19);
		getContentPane().add(lblValorDeVenda);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(52, 198, 148, 19);
		getContentPane().add(lblNewLabel_1);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Arial", Font.PLAIN, 16));
		tfNome.setBounds(191, 76, 390, 28);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		tfValorCompra = new JTextField();
		tfValorCompra.setFont(new Font("Arial", Font.PLAIN, 16));
		tfValorCompra.setColumns(10);
		tfValorCompra.setBounds(191, 115, 390, 28);
		getContentPane().add(tfValorCompra);
		
		JTextField tfValorVenda = new JTextField();
		tfValorVenda.setFont(new Font("Arial", Font.PLAIN, 16));
		tfValorVenda.setColumns(10);
		tfValorVenda.setBounds(191, 156, 390, 28);
		getContentPane().add(tfValorVenda);
		
		tfQuantidade = new JTextField();
		tfQuantidade.setFont(new Font("Arial", Font.PLAIN, 16));
		tfQuantidade.setColumns(10);
		tfQuantidade.setBounds(191, 195, 390, 28);
		getContentPane().add(tfQuantidade);
		
		JButton btSalvar = new JButton("SALVAR PRODUTO");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = tfNome.getText().toUpperCase();
				Float valorCompra = Float.parseFloat(tfValorCompra.getText());
				Float valorVenda = Float.parseFloat(tfValorVenda.getText());
				int quantidade = Integer.parseInt(tfQuantidade.getText());
				
				//CHAMAR CONEXAO PARA SALVAR O PRODUTO
				Conexao.pegarInstancia().salvarProduto(0, nome, valorCompra, valorVenda, quantidade);
				
				//LIMPAR CAMPOS
				tfNome.setText("");
				tfValorCompra.setText("");
				tfValorVenda.setText("");
				tfQuantidade.setText("");
				
				JOptionPane.showMessageDialog(null, "PRODUTO CADASTRADO COM SUCESSO !!");
			}
		});
		btSalvar.setBackground(Color.LIGHT_GRAY);
		btSalvar.setFont(new Font("Arial", Font.PLAIN, 15));
		btSalvar.setBounds(380, 244, 201, 23);
		getContentPane().add(btSalvar);
		
		
		
		setVisible(true);
	}
}
