package com.softuni.residentEvil.annotations;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PresentOrPassValidator implements ConstraintValidator<PresentOrPass, Date> {

	@Override
	public boolean isValid(Date date, ConstraintValidatorContext arg1) {
		if (date == null) {
			return false;
		}
		return date.before(new Date());
	}
	
}
