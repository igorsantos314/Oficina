package oficina.gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;

import oficina.facade.Conexao;
import oficina.modelo.OrdemDeServico;
import oficina.modelo.ProdutoVendido;
import oficina.persistencia.PersistenciaEmBanco;
import oficina.types.PagamentoTypes;
import oficina.types.StatusTypes;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class TelaEditarOrdemDeServico extends JDialog{
	/**
	 * 
	 */ 
	private static final long serialVersionUID = 1L;
	
	private JTextField tfCod;
	private JTextField tfValorMaoDeObra;
	private JTextArea taDescricao;
	private JTextField tfCliente;
	private JTextField tfPlaca;
	private JFormattedTextField tfSaida;
	private JFormattedTextField tfEntrada;
	private JComboBox<PagamentoTypes> cbPagamento;
	private JComboBox<StatusTypes> cbStatus;
	private JTextField tfValorPecas;
	
	private JScrollPane scrollPaneVenda;
	private JTable tableVenda;
	
	private int QUANTIDADE_MAX_CONTAS = 100;
	private String[] colunasTabelaVenda = {"Cod Venda", "Cod Produto", "Nome Produto", "Valor UND.", "Quantidade", "Valor Total", "Data"};
	private Object[][] elementosVenda = new Object[QUANTIDADE_MAX_CONTAS][7];
	private ArrayList<ProdutoVendido> listaDeCompra = new ArrayList<ProdutoVendido>();
	
	private JTextField tfCodigoVenda;

	private JTextArea taLaudo;
	
	public TelaEditarOrdemDeServico(String cod) throws ParseException {
		
		setResizable(false);
		
		setSize(1166,755);
		setTitle("EDITAR ORDEM DE SERVIÇO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setModal(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 1121, 119);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCod = new JLabel("Cod:");
		lblCod.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCod.setBounds(10, 11, 46, 14);
		panel.add(lblCod);
		
		tfCod = new JTextField();
		tfCod.setFont(new Font("Arial", Font.PLAIN, 13));
		tfCod.setEditable(false);
		tfCod.setBounds(10, 28, 75, 20);
		panel.add(tfCod);
		tfCod.setColumns(10);
		
		JLabel lblDataEntrada = new JLabel("Data Entrada:");
		lblDataEntrada.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDataEntrada.setBounds(95, 11, 218, 14);
		panel.add(lblDataEntrada);
		
		JLabel lblSaida = new JLabel("Data Sa\u00EDda:");
		lblSaida.setFont(new Font("Arial", Font.PLAIN, 13));
		lblSaida.setBounds(323, 11, 230, 14);
		panel.add(lblSaida);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCliente.setBounds(10, 59, 58, 14);
		panel.add(lblCliente);
		
		JLabel lblPlaca = new JLabel("Veiculo:");
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPlaca.setBounds(563, 59, 249, 14);
		panel.add(lblPlaca);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 141, 1121, 220);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		taDescricao = new JTextArea();
		taDescricao.setFont(new Font("Arial", Font.PLAIN, 13));
		taDescricao.setBounds(10, 30, 555, 179);
		panel_1.add(taDescricao);
		
		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O DO CLIENTE:");
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDescricao.setBounds(10, 11, 536, 14);
		panel_1.add(lblDescricao);
		
		taLaudo = new JTextArea();
		taLaudo.setFont(new Font("Arial", Font.PLAIN, 13));
		taLaudo.setText((String) null);
		taLaudo.setBounds(575, 30, 536, 179);
		panel_1.add(taLaudo);
		
		JLabel lblLaudoTecnico = new JLabel("LAUDO TECNICO:");
		lblLaudoTecnico.setFont(new Font("Arial", Font.PLAIN, 13));
		lblLaudoTecnico.setBounds(575, 11, 536, 14);
		panel_1.add(lblLaudoTecnico);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 546, 1121, 85);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Status:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel_2.add(lblNewLabel);
		
		cbStatus = new JComboBox<StatusTypes>();
		cbStatus.setFont(new Font("Arial", Font.BOLD, 28));
		cbStatus.setBackground(Color.WHITE);
		cbStatus.setModel(new DefaultComboBoxModel<>(StatusTypes.values()));
		cbStatus.setBounds(10, 27, 337, 47);
		panel_2.add(cbStatus);
		
		JLabel lblPagamento = new JLabel("Forma de Pagamento:");
		lblPagamento.setBounds(357, 11, 270, 14);
		panel_2.add(lblPagamento);
		
		cbPagamento = new JComboBox<PagamentoTypes>();
		cbPagamento.setFont(new Font("Arial", Font.BOLD, 28));
		cbPagamento.setBackground(Color.WHITE);
		cbPagamento.setModel(new DefaultComboBoxModel<>(PagamentoTypes.values()));
		cbPagamento.setBounds(357, 27, 336, 47);
		panel_2.add(cbPagamento);
		
		JLabel lblValor = new JLabel("Valor M\u00E3o de Obra");
		lblValor.setBounds(705, 11, 198, 14);
		panel_2.add(lblValor);
		
		tfValorMaoDeObra = new JTextField();
		tfValorMaoDeObra.setForeground(Color.RED);
		tfValorMaoDeObra.setFont(new Font("Arial", Font.BOLD, 28));
		tfValorMaoDeObra.setBounds(703, 28, 198, 45);
		panel_2.add(tfValorMaoDeObra);
		tfValorMaoDeObra.setColumns(10);
		
		tfValorPecas = new JTextField();
		tfValorPecas.setEditable(false);
		tfValorPecas.setForeground(Color.BLUE);
		tfValorPecas.setFont(new Font("Arial", Font.BOLD, 28));
		tfValorPecas.setBounds(913, 28, 198, 45);
		panel_2.add(tfValorPecas);
		tfValorPecas.setColumns(10);
		
		JLabel lblValorEmPeas = new JLabel("Valor em Pe\u00E7as:");
		lblValorEmPeas.setBounds(913, 13, 198, 14);
		panel_2.add(lblValorEmPeas);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(10, 642, 1121, 54);
		getContentPane().add(panel_3);
		
		tfCliente = new JTextField();
		tfCliente.setFont(new Font("Arial", Font.BOLD, 25));
		tfCliente.setEditable(false);
		tfCliente.setBounds(10, 71, 543, 37);
		panel.add(tfCliente);
		tfCliente.setColumns(10);
		
		tfPlaca = new JTextField();
		tfPlaca.setFont(new Font("Arial", Font.BOLD, 25));
		tfPlaca.setEditable(false);
		tfPlaca.setBounds(563, 71, 548, 37);
		panel.add(tfPlaca);
		tfPlaca.setColumns(10);
		
		tfEntrada = new JFormattedTextField();
		tfEntrada.setFont(new Font("Arial", Font.PLAIN, 13));
		tfEntrada.setBounds(95, 28, 218, 20);
		panel.add(tfEntrada);
		
		tfSaida = new JFormattedTextField();
		tfSaida.setFont(new Font("Arial", Font.PLAIN, 13));
		tfSaida.setBounds(323, 28, 230, 20);
		panel.add(tfSaida);
		
		JButton btnEditar = new JButton("EDITAR OS");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEditar.setBackground(Color.LIGHT_GRAY);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String descricao = taDescricao.getText().toUpperCase();
				String laudo = taLaudo.getText().toUpperCase();
				String codigo = tfCodigoVenda.getText();
				Float valorMaoDeObra = Float.parseFloat(tfValorMaoDeObra.getText());
				Float valorPecas = Float.parseFloat(tfValorPecas.getText());
				String data_Entrada = tfEntrada.getText();
				String data_Saida = tfSaida.getText();
				String pagamento = cbPagamento.getSelectedItem().toString();
				String status = cbStatus.getSelectedItem().toString();
				String nome_Cliente = tfCliente.getText();
				
				int resposta = JOptionPane.showConfirmDialog(null, "DESEJA REALMENTE EDITAR ESTÁ OS?");
				
				//VERIFICAR SE O USUARIO DESEJA ATUALIZAR O ORDEM DE SERVICO
                if(resposta == 0)
                {
                	//ATUALIZAR DADOS
                	Conexao.pegarInstancia().atualizarOS(cod, descricao, laudo, codigo, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, cod, nome_Cliente);
                    
                	//MENSAGEM DE SUCESSO
    				JOptionPane.showMessageDialog(null, "Editado com Sucesso!");
                	
    				//FECHAR A TELA DE EDICAO
    				dispose();
    				
    				//EXIBIR JANELA COM TODOS OS DADOS
    				new TelaConsultarOrdemDeServico();
                }
                else
                {
                	
                }
			}
		});
		panel_3.add(btnEditar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FECHAR JANELA
				dispose();
				
				//EXIBIR JANELA COM TODOS OS DADOS
				new TelaConsultarOrdemDeServico();
			}
		});
		panel_3.add(btnCancelar);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 14));
		btnExcluir.setBackground(Color.LIGHT_GRAY);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resposta = JOptionPane.showConfirmDialog(null, "DESEJA REALMENTE EXCLUIR ESTÁ OS?");
				
				//VERIFICAR SE O USUARIO DESEJA EXCLUIR O CADASTRO
                if(resposta == 0)
                {
                	//DELETAR OS
                    PersistenciaEmBanco.pegarInstancia().deleteOS(cod);
                    
                    //MENSAGEM DE SUCESSO
    				JOptionPane.showMessageDialog(null, "ORDEM DE SERVIÇO EXCLUIDA!");
    				
    				//INCIALIZA O OBJETO PARA ATUALIZAR A TABELA
    				new TelaConsultarOrdemDeServico();
    				
    				//FECHAR JANELA
    				dispose();
    				
    				//EXIBIR JANELA COM TODOS OS DADOS
    				new TelaConsultarOrdemDeServico();
                }
                else
                {
                
                }
			}
		});
		panel_3.add(btnExcluir);
		
		JButton btImprimir = new JButton("IMPRIMIR");
		btImprimir.setFont(new Font("Arial", Font.PLAIN, 14));
		btImprimir.setBackground(Color.LIGHT_GRAY);
		btImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEGAR DADOS DOS CAMPOS
				String descricao = taDescricao.getText().toUpperCase();
				String laudo = taLaudo.getText().toUpperCase();
				String codigo = tfCodigoVenda.getText();
				Float valorMaoDeObra = Float.parseFloat(tfValorMaoDeObra.getText());
				Float valorPecas = Float.parseFloat(tfValorPecas.getText());
				String data_Entrada = tfEntrada.getText();
				String data_Saida = tfSaida.getText();
				String pagamento = cbPagamento.getSelectedItem().toString();
				String status = cbStatus.getSelectedItem().toString();
				String placa = tfPlaca.getText();
				String nome_Cliente = tfCliente.getText();
				
				//IMPRIMIR OS
				Conexao.pegarInstancia().imprimirOS(descricao, laudo, codigo, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, placa, nome_Cliente, listaDeCompra);
				
			}
		});
		panel_3.add(btImprimir);

		//SETAR MASCARAS
		MaskFormatter mfDataE = new MaskFormatter("##/##/####");
		mfDataE.install(tfEntrada);
		
		MaskFormatter mfDataS = new MaskFormatter("##/##/####");
		mfDataS.install(tfSaida);
		
		//CRIAR OBJETO DE ORDEM DE SERVICO
		OrdemDeServico os = PersistenciaEmBanco.pegarInstancia().getOSCod(cod);
		
		tfEntrada.setText(os.getData_Entrada());
		tfSaida.setText(os.getData_Saida());
		
		//VENDA
		scrollPaneVenda = new JScrollPane();
		scrollPaneVenda.setBounds(10, 388, 916, 147);
		getContentPane().add(scrollPaneVenda);
		
		tableVenda = new JTable(elementosVenda, colunasTabelaVenda);
		tableVenda.setFont(new Font("Arial", Font.PLAIN, 13));
		tableVenda.setForeground(Color.BLACK);
		tableVenda.setDefaultEditor(Object.class, null);
		scrollPaneVenda.setViewportView(tableVenda);
		
		JLabel lblNewLabel_1 = new JLabel("LISTA DE PRODUTOS:");
		lblNewLabel_1.setBounds(10, 373, 253, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("COD. DA VENDA:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(936, 388, 195, 21);
		getContentPane().add(lblNewLabel_2);
		
		tfCodigoVenda = new JTextField();
		tfCodigoVenda.setEditable(false);
		tfCodigoVenda.setFont(new Font("Arial", Font.BOLD, 50));
		tfCodigoVenda.setForeground(Color.RED);
		tfCodigoVenda.setBounds(936, 412, 195, 89);
		getContentPane().add(tfCodigoVenda);
		tfCodigoVenda.setColumns(10);
		
		JButton btEditarCodigo = new JButton("EDITAR C\u00D3DIGO");
		btEditarCodigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//MUDAR O ESTADO DE EDIÇÃO DO CAMPO DE CODIGO VENDA
				if(tfCodigoVenda.isEditable()) {
					tfCodigoVenda.setEditable(false);
					btEditarCodigo.setText("EDITAR CÓDIGO");
					
					if(tfCodigoVenda.getText().equals("")) {
						//ZERAR VALOR DE PEÇAS E TABELA
						clearTableVenda();
						tfValorPecas.setText("0.00");
						
						//ATUALIZAR TABELA
						tableVenda.updateUI();
					}
					else {
						//ATUALIZAR TABELA E VALORES
						atualizarTabela();

					}
				}
				else {
					tfCodigoVenda.setEditable(true);
					btEditarCodigo.setText("SALVAR");
				}
			}
		});
		btEditarCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
		btEditarCodigo.setBackground(Color.LIGHT_GRAY);
		btEditarCodigo.setForeground(Color.BLACK);
		btEditarCodigo.setBounds(936, 512, 195, 23);
		getContentPane().add(btEditarCodigo);
				
		//CALL FUNCAO DE SETAR DADOS
		setDados(os);
		
		setModal(true);
		setVisible(true);
	}
	
	public void setDados(OrdemDeServico os) {
		
		//SETAR CAMPOS
		this.tfCod.setText(os.getCod());
		this.taDescricao.setText(os.getDescricao());
		this.tfCliente.setText(os.getNomeCliente());
		this.tfPlaca.setText(os.getPlacaVeiculo());
		this.tfValorMaoDeObra.setText(os.getValorMaoDeObra().toString());
		this.tfValorPecas.setText(os.getValorPecas().toString());
		this.taLaudo.setText(os.getLaudoTecnico());
		this.tfCodigoVenda.setText(os.getCodigoVenda());
		
		//SETAR FORMA DE PAGAMENTO E STATUS
		this.setComboBox(os.getStatus(), os.getForma_pagamento());
		
		//SETAR PRODUTOS
		if(os.getCodigoVenda() == null || os.getCodigoVenda().equals("")) {
			
		}
		else {
			atualizarTabela();
		}
	}
	
	public void atualizarTabela() {
		int codigo = Integer.parseInt(this.tfCodigoVenda.getText());
		
		//SETAR LISTA DE COMPRA
		setListaCompraCod(codigo);
		
		//ATUALIZAR TABELA
		setTabelaVenda();
		
		//SETAR VALOR DA COMPRA
		this.tfValorPecas.setText(""+getTotalProduto());
	}
	
	public void setComboBox(String status, String pag) {

		int indiceStatus = 0;
		int indicePagamento = 0;
		
		//STATUS
		switch(status) {

			case "EM_ANDAMENTO":
				indiceStatus = 1;
				break;	
			
			case "CONCLUIDO":
				indiceStatus = 2;
				break;	
				
			case "CANCELADO":
				indiceStatus = 3;
				break;	
		}

		//FORMA DE PAGAMENTO
		switch(pag) {

			case "CARTÃO":
				indicePagamento = 1;
				break;
				
			case "DINHEIRO_E_CARTÃO":
				indicePagamento = 2;
				break;
			
			case "BOLETO":
				indicePagamento = 3;
				break;	
				
			case "PIX":
				indicePagamento = 4;
				break;	
		}
		
		//ATUALIZAR O INDICE
		this.cbStatus.setSelectedIndex(indiceStatus);
		this.cbPagamento.setSelectedIndex(indicePagamento);
	}
	
	public float getTotalProduto() {
		
		float total = 0f;
		
		//VARRER LISTA DE COMPRA PEGANDO O VALOR DOS PRODUTOS
		for(ProdutoVendido pv: listaDeCompra) {
			total += pv.getValorTotal();
		}
		
		//RETORNA O TOTAL DOS PRODUTOS
		return total;
	}
	
	public void setListaCompraCod(int codigo) {
	
		//PEGAR A LISTA DE COMPRA
		listaDeCompra = PersistenciaEmBanco.pegarInstancia().getVendaID(codigo);
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
}
