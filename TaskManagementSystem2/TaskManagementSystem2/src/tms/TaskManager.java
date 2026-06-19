package tms;

import java.util.*;
import java.text.SimpleDateFormat;

//handles adding, deleting, and reading tasks from file
public class TaskManager {

    private final FileManager fm;
    private final String TASK_FILE = "data/Tasks.txt";
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public TaskManager(FileManager fm) {
        this.fm = fm;
    }

    // Add a task
    public void addTask(Task t) throws Exception {
        String assigned = t.getAssignedEmployee() != null ? t.getAssignedEmployee().getUsername() : "";
        String project = t.getProject() != null ? t.getProject().getProjectName() : "";
        String priority = t.getPriority() != null ? t.getPriority() : "";
        String start = t.getStartDate() != null ? sdf.format(t.getStartDate()) : "";
        String end = t.getEndDate() != null ? sdf.format(t.getEndDate()) : "";

        String line = t.getCode() + "," + t.getTitle() + ","
                + assigned + "," + t.getPhase() + ","
                + project + "," + priority + ","
                + start + "," + end;

        fm.writeLine(TASK_FILE, line);
    }

    // Delete task (by code)
    public void deleteTask(String code) throws Exception {
        List<Task> tasks = getAllTasks();
        tasks.removeIf(t -> t.getCode().equals(code));

        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            String assigned = t.getAssignedEmployee() != null ? t.getAssignedEmployee().getUsername() : "";
            String project = t.getProject() != null ? t.getProject().getProjectName() : "";
            String priority = t.getPriority() != null ? t.getPriority() : "";
            String start = t.getStartDate() != null ? sdf.format(t.getStartDate()) : "";
            String end = t.getEndDate() != null ? sdf.format(t.getEndDate()) : "";

            lines.add(t.getCode() + "," + t.getTitle() + ","
                    + assigned + "," + t.getPhase() + ","
                    + project + "," + priority + ","
                    + start + "," + end);
        }

        fm.overwriteFile(TASK_FILE, lines);
    }

    // Get all tasks
    public List<Task> getAllTasks() throws Exception {
        List<Task> tasks = new ArrayList<>();
        EmployeeManager empManager = new EmployeeManager(fm);
        List<Employee> allEmployees = empManager.getAllEmployees();

        for (String line : fm.readLines(TASK_FILE)) {
            String[] parts = line.split(",", -1);

            String code = parts.length > 0 ? parts[0] : "";
            String title = parts.length > 1 ? parts[1] : "";
            String employeeName = parts.length > 2 ? parts[2] : "";
            String phase = parts.length > 3 ? parts[3] : "";
            String projectName = parts.length > 4 ? parts[4] : "";
            String priority = parts.length > 5 ? parts[5] : "";
            String startStr = parts.length > 6 ? parts[6] : "";
            String endStr = parts.length > 7 ? parts[7] : "";

            Task t = new Task(code, title);

            // Assign employee if exists
            Employee assigned = null;
            if (!employeeName.isEmpty()) {
                for (Employee e : allEmployees) {
                    if (e.getUsername().equals(employeeName)) {
                        assigned = e;
                        break;
                    }
                }
            }
            t.setAssignedEmployee(assigned);
            t.setPhase(phase);

            // Set project if exists
            if (!projectName.isEmpty()) {
                t.setProject(new Project(projectName));
            }
            t.setPriority(priority);

            if (!startStr.isEmpty()) {
                t.setStartDate(sdf.parse(startStr));
            }
            if (!endStr.isEmpty()) {
                t.setEndDate(sdf.parse(endStr));
            }

            tasks.add(t);
        }

        return tasks;
    }
}
