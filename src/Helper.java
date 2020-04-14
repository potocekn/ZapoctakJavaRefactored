import calculations.Calculator;
import exceptions.WrongDataFormatException;
import readers.*;

import java.io.File;
import java.io.IOException;

/**This class contains all functions that will help with creating and checking data for further calculations*/
public class Helper {

    public boolean filesExist(String dirPath, String[] files)
    {
        boolean result = true;

        for (int i = 0; i < files.length; i++)
        {
            File file = new File(dirPath+"/"+files[i]);
            if (!file.exists()) {
                result = false;
                System.out.println("Missing "+files[i]+" file.");
            }
        }

        return result;
    }

    /**This method reads input data from all necessary files and will return Calculator that will contain this data.
     * @return Calculator
     * @see Calculator for more information about Calculator class.*/
    public Calculator readInput(String dirPath) throws WrongDataFormatException {
        try
        {
            Calculator calculator = new Calculator();

            ASIMReader asimReader = new ASIMReader(dirPath+"/asim.csv");
            calculator.setAsimData(asimReader.readASIMFile());

            PricesReader pricesReader = new PricesReader(dirPath+"/prices.csv");
            calculator.setPrices(pricesReader.readPrices());

            AdditionalDataReader additionalDataReader = new AdditionalDataReader(dirPath+"/otherData.csv");
            calculator.setAdditionalData(additionalDataReader.readAdditionalData());

            OpexReader opexReader = new OpexReader(dirPath+"/opex.csv");
            calculator.setOpexData(opexReader.readOpexData());

            RatioReader ratioReader = new RatioReader(dirPath+"/pomerASIM.csv");
            calculator.setRatioData(ratioReader.readASIMRatios());

            return calculator;
        }
        catch (IOException e)
        {
            System.out.println("Problem while reading input");
            return null;
        }
    }//end of readInput method



}
