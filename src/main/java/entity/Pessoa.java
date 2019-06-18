package entity;

import java.io.Serializable;

public class Pessoa implements Serializable{

	private static final long serialVersionUID = 7052043208745875658L;
	
	private int codigo;
	private String nome;
	private String dataNascimento;
	
	public int getId() {
		return codigo;
	}
	public void setId(int id) {
		this.codigo = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
	
	
}
