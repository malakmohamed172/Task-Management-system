package tms;

import java.util.Date;

//represents an employee's attendance record for a day.
public class Timecard {

    private String employeeUsername;
    private Date date;
    private Date timeIn;
    private Date timeOut;

    // Full constructor
    public Timecard(String employeeUsername, Date date, Date timeIn, Date timeOut) {
        this.employeeUsername = employeeUsername;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    // Constructor with only date
    public Timecard(String employeeUsername, Date date) {
        this.employeeUsername = employeeUsername;
        this.date = date;
        this.timeIn = null;
        this.timeOut = null;
    }

    // Getters and setters
    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public Date getDate() {
        return date;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }

    // save to file
    public String toFileString() {
        return employeeUsername + "," + date.getTime() + ","
                + (timeIn == null ? "null" : timeIn.getTime()) + ","
                + (timeOut == null ? "null" : timeOut.getTime());
    }

    // load from file
    public static Timecard fromFileString(String line) throws Exception {

        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(",");

        if (parts.length != 4) {
            throw new Exception("Invalid timecard line: " + line);
        }

        String username = parts[0];
        Date date = new Date(Long.parseLong(parts[1]));

        Date timeIn = parts[2].equals("null")
                ? null
                : new Date(Long.parseLong(parts[2]));

        Date timeOut = parts[3].equals("null")
                ? null
                : new Date(Long.parseLong(parts[3]));

        return new Timecard(username, date, timeIn, timeOut);
    }

}
