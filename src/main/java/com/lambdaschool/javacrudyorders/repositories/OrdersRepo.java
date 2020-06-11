package com.lambdaschool.javacrudyorders.repositories;


import com.lambdaschool.javacrudyorders.models.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepo extends CrudRepository<Orders, Long> {

    List<Orders> findByAdvanceamountGreaterThan(double num);

}