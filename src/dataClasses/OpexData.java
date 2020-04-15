package dataClasses;

import java.util.HashMap;
import java.util.Map;

/**This class represents data that are obtained from opex.csv file.
 * money represents map of finances for each key.*/
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
