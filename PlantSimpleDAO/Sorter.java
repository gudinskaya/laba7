package org.anngudin.laba10.PlantSimpleDAO;

import org.anngudin.laba10.Plant.Plant;
import java.util.Comparator;


public class Sorter implements Comparator<Plant> {
    public int compare(Plant one, Plant another) {
        return one.getPlantName().compareTo(another.getPlantName());
    }
}
