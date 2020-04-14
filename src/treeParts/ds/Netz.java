package treeParts.ds;

/**This class contains planned finances for Netz part of tree.*/
public class Netz {
    double[] plannedFinance;

    public Netz(int numberOfYears)
    {
        this.plannedFinance = new double[numberOfYears];
    }

    public double[] getPlannedFinance() {
        return plannedFinance;
    }

    public void setPlannedFinance(double[] plannedFinance) {
        this.plannedFinance = plannedFinance;
    }
}
