package treeParts.ds.nn;

import treeParts.ds.Asset;
import treeParts.ds.MiddleLevel;
import treeParts.ds.Names;

/**This class represents mid-level in the tree hierarchy. To this class belongs one asset (NN_vedenie).*/
public class NN_vzduch extends MiddleLevel{

    public NN_vzduch( int numberOfYears)
    {
        this.assets.put(Names.NN_VEDENIE.getName(), new Asset(Names.NN_VEDENIE.getPrintName(), numberOfYears, "km"));
    }

}
