package tms;

import java.util.*;

//Handles CRUD operations for Users
public class UserManager {

    private final FileManager fm;
    private final String USER_FILE = "data/Users.txt";

    public UserManager(FileManager fm) {
        this.fm = fm;
    }

    // Add a new user
    public void addUser(User u) throws Exception {
        String line = u.getUsername() + "," + u.getPassword() + "," + u.getRole();
        fm.writeLine(USER_FILE, line);
    }

    // Delete a user 
    public void deleteUser(String username) throws Exception {
        List<User> users = getAllUsers();
        User toRemove = null;
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                toRemove = u;
                break;
            }
        }
        if (toRemove != null) {
            users.remove(toRemove);
            List<String> lines = new ArrayList<>();
            for (User u : users) {
                lines.add(u.getUsername() + "," + u.getPassword() + "," + u.getRole());
            }
            fm.overwriteFile(USER_FILE, lines);
        }
    }

    // Update existing user
    public void updateUser(User u) throws Exception {
        List<User> users = getAllUsers();
        List<String> lines = new ArrayList<>();
        for (User existing : users) {
            if (existing.getUsername().equals(u.getUsername())) {
                lines.add(u.getUsername() + "," + u.getPassword() + "," + u.getRole());
            } else {
                lines.add(existing.getUsername() + "," + existing.getPassword() + "," + existing.getRole());
            }
        }
        fm.overwriteFile(USER_FILE, lines);
    }

    // Get all users from file
    public List<User> getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        for (String line : fm.readLines(USER_FILE)) {
            String[] parts = line.split(",", -1);
            if (parts.length < 3) {
                continue;
            }

            String username = parts[0];
            String password = parts[1];
            String role = parts[2];

            User u;
            switch (role.toUpperCase()) {
                case "ADMIN":
                    u = new Admin(username, password, this, null);
                    break;
                case "LEADER":
                    u = new Leader(username, password);
                    break;
                case "EMPLOYEE":
                    u = new Employee(username, password, "");
                    break;
                default:
                    continue;
            }
            users.add(u);
        }
        return users;
    }
}
