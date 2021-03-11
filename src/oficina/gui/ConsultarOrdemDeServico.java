package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultarOrdemDeServico extends JFrame{
	
	private JTable table;
	private String[] colunasTabela = {"Cod", "Modelo", "Placa", "Status"};
	private final int QUANTIDADE_MAX_CONTAS = 20;
	private Object[][] elementos = new Object[QUANTIDADE_MAX_CONTAS][4];
	private JTextField tfPlaca;
	
	public ConsultarOrdemDeServico() {
		setResizable(false);
		
		setSize(705,468);
		setTitle("CONSULTAR ORDEM DE SERVIÇO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 665, 373);
		getContentPane().add(scrollPane);
		
		table = new JTable(elementos, colunasTabela);
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		JLabel lblPlaca = new JLabel("PLACA:");
		lblPlaca.setBounds(10, 21, 46, 14);
		getContentPane().add(lblPlaca);
		
		tfPlaca = new JTextField();
		tfPlaca.setBounds(59, 18, 449, 20);
		getContentPane().add(tfPlaca);
		tfPlaca.setColumns(10);
		
		JButton btConsultar = new JButton("CONSULTAR");
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String placa = tfPlaca.getText();
				
			}
		});
		
		btConsultar.setBounds(518, 15, 157, 23);
		getContentPane().add(btConsultar);
		setVisible(true);
		
		//POVOAR TABELA
		inserirTabela();
		
		setVisible(true);
	}
	
	public void inserirTabela() {
		elementos[0][0] = "1";
		elementos[0][1] = "PALIO";
		elementos[0][2] = "NES1234";
		elementos[0][3] = "EM ANDAMENTO";
		
		//ATUALIZAR TABELA
		table.updateUI();
	}
	
}
