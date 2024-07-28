package test.dashboard.testdashboard.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.dashboard.testdashboard.Entities.Project;
import test.dashboard.testdashboard.Repositories.ProjectRepository;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(String id, Project projectDetails) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            project.setTitle(projectDetails.getTitle());
            project.setProblematicID(projectDetails.getProblematicID());
            project.setStatus(projectDetails.getStatus());
            project.setStatementWork(projectDetails.getStatementWork());
            project.setDateSubmitted(projectDetails.getDateSubmitted());
            project.setProjectManager(projectDetails.getProjectManager());
            project.setArchived(projectDetails.isArchived());
            project.setProjectManager(projectDetails.getProjectManager());
            return projectRepository.save(project);
        }
        return null;
    }

    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }
}
