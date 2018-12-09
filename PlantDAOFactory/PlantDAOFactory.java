package org.anngudin.laba10.PlantDAOFactory;
import org.anngudin.laba10.PlantDAO.PlantDAO;
import org.anngudin.laba10.PlantSimpleDAO.PlantSimpleDAO;

/**
 * Фабрика для создания экземпляра PlantDAO
 */
public class PlantDAOFactory 
{
    public static PlantDAO getPlantDAO() {
        return new PlantSimpleDAO();
    }
}
