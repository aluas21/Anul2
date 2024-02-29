package com.example.demo.repository;

import com.example.demo.domain.Entity;
import com.example.demo.domain.EntityConvert;
import com.example.demo.domain.PacientConvert;
import com.example.demo.exception.DuplicateIdException;
import com.example.demo.exception.NotFoundId;
import com.example.demo.exception.RepositoryException;

import java.io.*;

public class TextRepository <T extends Entity> extends Repository<T>{
    private String fileName;

    private EntityConvert<T> convert;

    public TextRepository(String fileName, EntityConvert<T> converter) {
        this.fileName = fileName;
        this.convert = converter;

        loadFile();
    }

    @Override
    public void addEntity(T elem) throws RepositoryException {
        super.addEntity(elem);
        try{
            saveFile();
        }
        catch (RuntimeException re){throw new RepositoryException("Error saving file",re);
        }
    }

    @Override
    public void deleteById(int id) throws RepositoryException {
        super.deleteById(id);
        try{
            saveFile();
        }
        catch (RuntimeException re){throw new RepositoryException("Error saving file",re);
        }
    }

    @Override
    public void update(T elem) throws RepositoryException {
        super.update(elem);
        try{
            saveFile();
        }
        catch (RuntimeException re){throw new RepositoryException("Error saving file",re);
        }
    }


    private void saveFile(){
        try (FileWriter fw = new FileWriter(fileName)) {
            for (T object : elems) {
                fw.write(convert.ToString(object));
                fw.write("\r\n");
            }
        } catch (IOException e) {throw new RuntimeException(e);
        }
    }

    private void loadFile(){
        elems.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null && !line.isEmpty()) {
                elems.add(convert.fromString(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
