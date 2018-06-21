package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Responsabile;
import it.uniroma3.repository.ResponsabileRepository;


@Transactional
@Service
public class ResponsabileService {
	
	@Autowired
	private ResponsabileRepository repository;
	
	
	public Responsabile save(Responsabile r) {
		return this.repository.save(r);
	}
	
	public List<Responsabile> findAll(){
		return (List<Responsabile>) this.repository.findAll();
	}
	
	public Responsabile findById(Long id) {
		Optional<Responsabile> responsabile = this.repository.findById(id);
			if(responsabile.isPresent())
				return responsabile.get();
			else
				return null;
	}
	
	public boolean alreadyExists(Responsabile responsabile) {
		List<Responsabile> responsabili = this.repository.findByNomeAndCognomeAndCentro(responsabile.getNome(), responsabile.getCognome(), responsabile.getCentro());
		if (responsabili.size() > 0)
			return true;
		else 
			return false;
	}	
	
	

}
