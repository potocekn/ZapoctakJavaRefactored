package readers;

import exceptions.WrongDataFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**This class reads all additional data from given file, like: inflation, beginning year and ending year*/
public class AdditionalDataReader extends AbstractReader{

    public AdditionalDataReader(String fileName)
    {
        this.fileName = fileName;
    }

    /**This method reads data from file and returns them.
     * @throws IOException if there is trouble with creating reader.*/
    public Map<String,double[]> readAdditionalData() throws IOException, WrongDataFormatException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String beginningYearLine = reader.readLine();
            String endingYearLine = reader.readLine();
            String inflationLine = reader.readLine();

            int beginningYear = 0;
            int endingYear = 0;
            double[] inflationYearly;

            String[] beginningYearArr = beginningYearLine.split(";",0); //removes empty entries
            if (isNumber(beginningYearArr[1]))
            {
                beginningYear = Integer.parseInt(beginningYearArr[1]);
            }
            else
            {
                throw new WrongDataFormatException("Wrong beginning year format in otherData file.");
            }

            String[] endingYearArr = endingYearLine.split(";",0); //removes empty entries
            if (isNumber(endingYearArr[1]))
            {
                endingYear = Integer.parseInt(endingYearArr[1]);
            }
            else
            {
                throw new WrongDataFormatException("Wrong ending year format in otherData file.");
            }

            if ((beginningYear != 0) && (endingYear != 0)) //we have correct years
            {
                inflationYearly = new double[endingYear - beginningYear + 1];
                inflationLine = inflationLine.replaceAll(",","\\."); //replacing all , for . so that we can try to make double values
                String[] inflationYearlyStringArr = inflationLine.split(";",0);
                boolean success = true;

                for (int i = 1; i < inflationYearlyStringArr.length; i++)
                {
                    try
                    {
                        double num = Double.parseDouble(inflationYearlyStringArr[i]);
                        inflationYearly[i-1] = num;
                    }
                    catch (NumberFormatException e)
                    {
                        success = false;
                        break;
                    }
                }//end of for

                if (success)
                {
                    Map<String,double[]> result = new HashMap<>();
                    String key = beginningYear + "_" +endingYear;
                    result.put(key,inflationYearly);
                    return result;
                }
                else
                {
                    return null;
                }
            }//end of if
            return null; //if not correct years then return null
        }//end of try
    }//end of method
}
