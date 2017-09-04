package weddingplanner.weddingplanner.dto.binding.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddPresentDto {

    @XmlAttribute(name = "type")
    private String type;
    @XmlAttribute(name = "invitation-id")
    private Long invitationId;
    @XmlAttribute(name = "amount")
    private BigDecimal amount;
    @XmlAttribute(name = "present-name")
    private String presentName;
    @XmlAttribute(name = "size")
    private String size;

    public AddPresentDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Long invitationId) {
        this.invitationId = invitationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPresentName() {
        return presentName;
    }

    public void setPresentName(String presentName) {
        this.presentName = presentName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
