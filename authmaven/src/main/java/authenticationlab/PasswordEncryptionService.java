package authenticationlab;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
 
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
 
public class PasswordEncryptionService {
 
    public boolean authenticate(String tryPW, byte[] encryptedPW, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] encryptedTryPW = encryptPassword(tryPW, salt);
        return Arrays.equals(encryptedPW, encryptedTryPW);
    }
 
    public byte[] encryptPassword(String pw, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        int keyLength = 160;

        int iterations = 20000;

        KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt, iterations, keyLength);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        return factory.generateSecret(spec).getEncoded();
    }
 
    public byte[] generateSalt() throws NoSuchAlgorithmException {

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }
}