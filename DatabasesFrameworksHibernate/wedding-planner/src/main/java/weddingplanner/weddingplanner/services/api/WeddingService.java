package weddingplanner.weddingplanner.services.api;

import weddingplanner.weddingplanner.dto.binding.json.AddWeddingDto;
import weddingplanner.weddingplanner.dto.view.json.WeddingGuestsDto;

import java.util.List;

public interface WeddingService {
    void saveWeddings(AddWeddingDto[] addWeddingDtos);

    void addVenues();

    List<WeddingGuestsDto> findAllGuestsToWedding();
}
