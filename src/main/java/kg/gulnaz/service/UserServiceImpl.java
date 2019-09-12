package kg.gulnaz.service;

import kg.gulnaz.jpa.entity.RoleEntity;
import kg.gulnaz.jpa.entity.UserEntity;
import kg.gulnaz.jpa.repository.RoleRepository;
import kg.gulnaz.jpa.repository.UserRepository;
import kg.gulnaz.model.Role;
import kg.gulnaz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private UserRepository userDao;
    private RoleRepository roleDao;

    @Autowired
    public UserServiceImpl(UserRepository userDao, RoleRepository roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(String login, char[] password) {
        CharSequence encodedPassword = passwordEncoder.encode(password);
        UserEntity newUser = new UserEntity(login, encodedPassword.toString().toCharArray());
        RoleEntity defaultUserRole = roleDao.getDefaultUserRole();
        newUser.setRoles(Arrays.asList(defaultUserRole));
        UserEntity entity = userDao.save(newUser);
        return dto(entity);
    }

    private User dto(UserEntity entity) {
        List<Role> roles = toDto(entity.getRoles());
        return new User(entity.getUsername(), roles);
    }

    private List<Role> toDto(List<RoleEntity> roles) {
        return roles.stream().map(e -> new Role(e.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO
        return null;
    }
}
