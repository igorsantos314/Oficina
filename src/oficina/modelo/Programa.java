package oficina.modelo;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import oficina.gui.TelaPrincipal;
import oficina.persistencia.FabricaConexao;
import oficina.persistencia.PersistenciaEmBanco;

public class Programa implements Serializable{

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		//PersistenciaEmBanco.pegarInstancia().getAllClientes();
		//PersistenciaEmBanco.pegarInstancia().getAllVeiculos();
		//PersistenciaEmBanco.pegarInstancia().getOS("nes");
		//PersistenciaEmBanco.pegarInstancia().getOSCod("2");
		
		new TelaPrincipal();
		//SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		//java.util.Date dataFormatada = formato.parse("16/03/2021"); 
		//System.out.println(dataFormatada);
		
		//Connection con = FabricaConexao.getConnection();
		//String buildPath = "./Relatorios/Blank_A4.jasper";   
		
		//Map parametros = new HashMap();
		
		//JasperPrint print = JasperFillManager.fillReport(buildPath, null, con);
	}
	
}
