package tms.gui;

import tms.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class AdminDashboard extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminDashboard.class.getName());

    private final UserManager userManager;
    private final ProjectManager projectManager;
    private final EmployeeManager employeeManager;
    private final TaskManager taskManager;
    private final FileManager fileManager;

    public AdminDashboard(UserManager um, ProjectManager pm) {
        this.userManager = um;
        this.projectManager = pm;
        this.fileManager = new FileManager();

        this.taskManager = new TaskManager(fileManager);
        this.employeeManager = new EmployeeManager(fileManager);

        initComponents();
        setLocationRelativeTo(null);

        // Loading tables data
        loadUsersTable();
        loadEmployeesTable();
        loadTasksTable();
        loadProjectsTable();
    }

    //tables loading
    private void loadUsersTable() {
        try {
            List<User> users = userManager.getAllUsers();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            for (User u : users) {
                model.addRow(new Object[]{u.getUsername(), u.getRole()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading users!");
        }
    }

    private void loadEmployeesTable() {
        try {
            List<Employee> employees = employeeManager.getAllEmployees();
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            for (Employee e : employees) {
                model.addRow(new Object[]{e.getUsername(), e.getEmployeeType()});
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading employees!");
        }
    }

    private void loadTasksTable() {
        try {
            List<Task> tasks = taskManager.getAllTasks();
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
            for (Task t : tasks) {
                model.addRow(new Object[]{
                    t.getCode(),
                    t.getTitle(),
                    t.getAssignedEmployee() != null ? t.getAssignedEmployee().getUsername() : "",
                    t.getPhase(),
                    t.getProject() != null ? t.getProject().getProjectName() : "",
                    t.getPriority(),
                    t.getStartDate(),
                    t.getEndDate()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading tasks!");
        }
    }

    private void loadProjectsTable() {
        try {
            List<Project> projects = projectManager.getAllProjects();
            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);
            for (Project p : projects) {
                model.addRow(new Object[]{p.getProjectName(), p.getClientName()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading projects!");
        }
    }

    //DELETE METHODS 
    private void removeUserAndEmployee(String username) {
        try {
            List<Employee> employees = employeeManager.getAllEmployees();
            for (Employee e : employees) {
                if (e.getUsername().equals(username)) {
                    employeeManager.removeEmployee(e);
                    break;
                }
            }
            userManager.deleteUser(username);

            // Refresh tables
            loadUsersTable();
            loadEmployeesTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting user/employee!");
        }
    }

    private void deleteTaskTab() {
        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this task?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            String taskCode = jTable3.getValueAt(selectedRow, 0).toString();
            taskManager.deleteTask(taskCode);
            loadTasksTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting task!");
        }
    }

    private void deleteProjectTab() {
        int selectedRow = jTable4.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this project?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            String projectName = jTable4.getValueAt(selectedRow, 0).toString();
            projectManager.deleteProject(projectName);
            loadProjectsTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting project!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnsPanel = new javax.swing.JPanel();
        AddUserBtn = new javax.swing.JButton();
        UpdateUserBtn = new javax.swing.JButton();
        DeleteUserBtn = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Username", "Role"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTabbedPane1.addTab("Users Management", jScrollPane1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Username", "Type"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTabbedPane1.addTab("Employee Management", jScrollPane2);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "project name", "client name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jTabbedPane1.addTab("Project Management", jScrollPane4);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Title", "Employee", "Phase", "project", "priority", "Start", "End"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jTabbedPane1.addTab("Task Management", jScrollPane3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 290));

        btnsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddUserBtn.setText("Add");
        AddUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddUserBtnActionPerformed(evt);
            }
        });
        btnsPanel.add(AddUserBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 17, 197, 39));

        UpdateUserBtn.setText("Update");
        UpdateUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateUserBtnActionPerformed(evt);
            }
        });
        btnsPanel.add(UpdateUserBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 17, 197, 39));

        DeleteUserBtn.setText("Delete");
        DeleteUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteUserBtnActionPerformed(evt);
            }
        });
        btnsPanel.add(DeleteUserBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 17, 197, 39));

        jButton6.setText("Logout");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        btnsPanel.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 73, 197, 39));

        getContentPane().add(btnsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 286, 620, 120));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

        this.dispose();

        LoginFrame login = new LoginFrame();
        login.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void DeleteUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteUserBtnActionPerformed
        // TODO add your handling code here:

        int selectedTab = jTabbedPane1.getSelectedIndex();
        switch (selectedTab) {
            case 0, 1:  // Users or Employees tab
                JTable table = selectedTab == 0 ? jTable1 : jTable2;
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a row to delete!");
                    return;
                }
                String username = table.getValueAt(row, 0).toString();
                if (JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    removeUserAndEmployee(username);
                }
                break;
            case 2: // Project Tab
                deleteProjectTab();
                break;

            case 3: // Tasks tab
                JOptionPane.showMessageDialog(this, "Admin cannot delete tasks. Only Leaders can add/update/delete tasks.");
                return;

        }


    }//GEN-LAST:event_DeleteUserBtnActionPerformed

    private void UpdateUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateUserBtnActionPerformed
        // TODO add your handling code here:
        int selectedTab = jTabbedPane1.getSelectedIndex();

        try {
            switch (selectedTab) {
                // Users tab 
                case 0: {
                    int row = jTable1.getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(this, "Please select a user to update!");
                        return;
                    }
                    String oldUsername = jTable1.getValueAt(row, 0).toString();

                    String newUsername = JOptionPane.showInputDialog(this, "New username:", oldUsername);
                    if (newUsername == null) {
                        return;
                    }

                    String newPassword = JOptionPane.showInputDialog(this, "New password:");
                    if (newPassword == null) {
                        return;
                    }

                    String newRole = JOptionPane.showInputDialog(this, "New role (ADMIN/LEADER/EMPLOYEE):");
                    if (newRole == null) {
                        return;
                    }

                    User newUser;
                    if (newRole.equalsIgnoreCase("ADMIN")) {
                        newUser = new Admin(newUsername, newPassword, userManager, projectManager);
                    } else if (newRole.equalsIgnoreCase("LEADER")) {
                        newUser = new Leader(newUsername, newPassword);
                    } else {
                        String newType = JOptionPane.showInputDialog(this, "Employee type:");
                        if (newType == null) {
                            return;
                        }

                        newUser = new Employee(newUsername, newPassword, newType);

                        Employee oldEmp = employeeManager.getEmployeeByUsername(oldUsername);
                        if (oldEmp != null) {
                            employeeManager.removeEmployee(oldEmp);
                        }
                        employeeManager.addEmployee((Employee) newUser);
                    }

                    userManager.deleteUser(oldUsername);
                    userManager.addUser(newUser);

                    loadUsersTable();
                    loadEmployeesTable();
                    break;
                }

                // Employees tab
                case 1: {
                    int row = jTable2.getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(this, "Select an employee to update!");
                        return;
                    }
                    String oldUsername = jTable2.getValueAt(row, 0).toString();
                    String oldType = jTable2.getValueAt(row, 1).toString();

                    String newUsername = JOptionPane.showInputDialog(this, "New username:", oldUsername);
                    if (newUsername == null) {
                        return;
                    }
                    String newPassword = JOptionPane.showInputDialog(this, "New password:");
                    if (newPassword == null) {
                        return;
                    }
                    String newType = JOptionPane.showInputDialog(this, "New type:", oldType);
                    if (newType == null) {
                        return;
                    }

                    Employee oldEmp = employeeManager.getEmployeeByUsername(oldUsername);
                    if (oldEmp != null) {
                        employeeManager.removeEmployee(oldEmp);
                    }

                    Employee newEmp = new Employee(newUsername, newPassword, newType);
                    employeeManager.addEmployee(newEmp);

                    userManager.deleteUser(oldUsername);
                    userManager.addUser(newEmp);

                    loadEmployeesTable();
                    loadUsersTable();
                    break;
                }

                //Projects tab 
                case 2: {
                    int row = jTable4.getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(this, "Select a project to update!");
                        return;
                    }
                    String oldProj = jTable4.getValueAt(row, 0).toString();

                    String newProjName = JOptionPane.showInputDialog(this, "New project name:", oldProj);
                    if (newProjName == null) {
                        return;
                    }
                    String newClient = JOptionPane.showInputDialog(this, "New client:");
                    if (newClient == null) {
                        return;
                    }

                    projectManager.deleteProject(oldProj);
                    Project newProject = new Project(newProjName, newClient);
                    projectManager.addProject(newProject);

                    loadProjectsTable();
                    break;
                }

                // Tasks tab
                case 3:
                    int row = jTable3.getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(this, "Select a task to update!");
                        return;
                    }

                    String taskCode = jTable3.getValueAt(row, 0).toString();
                    Task task = taskManager.getAllTasks().stream()
                            .filter(t -> t.getCode().equals(taskCode))
                            .findFirst().orElse(null);
                    if (task == null) {
                        return;
                    }

                    String newPhase = JOptionPane.showInputDialog(this, "Update Phase:", task.getPhase());
                    if (newPhase != null && !newPhase.trim().isEmpty()) {
                        task.setPhase(newPhase);
                        taskManager.deleteTask(taskCode);
                        taskManager.addTask(task);
                        loadTasksTable();
                    }
                    break;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating data: " + ex.getMessage());
        }
    }//GEN-LAST:event_UpdateUserBtnActionPerformed

    private void AddUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddUserBtnActionPerformed
        // TODO add your handling code here:
        int selectedTab = jTabbedPane1.getSelectedIndex();

        try {
            switch (selectedTab) {

                //USERS TAB
                case 0:
                    String username = JOptionPane.showInputDialog(this, "Enter username:");
                    String password = JOptionPane.showInputDialog(this, "Enter password:");
                    String role = JOptionPane.showInputDialog(this, "Enter role (ADMIN/LEADER/EMPLOYEE):");

                    if (username == null || password == null || role == null) {
                        return;
                    }

                    if (role.equalsIgnoreCase("ADMIN")) {
                        Admin admin = new Admin(username, password, userManager, projectManager);
                        userManager.addUser(admin);

                    } else if (role.equalsIgnoreCase("LEADER")) {
                        Leader leader = new Leader(username, password);
                        userManager.addUser(leader);

                    } else {
                        String employeeType = JOptionPane.showInputDialog(this, "Enter employee type:");
                        if (employeeType == null) {
                            return;
                        }

                        Employee emp = new Employee(username, password, employeeType);
                        userManager.addUser(emp);
                        employeeManager.addEmployee(emp);
                    }

                    loadUsersTable();
                    loadEmployeesTable();
                    break;

                //EMPLOYEES TAB 
                case 1:
                    username = JOptionPane.showInputDialog(this, "Enter username:");
                    password = JOptionPane.showInputDialog(this, "Enter password:");
                    String type = JOptionPane.showInputDialog(this, "Enter employee type:");

                    if (username == null || password == null || type == null) {
                        return;
                    }

                    Employee emp = new Employee(username, password, type);
                    userManager.addUser(emp);
                    employeeManager.addEmployee(emp);

                    loadUsersTable();
                    loadEmployeesTable();
                    break;

                // PROJECTS TAB 
                case 2:
                    String projectName = JOptionPane.showInputDialog(this, "Enter project name:");
                    String clientName = JOptionPane.showInputDialog(this, "Enter client name:");

                    if (projectName == null || clientName == null) {
                        return;
                    }

                    Project p = new Project(projectName, clientName);
                    projectManager.addProject(p);
                    loadProjectsTable();
                    break;

                // TASKS TAB
                case 3:
                    JOptionPane.showMessageDialog(this, "Admin cannot add tasks. Only Leaders can add/update/delete tasks.");
                    return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding data!");
        }

    }//GEN-LAST:event_AddUserBtnActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FileManager fm = new FileManager();
        ProjectManager pm = new ProjectManager(fm);
        UserManager um = new UserManager(fm);
        AdminDashboard dashboard = new AdminDashboard(um, pm);
        dashboard.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddUserBtn;
    private javax.swing.JButton DeleteUserBtn;
    private javax.swing.JButton UpdateUserBtn;
    private javax.swing.JPanel btnsPanel;
    private javax.swing.JButton jButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
