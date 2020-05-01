package dataClasses;

import java.util.HashMap;
import java.util.Map;

/**This class represents data that are obtained from opex.csv file.
 * money represents map of finances for each key.
 *      String keys represent tuple of level 2 layer item in DS hierarchy and its level 3 asset
 *      (for example: ES-HV_field represents level 2 item ES and it's asset HV_field)
 *      Double value represent amount of money that should be added to given asset*/
public class OpexData {
    Map<String, Double> money;

    public OpexData()
    {
        this.money = new HashMap<>();
    }

    public Map<String, Double> getMoney() {
        return money;
    }

    public void setMoney(Map<String, Double> money) {
        this.money = money;
    }
}
