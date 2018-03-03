package com.softuni.carDealer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softuni.carDealer.dtos.view.TotalCustomerSalesView;
import com.softuni.carDealer.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT new com.softuni.carDealer.dtos.view.TotalCustomerSalesView(c.name, c.sales.size, sum(s.car.price)) FROM Customer AS c " +
            "INNER JOIN c.sales AS s " +
            "INNER JOIN s.car " +
            "GROUP BY c.name " +
            "ORDER BY sum(s.car.price) DESC, c.sales.size")
    List<TotalCustomerSalesView> totalCustomerSales();
    

}
