package oficina.facade;

import java.time.LocalDate;
import java.util.Date;

import oficina.exception.ClienteJaCadastradoException;
import oficina.exception.VeiculoJaCadastradoException;
import oficina.impressao.Impressao;
import oficina.modelo.Cliente;
import oficina.modelo.IVeiculo;
import oficina.modelo.OrdemDeServico;
import oficina.modelo.VeiculoCarro;
import oficina.modelo.VeiculoMoto;
import oficina.persistencia.PersistenciaEmBanco;

public class Conexao {
	
	public static Conexao instance;
	public static LocalDate localDate = LocalDate.now();
	
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
	
	public void salvarCliente(String cpf, String nome, String telefone, String email) throws ClienteJaCadastradoException{
		
		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().CadastrarCliente(new Cliente(nome, cpf, telefone, email));
	}
	
	public void salvarVeiculo(String placa, String modelo, String cor, String ano, int km_atual) throws VeiculoJaCadastradoException {
		
		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().CadastrarVeiculo(new VeiculoMoto(modelo, placa, cor, ano, km_atual));
		
	}
	
	public void salvarOS(String descricao, Float valor, String data_Entrada, String data_Saida, String forma_pagamento, String status, String veiculo, String cliente) {

		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().CadastrarOS(new OrdemDeServico(null, descricao, valor, data_Entrada, data_Saida, forma_pagamento, status, veiculo, cliente));
	}
	
	public void atualizarOS(String cod, String descricao, Float valor, String data_Entrada, String data_Saida, String forma_pagamento, String status, String veiculo, String cliente) {
		
		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().UpdateOS(new OrdemDeServico(cod, descricao, valor, data_Entrada, data_Saida, forma_pagamento, status, veiculo, cliente));
	}
	
	public void imprimirOS(String descricao, Float valor, String data_Entrada, String data_Saida, String pagamento, String status, String placa, String nome_Cliente) {
		
		//TEXTO DE IMPRESS�O DA OS
		String textToPrint = 	"                                ORDEM DE SERVI�O\n"
							 + 	"--------------------------------------------------------------------------------\n"
							 +  "Respons�vel:                                            Welson Sandro           \n" 
							 + 	"Cidade:                                                 Belo Jardim             \n" 
							 +  "Bairro:                                                 Santo Antonio           \n" 
							 +  "Endere�o:                                                                       \n"
							 + 	"Ponto de Refer�ncia:                                    Rua do SESC             \n"
							 + 	"--------------------------------------------------------------------------------\n"
							 + 	"Data de Entrada:                                        " + data_Entrada     + "\n"
							 +  "Data de Saida:                                          " + data_Saida       + "\n"
							 +	"--------------------------------------------------------------------------------\n"
							 + 	"Cliente:                                                " + nome_Cliente     + "\n"
							 +  "Veiculo:                                                " + placa            + "\n"
							 +  "Status:                                                 " + status           + "\n"
							 +	"--------------------------------------------------------------------------------\n"
							 + 	"Forma de Pag.:                                          " + pagamento        + "\n"
							 +  "Valor m�o de Obra:                                      R$ " + valor         + "\n"
							 + 	"Valor em Pe�as:                                         R$ " + valor         + "\n"
							 +	"--------------------------------------------------------------------------------\n"
							 + 	"Descrcao:\n"
							 + 	"      " + descricao + "\n\n"
							 +	"--------------------------------------------------------------------------------\n"
							 + 	"Data da Impress�o:                                      "+ localDate.toString() + "\n\n"
							 + 	"                                  Volte Sempre !                                \n";
		
		//FUN��O DE IMPRIMIR
		new Impressao().imprime(textToPrint);
	}
	
}
