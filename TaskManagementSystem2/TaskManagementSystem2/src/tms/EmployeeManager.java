package tms;

import java.util.ArrayList;
import java.util.List;

//this class handles all employee data operations
public class EmployeeManager {

    private final FileManager fm;
    private final String EMPLOYEE_FILE = "data/Employees.txt";

    public EmployeeManager(FileManager fm) {
        this.fm = fm;
    }

    // Add new employee
    public void addEmployee(Employee e) throws Exception {
        String line = e.getUsername() + "," + e.getPassword() + "," + e.getEmployeeType();
        fm.writeLine(EMPLOYEE_FILE, line);
    }

    // Remove employee
    public void removeEmployee(Employee e) throws Exception {
        List<String> lines = fm.readLines(EMPLOYEE_FILE);
        List<String> newLines = new ArrayList<>();
        for (String l : lines) {
            if (!l.startsWith(e.getUsername() + ",")) {
                newLines.add(l);
            }
        }
        fm.overwriteFile(EMPLOYEE_FILE, newLines);
    }

    // Read all employees
    public List<Employee> getAllEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        for (String line : fm.readLines(EMPLOYEE_FILE)) {
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",", -1);
            if (parts.length < 1) {
                continue;
            }
            String username = parts[0];
            String password = parts.length > 1 ? parts[1] : "";
            String type = parts.length > 2 ? parts[2] : "";

            employees.add(new Employee(username, password, type));
        }
        return employees;
    }

    // Get one employee
    public Employee getEmployeeByUsername(String username) throws Exception {
        for (Employee e : getAllEmployees()) {
            if (e.getUsername().equals(username)) {
                return e;
            }
        }
        return null;
    }
}
