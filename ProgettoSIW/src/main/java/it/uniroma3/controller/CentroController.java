package it.uniroma3.controller;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.UriComponentsBuilderMethodArgumentResolver;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;
import it.uniroma3.model.Responsabile;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.CentroService;
import it.uniroma3.service.ResponsabileService;
import it.uniroma3.validator.AllievoValidator;
import it.uniroma3.validator.AttivitaValidator;
import net.bytebuddy.asm.Advice.This;

@Controller
public class CentroController {
	
	@Autowired
	private CentroService centroService;
	
	@Autowired
	private AttivitaService attivitaService;
	
	@Autowired
	private AllievoService allievoService;
	
	@Autowired
	private ResponsabileService responsabileService;
	
	@Autowired
	private AttivitaValidator attivitaValidator;
	
	@Autowired
	private AllievoValidator allievoValidator;
	
	
	//Operazioni che può fare il centro
	@RequestMapping(value = "/centro/{id}")
	public String operazioni(@PathVariable Long id, Model model) {
		Centro centro = this.centroService.findById(id);
		model.addAttribute("centro", centro);
		model.addAttribute("responsabile", centro.getResponsabile());
		return "centro";
	}
	
	//Lista attività del centro
	@RequestMapping(value = "/centro/{id}/leattivita")
	public String elencoAttivita(@PathVariable Long id, Model model) {
		model.addAttribute("centro", this.centroService.findById(id));
		model.addAttribute("leattivita", this.attivitaService.findAll());
		return "leattivitaList";
	}
	
	//Aggiunta attività
	@RequestMapping(value = "/centro/{id}/addAttivita")
	public String addAttivita(@PathVariable Long id, Model model) { 
		Centro centro = this.centroService.findById(id);
		model.addAttribute("attivita", new Attivita(centro));
		model.addAttribute("centro", centro);
		return "attivitaForm";
	}
	
	@RequestMapping (value = "/centro/{id}/attivita", method = RequestMethod.POST)
	public String newAttivita(@Valid @ModelAttribute Attivita attivita, 
			@PathVariable Long id, Model model, BindingResult bindingResult) {
		
		attivita.setCentro(this.centroService.findById(id));
		
		this.attivitaValidator.validate(attivita, bindingResult);
		
		model.addAttribute("centro", this.centroService.findById(id));
		
		if(this.attivitaService.alreadyExists(attivita)) {
			model.addAttribute("exists", "Attività gia esistente");
			return "attivitaForm";
		}
		
		else {
			if (!bindingResult.hasErrors()) {
				this.attivitaService.save(attivita);
				model.addAttribute("leattivita", this.attivitaService.findAll());
				return elencoAttivita(id, model);
			}
		}
		return "attivitaForm";
	}
	
	/*
	 * Parte Attività
	 */
	
	@RequestMapping(value = "/centro/{idCentro}/{idAttivita}")
	public String showAttivita(@PathVariable Long idCentro, @PathVariable Long idAttivita, Model model) {
		//se l'attività fa parte del centro
		if(this.centroService.findById(idCentro).getId() == this.attivitaService.findById(idAttivita).getCentro().getId()) {
			model.addAttribute("attivita", this.attivitaService.findById(idAttivita));
			model.addAttribute("centro", this.centroService.findById(idCentro));
			return "mostraAttivita";
		}
		return "mostraAttivita";
	}
	
	@RequestMapping(value = "/centro/{idCentro}/{idAttivita}/allieviList")
	public String elencoAllievi(@PathVariable Long idCentro, @PathVariable Long idAttivita, Model model) {
		//se l'attività fa parte del centro
		if(this.centroService.findById(idCentro).getId() == this.attivitaService.findById(idAttivita).getCentro().getId()) {
			model.addAttribute("attivita", this.attivitaService.findById(idAttivita));
			model.addAttribute("allievi", this.attivitaService.findById(idAttivita).getAllievi());
			return "allieviList";
		}
		return null;
	}
	
	//Dati allievo
	@RequestMapping(value = "/allievo/{id}")
	public String showAllievo(@PathVariable Long id, Model model) {
		model.addAttribute("allievo", this.allievoService.findById(id));
		return "mostraAllievo";
	}
	
	@RequestMapping(value = "/centro/{idCentro}/{idAttivita}/allievoForm")
	public String addAllievo(@PathVariable Long idCentro, @PathVariable Long idAttivita, Model model) {
		Centro centro = this.centroService.findById(idCentro);
		Attivita attivita = this.attivitaService.findById(idAttivita);
		Allievo allievo = new Allievo();
		attivita.getAllievi().add(allievo);
		model.addAttribute("allievo", allievo);
		model.addAttribute("centro", centro);
		model.addAttribute("attivita", attivita);
		return "allievoForm";
	}
	
	@RequestMapping(value = "/centro/{idCentro}/{idAttivita}/allievi", method = RequestMethod.POST)
	public String newAllievo(@Valid @ModelAttribute Allievo allievo, @PathVariable Long idCentro,
			@PathVariable Long idAttivita, Model model, BindingResult bindingResult) {
		
		this.allievoValidator.validate(allievo, bindingResult);
		
		if(this.allievoService.alreadyExists(allievo)) {
			model.addAttribute("exists", "Allievo già esistente");
			return "allievoForm";
		}
		else {
			if(!bindingResult.hasErrors()) {
				this.allievoService.save(allievo); //Lo aggiungo nell'attività?
				this.attivitaService.findById(idAttivita).getAllievi().add(allievo);
				model.addAttribute("allievi", this.attivitaService.findById(idAttivita).getAllievi());
				return elencoAllievi(idCentro, idAttivita, model);
			}
		}
		return "allievoForm";
	}
	
	@RequestMapping(value = "/responsabili")
	public String responsabili(Model model) {
		List<Responsabile> responsabili = new ArrayList<>();
		for (Centro centro : this.centroService.findAll()) {
			responsabili.add(centro.getResponsabile());
		}
		model.addAttribute("responsabili", responsabili);
		return "elencoResponsabili";
	}
}
