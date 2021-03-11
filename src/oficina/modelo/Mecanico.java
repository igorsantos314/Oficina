package oficina.modelo;

public class Mecanico {
	
	String nome;
	String especialidade;
	String telefone;
	
	public Mecanico(String nome, String especialidade, String telefone) {
		super();
		this.nome = nome;
		this.especialidade = especialidade;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public String getTelefone() {
		return telefone;
	}

	@Override
	public String toString() {
		return "Mecanico [nome=" + nome + ", especialidade=" + especialidade + ", telefone=" + telefone + "]";
	}

}
