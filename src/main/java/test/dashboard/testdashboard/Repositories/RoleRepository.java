package test.dashboard.testdashboard.Repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import test.dashboard.testdashboard.Entities.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
}