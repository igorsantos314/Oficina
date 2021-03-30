package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import oficina.facade.Conexao;
import oficina.modelo.Cliente;
import oficina.modelo.IVeiculo;
import oficina.persistencia.PersistenciaEmBanco;
import oficina.types.PagamentoTypes;
import oficina.types.StatusTypes;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.border.EtchedBorder;

public class TelaCadastrarOrdemDeServico extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField tfValorMaoDeObra;
	private JFormattedTextField tfEntrada;
	private JFormattedTextField tfSaida;
	
	private JComboBox<Cliente> cbCliente;
	private JComboBox<IVeiculo> cbVeiculo;
	private JTextField tfCliente;
	private JTextField tfVeiculo;
	
	public TelaCadastrarOrdemDeServico() throws ParseException {
		
		setResizable(false);
		
		setSize(723,680);
		setTitle("NOVA ORDEM DE SERVIÇO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 675, 184);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDataEntrada = new JLabel("Data Entrada:");
		lblDataEntrada.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDataEntrada.setBounds(10, 11, 120, 14);
		panel.add(lblDataEntrada);
		
		JLabel lblSaida = new JLabel("Data Sa\u00EDda:");
		lblSaida.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSaida.setBounds(325, 11, 109, 14);
		panel.add(lblSaida);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(325, 66, 46, 14);
		panel.add(lblCliente);
		
		cbCliente = new JComboBox<Cliente>();
		cbCliente.setFont(new Font("Arial", Font.PLAIN, 14));
		cbCliente.setBackground(Color.WHITE);
		
		for(Cliente c : PersistenciaEmBanco.pegarInstancia().getAllClientes())
		{
			cbCliente.addItem(c);
		}
		
		cbCliente.setBounds(325, 80, 340, 25);
		panel.add(cbCliente);
		
		JLabel lblVeiculo = new JLabel("Veiculo:");
		lblVeiculo.setBounds(325, 125, 46, 14);
		panel.add(lblVeiculo);
		
		cbVeiculo = new JComboBox<IVeiculo>();
		cbVeiculo.setFont(new Font("Arial", Font.PLAIN, 14));
		cbVeiculo.setBackground(Color.WHITE);
		
		for(IVeiculo c : PersistenciaEmBanco.pegarInstancia().getAllVeiculos())
		{
			cbVeiculo.addItem(c);
		}
		
		cbVeiculo.setBounds(325, 139, 340, 25);
		panel.add(cbVeiculo);
		
		tfEntrada = new JFormattedTextField();
		tfEntrada.setFont(new Font("Arial", Font.PLAIN, 14));
		tfEntrada.setBounds(10, 28, 120, 20);
		panel.add(tfEntrada);
		
		tfSaida = new JFormattedTextField();
		tfSaida.setFont(new Font("Arial", Font.PLAIN, 14));
		tfSaida.setBounds(325, 28, 139, 20);
		panel.add(tfSaida);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 206, 675, 193);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea taDescricao = new JTextArea();
		taDescricao.setForeground(Color.BLUE);
		taDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
		taDescricao.setBounds(10, 30, 655, 152);
		panel_1.add(taDescricao);
		
		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O:");
		lblDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDescricao.setBounds(10, 11, 655, 14);
		panel_1.add(lblDescricao);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 410, 675, 147);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Status:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 330, 14);
		panel_2.add(lblNewLabel);
		
		JComboBox<StatusTypes> cbStatus = new JComboBox<StatusTypes>();
		cbStatus.setFont(new Font("Arial", Font.PLAIN, 14));
		cbStatus.setBackground(Color.WHITE);
		cbStatus.setModel(new DefaultComboBoxModel<>(StatusTypes.values()));
		cbStatus.setBounds(10, 27, 330, 22);
		panel_2.add(cbStatus);
		
		JLabel lblPagamento = new JLabel("Forma de Pagamento:");
		lblPagamento.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPagamento.setBounds(350, 11, 315, 14);
		panel_2.add(lblPagamento);
		
		JComboBox<PagamentoTypes> cbPagamento = new JComboBox<PagamentoTypes>();
		cbPagamento.setFont(new Font("Arial", Font.PLAIN, 14));
		cbPagamento.setBackground(Color.WHITE);
		cbPagamento.setModel(new DefaultComboBoxModel<>(PagamentoTypes.values()));
		cbPagamento.setBounds(350, 27, 315, 22);
		panel_2.add(cbPagamento);
		
		JLabel lblValor = new JLabel("Valor de M\u00E3o de Obra:");
		lblValor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblValor.setBounds(10, 60, 330, 14);
		panel_2.add(lblValor);
		
		tfValorMaoDeObra = new JTextField();
		tfValorMaoDeObra.setText("0.00");
		tfValorMaoDeObra.setForeground(Color.RED);
		tfValorMaoDeObra.setFont(new Font("Arial", Font.BOLD, 25));
		tfValorMaoDeObra.setBounds(10, 79, 330, 41);
		panel_2.add(tfValorMaoDeObra);
		tfValorMaoDeObra.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 568, 675, 54);
		getContentPane().add(panel_3);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnSalvar.setBackground(Color.LIGHT_GRAY);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String descricao = taDescricao.getText().toUpperCase();
				Float valorMaoDeObra = Float.parseFloat(tfValorMaoDeObra.getText());
				Float valorPecas = 0f;
				String data_Entrada = tfEntrada.getText();
				String data_Saida = tfSaida.getText();
				String pagamento = cbPagamento.getSelectedItem().toString();
				String status = cbStatus.getSelectedItem().toString();
				String placa = cbVeiculo.getSelectedItem().toString();
				String nome_Cliente = cbCliente.getSelectedItem().toString();
				
				Conexao.pegarInstancia().salvarOS(descricao, "", "", valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, placa, nome_Cliente);
				
				//LIMPAR CAMPOS
				taDescricao.setText("");
				tfValorMaoDeObra.setText("0.00");
				tfEntrada.setText("");
				tfSaida.setText("");
				
				//MENSAGEM DE SUCESSO
				JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
			}
		});
		
		panel_3.add(btnSalvar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
		btnCancelar.setBackground(Color.LIGHT_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_3.add(btnCancelar);
		
		//SETAR MASCARAS
		MaskFormatter mfDataE = new MaskFormatter("##/##/####");
		mfDataE.install(tfEntrada);
		
		MaskFormatter mfDataS = new MaskFormatter("##/##/####");
		mfDataS.install(tfSaida);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar Cliente:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 66, 215, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Buscar Veiculo");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 125, 215, 14);
		panel.add(lblNewLabel_1_1);
		
		tfCliente = new JTextField();
		tfCliente.setForeground(Color.RED);
		tfCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfCliente.setColumns(10);
		tfCliente.setBounds(10, 81, 215, 24);
		panel.add(tfCliente);
		
		tfVeiculo = new JTextField();
		tfVeiculo.setForeground(Color.RED);
		tfVeiculo.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfVeiculo.setColumns(10);
		tfVeiculo.setBounds(10, 140, 215, 24);
		panel.add(tfVeiculo);
		
		JButton btBuscarCliente = new JButton("Buscar");
		btBuscarCliente.setFont(new Font("Arial", Font.PLAIN, 14));
		btBuscarCliente.setBackground(Color.LIGHT_GRAY);
		btBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//BUSCAR CLIENTES PELO NOME
				String nome = tfCliente.getText().toUpperCase();
				
				//LIMPAR COMBOBOX
				cbCliente.setModel(new DefaultComboBoxModel<Cliente>());
				
				//INSERIR OS CLIENTES NO COMBOBOX
				for(Cliente c : PersistenciaEmBanco.pegarInstancia().getClientesNome(nome))
				{
					cbCliente.addItem(c);
				}
				
			}
		});
		btBuscarCliente.setBounds(228, 80, 87, 25);
		panel.add(btBuscarCliente);
		
		JButton btBuscarVeiculo = new JButton("Buscar");
		btBuscarVeiculo.setFont(new Font("Arial", Font.PLAIN, 14));
		btBuscarVeiculo.setBackground(Color.LIGHT_GRAY);
		btBuscarVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//BUSCAR CLIENTES POR NOME OU PLACA
				String placaNome = tfVeiculo.getText().toUpperCase();
				
				//LIMPAR COMBOBOX
				cbVeiculo.setModel(new DefaultComboBoxModel<IVeiculo>());
				
				//INSERIR OS VEICULOS NO COMBOBOX
				for(IVeiculo v : PersistenciaEmBanco.pegarInstancia().getVeiculoPlacaNome(placaNome))
				{
					cbVeiculo.addItem(v);
				}
			}
		});
		btBuscarVeiculo.setBounds(228, 139, 87, 25);
		panel.add(btBuscarVeiculo);
		
		setVisible(true);
	}
}
