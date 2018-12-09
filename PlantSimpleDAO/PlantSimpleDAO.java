//доделать вывод findsame на экран лиюл подцветка в таблице

package org.anngudin.laba10.PlantSimpleDAO;

import org.anngudin.laba10.Plant.Plant;
import org.anngudin.laba10.PlantDAO.PlantDAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PlantSimpleDAO implements PlantDAO {
    private final List<Plant> plants = new ArrayList<Plant>();

    // Прямо в конструкторе добавляем три контакта
    public PlantSimpleDAO() {
         addPlant(new Plant("незабудка", "буранчивовое", "11", "болотная"));
        addPlant(new Plant("клен", "сапиндовые", "15", "остролистный"));
        addPlant(new Plant("дуб", "буковые", "50", "черешчатый"));
    }

    @Override
    public Long addPlant(Plant plant) {
        Long id = generatePlantId();
        plant.setPlantId(id);
        plants.add(plant);
        return id;
    }

    // public void sortplants(List<plant> plants) {
    //     plants.sort(plants, (plant one, plant other) -> {
    //         return
    //         one.getPlantName().compareTo(other.getPlantName());
    //     });
    // }

    @Override
    public void updatePlant(Plant plant) {
        Plant oldPlant = getPlant(plant.getPlantId());
        if (oldPlant != null) {
            oldPlant.setPlantName(plant.getPlantName());
            oldPlant.setFamily(plant.getFamily());
            oldPlant.setAge(plant.getAge());
            oldPlant.setKind(plant.getKind());
        }
    }

    @Override
    public void deletePlant(Long plantId) {
        for (Iterator<Plant> it = plants.iterator(); it.hasNext();) {
            Plant cnt = it.next();
            if (cnt.getPlantId().equals(plantId)) {
                it.remove();
            }
        }
    }

    // @Override
    // public List findSame(String age, List<Plant> plants) {
    //     for (Plant plant : plants) {
    //         if (plant.getAge().equals(age)) {
    //             return plant;
    //          }
    //     }
    //     return null;

    // }    
    
    @Override
    public Plant getPlant(Long plantId) {
        for (Plant plant : plants) {
            if (plant.getPlantId().equals(plantId)) {
                return plant;
            }
        }
        return null;
    }

    @Override
    public List<Plant> findPlants() {
        return plants;
    }

    private Long generatePlantId() {
        Long plantId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while (getPlant(plantId) != null) {
            plantId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return plantId;
    }
}
