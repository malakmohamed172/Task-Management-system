package tms;

//This class represents an employee in the system
public class Employee extends User {

    private String employeeType; //Full time , Part time

    public Employee(String username, String password, String employeeType) {
        super(username, password, "EMPLOYEE");
        this.employeeType = employeeType;
    }

    // Getter & Setter
    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    // Employee login
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Submit attendance timecard
    public void submitTimecard(Timecard t, TimecardManager tm) throws Exception {
        tm.addTimecard(t);
    }

    // Requests
    public void requestLeave(Leave l, LeaveManager lm) throws Exception {
        lm.addLeave(l);
    }

    public void requestMission(Mission m, MissionManager mm) throws Exception {
        mm.addMission(m);
    }
}
