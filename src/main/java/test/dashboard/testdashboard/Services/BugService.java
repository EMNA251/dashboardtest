package test.dashboard.testdashboard.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dashboard.testdashboard.Entities.Bug;
import test.dashboard.testdashboard.Entities.Task;
import test.dashboard.testdashboard.Repositories.BugRepository;
import test.dashboard.testdashboard.Repositories.TaskRepository;

import java.util.List;

@Service
public class BugService {
    @Autowired
    private BugRepository bugRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    public Bug getBugById(String id) {
        return bugRepository.findById(id).orElse(null);
    }

    public Bug createBug(Bug bug) {
        return bugRepository.save(bug);
    }

    public Bug updateBug(String id, Bug bugDetails) {
        Bug bug = bugRepository.findById(id).orElse(null);
        if (bug != null) {
            bug.setTitle(bugDetails.getTitle());
            bug.setDescription(bugDetails.getDescription());
            bug.setStatus(bugDetails.getStatus());
            bug.setTask(bugDetails.getTask());
            return bugRepository.save(bug);
        }
        return null;
    }


    public void deleteBug(String id) {
        bugRepository.deleteById(id);
    }
    public Bug assignBugToTask(String bugId, String taskId) {
        Bug bug = bugRepository.findById(bugId)
                .orElse(null);

        Task task = taskRepository.findById(taskId)
                .orElse(null);

        bug.setTask(task);
        bugRepository.save(bug);

        if (!task.getBugs().contains(bug)) {
            task.getBugs().add(bug);
            taskRepository.save(task);
        }

        return bug;
    }
}
