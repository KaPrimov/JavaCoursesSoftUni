package com.softuni.residentEvil.services;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softuni.residentEvil.entities.Capital;
import com.softuni.residentEvil.entities.Virus;
import com.softuni.residentEvil.entities.enums.MagnitudeEnum;
import com.softuni.residentEvil.entities.enums.MutationEnum;
import com.softuni.residentEvil.models.binding.FullVirusDTO;
import com.softuni.residentEvil.models.view.ListViewVirusDTO;
import com.softuni.residentEvil.repositories.CapitalRepository;
import com.softuni.residentEvil.repositories.VirusRepository;
import com.softuni.residentEvil.utils.ModelParser;

@Service
public class VirusServiceImpl implements VirusService {

	private VirusRepository virusRepository;
	private CapitalRepository capitalRepository;
	
	@Autowired
	public VirusServiceImpl(VirusRepository virusRepository, CapitalRepository capitalRepository) {
		this.virusRepository = virusRepository;
		this.capitalRepository = capitalRepository;
	}

	@Override
	public Set<ListViewVirusDTO> gelAll() {
		Set<ListViewVirusDTO> virusDTOs = new LinkedHashSet<>();
		List<Virus> allViruses = this.virusRepository.findAll();
		for (Virus virus : allViruses) {
			ListViewVirusDTO virusDTO = ModelParser.getInstance().map(virus, ListViewVirusDTO.class);
			virusDTO.setMagnitude(virus.getMagnitude().getName());
			virusDTOs.add(virusDTO);
		}
		return virusDTOs;
	}

	@Override
	public void saveVirus(final FullVirusDTO virusDTO) {
		Virus virus = ModelParser.getInstance().map(virusDTO, Virus.class);
		virus.setMagnitude(MagnitudeEnum.valueOf(virusDTO.getMagnitude()));
		virus.setMutation(MutationEnum.valueOf(virusDTO.getMutation()));
		virus.setIsCurable(virusDTO.getIsCurable() == null ? false : true);
		virus.setIsCurable(virusDTO.getIsDeadly() == null ? false : true);
		Set<Capital> capitals = new LinkedHashSet<>();
		for (String capitalName : virusDTO.getCapitals()) {
			Capital capital = this.capitalRepository.findByName(capitalName);
			if (capital != null) {
				capitals.add(capital);
			}
		}
		virus.setCapitals(capitals);
		
		this.virusRepository.save(virus);		
	}

}
