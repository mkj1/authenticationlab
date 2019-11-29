/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import java.util.HashMap;
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
    AccessManager manager;
    HashMap<String, Integer> policies;
    HashMap<String, String> rolesToAccesses;
    HashMap<String, String> usersToRoles;
    MethodAcceses accesses;

    public PrintServant() throws RemoteException, FileNotFoundException {
        super();
        try {
            logger = new FileLogger();
            policies = loadACLPolicies();
            rolesToAccesses = loadRolesToAccesses();
            usersToRoles = loadUsersToRoles();
            manager = new AccessManager();
            accesses = new MethodAcceses();
        } catch (IOException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void print(String username, String password, String filename, String printer) throws RemoteException {
        try {
            var UM = new UserManagementTool();
            var authenticated = UM.authenticate(username, password);

            if (!authenticated || !manager.checkRoleAccess(username, accesses.Print, usersToRoles, rolesToAccesses)) {
                return;
            }

//            int userPermissions = policies.get(username);
//            if (!manager.checkAccess(userPermissions, accesses.Print)) {
//                return;
//            }
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println(methodName + " invoked" + " by " + username);
            logger.log("New");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void queue(String username, String password) throws RemoteException {

        try {
            var UM = new UserManagementTool();
            var authenticated = UM.authenticate(username, password);

            if (!authenticated || !manager.checkRoleAccess(username, accesses.Queue, usersToRoles, rolesToAccesses)) {
                return;
            }
//            int userPermissions = policies.get(username);
//            if (!manager.checkAccess(userPermissions, accesses.Queue)) {
//                return;
//            }

            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println(methodName + " invoked" + " by " + username);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void topQueue(String username, String password, int job) throws RemoteException {
        try {
            var UM = new UserManagementTool();
            var authenticated = UM.authenticate(username, password);

            if (!authenticated || !manager.checkRoleAccess(username, accesses.TopQueue, usersToRoles, rolesToAccesses)) {
                return;
            }
//            int userPermissions = policies.get(username);
//            if (!manager.checkAccess(userPermissions, accesses.TopQueue)) {
//                return;
//            }
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println(methodName + " invoked" + " by " + username);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(String username, String password) throws RemoteException {
        try {
            var UM = new UserManagementTool();
            var authenticated = UM.authenticate(username, password);

            if (!authenticated || !manager.checkRoleAccess(username, accesses.Start, usersToRoles, rolesToAccesses)) {
                return;
            }
//            int userPermissions = policies.get(username);
//            if (!manager.checkAccess(userPermissions, accesses.Start)) {
//                return;
//            }
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println(methodName + " invoked" + " by " + username);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void stop(String username, String password) throws RemoteException {
        try {
            var UM = new UserManagementTool();
            var authenticated = UM.authenticate(username, password);

            if (!authenticated || !manager.checkRoleAccess(username, accesses.Stop, usersToRoles, rolesToAccesses)) {
                return;
            }
//        int userPermissions = policies.get(username);
//        if (!manager.checkAccess(userPermissions, accesses.Stop)) {
//            return;
//        }
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println(methodName + " invoked" + " by " + username);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void restart(String username, String password) throws RemoteException {
        try {
            var UM = new UserManagementTool();
            var authenticated = UM.authenticate(username, password);

            if (!authenticated || !manager.checkRoleAccess(username, accesses.Restart, usersToRoles, rolesToAccesses)) {
                return;
            }
//        int userPermissions = policies.get(username);
//        if (!manager.checkAccess(userPermissions, accesses.Restart)) {
//            return;
//        }
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println(methodName + " invoked" + " by " + username);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void status(String username, String password) throws RemoteException {
        try {
            var UM = new UserManagementTool();
            var authenticated = UM.authenticate(username, password);

            if (!authenticated || !manager.checkRoleAccess(username, accesses.Status, usersToRoles, rolesToAccesses)) {
                return;
            }
//        int userPermissions = policies.get(username);
//        if (!manager.checkAccess(userPermissions, accesses.Status)) {
//            return;
//        }
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println(methodName + " invoked" + " by " + username);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void readConfig(String username, String password, String parameter) throws RemoteException {
        try {
            var UM = new UserManagementTool();
            var authenticated = UM.authenticate(username, password);

            if (!authenticated || !manager.checkRoleAccess(username, accesses.ReadConfig, usersToRoles, rolesToAccesses)) {
                return;
            }
//        int userPermissions = policies.get(username);
//        if (!manager.checkAccess(userPermissions, accesses.ReadConfig)) {
//            return;
//        }
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println(methodName + " invoked" + " by " + username);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setConfig(String username, String password, String parameter, String value) throws RemoteException {
        try {
            var UM = new UserManagementTool();
            var authenticated = UM.authenticate(username, password);

            if (!authenticated || !manager.checkRoleAccess(username, accesses.SetConfig, usersToRoles, rolesToAccesses)) {
                return;
            }
//        int userPermissions = policies.get(username);
//        if (!manager.checkAccess(userPermissions, accesses.SetConfig)) {
//            return;
//        }
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            System.out.println(methodName + " invoked" + " by " + username);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(PrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<String, Integer> loadACLPolicies() throws FileNotFoundException {
        HashMap<String, Integer> policies = new HashMap<String, Integer>();
        Scanner scanner = new Scanner(new File("Policies"));
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] stuff = line.split(":");
            String name = stuff[0];
            int permissions = Integer.parseInt(stuff[1]);
//            Policy newpol = new Policy(name, permissions);
//            policies.add(newpol);
            policies.put(name, permissions);
        }
        System.out.println("Policies loaded");
        return policies;
    }

    public HashMap<String, String> loadRolesToAccesses() throws FileNotFoundException, IOException {
        var rolesToAccesses = new HashMap<String, String>();
        ArrayList<String> Roles = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("Roles"))) {
            while (br.ready()) {
                Roles.add(br.readLine());
            }
        }

        for (String str : Roles) {
            var splitted = str.split(":");
            rolesToAccesses.put(splitted[0], splitted[1]);

        }

        return rolesToAccesses;
    }

    private HashMap<String, String> loadUsersToRoles() throws FileNotFoundException, IOException {

        var usersToRoles = new HashMap<String, String>();
        ArrayList<String> UsersRoles = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("UsersRoles"))) {
            while (br.ready()) {
                UsersRoles.add(br.readLine());
            }
        }

        for (String str : UsersRoles) {
            var splitted = str.split(":");
            usersToRoles.put(splitted[0], splitted[1]);

        }
        
        return usersToRoles;

    }

}
