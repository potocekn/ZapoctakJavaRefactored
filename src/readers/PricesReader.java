package readers;

import exceptions.WrongDataFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**This class contains all methods needed for reading data from prices.csv file*/
public class PricesReader extends AbstractReader{


    public PricesReader(String fileName)
    {

        this.fileName = fileName;
    }

    /**This method reads input data for prices from given file and returns them.
     * @throws IOException when there is trouble when creating reader.*/
    public Map<Directions, Map<String, Integer>> readPrices() throws IOException, WrongDataFormatException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            Map<Directions, Map<String, Integer>> finalPrices = new HashMap<>();
            Map<String, Integer> af1Map = new HashMap<>();
            Map<String, Integer> af4Map = new HashMap<>();
            Map<String, Integer> af6Map = new HashMap<>();

            String line = reader.readLine(); //we do not need first line, it's just header

            while ((line = reader.readLine())!= null)
            {
                String[] lineArr = line.split(";");
                int[] values = new int[3]; //we work with only 3 directions for investment
                String key = lineArr[0];
                for (int i = 1; i < lineArr.length; i++)
                {
                    if ((lineArr[i] == null) || (lineArr[i].equals("")) )// no given value -> 0
                    {
                        values[i-1] = 0;
                    }
                    else
                    {
                        //System.out.println("LineArr value: "+lineArr[i]);
                        try
                        {
                            values[i-1] = Integer.parseInt(lineArr[i]);
                        }
                        catch (NumberFormatException e)
                        {
                            throw new WrongDataFormatException("Wrong data format in Prices data file: "+key+" "+"column "+i);
                        }

                    }
                }//end of for loop

                //add values to directions maps
                af1Map.put(key,values[0]);
                af4Map.put(key,values[1]);
                af6Map.put(key,values[2]);
            }//end of while

            //add directions maps to final map with correct direction key
            finalPrices.put(Directions.AF1, af1Map);
            finalPrices.put(Directions.AF4, af4Map);
            finalPrices.put(Directions.AF6, af6Map);

            return finalPrices;
        }
    }
}
