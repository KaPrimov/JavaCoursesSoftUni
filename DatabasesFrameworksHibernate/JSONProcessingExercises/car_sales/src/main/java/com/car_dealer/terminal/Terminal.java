package com.car_dealer.terminal;

import com.car_dealer.dtos.binding.add.*;
import com.car_dealer.dtos.binding.relations.CarDto;
import com.car_dealer.dtos.binding.relations.CustomerDto;
import com.car_dealer.dtos.binding.relations.PartDto;
import com.car_dealer.dtos.binding.relations.SupplierDto;
import com.car_dealer.dtos.view.SaleWithCarView;
import com.car_dealer.entities.*;
import com.car_dealer.io.JsonParser;
import com.car_dealer.services.apis.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CarService<Car, Long> carService;
    private final CustomerService<Customer, Long> customerService;
    private final PartService<Part, Long> partService;
    private final SaleService<Sale, Long> saleService;
    private final SupplierService<Supplier, Long> supplierService;
    private final JsonParser jsonParser;

    @Autowired
    public Terminal(CarService<Car, Long> carService, CustomerService<Customer, Long> customerService, PartService<Part, Long> partService, SaleService<Sale, Long> saleService, SupplierService<Supplier, Long> supplierService, JsonParser jsonParser) {
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
        this.jsonParser = jsonParser;
    }

    @Override
    public void run(String... strings) throws Exception {
        //importSuppliers();
        //importParts();
        //importCars();
        //importCustomers();
        //importSales();

        //1st Query
        //List<CustomerView> customerViews = this.customerService.findAllOrderedCustomers().stream()
        //        .sorted((a, b) -> {
        //            int index = a.getBirthDate().compareTo(b.getBirthDate());
        //            if (index == 0) {
        //                index = -1;
        //                if (a.getYoungDriver()) {
        //                    index = 1;
        //                }
        //            }
        //            return index;
        //        }).collect(Collectors.toList());
        //this.jsonParser.serialize(customerViews, JSON_OUT_PATH + "ordered-customers.json");

        //2nd Query
        //List<CarMakerView> carMakerViews = this.carService.findAllCarsByMaker("Toyota");
        //this.jsonParser.serialize(carMakerViews, JSON_OUT_PATH + "toyota-cars.json");

        //3rd Task
        //List<LocalSupplierView> supplierViews = this.supplierService.findAllLocalSuppliers();
        //this.jsonParser.serialize(supplierViews, JSON_OUT_PATH + "findAllLocalSuppliers.json");

        //4th Task
        //List<CarPartsView> carPartsViews = this.carService.findAllCarsWithParts();
        //this.jsonParser.serialize(carPartsViews, JSON_OUT_PATH + "cars-and-parts.json");

        //5th Task
        //List<TotalCustomerSalesView> totalCustomerSalesViews = this.customerService.totalCustomerSales();
        //this.jsonParser.serialize(totalCustomerSalesViews, JSON_OUT_PATH + "customers-total-sales.json");

        //6th Task
        List<SaleWithCarView> saleWithCarViews = this.saleService.saleWithCars();
        this.jsonParser.serialize(saleWithCarViews, JSON_OUT_PATH + "sales-discounts.json");
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
}
