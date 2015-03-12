package ru.shapovalov.UIswing;

    import ru.shapovalov.Notification.Notification;
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

    public class UIEventManage{

        //Объект таблицы
        JTable jTabTask;
        //    Задаём ширену поля
        int widthId=30; //например
        int widthName=100;
        int widthDescription=350; //например
        int widthStartDate = 215;


        //    Задаём поля
        int columnId=0; //например
        int columnName=1;
        int columnDescription=2; //например
        int columnStartDate = 3;


        public UIEventManage() {
            //Создаем новый контейнер JFrame
            JFrame jfrm = new JFrame("Event manage");
            //Устанавливаем диспетчер компоновки
            jfrm.getContentPane().setLayout(new FlowLayout());
            //Устанавливаем размер окна
            jfrm.setSize(750, 300);
            //Устанавливаем завершение программы при закрытии окна
            jfrm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            //Создаем новую таблицу на основе двумерного массива данных и заголовков
            jTabTask = new JTable(toTableModel(Notification.getEventMap()));
            jTabTask.getTableHeader().setResizingAllowed(false);

            jTabTask.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS); //так красивее... можно AUTO_RESIZE_OFF
            jTabTask.getColumnModel().getColumn(columnId).setPreferredWidth(widthId);
            jTabTask.getColumnModel().getColumn(columnId).setMaxWidth(widthId);
            jTabTask.getColumnModel().getColumn(columnName).setPreferredWidth(widthName);
            jTabTask.getColumnModel().getColumn(columnName).setMaxWidth(widthName);
            jTabTask.getColumnModel().getColumn(columnDescription).setPreferredWidth(widthDescription);
            jTabTask.getColumnModel().getColumn(columnDescription).setMaxWidth(widthDescription);
            jTabTask.getColumnModel().getColumn(columnStartDate).setPreferredWidth(widthStartDate);
            jTabTask.getColumnModel().getColumn(columnStartDate).setMaxWidth(widthStartDate);
            //Создаем панель прокрутки и включаем в ее состав нашу таблицу
            JScrollPane jscrlp = new JScrollPane(jTabTask);
            //Устаналиваем размеры прокручиваемой области
            jTabTask.setPreferredScrollableViewportSize(new Dimension(695, 300));
            //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
            jfrm.getContentPane().add(jscrlp);
            jfrm.setLocationRelativeTo(null);//Размещаем окно в центре экрана
            //Отображаем контейнер
            jfrm.setVisible(true);
            jfrm.pack();

        }
        public static TableModel toTableModel(HashMap<Integer,Notification> map) {
            DefaultTableModel model = new DefaultTableModel(
//                    Имена столбцов таблицы
                    new Object[] { "Id", "Name", "Description", "Start date" }, 0
            );
//            Конвертируем HashMap в массив Object
            for (HashMap.Entry<Integer,Notification> entry : map.entrySet()) {
                model.addRow(new Object[] { entry.getKey(), entry.getValue().getNameTask(), entry.getValue().getTaskDescription(),
                        entry.getValue().getStartDate()});

            }
            return model;
        }



    }
