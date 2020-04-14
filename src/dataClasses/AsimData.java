package dataClasses;

import readers.Directions;

import java.util.HashMap;
import java.util.Map;

public class AsimData {

    Map<String,double[]> directionsData;
    Map<Directions,Map<String, double[]>> finalData;

    /**Implicit constructor. Sets default values for directionsData and finalData.*/
    public AsimData()
    {
        this.directionsData = new HashMap<>();
        this.finalData = new HashMap<>();
    }

    public void setDirectionsData(Map<String,double[]> directionsData)
    {
        this.directionsData = directionsData;
    }

    public void setFinalData(Map<Directions, Map<String, double[]>> finalData)
    {
        this.finalData = finalData;
    }

    public Map<String, double[]> getDirectionsData() {
        return directionsData;
    }

    public Map<Directions, Map<String, double[]>> getFinalData() {
        return finalData;
    }
}
