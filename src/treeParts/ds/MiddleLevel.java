package treeParts.ds;

import exceptions.MissingDataException;

import java.util.HashMap;
import java.util.Map;

/**This generic class represents mid-level in the tree hierarchy and has map of assets and their keys.
 * Class contains all needed methods for filling data and calculations.*/
public class MiddleLevel {

    protected  Map<String, Asset> assets = new HashMap<>();

    /**This method calculates final amounts for each child.
     * @param inflation represents array of inflation values for each year of simulation.
     * @throws MissingDataException when there are some necessary data missing when calculating final results.*/
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

    /**This method fills additional finances for level.
     * @param ratio represents map of ratios for each level and item.
     * @param money represents array of finances for each year of simulation*/
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

    /**This method fills opex data for AF1 level. Opex data are added to additional finances of Af1 assets.
     * @param opex represents opex data for simulation
     * */
    public void fillOpex(Map<String,Double> opex)
    {
        for (String key:opex.keySet())
        {
            String[] keys = key.split("-");

            if (assets.containsKey(keys[1]))
            {
                assets.get(keys[1]).additionalFinance = assets.get(keys[1]).utilAsset.addArrayConstant(assets.get(keys[1]).additionalFinance, opex.get(key));
            }
        }//end of for
    }

    /**This method fills its asset's data (ration, price, planned finances)
     * @param ratio represents map of ratios for each level and item.
     * @param money represents array of finances for each year of simulation
     * @prices represents map of prices for each level and item.*/
    public void fill(Map<String,double[]> ratio, double[] money, Map<String,Integer> prices)
    {
        for (String key:ratio.keySet())
        {
            String[] keys = key.split("-");

            if (assets.containsKey(keys[1]))
            {
                assets.get(keys[1]).ratio = ratio.get(key);
                assets.get(keys[1]).plannedFinance = assets.get(keys[1]).utilAsset.multiplyArrays(assets.get(keys[1]).ratio, money);
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
