package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import oficina.modelo.IVeiculo;
import oficina.modelo.OrdemDeServico;
import oficina.persistencia.PersistenciaEmBanco;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaConsultarVeiculos extends JDialog{
	
	private JTable table;
	private String[] colunasTabela = {"Modelo", "Placa", "Cor", "Ano", "KM Atual"};
	private final int QUANTIDADE_MAX_CONTAS = 100;
	private Object[][] elementos = new Object[QUANTIDADE_MAX_CONTAS][5];
	private JTextField tfPlaca;
	
	public TelaConsultarVeiculos() {
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
		
		JLabel lblPlaca = new JLabel("MODELO OU PLACA:");
		lblPlaca.setBounds(9, 19, 116, 14);
		getContentPane().add(lblPlaca);
		
		tfPlaca = new JTextField();
		tfPlaca.setBounds(130, 16, 440, 20);
		getContentPane().add(tfPlaca);
		tfPlaca.setColumns(10);
		
		JButton btConsultar = new JButton("CONSULTAR");
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String modeloPlaca = tfPlaca.getText().toUpperCase();
				
				if(modeloPlaca.equalsIgnoreCase("")) {
					
					//POVOAR TABELA
					updateTable();
					
				}
				
				else {
					//FAZER CONSULTA NO BD
					ArrayList<IVeiculo> veiculos = (ArrayList<IVeiculo>) PersistenciaEmBanco.pegarInstancia().getVeiculoPlacaNome(modeloPlaca);
					
					//POVOAR TABELA
					inserirTabela(veiculos);
				}
				
			}
		});
		
		btConsultar.setBounds(593, 15, 157, 23);
		getContentPane().add(btConsultar);
		
		JButton btnNewButton = new JButton("EDITAR VEICULO");
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
						TelaEditarOrdemDeServico eos = new TelaEditarOrdemDeServico(os_selecionada);
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
	
	public void clearTable() {
		//LIMPAR CAMPOS
		for(int i = 0; i<QUANTIDADE_MAX_CONTAS; i++)
		{
			elementos[i][0] = "";
			elementos[i][1] = "";
			elementos[i][2] = "";
			elementos[i][3] = "";
			elementos[i][4] = "";
		}
	}
	
	public void inserirTabela(ArrayList<IVeiculo> veiculos) {
		
		//LIMPAR CAMPOS
		clearTable();
		
		//ITERAR LINHAS
		int i = 0;
		
		//PRENCHER CAMPOS
		for(IVeiculo veiculo : veiculos)
		{
			elementos[i][0] = veiculo.getModelo();
			elementos[i][1] = veiculo.getPlaca();
			elementos[i][2] = veiculo.getCor();
			elementos[i][3] = veiculo.getAno();
			elementos[i][4] = veiculo.getKm_atual();
			
			i++;
		}
		
		//ATUALIZAR TABELA
		table.updateUI();
	}
	
	public void updateTable() {
		
		//FAZER CONSULTA NO BD
		ArrayList<IVeiculo> oss = (ArrayList<IVeiculo>) PersistenciaEmBanco.pegarInstancia().getAllVeiculos();
	
		//ATUALIZAR TABELA
		inserirTabela(oss);
	}
}
