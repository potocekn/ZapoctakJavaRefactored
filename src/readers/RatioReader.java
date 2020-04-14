package readers;

import exceptions.WrongDataFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** This class is used for reading input ratio data from given file.*/
public class RatioReader extends AbstractReader
{
    final int SIMULATION_YEARS = 11;

    public RatioReader(String fileName)
    {
        this.fileName = fileName;
    }

    /**This method is used for reading input ratio data
     * @throws IOException when there is trouble when creating reader*/
    public Map<Directions,Map<String,double[]>> readASIMRatios() throws IOException, WrongDataFormatException {

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            Map<Directions,Map<String,double[]>> result = new HashMap<>();
            Map<String,double[]> ratio = new HashMap<>();
            String key = "";
            double[] ratios = new double[SIMULATION_YEARS]; //we plan for 11 years
            String mainKey = "";
            Directions dir = Directions.def;

            while(!onlySemicolonsOnLine(line)) //while not empty csv line
            {
                String[] lineArr = line.split(";",0); //should remove empty entries

                if (lineArr.length == 1) //new key
                {
                    if (!mainKey.isEmpty()) //invalid key -> invalid table format
                    {
                        result.put(dir,ratio);
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

                    ratio = new HashMap<>();
                }
                else if (lineArr.length == 2) // constant ratio;
                {
                    key = lineArr[0];
                    double val;
                    try{
                        val = Double.parseDouble(lineArr[1]);
                        ratios = new double[SIMULATION_YEARS];
                        for (int i = 0; i < 11; i++)
                        {
                            ratios[i] = val;
                        }
                        ratio.put(key,ratios);
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
                        ratio.put(key,ratios);
                    }
                    catch (NumberFormatException e)
                    {
                        throw new WrongDataFormatException("Wrong format of numbers on line" + line);
                    }
                }
                line = reader.readLine();
            }//end of while
            ratio.put(key,ratios);
            result.put(dir,ratio);
            return result;
        }//end of try
    }
}
