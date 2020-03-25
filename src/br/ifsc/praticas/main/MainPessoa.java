package br.ifsc.praticas.main;

import java.util.List;

import br.ifsc.praticas.dao.PessoaDAO;
import br.ifsc.praticas.jdbc.JDBCPessoaDAO;
import br.ifsc.praticas.model.Pessoa;

public class MainPessoa {

	public static void imprimir(List<Pessoa> lista) {
		System.out.println("Imprimindo lista...");
		for (Pessoa pe : lista) {
			System.out.print(pe.getId() + " ");
			System.out.print(pe.getPrimeiroNome() + " ");
			System.out.print(pe.getUltimoNome() + " ");
			System.out.print(pe.getIdade() + " ");
			System.out.println(pe.getProfissao());
		}
		System.out.println("");
	}

	public static void imprimirPessoa(Pessoa p) {
		System.out.println("Imprimindo pessoa...");
		System.out.println(p.getId());
		System.out.println(p.getPrimeiroNome());
		System.out.println(p.getUltimoNome());
		System.out.println(p.getIdade());
		System.out.println(p.getProfissao());
		System.out.println("");
	}

	public static void main(String[] args) {

		PessoaDAO banco = new JDBCPessoaDAO();

		List<Pessoa> lista = banco.listar();
		imprimir(lista);

		Pessoa p1 = new Pessoa();
		p1.setId(11);
		p1.setPrimeiroNome("Cris");
		p1.setProfissao("Jogador");
		p1.setUltimoNome("Ronaldo");
		p1.setIdade(30);
		banco.salvar(p1);

		Pessoa p2 = banco.buscarPorId(11);
		imprimirPessoa(p2);

		p2.setPrimeiroNome("Cristiano");
		banco.atualizar(p2);

		imprimirPessoa(banco.buscarPorId(11));

		lista = banco.listar();
		imprimir(lista);

		banco.remover(p2);

		lista = banco.listar();
		imprimir(lista);
		
		banco.remover(p1);

	}

}

//Imprimindo lista...
//1 Tamer Cavalcante 28 Professor
//2 Lucas Bueno 25 Professor
//3 Glaucio Wachinski 39 Professor
//4 Luciano Barreto 33 Professor
//5 Flavio Pereira 40 Professor
//6 Denilson Barbosa 40 Professor
//7 Michel Temer 99 Presidente
//8 Chico Buarque 73 Músico
//9 Roberto Carlos 76 Músico
//10 Neymar Junior 25 Jogador
//
//Imprimindo pessoa...
//11
//Cris
//Ronaldo
//30
//Jogador
//
//Imprimindo pessoa...
//11
//Cristiano
//Ronaldo
//30
//Jogador
//
//Imprimindo lista...
//1 Tamer Cavalcante 28 Professor
//2 Lucas Bueno 25 Professor
//3 Glaucio Wachinski 39 Professor
//4 Luciano Barreto 33 Professor
//5 Flavio Pereira 40 Professor
//6 Denilson Barbosa 40 Professor
//7 Michel Temer 99 Presidente
//8 Chico Buarque 73 Músico
//9 Roberto Carlos 76 Músico
//10 Neymar Junior 25 Jogador
//11 Cristiano Ronaldo 30 Jogador
//
//Imprimindo lista...
//1 Tamer Cavalcante 28 Professor
//2 Lucas Bueno 25 Professor
//3 Glaucio Wachinski 39 Professor
//4 Luciano Barreto 33 Professor
//5 Flavio Pereira 40 Professor
//6 Denilson Barbosa 40 Professor
//7 Michel Temer 99 Presidente
//8 Chico Buarque 73 Músico
//9 Roberto Carlos 76 Músico
//10 Neymar Junior 25 Jogador
