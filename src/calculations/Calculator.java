package calculations;

import calculations.utils.CalcUtil;
import dataClasses.*;
import exceptions.InvalidDirectionException;
import exceptions.MissingDataException;
import dataClasses.Directions;

import java.util.HashMap;
import java.util.Map;

/**This class contains input data and functions needed for their processing.
 * In order to get correct data into trees, methods of this class have to be called in this order:
 * 1) createTrees() which will create tree for each of investment directions.
 * 2) fillData() which will get data from input files and put them in trees.
 * 3) calculateResultItems() which will calculate result pieces/kilometres with respect to inflation, opex money and transfers from AF4 to AF1.
 *
 * Not respecting oredr of calling will end up in exception and program will fail.*/
public class Calculator {
    final double AF4ToAF1 = 0.2; //fixed

    AsimData asimData;
    OpexData opexData;
    AsimData ratioData;

    PricesData prices;
    AdditionalData additionalData; //years + inflation

    Tree af1Tree;
    Tree af4Tree;
    Tree af6Tree;

    CalcUtil util = new CalcUtil();

    public Tree getAf1Tree() {
        return af1Tree;
    }

    public Tree getAf4Tree() {
        return af4Tree;
    }

    public Tree getAf6Tree() {
        return af6Tree;
    }

    public AsimData getAsimData() {
        return asimData;
    }

    public void setAsimData(AsimData asimData) {
        this.asimData = asimData;
    }

    public PricesData getPrices() {
        return prices;
    }

