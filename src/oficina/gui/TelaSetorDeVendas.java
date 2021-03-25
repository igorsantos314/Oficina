package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import oficina.modelo.Produto;
import oficina.persistencia.PersistenciaEmBanco;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TelaSetorDeVendas extends JFrame{
	
	private JTable tableProdutos;
	private JTable tableVenda;
	private JScrollPane scrollPaneProduto;
	private JScrollPane scrollPaneVenda;
	
	private String[] colunasTabelaProduto = {"Codigo", "Nome", "Valor"};
	private final int QUANTIDADE_MAX_CONTAS = 100;
	private Object[][] elementosProduto = new Object[QUANTIDADE_MAX_CONTAS][3];
	
	private String[] colunasTabelaVenda = {"Codigo Produto", "Nome Produto", "Valor UND.", "Quantidade", "Valor Total"};
	private Object[][] elementosVenda = new Object[QUANTIDADE_MAX_CONTAS][5];
	private DefaultTableModel tbVenda;
	
	private JTextField tfProduto;
	private JButton btnNewButton;
	private JButton btnRemove;
	private JLabel lblCodigoVenda;
	private JLabel lblCodVenda;
	private JLabel lblQuantidade;
	private JLabel lblQuant;
	private JLabel lblTotal;
	private JLabel lblValorTotal;
	private JButton btnFinalizar;
	
	private int numeroDoProduto = 0;
	
	private int quantidadeDeProdutos = 0;
	private float subtotal = 0f;
	private float total = 0f;
	
	private JTextField tfQuantidade;
	private JLabel lbl;
	private JLabel lblSubTotal;
	
	public TelaSetorDeVendas() {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setResizable(false);
		
		setSize(1187,813);
		setTitle("SETOR DE VENDAS");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//PRODUTO
		scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 50, 1003, 198);
		getContentPane().add(scrollPaneProduto);
		
		tableProdutos = new JTable(elementosProduto, colunasTabelaProduto);
		tableProdutos.setFont(new Font("Arial", Font.PLAIN, 13));
		tableProdutos.setForeground(Color.BLACK);
		tableProdutos.setDefaultEditor(Object.class, null);
		scrollPaneProduto.setViewportView(tableProdutos);
		
		//VENDA
		scrollPaneVenda = new JScrollPane();
		scrollPaneVenda.setBounds(10, 266, 1003, 436);
		getContentPane().add(scrollPaneVenda);
		
		tableVenda = new JTable(elementosVenda, colunasTabelaVenda);
		tableVenda.setFont(new Font("Arial", Font.PLAIN, 13));
		tableVenda.setForeground(Color.BLACK);
		tableVenda.setDefaultEditor(Object.class, null);
		scrollPaneVenda.setViewportView(tableVenda);
		
		JLabel lblNewLabel = new JLabel("BUSCAR:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(338, 19, 77, 14);
		getContentPane().add(lblNewLabel);
		
		tfProduto = new JTextField();
		tfProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		tfProduto.setBounds(418, 17, 482, 20);
		getContentPane().add(tfProduto);
		tfProduto.setColumns(10);
		
		JButton btBuscarProduto = new JButton("BUSCAR");
		btBuscarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeProduto = tfProduto.getText().toUpperCase();
				
				//FAZER CONSULTA NO BD
				ArrayList<Produto> produtos = PersistenciaEmBanco.pegarInstancia().getProdutoNome(nomeProduto);
				
				//POVOAR TABELA
				inserirTabela(produtos);
			}
		});
		btBuscarProduto.setBackground(Color.LIGHT_GRAY);
		btBuscarProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		btBuscarProduto.setBounds(910, 16, 103, 23);
		getContentPane().add(btBuscarProduto);
		
		btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEGAR O INDICE DA LINHA SELECIONADA
				int numberLine = tableProdutos.getSelectedRow();
				
				if(numberLine >= 0) {
					
					//PEGAR O CODIGO DA OS
					String codProd = tableProdutos.getModel().getValueAt(numberLine,0).toString();
					String nome = tableProdutos.getModel().getValueAt(numberLine,1).toString();
					Float valor = Float.parseFloat(tableProdutos.getModel().getValueAt(numberLine,2).toString());
					int quant = Integer.parseInt(tfQuantidade.getText());
					float totalProduto = quant*valor;
					
					elementosVenda[numeroDoProduto][0] = codProd;
					elementosVenda[numeroDoProduto][1] = nome;
					elementosVenda[numeroDoProduto][2] = valor;
					elementosVenda[numeroDoProduto][3] = quant;
					elementosVenda[numeroDoProduto][4] = totalProduto;
					
					quantidadeDeProdutos += quant;
					subtotal += valor;
					total += totalProduto;
					
					//ATUALIZAR VALORES
					atualizarValores();
					
					//INDICA NOVO PRODUTO
					numeroDoProduto++;
					
					//ATUALIZA A TABELA
					tableVenda.updateUI();
				}
				else {
					JOptionPane.showMessageDialog(null, "POR FAVOR, SELECIONE UMA PRODUTO!");
				}
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(1023, 225, 118, 23);
		getContentPane().add(btnNewButton);
		
		btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEGAR O INDICE DA LINHA SELECIONADA
				int numberLine = tableVenda.getSelectedRow();
				
				//PEGAR O CODIGO DA OS
				Float valor = Float.parseFloat(tableVenda.getModel().getValueAt(numberLine,2).toString());
				int quant = Integer.parseInt(tableVenda.getModel().getValueAt(numberLine,3).toString());
				float totalProduto = quant*valor;
				
				quantidadeDeProdutos -= quant;
				subtotal -= valor;
				total -= totalProduto;
				
				//REMOVER DA TABELA
				deleteItemTable(numberLine);
				
				//ATUALIZAR VALORES
				atualizarValores();
				
				//ATUALIZAR TABELA
				tableVenda.updateUI();
			}
		});
		btnRemove.setBackground(Color.LIGHT_GRAY);
		btnRemove.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRemove.setBounds(1023, 586, 118, 23);
		getContentPane().add(btnRemove);
		
		lblCodigoVenda = new JLabel("1");
		lblCodigoVenda.setFont(new Font("Arial", Font.BOLD, 28));
		lblCodigoVenda.setForeground(Color.RED);
		lblCodigoVenda.setBounds(204, 13, 95, 28);
		getContentPane().add(lblCodigoVenda);
		
		lblCodVenda = new JLabel("COD. VENDA:");
		lblCodVenda.setForeground(Color.BLACK);
		lblCodVenda.setFont(new Font("Arial", Font.BOLD, 28));
		lblCodVenda.setBounds(10, 13, 196, 28);
		getContentPane().add(lblCodVenda);
		
		lblQuantidade = new JLabel("0");
		lblQuantidade.setForeground(Color.BLUE);
		lblQuantidade.setFont(new Font("Arial", Font.BOLD, 28));
		lblQuantidade.setBounds(123, 713, 107, 48);
		getContentPane().add(lblQuantidade);
		
		lblQuant = new JLabel("QUANT:");
		lblQuant.setFont(new Font("Arial", Font.BOLD, 28));
		lblQuant.setBounds(10, 713, 118, 48);
		getContentPane().add(lblQuant);
		
		lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Arial", Font.BOLD, 28));
		lblTotal.setBounds(741, 713, 107, 48);
		getContentPane().add(lblTotal);
		
		lblValorTotal = new JLabel("0.00");
		lblValorTotal.setForeground(new Color(0, 153, 0));
		lblValorTotal.setFont(new Font("Arial", Font.BOLD, 28));
		lblValorTotal.setBounds(851, 713, 162, 48);
		getContentPane().add(lblValorTotal);
		
		btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.setBackground(Color.LIGHT_GRAY);
		btnFinalizar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFinalizar.setBounds(1023, 645, 118, 23);
		getContentPane().add(btnFinalizar);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSalvar.setBackground(Color.LIGHT_GRAY);
		btnSalvar.setBounds(1023, 679, 118, 23);
		getContentPane().add(btnSalvar);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(1023, 177, 118, 14);
		getContentPane().add(lblNewLabel_1);
		
		tfQuantidade = new JTextField();
		tfQuantidade.setFont(new Font("Arial", Font.PLAIN, 14));
		tfQuantidade.setText("1");
		tfQuantidade.setBounds(1023, 194, 118, 20);
		getContentPane().add(tfQuantidade);
		tfQuantidade.setColumns(10);
		
		lbl = new JLabel("SUBTOTAL:");
		lbl.setFont(new Font("Arial", Font.BOLD, 28));
		lbl.setBounds(240, 713, 177, 48);
		getContentPane().add(lbl);
		
		lblSubTotal = new JLabel("0.00");
		lblSubTotal.setForeground(Color.ORANGE);
		lblSubTotal.setFont(new Font("Arial", Font.BOLD, 28));
		lblSubTotal.setBounds(405, 713, 162, 48);
		getContentPane().add(lblSubTotal);
		
		//INICIALIZAR TABELA DE PRODUTOS
		updateTable();
		
		setVisible(true);
	}
	
	public void deleteItemTable(int pos) {
		elementosVenda[pos][0] = "";
		elementosVenda[pos][1] = "";
		elementosVenda[pos][2] = "";
		elementosVenda[pos][3] = "";
		elementosVenda[pos][4] = "";
	}
	
	public void clearTable() {
		//LIMPAR CAMPOS
		for(int i = 0; i<QUANTIDADE_MAX_CONTAS; i++)
		{
			elementosProduto[i][0] = "";
			elementosProduto[i][1] = "";
			elementosProduto[i][2] = "";
		}
	}
	
	public void inserirTabela(ArrayList<Produto> produtos) {
		
		//LIMPAR CAMPOS
		clearTable();
		
		//ITERAR LINHAS
		int i = 0;
		
		//PRENCHER CAMPOS
		for(Produto p : produtos)
		{	
			elementosProduto[i][0] = p.getCod();
			elementosProduto[i][1] = p.getNome();
			elementosProduto[i][2] = p.getValorDeVenda();
			
			i++;
		}
		
		//ATUALIZAR TABELA
		tableProdutos.updateUI();
	}
	
	public void updateTable() {
		
		//FAZER CONSULTA NO BD
		ArrayList<Produto> produtos = PersistenciaEmBanco.pegarInstancia().getAllProdutos();
	
		//ATUALIZAR TABELA
		inserirTabela(produtos);
	}
	
	public void atualizarValores() {
		lblQuantidade.setText(""+quantidadeDeProdutos);
		lblSubTotal.setText(""+subtotal);
		lblValorTotal.setText(""+total);
	}
}
