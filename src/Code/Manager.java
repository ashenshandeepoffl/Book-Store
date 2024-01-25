package Code;

import java.sql.ResultSet;

public class Manager {

    Database database = new Database();

    private String userEmail;
    private String userName;
    private String userType;
    private String password;

    public Manager() {
    }

    public Manager(String userEmail, String userName, String userType, String password) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userType = userType;
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int registerNewUser() {
        int results = 0;

        String SQL = "INSERT INTO UserInfo (Email, Name, UserType, Password)" +
                " VALUES('" + getUserEmail() + "','"
                + getUserName() +"','" + getUserType() + "','" + getPassword()
                + "')";

        try {
            results = database.executeMethod(SQL);

        }
        catch (Exception e) {
            System.out.println("Error inserting data " + e);

        }
        return results;
    }

    public String[] getAllUserTypes() {
        String[] userTypes = new String[10];
        try {
            String SQL = "SELECT DISTINCT UserType FROM UserType;";

            ResultSet resultSet = database.SelectValue(SQL);
            int index = 0;

            while(resultSet.next()) {
                userTypes[index] = resultSet.getString("UserType"); // [0] = Manager, [1] = Cashier
                index++;
            };
        }
        catch (Exception exception) {
            System.out.println("Error getting User Type " + exception);
        }
        return userTypes;
    }

}
