package weddingplanner.weddingplanner.services.api;

import weddingplanner.weddingplanner.dto.binding.json.AddAgencyDto;

public interface AgencyService {
    void saveAgencies(AddAgencyDto[] addAgencyDto);
}
