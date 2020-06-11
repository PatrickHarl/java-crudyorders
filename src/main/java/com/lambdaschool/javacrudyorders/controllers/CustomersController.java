package com.lambdaschool.javacrudyorders.controllers;


import com.lambdaschool.javacrudyorders.models.Customers;
import com.lambdaschool.javacrudyorders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping(value="/customer", consumes = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@RequestBody Customers newCustomer)
    {

        newCustomer.setCustcode(0);
        newCustomer = customersService.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{custcode}").buildAndExpand(newCustomer.getCustcode()).toUri();

        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    @PutMapping(value="/customer/{custcode}", consumes = {"application/json"})
    public ResponseEntity<?> updateFullCustomer(@Valid @RequestBody Customers updateCustomer, @PathVariable long custcode)
    {

        updateCustomer.setCustcode(custcode);
        customersService.save(updateCustomer);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PatchMapping(value = "/customer/{custcode}", consumes = {"application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody Customers updateCustomer,@PathVariable long custcode)
    {

        customersService.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/customer/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {

        customersService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
