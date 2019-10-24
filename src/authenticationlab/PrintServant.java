/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authenticationlab;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Mads
 */
public class PrintServant extends UnicastRemoteObject implements PrintService{
    public PrintServant() throws RemoteException{
        super();
    }

    @Override
    public void print(String filename, String printer) throws RemoteException {
        String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println(methodName + " invoked");
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
