package tms;

import tms.gui.LoginFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LoginFrame login = new LoginFrame();
            login.setVisible(true);
        });
    }
}
