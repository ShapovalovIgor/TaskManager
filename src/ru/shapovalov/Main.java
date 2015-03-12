package ru.shapovalov;

import ru.shapovalov.Notification.Notification;
import ru.shapovalov.Notification.TaskToEvent;
import ru.shapovalov.UI.UI;
import ru.shapovalov.Server.Server;
import ru.shapovalov.UIswing.UITaskManage;
import ru.shapovalov.UIswing.UIswing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
   public static ExecutorService exec;
    public static void main(String[] args){
        getInstance().start();
    }
public void start (){
    exec = Executors.newCachedThreadPool();
    exec.execute(new UI());
    exec.execute(new Notification());
    exec.execute(new TaskToEvent());
    exec.execute(new Server());
    exec.execute(new UIswing());
//    exec.shutdownNow();
    exec.shutdown();
}
    public static Main getInstance() {
        Main main = new Main();
        return main;
    }
}
