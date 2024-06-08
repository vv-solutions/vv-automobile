package dk.vv.automobile.errorhandling;

public class ChatException extends Exception{
    public ChatException(String msg) {
        super(msg);
    }
    public ChatException() {
        super("The chatbot was unable to asnwer");
    }
}
