package oficina.gui;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaTroco extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField tfTroco;
	
	private int quantidadeDeProdutos = 0;
	private float subtotal = 0f;
	private float total = 0f;
	
	private JLabel lblQuantidade;
	private JLabel lblSubTotal;
	private JLabel lblValorTotal;

	public TelaTroco(int quant, float sub, float total) {
		
		this.quantidadeDeProdutos = quant;
		this.subtotal = sub;
		this.total = total;
		
		setFont(new Font("Arial", Font.PLAIN, 12));
		setResizable(false);
		
		setSize(422,486);
		setTitle("SETOR DE VENDAS");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblQuant = new JLabel("QUANT:");
		lblQuant.setFont(new Font("Arial", Font.BOLD, 28));
		lblQuant.setBounds(23, 11, 118, 48);
		getContentPane().add(lblQuant);
		
		lblQuantidade = new JLabel("0");
		lblQuantidade.setForeground(Color.BLUE);
		lblQuantidade.setFont(new Font("Arial", Font.BOLD, 28));
		lblQuantidade.setBounds(210, 11, 107, 48);
		getContentPane().add(lblQuantidade);
		
		lblSubTotal = new JLabel("0.00");
		lblSubTotal.setForeground(Color.ORANGE);
		lblSubTotal.setFont(new Font("Arial", Font.BOLD, 28));
		lblSubTotal.setBounds(210, 70, 162, 48);
		getContentPane().add(lblSubTotal);
		
		JLabel lbl = new JLabel("SUBTOTAL:");
		lbl.setFont(new Font("Arial", Font.BOLD, 28));
		lbl.setBounds(23, 70, 177, 48);
		getContentPane().add(lbl);
		
		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Arial", Font.BOLD, 28));
		lblTotal.setBounds(23, 129, 107, 48);
		getContentPane().add(lblTotal);
		
		lblValorTotal = new JLabel("0.00");
		lblValorTotal.setForeground(new Color(0, 153, 0));
		lblValorTotal.setFont(new Font("Arial", Font.BOLD, 28));
		lblValorTotal.setBounds(210, 129, 162, 48);
		getContentPane().add(lblValorTotal);
		
		JLabel lblClientePagou = new JLabel("CLIENTE:");
		lblClientePagou.setFont(new Font("Arial", Font.BOLD, 28));
		lblClientePagou.setBounds(23, 188, 177, 48);
		getContentPane().add(lblClientePagou);
		
		tfTroco = new JTextField();
		tfTroco.setForeground(new Color(0, 0, 0));
		tfTroco.setFont(new Font("Arial", Font.BOLD, 28));
		tfTroco.setBounds(210, 188, 162, 48);
		getContentPane().add(tfTroco);
		tfTroco.setColumns(10);
		
		JLabel lblTroco1 = new JLabel("TROCO:");
		lblTroco1.setFont(new Font("Arial", Font.BOLD, 28));
		lblTroco1.setBounds(23, 247, 177, 48);
		getContentPane().add(lblTroco1);
		
		JLabel lblTroco = new JLabel("0.00");
		lblTroco.setForeground(new Color(255, 0, 0));
		lblTroco.setFont(new Font("Arial", Font.BOLD, 28));
		lblTroco.setBounds(210, 247, 177, 48);
		getContentPane().add(lblTroco);
		
		JButton btnCalcular = new JButton("CALCULAR");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PEGAR O VALOR QUE O CLIENTE DEU
				float valorCliente = Float.parseFloat(tfTroco.getText());
				
				float troco = valorCliente - total;
				
				//SETAR O TROCO
				lblTroco.setText(""+troco);
			}
		});
		btnCalcular.setBackground(Color.LIGHT_GRAY);
		btnCalcular.setFont(new Font("Arial", Font.BOLD, 28));
		btnCalcular.setBounds(23, 305, 349, 48);
		getContentPane().add(btnCalcular);
		
		JButton btnFinalizar = new JButton("FINALIZAR");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFinalizar.setFont(new Font("Arial", Font.BOLD, 28));
		btnFinalizar.setBackground(Color.LIGHT_GRAY);
		btnFinalizar.setBounds(23, 364, 349, 48);
		getContentPane().add(btnFinalizar);
		setModal(true);
		
		//SETAR OS VALORES
		setarValores();
		
		setVisible(true);
	}
	
	public void setarValores() {
		System.out.println(quantidadeDeProdutos + " " + subtotal + " "+ total);
		
		lblQuantidade.setText(""+quantidadeDeProdutos);
		lblSubTotal.setText(""+subtotal);
		lblValorTotal.setText(""+total);
	}
}
