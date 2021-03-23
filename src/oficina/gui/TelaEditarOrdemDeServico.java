package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;

import oficina.facade.Conexao;
import oficina.impressao.Impressao;
import oficina.modelo.Cliente;
import oficina.modelo.IVeiculo;
import oficina.modelo.OrdemDeServico;
import oficina.persistencia.PersistenciaEmBanco;
import oficina.types.PagamentoTypes;
import oficina.types.StatusTypes;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

public class TelaEditarOrdemDeServico extends JDialog{
	private JTextField tfCod;
	private JTextField tfValorMaoDeObra;
	JTextArea taDescricao;
	private JTextField tfCliente;
	private JTextField tfPlaca;
	private JFormattedTextField tfSaida;
	private JFormattedTextField tfEntrada;
	private JComboBox cbPagamento;
	private JComboBox cbStatus;
	private JTextField tfValorPecas;
	
	public TelaEditarOrdemDeServico(String cod) throws ParseException {
		
		setResizable(false);
		
		setSize(473,590);
		setTitle("EDITAR ORDEM DE SERVIÇO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setModal(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 433, 146);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCod = new JLabel("Cod:");
		lblCod.setBounds(10, 11, 46, 14);
		panel.add(lblCod);
		
		tfCod = new JTextField();
		tfCod.setEditable(false);
		tfCod.setBounds(10, 28, 75, 20);
		panel.add(tfCod);
		tfCod.setColumns(10);
		
		JLabel lblDataEntrada = new JLabel("Data Entrada:");
		lblDataEntrada.setBounds(95, 11, 87, 14);
		panel.add(lblDataEntrada);
		
		JLabel lblSaida = new JLabel("Data Sa\u00EDda:");
		lblSaida.setBounds(269, 11, 69, 14);
		panel.add(lblSaida);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 59, 46, 14);
		panel.add(lblCliente);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(10, 99, 46, 14);
		panel.add(lblPlaca);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 168, 433, 193);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		taDescricao = new JTextArea();
		taDescricao.setBounds(10, 30, 413, 152);
		panel_1.add(taDescricao);
		
		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O:");
		lblDescricao.setBounds(185, 11, 73, 14);
		panel_1.add(lblDescricao);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 372, 433, 107);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Status:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel_2.add(lblNewLabel);
		
		cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel<>(StatusTypes.values()));
		cbStatus.setBounds(10, 27, 198, 22);
		panel_2.add(cbStatus);
		
		JLabel lblPagamento = new JLabel("Forma de Pagamento:");
		lblPagamento.setBounds(218, 11, 113, 14);
		panel_2.add(lblPagamento);
		
		cbPagamento = new JComboBox();
		cbPagamento.setModel(new DefaultComboBoxModel<>(PagamentoTypes.values()));
		cbPagamento.setBounds(218, 27, 198, 22);
		panel_2.add(cbPagamento);
		
		JLabel lblValor = new JLabel("Valor M\u00E3o de Obra");
		lblValor.setBounds(12, 59, 198, 14);
		panel_2.add(lblValor);
		
		tfValorMaoDeObra = new JTextField();
		tfValorMaoDeObra.setForeground(Color.RED);
		tfValorMaoDeObra.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfValorMaoDeObra.setBounds(10, 76, 198, 20);
		panel_2.add(tfValorMaoDeObra);
		tfValorMaoDeObra.setColumns(10);
		
		tfValorPecas = new JTextField();
		tfValorPecas.setForeground(Color.BLUE);
		tfValorPecas.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfValorPecas.setBounds(218, 76, 198, 20);
		panel_2.add(tfValorPecas);
		tfValorPecas.setColumns(10);
		
		JLabel lblValorEmPeas = new JLabel("Valor em Pe\u00E7as:");
		lblValorEmPeas.setBounds(218, 60, 198, 14);
		panel_2.add(lblValorEmPeas);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(10, 490, 433, 54);
		getContentPane().add(panel_3);
		
		tfCliente = new JTextField();
		tfCliente.setEditable(false);
		tfCliente.setBounds(10, 71, 413, 20);
		panel.add(tfCliente);
		tfCliente.setColumns(10);
		
		tfPlaca = new JTextField();
		tfPlaca.setEditable(false);
		tfPlaca.setBounds(10, 111, 249, 20);
		panel.add(tfPlaca);
		tfPlaca.setColumns(10);
		
		JFormattedTextField tfEntrada = new JFormattedTextField();
		tfEntrada.setBounds(95, 28, 164, 20);
		panel.add(tfEntrada);
		
		tfSaida = new JFormattedTextField();
		tfSaida.setBounds(269, 28, 154, 20);
		panel.add(tfSaida);
		
		JButton btnEditar = new JButton("EDITAR OS");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String descricao = taDescricao.getText().toUpperCase();
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
                	Conexao.pegarInstancia().atualizarOS(cod, descricao, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, cod, nome_Cliente);
                    
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
    				TelaConsultarOrdemDeServico C = new TelaConsultarOrdemDeServico();
    				
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
		btImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEGAR DADOS DOS CAMPOS
				String descricao = taDescricao.getText().toUpperCase();
				Float valorMaoDeObra = Float.parseFloat(tfValorMaoDeObra.getText());
				Float valorPecas = Float.parseFloat(tfValorPecas.getText());
				String data_Entrada = tfEntrada.getText();
				String data_Saida = tfSaida.getText();
				String pagamento = cbPagamento.getSelectedItem().toString();
				String status = cbStatus.getSelectedItem().toString();
				String placa = tfPlaca.getText();
				String nome_Cliente = tfCliente.getText();
				
				//IMPRIMIR OS
				Conexao.pegarInstancia().imprimirOS(descricao, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, placa, nome_Cliente);
				
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
		
		//SETAR FORMA DE PAGAMENTO E STATUS
		this.setComboBox(os.getStatus(), os.getForma_pagamento());
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
}
