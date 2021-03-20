package oficina.modelo;

import java.util.Date;

public class OrdemDeServico {
	
	String cod;
	String Descricao;
	Float  ValorPecas = 0f;
	Float  ValorMaoDeObra = 0f;
	String Data_Entrada;
	String Data_Saida;
	String forma_pagamento;
	String status;
	
	String placaVeiculo;
	String nomeCliente;

	public OrdemDeServico(String cod, String descricao, Float ValorMaoDeObra, Float ValorPecas, String data_Entrada, String data_Saida, String forma_pagamento,
			String status, String veiculo, String cliente) {
		super();
		this.cod = cod;
		this.Descricao = descricao;
		this.ValorMaoDeObra = ValorMaoDeObra;
		this.ValorPecas = ValorPecas;
		this.Data_Entrada = data_Entrada;
		this.Data_Saida = data_Saida;
		this.forma_pagamento = forma_pagamento;
		this.status = status;
		this.placaVeiculo = veiculo;
		this.nomeCliente = cliente;
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
