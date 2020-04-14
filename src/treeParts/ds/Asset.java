package treeParts.ds;

import calculations.utils.CalcUtil;

/**This abstract class represents all assets in the hierarchy. It contains all necessary features that all assets in the tree must contain.
 * The features are: price, ratio, metric, planned finance, additional finance and amound of asset.*/
public class Asset {
    public String name;
    public int price;
    public double[] ratio;
    public String metric;
    public double[] plannedFinance;
    public double[] additionalFinance;
    public double[] amount;
    public CalcUtil utilAsset = new CalcUtil();

    public Asset(String name, int numberOfYears, String metric)
    {
        this.name = name;
        this.ratio = new double[numberOfYears];
        this.amount = new double[numberOfYears];
        this.plannedFinance = new double[numberOfYears];
        this.additionalFinance = new double[numberOfYears];
        this.price = 0;
        this.metric = metric;
    }

    /**This method calculates result items.*/
    public void calculate(double[] inflation)
    {
        double[] totalMoney = utilAsset.addArrays(plannedFinance,additionalFinance);
        double[] priceWithInflation = utilAsset.multiply(utilAsset.inflationMultiply(inflation),price);
        amount = utilAsset.divideArrays(totalMoney,priceWithInflation);
    }

    /**This method returns product of two given arrays (multiplication by values).*/
    public double[] multiply(double[] ratios, double[] money)
    {
        if ((ratios == null) || (money == null))
        {
            return null;
        }
        double[] result = new double[Math.max(ratios.length,money.length)];
        for (int i = 0; i < Math.min(ratios.length,money.length);i++)
        {
            result[i] = ratios[i]*money[i];
        }

        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double[] getRatio() {
        return ratio;
    }

    public void setRatio(double[] ratio) {
        this.ratio = ratio;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public double[] getPlannedFinance() {
        return plannedFinance;
    }

    public void setPlannedFinance(double[] plannedFinance) {
        this.plannedFinance = plannedFinance;
    }

    public double[] getAdditionalFinance() {
        return additionalFinance;
    }

    public void setAdditionalFinance(double[] additionalFinance) {
        this.additionalFinance = additionalFinance;
    }

    public double[] getAmount() {
        return amount;
    }

    public void setAmount(double[] amount) {
        this.amount = amount;
    }
}
