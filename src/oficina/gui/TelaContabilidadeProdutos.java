package oficina.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;

import oficina.modelo.Produto;
import oficina.modelo.ProdutoVendido;
import oficina.persistencia.PersistenciaEmBanco;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TelaContabilidadeProdutos extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] colunasTabelaVenda = {"Código do Produto", "Nome do Produto", "Quantidade Vendida", "Receita Total"};
	private final int QUANTIDADE_MAX_CONTAS = 100;
	private Object[][] elementosVenda = new Object[QUANTIDADE_MAX_CONTAS][4];
	private ArrayList<ProdutoVendido> produtosVendidos = new ArrayList<ProdutoVendido>();
	
	private JTable tableVenda;
	private JScrollPane scrollPaneVenda;
	
	public TelaContabilidadeProdutos() {
		
		setResizable(false);
		setSize(958,512);
		setTitle("CONTABILIDADE");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//VENDA
		scrollPaneVenda = new JScrollPane();
		scrollPaneVenda.setBounds(10, 59, 912, 405);
		getContentPane().add(scrollPaneVenda);
		
		tableVenda = new JTable(elementosVenda, colunasTabelaVenda);
		tableVenda.setFont(new Font("Arial", Font.PLAIN, 15));
		tableVenda.setForeground(Color.BLACK);
		tableVenda.setDefaultEditor(Object.class, null);
		scrollPaneVenda.setViewportView(tableVenda);
		
		JLabel lblNewLabel = new JLabel("CONTABILIDADE POR PRODUTO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 858, 37);
		getContentPane().add(lblNewLabel);
		
		//INICIALIZAR A TABELA
		setListaDeProdutos();
		
		setVisible(true);
	}
	
	public void clearTableVenda() {
		//LIMPAR CAMPOS
		for(int i = 0; i<QUANTIDADE_MAX_CONTAS; i++)
		{
			elementosVenda[i][0] = "";
			elementosVenda[i][1] = "";
			elementosVenda[i][2] = "";
			elementosVenda[i][3] = "";
		}
	}
	
	public void setTabelaVenda() {
		
		//LIMPAR TABELA
		clearTableVenda();
		
		//ITERAR LINHAS
		int i = 0;
		
		for(ProdutoVendido pV : produtosVendidos)
		{
			elementosVenda[i][0] = pV.getCodProd();
			elementosVenda[i][1] = pV.getNome();
			elementosVenda[i][2] = pV.getQuantidade();
			elementosVenda[i][3] = pV.getValorUnd();
			
			i++;
		}
		
		//ATUALIZAR TABELA
		tableVenda.updateUI();
		
	}
	
	public void setListaDeProdutos() {
		//SETAR LISTA DE PRODUTOS
		produtosVendidos = PersistenciaEmBanco.pegarInstancia().contabilidadeProdutos();
		
		//INSERIR NA TABELA
		setTabelaVenda();
	}
	
}
