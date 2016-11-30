/**
 * Created by Adam Manley Kelly - R00113196 on 30/11/2016.
 * https://github.com/adammanley-kelly/Assignment4-AOP-2016
 */
import java.io.Serializable;

public class Movie extends Thread implements Serializable
{
    String movies[]={"The Breakfast Club","Ferris Buellers day Off","Deadpool"};
    int [] year = {1985,1986,2016};
    int i = 3;
    boolean done = false;

    public void run()  {
        try {
            done = false;
            i++;
            if (i == 4) {
                i = 0;
            }
            Thread.sleep(3000);
            done = true;
        }
        catch(Exception e){
            System.out.println("Server: Oh no!!!");
        };
    }

    public String getMovie()
    {
        done = false;
        return movies[i];
    }

    public int getYear()
    {
        return year[i];
    }

    public boolean doneYet() {
        return done;
    }
}
