package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class CadastrarOrdemDeServico extends JFrame{
	private JTextField tfCod;
	private JTextField tfEntrada;
	private JTextField tfSaida;
	private JTextField textField;
	
	public CadastrarOrdemDeServico() {
		
		setResizable(false);
		
		setSize(473,519);
		setTitle("NOVA ORDEM DE SERVIÇO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 433, 114);
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
		
		tfEntrada = new JTextField();
		tfEntrada.setBounds(95, 28, 152, 20);
		panel.add(tfEntrada);
		tfEntrada.setColumns(10);
		
		JLabel lblSaida = new JLabel("Data Sa\u00EDda:");
		lblSaida.setBounds(257, 11, 69, 14);
		panel.add(lblSaida);
		
		tfSaida = new JTextField();
		tfSaida.setBounds(257, 28, 166, 20);
		panel.add(tfSaida);
		tfSaida.setColumns(10);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 59, 46, 14);
		panel.add(lblCliente);
		
		JComboBox cbCliente = new JComboBox();
		cbCliente.setBounds(10, 73, 214, 22);
		panel.add(cbCliente);
		
		JLabel lblVeiculo = new JLabel("Veiculo:");
		lblVeiculo.setBounds(234, 59, 46, 14);
		panel.add(lblVeiculo);
		
		JComboBox cbVeiculo = new JComboBox();
		cbVeiculo.setBounds(234, 73, 189, 22);
		panel.add(cbVeiculo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 136, 433, 193);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 30, 413, 152);
		panel_1.add(textArea);
		
		JLabel lblDescricao = new JLabel("DESCRI\u00C7\u00C3O:");
		lblDescricao.setBounds(185, 11, 73, 14);
		panel_1.add(lblDescricao);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 340, 433, 62);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Status:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel_2.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ESPERA", "ANDAMENTO", "CONCLUIDO"}));
		comboBox.setBounds(10, 27, 131, 22);
		panel_2.add(comboBox);
		
		JLabel lblPagamento = new JLabel("Forma de Pagamento:");
		lblPagamento.setBounds(151, 11, 113, 14);
		panel_2.add(lblPagamento);
		
		JComboBox cbPagamento = new JComboBox();
		cbPagamento.setModel(new DefaultComboBoxModel(new String[] {"DINHEIRO", "CART\u00C3O", "DINHEIRO E CART\u00C3O"}));
		cbPagamento.setBounds(151, 27, 130, 22);
		panel_2.add(cbPagamento);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(301, 11, 46, 14);
		panel_2.add(lblValor);
		
		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Tahoma", Font.BOLD, 11));
		textField.setBounds(299, 28, 124, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBounds(10, 409, 433, 54);
		getContentPane().add(panel_3);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		setVisible(true);
	}
}
