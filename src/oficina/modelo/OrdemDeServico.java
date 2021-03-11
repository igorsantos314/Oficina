package oficina.modelo;

public class OrdemDeServico {
	
	String Descricao;
	Float Valor = 0f;
	String Data_Entrada;
	String Data_Saida;
	String forma_pagamento;
	String status;
	
	IVeiculo veiculo;
	Cliente cliente;
	
	public OrdemDeServico(String descricao, String data_Entrada, IVeiculo veiculo, Cliente cliente) {
		Descricao = descricao;
		Data_Entrada = data_Entrada;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "OrdemDeServico [Descricao=" + Descricao + ", Valor=" + Valor + ", Data_Entrada=" + Data_Entrada
				+ ", Data_Saida=" + Data_Saida + ", veiculo=" + veiculo + ", cliente=" + cliente + "]";
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

	public IVeiculo getVeiculo() {
		return veiculo;
	}

	public Cliente getCliente() {
		return cliente;
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
