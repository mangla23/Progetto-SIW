package it.uniroma3.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;

public interface AllievoRepository extends CrudRepository<Allievo, Long> {
	
	public List<Allievo> findByNomeAndCognomeAndEmailAndTelefonoAndDataDiNascitaAndLuogoDiNascita(String nome, String cognome,
			String email, String telefono, String dataDiNascita, String luogoDiNascita);
}
