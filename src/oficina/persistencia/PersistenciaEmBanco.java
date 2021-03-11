package oficina.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import oficina.modelo.Cliente;
import oficina.modelo.IVeiculo;
import oficina.modelo.OrdemDeServico;
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
	
	public List<Cliente> getAllClientes() {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		String sql = "select * from clientes";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String telefone = rs.getString("telefone");
				String email = rs.getString("email");
				
				Cliente c = new Cliente(nome, cpf, telefone, email);
				clientes.add(c);
				
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return clientes;
	}
	
	public List<IVeiculo> getAllVeiculos(){
		
		ArrayList<IVeiculo> placas = new ArrayList<IVeiculo>();
		
		String sqlMoto = "select * from veiculoMoto";
		String sqlCarro = "select * from veiculoCarro";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlMoto);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String modelo = rs.getString("modelo");
				String placa = rs.getString("placa");
				String cor = rs.getString("cor");
				
				IVeiculo veiculo = new VeiculoMoto(modelo, placa, cor);
				placas.add(veiculo);
			
			}
			
			pstmt.execute();
			pstmt.close();
			
			pstmt = FabricaConexao.getConnection().prepareStatement(sqlCarro);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				String modelo = rs.getString("modelo");
				String placa = rs.getString("placa");
				String cor = rs.getString("cor");
				
				IVeiculo veiculo = new VeiculoCarro(modelo, placa, cor);
				placas.add(veiculo);
				
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return placas;
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
				+ " values (?,?,?);";
		
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
	
	public void CadastrarOS(OrdemDeServico obj) {
		
		String sqlInserirCliente = "insert into ordemdeservico (descricao, valor, data_entrada, data_saida, forma_pagamento, status, placa_veiculo, nome_cliente)"
				+ " values (?,?,?,?,?,?,?,?);";
		
		try 
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlInserirCliente);
			pstmt.setString(1, obj.getDescricao());
			pstmt.setFloat(2, obj.getValor());
			pstmt.setString(3, obj.getData_Entrada());
			pstmt.setString(4, obj.getData_Saida());
			pstmt.setString(5, obj.getForma_pagamento());
			pstmt.setString(6, obj.getStatus());
			pstmt.setString(7, obj.getVeiculo());
			pstmt.setString(8, obj.getCliente());
			
			pstmt.execute();
			pstmt.close();
			
			System.out.println("ok");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
}
