package com.softuni.carDealer.services.apis;

import java.text.ParseException;
import java.util.List;

import com.softuni.carDealer.dtos.binding.add.CustomerAddDto;
import com.softuni.carDealer.dtos.binding.relations.CustomerDto;
import com.softuni.carDealer.dtos.view.CustomerView;
import com.softuni.carDealer.dtos.view.TotalCustomerSalesView;

public interface CustomerService<Customer, Long> {
    void save(CustomerAddDto customerAddDto) throws ParseException;

    List<CustomerDto> findAllCustomerDtos();

    List<CustomerView> findAllOrderedCustomers(final boolean isAscending);

    TotalCustomerSalesView totalCustomerSales(final Long id);
    
    CustomerView getById(final Long id);

	void updateCustomer(final CustomerAddDto customerToAdd) throws ParseException;

}
