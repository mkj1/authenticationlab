/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Mads
 */
public class PrintServant extends UnicastRemoteObject implements PrintService{
    FileLogger logger;
    
     public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Encrypt the clear-text password using the same salt that was used to
        // encrypt the original password
        byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);

        // Authentication succeeds if encrypted password that the user entered
        // is equal to the stored hash
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }

    public byte[] getEncryptedPassword(String password, byte[] salt)
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

    public byte[] generateSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }
    
    public PrintServant() throws RemoteException{
        super();
        logger = new FileLogger();
    }

    @Override
    public void print(String filename, String printer) throws RemoteException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
        logger.log("New");
        
        try {
            var salt = generateSalt();
            var encrypted = getEncryptedPassword("pw1234", salt);
            var auth = authenticate("pw1234",encrypted,salt);
            var x = auth;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void queue() throws RemoteException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void topQueue(int job) throws RemoteException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void start() throws RemoteException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void stop() throws RemoteException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void restart() throws RemoteException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void status() throws RemoteException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void readConfig(String parameter) throws RemoteException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }
    
}
