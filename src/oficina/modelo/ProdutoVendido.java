package oficina.modelo;

public class ProdutoVendido {

	int codVenda;
	int codProd;
	String nome;
	Float valorUnd;
	int quantidade;
	Float valorTotal;
	
	public ProdutoVendido(int codVenda, int codProd, String nome, Float valorUnd, int quantidade) {
		super();
		this.codVenda = codVenda;
		this.codProd = codProd;
		this.nome = nome;
		this.valorUnd = valorUnd;
		this.quantidade = quantidade;
		this.valorTotal = quantidade * valorUnd;
	}

	public int getCodVenda() {
		return codVenda;
	}

	public int getCodProd() {
		return codProd;
	}

	public String getNome() {
		return nome;
	}

	public Float getValorUnd() {
		return valorUnd;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Float getValorTotal() {
		return valorTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codProd;
		result = prime * result + codVenda;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		ProdutoVendido other = (ProdutoVendido) obj;
		if (codProd != other.codProd)
			return false;
		if (codVenda != other.codVenda)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
}
