package softuni.services.api;

import softuni.dto.binding.EmplCardJsonDto;
import softuni.dto.view.json.FreeCardsViewDto;

import java.util.List;

public interface EmployeeCardService {
    void saveCards(EmplCardJsonDto[] emplCardJsonDtos);

    List<FreeCardsViewDto> findAllUnusedEmployeeCards();
}
