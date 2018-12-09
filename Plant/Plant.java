package org.anngudin.laba10.Plant;


import javax.swing.JOptionPane;


public class Plant 
{
   
    private Long plantId;
   
    private String plantName; 
    
    private String family;
    
    private String age;
    
    private String kind; 
 
    public Plant() {
    }
 
    public Plant(String plantName, String family, String age, String kind) {
        this.plantName = plantName;
        this.family = family;
        this.age = age;
        this.kind = kind;
    }
    
    public Plant(Long plantId, String plantName, String family, String age, String kind) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.family = family;
        this.age = age;
        this.kind = kind;
    }
//  public static Scan() 
    public Long getPlantId() {
        return plantId;
    }
 
    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }
 
    public String getPlantName() {
        return plantName;
    }
 
    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
 
    public String getFamily() {
        return family;
    }
 
    public void setFamily(String family) {
        this.family = family;
    }
 
    public String getAge() {
        return age;
    }
 
    public void setAge(String age) {
        this.age = age;
    }
 
    public String getKind() {
        return kind;
    }
 
    public void setKind(String kind) {
        this.kind = kind;
    }
 
    @Override
    public String toString() {
        return "Plant{" + "plantId=" + plantId + ", plantName=" + plantName + ", family=" + family + ", age=" + age + ", kind=" + kind + '}';
    }
}
