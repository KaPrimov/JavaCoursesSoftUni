package softuni.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.dto.binding.AddBranchJsonDto;
import softuni.dto.view.xml.elements.TopBranchXmlDto;
import softuni.dto.view.xml.wrappers.TopBranchesXmlWrapper;
import softuni.entities.Branch;
import softuni.io.MessageWriter;
import softuni.repositories.BranchRepository;
import softuni.repositories.ProductRepository;
import softuni.repositories.TownRepository;
import softuni.services.api.BranchService;
import softuni.utils.CustomValidator;
import softuni.utils.DTOConvertUtil;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final ProductRepository productRepository;


    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository, ProductRepository productRepository) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void saveBranches(AddBranchJsonDto[] branchJsonDtos) {
        for (AddBranchJsonDto branchJsonDto : branchJsonDtos) {
            Branch branch = DTOConvertUtil.convert(branchJsonDto, Branch.class);
            branch.setTown(this.townRepository.findByName(branchJsonDto.getTown()));
            if (CustomValidator.isValid(branch)) {
                this.branchRepository.saveAndFlush(branch);
                MessageWriter.getInstance().printSuccess(Branch.class, branch.getName());
            } else {
                MessageWriter.getInstance().printError();
            }
        }
    }

    @Override
    public TopBranchesXmlWrapper findTheTopBranches() {
        List<Branch> branches = this.branchRepository.findAll();
        List<TopBranchXmlDto> topBranches = DTOConvertUtil.convert(branches, TopBranchXmlDto.class);
        TopBranchesXmlWrapper topBranchesXmlWrapper = new TopBranchesXmlWrapper();
        for (TopBranchXmlDto topBranch : topBranches) {
            Integer clients = this.productRepository.returnClientsInBranch(topBranch.getName()) == null ?
                    0 :
                    this.productRepository.returnClientsInBranch(topBranch.getName());

            topBranch.setTotalClients(clients);
        }
        topBranches.sort(Comparator.comparing(TopBranchXmlDto::getTotalClients).reversed());
        topBranchesXmlWrapper.setTopBranches(topBranches);
        return topBranchesXmlWrapper;
    }
}
