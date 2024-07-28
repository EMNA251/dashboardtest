package test.dashboard.testdashboard.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import test.dashboard.testdashboard.Entities.Iteration;
import test.dashboard.testdashboard.Entities.Task;
import test.dashboard.testdashboard.Entities.UserStory;
import test.dashboard.testdashboard.Repositories.IterationRepository;
import test.dashboard.testdashboard.Repositories.UserStoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserStoryService {
    @Autowired
    private UserStoryRepository userStoryRepository;


    @Autowired
    private IterationRepository iterationRepository;


    public List<UserStory> getAllUserStories() {
        return userStoryRepository.findAll();
    }

    public UserStory getUserStoryById(String id) {
        return userStoryRepository.findById(id).orElse(null);
    }

    public UserStory createUserStory(UserStory userStory) {
        return userStoryRepository.save(userStory);
    }

    public UserStory updateUserStory(String id, UserStory userStoryDetails) {
        UserStory userStory = userStoryRepository.findById(id).orElse(null);
        if (userStory != null) {
            userStory.setTitle(userStoryDetails.getTitle());
            userStory.setDescription(userStoryDetails.getDescription());
            userStory.setPriority(userStoryDetails.getPriority());
            userStory.setStatus(userStoryDetails.getStatus());
            userStory.setTasks(userStoryDetails.getTasks());
            userStory.setIteration(userStoryDetails.getIteration());
            return userStoryRepository.save(userStory);
        }
        return null;
    }
    public List<UserStory> getUserStoriesForIteration(String iterationId) {
        return userStoryRepository.findByIterationId(iterationId);
    }
    public void deleteUserStory(String id) {
        userStoryRepository.deleteById(id);
    }
    public UserStory assignIterationToUserStory(String userStoryId, String iterationId) {
        Optional<UserStory> userStoryOpt = userStoryRepository.findById(userStoryId);
        Optional<Iteration> iterationOpt = iterationRepository.findById(iterationId);

        if (userStoryOpt.isPresent() && iterationOpt.isPresent()) {
            UserStory userStory = userStoryOpt.get();
            Iteration iteration = iterationOpt.get();

            // Set the iteration in UserStory
            userStory.setIteration(iteration);
            userStoryRepository.save(userStory);

            // Add the UserStory to the Iteration's list
            if (!iteration.getUserStories().contains(userStory)) {
                iteration.getUserStories().add(userStory);
                iterationRepository.save(iteration);
            }

            return userStory;
        }
        return null;
    }

    public UserStory addTaskToUserStory(String userStoryId, Task task) {
        UserStory userStory = null;
        try {
            userStory = userStoryRepository.findById(userStoryId)
                    .orElseThrow(() -> new Exception("UserStory not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        userStory.getTasks().add(task);
        return userStoryRepository.save(userStory);
    }
}

