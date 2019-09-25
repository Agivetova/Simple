package kg.gulnaz.service;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {
    private final static Logger logger = LoggerFactory.getLogger(PasswordEncoderImpl.class);
    private MessageDigest md = null;

    public PasswordEncoderImpl() {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Could not find algorith MD5");
        }
    }

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] encodedBytes = md.digest(rawPassword.toString().getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte encodedByte : encodedBytes) {
            sb.append(Integer.toString((encodedByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword.isEmpty()) {
            return false;
        }
        return StringUtils.equals(encodedPassword, encode(rawPassword));
    }
}
