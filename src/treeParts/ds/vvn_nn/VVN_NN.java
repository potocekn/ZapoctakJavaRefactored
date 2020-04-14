package treeParts.ds.vvn_nn;

import exceptions.MissingDataException;

/**This class represents VVN_NN level in tree hierarchy. It contains 1 mid-level ES.*/
public class VVN_NN {

    private ES es;

    public VVN_NN(int numberOfYears)
    {
        this.es = new ES(numberOfYears);
    }

    public ES getEs() {
        return es;
    }

    public void setEs(ES es) {
        this.es = es;
    }

    /**This method calculates final amounts for each child.*/
    public void calculate(double inflation[]) throws MissingDataException {
        es.calculate(inflation);
    }
}
