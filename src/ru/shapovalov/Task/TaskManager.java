package ru.shapovalov.Task;

import ru.shapovalov.Notification.Notification;
import ru.shapovalov.Notification.TaskToEvent;
import ru.shapovalov.UI.UI;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Date;
import java.util.HashMap;

public  class  TaskManager {
    public static String fileNameWriterReader = "Task.xml";
    public static HashMap<Integer, Task> getTaskHashMap() {
        return taskHashMap;
    }
    public static void setTaskHashMap(HashMap<Integer, Task> taskHashMap) {
        TaskManager.taskHashMap = taskHashMap;
    }

    private static HashMap<Integer, Task> taskHashMap = new HashMap<Integer, Task>();

    public void create(){
        TaskManager.getInstance().readTask();
        String name = null;
        String description = null;
        Date startDate = null;
        boolean active = false;
        System.out.println("You are on the menu of the task creation.\n");
        Task task = new Task(UI.menuCreateName(name), UI.menuCreateDescription(description),
                UI.menuCreateDate(startDate), UI.menuCreateActive(active), new Integer(GeneratorId.getInstance().getNextID()));
            taskHashMap.put(task.getId(), task);
            System.out.println("task successfully created ! \n");
            TaskManager.getInstance().writeTask();
            TaskToEvent.getInstance().taskToEventCeateAadEditTask();
//        }else {
//            System.err.println("One of the fields blank name, description, startDate. task is not created! \n");
//        }

    }
    public void edit(){
        for (HashMap.Entry<Integer, Task> pair : TaskManager.getTaskHashMap().entrySet())
        {
            if(pair.getValue().getId() == Integer.parseInt(UI.getBr())){
                String name = pair.getValue().getName();
                String description = pair.getValue().getDescription();
                Date startDate = pair.getValue().getStartDate();
                boolean active = pair.getValue().getActive();
                System.out.println("You are on the menu of the task edit.\n");

                Task task = new Task(UI.menuCreateName(name), UI.menuCreateDescription(description), UI.menuCreateDate(startDate), UI.menuCreateActive(active), pair.getValue().getId());

                taskHashMap.put(pair.getValue().getId(), task);
                if(pair.getValue().getActive() == false) {
                    Notification.getEventMap().remove(pair.getValue().getId());
                }else if(pair.getValue().getActive() == false){
                    Notification notification = new Notification(pair.getValue().getName(), pair.getValue().getDescription(), pair.getValue().getStartDate());
                    Notification.getEventMap().put(pair.getValue().getId(), notification);
                }

                System.out.println(pair.getValue().getId() + " - " + pair.getValue().getName()  + " - "
                        + pair.getValue().getDescription() + " - " + pair.getValue().getStartDate() + " - "
                        + pair.getValue().getActive() + " - finish edit");
                 }
        }
        TaskToEvent.getInstance().taskToEventCeateAadEditTask();
    }
    public void delete(){
        try {
        for (HashMap.Entry<Integer, Task> pair : TaskManager.getTaskHashMap().entrySet())
        {
            if(pair.getKey().equals(Integer.parseInt(UI.getBr()))){
                TaskManager.getTaskHashMap().remove(pair.getKey());
                System.out.println(pair.getKey() + " - delete");
                writeTask();
              break;
            }
        }


        }catch (Exception e)
        {
            System.err.println("Очень плохое исключение");
        }
    }
    public void writeTask() {
        write(taskHashMap, fileNameWriterReader);
    }

    public void readTask(){
        TaskManager.taskHashMap = read(fileNameWriterReader);
    }

    public void write(HashMap map, String fileNameWriterReader) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                new FileOutputStream(fileNameWriterReader)))) {
            encoder.writeObject(map);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public HashMap read(String fileNameWriterReader) {
        HashMap taskHashMap = null;

        try(XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                new FileInputStream(fileNameWriterReader)))) {
            taskHashMap = (HashMap)decoder.readObject();
        } catch(IOException ex) {
            System.out.println("Will create a new Event Log task");
        }

        return taskHashMap;

    }


    public static TaskManager getInstance() {
        TaskManager taskManager = new TaskManager();
        return taskManager;
    }


}
