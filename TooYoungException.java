public class TooYoungException extends ArithmeticException
{
    public TooYoungException()
    {
        super("You must be over 18 to register");
    }
}
