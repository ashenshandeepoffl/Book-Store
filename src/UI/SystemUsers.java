package UI;

import Code.BookCategory;
import Code.Books;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SystemUsers extends JFrame{
    BookCategory bookCategory = new BookCategory();
    Books books = new Books();

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JLabel txtCategoryId;
    private JTextField txtCategoryName;
    private JButton btnAddCategory;
    private JButton btnLogout;
    private JLabel txtBookId;
    private JComboBox cbAddCategory;
    private JTextField txtBookName;
    private JTextField txtBookAuthor;
    private JTextField txtPublisher;
    private JTextField txtYear;
    private JTextField txtPrice;
    private JButton btnClear;
    private JButton btnSubmit;
    private JComboBox cbSearchCategory;
    private JTextField txtSearchAuthor;
    private JTable showTable;
    private JTextField txtSearchBox;
    private JButton btnSearch;
    private JTextField txtDUBookName;
    private JTextField txtDUAuthorsName;
    private JTextField txtDUPublisher;
    private JLabel lblCurrentCategory;
    private JTextField txtDUYear;
    private JTextField txtDUPrice;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JPanel homePanel;
    private JPanel CategoryPanel;
    private JPanel newBookPanel;
    private JPanel searchPanel;
    private JPanel dUPanel;
    private JTable showAllBooks;
    private JPanel billingSystem;
    private JTextField txtBillingBookID;
    private JLabel txtBillingPrice;
    private JTextField txtBillingQuantity;
    private JButton btnBillingAdd;
    private JButton btnBillingClear;
    private JButton btnBillingSearchBookID;
    private JTextField txtBillingTotalPrice;
    private JTextArea txtAreaPrintBill;
    private JComboBox cbDUCategory;
    private JTextField txtDUQuantity;
    private JTextField txtQuantity;
    private JLabel Quantity;

    // Global Variables
    int totalPrice = 0;
    int price = 0;
    int quantity = 0;
    int fullPrice= 0;
    int bookQuantity = 0;
    int updateBookQuantity = 0;
    String bookId = "";


    public SystemUsers() {
        // GUI Settings
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1380, 720);
        setTitle("Main Screen");
        setVisible(true);



        // Auto ID, Combo Box Details
        txtCategoryId.setText(bookCategory.autoID());
        txtBookId.setText(books.autoID());

        // ComboBox -> Add New Book
        String[] BookCategory = bookCategory.getBookCategoryName();
        cbAddCategory.setModel(new javax.swing.DefaultComboBoxModel<>(BookCategory));

        // ComboBox -> Search new Book
        String[] BookCategory2 = bookCategory.getBookCategoryName();
        cbSearchCategory.setModel(new javax.swing.DefaultComboBoxModel<>(BookCategory2));




        // Logout
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                SystemUsers.this.setVisible(false);
            }
        });

        cbAddCategory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] BookCategory = bookCategory.getBookCategoryName();
                cbAddCategory.setModel(new javax.swing.DefaultComboBoxModel<>(BookCategory));
            }
        });

        txtCategoryId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
        cbSearchCategory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] BookCategory2 = bookCategory.getBookCategoryName();
                cbSearchCategory.setModel(new javax.swing.DefaultComboBoxModel<>(BookCategory2));
            }
        });

        // Adding New Book Category Panel
        btnAddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = true;
                if (txtCategoryId.getText().isEmpty() || txtCategoryName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Text fields cannot be empty, please enter valid details.",
                            "City Bookshop", JOptionPane.ERROR_MESSAGE);
                    valid = false;
                }

                if (valid) {
                    bookCategory.setCategoryID(txtCategoryId.getText().trim());
                    bookCategory.setCategoryName(txtCategoryName.getText());

                    int noRows = bookCategory.insertBookCategory();

                    if (noRows > 0) {
                        txtCategoryName.setText("");
                        txtCategoryId.setText(bookCategory.autoID());
                        JOptionPane.showMessageDialog(rootPane, "New category added successfully");
                    }
                    else {
                        JOptionPane.showMessageDialog(rootPane, "Entered Details Are Not Valid, Please Try Again",
                                "City Bookshop", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Add new book Panel
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                if (txtBookId.getText().isEmpty() ||
                        cbAddCategory.getSelectedItem().toString().isEmpty() ||
                        txtBookName.toString().isEmpty() || txtBookAuthor.getText().isEmpty() ||
                        txtPublisher.getText().isEmpty() || txtYear.getText().isEmpty() || txtPrice.getText().isEmpty()
                        || txtQuantity.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Text fields cannot be empty, please enter valid details.",
                            "City Bookshop", JOptionPane.ERROR_MESSAGE);
                    isValid = false;
                }

                if (isValid) {
                    books.setBookId(txtBookId.getText());
                    books.setBookCategory(cbAddCategory.getSelectedItem().toString());
                    books.setBookName(txtBookName.getText());
                    books.setAuthorName(txtBookAuthor.getText());
                    books.setPublisher(txtPublisher.getText());
                    books.setYear(Integer.parseInt(txtYear.getText()));
                    books.setPrice(Integer.parseInt(txtPrice.getText()));
                    books.setQuantity(Integer.parseInt(txtQuantity.getText()));

                    int noRows = books.addNewBook();

                    if (noRows > 0) {
                        txtBookName.setText("");
                        txtBookAuthor.setText("");
                        txtPublisher.setText("");
                        txtYear.setText("");
                        txtPrice.setText("");
                        txtQuantity.setText("");
                        txtBookId.setText(books.autoID());
                        JOptionPane.showMessageDialog(rootPane, "Book Details Added Successfully");
                    }

                    else {
                        JOptionPane.showMessageDialog(rootPane, "Entered Details Are Not Valid, Please Try Again",
                                "City Bookshop", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Clear Text Fields
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBookName.setText("");
                txtBookAuthor.setText("");
                txtPublisher.setText("");
                txtYear.setText("");
                txtPrice.setText("");
                txtQuantity.setText("");
            }
        });

        // Search Panel
        searchPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                showBooksTable();
                cbSearchCategory.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        String[] BookCategory2 = bookCategory.getBookCategoryName();
                        cbSearchCategory.setModel(new javax.swing.DefaultComboBoxModel<>(BookCategory2));
                    }
                });

                txtSearchAuthor.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        super.keyTyped(e);
                        getBooksUsingAuthors();
                    }
                });

                btnSearch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        books.setBookId(txtSearchBox.getText());
                        // Data sending to the DATABASE
                        try {
                            ResultSet resultSet = books.searchBook();
                            if (resultSet.next()) {
                                lblCurrentCategory.setText(resultSet.getString("BookCategory"));
                                txtDUBookName.setText(resultSet.getString("BookName"));
                                txtDUAuthorsName.setText(resultSet.getString("Author"));
                                txtDUPublisher.setText(resultSet.getString("Publisher"));
                                txtDUYear.setText(resultSet.getString("Year"));
                                txtDUPrice.setText(resultSet.getString("Price"));
                                txtDUQuantity.setText(resultSet.getString("Quantity"));

                                JOptionPane.showMessageDialog(rootPane, "Book Found");
                            }

                            else {
                                JOptionPane.showMessageDialog(rootPane, "Book Not Found");
                            }
                        }
                        catch (Exception ex){
                            JOptionPane.showMessageDialog(rootPane, "Error inserting data " + ex,
                                    "City Bookshop", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });


        // Delete / Update Panel
        cbDUCategory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] BookCategory = bookCategory.getBookCategoryName();
                cbDUCategory.setModel(new javax.swing.DefaultComboBoxModel<>(BookCategory));
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                if (txtDUBookName.toString().isEmpty() || txtDUAuthorsName.getText().isEmpty() ||
                        txtDUPublisher.getText().isEmpty() || txtDUYear.getText().isEmpty() ||
                        txtDUPrice.getText().isEmpty() || txtDUQuantity.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Text fields cannot be empty, please enter valid details.",
                            "City Bookshop", JOptionPane.ERROR_MESSAGE);
                    isValid = false;
                }

                if (isValid) {
                    books.setBookCategory(cbDUCategory.getSelectedItem().toString());
                    books.setBookName(txtDUBookName.getText());
                    books.setAuthorName(txtDUAuthorsName.getText());
                    books.setPublisher(txtDUPublisher.getText());
                    books.setYear(Integer.parseInt(txtDUYear.getText()));
                    books.setPrice(Integer.parseInt(txtDUPrice.getText()));
                    books.setQuantity(Integer.parseInt(txtDUQuantity.getText()));

                    int noRows = books.updateBooks();

                    if (noRows > 0) {
                        clearDU();
                        JOptionPane.showMessageDialog(rootPane, "Book Details Updated!");
                    }

                    else {
                        JOptionPane.showMessageDialog(rootPane, "Book Details are not valid",
                                "City Bookshop", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                if (txtBookId.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Book ID cannot be Empty",
                            "City Bookshop", JOptionPane.ERROR_MESSAGE);
                    isValid = false;
                }
                if (isValid) {
                    int noRows = books.deleteBook();
                    clearDU();


                    if (noRows > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Book Details Deleted!");
                    }
                    else {
                        JOptionPane.showMessageDialog(rootPane, "Book Details are not valid",
                                "City Bookshop", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        cbSearchCategory.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    giveSelectedResults();
                }
            }
        });

        // Billing Panel
        btnBillingSearchBookID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookId = txtBillingBookID.getText();
                books.setBookId(bookId);
                // Data sending to the DATABASE
                try {
                    ResultSet resultSet = books.searchBook();
                    if (resultSet.next()) {
                        price = resultSet.getInt(7);
                        bookQuantity = resultSet.getInt(8);
                        String.valueOf(price);
                        txtBillingPrice.setText(String.valueOf(price));
                    }
                    else {
                        JOptionPane.showMessageDialog(rootPane, "Inserted Book ID is not available");
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(rootPane, "Data inserting error: " + ex,
                            "City Bookshop", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        List<String> lineItems = new ArrayList<>();

        btnBillingAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantity = Integer.parseInt(txtBillingQuantity.getText());
                fullPrice = quantity * price;
                totalPrice += fullPrice;
                txtBillingTotalPrice.setText(String.valueOf(totalPrice));
                updateBookQuantity = bookQuantity - quantity;
                books.setQuantity(updateBookQuantity);
                int noRows = books.updateQuantity();


                lineItems.add(bookId + "          " + price + "       " + quantity + "\n");
                StringBuilder fullList = new StringBuilder();
                for (String item : lineItems) {
                    fullList.append(item);
                }

                txtAreaPrintBill.setText(
                        "      **** City Bookshop **** \n" + " " + "\n" +
                                "Book ID     " +
                                "Price   " +
                                "Quantity       "
                                + "\n"
                                + fullList + "\n" + "  "
                );
                txtAreaPrintBill.setText(txtAreaPrintBill.getText() + " \n -----------------------------------------");
                txtAreaPrintBill.setText(txtAreaPrintBill.getText() + "\n" + "Total Price                    "
                        + totalPrice);
                txtAreaPrintBill.setText(txtAreaPrintBill.getText() + "\n ------------------------------------------\n" +
                        "                Thank You              ");

                if (noRows > 0) {
                    // Empty text values
                    txtBillingBookID.setText(null);
                    txtBillingQuantity.setText(null);
                    txtBillingPrice.setText(null);
                    JOptionPane.showMessageDialog(rootPane, "Book Quantity Updated!");
                }
            }
        });

    }

    // Show all books in one single table method
    private void cellRenderer(JTable x) {
        TableColumnModel columnModel = x.getColumnModel();
        columnModel.getColumn(4).setMinWidth(250);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        columnModel.getColumn(0).setCellRenderer(cellRenderer);
        columnModel.getColumn(1).setCellRenderer(cellRenderer);
        columnModel.getColumn(2).setCellRenderer(cellRenderer);
        columnModel.getColumn(3).setCellRenderer(cellRenderer);
        columnModel.getColumn(4).setCellRenderer(cellRenderer);
        columnModel.getColumn(5).setCellRenderer(cellRenderer);
        columnModel.getColumn(6).setCellRenderer(cellRenderer);
        columnModel.getColumn(7).setCellRenderer(cellRenderer);
    }

    private void showBooksTable() {
        DefaultTableModel model = new DefaultTableModel(
                null,
                new String[]{"Book ID", "Category", "Name", "Author", "Publisher", "Year", "Price", "Quantity"});
        try{
            ResultSet resultSet = books.searchAllBooks();

            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8)
                };
                model.addRow(row);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        showAllBooks.setModel(model);
        cellRenderer(showAllBooks);
    }

    private void giveSelectedResults() {
        DefaultTableModel model = new DefaultTableModel(null,
                new String[]{"Book ID", "Category", "Name", "Author", "Publisher", "Year", "Price", "Quantity"});

        books.setBookCategory(cbSearchCategory.getSelectedItem().toString());
        // Data sending to the DATABASE
        try {
            ResultSet resultSet = books.giveSelectedResults();
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8)
                };
                model.addRow(row);
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Error Filtering Data " + ex,
                    "Filter Results", JOptionPane.ERROR_MESSAGE);
        }
        showTable.setModel(model);
        cellRenderer(showTable);
    }

    private void getBooksUsingAuthors() {
        DefaultTableModel model = new DefaultTableModel(null,
                new String[]{"Book ID", "Category", "Name", "Author", "Publisher", "Year", "Price", "Quantity"});

        books.setAuthorName(txtSearchAuthor.getText());
        // Data sending to the DATABASE
        try {
            ResultSet resultSet = books.getBooksUsingAuthors();
            while (resultSet.next()) {
                Object[] row = {
                        resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8)
                };
                model.addRow(row);
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Error Filtering Data " + ex,
                    "Filter Results", JOptionPane.ERROR_MESSAGE);
        }

        showTable.setModel(model);
        cellRenderer(showTable);
    }

    private void clearDU() {
        txtSearchBox.setText("");
        lblCurrentCategory.setText("");
        txtDUBookName.setText("");
        txtDUAuthorsName.setText("");
        txtDUPublisher.setText("");
        txtDUYear.setText("");
        txtDUPrice.setText("");
        txtDUQuantity.setText("");
    }

    public static void main(String[] args) {
        SystemUsers systemUsers = new SystemUsers();
    }


}


