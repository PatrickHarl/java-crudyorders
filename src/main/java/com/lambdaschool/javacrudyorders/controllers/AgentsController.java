package com.lambdaschool.javacrudyorders.controllers;


import com.lambdaschool.javacrudyorders.models.Agents;
import com.lambdaschool.javacrudyorders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentsController
{
    @Autowired
    private CustomersService customersService;

     //http://localhost:2019/agents/agent/9
    @GetMapping(value = "/agent/{id}", produces = {"application/json"})
    public ResponseEntity<?> findAgentById(@PathVariable long id)
    {
        Agents myAgent = customersService.findAgentById(id);
        return new ResponseEntity<>(myAgent, HttpStatus.OK);
    }

}