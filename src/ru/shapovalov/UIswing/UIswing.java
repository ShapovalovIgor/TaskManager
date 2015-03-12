package ru.shapovalov.UIswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by igor on 05.03.15.
 */
public class UIswing implements Runnable {
    private JButton task_manage;
    private JButton event_manage;
    private JButton exit;

    public UIswing(){
        JFrame jfrm = new JFrame("Task manager");
        //Устанавливаем завершение программы при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        размер окна
        jfrm.setSize(300, 200);
////        Добовляем свою иконку
//        URL imgURL = UIswing.class.getResource("icon.png");
//        ImageIcon icon = new ImageIcon(imgURL);
//        jfrm.setIconImage(icon.getImage());
//      Добовляем кнопки
        task_manage = new JButton("Task manage");
        event_manage = new JButton("Event manage");
        exit = new JButton("Exit");
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(task_manage);
        buttonsPanel.add(event_manage);
        buttonsPanel.add(exit);
        jfrm.add(buttonsPanel, BorderLayout.SOUTH);

        task_manage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UITaskManage();
            }
        });

        event_manage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new UIEventManage();
            }
        });

        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Отображаем контейнер
        jfrm.setLocationRelativeTo(null);
        jfrm.setVisible(true);
        jfrm.pack();
    }

    @Override
    public void run() {

    }
}
