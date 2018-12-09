package org.anngudin.laba10.PlantFrame;

import org.anngudin.laba10.PlantManager.PlantManager;
import org.anngudin.laba10.Plant.Plant;
import org.anngudin.laba10.PlantModel.PlantModel;
import org.anngudin.laba10.EditPlantDialog.EditPlantDialog;
import org.anngudin.laba10.PlantSimpleDAO.PlantSimpleDAO;
import org.anngudin.laba10.PlantSimpleDAO.Sorter;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class PlantFrame extends JFrame implements ActionListener {
    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";
    private static final String SORT = "SORT";
    // private static final String FIND = "FIND";

    private final PlantManager PlantManager = new PlantManager();
    private final JTable plantTable = new JTable();

   
    public PlantFrame() {
        // Выставляем у таблицы свойство, которое позволяет выделить
        // ТОЛЬКО одну строку в таблице
        plantTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // Используем layout manager
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        // Каждый элемент является последним в строке
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(5, 5, 0, 5);

        JPanel btnPanel = new JPanel();
    
        btnPanel.setLayout(gridbag);
       
      
        btnPanel.add(createButton(gridbag, gbc, "Обновить", LOAD));
        btnPanel.add(createButton(gridbag, gbc, "Добавить", ADD));
        btnPanel.add(createButton(gridbag, gbc, "Исправить", EDIT));
        btnPanel.add(createButton(gridbag, gbc, "Удалить", DELETE));
        btnPanel.add(createButton(gridbag, gbc, "Сортировать", SORT));
        // btnPanel.add(createButton(gridbag, gbc, "Найти", FIND));

        JPanel left = new JPanel();
        
        left.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        left.add(btnPanel, BorderLayout.NORTH);
        // Кладем панель для левой колонки на форму слева - WEST
        add(left, BorderLayout.WEST);

        // Кладем панель со скролингом, внутри которой нахоится наша таблица
        // Теперь таблица может скроллироваться
        add(new JScrollPane(plantTable), BorderLayout.CENTER);

        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Загружаем контакты
        loadPlant();
    }

    // Метод создает кнопку с заданными харктеристиками - заголовок и действие
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будетп роверяться в обработчике и мы будем знать, какую

// именно кнопку нажали
        button.setActionCommand(action);
        // Обработчиком события от кнопки являемся сама форма
        button.addActionListener(this);
        // Выставляем свойства для размещения для кнопки
        gridbag.setConstraints(button, gbc);
        return button;
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        // Получаем команду - ActionCommand
        String action = ae.getActionCommand();
        // В зависимости от команды выполняем действия
        switch (action) {
        // Перегрузка данных
        case LOAD:
            loadPlant();
            break;
        // Добавление записи
        case ADD:
            addPlant();
            break;
        // Исправление записи
        case EDIT:
            editPlant();
            break;
        // Удаление записи
        case DELETE:
            deletePlant();
            break;
         case SORT:
           sortPlants();
           break;
         }
    }


    
    private void sortPlants() {
        // Обращаемся к классу для загрузки списка контактов
        List<Plant> plants = PlantManager.findPlants();
        Collections.sort(plants, new Sorter());
    }

    // Загрухить список контактов
    private void loadPlant() {
        // Обращаемся к классу для загрузки списка контактов
        List<Plant> plants = PlantManager.findPlants();
        // Создаем модель, которой передаем полученный список
        PlantModel cm = new PlantModel(plants);
        // Передаем нашу модель таблице - и она может ее отображать
        plantTable.setModel(cm);
    }

    // private void sortPlants() {
    //     // Обращаемся к классу для сортировки списка контактов
    //     List<Plant> Plants = PlantManager.findPlants();
    //     PlantManager.sortPlants(List<Plant> Plants);
    //     // Создаем модель, которой передаем полученный список
    //     loadPlant();
    //     // Передаем нашу модель таблице - и она может ее отображать
    // }

    // Добавление контакта
    private void addPlant() {
        // Создаем диалог для ввода данных
        EditPlantDialog ecd = new EditPlantDialog();
        // Обрабатываем закрытие диалога
        savePlant(ecd);
    }

    // Редактирование контакта
    private void editPlant() {
        // Получаем выделеннуб строку
        int sr = plantTable.getSelectedRow();
        // если строка выделена - можно ее редактировать
        if (sr != -1) {
            // Получаем ID контакта
            Long id = Long.parseLong(plantTable.getModel().getValueAt(sr, 0).toString());
            // получаем данные контакта по его ID
            Plant cnt = PlantManager.getPlant(id);
            // Создаем диалог для ввода данных и передаем туда контакт
            EditPlantDialog ecd = new EditPlantDialog(cnt);
            // Обрабатываем закрытие диалога
            savePlant(ecd);
        } else {
            // Если строка не выделена - сообщаем об этом
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для редактирования");
        }
    }

    // Удаление контакта
    private void deletePlant() {
        // Получаем выделеннуб строку
        int sr = plantTable.getSelectedRow();
        if (sr != -1) {
            // Получаем ID контакта
            Long id = Long.parseLong(plantTable.getModel().getValueAt(sr, 0).toString());
            // Удаляем контакт
            PlantManager.deletePlant(id);
            // перегружаем список контактов
            loadPlant();
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
        }
    }

   
    // Общий метод для добавления и изменения контакта
    private void savePlant(EditPlantDialog ecd) {
        // Если мы нажали кнопку SAVE
        if (ecd.isSave()) {
            // Получаем контакт из диалогового окна
            Plant cnt = ecd.getPlant();
            if (cnt.getPlantId() != null) {
                // Если ID у контакта есть, то мы его обновляем
                PlantManager.updatePlant(cnt);
            } else {
                // Если у контакта нет ID - значит он новый и мы его добавляем
                PlantManager.addPlant(cnt);
            }
            loadPlant();
        }
    }
}
