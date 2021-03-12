package oficina.modelo;

public class OrdemDeServico {
	
	String cod;
	String Descricao;
	Float  Valor = 0f;
	String Data_Entrada;
	String Data_Saida;
	String forma_pagamento;
	String status;
	
	String placaVeiculo;
	String nomeCliente;

	public OrdemDeServico(String cod, String descricao, Float valor, String data_Entrada, String data_Saida, String forma_pagamento,
			String status, String veiculo, String cliente) {
		super();
		this.cod = cod;
		this.Descricao = descricao;
		this.Valor = valor;
		this.Data_Entrada = data_Entrada;
		this.Data_Saida = data_Saida;
		this.forma_pagamento = forma_pagamento;
		this.status = status;
		this.placaVeiculo = veiculo;
		this.nomeCliente = cliente;
	}

	@Override
	public String toString() {
		return "OrdemDeServico [Descricao=" + Descricao + ", Valor=" + Valor + ", Data_Entrada=" + Data_Entrada
				+ ", Data_Saida=" + Data_Saida + ", veiculo=" + placaVeiculo + ", cliente=" + nomeCliente + "]";
	}

	public String getCod() {
		return cod;
	}

	public String getDescricao() {
		return Descricao;
	}

	public Float getValor() {
		return Valor;
	}

	public String getData_Entrada() {
		return Data_Entrada;
	}

	public String getData_Saida() {
		return Data_Saida;
	}

	public String getForma_pagamento() {
		return forma_pagamento;
	}

	public String getStatus() {
		return status;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	
}
