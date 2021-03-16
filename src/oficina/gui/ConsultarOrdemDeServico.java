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
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultarOrdemDeServico extends JFrame{
	
	private JTable table;
	private String[] colunasTabela = {"Cod", "Placa", "Status", "Nome Cliente", "Valor", "Pagamento"};
	private final int QUANTIDADE_MAX_CONTAS = 20;
	private Object[][] elementos = new Object[QUANTIDADE_MAX_CONTAS][6];
	private JTextField tfPlaca;
	
	public ConsultarOrdemDeServico() {
		setResizable(false);
		
		setSize(869,474);
		setTitle("CONSULTAR ORDEM DE SERVIÇO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 816, 373);
		getContentPane().add(scrollPane);
		
		table = new JTable(elementos, colunasTabela);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		JLabel lblPlaca = new JLabel("PLACA:");
		lblPlaca.setBounds(10, 21, 46, 14);
		getContentPane().add(lblPlaca);
		
		tfPlaca = new JTextField();
		tfPlaca.setBounds(66, 16, 449, 20);
		getContentPane().add(tfPlaca);
		tfPlaca.setColumns(10);
		
		JButton btConsultar = new JButton("CONSULTAR");
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String placa = tfPlaca.getText().toUpperCase();
				
				if(placa.equalsIgnoreCase("")) {
					//FAZER CONSULTA NO BD
					ArrayList<OrdemDeServico> oss = (ArrayList<OrdemDeServico>) PersistenciaEmBanco.pegarInstancia().getAllOS();
					
					//POVOAR TABELA
					inserirTabela(oss);
					
				}
				
				else {
					//FAZER CONSULTA NO BD
					ArrayList<OrdemDeServico> oss = (ArrayList<OrdemDeServico>) PersistenciaEmBanco.pegarInstancia().getOSPlaca(placa);
					
					//POVOAR TABELA
					inserirTabela(oss);
				}
				
			}
		});
		
		btConsultar.setBounds(518, 15, 157, 23);
		getContentPane().add(btConsultar);
		
		JButton btnNewButton = new JButton("EDITAR OS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEGAR O INDICE DA LINHA SELECIONADA
				int numberLine = table.getSelectedRow();
				
				//PEGAR O CODIGO DA OS
				String os_selecionada = table.getModel().getValueAt(numberLine,0).toString();
				
				//ABRIR TELA DE EDICAO
				try {
					EditarOrdemDeServico eos = new EditarOrdemDeServico(os_selecionada);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(679, 15, 151, 23);
		getContentPane().add(btnNewButton);
		setVisible(true);
		
		setVisible(true);
	}
	
	public void inserirTabela(ArrayList<OrdemDeServico> oss) {
		
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
		
		//ITERAR LINHAS
		int i = 0;
		
		//PRENCHER CAMPOS
		for(OrdemDeServico Ordem : oss)
		{
			elementos[i][0] = Ordem.getCod();
			elementos[i][1] = Ordem.getPlacaVeiculo();
			elementos[i][2] = Ordem.getStatus();
			elementos[i][3] = Ordem.getNomeCliente();
			elementos[i][4] = Ordem.getValor();
			elementos[i][5] = Ordem.getForma_pagamento();
			
			i++;
		}
		
		//ATUALIZAR TABELA
		table.updateUI();
	}
}
