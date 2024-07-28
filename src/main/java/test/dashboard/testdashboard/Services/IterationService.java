package test.dashboard.testdashboard.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import test.dashboard.testdashboard.Entities.Iteration;
import test.dashboard.testdashboard.Entities.UserStory;
import test.dashboard.testdashboard.Repositories.IterationRepository;
import test.dashboard.testdashboard.Repositories.UserStoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class IterationService {
    @Autowired
    private IterationRepository iterationRepository;

    public List<Iteration> getAllIterations() {
        return iterationRepository.findAll();
    }

    public Iteration getIterationById(String id) {
        return iterationRepository.findById(id).orElse(null);
    }

    public Iteration createIteration(Iteration iteration) {
        return iterationRepository.save(iteration);
    }

    public Iteration updateIteration(String id, Iteration iterationDetails) {
        Iteration iteration = iterationRepository.findById(id).orElse(null);
        if (iteration != null) {
            iteration.setTitle(iterationDetails.getTitle());
            iteration.setDescription(iterationDetails.getDescription());
            iteration.setStartDate(iterationDetails.getStartDate());
            iteration.setEndDate(iterationDetails.getEndDate());
            iteration.setProject(iterationDetails.getProject());
            iteration.setUserStories(iterationDetails.getUserStories());
            return iterationRepository.save(iteration);
        }
        return null;
    }

    public void deleteIteration(String id) {
        iterationRepository.deleteById(id);
    }
    @Autowired
    private UserStoryRepository userStoryRepository;

    public Iteration addUserStoryToIteration(String iterationId, String userStoryId) {
        // Fetch the iteration by ID
        Iteration iteration = iterationRepository.findById(iterationId)
                .orElseThrow(() -> new ResourceNotFoundException("Iteration not found with id: " + iterationId));

        // Fetch the user story by ID
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new ResourceNotFoundException("UserStory not found with id: " + userStoryId));

        // Initialize the userStories list if it is null
        if (iteration.getUserStories() == null) {
            iteration.setUserStories(new ArrayList<>());
        }

        // Add the user story to the iteration's list of user stories
        iteration.getUserStories().add(userStory);

        // Save the updated iteration
        iterationRepository.save(iteration);

        // Update the user story's iteration
        userStory.setIteration(iteration);
        userStoryRepository.save(userStory);

        return iteration;
    }

}

