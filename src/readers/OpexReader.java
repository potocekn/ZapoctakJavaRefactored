package readers;

import dataClasses.OpexData;
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
     * @return OpexData
     * @throws IOException when there is trouble while creating reader.
     * @throws WrongDataFormatException when data are not as expected format
     * For information about return type, please see:
     * @see OpexData*/
    public OpexData readOpexData() throws IOException, WrongDataFormatException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            OpexData result = new OpexData();
            String line = reader.readLine(); // we don't care about first line
            Map<String,Double> money = new HashMap<>();
            line = reader.readLine();
            try
            {
                while(!onlySemicolonsOnLine(line))            {

                    String[] lineArr = line.split(":",0);
                    String key = lineArr[0];
                    try {

                        double m = Double.parseDouble(lineArr[1].replaceAll(";",""));
                        money.put(key,m);
                    }
                    catch (NumberFormatException e)
                    {
                        throw new WrongDataFormatException("Wrong format of number in opex file on line : "+line);
                    }

                    line = reader.readLine();
                }// end of while
            }
            catch (NullPointerException e)
            {
                throw new WrongDataFormatException("Missing empty csv line at the end of opex file.");
            }
            catch (IndexOutOfBoundsException e)
            {
                throw new WrongDataFormatException("Wrong format of empty csv line at the end of opex file.");
            }

            result.setMoney(money);
            return result;
        }//end of try
    }//end of method read opex
}
