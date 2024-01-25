package UI;

import Code.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame{
    private JPanel mainPanel;
    private JButton btnLogin;
    private JTextField txtLEmail;
    private JPasswordField pwdLPassword;

    User user = new User();

    public Login() {
        // Application GUI settings
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1380, 720);
        setLocationByPlatform(true);
        setTitle("City Bookshop Logging");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = true;

                // Check user input fields are empty
                if (txtLEmail.getText().toString().isEmpty() ||
                        pwdLPassword.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Text Fields Shouldn't Be Blank",
                            "City Bookshop", JOptionPane.ERROR_MESSAGE);
                    valid = false;
                }

                // Validation
                if (valid) {

                    user.setEmail(txtLEmail.getText().trim());
                    user.setPassword(pwdLPassword.getText().trim());
                    boolean userValid = user.login();

                    if (userValid) {
                        JOptionPane.showMessageDialog(rootPane,
                                "Hey, Welcome Back " + user.getUserType());
                        String userType = user.getUserType();

                        if(userType.equals("Manager")) {
                            Manager manager = new Manager();
                            manager.setVisible(true);
                            Login.this.setVisible(false);
                        }
                        else if (userType.equals("Cashier")) {
                            SystemUsers systemUsers = new SystemUsers();
                            systemUsers.setVisible(true);
                            Login.this.setVisible(false);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(rootPane,
                                "Inserted details are not valid, " +
                                "please try again with a valid email address or password",
                                "City Bookshop", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

}
