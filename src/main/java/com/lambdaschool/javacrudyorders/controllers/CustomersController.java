package com.lambdaschool.javacrudyorders.controllers;


import com.lambdaschool.javacrudyorders.models.Customers;
import com.lambdaschool.javacrudyorders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController
{
    @Autowired
    private CustomersService customersService;

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        List<Customers> myCustomers = customersService.findAllCustomers();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/7
    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerById(@PathVariable long id)
    {
        Customers c = customersService.findCustomerById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }


    // http://localhost:2019/customers/namelike/mes/
    @GetMapping(value = "/namelike/{thename}", produces = {"application/json"})
    public ResponseEntity<?> findByNameLike(@PathVariable String thename)
    {
        List<Customers> myCustomers = customersService.findByNameLike(thename);
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }
}
