package tms;

import java.util.List;

//handles storing and updating leave requests
public class LeaveManager {

    private final FileManager fm;
    private final String FILE = "data/Leaves.txt";

    public LeaveManager(FileManager fm) {
        this.fm = fm;
    }

    // Add new leave request
    public void addLeave(Leave l) throws Exception {
        String line = l.getEmployeeUsername() + ","
                + l.getLeaveType() + ","
                + l.getReason() + ","
                + l.getStartDate() + ","
                + l.getEndDate() + ","
                + l.getStatus();
        fm.writeLine(FILE, line);
    }

    // Update leave status (approve or disapprove)
    public void updateLeave(Leave updatedLeave, String newStatus) throws Exception {
        List<String> leaves = getAllLeaves();
        boolean found = false;

        for (int i = 0; i < leaves.size(); i++) {
            String[] parts = leaves.get(i).split(",");

            // Match leave by employee
            if (parts[0].equals(updatedLeave.getEmployeeUsername())
                    && parts[1].equals(updatedLeave.getLeaveType())
                    && parts[3].equals(updatedLeave.getStartDate())
                    && parts[4].equals(updatedLeave.getEndDate())) {

                // Update status column
                leaves.set(i, parts[0] + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + newStatus);
                found = true;
                break;
            }
        }
        // If leave not found, add it as a new record
        if (!found) {
            leaves.add(updatedLeave.getEmployeeUsername() + ","
                    + updatedLeave.getLeaveType() + ","
                    + updatedLeave.getReason() + ","
                    + updatedLeave.getStartDate() + ","
                    + updatedLeave.getEndDate() + ","
                    + newStatus);
        }
        fm.overwriteFile(FILE, leaves);
    }

    // Read all leave records
    public List<String> getAllLeaves() throws Exception {
        List<String> lines = fm.readLines(FILE);
        return lines;
    }

}
