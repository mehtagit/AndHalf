package org.gl.ceir.configuration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class Translator {

	@Autowired
	ResourceBundleMessageSource messageSource;

	@Autowired
	Translator(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public  String toLocale(String msgCode) {
		Locale locale = LocaleContextHolder.getLocale(); 
		System.out.println("lang----"+locale+"------msgCode"+msgCode);
		return messageSource.getMessage(msgCode, null, locale);
	}
}