package softuni.photography.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.photography.dto.bindingModels.add.AddLensJsonDto;
import softuni.photography.entities.Lens;
import softuni.photography.repositories.LensRepository;
import softuni.photography.services.api.LensService;
import softuni.photography.utils.DTOConvertUtil;

import java.util.List;

@Service
@Transactional
public class LensServiceImpl implements LensService {

    private final LensRepository lensRepository;

    @Autowired
    public LensServiceImpl(LensRepository lensRepository) {
        this.lensRepository = lensRepository;
    }


    @Override
    public void save(AddLensJsonDto lensJsonDto) {
        Lens lens = DTOConvertUtil.convert(lensJsonDto, Lens.class);
        this.lensRepository.save(lens);
        System.out.printf("Successfully imported Canon %dmm f%.1f%n", lens.getFocalLength(), lens.getMaxAperture());

    }

    @Override
    public List<Lens> findAllByIdIn(Iterable<Long> ids) {
        return this.lensRepository.findAllByIdIn(ids);
    }
}
