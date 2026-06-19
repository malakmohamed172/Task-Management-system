package tms.gui;

import tms.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EmployeeFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EmployeeFrame.class.getName());

    private TaskManager taskManager;
    private String currentEmployeeUsername;
    private LeaveManager leaveManager;
    private Leave currentLeave;
    private MissionManager missionManager;

    public EmployeeFrame(TaskManager tm, String username, LeaveManager lm, MissionManager mm) {
        this.taskManager = tm;
        this.currentEmployeeUsername = username;
        this.leaveManager = lm;
        this.missionManager = mm;
        initComponents();

        setLocationRelativeTo(null);
        loadTasks();

    }

    private void loadTasks() {
        try {
            List<Task> tasks = taskManager.getAllTasks();
            DefaultTableModel model = (DefaultTableModel) tasksTable.getModel();
            model.setRowCount(0);

            for (Task t : tasks) {
                if (t.getAssignedEmployee() != null
                        && t.getAssignedEmployee().getUsername().equals(currentEmployeeUsername)) {
                    model.addRow(new Object[]{
                        t.getCode(), t.getTitle(), t.getPhase(),
                        t.getProject() != null ? t.getProject().getProjectName() : "",
                        t.getPriority(), t.getStartDate(), t.getEndDate()
                    });
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading tasks: " + ex.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tasksTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        phaseComboBox = new javax.swing.JComboBox<>();
        updateStatusButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        TimeCard = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ateendanceBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        checkoutBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        submitLeave = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        leaveType = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        leaveReason = new javax.swing.JTextArea();
        StartDate = new javax.swing.JTextField();
        EndDate = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tasksTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tasksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Title ", "Phase", "Project", "Priority", "Start", "End"
            }
        ));
        jScrollPane2.setViewportView(tasksTable);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));
        jPanel3.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Change Phase To:");
        jPanel3.add(jLabel2);

        phaseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pending", "test", "done" }));
        jPanel3.add(phaseComboBox);

        updateStatusButton.setText("Update Task Status");
        updateStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStatusButtonActionPerformed(evt);
            }
        });
        jPanel3.add(updateStatusButton);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("MyTasks", jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(3, 1));

        TimeCard.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "TimeCard", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Emoji", 1, 14))); // NOI18N
        TimeCard.setToolTipText("\n");
        TimeCard.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        TimeCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        jLabel4.setText("Current Date:");
        TimeCard.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        jLabel3.setText("18\\12]2025");
        TimeCard.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        ateendanceBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ateendanceBtn.setText("check in");
        ateendanceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ateendanceBtnActionPerformed(evt);
            }
        });
        TimeCard.add(ateendanceBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 190, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tap to record your attendance for today.");
        TimeCard.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 20));

        checkoutBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        checkoutBtn.setText("Check out");
        checkoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutBtnActionPerformed(evt);
            }
        });
        TimeCard.add(checkoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 200, 40));

        jPanel2.add(TimeCard);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "LeaveRequest", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Emoji", 1, 14))); // NOI18N
        jPanel5.setAlignmentX(0.0F);
        jPanel5.setAlignmentY(0.0F);
        jPanel5.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel5.setMinimumSize(new java.awt.Dimension(429, 100));
        jPanel5.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel5ComponentShown(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        submitLeave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        submitLeave.setText("Submit");
        submitLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitLeaveActionPerformed(evt);
            }
        });
        jPanel5.add(submitLeave, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        jLabel6.setText("Leave Type:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 20));

        leaveType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Annual", "Sick", "Emergency" }));
        jPanel5.add(leaveType, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 130, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        jLabel7.setText("Reason:");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 50, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        jLabel8.setText("Start Date:");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 20));

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        jLabel9.setText("End Date:");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 20));

        jScrollPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setAlignmentX(0.0F);
        jScrollPane1.setAlignmentY(0.0F);

        leaveReason.setColumns(20);
        leaveReason.setRows(5);
        jScrollPane1.setViewportView(leaveReason);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 16, 140, 80));
        jPanel5.add(StartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 120, 20));

        EndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EndDateActionPerformed(evt);
            }
        });
        jPanel5.add(EndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 120, 20));

        jPanel2.add(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "MissionRequest", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI Emoji", 1, 14))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        jLabel10.setText(" Priority:");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 50, 20));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Low", "Medium", "High" }));
        jPanel6.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 20, 140, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        jLabel11.setText("Description:");
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 16, -1, 80));

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("Send Mission Request");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 180, 30));

        jPanel2.add(jPanel6);

        jTabbedPane1.addTab("Submit Requests", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStatusButtonActionPerformed

        int selectedRow = tasksTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a task row to update its status.",
                    "Selection Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object taskCodeObject = tasksTable.getValueAt(selectedRow, 0);

        if (taskCodeObject == null || taskCodeObject.toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "You selected an empty row. Please select a valid task.",
                    "Selection Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String codeToUpdate = taskCodeObject.toString();
        String newPhase = (String) phaseComboBox.getSelectedItem();

        try {
            List<Task> tasks = taskManager.getAllTasks();
            Task taskToUpdate = null;

            for (Task t : tasks) {
                if (t.getCode().equals(codeToUpdate)) {
                    taskToUpdate = t;
                    break;
                }
            }

            if (taskToUpdate == null) {
                JOptionPane.showMessageDialog(this,
                        "Task not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            taskToUpdate.setPhase(newPhase);

            taskManager.deleteTask(codeToUpdate);
            taskManager.addTask(taskToUpdate);

            loadTasks();

            JOptionPane.showMessageDialog(this,
                    "Task phase updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error updating task phase: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_updateStatusButtonActionPerformed

    private void jPanel5ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel5ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel5ComponentShown

    // Submit mission request
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String description = jTextArea2.getText();

        if (description.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a mission description.");
            return;
        }

        try {
            String priority = jComboBox3.getSelectedItem().toString();
            Mission mission = new Mission(currentEmployeeUsername, description, priority);
            missionManager.addMission(mission);

            JOptionPane.showMessageDialog(this, "Mission Request submitted successfully!");
            jTextArea2.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error submitting mission request: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    // Check in attendance
    private void ateendanceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ateendanceBtnActionPerformed
        try {
            TimecardManager tcm = new TimecardManager(new FileManager());
            tcm.checkIn(currentEmployeeUsername);
            JOptionPane.showMessageDialog(this, "Checked in successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error in check in");
        }

    }//GEN-LAST:event_ateendanceBtnActionPerformed

    // Check out attendance
    private void checkoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutBtnActionPerformed
        // TODO add your handling code here:
        try {
            TimecardManager tcm = new TimecardManager(new FileManager());
            tcm.checkOut(currentEmployeeUsername);
            JOptionPane.showMessageDialog(this, "Checked out successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error in check out");
        }
    }//GEN-LAST:event_checkoutBtnActionPerformed

    // Submit leave request
    private void submitLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitLeaveActionPerformed
        // TODO add your handling code here:
        if (leaveManager == null) {
            JOptionPane.showMessageDialog(this, "LeaveManager is not initialized!");
            return;
        }

        try {
            String type = (String) leaveType.getSelectedItem();
            String reason = leaveReason.getText();
            String start = StartDate.getText();
            String end = EndDate.getText();

            if (reason.isEmpty() || start.isEmpty() || end.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            if (currentLeave == null) {
                currentLeave = new Leave(currentEmployeeUsername, type);
            }

            currentLeave.setReason(reason);
            currentLeave.setStartDate(start);
            currentLeave.setEndDate(end);

            leaveManager.addLeave(currentLeave);

            JOptionPane.showMessageDialog(this, "Leave request submitted successfully!");

            leaveReason.setText("");
            StartDate.setText("");
            EndDate.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error submitting leave: " + ex.getMessage());
        }
    }//GEN-LAST:event_submitLeaveActionPerformed

    private void EndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EndDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EndDateActionPerformed

    // Main
    public static void main(String args[]) {
        try {
            FileManager fm = new FileManager();
            LeaveManager lm = new LeaveManager(fm);
            TaskManager tm = new TaskManager(fm);
            MissionManager mm = new MissionManager(fm);

            String username = "ahmed";
            EmployeeFrame frame = new EmployeeFrame(tm, username, lm, mm);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField EndDate;
    private javax.swing.JTextField StartDate;
    private javax.swing.JPanel TimeCard;
    private javax.swing.JButton ateendanceBtn;
    private javax.swing.JButton checkoutBtn;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea leaveReason;
    private javax.swing.JComboBox<String> leaveType;
    private javax.swing.JComboBox<String> phaseComboBox;
    private javax.swing.JButton submitLeave;
    private javax.swing.JTable tasksTable;
    private javax.swing.JButton updateStatusButton;
    // End of variables declaration//GEN-END:variables
}
