package dataClasses;

/**This class represents data that are read from additionalData.csv file.
 * DEFAULT_YEARS is number of years that is used as length of simulation if there are no information about length of simulation in file.
 * beginning and ending year are integers that are used for calculation of the length of the simulation.
 * inflation is array of doubles that contains values of inflation for each year of simulation.*/
public class AdditionalData {
    final int DEFAULT_YEARS = 10;
    int beginningYear;
    int endingYear;
    double[] inflation;

    public int getBeginningYear() {
        return beginningYear;
    }

    public void setBeginningYear(int beginningYear) {
        this.beginningYear = beginningYear;
    }

    public int getEndingYear() {
        return endingYear;
    }

    public void setEndingYear(int endingYear) {
        this.endingYear = endingYear;
    }

    public double[] getinflation() {
        return inflation;
    }

    public void setInflation(double[] inflation) {
        this.inflation = inflation;
    }

    /**This method calculates length of simulation.
     * When there are no information about length of simulation in the input file or years are below 1900, method returns default value.
     * If we have correct data for simulation result is: endingYear - beginningYear +1.*/
    public int getNumberOfYears() {

        if ((beginningYear < 1900)||(endingYear<1950))
        {
            return DEFAULT_YEARS;
        }
        return endingYear - beginningYear + 1;
    }
}
