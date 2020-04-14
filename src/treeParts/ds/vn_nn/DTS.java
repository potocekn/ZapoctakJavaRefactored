package treeParts.ds.vn_nn;

import calculations.utils.CalcUtil;
import exceptions.MissingDataException;
import treeParts.ds.Asset;
import treeParts.ds.MiddleLevel;
import treeParts.ds.Names;

import java.util.HashMap;
import java.util.Map;

/**This class represents mid-level in the tree hierarchy. To this class belong 3 assets DTS_kiosk, DTS_murovana, DTS_stoziarova.*/
public class DTS  extends MiddleLevel {

    public DTS( int numberOfYears)
    {
        this.assets.put(Names.DTS_KIOSK.getName(), new Asset(Names.DTS_KIOSK.getPrintName(), numberOfYears, "ks"));
        this.assets.put(Names.DTS_MUROVANA.getName(), new Asset(Names.DTS_MUROVANA.getPrintName(), numberOfYears, "ks"));
        this.assets.put(Names.DTS_STOZIAROVA.getName(), new Asset(Names.DTS_STOZIAROVA.getPrintName(), numberOfYears, "ks"));

    }


}
