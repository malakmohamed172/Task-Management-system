package tms;

import java.util.*;

public class TimecardManager {

    private final FileManager fm;
    private final String FILE = "data/Timecards.txt";

    public TimecardManager(FileManager fm) {
        this.fm = fm;
    }

    // Add a new timecard
    public void addTimecard(Timecard t) throws Exception {
        fm.writeLine(FILE, t.toFileString());
    }

    // Get all timecards
    public List<Timecard> getAllTimecards() throws Exception {
        List<Timecard> list = new ArrayList<>();
        for (String line : fm.readLines(FILE)) {
            Timecard t = Timecard.fromFileString(line);
            if (t != null) {
                list.add(t);
            }
        }

        return list;
    }

    // Delete a timecard
    public void deleteTimecard(Timecard t) throws Exception {
        List<String> lines = fm.readLines(FILE);
        List<String> newLines = new ArrayList<>();
        for (String line : lines) {
            Timecard existing = Timecard.fromFileString(line);
            if (!(existing.getEmployeeUsername().equals(t.getEmployeeUsername())
                    && existing.getDate().equals(t.getDate()))) {
                newLines.add(line);
            }
        }
        fm.overwriteFile(FILE, newLines);
    }

    // Update timecard
    public void updateTimecard(Timecard oldT, Timecard updated) throws Exception {
        List<String> lines = fm.readLines(FILE);
        List<String> newLines = new ArrayList<>();
        for (String line : lines) {
            Timecard t = Timecard.fromFileString(line);
            if (t.getEmployeeUsername().equals(oldT.getEmployeeUsername())
                    && t.getDate().equals(oldT.getDate())) {
                newLines.add(updated.toFileString());
            } else {
                newLines.add(line);
            }
        }
        fm.overwriteFile(FILE, newLines);
    }

    // Check In
    public void checkIn(String username) throws Exception {
        Date now = new Date();
        Timecard t = new Timecard(username, now, now, null);
        fm.writeLine(FILE, t.toFileString());
    }

    // Check Out
    public void checkOut(String username) throws Exception {

        List<String> lines = fm.readLines(FILE);
        List<String> newLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {

            Timecard t = Timecard.fromFileString(line);

            if (t == null) {
                continue;
            }

            // Update timeOut if employee has checked in but not out
            if (t.getEmployeeUsername().equals(username)
                    && t.getTimeIn() != null
                    && t.getTimeOut() == null) {

                t.setTimeOut(new Date());
                newLines.add(t.toFileString());
                found = true;

            } else {
                newLines.add(line);
            }
        }

        if (!found) {
            throw new Exception("You must check in first");
        }

        fm.overwriteFile(FILE, newLines);
    }

}
