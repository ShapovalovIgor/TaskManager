package ru.shapovalov.UIswing;


    import ru.shapovalov.Notification.TaskToEvent;
    import ru.shapovalov.Task.GeneratorId;
    import ru.shapovalov.Task.Task;
    import ru.shapovalov.Task.TaskManager;
    import ru.shapovalov.UI.UI;

    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.math.BigDecimal;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.Map;
    import javax.swing.*;
    import javax.swing.table.*;

public class UITaskManage{

        //Объект таблицы
        JTable jTabTask;
//    Задаём ширену поля
    int widthId=30; //например
    int widthName=100;
    int widthIdtask=50; //например
    int widthStartDate = 215;
    int widthActive = 45;

//    Задаём поля
    int columnId=0; //например
    int columnName=1;
    int columnIdTask=5; //например
    int columnStartDate = 3;
    int columnActive= 4;

//     Кнопочки
    JButton newTask;

        public UITaskManage() {
            //Создаем новый контейнер JFrame
            JFrame jfrm = new JFrame("Task manage");
            //Устанавливаем диспетчер компоновки
            jfrm.getContentPane().setLayout(new FlowLayout());
            //Устанавливаем размер окна
            jfrm.setSize(850, 400);
            //Устанавливаем завершение программы при закрытии окна
            jfrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //Создаем новую таблицу на основе двумерного массива данных и заголовков
            jTabTask = new JTable(toTableModel(TaskManager.getTaskHashMap()));
            jTabTask.getTableHeader().setResizingAllowed(false);

            jTabTask.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS); //так красивее... можно AUTO_RESIZE_OFF
            jTabTask.getColumnModel().getColumn(columnId).setPreferredWidth(widthId);
            jTabTask.getColumnModel().getColumn(columnId).setMaxWidth(widthId);
            jTabTask.getColumnModel().getColumn(columnName).setPreferredWidth(widthName);
            jTabTask.getColumnModel().getColumn(columnName).setMaxWidth(widthName);
            jTabTask.getColumnModel().getColumn(columnIdTask).setPreferredWidth(widthIdtask);
            jTabTask.getColumnModel().getColumn(columnIdTask).setMaxWidth(widthIdtask);
            jTabTask.getColumnModel().getColumn(columnStartDate).setPreferredWidth(widthStartDate);
            jTabTask.getColumnModel().getColumn(columnStartDate).setMaxWidth(widthStartDate);
            jTabTask.getColumnModel().getColumn(columnActive).setPreferredWidth(widthActive);
            jTabTask.getColumnModel().getColumn(columnActive).setMaxWidth(widthActive);
            //Создаем панель прокрутки и включаем в ее состав нашу таблицу
            JScrollPane jscrlp = new JScrollPane(jTabTask);
            //Устаналиваем размеры прокручиваемой области
            jTabTask.setPreferredScrollableViewportSize(new Dimension(800, 300));
            //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
            jfrm.getContentPane().add(jscrlp);
            //Give the name of the button
            newTask = new JButton("New task");
            //adds a button to new task
            jfrm.add(newTask);
            //      create a response by pressing a button "send"
            newTask.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new NewTask();
                }
            });
            //Отображаем контейнер
            jfrm.setLocationRelativeTo(null);//Размещаем окно в центре экрана
            jfrm.setVisible(true);
//            jfrm.pack();

        }
        public static TableModel toTableModel(HashMap<Integer,Task> map) {
            DefaultTableModel model = new DefaultTableModel(
//                    Имена столбцов таблицы
                    new Object[] { "Id", "Name", "Description", "Start date", "Active", "Id task" }, 0
            );
//            Конвертируем HashMap в массив Object
            for (HashMap.Entry<Integer,Task> entry : map.entrySet()) {
                model.addRow(new Object[] { entry.getKey(), entry.getValue().getName(), entry.getValue().getDescription(),
                entry.getValue().getStartDate(), entry.getValue().getActive(), entry.getValue().getId()});
            }
            return model;
        }
    }
