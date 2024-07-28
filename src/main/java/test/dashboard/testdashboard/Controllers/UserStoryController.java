package test.dashboard.testdashboard.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.dashboard.testdashboard.Entities.Task;
import test.dashboard.testdashboard.Entities.UserStory;
import test.dashboard.testdashboard.Services.UserStoryService;

import java.util.List;

@RestController
@RequestMapping("/api/user-stories")
public class UserStoryController {
    @Autowired
    private UserStoryService userStoryService;

    @GetMapping
    public List<UserStory> getAllUserStories() {
        return userStoryService.getAllUserStories();
    }

    @GetMapping("/{id}")
    public UserStory getUserStoryById(@PathVariable String id) {
        return userStoryService.getUserStoryById(id);
    }

    @PostMapping
    public UserStory createUserStory(@RequestBody UserStory userStory) {
        return userStoryService.createUserStory(userStory);
    }

    @PutMapping("/{id}")
    public UserStory updateUserStory(@PathVariable String id, @RequestBody UserStory userStoryDetails) {
        return userStoryService.updateUserStory(id, userStoryDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteUserStory(@PathVariable String id) {
        userStoryService.deleteUserStory(id);
    }


    @PutMapping("/{userStoryId}/assignIteration")
    public ResponseEntity<UserStory> assignIterationToUserStory(
            @PathVariable String userStoryId, @RequestParam String iterationId) {
        UserStory updatedUserStory = userStoryService.assignIterationToUserStory(userStoryId, iterationId);
        return ResponseEntity.ok(updatedUserStory);
    }

    @PostMapping("/{userStoryId}/addTask")
    public ResponseEntity<UserStory> addTaskToUserStory(@PathVariable String userStoryId, @RequestBody Task task) {
        UserStory updatedUserStory = userStoryService.addTaskToUserStory(userStoryId, task);
        return ResponseEntity.ok(updatedUserStory);
    }
}


