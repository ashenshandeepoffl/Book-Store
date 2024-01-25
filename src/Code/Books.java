package Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static Code.Database.getConnection;

public class Books {

    Database database = new Database();

    private String bookId;
    private String bookCategory;
    private String bookName;
    private String authorName;
    private String publisher;
    private int year;
    private int price;
    private int quantity;

    public Books() {
    }

    public Books(String bookId, String bookCategory, String bookName, String authorName, String publisher, int year, int price, int quantity) {
        this.bookId = bookId;
        this.bookCategory = bookCategory;
        this.bookName = bookName;
        this.authorName = authorName;
        this.publisher = publisher;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    // Book ID Auto Generate
    public String autoID() {
        String IDS = null;
        try {
            String SQl = "SELECT BookID FROM Books";
            int noRows = 0;
            ResultSet resultSet = database.SelectValue(SQl);
            String BID = null;

            while (resultSet.next()) {
                BID = resultSet.getString("BookID");
                System.out.println(BID);
            }

            assert BID != null;
            String x = BID.substring(1);
            int ID = Integer.parseInt(x);

            if (ID > 0 && ID < 9) {
                ID = ID + 1;
                IDS = "B00" + ID;
            }
            else if (ID >= 9 && ID <99) {
                ID = ID + 1;
                IDS = "B0" + ID;
            }
            else if (ID >= 99) {
                ID = ID + 1;
                IDS = "B" + ID;
            }

        }
        catch(Exception e){
            System.out.println("Error Auto ID " + e);
        }
        return IDS;
    }

    public int addNewBook(){
        int noRows = 0;
        String SQL = "INSERT INTO Books VALUES ('" + getBookId() + "','" + getBookCategory() +
                "','" + getBookName() + "','" + getAuthorName() + "','" + getPublisher() + "','"
                + getYear() + "','" + getPrice() +  "','" + getQuantity() + "')";

        /*
        INSERT INTO Books VALUES ('B004', 'Action', 'ABC', 'ABC', 'ABC', '2001', '660')
         */

        try{
            noRows = database.executeMethod(SQL);
        }
        catch(Exception ex){
            System.out.println("Error inserting data " + ex);
        }
        return noRows;
    }

    public ResultSet searchAllBooks() {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try {
            String SQL = "Select * from Books";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception ex) {
            System.out.print("Error View All Books" + ex);

        }
        return resultSet;
    }

    public ResultSet searchBook() {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try {
            String SQL = "Select * from Books WHERE BookId = '" + getBookId() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception ex) {
            System.out.print("Error View All Books" + ex);

        }
        return resultSet;
    }

    public int updateBooks(){
        int noRows = 0;
        String SQL=" UPDATE Books SET BookCategory = '" + getBookCategory() +
                "', BookName ='" + getBookName() + "', Author = '" + getAuthorName() + "', Publisher ='" + getPublisher()
                + "', Year ='" + getYear() + "', Price ='" + getPrice() + "', Quantity ='" + getQuantity() + "'WHERE BookID='" + getBookId() + "'" ;

        /*

        UPDATE Books
        SET BookCategory = 'Action', BookName = 'ABC', Author = 'ABC', Publisher = 'ABc', Year = '2020', Price = '150', Quantity = '50'
        WHERE BookID = 'B003'

         */

        try{
            noRows = database.executeMethod(SQL);
        }
        catch(Exception ex){
            System.out.println("Error Updating data" + ex);
        }
        return noRows;
    }

    public int deleteBook() {
        int noRows = 0;
        String SQL = "DELETE FROM Books WHERE BookId = '" + getBookId() + "'";
        try {
            noRows = database.executeMethod(SQL);

        } catch (Exception ex) {
            System.out.print("Error Deleting Books" + ex);
        }
        return noRows;
    }

    public ResultSet giveSelectedResults() {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try {
            String SQL = "Select * from Books WHERE BookCategory = '" + getBookCategory() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception ex) {
            System.out.print("Error View All Books" + ex);

        }
        return resultSet;
    }

    public ResultSet getBooksUsingAuthors() {
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try {
            String SQL = "Select * from Books WHERE Author LIKE '"+ getAuthorName() + "%'";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception ex) {
            System.out.print("Error View All Books" + ex);

        }
        return resultSet;
    }

    public int updateQuantity(){
        int noRows = 0;
        String SQL="UPDATE Books SET Quantity = '" + getQuantity() + "' WHERE BookId='" + getBookId() + "'" ;
        try{
            noRows = database.executeMethod(SQL);
        }
        catch(Exception ex){
            System.out.println("Error Updating data " + ex);
        }
        return noRows;
    }
}
