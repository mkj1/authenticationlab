/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author osboxes
 */
public class AuthService {
    public HashMap<UUID, Boolean> sessions;
    
    public AuthService(){
        sessions = new HashMap<UUID, Boolean>();
    }
    
    public String Login(String username, String password){
        String sessionKey = GetNewSession(username, password);
        if (sessionKey.isEmpty()){
            return "";
        }
        UUID uuid = UUID.randomUUID();
        sessions.put(uuid, true);
        return uuid.toString();
    }
    
    public String GetNewSession(String username, String password){ 
        String hashInStore = ""; // retrieve password from storage
        if (hashInStore.isEmpty()){
            return "";
        }
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] passHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            int HashesEqual = passHash.toString().compareTo(hashInStore);
            if (HashesEqual == 0){
                
            }
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return "hey";
    }
    
    public boolean ValidateSession(String session){
        return sessions.containsKey(session);
    }
}
