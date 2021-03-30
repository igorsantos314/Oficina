package oficina.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import oficina.modelo.Produto;
import oficina.persistencia.PersistenciaEmBanco;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class TelaConsultarProdutos extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private String[] colunasTabela = {"Cod", "Nome", "Valor de Compra", "Valor de Venda", "Lucro por UND.", "Stock"};
	private final int QUANTIDADE_MAX_CONTAS = 100;
	private Object[][] elementos = new Object[QUANTIDADE_MAX_CONTAS][6];
	
	private JTextField tfNomeProduto;
	
	public TelaConsultarProdutos() {
		setResizable(false);
		
		setSize(1204,563);
		setTitle("CONSULTAR PRODUTOS");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 1159, 456);
		getContentPane().add(scrollPane);
		
		table = new JTable(elementos, colunasTabela);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		JLabel lblPlaca = new JLabel("NOME DO PRODUTO:");
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPlaca.setBounds(9, 19, 176, 14);
		getContentPane().add(lblPlaca);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setFont(new Font("Arial", Font.PLAIN, 14));
		tfNomeProduto.setBounds(166, 16, 640, 20);
		getContentPane().add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		JButton btConsultar = new JButton("CONSULTAR");
		btConsultar.setFont(new Font("Arial", Font.PLAIN, 14));
		btConsultar.setBackground(Color.LIGHT_GRAY);
		btConsultar.setForeground(Color.BLACK);
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomeProduto = tfNomeProduto.getText().toUpperCase();
				
				//FAZER CONSULTA NO BD
				ArrayList<Produto> produtos = (ArrayList<Produto>) PersistenciaEmBanco.pegarInstancia().getProdutoNome(nomeProduto);
				
				//POVOAR TABELA
				inserirTabela(produtos);
				
			}
		});
		
		btConsultar.setBounds(816, 15, 157, 23);
		getContentPane().add(btConsultar);
		
		JButton btnNewButton = new JButton("EDITAR PRODUTO");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEGAR O INDICE DA LINHA SELECIONADA
				int numberLine = table.getSelectedRow();
				
				if(numberLine >= 0) {
					
					//PEGAR O CODIGO DA OS
					String os_selecionada = table.getModel().getValueAt(numberLine,0).toString();
					
					try {
						//FECHAR JANELA
						dispose();
						
						//ABRIR JANELA DE EDIÇÃO
						new TelaEditarOrdemDeServico(os_selecionada);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "POR FAVOR, SELECIONE UMA OS!");
				}

			}
		});
		btnNewButton.setBounds(983, 15, 186, 23);
		getContentPane().add(btnNewButton);
		setVisible(true);
		
		//INICIALIZAR A TABELA
		this.updateTable();
		
		setVisible(true);
	}
	
	public void clearTable() {
		//LIMPAR CAMPOS
		for(int i = 0; i<QUANTIDADE_MAX_CONTAS; i++)
		{
			elementos[i][0] = "";
			elementos[i][1] = "";
			elementos[i][2] = "";
			elementos[i][3] = "";
			elementos[i][4] = "";
			elementos[i][5] = "";
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
			float compra = p.getValorDeCompra();
			float venda = p.getValorDeVenda();
					
			elementos[i][0] = p.getCod();
			elementos[i][1] = p.getNome();
			elementos[i][2] = compra;
			elementos[i][3] = venda;
			elementos[i][4] = Produto.formatarFloat(venda - compra);
			elementos[i][5] = p.getQuantidade();
			
			i++;
		}
		
		//ATUALIZAR TABELA
		table.updateUI();
	}
	
	public void updateTable() {
		
		//FAZER CONSULTA NO BD
		ArrayList<Produto> produtos = PersistenciaEmBanco.pegarInstancia().getAllProdutos();
	
		//ATUALIZAR TABELA
		inserirTabela(produtos);
	}

}
