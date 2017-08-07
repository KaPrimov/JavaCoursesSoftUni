package softuni.course.products.dtos.binding.xmlDtos;

import softuni.course.products.dtos.binding.add.CategoryAddDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesImportXmlDto {

    @XmlElement(name = "category")
    private List<CategoryAddDto> categoryAddDtos;

    public CategoriesImportXmlDto() {
    }

    public List<CategoryAddDto> getCategoryAddDtos() {
        return categoryAddDtos;
    }

    public void setCategoryAddDtos(List<CategoryAddDto> categoryAddDtos) {
        this.categoryAddDtos = categoryAddDtos;
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(1);
        System.out.println(integers.stream().mapToInt(a -> a).sum());
        int[] erm = {1,2};
        System.out.println(Arrays.stream(erm).sum());
    }
}
