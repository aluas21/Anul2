package com.example.demo.repository;

import com.example.demo.domain.Pacient;
import com.example.demo.domain.Programare;
import com.example.demo.exception.RepositoryException;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RepoProgramariDB extends Repository<Programare>{

    IRepository<Pacient> repoPacienti;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("H:mm");
    private static final String JDBC_URL = "jdbc:sqlite:Clinica.db";//se salveaza o baza de date cu numele dat

    public RepoProgramariDB(IRepository<Pacient> repoPacienti){
        this.repoPacienti = repoPacienti;
        openConnection();
        createSchema();
        initTables();
        loadData();

    }

    private void loadData() {
        ArrayList<Programare> programari = this.getAll();
        for(Programare p : programari) {
            try {
                super.addEntity(p);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
    }

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
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Programari(id int, idPacient int, date varchar(20), time varchar(20), motiv varchar(40));");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    void initTables() {
//        final String[] Programari = new String[]{
//                "1 1 2022/11/21 21:30 racit",
//        };
//
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Programari VALUES (?, ?, ?,?,?)")) {
//                for (String P : Programari) {
//                    String[] tokens = P.split(" ");
//                    statement.setInt(1, Integer.parseInt(tokens[0]));
//                    statement.setInt(2, Integer.parseInt(tokens[1]));
//                    statement.setString(3, tokens[2]);
//                    statement.setString(4, tokens[3]);
//                    statement.setString(5, tokens[4]);
//                    statement.executeUpdate();
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void addEntity(Programare programare) {
        try {
            super.addEntity(programare);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Programari VALUES (?, ?, ?,?,?)")) {
                statement.setInt(1, programare.getId());
                statement.setInt(2, programare.getPacient().getId());
                statement.setString(3, formatter.format(programare.getDate()));
                statement.setString(4, formatter2.format(programare.getTime()));
                statement.setString(5, programare.getMotiv());
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
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Programari WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Programare> getAll() {
        ArrayList<Programare> programare = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Programari"); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Pacient pacient = new Pacient(rs.getInt("idPacient"));
                    pacient = repoPacienti.getById(pacient.getId());
                    Programare p = new Programare(rs.getInt("id"),
                            pacient,
                            LocalDate.parse(rs.getString("date"),formatter),
                            LocalTime.parse(rs.getString("time"),formatter2),
                            rs.getString("motiv"));
                    programare.add(p);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return programare;
    }

    @Override
    public void update(Programare programare) {
        try {
            super.update(programare);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement updateProgramare = conn.prepareStatement("UPDATE Programari SET id = ?, idPacient = ?, date = ?, time = ?, motiv = ? WHERE id = ?")){
                updateProgramare.setInt(1, programare.getId());
                updateProgramare.setInt(2, programare.getPacient().getId());
                updateProgramare.setString(3, formatter.format(programare.getDate()));
                updateProgramare.setString(4, formatter2.format(programare.getTime()));
                updateProgramare.setString(5, programare.getMotiv());
                updateProgramare.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



}
