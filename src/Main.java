import calculations.Calculator;
import exceptions.InvalidDirectionException;
import exceptions.MissingDataException;
import exceptions.WrongDataFormatException;
import writer.ExcelWriter;

import java.io.IOException;

public class Main {

    public static void  main(String[] args) {
        Helper helper = new Helper();
        String inputPath;
        String outputPath;
        if (args == null)
        {
            System.out.println("No path to files given.");
            System.out.println("Ending the program ...");
            return;
        }
        else
        {
            if (args.length == 1)
            {
                inputPath = args[0];
                outputPath = inputPath;
            }
            else if (args.length == 2)
            {
                inputPath = args[0];
                outputPath = args[1];
            }
            else
            {
                System.out.println("Wrong number of parameters.");
                System.out.println("Ending the program ...");
                return;
            }
        }

        //checking if all needed files exist
        String[] files = {"asim.csv","opex.csv","otherData.csv","pomerASIM.csv","prices.csv"};
        boolean valid = helper.filesExist(inputPath,files);
        if (!valid)
        {
            System.out.println("Missing files in the directory.");
            System.out.println("Ending the program ...");
            return;
        }

        //reading input data from files
        Calculator calculator;
        try
        {
            calculator = helper.readInput(inputPath);

        }
        catch (WrongDataFormatException e)
        {
            System.out.println(e.getMessage());
            return;
        }

        if (!calculator.isValid())
        {
            System.out.println("Invalid data.");
            return;
        }

        //filling data
        try
        {
            calculator.createTrees();
            calculator.fillData();
            calculator.calculateResultItems();
        }
        catch (MissingDataException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        catch (InvalidDirectionException e)
        {
            System.out.println(e.getMessage());
            return;
        }


        //creating output excel file
        ExcelWriter writer = new ExcelWriter(calculator);
        writer.write(outputPath);

    }
}
