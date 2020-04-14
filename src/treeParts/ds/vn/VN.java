package treeParts.ds.vn;

import exceptions.MissingDataException;

/**This class represents VN level in tree hierarchy. It contains 2 mid-levels VN_vzduch and VN_kabel*/
public class VN {
    private VN_vzduch vn_vzduch;
    private VN_kabel vn_kabel;

    public VN(int numberOfYears)
    {
        this.vn_vzduch = new VN_vzduch(numberOfYears);
        this.vn_kabel = new VN_kabel(numberOfYears);
    }

    /**This method calculates final amounts for each child.*/
    public void calculate(double inflation[]) throws MissingDataException {
        try
        {
            vn_kabel.calculate(inflation);
            vn_vzduch.calculate(inflation);
        }
        catch (NullPointerException e)
        {
            throw new MissingDataException("Missing data for VN");
        }

    }

    public VN_vzduch getVn_vzduch() {
        return vn_vzduch;
    }

    public void setVn_vzduch(VN_vzduch vn_vzduch) {
        this.vn_vzduch = vn_vzduch;
    }

    public VN_kabel getVn_kabel() {
        return vn_kabel;
    }
}
