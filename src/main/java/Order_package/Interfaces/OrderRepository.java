package Order_package.Interfaces;

import Order_package.Models.OrderData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<OrderData, String> {

    public List<OrderData> findByOrderName(String OrderName);
    public List<OrderData> findByCustomerName(String customerName);


}
