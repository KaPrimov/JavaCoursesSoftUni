package softuni.terminal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.dto.binding.AddBranchJsonDto;
import softuni.dto.binding.AddTownJsonDto;
import softuni.dto.binding.EmplCardJsonDto;
import softuni.dto.binding.xmlWrappers.AddEmployeesXmlWrapper;
import softuni.dto.binding.xmlWrappers.AddProductsXmlWrapper;
import softuni.dto.view.xml.wrappers.TopBranchesXmlWrapper;
import softuni.io.Serializer;
import softuni.services.api.*;

@Component
public class Terminal implements CommandLineRunner {

    private static final String INPUT_PATH = "/input/";
    private static final String TOWNS_JSON_PATH = "towns.json";
    private static final String BRANCHES_JSON_PATH = "branches.json";
    private static final String EMPLOYEE_CARDS_JSON_PATH = "employee_cards.json";
    private static final String PRODUCTS_XML_PATH = "products.xml";
    private static final String EMPLOYEES_XML_PATH = "employees.xml";
    private static final String OUT_PATH = "src/main/resources/output/";
    public static final String FREE_CARDS_JSON_FILE_NAME = "free_cards.json";
    public static final String PRODUCTIVE_EMPLOYEES_JSON_PATH = "productive-employees.json";
    public static final String TOWNS_XML_PATH = "towns.xml";
    public static final String TOP_BRANCHES_XML_PATH = "top-branches.xml";
    private BranchService  branchService;
    private EmployeeCardService employeeCardService;
    private EmployeeService employeeService;
    private ProductService productService;
    private TownService townService;
    private Serializer xmlSerializer;
    private Serializer jsonSerializer;


    public Terminal(BranchService branchService, EmployeeCardService employeeCardService, EmployeeService employeeService, ProductService productService, TownService townService, @Qualifier("XmlParser") Serializer xmlSerializer, @Qualifier("JsonParser") Serializer jsonSerializer) {
        this.branchService = branchService;
        this.employeeCardService = employeeCardService;
        this.employeeService = employeeService;
        this.productService = productService;
        this.townService = townService;
        this.xmlSerializer = xmlSerializer;
        this.jsonSerializer = jsonSerializer;
    }

    @Override
    public void run(String... strings) throws Exception {
        //importTowns();
        //importBranches();
        //importEmplCards();
        //importProducts();
        //importEmployees();

        //1st Query
//        List<FreeCardsViewDto> allUnusedEmployeeCards = this.employeeCardService.findAllUnusedEmployeeCards();
//        this.jsonSerializer.serialize(allUnusedEmployeeCards, OUT_PATH + FREE_CARDS_JSON_FILE_NAME);

        //2nd Query
//        List<ProductiveEmployee> productiveEmployees = this.employeeService.findAllProductiveEmployees();
//        this.jsonSerializer.serialize(productiveEmployees, OUT_PATH + PRODUCTIVE_EMPLOYEES_JSON_PATH);

        //3rd Query
//        TownsAndPopulationXmlWrapper townDtos = this.townService.townsAndClients();
//        this.xmlSerializer.serialize(townDtos, OUT_PATH + TOWNS_XML_PATH);

        //4th Query
        TopBranchesXmlWrapper topBranches = this.branchService.findTheTopBranches();
        this.xmlSerializer.serialize(topBranches, OUT_PATH + TOP_BRANCHES_XML_PATH);
    }

    private void importEmployees() {
        AddEmployeesXmlWrapper employeesXmlWrapper = this.xmlSerializer.deserialize(AddEmployeesXmlWrapper.class, INPUT_PATH + EMPLOYEES_XML_PATH);
        this.employeeService.saveEmployees(employeesXmlWrapper);
    }

    private void importProducts() {
        AddProductsXmlWrapper productsXmlWrapper = this.xmlSerializer.deserialize(AddProductsXmlWrapper.class, INPUT_PATH + PRODUCTS_XML_PATH);
        this.productService.saveProducts(productsXmlWrapper);
    }

    private void importEmplCards() {
        EmplCardJsonDto[] emplCardJsonDtos = this.jsonSerializer.deserialize(EmplCardJsonDto[].class, INPUT_PATH + EMPLOYEE_CARDS_JSON_PATH);
        this.employeeCardService.saveCards(emplCardJsonDtos);
    }

    private void importBranches() {
        AddBranchJsonDto[] branchJsonDtos = this.jsonSerializer.deserialize(AddBranchJsonDto[].class, INPUT_PATH + BRANCHES_JSON_PATH);
        this.branchService.saveBranches(branchJsonDtos);
    }


    private void importTowns() {
        AddTownJsonDto[] townJsonDtos = this.jsonSerializer.deserialize(AddTownJsonDto[].class, INPUT_PATH + TOWNS_JSON_PATH);
        this.townService.saveTowns(townJsonDtos);
    }
}
