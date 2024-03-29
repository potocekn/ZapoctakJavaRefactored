package treeParts.ds.vn_nn;

import exceptions.MissingDataException;

/**This class represents VN_NN level in tree hierarchy. It contains 1 mid-level DTS.*/
public class VN_NN {
    private DTS dts;

    public VN_NN(int numberOfYears)
    {
        this.dts = new DTS(numberOfYears);
    }

    public DTS getDts() {
        return dts;
    }

    public void setDts(DTS dts) {
        this.dts = dts;
    }

    /**This method calculates final amounts for each child.
     * @param inflation represents array of inflation values for each year of simulation.
     * @throws MissingDataException when there are some necessary data missing when calculating final results.*/
    public void calculate(double inflation[]) throws MissingDataException {
        dts.calculate(inflation);
    }
}
