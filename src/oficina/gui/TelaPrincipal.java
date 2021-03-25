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
		
		getContentPane().setBackground(Color.BLACK);
		setResizable(false);
		
		setSize(1043,728);
		setTitle("SISTEMA DE OFICINAS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 622, 1005, 28);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("VERSION: 2.0.0.0");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setForeground(Color.WHITE);
		panel.add(lblNewLabel_2);
		
		//Setar Imagem
		ImageIcon imagem = new ImageIcon(getClass().getResource("logo.jpeg")); 
		
		JLabel lblLogo = new JLabel(imagem);
		lblLogo.setBounds(10, 11, 1005, 600);
		getContentPane().add(lblLogo);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		JMenu mnVeiculos = new JMenu("Veiculos");
		mnVeiculos.setForeground(Color.BLACK);
		mnVeiculos.setBackground(Color.WHITE);
		menuBar.add(mnVeiculos);
		
		JMenuItem mntmNovoVeiculo = new JMenuItem("Cadastrar Veiculo");
		mntmNovoVeiculo.setBackground(Color.WHITE);
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
		mntmConsultarVeiculos.setBackground(Color.WHITE);
		mntmConsultarVeiculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ABRIR TELA DE CONSULTAR VEICULOS
				new TelaConsultarVeiculos();
			}
		});
		mnVeiculos.add(mntmConsultarVeiculos);
		
		JMenu mnCliente = new JMenu("Clientes");
		mnCliente.setForeground(Color.BLACK);
		mnCliente.setBackground(Color.WHITE);
		menuBar.add(mnCliente);
		
		JMenuItem mntmCadastrarCliente = new JMenuItem("Cadastrar Cliente");
		mntmCadastrarCliente.setBackground(Color.WHITE);
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
		mntmConsultarClientes.setBackground(Color.WHITE);
		mntmConsultarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//CHAMAR TELA DE CCONSULTAR VEICULOS
				new TelaConsultarClientes();
			}
		});
		mnCliente.add(mntmConsultarClientes);
		
		JMenu mnProduto = new JMenu("Produtos");
		mnProduto.setForeground(Color.BLACK);
		mnProduto.setBackground(Color.WHITE);
		menuBar.add(mnProduto);
		
		JMenuItem mntmCadastrarProduto = new JMenuItem("Cadastrar Produto");
		mntmCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CHAMAR TELA DE CADASTRAR PRODUTO
				new TelaCadastrarProduto();
			}
		});
		mntmCadastrarProduto.setBackground(Color.WHITE);
		mnProduto.add(mntmCadastrarProduto);
		
		JMenuItem mntmConsultarProdutos = new JMenuItem("Consultar Produtos");
		mntmConsultarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CHAMAR TELA DE CONSULTAR PRODUTOS
				new TelaConsultarProdutos();
			}
		});
		mntmConsultarProdutos.setBackground(Color.WHITE);
		mnProduto.add(mntmConsultarProdutos);
		
		JMenu mnOrdemServico = new JMenu("Ordens de Servi\u00E7o");
		mnOrdemServico.setForeground(Color.BLACK);
		mnOrdemServico.setBackground(Color.WHITE);
		menuBar.add(mnOrdemServico);
		
		JMenuItem mntmNovaOS = new JMenuItem("Nova OS");
		mntmNovaOS.setBackground(Color.WHITE);
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
		mntmConsultarOS.setBackground(Color.WHITE);
		mntmConsultarOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaConsultarOrdemDeServico();
			}
		});
		mnOrdemServico.add(mntmConsultarOS);
		
		JMenu mnSetorVendas = new JMenu("Setor de Vendas");
		mnSetorVendas.setForeground(Color.BLACK);
		mnSetorVendas.setBackground(Color.WHITE);
		menuBar.add(mnSetorVendas);
		
		JMenuItem mntmVenderProdutos = new JMenuItem("Vender Produtos");
		mntmVenderProdutos.setBackground(Color.WHITE);
		mntmVenderProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//CHAMAR TELA DE VENDER PRODUTO
				new TelaSetorDeVendas();
			}
		});
		mnSetorVendas.add(mntmVenderProdutos);
		
		setVisible(true);
	}
}
