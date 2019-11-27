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
import java.util.Scanner;

/**
 *
 * @author nilement
 */
public class AccessManager {

    public boolean checkAccess(int userPermissions, int methodPermissions) {
        return ((userPermissions >>> methodPermissions) & 1) != 0;
    }

    public boolean checkRoleAccess(String username, int methodPermissions) throws FileNotFoundException, IOException {
        ArrayList<String> UsersRoles = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("UsersRoles"))) {
            while (br.ready()) {
                UsersRoles.add(br.readLine());
            }
        }

        String role = null;
        for (String str : UsersRoles) {
            var splitted = str.split(":");
            if (splitted[0].equals(username)) {
                role = splitted[1];
            }
        }

        ArrayList<String> Roles = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("Roles"))) {
            while (br.ready()) {
                Roles.add(br.readLine());
            }
        }

        boolean permitted = false;
        for (String str : Roles) {
            var splitted = str.split(":");
            if (splitted[0].equals(role)) {
                var methods = splitted[1].split(",");
                for(String s : methods){
                    if(Integer.parseInt(s) == methodPermissions)
                        permitted = true;
                }
            }
        }

        return permitted;
    }

//    public void checkAccess(int userPermissions, int ){
//        
//    }
}
