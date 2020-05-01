package readers;

import dataClasses.AsimData;
import dataClasses.Directions;
import exceptions.WrongDataFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/** This class is used for reading input ratio data from given file.*/
public class RatioReader extends AbstractReader
{
    private int SIMULATION_YEARS;

    public RatioReader(String fileName, int numberYears)
    {
        this.fileName = fileName;
        this.SIMULATION_YEARS = numberYears;
    }

    /**This method is used for reading input ratio data.
     * @return AsimData
     * @throws IOException when there is trouble when creating reader
     * @throws WrongDataFormatException when there is some wrong format of data in input files
     * For more information about return type please see:
     * @see AsimData*/
    public AsimData readASIMRatios() throws IOException, WrongDataFormatException {

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            AsimData result = new AsimData();

            String key = "";
            double[] ratios = new double[SIMULATION_YEARS];
            String mainKey = "";
            Directions dir = Directions.def;

            try
            {
                while(!onlySemicolonsOnLine(line)) //while not empty csv line
                {
                    String[] lineArr = line.split(";",0); //should remove empty entries

                    if (lineArr.length == 1) //new key
                    {
                        if (!mainKey.isEmpty()) //invalid key -> invalid table format
                        {
                            result.getFinalData().put(dir,result.getDirectionsData());
                        }
                        mainKey = lineArr[0];

                        if (mainKey.equals("AF1"))
                        {
                            dir = Directions.AF1;
                        }
                        else if (mainKey.equals("AF4_AF1"))
                        {
                            dir = Directions.AF4_AF1;
                        }
                        else if (mainKey.equals("AF4"))
                        {
                            dir = Directions.AF4;
                        }
                        else
                        {
                            throw new WrongDataFormatException("Wrong key in ratios file on line"+line);
                        }
                        result.setDirectionsData(new HashMap<>());
                    }
                    else if (lineArr.length == 2) // constant ratio;
                    {
                        key = lineArr[0];
                        double val;
                        try{
                            val = Double.parseDouble(lineArr[1]);
                            ratios = new double[SIMULATION_YEARS];
                            for (int i = 0; i < ratios.length; i++)
                            {
                                ratios[i] = val;
                            }
                            result.getDirectionsData().put(key,ratios);
                        }
                        catch (NumberFormatException e)
                        {
                            throw new WrongDataFormatException("Wrong format of numbers on line" + line);
                        }
                    }
                    else //concrete ratios for each year
                    {
                        key = lineArr[0];

                        try
                        {
                            ratios = new double[SIMULATION_YEARS];
                            for (int i = 1; i < lineArr.length; i++)
                            {
                                ratios[i-1] = Double.parseDouble(lineArr[i]);
                            }
                            result.getDirectionsData().put(key,ratios);
                        }
                        catch (NumberFormatException e)
                        {
                            throw new WrongDataFormatException("Wrong format of numbers on line" + line);
                        }
                    }
                    line = reader.readLine();
                }//end of while
            }
            catch (NullPointerException e)
            {
                throw new WrongDataFormatException("Missing empty line at the bottom of pomerASIM file.");
            }
            catch (IndexOutOfBoundsException e)
            {
                throw new WrongDataFormatException("Wrong format of empty line in pomerASIM file.");
            }


            result.getDirectionsData().put(key,ratios);
            result.getFinalData().put(dir,result.getDirectionsData());
            return result;
        }//end of try
    }
}
