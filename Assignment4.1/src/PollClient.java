/**
 * Created by Adam Manley Kelly - R00113196 on 30/11/2016.
 * https://github.com/adammanley-kelly/Assignment4-AOP-2016
 *
 * The GUI does come up very small after attempting to implement this in JavaFX I went
 * back to Swing which im not fantastic at regarding layouts
 */

import java.awt.*;
import java.rmi.*;
import javax.swing.*;
public class PollClient extends javax.swing.JFrame
{
    JLabel activityLabel;
    JTextField movieLabel,yearLabel;
    JButton getMovie;
    Poll serverObject;
    MovieChecker testMovie;


    public PollClient()
    {
        initComponents();
        LabelChanger lb = new LabelChanger(activityLabel);
        lb.start();
        try {
            Remote remoteObject = Naming.lookup("MOVIE");
            serverObject = (Poll)remoteObject ;
        }
        catch(Exception e){
            System.out.println("Oh no!!!1");
        };
        testMovie = new MovieChecker(movieLabel, yearLabel, serverObject);
        testMovie.start();

    }

    private void initComponents() {
        activityLabel = new JLabel();
        getMovie= new JButton();
        movieLabel = new JTextField();
        yearLabel = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        activityLabel.setText(" ");
        getContentPane().add(activityLabel, java.awt.BorderLayout.NORTH);

        getMovie.setText("Get me a Movie");
        getMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getMovieActionPerformed(evt);
            }
        });

        getContentPane().add(getMovie,  BorderLayout.CENTER);
        getContentPane().add(yearLabel, BorderLayout.EAST);
        getContentPane().add(movieLabel, BorderLayout.WEST);

        pack();
    }

    private void getMovieActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            serverObject.nextMovie();
        }
        catch(Exception e){
            System.out.println("Oh no!!!");
        };
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PollClient().setVisible(true);
            }
        });
    }

}

class LabelChanger extends Thread {

    JLabel label;

    public LabelChanger(JLabel lb){
        label = lb;
    }

    public void run() {
        while (true){
            try {
                label.setText("Hello There!");
                Thread.sleep(1000);
                label.setText(" ");
                Thread.sleep(1000);
            }
            catch(Exception e){
                System.out.println("Client: Oh no!!!");
            };
        }
    }
}

class MovieChecker extends Thread {

    private JTextField textfield,textfieldY;
    private Poll remoteMovie;

    public MovieChecker(JTextField tf,JTextField tfy, Poll f)
    {
        textfield = tf;
        textfieldY = tfy;
        remoteMovie = f;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100) ;
                if (remoteMovie.doneYet()) {
                    textfield.setText(remoteMovie.getMovie().getMovie());
                    textfieldY.setText(Integer.toString(remoteMovie.getMovie().getYear()));
                }
            }
            catch(Exception e){
                System.out.println("Checker: Oh no!!!");
                e.printStackTrace();
            };
        }
    }
}
