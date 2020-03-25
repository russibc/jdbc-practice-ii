package br.ifsc.praticas.model;

/**
 * 
 * @author Bruna C.R.
 *
 */
public class Pedido {

	private int id;
	private int numeroPedido;
	private int valor;
	private Pessoa pessoa;

	public Pedido() {

	}

	public Pedido(int id, int numeroPedido, int valor, Pessoa pessoa) {
		this.id = id;
		this.numeroPedido = numeroPedido;
		this.valor = valor;
		this.pessoa = pessoa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
