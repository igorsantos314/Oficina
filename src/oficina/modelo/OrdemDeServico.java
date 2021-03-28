package oficina.modelo;

public class OrdemDeServico {
	
	String cod;
	String Descricao;
	String LaudoTecnico;
	String codigoVenda;
	Float  ValorPecas = 0f;
	Float  ValorMaoDeObra = 0f;
	String Data_Entrada;
	String Data_Saida;
	String forma_pagamento;
	String status;
	
	String placaVeiculo;
	String nomeCliente;

	public OrdemDeServico(String cod, String descricao, String laudoTecnico, String codigoVenda, Float valorPecas,
			Float valorMaoDeObra, String data_Entrada, String data_Saida, String forma_pagamento, String status,
			String placaVeiculo, String nomeCliente) {
		super();
		this.cod = cod;
		this.Descricao = descricao;
		this.LaudoTecnico = laudoTecnico;
		this.codigoVenda = codigoVenda;
		this.ValorPecas = valorPecas;
		this.ValorMaoDeObra = valorMaoDeObra;
		this.Data_Entrada = data_Entrada;
		this.Data_Saida = data_Saida;
		this.forma_pagamento = forma_pagamento;
		this.status = status;
		this.placaVeiculo = placaVeiculo;
		this.nomeCliente = nomeCliente;
	}

	public String getLaudoTecnico() {
		return LaudoTecnico;
	}

	public void setLaudoTecnico(String laudoTecnico) {
		LaudoTecnico = laudoTecnico;
	}

	public String getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(String codigoVenda) {
		this.codigoVenda = codigoVenda;
	}
	
	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public Float getValorPecas() {
		return ValorPecas;
	}

	public void setValorPecas(Float valorPecas) {
		ValorPecas = valorPecas;
	}

	public Float getValorMaoDeObra() {
		return ValorMaoDeObra;
	}

	public void setValorMaoDeObra(Float valorMaoDeObra) {
		ValorMaoDeObra = valorMaoDeObra;
	}

	public String getData_Entrada() {
		return Data_Entrada;
	}

	public void setData_Entrada(String data_Entrada) {
		Data_Entrada = data_Entrada;
	}

	public String getData_Saida() {
		return Data_Saida;
	}

	public void setData_Saida(String data_Saida) {
		Data_Saida = data_Saida;
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

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	
	
}
