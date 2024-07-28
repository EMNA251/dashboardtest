package test.dashboard.testdashboard.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.dashboard.testdashboard.Entities.Task;
import test.dashboard.testdashboard.Services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task taskDetails) {
        return taskService.updateTask(id, taskDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

    @PutMapping("/{taskId}/assignUserStory")
    public ResponseEntity<Task> assignUserStoryToTask(
            @PathVariable String taskId, @RequestParam String userStoryId) {
        Task updatedTask = taskService.assignUserStoryToTask(taskId, userStoryId);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{taskId}/assignBug")
    public ResponseEntity<Task> assignBugToTask(
            @PathVariable String taskId, @RequestParam String bugId) {
        Task updatedTask = taskService.assignBugToTask(taskId, bugId);
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/{taskId}/assignUser")
    public ResponseEntity<Task> assignUserToTask(
            @PathVariable String taskId, @RequestParam String userId) {
        Task updatedTask = taskService.assignUserToTask(taskId, userId);
        return ResponseEntity.ok(updatedTask);
    }

}

