package org.anngudin.laba10.PlantManager;
 
import org.anngudin.laba10.PlantDAO.PlantDAO;
import org.anngudin.laba10.PlantDAOFactory.PlantDAOFactory;
import org.anngudin.laba10.Plant.Plant;
import java.util.List;
 
/**
 * Класс для реализации функций над списком контактов
 */
public class PlantManager 
{
    private PlantDAO dao;
    
    public PlantManager() {
        dao = PlantDAOFactory.getPlantDAO();
    }
    
    // Добавление контакта - возвращает ID добавленного контакта
    public Long addPlant(Plant plant) {
        return dao.addPlant(plant);
    }
    
    // Редактирование контакта
    public void updatePlant(Plant plant) {
        dao.updatePlant(plant);
    }
 
    // Удаление контакта по его ID
    public void deletePlant(Long plantId) {
        dao.deletePlant(plantId);
    }
 
    // Получение одного контакта
    public Plant getPlant(Long plantId) {
        return dao.getPlant(plantId);
    }

    // public void sortPlants(List<Plant> Plants) {
    //     dao.sortPlants(Plants);
    // }  
    // Получение списка контактов
    public List<Plant> findPlants() {
        return dao.findPlants();
    }
}
