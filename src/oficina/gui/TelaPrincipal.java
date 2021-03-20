package oficina.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import oficina.modelo.OrdemDeServico;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

public class TelaPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelaPrincipal(){
		
		//DATA ATUAL
		LocalDate localDate      = LocalDate.now();
		
		getContentPane().setBackground(SystemColor.menu);
		setResizable(false);
		
		setSize(800,600);
		setTitle("SISTEMA DE OFICINAS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("  JUNIN OFICINAS");
		lblNewLabel.setForeground(new Color(255, 153, 51));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 74));
		lblNewLabel.setBounds(20, 133, 774, 238);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("-----------------------------------------------------------------------------------------------------");
		lblNewLabel_1.setForeground(new Color(255, 153, 51));
		lblNewLabel_1.setBounds(10, 120, 774, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("-----------------------------------------------------------------------------------------------------");
		lblNewLabel_1_1.setForeground(new Color(255, 153, 51));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(274, 375, 520, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 498, 762, 28);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("VERSION: 15.0.0.1");
		panel.add(lblNewLabel_2);
		
		JLabel lblDate = new JLabel("Date");
		panel.add(lblDate);
		
		//SETAR DATA
		lblDate.setText("Data: "+ localDate.toString());
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnVeiculos = new JMenu("Veiculos");
		menuBar.add(mnVeiculos);
		
		JMenuItem mntmNovoVeiculo = new JMenuItem("Cadastrar Veiculo");
		mntmNovoVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//CHAMAR TELA DE CADASTRO DE VEICULO
					new CadastrarVeiculo();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnVeiculos.add(mntmNovoVeiculo);
		
		JMenu mnCliente = new JMenu("Clientes");
		menuBar.add(mnCliente);
		
		JMenuItem mntmCadastrarCliente = new JMenuItem("Cadastrar Cliente");
		mntmCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//CHAMAR TELA DE CADASTRO DE CLIENTE
					new CadastrarCliente();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnCliente.add(mntmCadastrarCliente);
		
		JMenu mnOrdemServico = new JMenu("Ordem de Servi\u00E7o");
		menuBar.add(mnOrdemServico);
		
		JMenuItem mntmNovaOS = new JMenuItem("Nova OS");
		mntmNovaOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new CadastrarOrdemDeServico();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnOrdemServico.add(mntmNovaOS);
		
		JMenuItem mntmConsultarOS = new JMenuItem("Conultar OS");
		mntmConsultarOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConsultarOrdemDeServico();
			}
		});
		mnOrdemServico.add(mntmConsultarOS);
		
		setVisible(true);
	}
}
