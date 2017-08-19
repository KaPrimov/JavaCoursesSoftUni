package softuni.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.dto.binding.EmplCardJsonDto;
import softuni.dto.view.json.FreeCardsViewDto;
import softuni.entities.EmployeeCard;
import softuni.io.MessageWriter;
import softuni.repositories.EmployeeCardRepository;
import softuni.services.api.EmployeeCardService;
import softuni.utils.CustomValidator;
import softuni.utils.DTOConvertUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private final EmployeeCardRepository employeeCardRepository;


    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository) {
        this.employeeCardRepository = employeeCardRepository;
    }

        @Override
    public void saveCards(EmplCardJsonDto[] emplCardJsonDtos) {
        EmployeeCard[] employeeCards = DTOConvertUtil.convert(emplCardJsonDtos, EmployeeCard[].class);
        for (EmployeeCard employeeCard : employeeCards) {
            EmployeeCard employeeCardCheck = employeeCardRepository.findByNumber(employeeCard.getNumber());
            if (CustomValidator.isValid(employeeCard) && employeeCardCheck == null) {
                this.employeeCardRepository.save(employeeCard);
                MessageWriter.getInstance().printSuccess(EmployeeCard.class, employeeCard.getNumber());
            } else {
                MessageWriter.getInstance().printError();
            }
        }
    }

    @Override
    public List<FreeCardsViewDto> findAllUnusedEmployeeCards() {
        List<FreeCardsViewDto> freedCards = new ArrayList<>();
        List<EmployeeCard> allUnusedEmployeeCards = this.employeeCardRepository.findAllUnusedEmployeeCards();
        for (EmployeeCard unusedEmployeeCard : allUnusedEmployeeCards) {
            freedCards.add(DTOConvertUtil.convert(unusedEmployeeCard, FreeCardsViewDto.class));
        }
        return freedCards;
    }
}
