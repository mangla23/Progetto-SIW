package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.*;

public interface ResponsabileRepository extends CrudRepository<Responsabile, Long> {

	List<Responsabile> findByNomeAndCognomeAndCentro(String nome, String cognome, Centro centro);
}
