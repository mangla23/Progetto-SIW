package it.uniroma3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Allievo;
import it.uniroma3.repository.AllievoRepository;

@Transactional
@Service
public class AllievoService {
	
	@Autowired
	private AllievoRepository repository;
	
	
	public Allievo save(Allievo o) {
		return this.repository.save(o);
	}
	
	public List<Allievo> findAll(){
		return (List<Allievo>) this.repository.findAll();
	}
	
	public Allievo findById(Long id) {
		Optional<Allievo> allievo = this.repository.findById(id);
			if(allievo.isPresent())
				return allievo.get();
			else
				return null;
	}
	
	public boolean alreadyExists(Allievo allievo) {
		List<Allievo> allievi = this.repository.findByNomeAndCognomeAndEmailAndTelefonoAndDataDiNascitaAndLuogoDiNascita
				(allievo.getNome(), allievo.getCognome(), allievo.getEmail(), allievo.getTelefono(), allievo.getDataDiNascita(), allievo.getLuogoDiNascita());
		if (allievi.size() > 0)
			return true;
		else 
			return false;
	}	
	
	

}
