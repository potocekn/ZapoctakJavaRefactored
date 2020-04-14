package treeParts.ds.vvn;

import treeParts.ds.Asset;
import treeParts.ds.MiddleLevel;
import treeParts.ds.Names;

import java.util.Map;

/**This class represents mid-level in the tree hierarchy. To this class belong 2 assets VVN_vedenie, VVN_stoziar.*/
public class VVN_vzduch extends MiddleLevel {

    public VVN_vzduch( int numberOfYears)
    {
        this.assets.put(Names.VVN_VEDENIE.getName(), new Asset(Names.VVN_VEDENIE.getPrintName(), numberOfYears, "km"));
        this.assets.put(Names.VVN_STOZIAR.getName(), new Asset(Names.VVN_STOZIAR.getPrintName(), numberOfYears, "ks"));
    }
}
