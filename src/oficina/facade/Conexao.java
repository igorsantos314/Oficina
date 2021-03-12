package oficina.facade;

import oficina.modelo.Cliente;
import oficina.modelo.IVeiculo;
import oficina.modelo.OrdemDeServico;
import oficina.modelo.VeiculoCarro;
import oficina.modelo.VeiculoMoto;
import oficina.persistencia.PersistenciaEmBanco;

public class Conexao {
	
	public static Conexao instance;
	
	private Conexao() {
		// TODO Auto-generated constructor stub
	}
	
	public static Conexao pegarInstancia()
	{
		if(instance != null)
			return instance;
		else
		{
			return instance = new Conexao();
		}
	}
	
	public void salvarCliente(String cpf, String nome, String telefone, String email){
		
		//RECEBE O CLIENTE
		Cliente cliente = new Cliente(nome, cpf, telefone, email);
		
		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().CadastrarCliente(cliente);
	}
	
	public void salvarVeiculo(String placa, String modelo, String cor, String veiculo) {
		
		IVeiculo VeiculoCadastrado = null;
		
		if(veiculo == "Moto") {
			VeiculoCadastrado = new VeiculoMoto(modelo, placa, cor);
		}
		else if(veiculo == "Carro") {
			VeiculoCadastrado = new VeiculoCarro(modelo, placa, cor);
		}
		
		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().CadastrarVeiculo(VeiculoCadastrado);
		
	}
	
	public void salvarOS(String descricao, Float valor, String data_Entrada, String data_Saida, String forma_pagamento, String status, String veiculo, String cliente) {
		
		OrdemDeServico os = new OrdemDeServico("", descricao, valor, data_Entrada, data_Saida, forma_pagamento, status, veiculo, cliente);
		
		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().CadastrarOS(os);
	}
	
	public void atualizarOS(String cod, String descricao, Float valor, String data_Entrada, String data_Saida, String forma_pagamento, String status, String veiculo, String cliente) {
		OrdemDeServico os = new OrdemDeServico(cod, descricao, valor, data_Entrada, data_Saida, forma_pagamento, status, veiculo, cliente);
		
		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().UpdateOS(os);
	}
	
}
