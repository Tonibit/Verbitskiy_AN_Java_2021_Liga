package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crud/customer")
@Api(value = "Customer CRUD operations", description = "Customer CRUD operations")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @ApiOperation(value = "Enumerates all Customer entities")
    @GetMapping
    public List<CustomerDTO> enumerate(Pageable pageable) {

        return customerService.getAllCustomers(pageable);
    }

    @ApiOperation(value = "Store given Customer entity")
    @PostMapping
    public CustomerDTO save(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @ApiOperation(value = "Retrieves Customer entity by it ID")
    @GetMapping("/{id}")
    public CustomerDTO get(@PathVariable("id")String id) throws NotFoundException {
        return customerService.getCustomerById(id);
    }

    //TODO: добавить и проаннотировать операции удаления сущности Customer, и создания новой сущности Customer
    @ApiOperation(value = "Delete Customer entity by it ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) throws NotFoundException {
        customerService.deleteCustomer(id);
    }


    @ApiOperation(value = "Create new Customer entity")
    @PutMapping("/create")
    public CustomerDTO create(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }
}
