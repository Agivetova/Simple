package kg.gulnaz.service;

public class StockWithIPOExistsException extends RuntimeException {
    public StockWithIPOExistsException(String message) {
        super(message);
    }
}
