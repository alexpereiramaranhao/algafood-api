package com.algaworks.algafood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

	private Integer numero;
	
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.numero = constraintAnnotation.numero();		
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		if(value != null) {
			BigDecimal valorDecimal = BigDecimal.valueOf(value.doubleValue());
			BigDecimal valorMultiplo = BigDecimal.valueOf(this.numero);
			
			BigDecimal resto = valorDecimal.remainder(valorMultiplo);
			
			return BigDecimal.ZERO.compareTo(resto) == 0;
		}
		
		return false;

	}
	

}
