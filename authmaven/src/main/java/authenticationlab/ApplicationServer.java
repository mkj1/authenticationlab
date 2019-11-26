/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Mads
 */
public class ApplicationServer {
    public static void main(String[] args) throws RemoteException, FileNotFoundException{
        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("print", new PrintServant());
    }
    
}
