package Order_package.Controllers;

import Order_package.Interfaces.OrderRepository;
import Order_package.Models.OrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController{

    @Autowired
    private OrderRepository repository;


    //retreiving all orders
    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/orders")
    public ResponseEntity index(){
        return ResponseEntity.status(200).body(repository.findAll());

    }

    //create order
    @PostMapping("/order")
    public ResponseEntity createOrder(@RequestBody OrderData newOrder){
        repository.save(newOrder);
        return ResponseEntity.status(201).body(newOrder);

    }

    //deleting an order
    @DeleteMapping(value="/order/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity delete(@PathVariable String id){


        if(repository.findById(id)!=null){
            repository.delete(id);
            return ResponseEntity.status(200).build();


        }else{
            return ResponseEntity.status(404).build();
        }

    }

    //updating the object
    @PutMapping(value="/order", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity update(@RequestBody OrderData currentOrder){

        if (repository.findById(currentOrder.getId())!=null){
            repository.save(currentOrder);
            return ResponseEntity.status(200).body(currentOrder);

        }else{
            return ResponseEntity.status(404).build();
        }

    }


}
