package oficina.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import oficina.facade.Conexao;
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
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;

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
	private JTextField tfNome;
	private JTextField tfValorCompra;
	private JTextField tfValorVenda;
	private JTextField tfQuantidade;
	
	private JButton btnFechar;
	private JButton btnExcluirCliente;
	private JButton btnSalvarAlteracoes;
	private JButton btnEditar;
	private JLabel lblCod;
	private JTextField tfCod;
	
	public TelaConsultarProdutos() {
		setResizable(false);
		
		setSize(1204,755);
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
		
		btnEditar = new JButton("EDITAR PRODUTO");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEditar.setBackground(Color.LIGHT_GRAY);
		btnEditar.setForeground(Color.BLACK);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEGAR O INDICE DA LINHA SELECIONADA
				int numberLine = table.getSelectedRow();
				
				if(numberLine >= 0) {
					
					//PEGAR O CPF DO CLIENTE
					String cpf = table.getModel().getValueAt(numberLine,0).toString();
					
					//VERIFICAR SE O USUARIO SELECIONOU UMA LINHA EM BRANCO
					if(cpf.equals("")) {
						JOptionPane.showMessageDialog(null, "POR FAVOR, SELECIONE UM CLIENTE!");
					}
					else {
						//PEGAR CAMPOS
						String cod = table.getModel().getValueAt(numberLine,0).toString();
						String nome = table.getModel().getValueAt(numberLine,1).toString();
						String valorCompra = table.getModel().getValueAt(numberLine,2).toString();
						String valorVenda = table.getModel().getValueAt(numberLine,3).toString();
						String quantidade = table.getModel().getValueAt(numberLine,5).toString();
						
						//SETAR CAMPOS
						tfCod.setText(cod);
						tfNome.setText(nome);
						tfValorCompra.setText(valorCompra);
						tfValorVenda.setText(valorVenda);
						tfQuantidade.setText(quantidade);
						
						//HABILITAR CAMPOS
						tfNome.setEnabled(true);
						tfValorCompra.setEnabled(true);
						tfValorVenda.setEnabled(true);
						tfQuantidade.setEnabled(true);
						
						//HABILITAR BOTOES
						btnFechar.setEnabled(true);
						btnSalvarAlteracoes.setEnabled(true);
						btnExcluirCliente.setEnabled(true);
						
						//DESABILITAR BOTÃO DE EDIDAR
						btnEditar.setEnabled(false);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "POR FAVOR, SELECIONE UM PRODUTO!");
				}

			}
		});
		btnEditar.setBounds(983, 15, 186, 23);
		getContentPane().add(btnEditar);
		
		JLabel lblNewLabel = new JLabel("Nome do Produto:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 576, 148, 19);
		getContentPane().add(lblNewLabel);
		
		tfNome = new JTextField();
		tfNome.setEnabled(false);
		tfNome.setHorizontalAlignment(SwingConstants.CENTER);
		tfNome.setFont(new Font("Arial", Font.PLAIN, 16));
		tfNome.setColumns(10);
		tfNome.setBounds(149, 572, 475, 28);
		getContentPane().add(tfNome);
		
		JLabel lblValorDeCompra = new JLabel("Valor de Compra:");
		lblValorDeCompra.setFont(new Font("Arial", Font.PLAIN, 16));
		lblValorDeCompra.setBounds(10, 615, 148, 19);
		getContentPane().add(lblValorDeCompra);
		
		tfValorCompra = new JTextField();
		tfValorCompra.setEnabled(false);
		tfValorCompra.setHorizontalAlignment(SwingConstants.CENTER);
		tfValorCompra.setFont(new Font("Arial", Font.PLAIN, 16));
		tfValorCompra.setColumns(10);
		tfValorCompra.setBounds(149, 612, 475, 28);
		getContentPane().add(tfValorCompra);
		
		JLabel lblValorDeVenda = new JLabel("Valor de Venda:");
		lblValorDeVenda.setFont(new Font("Arial", Font.PLAIN, 16));
		lblValorDeVenda.setBounds(640, 576, 148, 19);
		getContentPane().add(lblValorDeVenda);
		
		tfValorVenda = new JTextField();
		tfValorVenda.setEnabled(false);
		tfValorVenda.setHorizontalAlignment(SwingConstants.CENTER);
		tfValorVenda.setFont(new Font("Arial", Font.PLAIN, 16));
		tfValorVenda.setColumns(10);
		tfValorVenda.setBounds(779, 572, 390, 28);
		getContentPane().add(tfValorVenda);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(640, 615, 148, 19);
		getContentPane().add(lblNewLabel_1);
		
		tfQuantidade = new JTextField();
		tfQuantidade.setEnabled(false);
		tfQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		tfQuantidade.setFont(new Font("Arial", Font.PLAIN, 16));
		tfQuantidade.setColumns(10);
		tfQuantidade.setBounds(779, 611, 390, 28);
		getContentPane().add(tfQuantidade);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 660, 1159, 39);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnFechar = new JButton("FECHAR");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DESABILITAR CAMPOS
				clearCamps();
			}
		});
		btnFechar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnFechar.setEnabled(false);
		btnFechar.setBackground(Color.LIGHT_GRAY);
		panel.add(btnFechar);
		
		btnExcluirCliente = new JButton("EXCLUIR");
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR O PRODUTO: " + tfNome.getText() + " ?");
				
				//VERIFICAR SE O USUARIO DESEJA EXCLUIR O PRODUTO
                if(resposta == 0)
                {
					//EXCLUIR PRODUTO
					PersistenciaEmBanco.pegarInstancia().deleteProduto(Integer.parseInt(tfCod.getText()));
					
					//ATUALIZAR TABELA
					updateTable();
					
					//LIMPAR CAMPOS
					clearCamps();
                }
			}
		});
		btnExcluirCliente.setFont(new Font("Arial", Font.PLAIN, 16));
		btnExcluirCliente.setEnabled(false);
		btnExcluirCliente.setBackground(Color.LIGHT_GRAY);
		panel.add(btnExcluirCliente);
		
		btnSalvarAlteracoes = new JButton("SALVAR");
		btnSalvarAlteracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resposta = JOptionPane.showConfirmDialog(null, "DESEJA EDITAR ESTE PRODUTO?");
				
				//VERIFICAR SE O USUARIO DESEJA EDITAR O PRODUTO
                if(resposta == 0)
                {
                	int cod = Integer.parseInt(tfCod.getText());
    				Float valorCompra = Float.parseFloat(tfValorCompra.getText());
    				Float valorVenda = Float.parseFloat(tfValorVenda.getText());
    				int quant = Integer.parseInt(tfQuantidade.getText());
    				
    				//CHAMAR CONEXAO PARA ATUALIZAR PRODUTO
    				Conexao.pegarInstancia().atualizarProduto(cod, tfNome.getText(), valorCompra, valorVenda, quant);
    				
    				//MENSAGEM DE SUCESSO
    				JOptionPane.showMessageDialog(null, "EDITADO COM SUCESSO !");
    				
    				//ATUALIZAR TABELA
    				updateTable();
    				
    				//LIMPAR CAMPOS
    				clearCamps();
                }
				
			}
		});
		btnSalvarAlteracoes.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSalvarAlteracoes.setEnabled(false);
		btnSalvarAlteracoes.setBackground(Color.LIGHT_GRAY);
		panel.add(btnSalvarAlteracoes);
		
		lblCod = new JLabel("C\u00F3digo Produto:");
		lblCod.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCod.setBounds(10, 531, 148, 19);
		getContentPane().add(lblCod);
		
		tfCod = new JTextField();
		tfCod.setEditable(false);
		tfCod.setHorizontalAlignment(SwingConstants.CENTER);
		tfCod.setFont(new Font("Arial", Font.BOLD, 16));
		tfCod.setColumns(10);
		tfCod.setBounds(149, 528, 1020, 28);
		getContentPane().add(tfCod);
		setVisible(true);
		
		//INICIALIZAR A TABELA
		this.updateTable();
		
		setVisible(true);
	}
	
	public void clearCamps() {
		
		//LIMPAR CAMPOS
		tfCod.setText("");
		tfNome.setText("");
		tfValorCompra.setText("");
		tfValorVenda.setText("");
		tfQuantidade.setText("");
		
		//DESABILITAR CAMPOS
		tfNome.setEnabled(false);
		tfValorCompra.setEnabled(false);
		tfValorVenda.setEnabled(false);
		tfQuantidade.setEnabled(false);
		
		//DESABILITAR BOTOES
		btnFechar.setEnabled(false);
		btnExcluirCliente.setEnabled(false);
		btnSalvarAlteracoes.setEnabled(false);
		btnEditar.setEnabled(true);
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
