package tms.gui;

import tms.*;
import javax.swing.*;
import java.util.*;

public class LoginFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LoginFrame.class.getName());

    private UserManager userManager;
    private ProjectManager projectManager;
    private LeaveManager leaveManager;

    public LoginFrame() {
        FileManager fm = new FileManager();
        this.projectManager = new ProjectManager(fm);

        this.userManager = new UserManager(fm);
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        useename = new javax.swing.JTextField();
        paswword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        loginbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setText("Login");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("username");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("password");

        loginbtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        loginbtn.setText("login");
        loginbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(paswword, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(useename, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(loginbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(useename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(paswword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(loginbtn)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed
        // TODO add your handling code here:
        String username = useename.getText();
        String password = new String(paswword.getPassword());

        try {
            User loggedUser = null;
            for (User u : userManager.getAllUsers()) {
                if (u.login(username, password)) {
                    loggedUser = u;
                    break;
                }
            }

            if (loggedUser != null) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                openDashboard(loggedUser);
            } else {
                JOptionPane.showMessageDialog(this, "Username or password incorrect");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading users file!");
        }

    }//GEN-LAST:event_loginbtnActionPerformed
    private void openDashboard(User loggedUser) {
        try {
            FileManager fm = new FileManager();
            ProjectManager pm = new ProjectManager(fm);
            TaskManager tm = new TaskManager(fm);
            EmployeeManager em = new EmployeeManager(fm);
            TimecardManager tcm = new TimecardManager(fm);
            LeaveManager lm = new LeaveManager(fm);
            MissionManager mm = new MissionManager(fm);

            if (loggedUser.getRole().equalsIgnoreCase("leader")) {
                LeaderDashboard leader = new LeaderDashboard(tm, em, pm, fm, tcm, lm, mm);
                leader.setVisible(true);
            } else if (loggedUser.getRole().equalsIgnoreCase("employee")) {
                EmployeeFrame empFrame = new EmployeeFrame(tm, loggedUser.getUsername(), lm, mm);
                empFrame.setVisible(true);
            } else if (loggedUser.getRole().equalsIgnoreCase("admin")) {
                AdminDashboard admin = new AdminDashboard(userManager, pm);
                admin.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Unknown role: " + loggedUser.getRole());
            }

            this.dispose();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error opening dashboard: " + e.getMessage());
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new LoginFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginbtn;
    private javax.swing.JPasswordField paswword;
    private javax.swing.JTextField useename;
    // End of variables declaration//GEN-END:variables
}
