package oficina.facade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import oficina.exception.ClienteJaCadastradoException;
import oficina.exception.VeiculoJaCadastradoException;
import oficina.impressao.Impressao;
import oficina.modelo.Cliente;
import oficina.modelo.IVeiculo;
import oficina.modelo.OrdemDeServico;
import oficina.modelo.Produto;
import oficina.modelo.ProdutoVendido;
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
	
	public void salvarOS(String descricao, Float valorMaoDeObra, Float valorPecas,  String data_Entrada, String data_Saida, String forma_pagamento, String status, String veiculo, String cliente) {

		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().CadastrarOS(new OrdemDeServico(null, descricao, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, forma_pagamento, status, veiculo, cliente));
	}
	
	public void salvarProduto(int cod, String nome, Float valorDeCompra, Float valorDeVenda, int quantidade) {
		
		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().CadastrarProduto(new Produto(cod, nome, valorDeCompra, valorDeVenda, quantidade));
	}
	
	public void atualizarOS(String cod, String descricao, Float valorMaoDeObra, Float valorPecas, String data_Entrada, String data_Saida, String forma_pagamento, String status, String veiculo, String cliente) {
		
		//CHAMAR O PACOTE DE PERSISTENCIA
		PersistenciaEmBanco.pegarInstancia().UpdateOS(new OrdemDeServico(cod, descricao, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, forma_pagamento, status, null, null));
	}
	
	public void imprimirOS(String descricao, Float valorMaoDeObra, Float valorPecas, String data_Entrada, String data_Saida, String pagamento, String status, String placa, String nome_Cliente) {
		
		//TEXTO DE IMPRESSÃO DA OS
		String textToPrint = 	"                                ORDEM DE SERVIÇO\n"
							 + 	"--------------------------------------------------------------------------------\n"
							 +  "Responsável:                                            Welson Sandro           \n" 
							 + 	"Cidade:                                                 Belo Jardim             \n" 
							 +  "Bairro:                                                 Santo Antonio           \n" 
							 +  "Endereço:                                                                       \n"
							 + 	"Ponto de Referência:                                    Rua do SESC             \n"
							 + 	"--------------------------------------------------------------------------------\n"
							 + 	"Data de Entrada:                                        " + data_Entrada     + "\n"
							 +  "Data de Saida:                                          " + data_Saida       + "\n"
							 +	"--------------------------------------------------------------------------------\n"
							 + 	"Cliente:                                                " + nome_Cliente     + "\n"
							 +  "Veiculo:                                                " + placa            + "\n"
							 +  "Status:                                                 " + status           + "\n"
							 +	"--------------------------------------------------------------------------------\n"
							 + 	"Forma de Pagamento:                                     " + pagamento        + "\n"
							 +  "Valor mão de Obra:                                      R$ " + valorMaoDeObra+ "\n"
							 + 	"Valor em Peças:                                         R$ " + valorPecas    + "\n"
							 +	"--------------------------------------------------------------------------------\n"
							 + 	"Descrcao:\n"
							 + 	"      " + descricao + "\n\n"
							 +	"--------------------------------------------------------------------------------\n"
							 + 	"Data da Impressão:                                      "+ localDate.toString() + "\n\n"
							 + 	"                                  Volte Sempre !                                \n";
		
		//FUNÇÃO DE IMPRIMIR
		new Impressao().imprime(textToPrint);
	}
	
	public void imprimirVenda(ArrayList<ProdutoVendido> listaDeProdutos) {
		
		String textToPrint = "VENDA DE PRODUTOS - W MOTOS\n\n"
						   + "CODIGO DA VENDA: " + listaDeProdutos.get(0).getCodVenda() + "\n\n";
		
		//VALOR TOTAL DA COMPRA
		Float total = 0f;
		
		//VARRER LISTA DE PRODUTOS
		for(ProdutoVendido pv : listaDeProdutos) {
			
			total += pv.getValorTotal();
			textToPrint += pv.getNome() + " " + pv.getQuantidade() + " " + pv.getValorTotal() + "\n"; 
		}
		
		textToPrint += 	"\nTotal: R$" + total +"\n"
					+ 	"Data da Compra: " + listaDeProdutos.get(0).getData() + "\n";
		
		//FUNÇÃO DE IMPRIMIR
		new Impressao().imprime(textToPrint);
	}
	
}
