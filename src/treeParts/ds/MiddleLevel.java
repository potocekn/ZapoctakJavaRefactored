package treeParts.ds;

import exceptions.MissingDataException;

import java.util.HashMap;
import java.util.Map;

/**This class represents mid-level in the tree hierarchy. To this class belong 3 assets DTS_kiosk, DTS_murovana, DTS_stoziarova.*/
public class MiddleLevel {

    protected  Map<String, Asset> assets = new HashMap<>();

    /**This method calculates final amounts for each child.*/
    public void calculate(double inflation[]) throws MissingDataException {
        try
        {
            for (Asset asset:assets.values()) {
                asset.calculate(inflation);
            }
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing data ");
        }

    }

    /**This method fills additional finances for level.*/
    public void fillAdditional(Map<String,double[]> ratio, double[] money)
    {
        for (String key:ratio.keySet())
        {
            String[] keys = key.split("-");

            if (assets.containsKey(keys[1]))
            {
                assets.get(keys[1]).additionalFinance = assets.get(keys[1]).utilAsset.multiplyArrays(ratio.get(key),money);
            }
        }//end of for
    }

    /**This method fills its asset's data (ration, price, planned finances)*/
    public void fill(Map<String,double[]> ratio, double[] money, Map<String,Integer> prices)
    {
        for (String key:ratio.keySet())
        {
            String[] keys = key.split("-");

            if (assets.containsKey(keys[1]))
            {
                assets.get(keys[1]).ratio = ratio.get(key);
                assets.get(keys[1]).plannedFinance = assets.get(keys[1]).multiply(assets.get(keys[1]).ratio, money);
            }

        }//end of for

        for (String key:prices.keySet())
        {
            String[] keys = key.split("-");

            if (assets.containsKey(keys[1]))
            {
                assets.get(keys[1]).price = prices.get(key);
            }

        }
    }

    public Map<String, Asset> getAssets() {
        return assets;
    }

}
