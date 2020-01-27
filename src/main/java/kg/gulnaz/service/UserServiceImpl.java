package kg.gulnaz.service;

import kg.gulnaz.jpa.entity.RoleEntity;
import kg.gulnaz.jpa.entity.UserEntity;
import kg.gulnaz.jpa.repository.RoleRepository;
import kg.gulnaz.jpa.repository.UserRepository;
import kg.gulnaz.model.Role;
import kg.gulnaz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.nio.CharBuffer;
import java.util.*;
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
    @Transactional
    public User register(String login, char[] password) {
        String encodedPassword = passwordEncoder.encode(CharBuffer.wrap(password));
        UserEntity newUser = new UserEntity(login, encodedPassword.toCharArray());
        RoleEntity defaultUserRole = roleDao.getDefaultUserRole();
        newUser.setRoles(Arrays.asList(defaultUserRole));
        try {
            UserEntity entity = userDao.save(newUser);
            return dto(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new UserWithLoginAlreadyExists(String.format("User with %s already exists", login));
        }
    }


    private User dto(UserEntity entity) {
        List<Role> roles = toDto(entity.getRoles());
        return new User(entity.getUsername(), roles);
    }

    private List<Role> toDto(List<RoleEntity> roles) {
        return roles.stream().map(e -> new Role(e.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with login %s not found", username));
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (RoleEntity role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        String password = new String(user.getPassword());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), password, grantedAuthorities);
    }
}