    public void setPrices(PricesData prices) {
        this.prices = prices;
    }

    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }

    public OpexData getOpexData() {
        return opexData;
    }

    public void setOpexData(OpexData opexData) {
        this.opexData = opexData;
    }

    public AsimData getRatioData() {
        return ratioData;
    }

    public void setRatioData(AsimData ratioData) {
        this.ratioData = ratioData;
    }

    /**This method checks if the data in calculator are values and not null.
     * This method checks validity of asim data, additional data, opex data, prices data and ratios data.
     * @return  boolaen value of true if all data are set otherwise false is returned */
    public boolean isValid()
    {
        if (this.getAsimData() == null)
        {
            System.out.println("Wrong asim data.");
            return false;
        }
        if (this.getAdditionalData() == null)
        {
            System.out.println("Wrong additional data.");
            return false;
        }
        if (this.getOpexData() == null)
        {
            System.out.println("Wrong opex data.");
            return false;
        }
        if (this.getPrices() == null)
        {
            System.out.println("Wrong prices data.");
            return false;
        }
        if (this.getRatioData() == null)
        {
            System.out.println("Wrong ratio data.");
            return false;
        }

        return true;
    }



    /**This method is used for filling input data in tree structures.
     * Filling order is: AF1, Af4, AF1 opex, AF6.
     * @throws MissingDataException when there are some data missing during filling the trees.
     * @throws InvalidDirectionException when at some point of filling the trees there was wrong direction used.*/
    public void fillData() throws MissingDataException, InvalidDirectionException {

        fillAF1();
        fillAF4();
        fillAF1Opex();
        fillAF6();
    }

    /**This method returns prices for all assets of given asset level key and direction.
     * @param dir is the direction of given tree.
     * @param assetLevel is the key that indicates the level of assets
     * @throws InvalidDirectionException there was wrong direction used when asking for prices for exact direction
     * @return Map of codes and their prices  */
    private Map<String, Integer> getPrices(Directions dir, String assetLevel) throws InvalidDirectionException {
        Map<String, Integer> dirPrices = prices.getDirectionPrices(dir);
        if (dirPrices == null)
        {
            throw new InvalidDirectionException("Null tree for given direction.");
        }
        Map<String, Integer> prices = new HashMap<>();
        for (String key : dirPrices.keySet())
        {
            if(key.startsWith(assetLevel + "-"))
            {
                prices.put(key, dirPrices.get(key));
            }
        }
        return prices;
    }

    /**This method returns ratios for all assets of given key and direction.
     * @param dir is the direction of given tree.
     * @param assetLevel is the key that indicates the level of assets
     * @return Map of codes and their ratios  */
    private Map<String, double[]> getRatios(Directions dir,String assetLevel)
    {
        Map<String,double[]> dirRatios = ratioData.getFinalData().get(dir);
        Map<String,double[]> ratios = new HashMap<>();
        for (String key:dirRatios.keySet())
        {
            if (key.startsWith(assetLevel+"-"))
            {
                ratios.put(key,dirRatios.get(key));
            }
        }
        return  ratios;
    }

    /**This method fills opex data for AF1 direction. Opex money are added to the additional finances of the AF1 components.
     * @throws MissingDataException when there were some necessary data missing during adding opex data.*/
    private void fillAF1Opex() throws MissingDataException {
        try {
            af1Tree.ds.getVvn().getVVN_vzduch().fillOpex(opexData.getMoney());
            af1Tree.ds.getVvn().getVVN_kabel().fillOpex(opexData.getMoney());
            af1Tree.ds.getVvn_vn().getEs().fillOpex(opexData.getMoney());
            af1Tree.ds.getVn().getVn_vzduch().fillOpex(opexData.getMoney());
            af1Tree.ds.getVn().getVn_kabel().fillOpex(opexData.getMoney());
            af1Tree.ds.getVn_nn().getDts().fillOpex(opexData.getMoney());
            af1Tree.ds.getNn().getNn_vzduch().fillOpex(opexData.getMoney());
            af1Tree.ds.getNn().getNn_kabel().fillOpex(opexData.getMoney());
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF1 VVN_vzduch data.");
        }
    }

    /**This method fills data for AF1 VVN_vzduch level.
     * @throws MissingDataException when there were some necessary data missing during filling VVN_vzduch level.
     * @throws InvalidDirectionException when there was invalid direction used for getting data.*/
    private void fillAF1VVN_vzduch() throws MissingDataException, InvalidDirectionException {
        try {
            String assetLevel = "VVN_vzduch";
            af1Tree.ds.getVvn().getVVN_vzduch().fill(getRatios(Directions.AF1, assetLevel),
                                                               asimData.getFinalData().get(Directions.AF1).get(assetLevel),
                                                               getPrices(Directions.AF1, assetLevel));
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF1 VVN_vzduch data.");
        }
    }

    /**This method fills data for AF1 ES level.
     * @throws MissingDataException when there were some necessary data missing during filling ES level.
     * @throws InvalidDirectionException when there was invalid direction used for getting data.*/
    private void fillAF1ES() throws MissingDataException, InvalidDirectionException {
        try
        {
            String assetLevel = "ES";
            af1Tree.ds.getVvn_vn().getEs().fill(getRatios(Directions.AF1,assetLevel)
                    ,asimData.getFinalData().get(Directions.AF1).get(assetLevel)
                    ,getPrices(Directions.AF1,assetLevel));
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF1 ES data.");
        }

    }

    /**This method fills data for AF1 DTS level.
      * @throws MissingDataException when there were some necessary data missing during filling DTS level.
      * @throws InvalidDirectionException when there was invalid direction used for getting data.*/
    private void fillAF1DTS() throws MissingDataException, InvalidDirectionException {
        try
        {
            String assetLevel = "DTS";
            af1Tree.ds.getVn_nn().getDts().fill(getRatios(Directions.AF1, assetLevel)
                    ,asimData.getFinalData().get(Directions.AF1).get(assetLevel)
                    ,getPrices(Directions.AF1, assetLevel));
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF1 DTS data.");
        }

    }

    /**This method fills data for AF1 VN_vzduch level.
     * @throws MissingDataException when there were some necessary data missing during filling VN_vzduch level.
     * @throws InvalidDirectionException when there was invalid direction used for getting data.*/
    private void fillAF1VN_vzduch() throws MissingDataException, InvalidDirectionException {
        try
        {
            String assetLevel = "VN_vzduch";
            af1Tree.ds.getVn().getVn_vzduch().fill(getRatios(Directions.AF1, assetLevel)
                    ,asimData.getFinalData().get(Directions.AF1).get(assetLevel),
                    getPrices(Directions.AF1, assetLevel));
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF1 VN_vzduch data.");
        }
    }

    /**This method fills data for AF1 VVN_kabel level.
     * @throws MissingDataException when there were some necessary data missing during filling VVN_kabel level.
     * @throws InvalidDirectionException when there was invalid direction used for getting data.*/
    private void fillAF1VVN_kabel() throws MissingDataException, InvalidDirectionException {
        try
        {
            String assetLevel = "VVN_kabel";
            af1Tree.ds.getVvn().getVVN_kabel().fill(getRatios(Directions.AF1,assetLevel),
                                                    asimData.getFinalData().get(Directions.AF1).get(assetLevel),
                                                    getPrices(Directions.AF1,assetLevel));
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF1 VVN_kabel data.");
        }

    }

    /**This method fills data for AF1 VN_kabel level.
     * @throws MissingDataException when there were some necessary data missing during filling VN_kabel level.
     * @throws InvalidDirectionException when there was invalid direction used for getting data.*/
    private void fillAF1VN_kabel() throws MissingDataException, InvalidDirectionException {
        try
        {
            String assetLevel = "VN_kabel";
            af1Tree.ds.getVn().getVn_kabel().fill(getRatios(Directions.AF1,assetLevel),
                                                  asimData.getFinalData().get(Directions.AF1).get(assetLevel),
                                                  getPrices(Directions.AF1,assetLevel));
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF1 VN_kabel data.");
        }

    }

    /**This method fills data for AF1 NN_vzduch level.
     * @throws MissingDataException when there were some necessary data missing during filling NN_vzduch level.
     * @throws InvalidDirectionException when there was invalid direction used for getting data.*/
    private void fillAF1NN_vzduch() throws MissingDataException, InvalidDirectionException {
        try
        {
            String assetLevel = "NN_vzduch";
            af1Tree.ds.getNn().getNn_vzduch().fill(getRatios(Directions.AF1,assetLevel),
                                                   asimData.getFinalData().get(Directions.AF1).get(assetLevel),
                                                   getPrices(Directions.AF1,assetLevel));
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF1 NN_vzduch data.");
        }
    }

    /**This method fills data for AF1 NN_kabel level.
     * @throws MissingDataException when there were some necessary data missing during filling NN_kabel level.
     * @throws InvalidDirectionException when there was invalid direction used for getting data.*/
    private void fillAF1NN_kabel() throws MissingDataException, InvalidDirectionException {
        try
        {
            String assetLevel = "NN_kabel";
            af1Tree.ds.getNn().getNn_kabel().fill(getRatios(Directions.AF1,assetLevel),
                                                  asimData.getFinalData().get(Directions.AF1).get(assetLevel),
                                                  getPrices(Directions.AF1,assetLevel));
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF1 NN_kabel data.");
        }

    }

    /**This method fills data for AF1 Netz level.
     * @throws MissingDataException when there were some necessary data missing during filling Netz level.
     * @throws InvalidDirectionException when there was invalid direction used for getting data.*/
    private void fillAF1Netz() throws MissingDataException {
        try {
            af1Tree.ds.getNetz().setPlannedFinance(util.addArrays(asimData.getFinalData().get(Directions.AF1).get("Ostatne"),
                                                   asimData.getFinalData().get(Directions.AF1).get("Delta_Gross_Capex")));
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF1 Netz data.");
        }
    }

    /**This method fills data for AF1 direction.
     * @throws MissingDataException when there were some necessary data missing during filling AF1 level.
     * @throws InvalidDirectionException when there was invalid direction used for getting data.*/
    private void fillAF1() throws MissingDataException, InvalidDirectionException {
        fillAF1DTS();
        fillAF1VVN_vzduch();
        fillAF1VVN_kabel();
        fillAF1ES();
        fillAF1VN_vzduch();
        fillAF1VN_kabel();
        fillAF1NN_vzduch();
        fillAF1NN_kabel();
        fillAF1Netz();
    }

    /**This method covers transfer from direction AF4 to AF1.
     * @throws MissingDataException when there were some necessary data missing while transfering data from AF4 to AF1.*/
    private void fillAF4ToAF1() throws MissingDataException {
        String levels = "VN+DTS+NN";
        double[] money = util.multiply(asimData.getFinalData().get(Directions.AF4).get(levels),AF4ToAF1);
        Map<String,double[]> ratios = getRatios(Directions.AF4_AF1,levels);
        String[] keys = levels.split("\\+");
        for (int i = 0; i < keys.length; i++)
        {
            Map<String,double[]> ratiosForKey = getRatios(Directions.AF4_AF1,keys[i]);
            ratios.putAll(util.multiplyMap(ratiosForKey, ratios.get(levels+"-"+keys[i]))); //adding all sub-keys of the and their values
        }

        try
        {
            fillAF4ToAF1DTS(money, ratios);
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF4toAF1 DTS data");
        }

        try
        {
            fillAF4ToAF1NN(money, ratios);
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF4toAF1 NN data");
        }

        try
        {
            fillAF4ToAF1VN(money, ratios);
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF4toAF1 VN data");
        }
    }

    /**This method fills additional data for AF1, DTS level.*/
    private void fillAF4ToAF1DTS(double[] money, Map<String,double[]> ratios)
    {
        af1Tree.ds.getVn_nn().getDts().fillAdditional(ratios, money);
    }

    /**This method fills additional data for AF1, NN level.*/
    private void fillAF4ToAF1NN(double[] money, Map<String,double[]> ratios)
    {
        af1Tree.ds.getNn().getNn_vzduch().fillAdditional(ratios,money);
        af1Tree.ds.getNn().getNn_kabel().fillAdditional(ratios,money);
    }

    /**This method fills additional data for AF1, VN level.*/
    private void fillAF4ToAF1VN(double[] money, Map<String,double[]> ratios)
    {
        af1Tree.ds.getVn().getVn_vzduch().fillAdditional(ratios,money);
        af1Tree.ds.getVn().getVn_kabel().fillAdditional(ratios,money);
    }

    /**This method fills data for direction AF4.*/
    private void fillReducedAF4() throws MissingDataException, InvalidDirectionException {
        try
        {
            fillAf4VN_NN_DTS();
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF4 VN_NN_DTS data.");
        }
        try
        {
            fillNN_NNprip();
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing AF4 NN_NNprip data.");
        }


    }

    /**This method fills data for NN level in direction AF4.*/
    private void fillNN_NNprip()
    {
        String levels = "NN+NNprip";
        double[] money = asimData.getFinalData().get(Directions.AF4).get(levels);
        Map<String,double[]> ratios = getRatios(Directions.AF4,levels);
        String[] keys = levels.split("\\+");

        //fill the assets additional finance
        af4Tree.ds.getNn().getNn_kabel().getAssets().get("NN_izolovane").additionalFinance = util.multiplyArrays(money,ratios.get(levels+"-NN_izolovane"));
        af4Tree.ds.getNn().getNn_vzduch().getAssets().get("NN_vedenie").additionalFinance = util.multiplyArrays(money,ratios.get(levels+"-NN_vedenie"));
    }

    /**This method fills data for DTS level of direction AF4.*/
    private void fillAF4DTS(double[] money, Map<String,double[]> ratios) throws InvalidDirectionException {
        String assetLevel = "DTS";
        af4Tree.ds.getVn_nn().getDts().fill(ratios, money, getPrices(Directions.AF4, assetLevel));
    }

    /**This method fills data for VN level of direction AF4.*/
    private void fillAF4VN(double[] money, Map<String,double[]> ratios) throws InvalidDirectionException {
        String assetLevel = "VN_vzduch";
        af4Tree.ds.getVn().getVn_vzduch().fill(ratios, money, getPrices(Directions.AF4, assetLevel));
        assetLevel = "VN_kabel";
        af4Tree.ds.getVn().getVn_kabel().fill(ratios, money, getPrices(Directions.AF4, assetLevel));
    }

    /**This method fills data for NN level of direction AF4.*/
    private void fillAF4NN(double[] money, Map<String,double[]> ratios) throws InvalidDirectionException {
        String assetLevel = "NN_vzduch";
        af4Tree.ds.getNn().getNn_vzduch().fill(ratios, money, getPrices(Directions.AF4, assetLevel));
        assetLevel = "NN_kabel";
        af4Tree.ds.getNn().getNn_kabel().fill(ratios, money, getPrices(Directions.AF4, assetLevel));
    }

    /**This method fills data for the combination of VN, DTS and NN levels of direction AF4.*/
    private void fillAf4VN_NN_DTS() throws InvalidDirectionException {
        String levels = "VN+DTS+NN";
        double[] money = util.multiply(asimData.getFinalData().get(Directions.AF4).get(levels),1-AF4ToAF1);
        Map<String,double[]> ratios = getRatios(Directions.AF4,levels);
        String[] keys = levels.split("\\+");
        for (int i = 0; i < keys.length; i++)
        {
            Map<String,double[]> ratiosForKey = getRatios(Directions.AF4,keys[i]);
            ratios.putAll(util.multiplyMap(ratiosForKey, ratios.get(levels+"-"+keys[i]))); //adding all sub-keys of the and their values
        }
        fillAF4DTS(money, ratios);
        fillAF4NN(money, ratios);
        fillAF4VN(money, ratios);
    }

    /**This method fills data for Netz level of direction AF4.*/
    private void fillAF4Netz() throws MissingDataException {
       try {
           af4Tree.ds.getNetz().setPlannedFinance(util.addArrays(asimData.getFinalData().get(Directions.AF4).get("Obnova_elm"),
                                                  asimData.getFinalData().get(Directions.AF4).get("Nakup_EN_VB")));
       }
       catch (NullPointerException e)
       {
           throw new MissingDataException("Missing AF4 Netz data.");
       }
    }

    /**This method fills data for direction AF4.*/
    private void fillAF4() throws MissingDataException, InvalidDirectionException {
        fillReducedAF4();
        fillAF4Netz();
        fillAF4ToAF1();

    }

    /**This method fills data for Netz level of direction AF6.*/
    private void fillAF6Netz()
    {
        af6Tree.ds.getNetz().setPlannedFinance(util.addArrays(asimData.getFinalData().get(Directions.AF6).get("IMS"),
                                               asimData.getFinalData().get(Directions.AF6).get("Ostatne")));
    }

    /**This method fills data for the direction AF6.*/
    private void fillAF6() throws MissingDataException {
        try
        {
            fillAF6Netz();
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing data for AF6 Netz data.");
        }

    }

    /**This method calculates final result data.
     * For all directions and their trees this method calculates results with respect to inflation.
     * Results are stored in the trees for the directions.
     * @throws MissingDataException when there are som important data missing during calculating final results.
     * When there is som data missing, exception contains message where the problem is.*/
    public void calculateResultItems() throws MissingDataException {

            try
            {
                af1Tree.ds.calculate(additionalData.getinflation());
            }
            catch (MissingDataException e)
            {
                throw new MissingDataException(e.getMessage()+" for AF1 tree.");
            }

            try
            {
                af4Tree.ds.calculate(additionalData.getinflation());
            }
            catch (MissingDataException e)
            {
                throw new MissingDataException(e.getMessage()+" for AF4 tree.");
            }


    }

    /**This method creates initial instances of trees for Af1, Af4 and Af6 direction.
     * Trees are initialized with number of years that we get from additional data file.
     * Number of years is calculated as: endingYear - beginningYear +1.
     * */
    public void createTrees() {
         af1Tree = new Tree(Directions.AF1, additionalData.getNumberOfYears());
         af4Tree = new Tree(Directions.AF4, additionalData.getNumberOfYears());
         af6Tree = new Tree(Directions.AF6, additionalData.getNumberOfYears());
    }
}
