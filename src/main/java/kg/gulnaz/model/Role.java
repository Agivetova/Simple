package kg.gulnaz.model;

import lombok.Getter;

@Getter
public class Role {
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
