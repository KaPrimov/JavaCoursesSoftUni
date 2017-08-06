package com.car_dealer.services.impls;

import com.car_dealer.dtos.binding.add.CustomerAddDto;
import com.car_dealer.dtos.binding.relations.CustomerDto;
import com.car_dealer.dtos.view.CustomerView;
import com.car_dealer.dtos.view.TotalCustomerSalesView;
import com.car_dealer.entities.Customer;
import com.car_dealer.repositories.CustomerRepository;
import com.car_dealer.services.apis.CustomerService;
import com.car_dealer.utils.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
}
