package test.dashboard.testdashboard.Repositories;



import org.springframework.data.mongodb.repository.MongoRepository;
import test.dashboard.testdashboard.Entities.Bug;

public interface BugRepository extends MongoRepository<Bug, String> {
}
