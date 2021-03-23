package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import oficina.modelo.Cliente;
import oficina.modelo.IVeiculo;
import oficina.modelo.OrdemDeServico;
import oficina.persistencia.PersistenciaEmBanco;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaConsultarClientes extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private String[] colunasTabela = {"CPF", "Nome", "Telefone", "Email"};
	private final int QUANTIDADE_MAX_CONTAS = 100;
	private Object[][] elementos = new Object[QUANTIDADE_MAX_CONTAS][4];
	private JTextField tfNomeCliente;
	
	public TelaConsultarClientes() {
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
		
		JLabel lblPlaca = new JLabel("NOME CLIENTE:");
		lblPlaca.setBounds(9, 19, 116, 14);
		getContentPane().add(lblPlaca);
		
		tfNomeCliente = new JTextField();
		tfNomeCliente.setBounds(130, 16, 440, 20);
		getContentPane().add(tfNomeCliente);
		tfNomeCliente.setColumns(10);
		
		JButton btConsultar = new JButton("CONSULTAR");
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomeCliente = tfNomeCliente.getText().toUpperCase();
				
				if(nomeCliente.equalsIgnoreCase("")) {
					
					//POVOAR TABELA
					updateTable();
					
				}
				
				else {
					//FAZER CONSULTA NO BD
					ArrayList<Cliente> veiculos = (ArrayList<Cliente>) PersistenciaEmBanco.pegarInstancia().getClientesNome(nomeCliente);
					
					//POVOAR TABELA
					inserirTabela(veiculos);
				}
				
			}
		});
		
		btConsultar.setBounds(593, 15, 157, 23);
		getContentPane().add(btConsultar);
		
		JButton btnNewButton = new JButton("EDITAR CLIENTE");
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
		}
	}
	
	public void inserirTabela(ArrayList<Cliente> clientes) {
		
		//LIMPAR CAMPOS
		clearTable();
		
		//ITERAR LINHAS
		int i = 0;
		
		//PRENCHER CAMPOS
		for(Cliente veiculo : clientes)
		{
			elementos[i][0] = veiculo.getCpf();
			elementos[i][1] = veiculo.getNome();
			elementos[i][2] = veiculo.getTelefone();
			elementos[i][3] = veiculo.getEmail();
			
			i++;
		}
		
		//ATUALIZAR TABELA
		table.updateUI();
	}
	
	public void updateTable() {
		
		//FAZER CONSULTA NO BD
		ArrayList<Cliente> clientes = (ArrayList<Cliente>) PersistenciaEmBanco.pegarInstancia().getAllClientes();
	
		//ATUALIZAR TABELA
		inserirTabela(clientes);
	}
}
