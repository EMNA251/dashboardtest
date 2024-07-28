package test.dashboard.testdashboard.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import test.dashboard.testdashboard.Entities.User;

public interface UserRepository extends MongoRepository<User, String> {
}