package test.dashboard.testdashboard.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.dashboard.testdashboard.Entities.Bug;
import test.dashboard.testdashboard.Services.BugService;

import java.util.List;

@RestController
@RequestMapping("/api/bugs")
public class BugController {
    @Autowired
    private BugService bugService;

    @GetMapping
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
    }

    @GetMapping("/{id}")
    public Bug getBugById(@PathVariable String id) {
        return bugService.getBugById(id);
    }

    @PostMapping
    public Bug createBug(@RequestBody Bug bug) {
        return bugService.createBug(bug);
    }

    @PutMapping("/{id}")
    public Bug updateBug(@PathVariable String id, @RequestBody Bug bugDetails) {
        return bugService.updateBug(id, bugDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBug(@PathVariable String id) {
        bugService.deleteBug(id);
    }

    @PutMapping("/{bugId}/assignToTask")
    public ResponseEntity<Bug> assignBugToTask(
            @PathVariable String bugId, @RequestParam String taskId) {
        Bug updatedBug = bugService.assignBugToTask(bugId, taskId);
        return ResponseEntity.ok(updatedBug);
    }
}
