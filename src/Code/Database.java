package Code;
import java.sql.*;
public class Database {
    public static Connection getConnection() {

        // Database connection details
        final String DBURL = "jdbc:mysql://localhost:3306/CityBookShop";
        final String username = "root";
        final String password = "As+s01galaxysa";

        Connection connection = null;

        // Database connecting
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, username, password);
            System.out.println("Connected Database successfully");

        }
        catch (Exception ex) {
            System.out.println("Error to connecting Database " + ex);
        }
        return connection;
    }

    //    Execute the query
    public int executeMethod(String SQL) throws Exception {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        return statement.executeUpdate(SQL);
    }

    public ResultSet SelectValue (String SQL) throws Exception {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        return preparedStatement.executeQuery();
    }

}
