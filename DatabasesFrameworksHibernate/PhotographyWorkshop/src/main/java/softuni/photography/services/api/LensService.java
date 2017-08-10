package softuni.photography.services.api;

import softuni.photography.dto.bindingModels.add.AddLensJsonDto;
import softuni.photography.entities.Lens;

import java.util.List;

public interface LensService {
    void save(AddLensJsonDto lens);
    List<Lens> findAllByIdIn(Iterable<Long> ids);
}
