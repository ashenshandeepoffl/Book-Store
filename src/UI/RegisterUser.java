package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Code.*;

public class RegisterUser extends JFrame {
    Code.Manager manager = new Code.Manager();
    private JPanel mainPanel;
    private JTextField txtFullName;
    private JTextField txtEmail;
    private JPasswordField pwdPassword;
    private JPasswordField pwdConfirmPassword;
    private JButton btnRegister;
    private JButton btnBack;
    private JComboBox cbUserType;

    public RegisterUser() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1380, 720);
        setTitle("Registration Panel");
        setVisible(true);

        String[] userTypes = manager.getAllUserTypes();
        cbUserType.setModel(new javax.swing.DefaultComboBoxModel<>(userTypes));

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager admin = new Manager();
                admin.setVisible(true);
                RegisterUser.this.setVisible(false);
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                if (txtEmail.getText().isEmpty() || txtFullName.getText().isEmpty() ||
                        cbUserType.getSelectedItem().toString().isEmpty() ||
                pwdPassword.getText().isEmpty() || pwdConfirmPassword.toString().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Text fields cannot be empty, please enter valid details.",
                            "Manager - Register New User", JOptionPane.ERROR_MESSAGE);
                    isValid = false;
                }

                boolean result = isEmailValid(txtEmail.getText());
                if (result == true){
                    if (!pwdPassword.getText().equals(pwdConfirmPassword.getText())) {
                        JOptionPane.showMessageDialog(null, "Password and Confirm Password are" +
                                        " not same, please use same password. ",
                                "Manager - Register New User", JOptionPane.ERROR_MESSAGE);
                        isValid = false;
                    }

                    if (pwdPassword.getText().length() >= 6) {
                        if (isValid) {
                            manager.setUserEmail(txtEmail.getText());
                            manager.setUserName(txtFullName.getText());
                            manager.setUserType(cbUserType.getSelectedItem().toString());
                            manager.setPassword(pwdPassword.getText().trim());
                            int noRows = manager.registerNewUser();
                            if (noRows > 0) {

                                txtEmail.setText("");
                                txtFullName.setText("");
                                pwdPassword.setText("");
                                pwdConfirmPassword.setText("");
                                JOptionPane.showMessageDialog(rootPane, "New " + cbUserType.getSelectedItem().toString() +", Successfully added");
                            }
                            else {
                                JOptionPane.showMessageDialog(rootPane, "Data is not valid",
                                        "Manager - Register New User", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(rootPane, "Password should contain more than 6 characters.",
                                "Manager - Register New User", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Please enter valid email address",
                            "Manager - Register New User", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static boolean isEmailValid(String email) {
        String includes = "^[a-zA-Z0-9_#$%&’*+/=?^.-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(includes);
        if (email == null)
            return false;
        return pattern.matcher(email).matches();
    }

    public static void main(String[] args) {
        RegisterUser registerUser = new RegisterUser();
    }
}
