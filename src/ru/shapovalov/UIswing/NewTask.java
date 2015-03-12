package ru.shapovalov.UIswing;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by igor on 05.03.15.
 */
public class NewTask {
    public NewTask(){
        JLabel nameLabel;
        JLabel descriptionLabel;
        JTextField name;
        JTextField description;
        JCalendar startDate;
        //Создаем новый контейнер JFrame
        JFrame jfrm = new JFrame("New Task");
        //Устанавливаем диспетчер компоновки
        jfrm.getContentPane().setLayout(new FlowLayout());
        //Устанавливаем размер окна
        jfrm.setSize(400, 500);
        //Устанавливаем завершение программы при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nameLabel = new JLabel("Name:");
        name = new JTextField(16);
        descriptionLabel = new JLabel("Description:");
        description = new JTextField();
        startDate = new JCalendar();
        jfrm.add(nameLabel);
        jfrm.add(name);
        jfrm.add(descriptionLabel);
        jfrm.add(description);
        jfrm.add(startDate);
        Date d = startDate.getDate();
        //      in the center of the screen window
        jfrm.setLocationRelativeTo(null);
//      Display the container
        jfrm.setVisible(true);
//        jfrm.pack();
    }
}
