package com.softuni.residentEvil.services;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softuni.residentEvil.entities.Capital;
import com.softuni.residentEvil.models.view.CapitalViewModel;
import com.softuni.residentEvil.repositories.CapitalRepository;
import com.softuni.residentEvil.utils.ModelParser;

@Service
public class CapitalServiceImpl implements CapitalService {

	private CapitalRepository capitalRepository;
	
	@Autowired
	public CapitalServiceImpl(CapitalRepository capitalRepository) {
		this.capitalRepository = capitalRepository;
	}

	@Override
	public Set<CapitalViewModel> getAllCapitals() {
		Set<CapitalViewModel> capitalModels = new LinkedHashSet<>();
		List<Capital> allCapitals = this.capitalRepository.findAll();
		for (Capital capital : allCapitals) {
			capitalModels.add(ModelParser.getInstance().map(capital, CapitalViewModel.class));
		}
		return capitalModels;
	}

	@Override
	public Capital getByName(final String name) {
		return this.capitalRepository.findByName(name);
	}

}
