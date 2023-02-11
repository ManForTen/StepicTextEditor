import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainForm extends JFrame {

    private JPanel panel;
    private JTextPane text;
    private JButton buttonOpen;
    private JButton buttonSave;
    private JButton buttonSaveAs;
    private JLabel labelFile;
    public File file;

    public MainForm() {
        this.getContentPane().add(panel);

        buttonOpen.addActionListener(e -> { // Открыть файл
            if (dialog(FileDialog.LOAD,"Открыть текстовый файл","*.txt")){ // Если диалог прошел успешно
                StringBuilder s = new StringBuilder(); // Создаем строку
                try (FileReader reader = new FileReader(file)) { // Считываем все в строку ридером
                    int c;
                    while ((c = reader.read()) != -1) // Пока есть символы
                        s.append ((char)c); // Добавляем из в строку
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                text.setText(s.toString());//записываем строку в редактор
                labelFile.setText(file.getName());//
            }
        });

        buttonSave.addActionListener(e -> { // Сохранить файл
            if (file!=null) save(); // Если файл был открыт, то сохраняем в него
            else saveNew(); // Сохраняем как новый файл
        });

        buttonSaveAs.addActionListener(e-> { // Сохранить файл как
            saveNew(); // Сохраняем как новый файл
        });
    }

    private void saveNew() {
        if (text.getText().length() > 0) // Если файла не было но есть текст, то открываем диалог
            if (dialog(FileDialog.SAVE,"Сохранить текстовый файл","*.txt")) { // Если диалог прошел успешно
                save();
                labelFile.setText(file.getName());//
            }
    }

    private void save() { // Метод записи файла
        try {
            FileWriter writer = new FileWriter(file);// врайтер
            writer.write(text.getText());
            writer.flush(); // Сохраняем
            writer.close(); // Закрываем
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean dialog(int dialog, String title, String filter){ // Метод диалога открытия или сохранения файла
        FileDialog fileDialog = new FileDialog(new Frame(), title, dialog);
        fileDialog.setFile(filter);
        fileDialog.setVisible(true);
        File[] files = fileDialog.getFiles();
        if (files.length > 0) { // Проверяем, возвращено ли что-то
            file = files[0];
            return true;
        } else return false;
    }
}
