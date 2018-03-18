package com.softuni.residentEvil.services;

import com.softuni.residentEvil.entities.Capital;
import com.softuni.residentEvil.entities.Virus;
import com.softuni.residentEvil.entities.enums.MagnitudeEnum;
import com.softuni.residentEvil.entities.enums.MutationEnum;
import com.softuni.residentEvil.models.binding.FullVirusDTO;
import com.softuni.residentEvil.models.view.ListViewVirusDTO;
import com.softuni.residentEvil.repositories.CapitalRepository;
import com.softuni.residentEvil.repositories.VirusRepository;
import com.softuni.residentEvil.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
		virus.setIsDeadly(virusDTO.getIsDeadly() == null ? false : true);
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

	@Override
	public FullVirusDTO findByName(String name) {
		Virus virus = this.virusRepository.findByName(name);
		FullVirusDTO virusDTO = ModelParser.getInstance().map(virus, FullVirusDTO.class);
		virusDTO.setCapitals(virus.getCapitals().stream().map(Capital::getName).collect(Collectors.toSet()));
		return virusDTO;
	}

	@Override
	public void editVirus(final @Valid FullVirusDTO virusDTO) {
		Virus virus = virusRepository.getOne(virusDTO.getId());
		virus.setMagnitude(MagnitudeEnum.valueOf(virusDTO.getMagnitude()));
		virus.setMutation(MutationEnum.valueOf(virusDTO.getMutation()));
		virus.setIsCurable(virusDTO.getIsCurable() == null ? false : true);
		virus.setIsDeadly(virusDTO.getIsDeadly() == null ? false : true);
		virus.setCreator(virusDTO.getCreator());
		virus.setName(virusDTO.getName());
		virus.setDescription(virusDTO.getDescription());
		virus.setSideEffect(virusDTO.getSideEffect());
		virus.setTurnoverRate(virusDTO.getTurnoverRate());
		virus.setHoursUntilTurn(virusDTO.getHoursUntilTurn());
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

	@Override
	public FullVirusDTO findById(final String id) {
		Virus virus = this.virusRepository.getOne(id);
		FullVirusDTO virusDTO = ModelParser.getInstance().map(virus, FullVirusDTO.class);
		virusDTO.setCapitals(virus.getCapitals().stream().map(Capital::getName).collect(Collectors.toSet()));
		return virusDTO;
	}

	@Override
	public void deleteVirus(final String id) {
		this.virusRepository.deleteById(id);
	}

	@Override
	public String findAllMapViruses() {
		StringBuilder json = new StringBuilder();
		List<Virus> viruses = this.virusRepository.findAll();
		json.append("{")
				.append("\"type\": \"FeatureCollection\",")
				.append("\"features\": [");

		for (Virus virus : viruses) {
			for (Capital capital : virus.getCapitals()) {
				json.append("{")
						.append("\"type\": \"Feature\",")
						.append("\"properties\": {")
						.append("\"mag\":").append(5 + virus.getMagnitude().ordinal()).append(",")
						.append("\"color\": \"#f00\"")
						.append("},")
						.append("\"geometry\": {")
						.append("\"type\": \"Point\",")
						.append("\"coordinates\": [")
							.append(capital.getLatitude()).append(",")
							.append(capital.getLongitude())
						.append("]")
						.append("}")
						.append("},");
			}
		}
		json.delete(json.length() - 1, json.length());
		json.append("]").append("}");
		return json.toString();
	}

}
