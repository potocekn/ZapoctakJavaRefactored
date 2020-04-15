package dataClasses;

import readers.Directions;

import java.util.HashMap;
import java.util.Map;

/**This class represents data that can be obtained from asim.csv file or pomerASIM.csv file.
 * directionsData is variable that is used for partial calculation of data for each direction.
 * finalData contains all data that are needed for further calculations.*/
public class AsimData {

    Map<String,double[]> directionsData;
    Map<Directions,Map<String, double[]>> finalData;

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
