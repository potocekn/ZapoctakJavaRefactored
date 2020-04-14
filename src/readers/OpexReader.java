package readers;

import exceptions.WrongDataFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**This class reads opex data from given file.*/
public class OpexReader extends AbstractReader{

    public OpexReader(String fileName)
    {
        this.fileName = fileName;
    }

    /**This method reads opex data and returns them.
     * @throws IOException when there is trouble while creating reader.*/
    public List<Map<String, Double >> readOpexData() throws IOException, WrongDataFormatException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine(); // we don't care about first line
            String currentKey = "";
            Map<String,Double> money = new HashMap<>();
            Map<String, Double> ratio = new HashMap<>();
            line = reader.readLine();
            while(!onlySemicolonsOnLine(line))
            {
                if (line.contains(":"))
                {
                    if(currentKey.isEmpty())
                    {
                        throw new WrongDataFormatException("Wrong format of input, no key detected in opex file.");
                    }

                    if (line.startsWith(currentKey))
                    {
                        String[] lineArr = line.split(":",0);
                        lineArr[1] = lineArr[1].substring(0,lineArr[1].length()-1);
                        lineArr[1] = lineArr[1].replaceAll(",","\\.");
                        String where = lineArr[0];
                        try {
                            double r = Double.parseDouble(lineArr[1]);
                            ratio.put(where,r);
                        }
                        catch (NumberFormatException e)
                        {
                            throw new WrongDataFormatException("Wrong format of number in opex file on line : "+line);
                        }
                    }
                    else
                    {
                        throw new WrongDataFormatException("Wrong format of line in opex file, line does not begin with previously given key: "+line);
                    }

                }
                else
                {
                    String[] lineArr = line.split(";",0);
                    String key = lineArr[0];
                    currentKey = key;
                    try {
                        double m = Double.parseDouble(lineArr[1]);
                        money.put(key,m);
                    }
                    catch (NumberFormatException e)
                    {
                        throw new WrongDataFormatException("Wrong format of number in opex file on line : "+line);
                    }
                }
                line = reader.readLine();
            }// end of while
            ArrayList<Map<String, Double>> result = new ArrayList<>();
            result.add(money);
            result.add(ratio);
            return result;
        }//end of try
    }//end of method read opex
}
