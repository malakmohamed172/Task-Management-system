package tms;

import java.util.ArrayList;
import java.util.List;

//handles adding, deleting, and reading projects
public class ProjectManager {

    private final FileManager fm;
    private final String PROJECT_FILE = "data/Projects.txt";

    public ProjectManager(FileManager fm) {
        this.fm = fm;
    }

    // Add a new project
    public void addProject(Project p) throws Exception {
        fm.writeLine(PROJECT_FILE, p.getProjectName() + "," + p.getClientName());
    }

    // Delete project
    public void deleteProject(String projectName) throws Exception {
        List<String> lines = fm.readLines(PROJECT_FILE);
        List<String> newLines = new ArrayList<>();
        for (String l : lines) {
            if (!l.split(",")[0].equals(projectName)) {
                newLines.add(l);
            }
        }
        fm.overwriteFile(PROJECT_FILE, newLines);
    }

    //Read all projects
    public List<Project> getAllProjects() throws Exception {
        List<Project> projects = new ArrayList<>();
        List<String> lines = fm.readLines(PROJECT_FILE);
        for (String l : lines) {
            if (l.trim().isEmpty()) {
                continue;
            }
            String[] parts = l.split(",", -1);
            String name = parts[0];
            String client = parts.length > 1 ? parts[1] : "";
            projects.add(new Project(name, client));
        }
        return projects;
    }

    // Get project by name
    public Project getProjectByName(String name) throws Exception {
        for (Project p : getAllProjects()) {
            if (p.getProjectName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
