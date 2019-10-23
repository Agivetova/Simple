package kg.gulnaz.jpa.entity;

import kg.gulnaz.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private char[] password;

    @ManyToMany()
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleEntity> roles;

    public UserEntity() {
        //default constructor
    }

    public UserEntity(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity(long id, String username, char[] password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserEntity(long id, String username, char[] password, List<RoleEntity> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
