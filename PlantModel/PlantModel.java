package org.anngudin.laba10.PlantModel;

import org.anngudin.laba10.Plant.Plant;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PlantModel extends AbstractTableModel {
    // Список загловков для колонок в таблице
    private static final String[] headers = { "ID", "Название", "Семейство", "Вид", "Возраст" };

    // Здесь мы храним список контактов, которые будем отображать в таблице
    private final List<Plant> plants;

    public PlantModel(List<Plant> plants) {
        this.plants = plants;
    }

    @Override
    // Получить количество строк в таблице - у нас это размер коллекции
    public int getRowCount() {
        return plants.size();
    }

    @Override
    // Получить количество столбцов - их у нас стольк же, сколько полей
    // у класса plant - всего пять
    public int getColumnCount() {
        return 5;
    }

    @Override
    // Вернуть загловок колонки - мы его берем из массива headers
    public String getColumnName(int col) {
        return headers[col];
    }

    @Override
    // Получить объект для тображения в кокнретной ячейке таблицы
    // В данном случае мы отдаем строковое представление поля
    public Object getValueAt(int row, int col) {
        Plant plant = plants.get(row);
        // В зависимости от номера колонки возвращаем то или иное поле контакта
        switch (col) {
        case 0:
            return plant.getPlantId().toString();
        case 1:
            return plant.getPlantName();
        case 2:
            return plant.getFamily();
        case 3:
            return plant.getKind();
        default:
            return plant.getAge();
        }
    }
}
