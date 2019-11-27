package authenticationlab;

import java.rmi.Remote;
import java.rmi.RemoteException;
        
/**
 *
 * @author Mads
 */
public interface PrintService extends Remote {
    public void print(String username, String password, String filename, String printer) throws RemoteException;
    public void queue(String username, String password) throws RemoteException;
    public void topQueue(String username, String password,int job) throws RemoteException;
    public void start(String username, String password) throws RemoteException;
    public void stop(String username, String password) throws RemoteException;
    public void restart(String username, String password) throws RemoteException;
    public void status(String username, String password) throws RemoteException;
    public void readConfig(String username, String password,String parameter) throws RemoteException;
    public void setConfig(String username, String password,String parameter, String value) throws RemoteException;
}
