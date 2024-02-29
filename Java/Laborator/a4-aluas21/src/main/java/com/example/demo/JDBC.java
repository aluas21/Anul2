package com.example.demo;

import com.example.demo.domain.Pacient;
import  org.sqlite.SQLiteDataSource;

import  java.sql.*;
import java.util.ArrayList;

public class JDBC {

    private static final String JDBC_URL =
            "jdbc:sqlite:Pacienti.db";//se creeaza dupa numele pe care il punem aici

    private Connection conn = null;

    /**
     * Gets a connection to the database.
     * If the underlying connection is closed, it creates a new connection. Otherwise, the current instance is returned.
     */
    private void openConnection() {
        try {
            // with DriverManager
//            if (conn == null || conn.isClosed())
//                conn = DriverManager.getConnection(JDBC_URL);

            // with DataSource
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the underlying connection to the in-memory SQLite instance.
     */
    private void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the sample schema for the database.
     */
    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Pacient(id int, name varchar(400), surname varchar(200), age int);");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    /**
     * Adds some entries in the table books;
     */
    void initTables() {
        final String[] Pacienti = new String[]{
                "1 Aluas Alin 19",
                "2 Bucila Mihai 20",
                "3 Tira Andra 20"
        };

        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Pacient VALUES (?, ?, ?,?)")) {
                for (String P : Pacienti) {
                    String[] tokens = P.split(" ");
                    statement.setInt(1, Integer.parseInt(tokens[0]));
                    statement.setString(2, tokens[1]);
                    statement.setString(3, tokens[2]);
                    statement.setInt(4, Integer.parseInt(tokens[3]));
                    statement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add the given book to the database.
     */
    void addPacient(Pacient pacient) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Pacient VALUES (?, ?, ?,?)")) {
                statement.setInt(1, pacient.getId());
                statement.setString(2, pacient.getName());
                statement.setString(3, pacient.getSurname());
                statement.setInt(4, pacient.getAge());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove the book with the given title from table books.
     *
     * @param id
     */
    void removePacoentById(int id) {
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Pacient WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all book from table books in the database.
     *
     * @return: an ArrayList with Book objects.
     */
    ArrayList<Pacient> getAll() {
        ArrayList<Pacient> pacienti = new ArrayList<>();

        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Pacient"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Pacient p = new Pacient(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),rs.getInt("age"));
                    pacienti.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pacienti;
    }

    void updateSalesTransaction() {
//        try {
//            conn.setAutoCommit(false);
//
//            try (PreparedStatement updateSales = conn.prepareStatement("UPDATE sales SET number_of_books = ? WHERE title = ?");
//                 PreparedStatement updateTotal = conn.prepareStatement("UPDATE sales SET total = total + ? WHERE title = ?")) {
//                updateSales.setInt(1, 20);
//                updateSales.setString(2, "Open");
//                updateSales.executeUpdate();
//
//                int bookPrice = 55;
//                updateTotal.setInt(1, 20 * bookPrice);
//                updateTotal.setString(2, "Open");
//                updateTotal.executeUpdate();
//
//                conn.commit();
//                conn.setAutoCommit(true);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        JDBC db_example = new JDBC();
        db_example.openConnection();
        db_example.createSchema();
        db_example.initTables();

        Pacient pacient = new Pacient(4,"Alex","Pop",12);
        db_example.addPacient(pacient);

        db_example.removePacoentById(1);

        db_example.updateSalesTransaction();

        ArrayList<Pacient> booksList = db_example.getAll();
        for (Pacient pacient1 : booksList)
            System.out.println(pacient1);

        db_example.closeConnection();
    }
}
