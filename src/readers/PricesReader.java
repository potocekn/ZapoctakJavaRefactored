package readers;

import dataClasses.PricesData;
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
     * @return PricesData
     * @throws IOException when there is trouble when creating reader.
     * @throws WrongDataFormatException when data are not as expected format
     * For more information about return type, please see:
     * @see PricesData*/
    public PricesData readPrices() throws IOException, WrongDataFormatException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            Map<String, Integer> af1Map = new HashMap<>();
            Map<String, Integer> af4Map = new HashMap<>();
            Map<String, Integer> af6Map = new HashMap<>();

            PricesData result = new PricesData();

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
            result.setAf1tree(af1Map);
            result.setAf4tree(af4Map);
            result.setAf6tree(af6Map);

            return result;
        }
    }
}
