package oficina.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class TelaPrincipal extends JFrame{
	
	public TelaPrincipal(){
		setResizable(false);
		
		setSize(800,600);
		setTitle("SISTEMA DE OFICINAS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnVeiculos = new JMenu("Veiculos");
		menuBar.add(mnVeiculos);
		
		JMenuItem mntmCarro = new JMenuItem("Cadastrar Carro");
		mnVeiculos.add(mntmCarro);
		
		JMenuItem mntmMoto = new JMenuItem("Cadastrar Moto");
		mnVeiculos.add(mntmMoto);
		
		JMenu mnCliente = new JMenu("Clientes");
		menuBar.add(mnCliente);
		
		JMenuItem mntmCadastrarCliente = new JMenuItem("Cadastrar Cliente");
		mnCliente.add(mntmCadastrarCliente);
		
		JMenu mnOrdemServico = new JMenu("Ordem de Servi\u00E7o");
		menuBar.add(mnOrdemServico);
		
		JMenuItem mntmNovaOS = new JMenuItem("Nova OS");
		mnOrdemServico.add(mntmNovaOS);
		
		
		setVisible(true);
	}
}
