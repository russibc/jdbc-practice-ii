package br.ifsc.praticas.main;

import java.util.List;

import br.ifsc.praticas.dao.PedidoDAO;
import br.ifsc.praticas.dao.PessoaDAO;
import br.ifsc.praticas.jdbc.JDBCPedidoDAO;
import br.ifsc.praticas.jdbc.JDBCPessoaDAO;
import br.ifsc.praticas.model.Pedido;
import br.ifsc.praticas.model.Pessoa;

public class MainPedido {

	public static void imprimir(List<Pedido> lista) {
		System.out.println("Imprimindo lista...");
		for (Pedido pe : lista) {
			System.out.println("ID: " + pe.getId());
			System.out.println("Numero Pedido: " + pe.getNumeroPedido());
			System.out.println("Valor: " + pe.getValor());
			imprimirPessoa(pe.getPessoa());
			System.out.println("");
		}
		System.out.println("----------------------------");
	}

	public static void imprimirPessoa(Pessoa p) {
		System.out.print("Pessoa: ");
		System.out.print(p.getId() + " ");
		System.out.print(p.getPrimeiroNome() + " ");
		System.out.print(p.getUltimoNome() + " ");
		System.out.print(p.getIdade() + " ");
		System.out.println(p.getProfissao());
	}

	public static void imprimirPedido(Pedido p) {
		System.out.print("Pedido: ");
		System.out.println("ID: " + p.getId());
		System.out.println("Numero Pedido: " + p.getNumeroPedido());
		System.out.println("Valor: " + p.getValor());
		imprimirPessoa(p.getPessoa());
	}

	public static void main(String[] args) {

		PedidoDAO banco = new JDBCPedidoDAO();

		imprimir(banco.listar());

		Pessoa p1 = new Pessoa();
		p1.setId(11);
		p1.setPrimeiroNome("Cris");
		p1.setProfissao("Jogador");
		p1.setUltimoNome("Ronaldo");
		p1.setIdade(30);
		PessoaDAO banco2 = new JDBCPessoaDAO();
		banco2.salvar(p1);

		Pedido pedido = new Pedido();
		pedido.setId(8);
		pedido.setPessoa(p1);
		pedido.setNumeroPedido(130);
		pedido.setValor(80000);
		banco.salvar(pedido);

		pedido = banco.buscarPorId(8);
		imprimirPedido(pedido);

		pedido.getPessoa().setPrimeiroNome("Cristiano");
		pedido.setValor(200000);
		banco2.atualizar(pedido.getPessoa());
		
		banco.atualizar(pedido);

		imprimirPedido(banco.buscarPorId(8));

		imprimir(banco.listar());

		banco.remover(pedido);

		imprimir(banco.listar());
		
		banco2.remover(p1);

	}

}
