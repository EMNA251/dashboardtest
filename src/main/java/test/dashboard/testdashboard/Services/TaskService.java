package test.dashboard.testdashboard.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dashboard.testdashboard.Entities.Bug;
import test.dashboard.testdashboard.Entities.Task;
import test.dashboard.testdashboard.Entities.User;
import test.dashboard.testdashboard.Entities.UserStory;
import test.dashboard.testdashboard.Repositories.BugRepository;
import test.dashboard.testdashboard.Repositories.TaskRepository;
import test.dashboard.testdashboard.Repositories.UserRepository;
import test.dashboard.testdashboard.Repositories.UserStoryRepository;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private BugRepository bugRepository;
    @Autowired
    private UserRepository userRepository;
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(String id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            task.setUserStory(taskDetails.getUserStory());
            task.setBugs(taskDetails.getBugs());
            task.setAssignedUser((taskDetails.getAssignedUser()));
            return taskRepository.save(task);
        }
        return null;
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
    public Task assignUserStoryToTask(String taskId, String userStoryId) {
        Task task = taskRepository.findById(taskId)
                .orElse(null);

        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElse(null);

        task.setUserStory(userStory);
        taskRepository.save(task);

        if (!userStory.getTasks().contains(task)) {
            userStory.getTasks().add(task);
            userStoryRepository.save(userStory);
        }

        return task;
    }

    public Task assignBugToTask(String taskId, String bugId) {
        Task task = taskRepository.findById(taskId)
                .orElse(null);

        Bug bug = bugRepository.findById(bugId)
                .orElse(null);

        bug.setTask(task);
        bugRepository.save(bug);

        if (!task.getBugs().contains(bug)) {
            task.getBugs().add(bug);
            taskRepository.save(task);
        }

        return task;
    }
    public Task assignUserToTask(String taskId, String userId) {
        Task task = taskRepository.findById(taskId)
                .orElse(null);

        User user = userRepository.findById(userId)
                .orElse(null);

        task.setAssignedUser(user);
        return taskRepository.save(task);
    }
}
