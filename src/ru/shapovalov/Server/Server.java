package ru.shapovalov.Server;
import ru.shapovalov.Notification.Notification;
import ru.shapovalov.Task.TaskManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class Server implements Runnable, Serializable  {

        public void SendMapEvent() throws Throwable {

            ServerSocket ss = new ServerSocket(9080);

            while (true) {
                Socket s = ss.accept();
//                System.err.println("Client accepted");
                new Thread(new SocketProcessor(s)).start();
            }
        }

        private static class SocketProcessor implements Runnable {

            private Socket s;
        final     private ObjectInputStream socketReader;
        final     private ObjectOutputStream socketWriter;
            private SocketProcessor(Socket s) throws Throwable {
                this.socketWriter = new ObjectOutputStream(s.getOutputStream());
                this.socketReader = new ObjectInputStream(s.getInputStream() );
            }

            public void run() {
                boolean exit = false;
//                System.out.println("The client is connected - " + socketWriter);

//                Send event data to the client
                while (exit != true){
                    try {
                        for (HashMap.Entry<Integer, Notification> pair : Notification.getEventMap().entrySet())
                        {
                            socketWriter.writeInt(pair.getKey());
                            socketWriter.flush();
                            socketWriter.writeObject(pair.getValue().getNameTask());
                           socketWriter.flush();
                            socketWriter.writeObject(pair.getValue().getTaskDescription());
                           socketWriter.flush();
                            socketWriter.writeObject(pair.getValue().getStartDate());
                        }
                        socketWriter.flush();
                    } catch (Throwable t) {
                        exit = false;
//                        System.out.println("Client is disconnected - " + socketWriter);
                    } finally {
                        try {
                            s.close();
                        } catch (Throwable t) {
                    /*do nothing*/
                        }
                    }
                    try {
//                        Pause for 10 minutes, then re-send the event log
                        TimeUnit.MINUTES.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    public void run() {
        try {
            SendMapEvent();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
} // /:~