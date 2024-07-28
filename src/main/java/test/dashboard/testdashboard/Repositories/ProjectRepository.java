package test.dashboard.testdashboard.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import test.dashboard.testdashboard.Entities.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
