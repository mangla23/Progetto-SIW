package it.uniroma3.validator;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.model.Attivita;

@Component
public class CentroValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Attivita.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
