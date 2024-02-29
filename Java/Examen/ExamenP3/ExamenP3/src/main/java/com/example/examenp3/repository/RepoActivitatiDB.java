package com.example.examenp3.repository;

import com.example.examenp3.domain.Activitate;
import com.example.examenp3.exception.RepositoryException;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RepoActivitatiDB extends Repository<Activitate>{
    public RepoActivitatiDB(){
        openConnection();
        createSchema();
        initTables();
        loadData();
    }

    private void loadData() {
        ArrayList<Activitate> pacients = this.getAll();
        for(Activitate p : pacients) {
            try {
                super.addEntity(p);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static final String JDBC_URL = "jdbc:sqlite:Activitati.db";//se salveaza o baza de date cu numele dat

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
    //yyyy-10-10
    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Pacient(id int, data varchar(20),nrpasi int, descriere varchar(20), durata int);");
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
    public void addEntity(Activitate pacient) throws RepositoryException {
        try {
            super.addEntity(pacient);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Pacient VALUES (?, ?, ?,?,?)")) {
                statement.setInt(1, pacient.getId());
                statement.setString(2, pacient.getData().toString());
                statement.setInt(3, pacient.getNrPasi());
                statement.setString(4, pacient.getDescriere());
                statement.setInt(5, pacient.getDurata());
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
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Pacient WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Activitate> getAll() {
        ArrayList<Activitate> pacienti = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Pacient"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Activitate p = new Activitate(rs.getInt("id"), LocalDate.parse(rs.getString("data")),rs.getInt("nrpasi"),rs.getString("descriere"),rs.getInt("durata"));
                    pacienti.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return pacienti;
    }

    @Override
    public void update(Activitate pacient) {
        try {
            super.update(pacient);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement updatePacient = conn.prepareStatement("UPDATE Pacient SET data = ?, nrpasi = ?, descriere = ?, durata = ? WHERE  = ?")){
                updatePacient.setInt(1, pacient.getId());
                updatePacient.setString(2, pacient.getData().toString());
                updatePacient.setInt(3, pacient.getNrPasi());
                updatePacient.setString(4, pacient.getDescriere());
                updatePacient.setInt(5, pacient.getDurata());
                updatePacient.executeUpdate();

                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
