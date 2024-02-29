//package com.example.template.repository;
//
//import com.example.template.domain.Pacient;
//import com.example.template.exception.RepositoryException;
//import org.sqlite.SQLiteDataSource;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class RepoPacientiDB extends Repository<Pacient>{
//    public RepoPacientiDB(){
//        openConnection();
//        createSchema();
//        initTables();
//        loadData();
//    }
//
//    private void loadData() {
//        ArrayList<Pacient> pacients = this.getAll();
//        for(Pacient p : pacients) {
//            try {
//                super.addEntity(p);
//            } catch (RepositoryException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    private static final String JDBC_URL = "jdbc:sqlite:Clinica.db";//se salveaza o baza de date cu numele dat
//
//    private Connection conn = null;
//
//    private void openConnection() {
//        try {
//            SQLiteDataSource ds = new SQLiteDataSource();
//            ds.setUrl(JDBC_URL);
//            if (conn == null || conn.isClosed())
//                conn = ds.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void closeConnection() {
//        try {
//            if (conn != null)
//                conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    void createSchema() {
//        try {
//            try (final Statement stmt = conn.createStatement()) {
//                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Pacient(id int, name varchar(400), surname varchar(200), age int);");
//            }
//        } catch (SQLException e) {
//            System.err.println("[ERROR] createSchema : " + e.getMessage());
//        }
//    }
//
//    void initTables() {
////        final String[] Pacienti = new String[]{
////                "1 Aluas Alin 19",
////        };
////
////        try {
////            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Pacient VALUES (?, ?, ?,?)")) {
////                for (String P : Pacienti) {
////                    String[] tokens = P.split(" ");
////                    statement.setInt(1, Integer.parseInt(tokens[0]));
////                    statement.setString(2, tokens[1]);
////                    statement.setString(3, tokens[2]);
////                    statement.setInt(4, Integer.parseInt(tokens[3]));
////                    statement.executeUpdate();
////                }
////            }
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//    }
//
//    @Override
//    public void addEntity(Pacient pacient) throws RepositoryException {
//        try {
//            super.addEntity(pacient);
//        } catch (RepositoryException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Pacient VALUES (?, ?, ?,?)")) {
//                statement.setInt(1, pacient.getId());
//                statement.setString(2, pacient.getName());
//                statement.setString(3, pacient.getSurname());
//                statement.setInt(4, pacient.getAge());
//                statement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteById(int id) {
//        try {
//            super.deleteById(id);
//        } catch (RepositoryException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Pacient WHERE id=?")) {
//                statement.setInt(1, id);
//                statement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public ArrayList<Pacient> getAll() {
//        ArrayList<Pacient> pacienti = new ArrayList<>();
//        try {
//            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Pacient"); ResultSet rs = statement.executeQuery();) {
//                while (rs.next()) {
//                    Pacient p = new Pacient(rs.getInt("id"),rs.getString("name"),rs.getString("surname"),rs.getInt("age"));
//                    pacienti.add(p);
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return pacienti;
//    }
//
//    @Override
//    public void update(Pacient pacient) {
//        try {
//            super.update(pacient);
//        } catch (RepositoryException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            conn.setAutoCommit(false);
//
//            try (PreparedStatement updatePacient = conn.prepareStatement("UPDATE sales SET name = ?, surname = ?, age = ? WHERE id = ?")){
//                updatePacient.setString(2, pacient.getName());
//                updatePacient.setString(3, pacient.getSurname());
//                updatePacient.setInt(4,pacient.getAge());
//                updatePacient.executeUpdate();
//
//                conn.commit();
//                conn.setAutoCommit(true);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//}
