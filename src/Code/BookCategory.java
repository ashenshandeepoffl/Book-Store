package Code;

import java.sql.ResultSet;

public class BookCategory{

    Database database = new Database();

    private String categoryID;
    private String categoryName;

    public BookCategory() {
    }

    public BookCategory(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // Category ID Auto Generate
    public String autoID() {
        String IDS = null;
        try {
            String SQl = "SELECT CategoryID FROM BookCategory";
            int noRows = 0;
            ResultSet resultSet = database.SelectValue(SQl);
            String CID = null;

            while (resultSet.next()) {
                CID = resultSet.getString("CategoryID");
                System.out.println(CID);
            }

            assert CID != null;
            String x = CID.substring(1);
            int ID = Integer.parseInt(x);

            if (ID > 0 && ID < 9) {
                ID = ID + 1;
                IDS = "C00" + ID;
            }
            else if (ID >= 9 && ID <99) {
                ID = ID + 1;
                IDS = "C0" + ID;
            }
            else if (ID >= 99) {
                ID = ID + 1;
                IDS = "C" + ID;
            }

        }
        catch(Exception e){
            System.out.println("Error Auto ID " + e);
        }
        return IDS;
    }

    public int insertBookCategory() {
        int noRows = 0;
        String SQL = "INSERT INTO BookCategory VALUES('" + getCategoryID() + "','" + getCategoryName() + "')";

        try{
            noRows = database.executeMethod(SQL);
        }
        catch(Exception ex){
            System.out.println("Error inserting data"+ex);
        }
        return noRows;

    }

    public String[] getBookCategoryName() {
        String[] DepartmentName = new String[50];
        try {
            String SQL = "SELECT CategoryName from BookCategory";

            ResultSet resultSet = database.SelectValue(SQL);
            int index = 0;

            while(resultSet.next()) {
                DepartmentName[index] = resultSet.getString("CategoryName"); // [0] = Novel, [1] = Science Friction
                index++;
            };
        }
        catch (Exception exception) {
            System.out.println("Error getting Book Category Name " + exception);
        }
        return DepartmentName;
    }
}
