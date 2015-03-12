package ru.shapovalov.UI;

import ru.shapovalov.Main;
import ru.shapovalov.Notification.Notification;
import ru.shapovalov.Notification.TaskToEvent;
import ru.shapovalov.Server.Server;
import ru.shapovalov.Task.Task;
import ru.shapovalov.Task.TaskManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by igor on 15.11.14.
 */
public class UI implements Runnable {


    private static   BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(System.in));




    public static   SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-HH:mm");

    public static String getBr() {
        return br;
    }

    private static  String br  = null;


        public static void menuMain()
        {
            boolean exit = false;
            while (exit != true){
                System.out.println("To continue, select typing its number and then pressing the ENTER key. \n" +
                        "Task manage - 1, Event manage - 2, Exit - 3");

                try {
                    br = bufferedReader.readLine();

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
                switch (br){

                    case "1":{
                        menuTaskManage();
                        break;
                    }
                    case "2":{
                        menuEventManage();
                        break;
                    }
                    case "3":{
                        System.exit(0);
//                     Main.exec.shutdown();
//                        break;
                    }
                    default:{
                        System.err.println("This command does not exist try again. \n");

                    }
                }
            }


        }

        public static void menuTaskManage()
        {
            boolean exit = false;
            while (exit != true){
            System.out.println("To continue, select typing its number and then pressing the ENTER key. \n" +
                    "Create - 1, Delete - 2, View - 3, Edit - 4, Return - 5");

            try {
                br = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
                switch (br){

                    case "1":{
                        menuCreate();
                        break;
                    }
                    case "2":{
                        menuDelete();
                        break;
                    }
                    case "3":{
                        menuView();
                        break;
                    }
                    case "4":{
                        menuEdit();
                        break;
                    }
                    case "5":{
                        exit = true;
                        break;
                    }
                    default:{
                        System.err.println("This command does not exist try again. \n");
                    }
                }
            }
         }

        public static  void menuEventManage()
        {
        menuViewEvent();
        }

        public static void menuCreate()
        {
      TaskManager.getInstance().create();
        }

        public static String menuCreateName(String name)
        {
            boolean exit = false;
            while (exit != true){
                System.out.println("Entered name is: " +    (name!=null?name:"no entered name"));
                System.out.println("Enter your choice: 1 for Enter name or 2 to exit from this menu");
                try {
                    br = bufferedReader.readLine();

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

                switch (br){

                    case "1":{
                        System.out.println("Enter a name, and then press Enter");
                        try {
                            br = bufferedReader.readLine();
                            name = br;
                        } catch (IOException e) {
                            System.err.println("You have entered an invalid name format. Try again.");
                            exit = true;
                            break;
                        }
                        break;
                    }

                    case "2":{
                        exit = true;
                        break;
                    }
                    default:{
                        System.err.println("This command does not exist try again. \n");

                    }
                }
            }


            return name;
        }

        public static String menuCreateDescription(String description)
        {
            boolean exit = false;
            while (exit != true){
                System.out.println("Entered description is: " +    (description!=null?description:"no entered description"));
                System.out.println("Enter your choice: 1 for Enter description or 2 to exit from this menu");
                try {
                    br = bufferedReader.readLine();

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

                switch (br){

                    case "1":{
                        System.out.println("Enter a description, and then press Enter");
                        try {
                            br = bufferedReader.readLine();
                            description = br;
                        } catch (IOException e) {
                            System.err.println("You have entered an invalid description format. Try again.");
                            exit = true;
                            break;
                        }
                        break;
                    }

                    case "2":{
                        exit = true;
                        break;
                    }
                    default:{
                        System.err.println("This command does not exist try again. \n");

                    }
                }
            }

            return description;
        }

        public static Date menuCreateDate(Date startDate)
        {

            boolean exit = false;
            while (exit != true){

                System.out.println("Entered date is: " +    (startDate!=null?startDate:"no entered date"));
                System.out.println("Enter your choice: 1 for Enter date or 2 to exit from this menu");
                try {
                    br = bufferedReader.readLine();

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
                switch (br){

                    case "1":{
                        System.out.println("Enter a start date (Must be administered on a strictly defined format \"dd.MM.yyyy-HH:mm = 09.09.2015-13:22\" ");
                        try {
                            br = bufferedReader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            exit = true;
                            break;
                        }
                        try {
                            startDate = dateFormat.parse(br);
                        } catch (ParseException e) {
                            System.err.println("You have entered an invalid date format. Try again.");
                        }
                        break;
                    }

                    case "2":{
                        exit = true;
                        break;
                    }
                    default:{
                        System.err.println("This command does not exist try again. \n");

                    }
                }
            }
            return startDate;
        }

        public static boolean menuCreateActive(boolean active)
        {
            boolean exit = false;
            while (exit != true){
                System.out.println("Entered active is: " +active);
                System.out.println("Enter your choice: 1 for Enter active or 2 to exit from this menu");
                try {
                    br = bufferedReader.readLine();

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

                switch (br){

                    case "1":{
                        System.out.println("Activate plan objectives - 1, deactivate - 0. ");
                        try {
                            br = bufferedReader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            exit = true;
                            break;
                        }

                           switch (br){
                               case "1":
                               {
                                   active = true;
                                   break;
                               }
                               case "0":{
                                   active = false;
                                   break;
                               }
                               default:{
                                   break;
                               }
                           }


                        break;
                    }

                    case "2":{
                        exit = true;
                        break;
                    }
                    default:{
                        System.err.println("This command does not exist try again. \n");

                    }
                }

                }
        return active;
        }

        public static void menuDelete()
        {
            boolean exit = false;
            while (exit != true){

                System.out.println("Enter your choice: 1 for delete task or 2 to exit from this menu");
                try {
                    br = bufferedReader.readLine();

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

                switch (br){

                    case "1":{
                        TaskManager.getInstance().readTask();
                        for (HashMap.Entry<Integer, Task> pair : TaskManager.getTaskHashMap().entrySet())
                        {
                            System.out.println(+ pair.getKey() + " - " + pair.getValue().getName()  + " - "
                                    + pair.getValue().getDescription() + " " + pair.getValue().getStartDate() + " - "
                                    + pair.getValue().getActive());
                        }
                        System.out.println("Enter a ID, and then press Enter");
                        try {

                          br = bufferedReader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            exit = true;
                            break;
                        }
                        try {
                            TaskManager.getInstance().delete();
                        } catch (Exception e) {
                            System.err.println("You have entered an invalid ID format. Try again.");
                        }
                        break;
                    }

                    case "2":{
                        exit = true;
                        break;
                    }
                    default:{
                        System.err.println("This command does not exist try again. \n");

                    }
                }   }

        }

        public static void menuView()
        {
            boolean exit = false;
            while (exit != true){

                System.out.println("Enter your choice: 1 for view task or 2 to exit from this menu");

                try {
                    br = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
                switch (br){

                    case "1":{
                        TaskManager.getInstance().readTask();

                        System.out.println("Enter a ID, and then press Enter");
                        for (HashMap.Entry<Integer, Task> pair : TaskManager.getTaskHashMap().entrySet())
                        {
                            System.out.println(pair.getValue().getId() + " - " + pair.getValue().getName()  + " - "
                                    + pair.getValue().getDescription() + " - " + pair.getValue().getStartDate() + " - "
                                    + pair.getValue().getActive());
                        }

                        break;
                    }

                    case "2":{
                        exit = true;
                        break;
                    }
                    default:{
                        System.err.println("This command does not exist try again. \n");

                    }
                }   }
        }

        public static void menuEdit()
        {
            boolean exit = false;
            while (exit != true){

                System.out.println("Enter your choice: 1 for edit task or 2 to exit from this menu");
                try {
                    br = bufferedReader.readLine();

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

                switch (br){

                    case "1":{
                        TaskManager.getInstance().readTask();
                        for (HashMap.Entry<Integer, Task> pair : TaskManager.getTaskHashMap().entrySet())
                        {
                            System.out.println(pair.getValue().getId() + " - " + pair.getValue().getName()  + " - "
                                    + pair.getValue().getDescription() + " " + pair.getValue().getStartDate() + " - "
                                    + pair.getValue().getActive());
                        }
                        System.out.println("Enter a ID, and then press Enter");
                        try {
                            br = bufferedReader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            exit = true;
                            break;
                        }
                        try {
                            TaskManager.getInstance().edit();
                            TaskManager.getInstance().writeTask();
                        } catch (Exception e) {
                            System.err.println("You have entered an invalid ID format. Try again.");
                        }
                        break;
                    }

                    case "2":{
                        exit = true;
                        break;
                    }
                    default:{
                        System.err.println("This command does not exist try again. \n");

                    }
                }   }
        }

        public static void menuViewEvent(){
            boolean exit = false;
            while (exit != true){

                System.out.println("Enter your choice: 1 for view event or 2 to exit from this menu");
                try {
                    br = bufferedReader.readLine();

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

                switch (br){

                    case "1":{

                        for (HashMap.Entry<Integer, Notification> pair : Notification.getEventMap().entrySet())
                        {
                            System.out.println(+ pair.getKey() + " - " +  pair.getValue().getNameTask() + " - "
                                    + pair.getValue().getTaskDescription() + " - " + pair.getValue().getStartDate());
                        }
                        System.out.println("Enter a ID, and then press Enter");

                        try {
                            br = bufferedReader.readLine();

                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                        try {
                        System.out.println("Active - 1 of dective - 0");
                            String bri = null;
                            try {
                                bri = bufferedReader.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            switch (bri){
                                case "1":
                                {
                                Notification.getInstance().eventEditionON();
                                    break;
                                }
                                case "0":{
                                Notification.getInstance().eventEditionOFF();
                                    break;
                                }
                                default:{
                                    break;
                                }
                                    }


                        } catch (Exception e) {
                            System.err.println("You have entered an invalid ID format. Try again.");
                        }
                        break;
                    }

                    case "2":{
                        exit = true;
                        break;
                    }
                    default:{
                        System.err.println("This command does not exist try again. \n");

                    }
                }
            }

        }


    @Override
    public void run() {
        menuMain();
    }
}
