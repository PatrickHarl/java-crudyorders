package com.lambdaschool.javacrudyorders.repositories;


import com.lambdaschool.javacrudyorders.models.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepo extends CrudRepository<Customers, Long> {

    List<Customers> findByCustnameContainingIgnoringCase(String subname);

}