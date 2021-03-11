package oficina.persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import oficina.modelo.Cliente;
import oficina.modelo.IVeiculo;
import oficina.modelo.VeiculoCarro;
import oficina.modelo.VeiculoMoto;

public class PersistenciaEmBanco {
	
	private static PersistenciaEmBanco instance;
	
	private PersistenciaEmBanco() {
		// TODO Auto-generated constructor stub
	}
	
	public static PersistenciaEmBanco pegarInstancia()
	{
		if(instance != null)
			return instance;
		else
		{
			return instance = new PersistenciaEmBanco();
		}
	} 
	
	public void CadastrarCliente(Cliente obj) {
		String sqlInserirCliente = "insert into clientes (cpf, nome, telefone, email)"
				+ " values (?,?,?,?);";
		
		try 
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlInserirCliente);
			pstmt.setString(1, obj.getCpf());
			pstmt.setString(2, obj.getNome());
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
	
	public void CadastrarVeiculo(IVeiculo obj) {
		
		String Veiculo = "";
		
		//VERIFICAR QUAL O TIPO DE VEICULO PARA MODFICAR A TABELA
		if(obj instanceof VeiculoCarro) {
			Veiculo = "veiculoCarro";
		}
		
		else if(obj instanceof VeiculoMoto) {
			Veiculo = "veiculoMoto";
		}
		
		String sqlInserirCliente = "insert into " + Veiculo + " (modelo, placa, cor)"
				+ " values (?,?,?,?);";
		
		try 
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlInserirCliente);
			pstmt.setString(1, obj.getModelo());
			pstmt.setString(2, obj.getPlaca());
			pstmt.setString(3, obj.getCor());
			
			pstmt.execute();
			pstmt.close();
			
			System.out.println("ok");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
}
