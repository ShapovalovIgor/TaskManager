package ru.shapovalov.Notification;

import ru.shapovalov.Server.Server;
import ru.shapovalov.Task.Task;
import ru.shapovalov.Task.TaskManager;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by igor on 08.12.14.
 */
public class TaskToEvent implements Runnable{

    @Override
    public void run() {
        TaskManager.getInstance().readTask();
        taskToEvent();
    }
    public void  taskToEvent(){
        boolean exitTaskToEvent = false;
       while (exitTaskToEvent != true){
            for (HashMap.Entry<Integer, Task> pair : TaskManager.getTaskHashMap().entrySet())
            {
                if(pair.getValue().getActive() == true){
                    Notification notification = new Notification(pair.getValue().getName(), pair.getValue().getDescription(), pair.getValue().getStartDate());
                    Notification.getEventMap().put(pair.getValue().getId(), notification);

                }
            }
            try {
                TimeUnit.MINUTES.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void  taskToEventCeateAadEditTask(){

            for (HashMap.Entry<Integer, Task> pair : TaskManager.getTaskHashMap().entrySet())
            {
                if(pair.getValue().getActive() == true){
                    Notification notification = new Notification(pair.getValue().getName(), pair.getValue().getDescription(), pair.getValue().getStartDate());
                    Notification.getEventMap().put(pair.getValue().getId(), notification);
                }
            }
    }


    public static TaskToEvent getInstance() {
        TaskToEvent taskToEvent = new TaskToEvent();
        return taskToEvent;
    }

}
