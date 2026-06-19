package tms;

//represents a leave request made by an employee
public class Leave {

    private String employeeUsername;
    private String leaveType;
    private String status;
    private String reason;
    private String startDate;
    private String endDate;

    public Leave(String employeeUsername, String leaveType) {
        this.employeeUsername = employeeUsername;
        this.leaveType = leaveType;
        this.status = "PENDING";
    }

    // getters
    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    // setters
    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    //Managing leave requests
    public void approve() {
        status = "APPROVED";
    }

    public void disapprove() {
        status = "DISAPPROVED";
    }
}
