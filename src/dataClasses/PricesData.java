package dataClasses;

import readers.Directions;
import java.util.HashMap;
import java.util.Map;

/**This class represents data that are obtained from prices.csv file.
 * Class contains maps for directions AF1, AF4 and AF6.*/
public class PricesData {
    private Map<String, Integer> af1tree;
    private Map<String, Integer> af4tree;
    private Map<String,Integer> af6tree;

    public PricesData()
    {
        this.af1tree = new HashMap<>();
        this.af4tree = new HashMap<>();
        this.af6tree = new HashMap<>();
    }

    /**This method returns tree for given direction. When there is wrong key, default value is null.
     * @param dir is the direction for which we would like to get info.*/
    public Map<String, Integer> getDirectionPrices(Directions dir)
    {
        switch (dir)
        {
            case AF1: return this.af1tree;
            case AF4: return this.af4tree;
            case AF6: return this.af6tree;
            default: return null;
        }
    }

    public Map<String, Integer> getAf1tree() {
        return af1tree;
    }

    public void setAf1tree(Map<String, Integer> af1tree) {
        this.af1tree = af1tree;
    }

    public Map<String, Integer> getAf4tree() {
        return af4tree;
    }

    public void setAf4tree(Map<String, Integer> af4tree) {
        this.af4tree = af4tree;
    }

    public Map<String, Integer> getAf6tree() {
        return af6tree;
    }

    public void setAf6tree(Map<String, Integer> af6tree) {
        this.af6tree = af6tree;
    }
}
