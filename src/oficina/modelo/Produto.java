package oficina.modelo;

import java.text.DecimalFormat;

public class Produto {
	
	int cod;
	String nome;
	Float valorDeCompra;
	Float valorDeVenda;
	int quantidade;
	
	public Produto(int cod, String nome, Float valorDeCompra, Float valorDeVenda, int quantidade) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.valorDeCompra = valorDeCompra;
		this.valorDeVenda = valorDeVenda;
		this.quantidade = quantidade;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getValorDeCompra() {
		return valorDeCompra;
	}

	public void setValorDeCompra(Float valorDeCompra) {
		this.valorDeCompra = valorDeCompra;
	}

	public Float getValorDeVenda() {
		return valorDeVenda;
	}

	public void setValorDeVenda(Float valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public static String formatarFloat(float numero){
		String retorno = "";
		DecimalFormat formatter = new DecimalFormat("#.00");
		
		try{
			retorno = formatter.format(numero);
		}catch(Exception ex){
			System.err.println("Erro ao formatar numero: " + ex);
		}
		
		return retorno;
	}
}
