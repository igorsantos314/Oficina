package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import oficina.modelo.OrdemDeServico;
import oficina.persistencia.PersistenciaEmBanco;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultarOrdemDeServico extends JDialog{
	
	private JTable table;
	private String[] colunasTabela = {"Cod", "Placa", "Status", "Nome Cliente", "Valor Mão de Obra", "Valor Peças", "Pagamento"};
	private final int QUANTIDADE_MAX_CONTAS = 100;
	private Object[][] elementos = new Object[QUANTIDADE_MAX_CONTAS][7];
	private JTextField tfPlaca;
	
	public ConsultarOrdemDeServico() {
		setResizable(false);
		
		setSize(943,563);
		setTitle("CONSULTAR ORDEM DE SERVIÇO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 901, 456);
		getContentPane().add(scrollPane);
		
		table = new JTable(elementos, colunasTabela);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		JLabel lblPlaca = new JLabel("NOME OU PLACA:");
		lblPlaca.setBounds(9, 19, 116, 14);
		getContentPane().add(lblPlaca);
		
		tfPlaca = new JTextField();
		tfPlaca.setBounds(130, 16, 440, 20);
		getContentPane().add(tfPlaca);
		tfPlaca.setColumns(10);
		
		JButton btConsultar = new JButton("CONSULTAR");
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomePlaca = tfPlaca.getText().toUpperCase();
				
				if(nomePlaca.equalsIgnoreCase("")) {
					
					//POVOAR TABELA
					updateTable();
					
				}
				
				else {
					//FAZER CONSULTA NO BD
					ArrayList<OrdemDeServico> oss = (ArrayList<OrdemDeServico>) PersistenciaEmBanco.pegarInstancia().getOSPlaca(nomePlaca);
					
					//POVOAR TABELA
					inserirTabela(oss);
				}
				
			}
		});
		
		btConsultar.setBounds(593, 15, 157, 23);
		getContentPane().add(btConsultar);
		
		JButton btnNewButton = new JButton("EDITAR OS");
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
						EditarOrdemDeServico eos = new EditarOrdemDeServico(os_selecionada);
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
		btnNewButton.setBounds(760, 15, 151, 23);
		getContentPane().add(btnNewButton);
		setVisible(true);
		
		//INICIALIZAR A TABELA
		this.updateTable();
		
		setVisible(true);
	}
	
	public ConsultarOrdemDeServico(boolean status) {
		//ATUALIZAR TABELA
		this.updateTable();
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
			elementos[i][6] = "";
		}
	}
	
	public void inserirTabela(ArrayList<OrdemDeServico> oss) {
		
		//LIMPAR CAMPOS
		clearTable();
		
		//ITERAR LINHAS
		int i = 0;
		
		//PRENCHER CAMPOS
		for(OrdemDeServico Ordem : oss)
		{
			elementos[i][0] = Ordem.getCod();
			elementos[i][1] = Ordem.getPlacaVeiculo();
			elementos[i][2] = Ordem.getStatus();
			elementos[i][3] = Ordem.getNomeCliente();
			elementos[i][4] = Ordem.getValorMaoDeObra();
			elementos[i][5] = Ordem.getValorPecas();
			elementos[i][6] = Ordem.getForma_pagamento();
			
			i++;
		}
		
		//ATUALIZAR TABELA
		table.updateUI();
	}
	
	public void updateTable() {
		
		//FAZER CONSULTA NO BD
		ArrayList<OrdemDeServico> oss = (ArrayList<OrdemDeServico>) PersistenciaEmBanco.pegarInstancia().getAllOS();
	
		//ATUALIZAR TABELA
		inserirTabela(oss);
	}
}
