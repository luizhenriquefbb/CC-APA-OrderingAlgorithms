package exceptions;

/**
 *
 * @author lhfba
 */
public class NegativeNumberException extends Exception {
    public NegativeNumberException(){
        super("Negative number in file");
    }
    
    public NegativeNumberException(String message){
        super(message);
    }
}
