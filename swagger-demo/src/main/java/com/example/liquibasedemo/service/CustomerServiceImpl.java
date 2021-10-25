package com.example.liquibasedemo.service;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<CustomerDTO> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).get()
                .map(customer -> new CustomerDTO(customer.getName()))
                .collect(Collectors.toList());
    }



    public CustomerDTO getCustomerById(String id) throws NotFoundException {
        return new CustomerDTO(customerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Couldn't find customer")).getName());
    }

    public CustomerDTO saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return new CustomerDTO(customer.getName());
    }

    public void deleteCustomer(String id) throws NotFoundException {
        customerRepository.deleteById(customerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Couldn't find customer")).getId());

    }

}
