package oficina.modelo;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IVeiculo meu = new VeiculoCarro("Palio", "NES");
		IVeiculo teu = new VeiculoMoto("BROS", "TES");
		
		Cliente c = new Cliente("Igor", "654", "81982333074", "i@gmail.com");
		
		System.out.println(meu);
		System.out.println(teu);
		System.out.println(c);
		
		OrdemDeServico os = new OrdemDeServico("Problema de Junta", "11/03/2021", meu, c);
		
		System.out.println(os);
	}

}
