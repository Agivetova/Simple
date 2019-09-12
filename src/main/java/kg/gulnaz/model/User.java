package kg.gulnaz.model;

import lombok.Getter;

import java.util.List;

@Getter
public class User {
    private String username;
    private List<Role> roles;

    public User(String username, List<Role> roles) {
        this.username = username;
        this.roles = roles;
    }
}
