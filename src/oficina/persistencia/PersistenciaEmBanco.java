package oficina.persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import oficina.exception.ClienteJaCadastradoException;
import oficina.exception.VeiculoJaCadastradoException;
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
	
	public boolean realizarLogin(String user, String senha) {
		
		String senhaUser = null;
		String sql = "select senha from users where usuario='" + user + "';";

		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				senhaUser = rs.getString("senha");
				
			}
			
			pstmt.execute();
			pstmt.close();
			
			//VERIFICAR SE A SENHA EST� CORRETA
			if(senhaUser.equals(senha)) {
				
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return false;
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
	
	public List<Cliente> getClientesNome(String nomeCliente) {
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		String sql = "select * from clientes where nome like '%" + nomeCliente + "%';";
		System.out.println(sql);
		
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
		
		String sqlMoto = "select * from veiculo";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlMoto);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String modelo = rs.getString("modelo");
				String placa = rs.getString("placa");
				String cor = rs.getString("cor");
				String ano = rs.getString("ano");
				int km_atual = rs.getInt("km_atual");
				
				IVeiculo veiculo = new VeiculoMoto(modelo, placa, cor, ano, km_atual);
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
	
	public List<IVeiculo> getVeiculoPlacaNome(String placaNome){
		
		ArrayList<IVeiculo> placas = new ArrayList<IVeiculo>();
		
		String sqlMoto = "select * from veiculo where modelo like'%" + placaNome + "%' or placa like'%" + placaNome + "%';";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlMoto);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String modelo = rs.getString("modelo");
				String placa = rs.getString("placa");
				String cor = rs.getString("cor");
				String ano = rs.getString("ano");
				int km_atual = rs.getInt("km_atual");
				
				IVeiculo veiculo = new VeiculoMoto(modelo, placa, cor, ano, km_atual);
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
	
	public ArrayList<OrdemDeServico> getAllOS(){
		
		ArrayList<OrdemDeServico> OrdensServico = new ArrayList<OrdemDeServico>();
		String sql = "select * from ordemdeservico order by codigo asc";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String cod_os = rs.getString("codigo");
				String descricao = rs.getString("descricao");
				Float valorMaoDeObra = rs.getFloat("valor_mao_obra");
				Float valorPecas = rs.getFloat("valor_pecas");
				String data_Entrada = rs.getString("data_entrada");
				String data_Saida = rs.getString("data_saida");
				String pagamento = rs.getString("forma_pagamento");
				String status = rs.getString("status");
				String placa_veiculo = rs.getString("placa_veiculo");
				String nome_Cliente = rs.getString("nome_cliente");
				
				OrdemDeServico os = new OrdemDeServico(cod_os, descricao, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, placa_veiculo, nome_Cliente);
				OrdensServico.add(os);
				
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return OrdensServico;
	}
	
	public OrdemDeServico getOSCod(String cod){
		
		String sql = "select * from ordemdeservico where codigo='" + cod + "'";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String cod_os = rs.getString("codigo");
				String descricao = rs.getString("descricao");
				Float valorMaoDeObra = rs.getFloat("valor_mao_obra");
				Float valorPecas = rs.getFloat("valor_pecas");
				String data_Entrada = rs.getString("data_entrada");
				String data_Saida = rs.getString("data_saida");
				String pagamento = rs.getString("forma_pagamento");
				String status = rs.getString("status");
				String placa_veiculo = rs.getString("placa_veiculo");
				String nome_Cliente = rs.getString("nome_cliente");
				
				OrdemDeServico os = new OrdemDeServico(cod_os, descricao, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, placa_veiculo, nome_Cliente);
				System.out.println(os);
				
				return os;
				
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return null;
	}
	
	public List<OrdemDeServico> getOSPlaca(String nomePlaca){
		
		ArrayList<OrdemDeServico> OrdensServico = new ArrayList<OrdemDeServico>();
		String sql = "select * from ordemdeservico where placa_veiculo LIKE '%" + nomePlaca + "%' or nome_cliente LIKE '%"  + nomePlaca + "%' order by codigo asc;";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String cod = rs.getString("codigo");
				String descricao = rs.getString("descricao");
				Float valorMaoDeObra = rs.getFloat("valor_mao_obra");
				Float valorPecas = rs.getFloat("valor_pecas");
				String data_Entrada = rs.getString("data_entrada");
				String data_Saida = rs.getString("data_saida");
				String pagamento = rs.getString("forma_pagamento");
				String status = rs.getString("status");
				String placa_veiculo = rs.getString("placa_veiculo");
				String nome_Cliente = rs.getString("nome_cliente");
				
				OrdemDeServico os = new OrdemDeServico(cod, descricao, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, placa_veiculo, nome_Cliente);
				OrdensServico.add(os);

			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return OrdensServico;
	}
	
	public void CadastrarCliente(Cliente obj) throws ClienteJaCadastradoException{
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
			throw new ClienteJaCadastradoException("Cliente j� Cadastrado!");
			//JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void CadastrarVeiculo(IVeiculo obj) throws VeiculoJaCadastradoException{
		
		String sqlInserirCliente = "insert into veiculo (modelo, placa, cor, ano, km_atual)"
				+ " values (?,?,?,?,?);";
		
		try 
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlInserirCliente);
			pstmt.setString(1, obj.getModelo());
			pstmt.setString(2, obj.getPlaca());
			pstmt.setString(3, obj.getCor());
			pstmt.setString(4, obj.getAno());
			pstmt.setInt(5, obj.getKm_atual());
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//JOptionPane.showMessageDialog(null, e.getMessage());
			throw new VeiculoJaCadastradoException("Veiculo J� Cadastrado!");
		}
		
	}
	
	public void CadastrarOS(OrdemDeServico obj) {
		
		String sqlInserirCliente = "insert into ordemdeservico (descricao, valor_mao_obra, valor_pecas, data_entrada, data_saida, forma_pagamento, status, placa_veiculo, nome_cliente)"
				+ " values (?,?,?,?,?,?,?,?,?);";
		
		try 
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlInserirCliente);
			pstmt.setString(1, obj.getDescricao());
			pstmt.setFloat( 2, obj.getValorMaoDeObra());
			pstmt.setFloat( 3, obj.getValorPecas());
			pstmt.setString(4, obj.getData_Entrada());
			pstmt.setString(5, obj.getData_Saida());
			pstmt.setString(6, obj.getForma_pagamento());
			pstmt.setString(7, obj.getStatus());
			pstmt.setString(8, obj.getPlacaVeiculo());
			pstmt.setString(9, obj.getNomeCliente());
			
			pstmt.execute();
			pstmt.close();
			
			System.out.println("ok");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	public void UpdateOS(OrdemDeServico obj) {
		
		String sql = "UPDATE ordemdeservico SET descricao = ?, data_entrada= ?, data_saida= ?, valor_mao_obra=?, valor_pecas=?, status= ?, forma_pagamento= ? WHERE codigo = ?;";
		
		try 
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			pstmt.setString(1, obj.getDescricao());
			pstmt.setString(2, obj.getData_Entrada());
			pstmt.setString(3, obj.getData_Saida());
			pstmt.setFloat( 4, obj.getValorMaoDeObra());
			pstmt.setFloat( 5, obj.getValorPecas());
			pstmt.setString(6, obj.getStatus());
			pstmt.setString(7, obj.getForma_pagamento());
			pstmt.setInt(8, Integer.parseInt(obj.getCod()));
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void deleteOS(String cod) {
		String sql = "DELETE FROM ordemdeservico WHERE codigo='" + cod + "'";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			
			pstmt.execute();
			pstmt.close();
			
			System.out.println("Deletado");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
}
