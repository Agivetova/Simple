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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
