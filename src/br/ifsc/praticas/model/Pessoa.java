package br.ifsc.praticas.model;

/**
 * 
 * @author Bruna C.R.
 *
 */
public class Pessoa {

	private String primeiroNome;
	private int id;
	private String ultimoNome;
	private String profissao;
	private int idade;

	public Pessoa() {
	}

	public Pessoa(int id, String primeiroNome, String ultimoNome, int idade, String profissao) {
		this.id = id;
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.idade = idade;
		this.profissao = profissao;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

}