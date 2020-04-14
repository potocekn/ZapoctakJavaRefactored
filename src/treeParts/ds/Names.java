package treeParts.ds;
public enum Names
{
     VVN_CABLE("VVN_cable"),
    VVN_VEDENIE("VVN_vedenie"),
    VVN_STOZIAR("VVN_stoziar"),

      DTS_KIOSK("DTS_kiosk"),
      DTS_MUROVANA("DTS_murovana"),
      DTS_STOZIAROVA("DTS_stoziarova"),


      VN_OLEJ("VN_olej"),
      VN_PLAST("VN_plast"),

      VN_OCEL("VN_ocel"),
      VN_DREVO("VN_drevo"),
      VN_BETON("VN_beton"),

      NN_IZOLOVANE("NN_izolovane"),
      NN_VEDENIE("NN_vedenie"),

      HV_TRAFO("HV_trafo"),
      HV_FIELD("HV_field"),
      PROTECTIONS("PROTECTIONS"),
      OWN_CONSUMPTION("OWN_CONSUMPTION"),
      BUILDING("BUILDING"),
      MV_FIELD("MV_field");


    private String name;

    Names(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getPrintName() {
        return name.replaceAll("_", " ");
    }
}