package oficina.persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import oficina.modelo.Cliente;

public class PersistenciaEmBancoCliente {
	
	private static PersistenciaEmBancoCliente instance;
	
	private PersistenciaEmBancoCliente() {
		// TODO Auto-generated constructor stub
	}
	
	public static PersistenciaEmBancoCliente pegarInstancia()
	{
		if(instance != null)
			return instance;
		else
		{
			return instance = new PersistenciaEmBancoCliente();
		}
	}
	
	public void CadastrarCliente(Cliente obj) {
		String sqlInserirCliente = "insert into clientes (cpf, nome, telefone, email)"
				+ " values (?,?,?,?);";
		
		try 
		{
			System.out.println("ok");
			
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlInserirCliente);
			pstmt.setString(1, obj.getNome());
			pstmt.setString(2, obj.getCpf());
			pstmt.setString(3, obj.getTelefone());
			pstmt.setString(4, obj.getEmail());
			
			pstmt.execute();
			pstmt.close();
			
			System.out.println("ok");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
}
