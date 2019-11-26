/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    ArrayList<Policy> policies;

    //This implementation only allows one loggedinID at a given moment,
    //to further improve this, a table to hold multiple session IDs should be implemented.
    private String loggedinID = null;

    private boolean authenticate(String tryPW, byte[] encryptedPW, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] encryptedTryPW = encryptPassword(tryPW, salt);
        return Arrays.equals(encryptedPW, encryptedTryPW);
    }

    private byte[] encryptPassword(String pw, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {

        int keyLength = 160;

        int iterations = 20000;

        KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt, iterations, keyLength);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        return factory.generateSecret(spec).getEncoded();
    }

    private byte[] generateSalt() throws NoSuchAlgorithmException {

        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        return salt;
    }

    public PrintServant() throws RemoteException, FileNotFoundException {
        super();
        logger = new FileLogger();
        policies = loadACLPolicies();
    }

    @Override
    public void print(String filename, String printer) throws RemoteException {
        try {
            if (!loggedinID.equals(getClientHost())) {
                return;
            }
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
        logger.log("New");
    }

    @Override
    public void queue() throws RemoteException {
        try {
            if (!loggedinID.equals(getClientHost())) {
                return;
            }
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
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
            try {
                loggedinID = getClientHost();
            } catch (ServerNotActiveException ex) {
                Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Login succesful. User: " + username);
        } else {
            System.out.println("Wrong username or password.");
        }
    }

    @Override
    public void topQueue(int job) throws RemoteException {
        try {
            if (!loggedinID.equals(getClientHost())) {
                return;
            }
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void start() throws RemoteException {
        try {
            if (!loggedinID.equals(getClientHost())) {
                return;
            }
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void stop() throws RemoteException {
        try {
            if (!loggedinID.equals(getClientHost())) {
                return;
            }
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void restart() throws RemoteException {
        try {
            if (!loggedinID.equals(getClientHost())) {
                return;
            }
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void status() throws RemoteException {
        try {
            if (!loggedinID.equals(getClientHost())) {
                return;
            }
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void readConfig(String parameter) throws RemoteException {
        try {
            if (!loggedinID.equals(getClientHost())) {
                return;
            }
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException {
        try {
            if (!loggedinID.equals(getClientHost())) {
                return;
            }
        } catch (ServerNotActiveException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        String methodName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
    }
    
    public ArrayList<Policy> loadACLPolicies() throws FileNotFoundException {
        ArrayList<Policy> policies = new ArrayList<Policy>();
        Scanner scanner = new Scanner(new File("Policies"));
        scanner.nextLine();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] stuff = line.split(":");
            String name = stuff[0];
            int permissions = Integer.parseInt(stuff[1]);
            Policy newpol = new Policy(name, permissions);
            policies.add(newpol);
        }
        System.out.println("Policies loaded");
        return policies;
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
                    if (!line.equals(username)) {
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
