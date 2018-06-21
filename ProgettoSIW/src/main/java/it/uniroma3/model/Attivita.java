package it.uniroma3.model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Attivita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	//@Temporal(TemporalType.DATE)
	@Column
	private String data;
	
	@Column(nullable = false)
	private LocalTime orarioInizio;
	
	@Column(nullable = false)
	private LocalTime orarioFine;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Centro centro;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Allievo> allievi;

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public LocalTime getOrarioInizio() {
		return orarioInizio;
	}

	public void setOrarioInizio(LocalTime orarioInizio) {
		this.orarioInizio = orarioInizio;
	}

	public LocalTime getOrarioFine() {
		return orarioFine;
	}

	public void setOrarioFine(LocalTime orarioFine) {
		this.orarioFine = orarioFine;
	}
	
	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public List<Allievo> getAllievi() {
		return allievi;
	}

	public void setAllievi(List<Allievo> allievi) {
		this.allievi = allievi;
	}
	
	public Attivita() {}
	
	public Attivita(Centro centro) {
		this.centro = centro;
	}
	
	public Attivita(String nome, String data, LocalTime orarioInizio, LocalTime orarioFine, List<Allievo> allievi, Centro centro) {
		this.nome = nome;
		this.data = data;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.allievi = allievi;
		this.centro = centro;
	}


}

