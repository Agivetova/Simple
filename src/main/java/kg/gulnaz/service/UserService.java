package kg.gulnaz.service;

import kg.gulnaz.model.User;

public interface UserService {
    User register(String login, char[] password);
}
