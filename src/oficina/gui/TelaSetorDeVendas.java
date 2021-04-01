package oficina.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import oficina.facade.Conexao;
import oficina.modelo.Produto;
import oficina.modelo.ProdutoVendido;
import oficina.persistencia.PersistenciaEmBanco;

public class TelaSetorDeVendas extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable tableProdutos;
	private JTable tableVenda;
	private JScrollPane scrollPaneProduto;
	private JScrollPane scrollPaneVenda;
	
	private String[] colunasTabelaProduto = {"Codigo", "Nome", "Valor"};
	private int QUANTIDADE_MAX_CONTAS = 100;
	private Object[][] elementosProduto = new Object[QUANTIDADE_MAX_CONTAS][3];
	
	private String[] colunasTabelaVenda = {"Cod Venda", "Cod Produto", "Nome Produto", "Valor UND.", "Quantidade", "Valor Total", "Data"};
	private Object[][] elementosVenda = new Object[QUANTIDADE_MAX_CONTAS][7];
	private ArrayList<ProdutoVendido> listaDeCompra = new ArrayList<ProdutoVendido>();
	
	private JTextField tfProduto;
	private JButton btnAddProduto;
	private JButton btnRemove;
	private JLabel lblCodigoVenda;
	private JLabel lblCodVenda;
	private JLabel lblQuantidade;
	private JLabel lblQuant;
	private JLabel lblTotal;
	private JLabel lblValorTotal;
	private JButton btnFinalizarVenda;
	private JButton btEditar;
	private JButton btExcluir;
	
	private int quantidadeDeProdutos = 0;
	private float subtotal = 0f;
	private float total = 0f;
	
	private JTextField tfQuantidade;
	private JLabel lbl;
	private JLabel lblSubTotal;
	private JTextField tfCodigoVenda;
	private JButton btnNovaVenda;
	
	//DATA ATUAL DO COMPUTADOR
	private LocalDate localDate = LocalDate.now();
	private JButton btnImprimir;
	private JTextField tfCodigoVendaNavegar;
			
	//POSICAO DO CODIGO
	private int posicaoCodigoVenda = 1;
	private JButton btnAll;
	
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
		scrollPaneVenda.setBounds(10, 297, 1003, 405);
		getContentPane().add(scrollPaneVenda);
		
		tableVenda = new JTable(elementosVenda, colunasTabelaVenda);
		tableVenda.setFont(new Font("Arial", Font.PLAIN, 13));
		tableVenda.setForeground(Color.BLACK);
		tableVenda.setDefaultEditor(Object.class, null);
		scrollPaneVenda.setViewportView(tableVenda);
		
		JLabel lblNewLabel = new JLabel("BUSCAR PRODUTO:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(338, 19, 152, 14);
		getContentPane().add(lblNewLabel);
		
		tfProduto = new JTextField();
		tfProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		tfProduto.setBounds(484, 17, 416, 20);
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
		
		btnAddProduto = new JButton("ADD");
		btnAddProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEGAR O INDICE DA LINHA SELECIONADA
				int numberLine = tableProdutos.getSelectedRow();
				
				if(numberLine >= 0) {
					
					//PEGAR O CODIGO DA OS
					int codProd = Integer.parseInt(tableProdutos.getModel().getValueAt(numberLine,0).toString());
					String nome = tableProdutos.getModel().getValueAt(numberLine,1).toString();
					Float valor = Float.parseFloat(tableProdutos.getModel().getValueAt(numberLine,2).toString());
					int quant = Integer.parseInt(tfQuantidade.getText());
					float totalProduto = quant*valor;
					
					//ADICIONAR NOVO PRODUTO A LISTA
					listaDeCompra.add(new ProdutoVendido(Integer.parseInt(lblCodigoVenda.getText()), codProd, nome, valor, quant, localDate.toString()));
					
					//ATUALIZAR LISTA ELEMENTOS
					setTabelaVenda();
					
					quantidadeDeProdutos += quant;
					subtotal += valor;
					total += totalProduto;
					
					//ATUALIZAR VALORES
					atualizarValores();
					
					//ATUALIZA A TABELA
					tableVenda.updateUI();
					
					//RESETAR QUANTIDADE DE PRODUTO
					tfQuantidade.setText("1");
					
					//HABILITAR BOTAO DE REMOVER
					btnRemove.setEnabled(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "POR FAVOR, SELECIONE UMA PRODUTO!");
				}
			}
		});
		btnAddProduto.setEnabled(false);
		btnAddProduto.setBackground(Color.LIGHT_GRAY);
		btnAddProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAddProduto.setBounds(1023, 225, 118, 23);
		getContentPane().add(btnAddProduto);
		
		btnRemove = new JButton("REMOVER");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEGAR O INDICE DA LINHA SELECIONADA
				int numberLine = tableVenda.getSelectedRow();
				
				if(numberLine >= 0) {
					//PEGAR O CODIGO DA OS
					Float valor = Float.parseFloat(tableVenda.getModel().getValueAt(numberLine,3).toString());
					int quant = Integer.parseInt(tableVenda.getModel().getValueAt(numberLine,4).toString());
					float totalProduto = quant*valor;
					
					//ATUALIZAR VALORES APOS A REMO��O
					quantidadeDeProdutos -= quant;
					subtotal -= valor;
					total -= totalProduto;
					
					//REMOVER DA LISTA
					listaDeCompra.remove(numberLine);
					
					//ATUALIZAR TABELA
					setTabelaVenda();
					
					//ATUALIZAR VALORES
					atualizarValores();
				}
				else {
					JOptionPane.showMessageDialog(null, "POR FAVOR, SELECIONE UM ITEM DA LISTA COMPRA PARA REMOVER!");
				}
				
			}
		});
		btnRemove.setEnabled(false);
		btnRemove.setBackground(Color.LIGHT_GRAY);
		btnRemove.setFont(new Font("Arial", Font.PLAIN, 12));
		btnRemove.setBounds(1023, 399, 118, 23);
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
		
		btnFinalizarVenda = new JButton("FINALIZAR");
		btnFinalizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//VERIFICA SE A LISTA DE PRODUTO EST� VAZIA
				if(listaDeCompra.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "N�O H� NADA NA LISTA DE COMPRA!");
				}
				else{
					int resposta = JOptionPane.showConfirmDialog(null, "DESEJA FINALIZAR A VENDA?");
					
					//VERIFICAR SE O USUARIO DESEJA FINALIZAR A VENDA
	                if(resposta == 0)
	                {
	                	//CHAMAR TELA DE TROCO
	    				new TelaTroco(quantidadeDeProdutos, subtotal, total);
	    				
	                	//CHAMAR FUN��O PARA SALVAR TODOS OS DADOS DA VENDA
	    				salvarVenda();
	    				
	    				//TIRAR PRODUTOS DO STOCK
	    				atualizarStock("-");
	    				
	    				JOptionPane.showMessageDialog(null, "VENDA SALVA COM SUCESSO !");
	    				
	    				//VERIFICAR SE DESEJA IMPRIMIR A NOTA
	    				int respostaNota = JOptionPane.showConfirmDialog(null, "DESEJA IMPRIMIR NOTA?");
						
						//VERIFICAR SE O USUARIO DESEJA IMPRIMIR COMPROVANTE
		                if(respostaNota == 0)
		                {
		                	imprimirComprovante();
		                }
		                
		                //DESABILITAR BOT�O DE ADD PRODUTO
		                btnAddProduto.setEnabled(false);
		                
		              //DESABILITAR BOT�O DE REMOVER PRODUTO
		                btnRemove.setEnabled(false);
		                
	    				//LIMPAR TUDO
	    				resetarVenda();
	    				
	                }
	                
				}
				
			}
		});
		btnFinalizarVenda.setFont(new Font("Arial", Font.PLAIN, 12));
		btnFinalizarVenda.setBackground(Color.LIGHT_GRAY);
		btnFinalizarVenda.setBounds(1023, 679, 118, 23);
		getContentPane().add(btnFinalizarVenda);
		
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
		
		JLabel lblBuscarVendaPor = new JLabel("BUSCAR VENDA POR C\u00D3DIGO:");
		lblBuscarVendaPor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBuscarVendaPor.setBounds(10, 272, 234, 14);
		getContentPane().add(lblBuscarVendaPor);
		
		tfCodigoVenda = new JTextField();
		tfCodigoVenda.setFont(new Font("Arial", Font.PLAIN, 12));
		tfCodigoVenda.setColumns(10);
		tfCodigoVenda.setBounds(227, 270, 574, 20);
		getContentPane().add(tfCodigoVenda);
		
		JButton btBuscarVenda = new JButton("BUSCAR VENDA");
		btBuscarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//LIMPAR LISTA DE PRODUTOS
				listaDeCompra.clear();
				
				String id = tfCodigoVenda.getText();
				
				if(id.equals("")) {
					JOptionPane.showMessageDialog(null, "DIGITE O ID DE UMA VENDA PARA BUSCAR!!");
				}
				else {
					int idVenda = Integer.parseInt(id);
					listaDeCompra = PersistenciaEmBanco.pegarInstancia().getVendaID(idVenda);
					
					if(listaDeCompra.isEmpty()) {
						
						//N�O FOI ENCONTRADA NENHUMA VENDA
						JOptionPane.showMessageDialog(null, "NENHUMA VENDA ENCONTRADA!");
						
						//LIMPAR CAMPO DE DIGITAR ID
						tfCodigoVenda.setText("");
					}
					else {
						
						//SETAR C�DIGO DA VENDA BUSCADA
						lblCodigoVenda.setText(""+idVenda);
						
						//SETAR NOVA LISTA DE VENDA
						setarVenda();
						
						//DESABILITAR INSERIR PRODUTO
						btnAddProduto.setEnabled(false);
						
						//HABILITAR EDITAR
						btEditar.setEnabled(true);
						
						//HABILITAR EXCLUIR
						btExcluir.setEnabled(true);
						
						//HABILITAR BOT�O DE IMPRESS�O
						btnImprimir.setEnabled(true);
						
						//DESABILITAR FINALIZAR COMPRA
						btnFinalizarVenda.setEnabled(false);
						
						//DESABILITAR REMOVE ITEM DA LISTA DE COMPRA
						btnRemove.setEnabled(false);
		
					}
				}
				
			}
		});
		btBuscarVenda.setFont(new Font("Arial", Font.PLAIN, 12));
		btBuscarVenda.setBackground(Color.LIGHT_GRAY);
		btBuscarVenda.setBounds(811, 269, 204, 23);
		getContentPane().add(btBuscarVenda);
		
		btnNovaVenda = new JButton("NOVA VENDA");
		btnNovaVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resposta = JOptionPane.showConfirmDialog(null, "DESEJA INICIAR UMA NOVA VENDA?");
				
				//VERIFICAR SE O USUARIO DESEJA INICIAR NOVA VENDA
                if(resposta == 0)
                {
                	//RESETAR TODOS OS CAMPOS E TABELA
                	resetarVenda();
                	
                	//DESABILITAR EDITAR
                	btEditar.setEnabled(false);
                	
                	//DESABILITAR EXCLUIR
					btExcluir.setEnabled(false);
                	
                	//HABILITAR BOT�O DE FINALIZAR VENDA
                	btnFinalizarVenda.setEnabled(true);
                	
                	//HABILITAR BOT�O DE ADD
                	btnAddProduto.setEnabled(true);
                	
                	//HABILITAR BOT�O DE REMOVER DA LISTA DE VENDA
    				btnRemove.setEnabled(false);
    				
    				//DESABILITAR BOT�O DE IMPRESS�O
    				btnImprimir.setEnabled(false);
    				
    				//ATUALIZAR COD DE VENDA
    				setarIDVenda();
                }
                
			}
		});
		btnNovaVenda.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNovaVenda.setBackground(Color.LIGHT_GRAY);
		btnNovaVenda.setBounds(1023, 365, 118, 23);
		getContentPane().add(btnNovaVenda);
		
		btEditar = new JButton("EDITAR");
		btEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(btEditar.getText().equals("EDITAR") && listaDeCompra.isEmpty() == false) {
					//MODIFICAR TEXTO DO BOT�O
					btEditar.setText("SALVAR");
					
					//HABILITAR BOT�O DE ADICIONAR ITEM
					btnAddProduto.setEnabled(true);
					
					//HABILITAR BOT�O DE REMOVER ITEM
					btnRemove.setEnabled(true);
				}
				else {
					
					int resposta = JOptionPane.showConfirmDialog(null, "DESEJA SALVAR ESTA EDI��O?");
					
					//VERIFICAR SE O USUARIO DESEJA SALVAR EDI��O
	                if(resposta == 0)
	                {	
	                	System.out.println(listaDeCompra.get(0).getCodVenda());
	                	//APAGAR VENDA
						PersistenciaEmBanco.pegarInstancia().deleteVenda(listaDeCompra.get(0).getCodVenda());
						
						//SALVAR VENDA
						PersistenciaEmBanco.pegarInstancia().cadastrarVenda(listaDeCompra);
						
						//MENSAGEM DE SUCESSO
						JOptionPane.showMessageDialog(null, "EDITADO COM SUCESSO!");
						
						//SALVAR AS ALTERA��ES
						btEditar.setText("EDITAR");
						
						//ATUALIZAR COD DE VENDA
						setarIDVenda();
						
						//RESETAR TODOS OS DADOS DA VENDA
						resetarVenda();
						
						//DESABILITAR BOT�ES
						btnAddProduto.setEnabled(false);
						btnRemove.setEnabled(false);
						btEditar.setEnabled(false);
						btExcluir.setEnabled(false);
						
						//DESABILITAR BOT�O DE IMPRESS�O
	    				btnImprimir.setEnabled(false);
	                }
					
				}
				
			}
		});
		btEditar.setEnabled(false);
		btEditar.setFont(new Font("Arial", Font.PLAIN, 12));
		btEditar.setBackground(Color.LIGHT_GRAY);
		btEditar.setBounds(1023, 300, 118, 23);
		getContentPane().add(btEditar);
		
		btExcluir = new JButton("EXCLUIR");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int codVenda = (int) elementosVenda[0][0];
				
				int resposta = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR ESSA VENDA?");
				
				//VERIFICAR SE O USUARIO DESEJA INICIAR NOVA VENDA
                if(resposta == 0)
                {
					//EXCLUIR TODAS AS PRODUTOS COM O CODIGO DE VENDA
					PersistenciaEmBanco.pegarInstancia().deleteVenda(codVenda);
					
					//REPOR ITENS NO STOCK
    				atualizarStock("+");
					
					//RESETAR TABELA
					resetarVenda();
					
					//DESABILITAR BOTAO DE EXCLUIR
					btExcluir.setEnabled(false);
					
					//ATUALIZAR ID
					setarIDVenda();
					
					//RESETAR TODOS OS DADOS DA VENDA
					resetarVenda();
					
					//DESABILITAR BOT�O DE EDI��O
					btEditar.setEnabled(false);
					
					JOptionPane.showMessageDialog(null, "VENDA EXCLUIDA COM SUCESSO !");
                }
			}
		});
		btExcluir.setEnabled(false);
		btExcluir.setFont(new Font("Arial", Font.PLAIN, 12));
		btExcluir.setBackground(Color.LIGHT_GRAY);
		btExcluir.setBounds(1023, 331, 118, 23);
		getContentPane().add(btExcluir);
		
		btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.setEnabled(false);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CHAMAR IMPRESS�O DE LISTA DE COMPRA
				imprimirComprovante();
			}
		});
		btnImprimir.setFont(new Font("Arial", Font.PLAIN, 12));
		btnImprimir.setBackground(Color.LIGHT_GRAY);
		btnImprimir.setBounds(1023, 645, 118, 23);
		getContentPane().add(btnImprimir);
		
		JLabel lblNewLabel_2 = new JLabel("NAVEGAR:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(1023, 471, 118, 14);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnPrev = new JButton("<");
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//N�O NAVEGAR POR NUMEROS NEGATIVOS
				if(posicaoCodigoVenda >= 2) {
					//AVAN�AR CODIGO DE NAVEGA��O
					posicaoCodigoVenda -= 1;
					
					//ATUALIZAR CODIGO
					changeCodigoNavegar();
				}
				
			}
		});
		btnPrev.setFont(new Font("Arial", Font.BOLD, 12));
		btnPrev.setBackground(Color.LIGHT_GRAY);
		btnPrev.setBounds(1023, 531, 59, 23);
		getContentPane().add(btnPrev);
		
		JButton btnNext = new JButton(">");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AVAN�AR CODIGO DE NAVEGA��O
				posicaoCodigoVenda += 1;
				
				//ATUALIZAR CODIGO
				changeCodigoNavegar();
			}
		});
		btnNext.setFont(new Font("Arial", Font.BOLD, 12));
		btnNext.setBackground(Color.LIGHT_GRAY);
		btnNext.setBounds(1082, 531, 59, 23);
		getContentPane().add(btnNext);
		
		tfCodigoVendaNavegar = new JTextField();
		tfCodigoVendaNavegar.setEditable(false);
		tfCodigoVendaNavegar.setFont(new Font("Arial", Font.BOLD, 25));
		tfCodigoVendaNavegar.setBounds(1023, 486, 118, 34);
		getContentPane().add(tfCodigoVendaNavegar);
		tfCodigoVendaNavegar.setColumns(10);
		
		btnAll = new JButton("<    >");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PEGAR TODOS OS PRODUTOS VENDIDOS
				listaDeCompra = PersistenciaEmBanco.pegarInstancia().getAllVendas();
				
				//MODIFICAR A QUANTIDA DE TUPLAS DA TABELA
				QUANTIDADE_MAX_CONTAS = 10000;
				
				//SETAR NOVA LISTA DE VENDA
				setarVenda();
				
				//LIMPAR CAMPO DE NAVEGAR
				tfCodigoVendaNavegar.setText("");
				
				//DESABILITAR BOTAO DE EDITAR
				btEditar.setEnabled(false);
				
				//DESEBALITAR BOTAO DE FINALIZAR A VENDA
				btnFinalizarVenda.setEnabled(false);
				
				//DESEBALITAR BOTAO DE REMOVER DA LISTA DE VENDA
				btnRemove.setEnabled(false);
				
				//DESABILITAR BOT�O DE ADICIONAR PRODUTO A LISTA
				btnAddProduto.setEnabled(false);
			}
		});
		btnAll.setFont(new Font("Arial", Font.BOLD, 12));
		btnAll.setBackground(Color.LIGHT_GRAY);
		btnAll.setBounds(1023, 559, 118, 23);
		getContentPane().add(btnAll);
		
		//SETAR ID DA VENDA
		setarIDVenda();
		
		//INICIALIZAR TABELA DE PRODUTOS
		updateTable();
		
		//SETAR CODIGO DE NAVEGA��O
		//changeCodigoNavegar();
		
		setVisible(true);
	}
	
	public void changeCodigoNavegar() {
		this.tfCodigoVendaNavegar.setText(""+posicaoCodigoVenda);
		
		//PEGAR VENDA COM O CODIGO DESEJADO
		listaDeCompra = PersistenciaEmBanco.pegarInstancia().getVendaID(posicaoCodigoVenda);
		
		//SETAR NOVA LISTA DE VENDA
		setarVenda();
	}
	
	public void setarVenda() {
		//RESETAR VALORES
		quantidadeDeProdutos = 0;
		subtotal = 0;
		total = 0;
		
		//PEGAR VALORES PARA EXIBIR
		for(ProdutoVendido pv : listaDeCompra) {
			quantidadeDeProdutos += pv.getQuantidade();
			subtotal += pv.getValorUnd();
			total += pv.getValorTotal();
		}
		
		//ATUALIZAR TABELA
		setTabelaVenda();
		
		//ATUALIZAR VALORES
		atualizarValores();
	}
	
	public void resetarVenda() {
		//RESETAR VALORES
		quantidadeDeProdutos = 0;
		subtotal = 0;
		total = 0;
		
		//LIMPAR CAMPO DE BUSCAR VENDA
		tfCodigoVenda.setText("");
		
		//LIMPAR LISTA DE PRODUTOS
		listaDeCompra.clear();
		
		//ATUALIZAR TABELA
		setTabelaVenda();
		
		//ATUALIZAR VALORES
		atualizarValores();
	}
	
	public void setarIDVenda() {
		//PEGAR A QUANTIDADE DE INSTANCIAS E SOMAR UM
		int id = PersistenciaEmBanco.pegarInstancia().getLastIdVenda() + 1;
		
		lblCodigoVenda.setText(""+id);
	}
	
	public void salvarVenda() {
		
		//SALVAR NO BANCO DE DADOS
		PersistenciaEmBanco.pegarInstancia().cadastrarVenda(listaDeCompra);
		
		//DESATIVAR BOT�O DE FINALIZAR
		btnFinalizarVenda.setEnabled(false);
		
		//ATUALIZAR COD DE VENDA
		setarIDVenda();
	}
	
	public void clearTableVenda() {
		//LIMPAR CAMPOS
		for(int i = 0; i<QUANTIDADE_MAX_CONTAS; i++)
		{
			elementosVenda[i][0] = "";
			elementosVenda[i][1] = "";
			elementosVenda[i][2] = "";
			elementosVenda[i][3] = "";
			elementosVenda[i][4] = "";
			elementosVenda[i][5] = "";
			elementosVenda[i][6] = "";
		}
	}
	
	public void setTabelaVenda() {
		
		//LIMPAR TABELA
		clearTableVenda();
		
		//ITERAR LINHAS
		int i = 0;
		
		for(ProdutoVendido pV : listaDeCompra)
		{
			elementosVenda[i][0] = pV.getCodVenda();
			elementosVenda[i][1] = pV.getCodProd();
			elementosVenda[i][2] = pV.getNome();
			elementosVenda[i][3] = pV.getValorUnd();
			elementosVenda[i][4] = pV.getQuantidade();
			elementosVenda[i][5] = pV.getValorTotal();
			elementosVenda[i][6] = pV.getData();
			
			i++;
		}
		
		//ATUALIZAR TABELA
		tableVenda.updateUI();
		
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
		
		//ATUALIZAR INFORMA��ES DE VALORES
		lblQuantidade.setText(""+quantidadeDeProdutos);
		lblSubTotal.setText(""+subtotal);
		lblValorTotal.setText(""+total);
	}
	
	public void atualizarStock(String opc) {
		
		for(ProdutoVendido pv : listaDeCompra) {
			//ATUALIZAR STOCK DE PRODUTOS VENDIDOS
			PersistenciaEmBanco.pegarInstancia().updateStockProduto(pv, opc);
		}
	}
	
	public void imprimirComprovante() {
		//CHAMAR CONEX�O ENVIANDO A LISTA DE COMPRA
		Conexao.pegarInstancia().imprimirVenda(listaDeCompra);
	}
}
