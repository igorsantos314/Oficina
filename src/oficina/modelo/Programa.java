package oficina.modelo;

import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import oficina.gui.TelaLogin;
import oficina.gui.TelaPrincipal;
import oficina.persistencia.FabricaConexao;
import oficina.persistencia.PersistenciaEmBanco;

public class Programa implements Serializable{

	public static void main(String[] args) throws ParseException, NoSuchAlgorithmException, UnsupportedEncodingException {

		new TelaPrincipal("");
		//new TelaLogin();
		
	}
	
}
