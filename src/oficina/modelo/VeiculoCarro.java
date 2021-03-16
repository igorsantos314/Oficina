package oficina.modelo;

public class VeiculoCarro implements IVeiculo{
	
	String modelo;
	String placa;
	String cor;
	
	public VeiculoCarro(String modelo, String placa, String cor) {
		this.modelo = modelo;
		this.placa = placa;
		this.cor = cor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		VeiculoCarro other = (VeiculoCarro) obj;
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

}
