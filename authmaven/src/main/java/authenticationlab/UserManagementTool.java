/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author gxv
 */
public class UserManagementTool {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {  
        var q = authenticate("David", "1234");
        var w = authenticate("Alice", "1234");
        var e = authenticate("Bob", "1234");
        var r = authenticate("Cecilia", "1234");
        var t = authenticate("Erica", "1234");
        var y = authenticate("Fred", "1234");
        var u = authenticate("George", "1234");
    }

    private static void createUser(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        var service = new PasswordEncryptionService();
        var salt = service.generateSalt();
        var encrypted = service.encryptPassword(password, salt);
        writeByte(username, encrypted, salt);
    }

    public static boolean authenticate(String username, String trypassword) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        byte[] pword = readPassword(username);
        byte[] salt = readSalt(username);

        var service = new PasswordEncryptionService();
        var auth = service.authenticate(trypassword, pword, salt);
        return auth;
    }

    static void writeByte(String username, byte[] encryptedPw, byte[] salt) {
        try {
            File file = new File("users\\" + username + "password");
            OutputStream os = new FileOutputStream(file);                    
            os.write(encryptedPw);
            os.close();

            File file2 = new File("users\\" + username + "salt");
            OutputStream os2 = new FileOutputStream(file2);                   
            os2.write(salt);
            os.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    static byte[] readPassword(String username) throws IOException {
        Path fileLocation = Paths.get("users\\" + username + "password");
        byte[] password = Files.readAllBytes(fileLocation);
        return password;
    }

    static byte[] readSalt(String username) throws IOException {
        Path fileLocation = Paths.get("users\\" + username + "salt");
        byte[] salt = Files.readAllBytes(fileLocation);
        return salt;
    }
}
