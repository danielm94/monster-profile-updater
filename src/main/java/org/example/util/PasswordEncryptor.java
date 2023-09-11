package org.example.util;

/**
 * Encrypts password for logging purposes.
 *
 * @author daniel
 */
public class PasswordEncryptor {
    private PasswordEncryptor() {
    }

    /**
     * Masks the password, so it can be used for logging purposes. Replaces all the characters with
     * an asterisk character except the first and last characters.
     *
     * @param password The password to mask.
     * @return The masked password as a String.
     */
    public static String maskPassword(String password) {
        var passCharArr = password.toCharArray();
        var len = passCharArr.length;
        for (var i = 0; i < len; i++) {
            if (i == 0 || i == len - 1) continue;
            passCharArr[i] = '*';
        }
        return new String(passCharArr);
    }
}
