package tms;

import java.util.Date;

//represents a task assigned to an employee
public class Task {

    private String code;
    private String title;
    private Employee assignedEmployee;
    private String phase;
    private Project project;
    private String priority;
    private Date startDate;
    private Date endDate;

    public Task(String code, String title, Employee assignedEmployee,
            String phase, Project project, String priority,
            Date startDate, Date endDate) {

        this.code = code;
        this.title = title;
        this.assignedEmployee = assignedEmployee;
        this.phase = phase;
        this.project = project;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //constructor for quick creation
    public Task(String code, String title) {
        this.code = code;
        this.title = title;
    }

    // Getters and setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(Employee assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
