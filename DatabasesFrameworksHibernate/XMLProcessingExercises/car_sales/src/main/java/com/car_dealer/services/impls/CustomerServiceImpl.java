package com.car_dealer.services.impls;

import com.car_dealer.dtos.binding.add.CustomerAddDto;
import com.car_dealer.dtos.binding.relations.CustomerDto;
import com.car_dealer.dtos.view.CustomerView;
import com.car_dealer.dtos.view.TotalCustomerSalesView;
import com.car_dealer.dtos.view.xmlWrappers.OrderedCustomersXmlWrapper;
import com.car_dealer.dtos.view.xmlWrappers.TotalSalesCustomersWrapper;
import com.car_dealer.entities.Customer;
import com.car_dealer.repositories.CustomerRepository;
import com.car_dealer.services.apis.CustomerService;
import com.car_dealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService<Customer, Long> {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository carRepository) {
        this.customerRepository = carRepository;
    }

    @Override
    public void save(CustomerAddDto customerAddDto) {
        Customer customer = ModelParser.getInstance().map(customerAddDto, Customer.class);

        this.customerRepository.save(customer);
    }

    @Override
    public List<CustomerDto> findAllCustomerDtos() {
        List<Customer> customers = this.customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();

        for (Customer customer : customers) {
            customerDtos.add(ModelParser.getInstance().map(customer, CustomerDto.class));
        }

        return customerDtos;
    }

    @Override
    public List<CustomerView> findAllOrderedCustomers() {
        List<Customer> customers = this.customerRepository.findAll();
        List<CustomerView> customerViews = new ArrayList<>();
        for (Customer customer : customers) {
            customerViews.add(ModelParser.getInstance().map(customer, CustomerView.class));
        }
        return customerViews;
    }

    @Override
    public List<TotalCustomerSalesView> totalCustomerSales() {
        return this.customerRepository.totalCustomerSales();
    }

    @Override
    public TotalSalesCustomersWrapper totalCustomerSalesXmd() {
        List<TotalCustomerSalesView> totalCustomerSalesViews = this.totalCustomerSales();
        TotalSalesCustomersWrapper totalSalesCustomersWrapper = new TotalSalesCustomersWrapper();
        totalSalesCustomersWrapper.setTotalCustomerSalesViews(totalCustomerSalesViews);
        return totalSalesCustomersWrapper;
    }

    @Override
    public OrderedCustomersXmlWrapper findAllOrderedCustomersFromXmld() {
        List<Customer> customerViews = this.customerRepository.findAll().stream()
                .sorted((a, b) -> {
                    int index = a.getBirthDate().compareTo(b.getBirthDate());
                    if (index == 0) {
                        index = -1;
                        if (a.getYoungDriver()) {
                            index = 1;
                        }
                    }
                    return index;
                }).collect(Collectors.toList());

        List<CustomerView> customerViewList = new ArrayList<>();
        for (Customer customerView : customerViews) {
            customerViewList.add(ModelParser.getInstance().map(customerView, CustomerView.class));
        }
        OrderedCustomersXmlWrapper orderedCustomersXmlWrapper = new OrderedCustomersXmlWrapper();
        orderedCustomersXmlWrapper.setCustomerViews(customerViewList);
        return orderedCustomersXmlWrapper;
    }
}
