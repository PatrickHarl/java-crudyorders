package com.lambdaschool.javacrudyorders.services;



import com.lambdaschool.javacrudyorders.models.Agents;
import com.lambdaschool.javacrudyorders.models.Customers;
import com.lambdaschool.javacrudyorders.models.Orders;

import java.util.List;

public interface CustomersService {

    List<Customers> findAllCustomers();

    Customers findCustomerById(long id);

    List<Customers> findByNameLike(String thename);

    Agents findAgentById(long id);

    Orders findOrderById(long id);

    List<Orders> findOrdersWithAdvanceAmount();

}
