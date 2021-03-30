package oficina.modelo;

public class VeiculoMoto implements IVeiculo{
	
	String modelo;
	String placa;
	String cor;
	String ano;
	String chassi;

	int km_atual;
	
	public VeiculoMoto(String modelo, String placa, String chassi, String cor, String ano, int km_atual) {
		super();
		this.modelo = modelo;
		this.placa = placa;
		this.chassi = chassi;
		this.cor = cor;
		this.ano = ano;
		this.km_atual = km_atual;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
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
		VeiculoMoto other = (VeiculoMoto) obj;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return placa + " - " + modelo;
	}

	public String getModelo() {
		return modelo;
	}
	
	public String getPlaca() {
		return placa;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return cor;
	}
	
	@Override
	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
	@Override
	public int getKm_atual() {
		return km_atual;
	}

	public void setKm_atual(int km_atual) {
		this.km_atual = km_atual;
	}
	
	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	
}
