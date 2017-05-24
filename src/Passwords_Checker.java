import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

/**
 * @author Cameron Grout
 *
 * A utility class to hash passwords and check passwords vs hashed values. Based on code from
 * <a href="http://stackoverflow.com/a/18143616">this StackOverflow post</a>, modified to be
 * inline with OWASP recommendations for algorithm and salt length. Overloads and base64 helpers
 * have been added for ease-of-use.
 */
public class Passwords_Checker {
    private static final Random RANDOM = new SecureRandom();
    private static final int DEFAULT_ITERATIONS = 100_000;
    private static final int KEY_LENGTH = 512;
    private static final int DEFAULT_SALT_LENGTH = 32;

    public Passwords_Checker() { }


    public static byte[] getNextSalt() {
        return getNextSalt(DEFAULT_SALT_LENGTH);
    }


    public static byte[] getNextSalt(int saltLength) {
        byte[] salt = new byte[saltLength];
        RANDOM.nextBytes(salt);
        return salt;
    }


    public static byte[] insecureHash(String password) {
        try {
            MessageDigest mda = MessageDigest.getInstance("SHA-512");
            return mda.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        }
    }

    public static boolean isInsecureHashMatch(String password, byte[] expectedHash) {
        byte[] pwdHash = insecureHash(password);

        if (pwdHash.length != expectedHash.length) {
            return false;
        }

        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) {
                return false;
            }
        }

        return true;
    }


    public static byte[] hash(char[] password, byte[] salt) {
        return hash(password, salt, DEFAULT_ITERATIONS);
    }


    public static byte[] hash(char[] password, byte[] salt, int iterations) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, KEY_LENGTH);

        // Blank out the provided password array - prevents accidental leakage of this information
        Arrays.fill(password, Character.MIN_VALUE);

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }


    public static boolean isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash) {
        return isExpectedPassword(password, salt, DEFAULT_ITERATIONS, expectedHash);
    }


    public static boolean isExpectedPassword(char[] password, byte[] salt, int iterations, byte[] expectedHash) {
        byte[] pwdHash = hash(password, salt, iterations);

        // Blank out the provided password array - prevents accidental leakage of this information
        Arrays.fill(password, Character.MIN_VALUE);

        if (pwdHash.length != expectedHash.length) {
            return false;
        }

        for (int i = 0; i < pwdHash.length; i++) {
            if (pwdHash[i] != expectedHash[i]) {
                return false;
            }
        }

        return true;
    }


    public static byte[] base64Decode(String b64) throws IllegalArgumentException {
        return Base64.getDecoder().decode(b64);
    }

    public static String base64Encode(byte[] array) {
        return Base64.getEncoder().encodeToString(array);
    }

    public String hashing(String password , int salt , int iterations) {
        byte[] saltbyte = {(byte) salt};
        byte[] passwordHashed = hash(password.toCharArray(), saltbyte, iterations);
        password = base64Encode(passwordHashed);
        return password;
    }
}
