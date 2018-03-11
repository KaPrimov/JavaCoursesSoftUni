package com.softuni.residentEvil.services;

import java.util.Set;

import com.softuni.residentEvil.entities.Capital;
import com.softuni.residentEvil.models.view.CapitalViewModel;

public interface CapitalService {
	Set<CapitalViewModel> getAllCapitals();
	
	Capital getByName(final String name);
}
