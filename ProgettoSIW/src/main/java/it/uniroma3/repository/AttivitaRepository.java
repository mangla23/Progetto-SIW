package it.uniroma3.repository;
import it.uniroma3.model.*;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AttivitaRepository extends CrudRepository<Attivita, Long> {

	public List<Attivita> findByNomeAndDataAndOrarioInizioAndOrarioFine(String nome, String Data, LocalTime orarioInizio, LocalTime orarioFine);

}
