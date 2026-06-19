package tms;

//The Leader class extends User, Can can manage tasks and control their progress.
public class Leader extends User {

    public Leader(String username, String password) {
        super(username, password, "LEADER");
    }

    // Leader login
    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    //Managing tasks
    public void createTask(Task t, TaskManager tm) throws Exception {
        tm.addTask(t);
    }

    public void updateTask(Task t, TaskManager tm) throws Exception {
        tm.deleteTask(t.getCode());
        tm.addTask(t);
    }

    // Assign task to another employee
    public void reassignTask(Task t, Employee e) {
        t.setAssignedEmployee(e);
    }

    // Change task phase
    public void evaluateTask(Task t, String phase) {
        t.setPhase(phase);
    }
}
