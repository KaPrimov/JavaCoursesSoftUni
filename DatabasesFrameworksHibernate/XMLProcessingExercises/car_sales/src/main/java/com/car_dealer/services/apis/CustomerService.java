package com.car_dealer.services.apis;

import com.car_dealer.dtos.binding.add.CustomerAddDto;
import com.car_dealer.dtos.binding.relations.CustomerDto;
import com.car_dealer.dtos.view.CustomerView;
import com.car_dealer.dtos.view.TotalCustomerSalesView;
import com.car_dealer.dtos.view.xmlWrappers.OrderedCustomersXmlWrapper;
import com.car_dealer.dtos.view.xmlWrappers.TotalSalesCustomersWrapper;

import java.util.List;

public interface CustomerService<Customer, Long> {
    void save(CustomerAddDto customerAddDto);

    List<CustomerDto> findAllCustomerDtos();

    List<CustomerView> findAllOrderedCustomers();

    List<TotalCustomerSalesView> totalCustomerSales();

    TotalSalesCustomersWrapper totalCustomerSalesXmd();

    OrderedCustomersXmlWrapper findAllOrderedCustomersFromXmld();
}
