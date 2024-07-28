package test.dashboard.testdashboard.Repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import test.dashboard.testdashboard.Entities.Task;

public interface TaskRepository extends MongoRepository<Task, String> {
}
