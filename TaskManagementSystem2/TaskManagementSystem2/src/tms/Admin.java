package tms;

//This class represents the administrator of the system
public class Admin extends User {

    private final UserManager userManager;
    private final ProjectManager projectManager;

    public Admin(String username, String password, UserManager um, ProjectManager pm) {
        super(username, password, "ADMIN");
        this.userManager = um;
        this.projectManager = pm;
    }

    // Admin login
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    //User  managment
    public void addUser(User u) throws Exception {
        userManager.addUser(u);
    }

    public void updateUser(User u) throws Exception {
        userManager.updateUser(u);
    }

    public void deleteUser(String username) throws Exception {
        userManager.deleteUser(username);
    }

    //project managment
    public void addProject(Project p) throws Exception {
        projectManager.addProject(p);
    }

    public void deleteProject(String projectName) throws Exception {
        projectManager.deleteProject(projectName);
    }
}
