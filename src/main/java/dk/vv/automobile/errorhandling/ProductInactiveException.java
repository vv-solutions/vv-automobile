package dk.vv.automobile.errorhandling;

public class ProductInactiveException extends Exception{
    public ProductInactiveException(String msg) {
        super(msg);
    }
    public ProductInactiveException() {
        super("Product is not active");
    }
}
