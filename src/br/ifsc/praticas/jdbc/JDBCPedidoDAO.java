package br.ifsc.praticas.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ifsc.praticas.dao.PedidoDAO;
import br.ifsc.praticas.model.Pedido;
import br.ifsc.praticas.model.Pessoa;

public class JDBCPedidoDAO implements PedidoDAO {

	private static String sql;
	private JDBCUtil banco;

	public JDBCPedidoDAO() {
		banco = JDBCUtil.getInstance();
		banco.conectar();
	}

	@Override
	public void salvar(Pedido pedido) {
		if (banco.isConnected()) {
			sql = "INSERT INTO pedido(PedidoId, NumeroPedido, PessoaID, Valor) VALUES(?,?,?,?)";

			PreparedStatement ps;
			try {
				ps = banco.conectar().prepareStatement(sql);
				ps.setInt(1, pedido.getId());
				ps.setInt(2, pedido.getNumeroPedido());
				ps.setInt(3, pedido.getPessoa().getId());
				ps.setInt(4, pedido.getValor());
				ps.execute();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void remover(Pedido pedido) {
		if (banco.isConnected()) {
			sql = "DELETE FROM pedido WHERE PedidoId=?";
			PreparedStatement ps;
			try {
				ps = banco.conectar().prepareStatement(sql);
				ps.setInt(1, pedido.getId());
				ps.execute();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void atualizar(Pedido pedido) {
		if (banco.isConnected()) {
			sql = "UPDATE pedido SET NumeroPedido=?,Valor=? WHERE PedidoId=?";

			PreparedStatement ps;
			try {
				ps = banco.conectar().prepareStatement(sql);
				ps.setInt(1, pedido.getNumeroPedido());
				ps.setInt(2, pedido.getValor());
				ps.setInt(3, pedido.getId());
				ps.execute();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public List<Pedido> listar() {
		List<Pedido> pedidos = null;
		if (banco.isConnected()) {

			sql = "SELECT * FROM pedido AS pe JOIN pessoa AS ps ON pe.PessoaID = ps.ID";

			ResultSet rs = this.executeQuery(sql, banco.conectar());
			pedidos = new ArrayList<Pedido>();
			try {
				while (rs.next()) {
					pedidos.add(this.getPedidoInfo(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return pedidos;
	}

	@Override
	public Pedido buscarPorId(int id) {
		Pedido pedido = null;
		if (banco.isConnected()) {
			sql = "SELECT" + "	pe.PedidoId, pe.NumeroPedido, pe.PessoaID, pe.Valor,"
					+ " ps.ID, ps.UltimoNome, ps.PrimeiroNome, ps.Idade, ps.Profissao"
					+ " FROM pedido AS pe JOIN pessoa AS ps ON pe.PessoaID = ps.ID AND pe.PedidoId =" + id;
			ResultSet rs = this.executeQuery(sql, banco.conectar());
			try {
				while (rs.next()) {
					pedido = getPedidoInfo(rs);
				}
			} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		return pedido;
	}

	private Pedido getPedidoInfo(ResultSet rs) throws SQLException {
		Pedido pedido = new Pedido();

		pedido.setId(rs.getInt("pe.PedidoId"));
		pedido.setNumeroPedido(rs.getInt("pe.NumeroPedido"));

		Pessoa pessoa = new Pessoa();
		pessoa.setId(Integer.valueOf(rs.getString("ps.ID")));
		pessoa.setIdade(Integer.valueOf(rs.getString("ps.Idade")));
		pessoa.setPrimeiroNome(rs.getString("ps.PrimeiroNome"));
		pessoa.setUltimoNome(rs.getString("ps.UltimoNome"));
		pessoa.setProfissao(rs.getString("ps.Profissao"));

		pedido.setPessoa(pessoa);
		pedido.setValor(rs.getInt("pe.Valor"));
		return pedido;
	}

	private ResultSet executeQuery(String query, Connection conexao) {
		Statement st = null;
		try {
			st = conexao.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

}
