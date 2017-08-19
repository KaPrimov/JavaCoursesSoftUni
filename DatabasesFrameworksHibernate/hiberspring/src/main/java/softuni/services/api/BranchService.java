package softuni.services.api;

import softuni.dto.binding.AddBranchJsonDto;
import softuni.dto.view.xml.wrappers.TopBranchesXmlWrapper;

public interface BranchService {
    void saveBranches(AddBranchJsonDto[] branchJsonDtos);

    TopBranchesXmlWrapper findTheTopBranches();
}
