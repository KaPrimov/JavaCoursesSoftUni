package com.softuni.residentEvil.services;

import com.softuni.residentEvil.models.binding.FullVirusDTO;
import com.softuni.residentEvil.models.view.ListViewVirusDTO;

import javax.validation.Valid;
import java.util.Set;

public interface VirusService {

	Set<ListViewVirusDTO> gelAll();
	
	void saveVirus(final FullVirusDTO virusDTO);
	
	FullVirusDTO findByName(final String name);

	void editVirus(final @Valid FullVirusDTO virusModel);

	FullVirusDTO findById(final String id);

	void deleteVirus(final String id);

    String findAllMapViruses();
}
