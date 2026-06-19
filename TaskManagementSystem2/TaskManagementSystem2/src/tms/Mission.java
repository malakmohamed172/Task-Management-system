package tms;

//task assigned to an employee outside normal tasks
public class Mission {

    private String employeeUsername;
    private String missionDescription;
    private String priority;
    private String status;

    public Mission(String employeeUsername, String missionDescription, String priority) {
        this.employeeUsername = employeeUsername;
        this.missionDescription = missionDescription;
        this.priority = priority;
        this.status = "PENDING";
    }

    // Getters
    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public String getMissionDescription() {
        return missionDescription;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    //Manage missions
    public void approve() {
        status = "APPROVED";
    }

    public void disapprove() {
        status = "DISAPPROVED";
    }
}
