package Order_package.Interfaces;

import Order_package.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByUserName(String userName);

}
