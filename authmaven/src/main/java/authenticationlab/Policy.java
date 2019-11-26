/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

/**
 *
 * @author nilement
 */
public class Policy {
    public String Username;
    public int Permissions;
    
    public Policy (String username, int permissions){
        Username = username;
        Permissions = permissions;
    }
}
