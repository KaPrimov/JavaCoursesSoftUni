package com.softuni.residentEvil.services;

import java.util.Set;

import com.softuni.residentEvil.models.binding.FullVirusDTO;
import com.softuni.residentEvil.models.view.ListViewVirusDTO;

public interface VirusService {

	Set<ListViewVirusDTO> gelAll();
	
	void saveVirus(final FullVirusDTO virusDTO);
	
}
