package weddingplanner.weddingplanner.services.api;

import weddingplanner.weddingplanner.dto.binding.json.AddWeddingDto;

public interface WeddingService {
    void saveWeddings(AddWeddingDto[] addWeddingDtos);

    void addVenues();
}
