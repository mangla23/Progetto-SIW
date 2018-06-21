package it.uniroma3.model;

import javax.persistence.*;

@Entity
public class Responsabile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Centro centro;
	
	public Responsabile() {}
	
	public Responsabile(String nome, String cognome, Centro centro) {
		this.nome = nome;
		this.cognome = cognome;
		this.centro = centro;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

}
