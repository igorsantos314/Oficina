package oficina.modelo;

public class Financeiro {
	
	@Override
	public String toString() {
		return "Financeiro [totalMaoObra=" + totalMaoObra + ", totalVendaPecas=" + totalVendaPecas + ", totalLucro="
				+ totalLucro + ", mes=" + mes + ", ano=" + ano + "]";
	}

	private Float totalMaoObra;
	private Float totalVendaPecas;
	private Float totalLucro;
	
	private String mes;
	private String ano;
	
	public Financeiro(Float totalMaoObra, Float totalVendaPecas, Float totalLucro) {
		super();
		this.totalMaoObra = totalMaoObra;
		this.totalVendaPecas = totalVendaPecas;
		this.totalLucro = totalLucro;
	}

	public Float getTotalMaoObra() {
		return totalMaoObra;
	}

	public void setTotalMaoObra(Float totalMaoObra) {
		this.totalMaoObra = totalMaoObra;
	}

	public Float getTotalVendaPecas() {
		return totalVendaPecas;
	}

	public void setTotalVendaPecas(Float totalVendaPecas) {
		this.totalVendaPecas = totalVendaPecas;
	}

	public Float getTotalLucro() {
		return totalLucro;
	}

	public void setTotalLucro(Float totalLucro) {
		this.totalLucro = totalLucro;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

}
