package exceptions;

/**This exception is thrown when there are some missing data in the input files.*/
public class MissingDataException extends Exception{
    public MissingDataException(String errorMessage)
    {
        super(errorMessage);
    }
}
