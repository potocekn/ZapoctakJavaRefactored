package treeParts.ds.nn;

import treeParts.ds.Asset;
import treeParts.ds.MiddleLevel;
import treeParts.ds.Names;

import java.util.Map;

/**This class represents mid-level in the tree hierarchy. To this class belongs one asset (NN_izolovane).*/
public class NN_kabel extends MiddleLevel {

    public NN_kabel( int numberOfYears)
    {
        this.assets.put(Names.NN_IZOLOVANE.getName(), new Asset(Names.NN_IZOLOVANE.getPrintName(), numberOfYears, "km"));
    }

}
