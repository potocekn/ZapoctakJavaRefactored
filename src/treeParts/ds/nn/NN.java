package treeParts.ds.nn;

import exceptions.MissingDataException;

/**This class represents NN level in tree hierarchy. It contains 2 mid-levels NN_vzduch and NN_kabel*/
public class NN {
    private NN_vzduch nn_vzduch;
    private NN_kabel nn_kabel;

    public NN(int numberOfYears)
    {
        this.nn_vzduch = new NN_vzduch(numberOfYears);
        this.nn_kabel = new NN_kabel(numberOfYears);
    }

    /**This method calculates final amounts for each child.
     * @param inflation represents array of inflation values for each year of simulation.
     * @throws MissingDataException when there are some necessary data missing when calculating final results.*/
    public void calculate(double inflation[]) throws MissingDataException {
        try
        {
            nn_kabel.calculate(inflation);
            nn_vzduch.calculate(inflation);
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing data for NN");
        }

    }

    public NN_vzduch getNn_vzduch() {
        return nn_vzduch;
    }

    public void setNn_vzduch(NN_vzduch nn_vzduch) {
        this.nn_vzduch = nn_vzduch;
    }

    public NN_kabel getNn_kabel() {
        return nn_kabel;
    }

    public void setNn_kabel(NN_kabel nn_kabel) {
        this.nn_kabel = nn_kabel;
    }
}
