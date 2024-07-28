package test.dashboard.testdashboard.Repositories;



import org.springframework.data.mongodb.repository.MongoRepository;
import test.dashboard.testdashboard.Entities.Iteration;

public interface IterationRepository extends MongoRepository<Iteration, String> {
}
