import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainForm mainWindow = new MainForm(); // Создаем объект класса MainWindow
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Способ выхода из формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width=1000, height=500;
        mainWindow.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна
        mainWindow.setTitle("Редактор");//заголовок формы
        mainWindow.setVisible(true);// Отображаем созданное окно
    }
}