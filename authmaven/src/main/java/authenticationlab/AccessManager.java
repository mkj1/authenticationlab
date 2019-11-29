/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author nilement
 */
public class AccessManager {

    public boolean checkAccess(int userPermissions, int methodPermissions) {
        return ((userPermissions >>> methodPermissions) & 1) != 0;
    }

    public boolean checkRoleAccess(String username, int methodPermissions, HashMap<String, String> usersToRoles, HashMap<String, String> rolesToAccesses) throws FileNotFoundException, IOException {

        String role = null;
        role = usersToRoles.get(username);

        boolean permitted = false;
        var methods = rolesToAccesses.get(role).split(",");
        for (String s : methods) {
            if (Integer.parseInt(s) == methodPermissions) {
                permitted = true;
            }
        }

        return permitted;
    }

//    public void checkAccess(int userPermissions, int ){
//        
//    }
}
