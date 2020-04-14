package treeParts.ds.vvn;

import treeParts.ds.Asset;
import treeParts.ds.MiddleLevel;
import treeParts.ds.Names;

import java.util.Map;

/**This class represents mid-level in the tree hierarchy. To this class belongs 1 asset VVN_cable.*/
public class VVN_kabel  extends MiddleLevel {

    public VVN_kabel( int numberOfYears)
    {
        this.assets.put(Names.VVN_CABLE.getName(), new Asset(Names.VVN_CABLE.getPrintName(), numberOfYears, "km"));
    }
}
