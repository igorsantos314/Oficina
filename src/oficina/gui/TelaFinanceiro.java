package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import oficina.modelo.Financeiro;
import oficina.persistencia.PersistenciaEmBanco;

import javax.swing.JTextField;
import java.awt.Window.Type;
import java.awt.Color;

public class TelaFinanceiro extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfReceitaObra;
	private JTextField tfReceitaVenda;
	private JTextField tfLucroTotal;

	public TelaFinanceiro() {
		setType(Type.POPUP);
		setResizable(false);
		
		setSize(844,427);
		setTitle("FINANCEIRO");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblReceitaObra = new JLabel("RECEITA DE M\u00C3O DE OBRA:");
		lblReceitaObra.setFont(new Font("Arial", Font.BOLD, 25));
		lblReceitaObra.setBounds(10, 37, 804, 30);
		lblReceitaObra.setHorizontalAlignment(lblReceitaObra.CENTER);
		getContentPane().add(lblReceitaObra);
		
		JLabel lblReceitaTotalDe = new JLabel("RECEITA TOTAL DE VENDA:");
		lblReceitaTotalDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceitaTotalDe.setFont(new Font("Arial", Font.BOLD, 25));
		lblReceitaTotalDe.setBounds(10, 158, 804, 30);
		getContentPane().add(lblReceitaTotalDe);
		
		JLabel lblLucroNasVendas = new JLabel("LUCRO TOTAL NAS VENDAS:");
		lblLucroNasVendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblLucroNasVendas.setFont(new Font("Arial", Font.BOLD, 25));
		lblLucroNasVendas.setBounds(10, 279, 804, 30);
		getContentPane().add(lblLucroNasVendas);
		
		tfReceitaObra = new JTextField();
		tfReceitaObra.setForeground(new Color(255, 0, 0));
		tfReceitaObra.setFont(new Font("Arial", Font.BOLD, 30));
		tfReceitaObra.setText("R$ 0.00");
		tfReceitaObra.setEditable(false);
		tfReceitaObra.setBounds(10, 78, 804, 45);
		tfReceitaObra.setHorizontalAlignment(tfReceitaObra.CENTER);
		getContentPane().add(tfReceitaObra);
		tfReceitaObra.setColumns(10);
		
		tfReceitaVenda = new JTextField();
		tfReceitaVenda.setForeground(new Color(255, 140, 0));
		tfReceitaVenda.setText("R$ 0.00");
		tfReceitaVenda.setHorizontalAlignment(SwingConstants.CENTER);
		tfReceitaVenda.setFont(new Font("Arial", Font.BOLD, 30));
		tfReceitaVenda.setEditable(false);
		tfReceitaVenda.setColumns(10);
		tfReceitaVenda.setBounds(10, 199, 804, 45);
		getContentPane().add(tfReceitaVenda);
		
		tfLucroTotal = new JTextField();
		tfLucroTotal.setForeground(new Color(50, 205, 50));
		tfLucroTotal.setText("R$ 0.00");
		tfLucroTotal.setHorizontalAlignment(SwingConstants.CENTER);
		tfLucroTotal.setFont(new Font("Arial", Font.BOLD, 30));
		tfLucroTotal.setEditable(false);
		tfLucroTotal.setColumns(10);
		tfLucroTotal.setBounds(10, 320, 804, 45);
		getContentPane().add(tfLucroTotal);
		
		//ATRIBUIR VALORES
		setValores();
		
		setVisible(true);
	}
	
	public void setValores() {
		Financeiro f = PersistenciaEmBanco.pegarInstancia().contabilidadeTotal();
		
		tfReceitaObra.setText("R$ "+f.getTotalMaoObra());
		tfReceitaVenda.setText("R$ "+f.getTotalVendaPecas());
		tfLucroTotal.setText("R$ "+f.getTotalLucro());
	}
}
