package kg.gulnaz.service;

public class UserWithLoginAlreadyExists extends RuntimeException {
    public UserWithLoginAlreadyExists(String message) {
        super(message);
    }
}
