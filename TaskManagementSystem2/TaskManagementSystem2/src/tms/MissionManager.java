package tms;

import java.util.ArrayList;
import java.util.List;

//handles storing and retrieving missions for employees
public class MissionManager {

    private final FileManager fm;
    private final String FILE = "data/Missions.txt";

    public MissionManager(FileManager fm) {
        this.fm = fm;
    }

    // Add a new mission
    public void addMission(Mission m) throws Exception {
        fm.writeLine(
                FILE,
                m.getEmployeeUsername() + ","
                + m.getMissionDescription().replace(",", ";") + ","
                + m.getPriority() + ","
                + m.getStatus());
    }

    // Get all missions
    public List<String> getAllMissions() throws Exception {
        return fm.readLines(FILE);
    }

    public void updateMission(Mission m) throws Exception {
        List<String> lines = fm.readLines(FILE);
        List<String> updated = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",", 4);
            if (parts[0].equals(m.getEmployeeUsername())
                    && parts[1].equals(m.getMissionDescription())) {

                updated.add(
                        m.getEmployeeUsername() + ","
                        + m.getMissionDescription() + ","
                        + m.getPriority() + ","
                        + m.getStatus()
                );
            } else {
                updated.add(line);
            }
        }

        fm.overwriteFile(FILE, updated);
    }
}
