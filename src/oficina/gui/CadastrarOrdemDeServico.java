package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;

import oficina.facade.Conexao;
import oficina.modelo.Cliente;
import oficina.modelo.IVeiculo;
import oficina.persistencia.PersistenciaEmBanco;
import oficina.types.PagamentoTypes;
import oficina.types.StatusTypes;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

public class CadastrarOrdemDeServico extends JFrame{
	private JTextField tfValor;
	private JFormattedTextField tfEntrada;
	private JFormattedTextField tfSaida;
	
	private JComboBox<Cliente> cbCliente;
	private JComboBox<IVeiculo> cbPlaca;
	
	public CadastrarOrdemDeServico() throws ParseException {
		
		setResizable(false);
		
		setSize(506,519);
		setTitle("NOVA ORDEM DE SERVIÇO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 455, 114);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDataEntrada = new JLabel("Data Entrada:");
		lblDataEntrada.setBounds(10, 11, 87, 14);
		panel.add(lblDataEntrada);
		
		JLabel lblSaida = new JLabel("Data Sa\u00EDda:");
		lblSaida.setBounds(116, 11, 69, 14);
		panel.add(lblSaida);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 59, 46, 14);
		panel.add(lblCliente);
		
		cbCliente = new JComboBox();
		
		for(Cliente c : PersistenciaEmBanco.pegarInstancia().getAllClientes())
		{
			cbCliente.addItem(c);
		}
		
		cbCliente.setBounds(10, 73, 214, 22);
		panel.add(cbCliente);
		
		JLabel lblVeiculo = new JLabel("Veiculo:");
		lblVeiculo.setBounds(234, 59, 46, 14);
		panel.add(lblVeiculo);
		
		cbPlaca = new JComboBox();
		
		for(IVeiculo c : PersistenciaEmBanco.pegarInstancia().getAllVeiculos())
		{
			cbPlaca.addItem(c);
		}
		
		cbPlaca.setBounds(234, 73, 211, 22);
		panel.add(cbPlaca);
		
		JFormattedTextField tfEntrada = new JFormattedTextField();
		tfEntrada.setBounds(10, 28, 96, 20);
		panel.add(tfEntrada);
		
		JFormattedTextField tfSaida = new JFormattedTextField();
		tfSaida.setBounds(116, 28, 109, 20);
		panel.add(tfSaida);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 136, 455, 193);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea taDescricao = new JTextArea();
		taDescricao.setBounds(10, 30, 413, 152);
		panel_1.add(taDescricao);
		
		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O:");
		lblDescricao.setBounds(185, 11, 73, 14);
		panel_1.add(lblDescricao);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 340, 455, 62);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Status:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel_2.add(lblNewLabel);
		
		JComboBox cbStatus = new JComboBox();
		cbStatus.setModel(new DefaultComboBoxModel<>(StatusTypes.values()));
		cbStatus.setBounds(10, 27, 131, 22);
		panel_2.add(cbStatus);
		
		JLabel lblPagamento = new JLabel("Forma de Pagamento:");
		lblPagamento.setBounds(151, 11, 113, 14);
		panel_2.add(lblPagamento);
		
		JComboBox cbPagamento = new JComboBox();
		cbPagamento.setModel(new DefaultComboBoxModel<>(PagamentoTypes.values()));
		cbPagamento.setBounds(151, 27, 130, 22);
		panel_2.add(cbPagamento);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(301, 11, 46, 14);
		panel_2.add(lblValor);
		
		tfValor = new JTextField();
		tfValor.setForeground(Color.RED);
		tfValor.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfValor.setBounds(299, 28, 124, 20);
		panel_2.add(tfValor);
		tfValor.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(10, 409, 455, 54);
		getContentPane().add(panel_3);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String descricao = taDescricao.getText().toUpperCase();
				Float valor = Float.parseFloat(tfValor.getText());
				String data_Entrada = tfEntrada.getText();
				String data_Saida = tfSaida.getText();
				String pagamento = cbPagamento.getSelectedItem().toString();
				String status = cbStatus.getSelectedItem().toString();
				String placa = cbPlaca.getSelectedItem().toString();
				String nome_Cliente = cbCliente.getSelectedItem().toString();
				
				//System.out.println(placa);
				//System.out.println(nome_Cliente);
				
				Conexao.pegarInstancia().salvarOS(descricao, valor, data_Entrada, data_Saida, pagamento, status, placa, nome_Cliente);
				
				//LIMPAR CAMPOS
				taDescricao.setText("");
				tfValor.setText("");
				tfEntrada.setText("");
				tfSaida.setText("");
				
				//MENSAGEM DE SUCESSO
				JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
				
			}
		});
		
		panel_3.add(btnSalvar);
		
		JButton btnCancelar = new JButton("CANCELAR");
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
		
		setVisible(true);
	}
}
