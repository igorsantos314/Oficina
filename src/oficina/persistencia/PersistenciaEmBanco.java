package oficina.persistencia;

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
import oficina.modelo.Produto;
import oficina.modelo.ProdutoVendido;
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
			
			//VERIFICAR SE A SENHA ESTÁ CORRETA
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
				String laudo = rs.getString("laudo_tecnico");
				String codigoVenda = rs.getString("codigo_venda");
				Float valorMaoDeObra = rs.getFloat("valor_mao_obra");
				Float valorPecas = rs.getFloat("valor_pecas");
				String data_Entrada = rs.getString("data_entrada");
				String data_Saida = rs.getString("data_saida");
				String pagamento = rs.getString("forma_pagamento");
				String status = rs.getString("status");
				String placa_veiculo = rs.getString("placa_veiculo");
				String nome_Cliente = rs.getString("nome_cliente");
				
				OrdemDeServico os = new OrdemDeServico(cod_os, descricao, laudo, codigoVenda, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, placa_veiculo, nome_Cliente);
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
				String laudo = rs.getString("laudo_tecnico");
				String codigoVenda = rs.getString("codigo_venda");
				Float valorMaoDeObra = rs.getFloat("valor_mao_obra");
				Float valorPecas = rs.getFloat("valor_pecas");
				String data_Entrada = rs.getString("data_entrada");
				String data_Saida = rs.getString("data_saida");
				String pagamento = rs.getString("forma_pagamento");
				String status = rs.getString("status");
				String placa_veiculo = rs.getString("placa_veiculo");
				String nome_Cliente = rs.getString("nome_cliente");
				
				OrdemDeServico os = new OrdemDeServico(cod_os, descricao, laudo, codigoVenda, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, placa_veiculo, nome_Cliente);
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
	
	public ArrayList<OrdemDeServico> getOSPlaca(String nomePlaca){
		
		ArrayList<OrdemDeServico> OrdensServico = new ArrayList<OrdemDeServico>();
		String sql = "select * from ordemdeservico where placa_veiculo LIKE '%" + nomePlaca + "%' or nome_cliente LIKE '%"  + nomePlaca + "%' order by codigo asc;";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String cod = rs.getString("codigo");
				String descricao = rs.getString("descricao");
				String laudo = rs.getString("laudo_tecnico");
				String codigoVenda = rs.getString("codigo_venda");
				Float valorMaoDeObra = rs.getFloat("valor_mao_obra");
				Float valorPecas = rs.getFloat("valor_pecas");
				String data_Entrada = rs.getString("data_entrada");
				String data_Saida = rs.getString("data_saida");
				String pagamento = rs.getString("forma_pagamento");
				String status = rs.getString("status");
				String placa_veiculo = rs.getString("placa_veiculo");
				String nome_Cliente = rs.getString("nome_cliente");
				
				OrdemDeServico os = new OrdemDeServico(cod, descricao, laudo, codigoVenda, valorMaoDeObra, valorPecas, data_Entrada, data_Saida, pagamento, status, placa_veiculo, nome_Cliente);
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
	
	public ArrayList<Produto> getAllProdutos(){
		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		String sql = "select * from produto;";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int cod = rs.getInt("cod");
				String nome = rs.getString("nome");
				Float valorDeCompra = rs.getFloat("valorDeCompra");
				Float valorDeVenda = rs.getFloat("valorDeVenda");
				int quantidade = rs.getInt("quantidade");
				
				Produto p = new Produto(cod, nome, valorDeCompra, valorDeVenda, quantidade);
				produtos.add(p);
				
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return produtos;

	}
	
	public ArrayList<Produto> getProdutoNome(String nome){
		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		String sql = "select * from produto where nome like '%" + nome + "%';";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int cod = rs.getInt("cod");
				String nomeProd = rs.getString("nome");
				Float valorDeCompra = rs.getFloat("valorDeCompra");
				Float valorDeVenda = rs.getFloat("valorDeVenda");
				int quantidade = rs.getInt("quantidade");
				
				Produto p = new Produto(cod, nomeProd, valorDeCompra, valorDeVenda, quantidade);
				produtos.add(p);
				
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return produtos;

	}
	
	public int getLastIdVenda() {
		
		//PEGA O ULTIMO INDICE DA VENDA
		String sql = "select max(codVenda) as quantidade from venderprodutos";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int quant = rs.getInt("quantidade");
				
				return quant;
				
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return 0;
		
	}
	
	public ArrayList<ProdutoVendido> getVendaID(int id){
		
		ArrayList<ProdutoVendido> listaDeProdutos = new ArrayList<ProdutoVendido>();
		
		String sql = "select * from venderprodutos where codVenda=" + id + ";";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {

				int codVenda = rs.getInt("codvenda");
				int codProduto = rs.getInt("codproduto");
				String nomeProd = rs.getString("nomeProduto");
				Float valor = rs.getFloat("valorProduto");
				int quant = rs.getInt("quantidade");
				String data = rs.getString("dataCompra");
				
				ProdutoVendido pv = new ProdutoVendido(codVenda, codProduto, nomeProd, valor, quant, data);
				listaDeProdutos.add(pv);
				
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return listaDeProdutos;
	}
	
	public ArrayList<ProdutoVendido> getAllVendas(){
		
		ArrayList<ProdutoVendido> listaDeProdutos = new ArrayList<ProdutoVendido>();
		
		String sql = "select * from venderprodutos order by codvenda asc";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {

				int codVenda = rs.getInt("codvenda");
				int codProduto = rs.getInt("codproduto");
				String nomeProd = rs.getString("nomeProduto");
				Float valor = rs.getFloat("valorProduto");
				int quant = rs.getInt("quantidade");
				String data = rs.getString("dataCompra");
				
				ProdutoVendido pv = new ProdutoVendido(codVenda, codProduto, nomeProd, valor, quant, data);
				listaDeProdutos.add(pv);
				
			}
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return listaDeProdutos;
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ClienteJaCadastradoException("Cliente já Cadastrado!");
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
			throw new VeiculoJaCadastradoException("Veiculo Já Cadastrado!");
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	public void CadastrarProduto(Produto obj){
		
		String sqlInserirCliente = "insert into produto (nome, valorDeCompra, valorDeVenda, quantidade)"
				+ " values (?,?,?,?);";
		
		try 
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlInserirCliente);
			pstmt.setString(1, obj.getNome());
			pstmt.setFloat(2, obj.getValorDeCompra());
			pstmt.setFloat(3, obj.getValorDeVenda());
			pstmt.setInt(4, obj.getQuantidade());
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	public void cadastrarVenda(ArrayList<ProdutoVendido> listaDeProdutos) {
		
		String sqlInserirVenda = "insert into venderprodutos"
				+ " values (?,?,?,?,?,?);";
		
		try 
		{
			//VARRER A LISTA DE PRODUTO ADICIONANDO AO BD
			for(ProdutoVendido pv : listaDeProdutos) {
				PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sqlInserirVenda);
				
				pstmt.setInt(1, pv.getCodVenda());
				pstmt.setInt(2, pv.getCodProd());
				pstmt.setString(3, pv.getNome());
				pstmt.setFloat(4, pv.getValorUnd());
				pstmt.setInt(5, pv.getQuantidade());
				pstmt.setString(6, pv.getData());
				
				pstmt.execute();
				pstmt.close();
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}
	
	
	public void UpdateOS(OrdemDeServico obj) {
		
		String sql = "UPDATE ordemdeservico SET descricao = ?, laudo_tecnico = ?, codigo_venda = ?, data_entrada= ?, data_saida= ?, valor_mao_obra=?, valor_pecas=?, status= ?, forma_pagamento= ? WHERE codigo = ?;";
		
		try 
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			pstmt.setString(1, obj.getDescricao());
			pstmt.setString(2, obj.getLaudoTecnico());
			pstmt.setString(3, obj.getCodigoVenda());
			pstmt.setString(4, obj.getData_Entrada());
			pstmt.setString(5, obj.getData_Saida());
			pstmt.setFloat( 6, obj.getValorMaoDeObra());
			pstmt.setFloat( 7, obj.getValorPecas());
			pstmt.setString(8, obj.getStatus());
			pstmt.setString(9, obj.getForma_pagamento());
			pstmt.setInt(10, Integer.parseInt(obj.getCod()));
			
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void deleteCliente(String cliente) {
		String sql = "DELETE FROM clientes WHERE cpf='" + cliente + "';";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void deleteVeiculo(String placa) {
		
		String sql = "DELETE FROM veiculo WHERE placa='" + placa + "';";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void deleteProduto(int cod) {
		String sql = "DELETE FROM produto WHERE cod=" + cod + ";";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void deleteVenda(int codVenda) {
		String sql = "DELETE FROM venderprodutos WHERE codvenda=" + codVenda + ";";
		
		try
		{
			PreparedStatement pstmt = FabricaConexao.getConnection().prepareStatement(sql);
			
			pstmt.execute();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
}
