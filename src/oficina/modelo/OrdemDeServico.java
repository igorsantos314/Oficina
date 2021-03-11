package oficina.modelo;

public class OrdemDeServico {
	
	String Descricao;
	Float Valor = 0f;
	String Data_Entrada;
	String Data_Saida;
	
	IVeiculo veiculo;
	Cliente cliente;
	
	public OrdemDeServico(String descricao, String data_Entrada, IVeiculo veiculo, Cliente cliente) {
		Descricao = descricao;
		Data_Entrada = data_Entrada;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemDeServico other = (OrdemDeServico) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
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
	
}
