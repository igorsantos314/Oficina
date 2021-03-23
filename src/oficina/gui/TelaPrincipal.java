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
	
	public TelaPrincipal(String nivelDeAcesso){
		
		//DATA ATUAL
		LocalDate localDate      = LocalDate.now();
		
		getContentPane().setBackground(SystemColor.menu);
		setResizable(false);
		
		setSize(800,600);
		setTitle("SISTEMA DE OFICINAS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 498, 762, 28);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("VERSION: 2.0.0.0");
		panel.add(lblNewLabel_2);
		
		JLabel lblDate = new JLabel("Date");
		panel.add(lblDate);
		
		//SETAR DATA
		lblDate.setText("Data: "+ localDate.toString());
		
		//Setar Imagem
		ImageIcon imagem = new ImageIcon(getClass().getResource("logo.png")); 
		
		JLabel lblLogo = new JLabel(imagem);
		lblLogo.setBounds(10, 11, 762, 476);
		getContentPane().add(lblLogo);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnVeiculos = new JMenu("Veiculos");
		menuBar.add(mnVeiculos);
		
		JMenuItem mntmNovoVeiculo = new JMenuItem("Cadastrar Veiculo");
		mntmNovoVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//CHAMAR TELA DE CADASTRO DE VEICULO
					new TelaCadastrarVeiculo();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnVeiculos.add(mntmNovoVeiculo);
		
		JMenuItem mntmConsultarVeiculos = new JMenuItem("Consultar Veiculos");
		mntmConsultarVeiculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ABRIR TELA DE CONSULTAR VEICULOS
				new TelaConsultarVeiculos();
			}
		});
		mnVeiculos.add(mntmConsultarVeiculos);
		
		JMenu mnCliente = new JMenu("Clientes");
		menuBar.add(mnCliente);
		
		JMenuItem mntmCadastrarCliente = new JMenuItem("Cadastrar Cliente");
		mntmCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//CHAMAR TELA DE CADASTRO DE CLIENTE
					new TelaCadastrarCliente();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnCliente.add(mntmCadastrarCliente);
		
		JMenuItem mntmConsultarClientes = new JMenuItem("Consultar Clientes");
		mntmConsultarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//CHAMAR TELA DE CCONSULTAR VEICULOS
				new TelaConsultarClientes();
			}
		});
		mnCliente.add(mntmConsultarClientes);
		
		JMenu mnProduto = new JMenu("Produtos");
		menuBar.add(mnProduto);
		
		JMenuItem mntmCadastrarProduto = new JMenuItem("Cadastrar Produto");
		mnProduto.add(mntmCadastrarProduto);
		
		JMenuItem mntmConsultarProdutos = new JMenuItem("Consultar Produtos");
		mnProduto.add(mntmConsultarProdutos);
		
		JMenu mnOrdemServico = new JMenu("Ordens de Servi\u00E7o");
		menuBar.add(mnOrdemServico);
		
		JMenuItem mntmNovaOS = new JMenuItem("Nova OS");
		mntmNovaOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new TelaCadastrarOrdemDeServico();
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
				new TelaConsultarOrdemDeServico();
			}
		});
		mnOrdemServico.add(mntmConsultarOS);
		
		setVisible(true);
	}
}
