package test.dashboard.testdashboard.Repositories;



import org.springframework.data.mongodb.repository.MongoRepository;
import test.dashboard.testdashboard.Entities.UserStory;

import java.util.List;

public interface UserStoryRepository extends MongoRepository<UserStory, String> {
    List<UserStory> findByIterationId(String iterationId);
}
