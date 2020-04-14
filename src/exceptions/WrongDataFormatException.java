package exceptions;

/**This exception is thrown when there are data in wrong format somewhere in input files.*/
public class WrongDataFormatException extends Exception{
    public WrongDataFormatException(String errorMessage)
    {
        super(errorMessage);
    }
}
