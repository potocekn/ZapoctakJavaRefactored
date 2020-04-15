package exceptions;

/**This exception is thrown when there is wrong direction used.
 * For example, when trying to get data from direction that does not contain required type of data.*/
public class InvalidDirectionException extends Exception{
    public InvalidDirectionException(String errorMessage)
    {
        super(errorMessage);
    }
}