package com.lambdaschool.javacrudyorders.repositories;


import com.lambdaschool.javacrudyorders.models.Agents;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepo extends CrudRepository<Agents, Long> {
}
