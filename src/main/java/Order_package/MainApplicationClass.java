package Order_package;

import Order_package.Interfaces.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MainApplicationClass {

    @Autowired
    private OrderRepository repository;


    public static void main(String[] args) {
        SpringApplication.run(MainApplicationClass.class, args);
    }
/*
    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        repository.save(new OrderData("Alice", "Smith"));
        repository.save(new OrderData("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (OrderData order : repository.findAll()) {
            System.out.println(order);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByOrderName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (OrderData order : repository.findByCustomerName("Smith")) {
            System.out.println(order);
        }

    }*/


}
