package tms;

//represents a project assigned to a client
public class Project {

    private String projectName;
    private String clientName;

    // Constructor with project name and client
    public Project(String projectName, String clientName) {
        this.projectName = projectName;
        this.clientName = clientName;
    }

    // Constructor with project name only
    public Project(String projectName) {
        this.projectName = projectName;
        this.clientName = "";
    }

    //Getters
    public String getProjectName() {
        return projectName;
    }

    public String getClientName() {
        return clientName;
    }
}
