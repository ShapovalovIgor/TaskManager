package ru.shapovalov.Notification;

import ru.shapovalov.Task.Task;
import ru.shapovalov.Task.TaskManager;
import ru.shapovalov.UI.UI;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by igor on 15.11.14.
 */
public class Notification implements Serializable, Runnable {
    public static boolean exitNotification = false;
    private static final long serialVersionUID = 1L;
    public static Date systemDate;
    public static HashMap<Integer, Notification> getEventMap() {
        return eventMap;
    }

    public static void setEventMap(HashMap<Integer, Notification> eventMap) {
        Notification.eventMap = eventMap;
    }

    private static     HashMap<Integer, Notification> eventMap = new HashMap<Integer, Notification>();

    private String nameTask;
    private String taskDescription;
    private Date startDate;

    public String getNameTask() {
        return nameTask;
    }




    public String getTaskDescription() {
        return taskDescription;
    }


    public Date getStartDate() {
        return startDate;
    }


    public Notification(String nameTask, String taskDescription, Date startDate){
        this.nameTask = nameTask;
        this.taskDescription = taskDescription;
        this.startDate = startDate;
    }

    public void notificationTime(){
      while ( exitNotification != true){
        {
        for (HashMap.Entry<Integer, Notification> pair : getEventMap().entrySet())
        {
            Date date = new Date();
            try {
                systemDate = UI.dateFormat.parse(UI.dateFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(pair.getValue().getStartDate().equals(systemDate)){
                for (int i = 0; i<10; i++)
                {
                    System.out.println("!!!!!! - " + pair.getKey() + " - " + pair.getValue().getStartDate() + " - " +
                            pair.getValue().getNameTask() + " - " + pair.getValue().getTaskDescription() + " - !!!!!\7");
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        }
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void eventEditionON(){
    for (HashMap.Entry<Integer, Task> pair : TaskManager.getTaskHashMap().entrySet()){

        if(pair.getValue().getId() == Integer.parseInt(UI.getBr())){
            Notification.getEventMap().remove(pair.getKey());
            Task task = new Task(pair.getValue().getName(), pair.getValue().getDescription(),
                    pair.getValue().getStartDate(), true, pair.getValue().getId());
            TaskManager.getTaskHashMap().put(pair.getKey(), task);
            TaskManager.getInstance().writeTask();
        }
    }
    }
    public void eventEditionOFF(){
        for (HashMap.Entry<Integer, Task> pair : TaskManager.getTaskHashMap().entrySet()){                                    {
            if(pair.getValue().getId() == Integer.parseInt(UI.getBr())){
                Notification.getEventMap().remove(pair.getKey());

                Task task = new Task(pair.getValue().getName(), pair.getValue().getDescription(),
                        pair.getValue().getStartDate(), false, pair.getValue().getId());
                TaskManager.getTaskHashMap().put(pair.getKey(), task);
                TaskManager.getInstance().writeTask();
            }
        }

    }
    }
    public Notification(){}
    public static Notification getInstance() {
        Notification notification = new Notification();
        return notification;
    }

    @Override
    public void run() {
        Notification.getInstance().notificationTime();
    }

}
