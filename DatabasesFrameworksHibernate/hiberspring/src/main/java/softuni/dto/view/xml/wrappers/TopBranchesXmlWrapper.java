package softuni.dto.view.xml.wrappers;

import softuni.dto.view.xml.elements.TopBranchXmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "branches")
@XmlAccessorType(XmlAccessType.FIELD)
public class TopBranchesXmlWrapper {

    @XmlElement(name = "branch")
    private List<TopBranchXmlDto> topBranches;

    public TopBranchesXmlWrapper() {
    }

    public List<TopBranchXmlDto> getTopBranches() {
        return topBranches;
    }

    public void setTopBranches(List<TopBranchXmlDto> topBranches) {
        this.topBranches = topBranches;
    }
}
