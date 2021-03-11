package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		mnOrdemServico.add(mntmNovaOS);
		
		
		setVisible(true);
	}
}
