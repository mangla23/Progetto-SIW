package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.repository.AllievoRepository;
import it.uniroma3.repository.AttivitaRepository;

@Transactional
@Service
public class AttivitaService {

	@Autowired
	private AttivitaRepository attivitàRepository;
	
	public Attivita save(Attivita attivita) {
		return this.attivitàRepository.save(attivita);
	}
	
	public List<Attivita> findAll(){
		return (List<Attivita>) this.attivitàRepository.findAll();
	}
	
	public Attivita findById(Long id) {
		Optional<Attivita> attivita = this.attivitàRepository.findById(id);
			if(attivita.isPresent())
				return attivita.get();
			else
				return null;
	}
	
	
	public boolean alreadyExists(Attivita attivita) {
		List<Attivita> leattivita = this.attivitàRepository.findByNomeAndDataAndOrarioInizioAndOrarioFine(attivita.getNome(),
				attivita.getData(), attivita.getOrarioInizio(), attivita.getOrarioFine());
		if(leattivita.size() > 0)
			return true;
		else
			return false;
	}

}
