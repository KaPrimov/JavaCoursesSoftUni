package softuni.course.products.dtos.view.xmlViews;

import softuni.course.products.dtos.view.CategoryStatisticView;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesStatisticsXmlWrapper {

    @XmlElement(name = "category")
    private List<CategoryStatisticView> categoryStatisticViews;

    public CategoriesStatisticsXmlWrapper() {
    }

    public List<CategoryStatisticView> getCategoryStatisticViews() {
        return categoryStatisticViews;
    }

    public void setCategoryStatisticViews(List<CategoryStatisticView> categoryStatisticViews) {
        this.categoryStatisticViews = categoryStatisticViews;
    }
}
