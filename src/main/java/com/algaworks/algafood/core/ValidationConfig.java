package com.algaworks.algafood.core;

import org.springframework.context.MessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Classe para configurar o BeanValidation.
 * Unifica os arquivos de validação do Spring (messages.properties) e Hibernate Validator (ValidationMessages.properties)
 * @author Alex Pereira Maranhão
 *
 */
public class ValidationConfig {
	LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource messageSource) {
		LocalValidatorFactoryBean validatorBean = new LocalValidatorFactoryBean();
		
		validatorBean.setValidationMessageSource(messageSource);
		
		return validatorBean;
	}
}
