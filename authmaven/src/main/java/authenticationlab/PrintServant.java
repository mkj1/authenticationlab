/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Mads
 */
public class PrintServant extends UnicastRemoteObject implements PrintService {

    FileLogger logger;

    private int loggedinID = 0;

    private boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Encrypt the clear-text password using the same salt that was used to
        // encrypt the original password
        byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);

        // Authentication succeeds if encrypted password that the user entered
        // is equal to the stored hash
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }

    private byte[] getEncryptedPassword(String password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
        // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
        String algorithm = "PBKDF2WithHmacSHA1";
        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        int derivedKeyLength = 160;
        // Pick an iteration count that works for you. The NIST recommends at
        // least 1,000 iterations:
        // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
        // iOS 4.x reportedly uses 10,000:
        // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
        int iterations = 20000;

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);

        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

        return f.generateSecret(spec).getEncoded();
    }

    private byte[] generateSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }

    public PrintServant() throws RemoteException {
        super();
        logger = new FileLogger();
    }

    @Override
    public void print(String filename, String printer) throws RemoteException {
        if (loggedinID != getRef().remoteHashCode()) {
            return;
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
        logger.log("New");
    }

    @Override
    public void queue() throws RemoteException {
        if (loggedinID != getRef().remoteHashCode()) {
            return;
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void login(String username, String pw) throws RemoteException {
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");

        var ok = auth(username, pw);
        if (ok) {
            loggedinID = getRef().remoteHashCode();
            System.out.println("Login succesful. User: " + username);
        }
        else{
            System.out.println("Wrong username or password.");
        }
    }

    @Override
    public void topQueue(int job) throws RemoteException {
        if (loggedinID != getRef().remoteHashCode()) {
            return;
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void start() throws RemoteException {
        if (loggedinID != getRef().remoteHashCode()) {
            return;
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void stop() throws RemoteException {
        if (loggedinID != getRef().remoteHashCode()) {
            return;
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void restart() throws RemoteException {
        if (loggedinID != getRef().remoteHashCode()) {
            return;
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void status() throws RemoteException {
        if (loggedinID != getRef().remoteHashCode()) {
            return;
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void readConfig(String parameter) throws RemoteException {
        if (loggedinID != getRef().remoteHashCode()) {
            return;
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {
        if (loggedinID != getRef().remoteHashCode()) {
            return;
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    private boolean auth(String username, String pw) {
        try {

            byte[] bytes = null;
            byte[] saltBytes = null;

            Scanner scanner = new Scanner(new File("users"));
            int linecount = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
               if (linecount == 0) {
                    if(!line.equals(username)){
                        return false;
                    }
                }
                if (linecount == 1) {
                    String s = line;
                    bytes = s.getBytes();
                } else if (linecount == 2) {
                    String saltString = line;
                    saltBytes = saltString.getBytes();
                }
                linecount++;
            }

            return authenticate(pw, bytes, saltBytes);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
