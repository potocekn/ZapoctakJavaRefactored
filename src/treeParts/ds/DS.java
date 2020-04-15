package treeParts.ds;

import exceptions.MissingDataException;
import treeParts.ds.nn.NN;
import treeParts.ds.vn.VN;
import treeParts.ds.vn_nn.VN_NN;
import treeParts.ds.vvn.VVN;
import treeParts.ds.vvn_vn.VVN_VN;

/**This class represents whole distribution system. It contains 6 high levels: VVN, VVN_VN, VN, VN_NN, NN, Netz*/
public class DS {
    private VVN vvn;
    private VVN_VN vvn_vn;
    private VN vn;
    private VN_NN vn_nn;
    private NN nn;
    private Netz netz;

    public DS(int numberOfYears)
    {
        this.vvn = new VVN(numberOfYears);
        this.vvn_vn = new VVN_VN(numberOfYears);
        this.vn = new VN(numberOfYears);
        this.vn_nn = new VN_NN(numberOfYears);
        this.nn = new NN(numberOfYears);
        this.netz = new Netz(numberOfYears);
    }

    /**This method calculates final amounts for each child.
     * @param inflation represents array of inflation values for each year of simulation.
     * @throws MissingDataException when there are some necessary data missing when calculating final results.*/
    public void calculate(double inflation[]) throws MissingDataException {
        vvn.calculate(inflation);
        vvn_vn.calculate(inflation);
        vn.calculate(inflation);
        vn_nn.calculate(inflation);
        nn.calculate(inflation);
    }

    public VVN getVvn() {
        return vvn;
    }

    public VVN_VN getVvn_vn() {
        return vvn_vn;
    }

    public VN getVn() {
        return vn;
    }


    public VN_NN getVn_nn() {
        return vn_nn;
    }

    public NN getNn() {
        return nn;
    }

    public Netz getNetz() {
        return netz;
    }
}
