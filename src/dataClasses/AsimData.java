package dataClasses;

import java.util.HashMap;
import java.util.Map;

/**This class represents data that can be obtained from asim.csv file or pomerASIM.csv file.
 * directionsData is variable that is used for partial calculation of data for each direction.
 *
 * For asim.csv directionsData is: *
 *      String keys in this map represent:
 *          for AF1 it represents the name of level 2 layer in DS hierarchy and last one are finances going into Netz
 *          for AF4 it represents the name of grouped level 2 layer items in DS hierarchy.
 *              for example: VN+DTS+NN represents the group of VN items, DTS items and NN items.
 *          for AF6 it represents names of items which finances go straightly into Netz.
 *       Double array of values has length of simulation years and represents finances for given key for each year of simulation.
 *
 * For pomerAsim.csv directionsData is:
 *      String keys in this map represent:
 *           Tuple of level 2 layer item and its concrete level 3 layer item, these items are separated by -
 *           (for example: VN_vzduch-VN_drevo represents VN_vzduch layer and its item VN_drevo)
 *           Or for transfer from AF4 to AF1 the key is either tuple described above or tuple of grouped level 2 items and one level 2 item
 *           (for example: VN+DTS+NN-VN represents group of VN level, DTS level and VN level and the ratio is for VN level)
 *      Double array of values has length of simulation years and represents ratios for given key for each year of simulation.
 *      If while reading we have only one value (constant), this value is used for each year of simulation.
 *
 * finalData contains all data that are needed for further calculations.
 *         Direction key represents investment direction for which we would like to store data.
 *         Map values are directionsData, that means that firstly we create directions data while reading files,
 *         and when data for actual direction are ready, we add them into final map with direction key snd the values we read.
 *         In the end this map will contain all read data from file for each direction we work with (AF1, AF4 and AF6).
 **/
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
