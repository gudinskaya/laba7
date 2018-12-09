
package org.anngudin.laba10.PlantDAO;
 
import org.anngudin.laba10.Plant.Plant;
import java.util.List;
 
public interface PlantDAO
{
    public Long addPlant(Plant plant);
   
    public void updatePlant(Plant plant);
    
    public void deletePlant(Long plantId);
    
    public Plant getPlant(Long plantId);
    // public List findSame(String age, List<Plant> plants);
    
    public List<Plant> findPlants();
    
}
