package treeParts.ds.vn;

import treeParts.ds.Asset;
import treeParts.ds.MiddleLevel;
import treeParts.ds.Names;

import java.util.Map;

/**This class represents mid-level in the tree hierarchy. To this class belong 3 assets VN_ocel, VN_drevo, VN_beton.*/
public class VN_vzduch extends MiddleLevel {

    public VN_vzduch( int numberOfYears)
    {
        this.assets.put(Names.VN_OCEL.getName(), new Asset(Names.VN_OCEL.getPrintName(), numberOfYears, "km"));
        this.assets.put(Names.VN_DREVO.getName(), new Asset(Names.VN_DREVO.getPrintName(), numberOfYears, "km"));
        this.assets.put(Names.VN_BETON.getName(), new Asset(Names.VN_BETON.getPrintName(), numberOfYears, "km"));
    }

}

