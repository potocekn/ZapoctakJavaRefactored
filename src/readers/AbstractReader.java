package readers;

/**This is abstract class that contains necessary methods and variables for reading input data.
 * This class is parent class for all readers used in this program.
 * String fileName represents name of the file from which we want to read data.
 * Method onlySemicolonsOnLine returns boolean if the given string contains only semicolons (given string is empty csv line).
 * Method isNumber returns boolean if given string can be parsed into number. */
public abstract class AbstractReader {
    String fileName;

    /**This method determines if given string is filled only with semicolons (if it is an empty csv line in table).
     * @param line is the string that we would like to be tested.
     * @return boolean*/
    protected boolean onlySemicolonsOnLine(String line)
    {
        boolean result = true;
        for (int i = 0; i < line.length(); i++)
        {
            if (line.charAt(i)!= ';')
            {
                result = false;
                break;
            }
        }
        return result;
    }

    /**This method determines if given string can be parsed into integer.
     * @param numberString is the string that we would like to test.
     * @return boolean*/
    boolean isNumber(String numberString)
    {
        try
        {
            int number = Integer.parseInt(numberString);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
