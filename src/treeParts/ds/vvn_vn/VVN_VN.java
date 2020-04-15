package treeParts.ds.vvn_vn;

import exceptions.MissingDataException;

/**This class represents VVN_VN level in tree hierarchy. It contains 1 mid-level ES.*/
public class VVN_VN {

    private ES es;

    public VVN_VN(int numberOfYears)
    {
        this.es = new ES(numberOfYears);
    }

    public ES getEs() {
        return es;
    }

    public void setEs(ES es) {
        this.es = es;
    }

    /**This method calculates final amounts for each child.
     * @param inflation represents array of inflation values for each year of simulation.
     * @throws MissingDataException when there are some necessary data missing when calculating final results.*/
    public void calculate(double inflation[]) throws MissingDataException {
        es.calculate(inflation);
    }
}
