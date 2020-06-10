package com.lambdaschool.javacrudyorders.controllers;


import com.lambdaschool.javacrudyorders.models.Orders;
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
@RequestMapping("/orders")
public class OrdersController
{
    @Autowired
    private CustomersService customersService;

     //http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> findOrderById(@PathVariable long id)
    {
        Orders myOrder = customersService.findOrderById(id);
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }

     //http://localhost:2019/orders/advanceamount

    @GetMapping(value = "/advanceamount", produces = {"application/json"})
    public ResponseEntity<?> findOrdersWithAdvanceAmount()
    {
        List<Orders> myOrders = customersService.findOrdersWithAdvanceAmount();
        return new ResponseEntity<>(myOrders, HttpStatus.OK);
    }

}