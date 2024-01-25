package Code;

import java.sql.ResultSet;

public class User implements Authenticator{
    Database database = new Database();
    private String email, password, userType;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean login() {
        ResultSet resultSet = null;
        String SQL = "SELECT * FROM UserInfo WHERE Email = '" + getEmail() + "' AND Password = '" + getPassword() + "';";

        try {
            resultSet = database.SelectValue(SQL);

            if (resultSet.next()) {
                setUserType((resultSet.getString("UserType")));
            }

            else {
                return false;
            }
        }
        catch (Exception ex) {
            System.out.printf("Error Validating User" + ex);
        }
        return true;
    }
}
