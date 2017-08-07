package com.car_dealer.terminal;

import com.car_dealer.dtos.binding.add.*;
import com.car_dealer.dtos.binding.add.xml.CarAddXml;
import com.car_dealer.dtos.binding.add.xml.CustomerAddXml;
import com.car_dealer.dtos.binding.add.xml.PartAddXml;
import com.car_dealer.dtos.binding.add.xml.SupplierAddXml;
import com.car_dealer.dtos.binding.relations.CarDto;
import com.car_dealer.dtos.binding.relations.CustomerDto;
import com.car_dealer.dtos.binding.relations.PartDto;
import com.car_dealer.dtos.binding.relations.SupplierDto;
import com.car_dealer.dtos.view.xmlWrappers.SaleWithDiscountXmlWrapper;
import com.car_dealer.entities.*;
import com.car_dealer.io.Serializer;
import com.car_dealer.services.apis.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class Terminal implements CommandLineRunner {
    public static final String JSON_OUT_PATH = "src\\main\\resources\\out\\";
    public static final String XML_OUT_PATH = "src\\main\\resources\\xml\\out\\";
    private final CarService<Car, Long> carService;
    private final CustomerService<Customer, Long> customerService;
    private final PartService<Part, Long> partService;
    private final SaleService<Sale, Long> saleService;
    private final SupplierService<Supplier, Long> supplierService;
    @Qualifier("JsonParser")
    @Autowired
    private Serializer jsonParser;
    @Qualifier("XmlParser")
    @Autowired
    private Serializer xmlParser;


    @Autowired
    public Terminal(CarService<Car, Long> carService, CustomerService<Customer, Long> customerService, PartService<Part, Long> partService, SaleService<Sale, Long> saleService, SupplierService<Supplier, Long> supplierService) {
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... strings) throws Exception {
        //importSuppliersFromXmld();
        //importPartsFromXml();
        //importCarsFromXml();
        //importCustomersFromXml();
        //importSales();

        //1st Task
        //OrderedCustomersXmlWrapper orderedCustomersXmlWrapper = this.customerService.findAllOrderedCustomersFromXmld();
        //this.xmlParser.serialize(orderedCustomersXmlWrapper, XML_OUT_PATH + "ordered-customers.xml");

        //2nd Task
        // MakerCarsXmlWrapper makerCarsXmlWrapper = this.carService.findAllCarsByMakerXml("Toyota");
        // this.xmlParser.serialize(makerCarsXmlWrapper, XML_OUT_PATH + "toyota-cars.xml");

        //3th Task
        //DomesticSuppliersXmlWrapper domesticSuppliersXmlWrapper = this.supplierService.findAllLocalSuppliersXml();
        //this.xmlParser.serialize(domesticSuppliersXmlWrapper, XML_OUT_PATH + "local-suppliers.xml");

        //4th Task
        //CarsPartsXmlWrapper carsPartsXmlWrapper = this.carService.findAllCarsWithPartsXml();
        //this.xmlParser.serialize(carsPartsXmlWrapper, XML_OUT_PATH + "cars-and-parts.xml");

        //5th Task
        //TotalSalesCustomersWrapper totalSalesCustomersWrapper = this.customerService.totalCustomerSalesXmd();
        //this.xmlParser.serialize(totalSalesCustomersWrapper, XML_OUT_PATH + "customers-total-sales.xml");

        //6th Task
        SaleWithDiscountXmlWrapper saleWithDiscountXmlWrapper = this.saleService.saleWithCarsXml();
        this.xmlParser.serialize(saleWithDiscountXmlWrapper, XML_OUT_PATH + "sales-discounts.xml");
    }

    private void importSales() {

        List<CarDto> carDtos = this.carService.findAllCarDtos();
        List<CustomerDto> customerDtos = this.customerService.findAllCustomerDtos();
        Double[] discounts = new Double[]{0.0, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};
        Random rnd = new Random();

        for (int i = 0; i < 50; i++) {
            SaleAddDto saleAddDto = new SaleAddDto();
            saleAddDto.setCar(carDtos.get(rnd.nextInt(carDtos.size())));
            saleAddDto.setCustomer(customerDtos.get(rnd.nextInt(customerDtos.size())));
            saleAddDto.setDiscount(discounts[rnd.nextInt(discounts.length)]);

            this.saleService.saveSaleDto(saleAddDto);
        }
    }

    private void importCustomers() {
        CustomerAddDto[] customerAddDtos = this.jsonParser.deserialize(CustomerAddDto[].class, "/in/customers.json");

        for (CustomerAddDto customerAddDto : customerAddDtos) {
            this.customerService.save(customerAddDto);
        }
    }

    private void importCars() {
        CarAddDto[] carAddDtos = this.jsonParser.deserialize(CarAddDto[].class, "/in/cars.json");
        List<PartDto> partDtos = this.partService.findAllPartDtos();
        Random rnd = new Random();

        for (CarAddDto carAddDto : carAddDtos) {
            Set<PartDto> singleCarSet = new HashSet<>();
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (int i = 0; i < rnd.nextInt(20 - 10) + 10; i++) {
                PartDto partDto = partDtos.get(rnd.nextInt(partDtos.size()));
                totalPrice = totalPrice.add(partDto.getPrice());
                singleCarSet.add(partDto);
            }
            carAddDto.setPrice(totalPrice);
            carAddDto.setParts(singleCarSet);
            this.carService.saveDto(carAddDto);
        }
        
    }

    private void importParts() {
        List<SupplierDto> supplierDtos = this.supplierService.findAllDtos();

        PartAddDto[] partAddDtos = this.jsonParser.deserialize(PartAddDto[].class, "/in/parts.json");

        Random rnd = new Random();

        for (PartAddDto partAddDto : partAddDtos) {
            partAddDto.setSupplier(supplierDtos.get(rnd.nextInt(supplierDtos.size())));
            this.partService.save(partAddDto);
        }
    }

    private void importSuppliers() {
        SupplierAddDto[] suppliersData = this.jsonParser.deserialize(SupplierAddDto[].class, "/in/suppliers.json");

        for (SupplierAddDto suppliersDatum : suppliersData) {
            this.supplierService.save(suppliersDatum);
        }
    }

    private void importCustomersFromXml() {
        CustomerAddXml customerAddDtos = this.xmlParser.deserialize(CustomerAddXml.class, "/in/customersValid.xml");

        for (CustomerAddDto customerAddDto : customerAddDtos.getCustomerAddDtos()) {
            this.customerService.save(customerAddDto);
        }
    }

    private void importCarsFromXml() {
        CarAddXml carAddDtos = this.xmlParser.deserialize(CarAddXml.class, "/in/carsValid.xml");
        List<PartDto> partDtos = this.partService.findAllPartDtos();
        Random rnd = new Random();

        for (CarAddDto carAddDto : carAddDtos.getCarAddDtos()) {
            Set<PartDto> singleCarSet = new HashSet<>();
            BigDecimal totalPrice = BigDecimal.ZERO;
            for (int i = 0; i < rnd.nextInt(20 - 10) + 10; i++) {
                PartDto partDto = partDtos.get(rnd.nextInt(partDtos.size()));
                totalPrice = totalPrice.add(partDto.getPrice());
                singleCarSet.add(partDto);
            }
            carAddDto.setPrice(totalPrice);
            carAddDto.setParts(singleCarSet);
            this.carService.saveDto(carAddDto);
        }
    }

    private void importPartsFromXml() {
        List<SupplierDto> supplierDtos = this.supplierService.findAllDtos();

        PartAddXml partAddDtos = this.xmlParser.deserialize(PartAddXml.class, "/in/partsValid.xml");

        Random rnd = new Random();

        for (PartAddDto partAddDto : partAddDtos.getPartAddDtos()) {
            partAddDto.setSupplier(supplierDtos.get(rnd.nextInt(supplierDtos.size())));
            this.partService.save(partAddDto);
        }
    }

    private void importSuppliersFromXmld() {
        SupplierAddXml supplierAddXml = this.xmlParser.deserialize(SupplierAddXml.class, "/in/suppliersValid.xml");

        for (SupplierAddDto suppliersDatum : supplierAddXml.getSupplierAddDtos()) {
            this.supplierService.save(suppliersDatum);
        }
    }
}
