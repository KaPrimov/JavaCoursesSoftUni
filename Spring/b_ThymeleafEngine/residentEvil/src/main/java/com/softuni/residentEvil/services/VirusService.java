package com.softuni.residentEvil.services;

import com.softuni.residentEvil.models.binding.FullVirusDTO;
import com.softuni.residentEvil.models.view.ListViewVirusDTO;
import com.softuni.residentEvil.models.view.pagination.AllVirusesViewModel;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import java.util.Set;

public interface VirusService {

	Set<ListViewVirusDTO> gelAll();

	AllVirusesViewModel findAllByPage(Pageable pageable);
	
	void saveVirus(final FullVirusDTO virusDTO);
	
	FullVirusDTO findByName(final String name);

	void editVirus(final @Valid FullVirusDTO virusModel);

	FullVirusDTO findById(final String id);

	void deleteVirus(final String id);

    String findAllMapViruses();

    default long getTotalPages() {
        return getTotalPages(10);
    }

    long getTotalPages(int size);
}
