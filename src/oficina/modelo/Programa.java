package oficina.modelo;

import oficina.gui.TelaPrincipal;
import oficina.persistencia.FabricaConexao;
import oficina.persistencia.PersistenciaEmBanco;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PersistenciaEmBanco.pegarInstancia().getAllClientes();
		TelaPrincipal tp = new TelaPrincipal();
		
	}

}
