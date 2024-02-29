package com.example.practic.repository;


import com.example.practic.domain.Quiz;
import com.example.practic.exception.RepositoryException;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class RepoQuizDB extends Repository<Quiz>{
    public RepoQuizDB(){
        openConnection();
        createSchema();
        initTables();
        loadData();
    }

    private void loadData() {
        ArrayList<Quiz> quizzes  = this.getAll();
        for(Quiz p : quizzes) {
            try {
                super.addEntity(p);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static final String JDBC_URL = "jdbc:sqlite:Intrebari.db";//se salveaza o baza de date cu numele dat

    private Connection conn = null;

    private void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Quiz(id int, text varchar(20), raspunsA varchar(20), raspunsB varchar(20), raspunsC varchar(20), raspunsCorect varchar(20), punctaj int);");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    void initTables() {
//        final String[] Pacienti = new String[]{
//                "1 Aluas Alin 19",
//        };
//
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Pacient VALUES (?, ?, ?,?)")) {
//                for (String P : Pacienti) {
//                    String[] tokens = P.split(" ");
//                    statement.setInt(1, Integer.parseInt(tokens[0]));
//                    statement.setString(2, tokens[1]);
//                    statement.setString(3, tokens[2]);
//                    statement.setInt(4, Integer.parseInt(tokens[3]));
//                    statement.executeUpdate();
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void addEntity(Quiz quiz) throws RepositoryException {
        try {
            super.addEntity(quiz);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Quiz VALUES (?, ?, ?,?,?,?,?)")) {
                statement.setInt(1, quiz.getId());
                statement.setString(2,quiz.getText());
                statement.setString(3, quiz.getRaspunsA());
                statement.setString(4, quiz.getRaspunsB());
                statement.setString(5, quiz.getRaspunsC());
                statement.setString(6, quiz.getRaspunsCorect());
                statement.setInt(7, quiz.getPunctaj());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            super.deleteById(id);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Quiz WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Quiz> getAll() {
        ArrayList<Quiz> pacienti = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Quiz"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Quiz p = new Quiz(rs.getInt("id"),rs.getString("text"),rs.getString("raspunsA"),rs.getString("raspunsB"),rs.getString("raspunsC"),rs.getString("raspunsCorect"),rs.getInt("punctaj"));
                    pacienti.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pacienti;
    }

    @Override
    public void update(Quiz pacient) {
        try {
            super.update(pacient);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement updatePacient = conn.prepareStatement("UPDATE Quiz SET text=?,raspunsA= ?, raspunsB=?,raspunsC=?,raspunsCorect=? punctaj=? WHERE id = ?")){
                updatePacient.setString(2, pacient.getText());
                updatePacient.setString(3, pacient.getRaspunsA());
                updatePacient.setString(4, pacient.getRaspunsB());
                updatePacient.setString(5, pacient.getRaspunsC());
                updatePacient.setString(6, pacient.getRaspunsCorect());
                updatePacient.setInt(7, pacient.getPunctaj());
                updatePacient.executeUpdate();

                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
