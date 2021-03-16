package oficina.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.ConstantesUtil;

public class FabricaConexao {
	
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://" + ConstantesUtil.OFICINA_DATABASE_IP 
					+ ":"+ ConstantesUtil.OFICINA_DATABASE_PORT  +"/" + ConstantesUtil.OFICINA_DATABASE_NAME, "postgres", "1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
