package kg.gulnaz.service;

import kg.gulnaz.model.User;

public interface UserService {
    User createUser(String login, char[] password);
}
