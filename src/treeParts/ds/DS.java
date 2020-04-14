package treeParts.ds;

import exceptions.MissingDataException;
import treeParts.ds.nn.NN;
import treeParts.ds.vn.VN;
import treeParts.ds.vn_nn.VN_NN;
import treeParts.ds.vvn.VVN;
import treeParts.ds.vvn_nn.VVN_NN;
/**This class represents whole distribution system. It contains 6 high levels: VVN, VVN_NN, VN, VN_NN, NN, Netz*/
public class DS {
    private VVN vvn;
    private VVN_NN vvn_nn;
    private VN vn;
    private VN_NN vn_nn;
    private NN nn;
    private Netz netz;

    public DS(int numberOfYears)
    {
        this.vvn = new VVN(numberOfYears);
        this.vvn_nn = new VVN_NN(numberOfYears);
        this.vn = new VN(numberOfYears);
        this.vn_nn = new VN_NN(numberOfYears);
        this.nn = new NN(numberOfYears);
        this.netz = new Netz(numberOfYears);
    }

    /**This method calculates final amounts for each child.*/
    public void calculate(double inflation[]) throws MissingDataException {
        vvn.calculate(inflation);
        vvn_nn.calculate(inflation);
        vn.calculate(inflation);
        vn_nn.calculate(inflation);
        nn.calculate(inflation);
    }

    public VVN getVvn() {
        return vvn;
    }

    public VVN_NN getVvn_nn() {
        return vvn_nn;
    }

    public VN getVn() {
        return vn;
    }

    public void setVn(VN vn) {
        this.vn = vn;
    }

    public VN_NN getVn_nn() {
        return vn_nn;
    }

    public NN getNn() {
        return nn;
    }

    public void setNn(NN nn) {
        this.nn = nn;
    }

    public Netz getNetz() {
        return netz;
    }
}
