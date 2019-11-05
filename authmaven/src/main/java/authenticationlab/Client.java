/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Mads
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        PrintService service = (PrintService) Naming.lookup("rmi://localhost:5099/print");
        service.queue();
        service.print("hey", "yo");
        service.start();
        service.status();
    }
    
}
