package kg.gulnaz.service;

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
    public CharSequence encode(char[] array) {
        byte[] encodedBytes = md.digest(new String(array).getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodedBytes.length; i++) {
            sb.append(Integer.toString((encodedBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
