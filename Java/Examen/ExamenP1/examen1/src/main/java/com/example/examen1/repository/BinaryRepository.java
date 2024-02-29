package com.example.examen1.repository;

import com.example.examen1.domain.Entity;
import com.example.examen1.exception.RepositoryException;

import java.io.*;
import java.util.ArrayList;


public class BinaryRepository<T extends Entity> extends Repository<T> {
    private String fileName;

    public BinaryRepository(String fileName) {
//        super();
        this.fileName = fileName;
        try {
            loadFile();
        } catch (IOException | ClassNotFoundException e) {
            // not very good practice
//            throw new RuntimeException(e);
        }
    }


    @Override
    public void addEntity(T o) throws RepositoryException {
        super.addEntity(o);
        // saveFile se executa doar daca super.add() nu a aruncat exceptie
        try {
            saveFile();
        } catch (IOException e) {
            throw new RepositoryException("Error saving file " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(int id) throws RepositoryException {
        super.deleteById(id);
        try {
            saveFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(T elem) throws RepositoryException {
        super.update(elem);
        try {
            saveFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            this.elems = (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            // TODO Replace with logging
            System.out.println("Repo starting a new file");
        } finally {
            // finally se executa tot timpul
            if (ois != null)
                ois.close();
        }
    }


    private void saveFile() throws IOException {
        // try-with-resources
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(elems);
        }

//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
//
//        oos.close();
    }

}



//public class BinaryRepository<T extends Entity> extends Repository<T>{
//    private String fileName;
//
//    public BinaryRepository(String fileName) {
//        this.fileName = fileName;
//        try {
//            loadFile();
//        } catch (IOException | ClassNotFoundException e) {
////            throw new RuntimeException(e);
//        }
//    }
//
//    public BinaryRepository() {
//
//    }
//
//
//    private void loadFile() throws IOException, ClassNotFoundException {
//        ObjectInputStream ois = null;
//        try {
//            ois = new ObjectInputStream(new FileInputStream(fileName));
//            this.elems = (ArrayList<T>) ois.readObject();
//        } catch (FileNotFoundException | RuntimeException e) {
//            // TODO Replace with logging
//            System.out.println("Repo starting a new file");
//        } finally {
//            if (ois != null)
//                ois.close();
//        }
//    }
//
//    private void saveFile() throws IOException {
//        // try-with-resources
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
//            oos.writeObject(elems);
//        }
////        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
////        oos.close();
//    }
//
//    @Override
//    public void addEntity(T elem) throws RepositoryException {
//        super.addEntity(elem);
//        // saveFile se executa doar daca super.add() nu a aruncat exceptie
//        try {
//            saveFile();
//        } catch (IOException e) {
//            throw new RepositoryException("Error saving file " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void deleteById(int id) throws RepositoryException {
//        super.deleteById(id);
//        try {
//            saveFile();
//        } catch (IOException e) {
//            throw new RepositoryException("Error saving file " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void update(T elem) throws RepositoryException {
//        super.update(elem);
//        try {
//            saveFile();
//        } catch (IOException e) {
//            throw new RepositoryException("Error saving file " + e.getMessage(), e);
//        }
//    }
//}
