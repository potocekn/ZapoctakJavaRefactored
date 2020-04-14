package treeParts.ds.vn;

import treeParts.ds.Asset;
import treeParts.ds.MiddleLevel;
import treeParts.ds.Names;

import java.util.Map;

/**This class represents mid-level in the tree hierarchy. To this class belong 2 assets VN_olej, VN_plast.*/
public class VN_kabel extends MiddleLevel {

    public VN_kabel( int numberOfYears)
    {
        this.assets.put(Names.VN_OLEJ.getName(), new Asset(Names.VN_OLEJ.getPrintName(), numberOfYears, "km"));
        this.assets.put(Names.VN_PLAST.getName(), new Asset(Names.VN_PLAST.getPrintName(), numberOfYears, "km"));
    }

}
