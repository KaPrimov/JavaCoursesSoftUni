package softuni.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.dto.binding.AddTownJsonDto;
import softuni.dto.view.xml.elements.TownXmlViewDto;
import softuni.dto.view.xml.wrappers.TownsAndPopulationXmlWrapper;
import softuni.entities.Town;
import softuni.io.MessageWriter;
import softuni.repositories.ProductRepository;
import softuni.repositories.TownRepository;
import softuni.services.api.TownService;
import softuni.utils.CustomValidator;
import softuni.utils.DTOConvertUtil;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ProductRepository productRepository;


    @Autowired
    public TownServiceImpl(TownRepository townRepository, ProductRepository productRepository) {
        this.townRepository = townRepository;
        this.productRepository = productRepository;
    }


    @Override
    public void saveTowns(AddTownJsonDto[] townJsonDto) {
        Town[] towns = DTOConvertUtil.convert(townJsonDto, Town[].class);
        for (Town town : towns) {
            if (CustomValidator.isValid(town)) {
                this.townRepository.saveAndFlush(town);
                MessageWriter.getInstance().printSuccess(Town.class, town.getName());
            } else {
                System.out.println("Error: Invalid data.");
                MessageWriter.getInstance().printError();
            }
        }
    }

    @Override
    public TownsAndPopulationXmlWrapper townsAndClients() {
        List<Town> towns = this.townRepository.findAll();
        List<TownXmlViewDto> townDtos = DTOConvertUtil.convert(towns, TownXmlViewDto.class);
        TownsAndPopulationXmlWrapper townsAndPopulationXmlWrapper = new TownsAndPopulationXmlWrapper();
        for (TownXmlViewDto townDto : townDtos) {
            Integer clients = this.productRepository.returnClientsInTown(townDto.getName()) != null
                    ?  this.productRepository.returnClientsInTown(townDto.getName()) : 0;

            townDto.setTownClients(Long.valueOf(clients));
        }
        townDtos.sort(Comparator.comparing(TownXmlViewDto::getTownClients).reversed().thenComparing(TownXmlViewDto::getName));
        townsAndPopulationXmlWrapper.setTowns(townDtos);
        return townsAndPopulationXmlWrapper;
    }
}
