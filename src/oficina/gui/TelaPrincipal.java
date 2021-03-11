package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import oficina.modelo.OrdemDeServico;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Panel;

public class TelaPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelaPrincipal(){
		setResizable(false);
		
		setSize(800,600);
		setTitle("SISTEMA DE OFICINAS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnVeiculos = new JMenu("Veiculos");
		menuBar.add(mnVeiculos);
		
		JMenuItem mntmNovoVeiculo = new JMenuItem("Cadastrar Veiculo");
		mntmNovoVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarVeiculo CV = new CadastrarVeiculo();
			}
		});
		
		mnVeiculos.add(mntmNovoVeiculo);
		
		JMenu mnCliente = new JMenu("Clientes");
		menuBar.add(mnCliente);
		
		JMenuItem mntmCadastrarCliente = new JMenuItem("Cadastrar Cliente");
		mntmCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarCliente CC = new CadastrarCliente();
			}
		});
		
		mnCliente.add(mntmCadastrarCliente);
		
		JMenu mnOrdemServico = new JMenu("Ordem de Servi\u00E7o");
		menuBar.add(mnOrdemServico);
		
		JMenuItem mntmNovaOS = new JMenuItem("Nova OS");
		mntmNovaOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarOrdemDeServico OS = new CadastrarOrdemDeServico();
			}
		});
		
		mnOrdemServico.add(mntmNovaOS);
		
		JMenuItem mntmConsultarOS = new JMenuItem("Conultar OS");
		mntmConsultarOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarOrdemDeServico COS = new ConsultarOrdemDeServico();
			}
		});
		mnOrdemServico.add(mntmConsultarOS);
		
		
		setVisible(true);
	}
}
