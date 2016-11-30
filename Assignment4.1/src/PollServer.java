/**
 * Created by Adam Manley Kelly - R00113196 on 30/11/2016.
 * https://github.com/adammanley-kelly/Assignment4-AOP-2016
 */

import java.rmi.*;
import java.rmi.server.*;
import java.lang.*;

public class PollServer extends UnicastRemoteObject implements Poll
{

    Movie movie  = new Movie();

    public PollServer() throws RemoteException {
    }

    public void nextMovie() throws RemoteException {
        movie = new Movie();
        movie.start();
    }

    public boolean doneYet() throws RemoteException {
        return movie.doneYet();
    }

    public Movie getMovie() throws RemoteException {
        return movie;
    }

    public static void main(String[] args) {
        try {

            PollServer server = new PollServer();
            Naming.rebind("MOVIE", server);
            System.out.println("Server Started");

        }
        catch (java.net.MalformedURLException e) {
            System.out.println("Malformed URL for MessageServer name " + e.toString());
        }
        catch (RemoteException e) {
            System.out.println("Communication error " + e.toString());
        }
    }

}

