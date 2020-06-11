package com.lambdaschool.javacrudyorders.services;



import com.lambdaschool.javacrudyorders.models.Agents;
import com.lambdaschool.javacrudyorders.models.Customers;
import com.lambdaschool.javacrudyorders.models.Orders;
import com.lambdaschool.javacrudyorders.models.Payments;
import com.lambdaschool.javacrudyorders.repositories.AgentsRepo;
import com.lambdaschool.javacrudyorders.repositories.CustomersRepo;
import com.lambdaschool.javacrudyorders.repositories.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customersService")
public class CustomersServiceImplementation implements CustomersService{

    @Autowired
    private CustomersRepo custrepos;
    @Autowired
    private AgentsRepo agentrepos;
    @Autowired
    private OrdersRepo orderrepos;

    @Override
    public List<Customers> findAllCustomers() {

        List<Customers> cstList = new ArrayList<>();

        custrepos.findAll().iterator().forEachRemaining(cstList::add);

        return cstList;
    }

    @Override
    public Customers findCustomerById(long id) {
        return custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));
    }

    @Override
    public List<Customers> findByNameLike(String thename) {
        return custrepos.findByCustnameContainingIgnoringCase(thename);
    }

    @Override
    public Agents findAgentById(long id) {
        return agentrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found"));
    }

    @Override
    public Orders findOrderById(long id) {
        return orderrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found"));
    }

    @Override
    public List<Orders> findOrdersWithAdvanceAmount() {
        return orderrepos.findByAdvanceamountGreaterThan((double)0.0);
    }

    @Transactional
    @Override
    public void delete(long id) {

        if (custrepos.findById(id).isPresent())
        {

            custrepos.deleteById(id);

        } else {

            throw new EntityNotFoundException("Customer " + id + " Not Found");

        }

    }

    @Transactional
    @Override
    public Customers save(Customers customer) {

        Customers newCustomer = new Customers();

        if(customer.getCustcode() != 0)
        {

            custrepos.findById(customer.getCustcode()).orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));
            newCustomer.setCustcode(customer.getCustcode());

        }

//        this.agentname = agentname;
//        this.workingarea = workingarea;
//        this.commission = commission;
//        this.phone = phone;
//        this.country = country;

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());



        newCustomer.setAgent(customer.getAgent());

        newCustomer.getOrders().clear();

        for (Orders o : customer.getOrders())
        {
                Orders newOrder = new Orders(o.getOrdamount(), o.getAdvanceamount(), o.getOrderdescription(), customer);

                newOrder.setPayments(o.getPayments());

                newCustomer.getOrders()
                        .add(newOrder);
        }

        return custrepos.save(newCustomer);

    }
    @Transactional
    @Override
    public Customers update(Customers customer, long id) {

        Customers currentCustomer = custrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));


        if(customer.getCustname() != null){

            currentCustomer.setCustname(customer.getCustname());

        }

        if(customer.getCustcity() != null){

            currentCustomer.setCustcity(customer.getCustcity());

        }

        if(customer.getWorkingarea() != null){

            currentCustomer.setWorkingarea(customer.getWorkingarea());

        }

        if(customer.getCustcountry() != null){

            currentCustomer.setCustcountry(customer.getCustcountry());

        }

        if(customer.getGrade() != null)
        {

            currentCustomer.setGrade(customer.getGrade());

        }

        if(customer.getPhone() != null)
        {

            currentCustomer.setPhone(customer.getPhone());

        }

        if(customer.hasvalueforopenamt)
        {

            currentCustomer.setOpeningamt(customer.getOpeningamt());

        }
        if(customer.hasvalueforrecamt)
        {

            currentCustomer.setReceiveamt(customer.getReceiveamt());

        }
        if(customer.hasvalueforpayamt)
        {

            currentCustomer.setPaymentamt(customer.getPaymentamt());

        }
        if(customer.hasvalueforoutamt)
        {

            currentCustomer.setOutstandingamt(customer.getOutstandingamt());

        }

        if(customer.getAgent().getAgentcode() != 0)
        {

            currentCustomer.setAgent(customer.getAgent());

        }


        if(customer.getOrders().size() > 0)
        {

            currentCustomer.getOrders().clear();

            for (Orders o : customer.getOrders())
            {
                Orders newOrder = new Orders(o.getOrdamount(), o.getAdvanceamount(), o.getOrderdescription(), customer);

                currentCustomer.getOrders()
                        .add(newOrder);
            }

        }



        return custrepos.save(currentCustomer);

    }
}
