package it.uniroma3;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.model.Allievo;
import it.uniroma3.model.Attivita;
import it.uniroma3.model.Centro;
import it.uniroma3.model.Responsabile;
import it.uniroma3.service.AllievoService;
import it.uniroma3.service.AttivitaService;
import it.uniroma3.service.CentroService;
import it.uniroma3.service.ResponsabileService;

@SpringBootApplication
public class ProgettoSIWApplication {

	@Autowired
	private AllievoService allievoService;
	
	@Autowired
	private CentroService centroService;
	
	@Autowired
	private AttivitaService attivitaService;
	
	@Autowired
	private ResponsabileService responsabileService;

	public static void main(String[] args) {
		SpringApplication.run(ProgettoSIWApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		List<Attivita> attivitaCentroOstia = new ArrayList<>();
		List<Attivita> attivitaCentroPortuense = new ArrayList<>();
		List<Attivita> attivitaCentroMarconi = new ArrayList<>();
		List<Attivita> attivitaCentroPrati= new ArrayList<>();
		
		List<Allievo> allieviPallavolo = new ArrayList<>();
		List<Allievo> allieviCalcio = new ArrayList<>();
		List<Allievo> allieviPingPong = new ArrayList<>();
		List<Allievo> allieviBasket = new ArrayList<>();
		List<Allievo> allieviPallaAvvelenata = new ArrayList<>();
		
		List<Attivita> attivitaIscritto1 = new ArrayList<>();
		List<Attivita> attivitaIscritto2 = new ArrayList<>();
		List<Attivita> attivitaIscritto3 = new ArrayList<>();
		List<Attivita> attivitaIscritto4 = new ArrayList<>();
		List<Attivita> attivitaIscritto5 = new ArrayList<>();
		
		Responsabile responsabileOstia = new Responsabile("Marco", "Ferrari", null);
		Responsabile responsabilePortuense = new Responsabile("Luigi", "Verdi", null);
		Responsabile responsabileMarconi = new Responsabile("Marta", "Cioli", null);
		Responsabile responsabilePrati= new Responsabile("Luca", "Trotto", null);
		
		Centro centroOstia = new Centro("Ostia", "Via del Porto 45", "centroostia@gmail.com", "06324533", 200, responsabileOstia);
		Centro centroPortuense = new Centro("Portuense", "Via da Qui 32", "centrooportuense@gmail.com", "0667533", 250, responsabilePortuense);
		Centro centroMarconi = new Centro("Marconi", "Viale Marconi 12", "centromarconi@gmail.com", "0672324", 300, responsabileMarconi);
		Centro centroPrati = new Centro("Prati", "Via Crescenzio 23", "centroprati@gmail.com", "06123567", 300, responsabilePrati);
		
		/*
		centroOstia.setId(1L);
		centroPortuense.setId(2L);
		centroMarconi.setId(3L);
		centroPrati.setId(4L);
		*/
		
		responsabileOstia.setCentro(centroOstia);
		responsabilePortuense.setCentro(centroPortuense);
		responsabileMarconi.setCentro(centroMarconi);
		responsabilePrati.setCentro(centroPrati);
		
		Attivita attivita1 = new Attivita("Pallavolo", "15/06/2018", LocalTime.of(10, 00),LocalTime.of(11, 00), allieviPallavolo, centroOstia);
		Attivita attivita2 = new Attivita("Calcio", "02/07/2018", LocalTime.of(17, 00),LocalTime.of(18, 00), allieviCalcio, centroPortuense);
		Attivita attivita3 = new Attivita("Ping-Pong", "13/06/2018", LocalTime.of(11, 30),LocalTime.of(12, 30), allieviPingPong, centroOstia);
		Attivita attivita4 = new Attivita("Basket", "17/06/2018", LocalTime.of(9, 30),LocalTime.of(10, 30), allieviBasket, centroMarconi);
		Attivita attivita5 = new Attivita("Palla Avvelenata", "22/06/2018", LocalTime.of(14, 30),LocalTime.of(15, 30), allieviPallaAvvelenata, centroPrati);
		
		attivitaCentroOstia.add(attivita1);
		attivitaCentroOstia.add(attivita3);
		attivitaCentroPortuense.add(attivita2);
		attivitaCentroMarconi.add(attivita4);
		attivitaCentroPrati.add(attivita5);
		
		
		Allievo allievo1 = new Allievo("Franco","Rossi","franco4@pippo.it","345678123", 
				"23/08/2000", "Zagarolo", attivitaIscritto1);
		Allievo allievo2 = new Allievo("Mimmo","Bianchi","mimmowhite@pluto.com","33983293", 
				"03/11/2003", "Padova", attivitaIscritto2);
		Allievo allievo3 = new Allievo("Luca","Bruni","lucabruni@pluto.com", "33537813", 
				"03/11/2006", "Roma", attivitaIscritto3);
		Allievo allievo4 = new Allievo("Marco","Verdi","marcoverdi@pluto.com", "33309563", 
				"03/11/2005", "Frosinone", attivitaIscritto4);
		Allievo allievo5 = new Allievo("Andrea","Piuma","andreapiuma@pluto.com","334665139", 
				"03/11/2010", "Livorno", attivitaIscritto5);
		
		
		allieviPallavolo.add(allievo1);
		allieviCalcio.add(allievo2);
		allieviCalcio.add(allievo5);
		allieviPingPong.add(allievo1);
		allieviBasket.add(allievo3);
		allieviPallaAvvelenata.add(allievo4);
		
		
		attivitaIscritto1.add(attivita1);
		attivitaIscritto1.add(attivita3);
		attivitaIscritto2.add(attivita2);
		attivitaIscritto4.add(attivita5);
		attivitaIscritto3.add(attivita4);
		attivitaIscritto5.add(attivita2);
		
		
		this.allievoService.save(allievo1);
		this.allievoService.save(allievo2);
		this.allievoService.save(allievo3);
		this.allievoService.save(allievo4);
		this.allievoService.save(allievo5);
		this.centroService.save(centroOstia);
		this.centroService.save(centroPortuense);
		this.centroService.save(centroMarconi);
		this.centroService.save(centroPrati);
		this.attivitaService.save(attivita1);
		this.attivitaService.save(attivita2);
		this.attivitaService.save(attivita3);
		this.attivitaService.save(attivita4);
		this.attivitaService.save(attivita5);
		
		
	}
}
