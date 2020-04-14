package treeParts.ds.vvn_nn;

import exceptions.MissingDataException;
import treeParts.ds.Asset;
import treeParts.ds.MiddleLevel;
import treeParts.ds.Names;

import java.util.Map;

/**This class represents mid-level in the tree hierarchy. To this class belong 6 assets HV_trafo, HV_field, PROTECTIONS, OWN_CONSUMPTION,
 * BUILDING, MV_field.*/
public class ES extends MiddleLevel {

    public ES( int numberOfYears)
    {
        this.assets.put(Names.HV_TRAFO.getName(), new Asset(Names.HV_TRAFO.getPrintName(), numberOfYears, "ks"));
        this.assets.put(Names.HV_FIELD.getName(), new Asset(Names.HV_FIELD.getPrintName(), numberOfYears, "ks"));
        this.assets.put(Names.PROTECTIONS.getName(), new Asset(Names.PROTECTIONS.getPrintName(), numberOfYears, "ks"));
        this.assets.put(Names.OWN_CONSUMPTION.getName(), new Asset(Names.OWN_CONSUMPTION.getPrintName(), numberOfYears, "ks"));
        this.assets.put(Names.BUILDING.getName(), new Asset(Names.BUILDING.getPrintName(), numberOfYears, "ks"));
        this.assets.put(Names.MV_FIELD.getName(), new Asset(Names.MV_FIELD.getPrintName(), numberOfYears, "ks"));
    }

}
