package oficina.modelo;
import java.io.Serializable;
import java.time.LocalDate;

import oficina.gui.TelaLogin;
import oficina.gui.TelaPrincipal;
import oficina.persistencia.FabricaConexao;
import oficina.persistencia.PersistenciaEmBanco;

public class Programa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args){
		
		//System.out.println("OI");
		new TelaPrincipal("");
		//new TelaLogin();
		
	}
	
}
