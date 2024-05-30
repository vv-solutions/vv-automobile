package dk.vv.automobile.errorhandling;

public class ProductQuantityException extends Exception{
    public ProductQuantityException(String msg) {
        super(msg);
    }
    public ProductQuantityException() {
        super("Unable stock of selected product is too low");
    }
}
