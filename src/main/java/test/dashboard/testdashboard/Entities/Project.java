package test.dashboard.testdashboard.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "project")
public class Project {
    @Id
    private String id;
    private String title;
    private Long problematicID;
    private String status;
    private String statementWork;
    private String dateSubmitted;
    @DBRef
    private User projectManager;
    private boolean archived;
    @DBRef
    private List<Iteration> iterations;

    public List<Iteration> getIterations() {
        return iterations;
    }

    public void setIterations(List<Iteration> iterations) {
        this.iterations = iterations;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getProblematicID() {
        return problematicID;
    }

    public void setProblematicID(Long problematicID) {
        this.problematicID = problematicID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatementWork() {
        return statementWork;
    }

    public void setStatementWork(String statementWork) {
        this.statementWork = statementWork;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public User getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(User projectManager) {
        this.projectManager = projectManager;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    // Getters and Setters
}