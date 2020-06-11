package com.lambdaschool.javacrudyorders.repositories;


import com.lambdaschool.javacrudyorders.models.Payments;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepo extends CrudRepository<Payments, Long> {
}