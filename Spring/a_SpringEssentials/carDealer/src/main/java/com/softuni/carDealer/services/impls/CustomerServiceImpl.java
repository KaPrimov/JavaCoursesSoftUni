package com.softuni.carDealer.services.impls;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softuni.carDealer.dtos.binding.add.CustomerAddDto;
import com.softuni.carDealer.dtos.binding.relations.CustomerDto;
import com.softuni.carDealer.dtos.view.CustomerView;
import com.softuni.carDealer.dtos.view.TotalCustomerSalesView;
import com.softuni.carDealer.entities.Customer;
import com.softuni.carDealer.repositories.CustomerRepository;
import com.softuni.carDealer.services.apis.CustomerService;
import com.softuni.carDealer.utils.ModelParser;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService<Customer, Long> {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void save(CustomerAddDto customerAddDto) throws ParseException {
		Customer customer = new Customer();
        this.customerRepository.save(convertCustomer(customerAddDto, customer));
    }

	private Customer convertCustomer(CustomerAddDto customerAddDto, Customer customer) throws ParseException {
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        customer.setName(customerAddDto.getName());
        if (customerAddDto.getBirthDate().trim().length() == 0) {
        	if (customer.getBirthDate() == null) {
        		customer.setBirthDate(new Date());
        	} 
        } else {
        	Date birthDate = df.parse(customerAddDto.getBirthDate());
            cal.setTime(new Date());
            cal.add(Calendar.YEAR, -2);
            if (birthDate.before(cal.getTime())) {
            	customer.setYoungDriver(false);
            } else {
            	customer.setYoungDriver(true);
            }
            customer.setBirthDate(birthDate);
        }
        
		return customer;
	}
    


	@Override
	public void updateCustomer(final CustomerAddDto customerToAdd) throws ParseException {
		Customer savedCustomer = this.customerRepository.getOne(customerToAdd.getId());
		this.customerRepository.save(this.convertCustomer(customerToAdd, savedCustomer));
		
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
    public List<CustomerView> findAllOrderedCustomers(final boolean isAscending) {
        List<Customer> customers = this.customerRepository.findAll().stream().sorted((a, b) -> {
        	int index = a.getBirthDate().compareTo(b.getBirthDate());
        	if (!isAscending) {
        		index = b.getBirthDate().compareTo(a.getBirthDate());
        	}
        	
        	if (index == 0) {
        		if (a.getYoungDriver()) {
        			index = 1;
        		} else if (b.getYoungDriver()) {
        			index = -1;
        		}
        	}
        	return index;
        }).collect(Collectors.toCollection(LinkedList::new));
        List<CustomerView> customerViews = new ArrayList<>();
        
        for (Customer customer : customers) {
            customerViews.add(ModelParser.getInstance().map(customer, CustomerView.class));
        }
        return customerViews;
    }

	@Override
	public CustomerView getById(Long id) {
		Customer customer = this.customerRepository.getOne(id);
		return ModelParser.getInstance().map(customer, CustomerView.class);
	}

    @Override
    public TotalCustomerSalesView totalCustomerSales(final Long id) {
        return this.customerRepository.totalCustomerSales(id);
    }
}
