package ru.shapovalov.Task;

import java.io.*;
public class GeneratorId {


    public static int id = 0;
    public int getNextID() {
                ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new
                    FileInputStream("id.bin"));
        } catch (IOException e) {
            System.out.println("Will create a new list id");
        }
        try {
            id = in.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        id++;
        try {
            ObjectOutputStream out = new
                    ObjectOutputStream(new
                    FileOutputStream("id.bin"));
            out.writeInt(id);
            out.close();
        }
        catch(IOException e) {
            System.out.println("Some error occurred!");
        }
        return id;
    }
    private GeneratorId(){}
    private static GeneratorId instance = null;
    public static final GeneratorId getInstance() {

        if (instance == null) {
            instance = new GeneratorId();
        }
        return instance;


    }

    public String getId() {
        return null;
    }
}