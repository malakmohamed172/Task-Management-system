package tms.gui;

import tms.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class LeaderDashboard extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LeaderDashboard.class.getName());

    private final TaskManager taskManager;
    private final EmployeeManager employeeManager;
    private final ProjectManager projectManager;
    private final FileManager fileManager;
    private final TimecardManager timecardManager;
    private final LeaveManager leaveManager;
    private final MissionManager missionManager;

    public LeaderDashboard(TaskManager tm, EmployeeManager em, ProjectManager pm, FileManager fm, TimecardManager tcm, LeaveManager lm, MissionManager mm) {
        this.taskManager = tm;
        this.employeeManager = em;
        this.projectManager = pm;
        this.fileManager = fm;
        this.timecardManager = tcm;
        this.leaveManager = lm;
        this.missionManager = mm;
        initComponents();
        setLocationRelativeTo(null);

        // Load all tables
        loadTasksTable();
        loadTimecardsTable();
        loadLeavesTable();
        loadMissionRequestsTable();
    }

//load tables
    private void loadLeavesTable() {
        DefaultTableModel model = (DefaultTableModel) leaveTable.getModel();
        model.setRowCount(0);
        try {
            List<String> leaves = leaveManager.getAllLeaves();
            for (String line : leaves) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    model.addRow(new Object[]{parts[0], parts[1], parts[3], parts[4], parts[2], parts[5]});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading leaves: " + e.getMessage());
        }
    }

    private void updateLeaveStatus(String status) {
        int row = leaveTable.getSelectedRow();
        if (row == -1) {
            return;
        }

        String emp = leaveTable.getValueAt(row, 0).toString();
        String type = leaveTable.getValueAt(row, 1).toString();
        String start = leaveTable.getValueAt(row, 2).toString();
        String end = leaveTable.getValueAt(row, 3).toString();
        String reason = leaveTable.getValueAt(row, 4).toString();

        try {
            Leave l = new Leave(emp, type);
            l.setStartDate(start);
            l.setEndDate(end);
            l.setReason(reason);

            leaveManager.updateLeave(l, status);
            loadLeavesTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating leave status.");
        }
    }

    private void loadTimecardsTable() {
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        model.setRowCount(0);

        List<Timecard> timecards;
        try {
            timecards = timecardManager.getAllTimecards();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }

        for (Timecard t : timecards) {

            if (t == null) {
                continue;
            }
            model.addRow(new Object[]{
                t.getEmployeeUsername(),
                t.getDate(),
                t.getTimeIn(),
                t.getTimeOut()
            });
        }

    }

    private void loadTasksTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            List<Task> tasks = taskManager.getAllTasks();
            for (Task t : tasks) {
                model.addRow(new Object[]{
                    t.getCode(),
                    t.getTitle(),
                    t.getAssignedEmployee() == null ? "" : t.getAssignedEmployee().getUsername(),
                    t.getPhase(),
                    t.getProject() == null ? "" : t.getProject().getProjectName(),
                    t.getPriority(),
                    new SimpleDateFormat("dd/MM/yyyy").format(t.getStartDate()),
                    new SimpleDateFormat("dd/MM/yyyy").format(t.getEndDate())
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading tasks!");
        }
    }

    private void loadMissionRequestsTable() {
        DefaultTableModel model = (DefaultTableModel) missionTable.getModel();
        model.setRowCount(0);
        try {
            List<String> lines = missionManager.getAllMissions();
            for (String line : lines) {
                String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    model.addRow(new Object[]{
                        parts[0], parts[1].replace(";", ","), parts[2], parts[3]});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading mission requests: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        AddBtn = new javax.swing.JButton();
        UpdateBtn = new javax.swing.JButton();
        DeleteBtn = new javax.swing.JButton();
        lgoutBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        leaveTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        leaveApproveBtn = new javax.swing.JButton();
        leaveDisapproveBtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        missionTable = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        approveBtn = new javax.swing.JButton();
        disapproveBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        refreshTCbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Title", "Employee", "Phase", "Project", "Priority", "Start", "End"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AddBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddBtn.setText("Add");
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });
        jPanel4.add(AddBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 230, 50));

        UpdateBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UpdateBtn.setText("Update");
        UpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBtnActionPerformed(evt);
            }
        });
        jPanel4.add(UpdateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 200, 50));

        DeleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteBtn.setText("Delete");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });
        jPanel4.add(DeleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 220, 50));

        lgoutBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lgoutBtn.setText("Logout");
        lgoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lgoutBtnActionPerformed(evt);
            }
        });
        jPanel4.add(lgoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 220, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Task Managment", jPanel1);

        jPanel7.setLayout(new java.awt.BorderLayout());

        leaveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Employee", "LeaveType", "Start Date", "End Date", "Reason ", "status"
            }
        ));
        jScrollPane2.setViewportView(leaveTable);

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        leaveApproveBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        leaveApproveBtn.setText("Approve");
        leaveApproveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveApproveBtnActionPerformed(evt);
            }
        });

        leaveDisapproveBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        leaveDisapproveBtn.setText("Disapprove");
        leaveDisapproveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveDisapproveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addComponent(leaveApproveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addComponent(leaveDisapproveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leaveApproveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leaveDisapproveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Leave Requests", jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout());

        missionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Employee", " Mission Description", "priority", "Status"
            }
        ));
        jScrollPane3.setViewportView(missionTable);

        jPanel6.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        approveBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        approveBtn.setText("Approve");
        approveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveBtnActionPerformed(evt);
            }
        });

        disapproveBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        disapproveBtn.setText("Disapprove");
        disapproveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disapproveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addComponent(approveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(disapproveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(disapproveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel6.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        jTabbedPane1.addTab("Mission Requests", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Review Requests", jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Employee", "Date", "Time In", "Time Out"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jPanel3.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        refreshTCbtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshTCbtn.setText("Refresh");
        refreshTCbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshTCbtnActionPerformed(evt);
            }
        });
        jPanel9.add(refreshTCbtn);

        jPanel3.add(jPanel9, java.awt.BorderLayout.PAGE_END);

        jTabbedPane2.addTab("Timecard Review", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        // TODO add your handling code here:
        try {
            String code = JOptionPane.showInputDialog(this, "Enter task code:");
            String title = JOptionPane.showInputDialog(this, "Enter task title:");
            String empName = JOptionPane.showInputDialog(this, "Enter employee username (optional):");
            String phase = JOptionPane.showInputDialog(this, "Enter phase:");
            String projName = JOptionPane.showInputDialog(this, "Enter project name:");
            String priority = JOptionPane.showInputDialog(this, "Enter priority:");
            String startDateStr = JOptionPane.showInputDialog(this, "Enter start date (dd/MM/yyyy):");
            String endDateStr = JOptionPane.showInputDialog(this, "Enter end date (dd/MM/yyyy):");

            if (code == null || title == null || phase == null || projName == null
                    || priority == null || startDateStr == null || endDateStr == null) {
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = sdf.parse(startDateStr);
            Date endDate = sdf.parse(endDateStr);

            Employee assignedEmp = null;
            if (empName != null && !empName.trim().isEmpty()) {
                assignedEmp = employeeManager.getEmployeeByUsername(empName);
            }

            Project proj = projectManager.getProjectByName(projName);

            Task newTask = new Task(code, title, assignedEmp, phase, proj, priority, startDate, endDate);
            taskManager.addTask(newTask);
            loadTasksTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding task!");
        }

    }//GEN-LAST:event_AddBtnActionPerformed

    private void UpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBtnActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a task to update!");
            return;
        }

        try {
            String taskCode = jTable1.getValueAt(row, 0).toString();

            Task task = taskManager.getAllTasks().stream()
                    .filter(t -> t.getCode().equals(taskCode))
                    .findFirst().orElse(null);

            if (task == null) {
                JOptionPane.showMessageDialog(this, "Task not found!");
                return;
            }

            //Convert Dates to String
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String startStr = sdf.format(task.getStartDate());
            String endStr = sdf.format(task.getEndDate());

            //Ask user for updates
            String newTitle = JOptionPane.showInputDialog(this, "Update Title:", task.getTitle());
            String newEmp = JOptionPane.showInputDialog(this, "Update Employee Username:",
                    task.getAssignedEmployee() == null ? "" : task.getAssignedEmployee().getUsername());
            String newPhase = JOptionPane.showInputDialog(this, "Update Phase:", task.getPhase());
            String newProj = JOptionPane.showInputDialog(this, "Update Project:",
                    task.getProject() == null ? "" : task.getProject().getProjectName());
            String newPriority = JOptionPane.showInputDialog(this, "Update Priority:", task.getPriority());
            String newStart = JOptionPane.showInputDialog(this, "Update Start Date (dd/MM/yyyy):", startStr);
            String newEnd = JOptionPane.showInputDialog(this, "Update End Date (dd/MM/yyyy):", endStr);

            //APPLY UPDATES
            task.setTitle(newTitle);
            task.setPhase(newPhase);
            task.setPriority(newPriority);

            task.setStartDate(sdf.parse(newStart));
            task.setEndDate(sdf.parse(newEnd));

            // UPDATE EMPLOYEE
            if (newEmp != null && !newEmp.trim().isEmpty()) {
                Employee emp = employeeManager.getEmployeeByUsername(newEmp);
                task.setAssignedEmployee(emp);
            } else {
                task.setAssignedEmployee(null);
            }

            // UPDATE PROJECT
            if (newProj != null && !newProj.trim().isEmpty()) {
                Project proj = projectManager.getProjectByName(newProj);
                task.setProject(proj);
            } else {
                task.setProject(null);
            }

            // SAVE
            taskManager.deleteTask(taskCode);
            taskManager.addTask(task);

            loadTasksTable();
            JOptionPane.showMessageDialog(this, "Task updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating task! " + e.getMessage());
        }
    }//GEN-LAST:event_UpdateBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a task to delete!");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Are you sure?", "Confirm Delete", JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION) {
            try {
                String taskCode = jTable1.getValueAt(row, 0).toString();
                taskManager.deleteTask(taskCode);
                loadTasksTable();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting task!");
            }
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed

    //LOGOUT
    private void lgoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lgoutBtnActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_lgoutBtnActionPerformed

    //LEAVE ACTIONS
    private void leaveApproveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveApproveBtnActionPerformed
        // TODO add your handling code here:
        int row = leaveTable.getSelectedRow();
        if (row == -1) {
            return;
        }

        String emp = leaveTable.getValueAt(row, 0) == null ? "" : leaveTable.getValueAt(row, 0).toString();
        String type = leaveTable.getValueAt(row, 1) == null ? "" : leaveTable.getValueAt(row, 1).toString();
        String start = leaveTable.getValueAt(row, 2) == null ? "" : leaveTable.getValueAt(row, 2).toString();
        String end = leaveTable.getValueAt(row, 3) == null ? "" : leaveTable.getValueAt(row, 3).toString();
        String reason = leaveTable.getValueAt(row, 4) == null ? "" : leaveTable.getValueAt(row, 4).toString();

        try {
            Leave l = new Leave(emp, type);
            l.setStartDate(start);
            l.setEndDate(end);
            l.setReason(reason);
            l.approve();

            leaveManager.updateLeave(l, "APPROVED");
            loadLeavesTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error approving leave.");
        }

    }//GEN-LAST:event_leaveApproveBtnActionPerformed

    //MISSION ACTIONS
    private void leaveDisapproveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveDisapproveBtnActionPerformed
        int row = leaveTable.getSelectedRow();
        if (row == -1) {
            return;
        }

        String emp = leaveTable.getValueAt(row, 0) == null ? "" : leaveTable.getValueAt(row, 0).toString();
        String type = leaveTable.getValueAt(row, 1) == null ? "" : leaveTable.getValueAt(row, 1).toString();
        String start = leaveTable.getValueAt(row, 2) == null ? "" : leaveTable.getValueAt(row, 2).toString();
        String end = leaveTable.getValueAt(row, 3) == null ? "" : leaveTable.getValueAt(row, 3).toString();
        String reason = leaveTable.getValueAt(row, 4) == null ? "" : leaveTable.getValueAt(row, 4).toString();

        try {
            Leave l = new Leave(emp, type);
            l.setStartDate(start);
            l.setEndDate(end);
            l.setReason(reason);
            l.disapprove();

            leaveManager.updateLeave(l, "DISAPPROVED");
            loadLeavesTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error disapproving leave.");
        }

    }//GEN-LAST:event_leaveDisapproveBtnActionPerformed

    private void refreshTCbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshTCbtnActionPerformed
        // TODO add your handling code here:
        loadTimecardsTable();
    }//GEN-LAST:event_refreshTCbtnActionPerformed

    private void approveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveBtnActionPerformed
        int row = missionTable.getSelectedRow();
        if (row == -1) {
            return;
        }

        String emp = missionTable.getValueAt(row, 0).toString();
        String desc = missionTable.getValueAt(row, 1).toString();
        String priority = missionTable.getValueAt(row, 2).toString();

        try {
            Mission m = new Mission(emp, desc, priority);
            m.approve();

            missionManager.updateMission(m);
            loadMissionRequestsTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error approving mission.");
        }

    }//GEN-LAST:event_approveBtnActionPerformed

    private void disapproveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disapproveBtnActionPerformed
        int row = missionTable.getSelectedRow();
        if (row == -1) {
            return;
        }

        String emp = missionTable.getValueAt(row, 0).toString();
        String desc = missionTable.getValueAt(row, 1).toString();
        String priority = missionTable.getValueAt(row, 2).toString();

        try {
            Mission m = new Mission(emp, desc, priority);
            m.disapprove();

            missionManager.updateMission(m);
            loadMissionRequestsTable();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error disapproving mission.");
        }
    }//GEN-LAST:event_disapproveBtnActionPerformed

    //MAIN
    public static void main(String args[]) {
        FileManager fm = new FileManager();
        ProjectManager pm = new ProjectManager(fm);
        TaskManager tm = new TaskManager(fm);
        EmployeeManager em = new EmployeeManager(fm);
        TimecardManager tcm = new TimecardManager(fm);
        LeaveManager lm = new LeaveManager(fm);
        MissionManager mm = new MissionManager(fm);

        LeaderDashboard leader = new LeaderDashboard(tm, em, pm, fm, tcm, lm, mm);
        leader.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JButton UpdateBtn;
    private javax.swing.JButton approveBtn;
    private javax.swing.JButton disapproveBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private javax.swing.JButton leaveApproveBtn;
    private javax.swing.JButton leaveDisapproveBtn;
    private javax.swing.JTable leaveTable;
    private javax.swing.JButton lgoutBtn;
    private javax.swing.JTable missionTable;
    private javax.swing.JButton refreshTCbtn;
    // End of variables declaration//GEN-END:variables
}
