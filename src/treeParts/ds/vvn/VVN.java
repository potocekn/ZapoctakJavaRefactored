package treeParts.ds.vvn;

import exceptions.MissingDataException;

/**This class represents VVN level in tree hierarchy. It contains 2 mid-levels VVN_vzduch, VVN_kabel.*/
public class VVN {
    private VVN_vzduch vvn_vzduch;
    private VVN_kabel vvn_kabel;

    public VVN(int numberOfYears)
    {
        this.vvn_vzduch = new VVN_vzduch(numberOfYears);
        this.vvn_kabel = new VVN_kabel(numberOfYears);
    }

    /**This method calculates final amounts for each child.*/
    public void calculate(double inflation[]) throws MissingDataException {
        try
        {
            vvn_kabel.calculate(inflation);
            vvn_vzduch.calculate(inflation);
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing data for VVN.");
        }

    }

    public VVN_vzduch getVVN_vzduch()
    {
        return this.vvn_vzduch;
    }

    public void setVvn_vzduch(VVN_vzduch vvn_vzduch) {
        this.vvn_vzduch = vvn_vzduch;
    }

    public VVN_kabel getVVN_kabel()
    {
        return this.vvn_kabel;
    }
}
