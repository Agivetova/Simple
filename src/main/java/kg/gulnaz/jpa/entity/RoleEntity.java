package kg.gulnaz.jpa.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @Column(name = "name", nullable = false)
    @Getter
    private String name;

    public RoleEntity() {
        //default constructor
    }

    public RoleEntity(String name) {
        this.name = name;
    }
}
