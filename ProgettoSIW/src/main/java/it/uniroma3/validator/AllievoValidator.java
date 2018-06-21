package it.uniroma3.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.model.Allievo;

/* TODO
 * Bisogna fare un validator per ogni entità, ognuna ha attributi diversi e vanno validati in
 * maniera differente.
 * intanto questa è impostata su Allievo (mancano molti attributi)
 */

@Component
public class AllievoValidator implements Validator {
	
	@Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataDiNascita", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "luogoDiNascita", "required");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Allievo.class.equals(aClass); //o specifico? Allievo
    }	
	

}
