package softuni.course.products.dtos.view.xmlViews;

import softuni.course.products.dtos.view.UserWithSoldProductView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSoldProductsXmlWrapper {

    @XmlElement(name = "user")
    private List<UserWithSoldProductView> userWithSoldProductViews;

    public UsersWithSoldProductsXmlWrapper() {
    }

    public List<UserWithSoldProductView> getUserWithSoldProductViews() {
        return userWithSoldProductViews;
    }

    public void setUserWithSoldProductViews(List<UserWithSoldProductView> userWithSoldProductViews) {
        this.userWithSoldProductViews = userWithSoldProductViews;
    }
}
