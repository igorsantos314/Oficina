package oficina.modelo;

public class OrdemDeServico {
	
	String Descricao;
	Float Valor = 0f;
	String Data_Entrada;
	String Data_Saida;
	String forma_pagamento;
	String status;
	
	String placaVeiculo;
	String nomeCliente;

	public OrdemDeServico(String descricao, Float valor, String data_Entrada, String data_Saida, String forma_pagamento,
			String status, String veiculo, String cliente) {
		super();
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

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public Float getValor() {
		return Valor;
	}

	public void setValor(Float valor) {
		Valor = valor;
	}

	public String getData_Entrada() {
		return Data_Entrada;
	}

	public String getData_Saida() {
		return Data_Saida;
	}

	public String getVeiculo() {
		return placaVeiculo;
	}

	public String getCliente() {
		return nomeCliente;
	}

	public String getForma_pagamento() {
		return forma_pagamento;
	}

	public void setForma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
