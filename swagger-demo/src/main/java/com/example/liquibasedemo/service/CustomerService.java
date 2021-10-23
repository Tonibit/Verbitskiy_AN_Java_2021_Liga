package com.example.liquibasedemo.service;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import javassist.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers(Pageable pageable);
    CustomerDTO getCustomerById(String id) throws NotFoundException;
    CustomerDTO saveCustomer(Customer customer);
    void deleteCustomer(String id) throws NotFoundException;
}
