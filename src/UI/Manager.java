package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager extends JFrame {

    private JPanel mainPanel;
    private JButton btnRegisterNewUser;
    private JButton btnCashier;
    private JButton btnMLogout;

    public Manager() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1380, 720);
        setTitle("Manager");
        setVisible(true);

        btnRegisterNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterUser registerUser = new RegisterUser();
                registerUser.setVisible(true);
                Manager.this.setVisible(false);
            }
        });

        btnCashier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemUsers systemUsers = new SystemUsers();
                systemUsers.setVisible(true);
                Manager.this.setVisible(false);
            }
        });
        btnMLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                Manager.this.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
    }
}
