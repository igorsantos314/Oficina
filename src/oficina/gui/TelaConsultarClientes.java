package oficina.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import oficina.modelo.Cliente;
import oficina.persistencia.PersistenciaEmBanco;

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
	private JTextField tfNome;
	private JTextField tfEmail;
	
	private JFormattedTextField tfCPF;
	private JLabel lblEmail;
	private JFormattedTextField tfTelefone;
	
	private JButton btnFechar;
	private JButton btnExcluirCliente;
	private JButton btnSalvarAlteraes;

	private JButton btnEditar;
	
	public TelaConsultarClientes() {
		
		setResizable(false);
		setSize(938,660);
		setTitle("CONSULTAR ORDEM DE SERVIÇO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 901, 411);
		getContentPane().add(scrollPane);
		
		table = new JTable(elementos, colunasTabela);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		JLabel lblPlaca = new JLabel("NOME CLIENTE:");
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPlaca.setBounds(9, 19, 116, 14);
		getContentPane().add(lblPlaca);
		
		tfNomeCliente = new JTextField();
		tfNomeCliente.setFont(new Font("Arial", Font.PLAIN, 14));
		tfNomeCliente.setBounds(130, 16, 453, 20);
		getContentPane().add(tfNomeCliente);
		tfNomeCliente.setColumns(10);
		
		JButton btConsultar = new JButton("CONSULTAR");
		btConsultar.setFont(new Font("Arial", Font.PLAIN, 14));
		btConsultar.setBackground(Color.LIGHT_GRAY);
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nomeCliente = tfNomeCliente.getText().toUpperCase();
				
				//FAZER CONSULTA NO BD
				ArrayList<Cliente> veiculos = (ArrayList<Cliente>) PersistenciaEmBanco.pegarInstancia().getClientesNome(nomeCliente);
				
				//POVOAR TABELA
				inserirTabela(veiculos);
				
			}
		});
		
		btConsultar.setBounds(593, 15, 157, 23);
		getContentPane().add(btConsultar);
		
		btnEditar = new JButton("EDITAR CLIENTE");
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEditar.setBackground(Color.LIGHT_GRAY);
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
						String nome = table.getModel().getValueAt(numberLine,1).toString();
						String telefone = table.getModel().getValueAt(numberLine,2).toString();
						String email = table.getModel().getValueAt(numberLine,3).toString();
						
						//SETAR CAMPOS
						tfNome.setText(nome);
						tfCPF.setText(cpf);
						tfTelefone.setText(telefone);
						tfEmail.setText(email);
						
						//HABILITAR CAMPOS
						tfNome.setEnabled(true);
						tfTelefone.setEnabled(true);
						tfEmail.setEnabled(true);
						
						//HABILITAR BOTOES
						btnFechar.setEnabled(true);
						btnExcluirCliente.setEnabled(true);
						
						//DESABILITAR BOTÃO DE EDIDAR
						btnEditar.setEnabled(false);
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "POR FAVOR, SELECIONE UM CLIENTE");
				}

			}
		});
		btnEditar.setBounds(760, 15, 151, 23);
		getContentPane().add(btnEditar);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNome.setBounds(10, 468, 98, 14);
		getContentPane().add(lblNome);
		
		tfNome = new JTextField();
		tfNome.setFont(new Font("Arial", Font.PLAIN, 14));
		tfNome.setEnabled(false);
		tfNome.setColumns(10);
		tfNome.setBounds(10, 483, 901, 20);
		getContentPane().add(tfNome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCpf.setBounds(10, 514, 55, 14);
		getContentPane().add(lblCpf);
		
		tfCPF = new JFormattedTextField();
		tfCPF.setFont(new Font("Arial", Font.PLAIN, 14));
		tfCPF.setEditable(false);
		tfCPF.setBounds(10, 529, 181, 20);
		getContentPane().add(tfCPF);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTelefone.setBounds(201, 514, 151, 14);
		getContentPane().add(lblTelefone);
		
		tfTelefone = new JFormattedTextField();
		tfTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
		tfTelefone.setEnabled(false);
		tfTelefone.setBounds(201, 529, 183, 20);
		getContentPane().add(tfTelefone);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(398, 514, 342, 14);
		getContentPane().add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(398, 529, 352, 20);
		getContentPane().add(tfEmail);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 572, 901, 39);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnFechar = new JButton("FECHAR");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LIMPAR E DESABILITAR TUDO
				clearCamps();
			}
		});
		btnFechar.setEnabled(false);
		btnFechar.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(btnFechar);
		btnFechar.setBackground(Color.LIGHT_GRAY);
		
		btnExcluirCliente = new JButton("EXCLUIR");
		btnExcluirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR O CLIENTE: " + tfNome.getText() + " ?");
				
				//VERIFICAR SE O USUARIO DESEJA FINALIZAR A VENDA
                if(resposta == 0)
                {
					//EXCLUIR CLIENTE
					PersistenciaEmBanco.pegarInstancia().deleteCliente(tfCPF.getText());
					
					//LIMPAR E DESABILITAR TUDO
					clearCamps();
					
					//ATUALIZAR TABELA
					updateTable();
					
					//MENSAGEM DE SUCESSO
					JOptionPane.showMessageDialog(null, "Cliente Excluido com Sucesso!");
                }
			}
		});
		btnExcluirCliente.setEnabled(false);
		btnExcluirCliente.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(btnExcluirCliente);
		btnExcluirCliente.setBackground(Color.LIGHT_GRAY);
		
		btnSalvarAlteraes = new JButton("SALVAR");
		btnSalvarAlteraes.setEnabled(false);
		btnSalvarAlteraes.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(btnSalvarAlteraes);
		btnSalvarAlteraes.setBackground(Color.LIGHT_GRAY);
		setVisible(true);
		
		//INICIALIZAR A TABELA
		this.updateTable();

		setVisible(true);
	}
	
	public void clearCamps() {
		
		//LIMPAR CAMPOS
		tfNome.setText("");
		tfCPF.setText("");
		tfTelefone.setText("");
		tfEmail.setText("");
		
		//HABILITAR CAMPOS
		tfNome.setEnabled(false);
		tfTelefone.setEnabled(false);
		tfEmail.setEnabled(false);
		
		//HABILITAR BOTOES
		btnFechar.setEnabled(false);
		btnExcluirCliente.setEnabled(false);
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
