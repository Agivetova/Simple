package kg.gulnaz.service;

public interface PasswordEncoder extends org.springframework.security.crypto.password.PasswordEncoder {
    @Override
    String encode(CharSequence rawPassword);

    @Override
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
