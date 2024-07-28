package test.dashboard.testdashboard.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.dashboard.testdashboard.Entities.Iteration;
import test.dashboard.testdashboard.Entities.UserStory;
import test.dashboard.testdashboard.Services.IterationService;
import test.dashboard.testdashboard.Services.UserStoryService;

import java.util.List;

@RestController
@RequestMapping("/api/iterations")
public class IterationController {
    @Autowired
    private IterationService iterationService;
    @Autowired
    private UserStoryService userStoryService;

    @GetMapping("/{iterationId}/user-stories")
    public List<UserStory> getUserStoriesForIteration(@PathVariable String iterationId) {
        return userStoryService.getUserStoriesForIteration(iterationId);
    }

    @GetMapping
    public List<Iteration> getAllIterations() {
        return iterationService.getAllIterations();
    }

    @GetMapping("/{id}")
    public Iteration getIterationById(@PathVariable String id) {
        return iterationService.getIterationById(id);
    }

    @PostMapping
    public Iteration createIteration(@RequestBody Iteration iteration) {
        return iterationService.createIteration(iteration);
    }

    @PutMapping("/{id}")
    public Iteration updateIteration(@PathVariable String id, @RequestBody Iteration iterationDetails) {
        return iterationService.updateIteration(id, iterationDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteIteration(@PathVariable String id) {
        iterationService.deleteIteration(id);
    }

    @PutMapping("/{iterationId}/addUserStory")
    public ResponseEntity<Iteration> addUserStoryToIteration(
            @PathVariable String iterationId, @RequestParam String userStoryId) {
        Iteration updatedIteration = iterationService.addUserStoryToIteration(iterationId, userStoryId);
        return ResponseEntity.ok(updatedIteration);
    }
}

