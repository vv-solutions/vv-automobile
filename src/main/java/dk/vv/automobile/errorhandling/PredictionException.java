package dk.vv.automobile.errorhandling;

public class PredictionException extends Exception{
    public PredictionException(String msg) {
        super(msg);
    }
    public PredictionException() {
        super("There was an error prediction has failed");
    }
}
