package com.example.liquibasedemo.service;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
//        Page<Customer> customerPages = customerRepository.findAll(pageable);
        return customerRepository.findAll(pageable).get()
                .map(customer -> new CustomerDTO(customer.getName()))
                .collect(Collectors.toList());
    }



    public CustomerDTO getCustomerById(String id) throws NotFoundException {
        Optional<Customer> customer = customerRepository
                .findById(UUID.fromString(id));
        Customer customerToFind = customer.orElse(null);
        if (customerToFind == null) {
            throw new NotFoundException("Couldn't find customer");
        }
        return new CustomerDTO(customerToFind.getName());
    }

    public CustomerDTO saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return new CustomerDTO(customer.getName());
    }

    public void deleteCustomer(String id) throws NotFoundException {
        Optional<Customer> customer = customerRepository.findById(UUID.fromString(id));
        Customer customerToDelete = customer.orElse(null);
        if (customerToDelete != null) {
            customerRepository.deleteById(UUID.fromString(id));
        } else {
            throw new NotFoundException("Couldn't find customer");
        }

    }

}
