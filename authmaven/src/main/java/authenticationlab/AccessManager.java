/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.math.BigInteger;

/**
 *
 * @author nilement
 */

public class AccessManager {
    public boolean checkAccess(int userPermissions, int methodPermissions){
          return ((userPermissions >>> methodPermissions) & 1) != 0;
    }
    
//    public void checkAccess(int userPermissions, int ){
//        
//    }
}
