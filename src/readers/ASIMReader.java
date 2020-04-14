package readers;

import dataClasses.AsimData;
import exceptions.WrongDataFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** This class is used for reading ASIM data from given file.*/
public class ASIMReader extends AbstractReader {

    final int SIMULATION_YEARS = 11;
    public ASIMReader(String fileName)
    {
        this.fileName = fileName;
    }


    /**This method reads input data from given file and returns them.
     * @throws IOException when trouble with creating reader.*/
    public AsimData readASIMFile() throws IOException, WrongDataFormatException {

        AsimData result = new AsimData();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            Directions dir = Directions.def;
            Map<Directions,Map<String, double[]>> finalASIMData = new HashMap<>();
            Map<String, double[]> directionLines = new HashMap<>();
            while (!onlySemicolonsOnLine(line))
            {
                String[] lineArr = line.split(";",0); //should remove empty entries in array
                if (lineArr.length == 1) //line that specifies direction for investment
                {
                    switch (lineArr[0]) //specifying the direction that will be the key
                    {
                        case "AF1": dir = Directions.AF1;
                        break;
                        case "AF2":
                            result.getFinalData().put(dir,result.getDirectionsData());
                            dir = Directions.AF2;
                            result.setDirectionsData(new HashMap<>());
                            break;
                        case "AF3":
                            result.getFinalData().put(dir,result.getDirectionsData());
                            dir = Directions.AF3;
                            result.setDirectionsData(new HashMap<>());
                        break;
                        case "AF4":
                            result.getFinalData().put(dir,result.getDirectionsData());
                            dir = Directions.AF4;
                            result.setDirectionsData(new HashMap<>());
                        break;
                        case "AF5":
                            result.getFinalData().put(dir,result.getDirectionsData());
                            dir = Directions.AF5;
                            result.setDirectionsData(new HashMap<>());
                        break;
                        case "AF6":
                            result.getFinalData().put(dir,result.getDirectionsData());
                            dir = Directions.AF6;
                            result.setDirectionsData(new HashMap<>());
                        break;
                        default:
                            dir = Directions.def;
                            System.out.println("Default in directions switch, sth went wrong");
                    }//end of switch
                }//end of if
                else //we have data for previously given direction
                {
                    String key = lineArr[0];
                    double[] values = new double[SIMULATION_YEARS]; //we do simulation for 11 years
                    for (int i = 1; i < lineArr.length;i++)
                    {
                        //System.out.println(lineArr[i]);
                        try
                        {
                            values[i-1] = Integer.parseInt(lineArr[i]);
                        }
                        catch (NumberFormatException e)
                        {
                            throw new WrongDataFormatException("Wrong data format in ASIM data file: "+key+" "+"column "+i);
                        }

                    }
                    result.getDirectionsData().put(key,values);
                }
                line = reader.readLine();
            }//end of while
            result.getFinalData().put(dir,result.getDirectionsData()); //adds AF6 data
            return result;
        }
    }
}
