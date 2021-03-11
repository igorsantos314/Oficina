package oficina.modelo;

public class VeiculoMoto implements IVeiculo{
	
	String modelo;
	String placa;
	public VeiculoMoto(String modelo, String placa) {
		super();
		this.modelo = modelo;
		this.placa = placa;
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
		return "VeiculoMoto [modelo=" + modelo + ", placa=" + placa + "]";
	}

	public String getModelo() {
		return modelo;
	}

	public String getPlaca() {
		return placa;
	}

	@Override
	public String getPlace() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
