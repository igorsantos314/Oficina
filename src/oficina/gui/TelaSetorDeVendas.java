package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class TelaSetorDeVendas extends JFrame{
	
	private JTable tableProdutos;
	private JTable tableVenda;
	private JScrollPane scrollPaneProduto;
	private JScrollPane scrollPaneVenda;
	
	private String[] colunasTabelaProduto = {"Codigo", "Nome", "Valor"};
	private final int QUANTIDADE_MAX_CONTAS = 100;
	private Object[][] elementosProduto = new Object[QUANTIDADE_MAX_CONTAS][3];
	
	private String[] colunasTabelaVenda = {"Codigo Produto", "Nome Produto", "Valor Produto", "Quantidade"};
	private Object[][] elementosVenda = new Object[QUANTIDADE_MAX_CONTAS][4];
	
	private JTextField tfProduto;
	private JButton btnNewButton;
	private JButton btnRemove;
	private JLabel lblCodigoVenda;
	private JLabel lblCodVenda;
	private JLabel lblQuantidadeProdutos;
	private JLabel lblQuantidade;
	private JLabel lblTotal;
	private JLabel lblValorTotal;
	private JButton btnFinalizar;
	
	public TelaSetorDeVendas() {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setResizable(false);
		
		setSize(1115,633);
		setTitle("SETOR DE VENDAS");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//PRODUTO
		scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 50, 375, 520);
		getContentPane().add(scrollPaneProduto);
		
		tableProdutos = new JTable(elementosProduto, colunasTabelaProduto);
		tableProdutos.setFont(new Font("Courier New", Font.PLAIN, 11));
		tableProdutos.setForeground(Color.BLACK);
		tableProdutos.setDefaultEditor(Object.class, null);
		scrollPaneProduto.setViewportView(tableProdutos);
		
		//VENDA
		scrollPaneVenda = new JScrollPane();
		scrollPaneVenda.setBounds(523, 50, 557, 461);
		getContentPane().add(scrollPaneVenda);
		
		tableVenda = new JTable(elementosVenda, colunasTabelaVenda);
		tableVenda.setFont(new Font("Courier New", Font.PLAIN, 11));
		tableVenda.setForeground(Color.BLACK);
		tableVenda.setDefaultEditor(Object.class, null);
		scrollPaneVenda.setViewportView(tableVenda);
		
		JLabel lblNewLabel = new JLabel("BUSCAR:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 22, 59, 14);
		getContentPane().add(lblNewLabel);
		
		tfProduto = new JTextField();
		tfProduto.setFont(new Font("Arial", Font.PLAIN, 11));
		tfProduto.setBounds(65, 19, 207, 20);
		getContentPane().add(tfProduto);
		tfProduto.setColumns(10);
		
		JButton btBuscarProduto = new JButton("BUSCAR");
		btBuscarProduto.setBackground(Color.LIGHT_GRAY);
		btBuscarProduto.setFont(new Font("Arial", Font.PLAIN, 11));
		btBuscarProduto.setBounds(282, 16, 103, 23);
		getContentPane().add(btBuscarProduto);
		
		btnNewButton = new JButton("ADD");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 11));
		btnNewButton.setBounds(395, 251, 118, 23);
		getContentPane().add(btnNewButton);
		
		btnRemove = new JButton("REMOVE");
		btnRemove.setBackground(Color.LIGHT_GRAY);
		btnRemove.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRemove.setBounds(395, 285, 118, 23);
		getContentPane().add(btnRemove);
		
		lblCodigoVenda = new JLabel("1");
		lblCodigoVenda.setFont(new Font("Arial", Font.BOLD, 28));
		lblCodigoVenda.setForeground(Color.RED);
		lblCodigoVenda.setBounds(720, 12, 360, 28);
		getContentPane().add(lblCodigoVenda);
		
		lblCodVenda = new JLabel("COD. VENDA:");
		lblCodVenda.setForeground(Color.BLACK);
		lblCodVenda.setFont(new Font("Arial", Font.BOLD, 28));
		lblCodVenda.setBounds(523, 12, 190, 28);
		getContentPane().add(lblCodVenda);
		
		lblQuantidadeProdutos = new JLabel("0");
		lblQuantidadeProdutos.setForeground(Color.BLUE);
		lblQuantidadeProdutos.setFont(new Font("Arial", Font.BOLD, 28));
		lblQuantidadeProdutos.setBounds(636, 522, 107, 48);
		getContentPane().add(lblQuantidadeProdutos);
		
		lblQuantidade = new JLabel("QUANT:");
		lblQuantidade.setFont(new Font("Arial", Font.BOLD, 28));
		lblQuantidade.setBounds(523, 522, 118, 48);
		getContentPane().add(lblQuantidade);
		
		lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Arial", Font.BOLD, 28));
		lblTotal.setBounds(808, 522, 107, 48);
		getContentPane().add(lblTotal);
		
		lblValorTotal = new JLabel("0.00");
		lblValorTotal.setForeground(new Color(0, 153, 0));
		lblValorTotal.setFont(new Font("Arial", Font.BOLD, 28));
		lblValorTotal.setBounds(918, 522, 162, 48);
		getContentPane().add(lblValorTotal);
		
		btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.setBackground(Color.LIGHT_GRAY);
		btnFinalizar.setFont(new Font("Arial", Font.PLAIN, 11));
		btnFinalizar.setBounds(395, 351, 118, 23);
		getContentPane().add(btnFinalizar);
		
		setVisible(true);
	}
}
