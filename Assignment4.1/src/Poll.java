/**
 * Created by Adam Manley Kelly - R00113196 on 30/11/2016.
 * https://github.com/adammanley-kelly/Assignment4-AOP-2016
 */

import java.rmi.*;

public interface Poll extends Remote {
    void nextMovie() throws RemoteException ;

    boolean doneYet() throws RemoteException;

    Movie getMovie() throws RemoteException;
}
