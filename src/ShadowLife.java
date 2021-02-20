import bagel.*;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.lang.System;

public class ShadowLife extends AbstractGame{

    //Maximum number of lines in a given csv input
    static final int MAX_ACTORS = 100;

    //Import all images
    private Image background;
    private Image gatherer;
    private Image tree;
    private long milliseconds = System.currentTimeMillis();
    //Hold all actors in respective arrays
    private Tree[] treeArray;
    private Gatherer[] gathererArray;

    //Constructing ShadowLife settings
    public ShadowLife(Tree[] treeArray, Gatherer[] gathererArray) {
        //Creating the window

        //Background
        background = new Image("res/images/background.png");

        //Declaring the actor images
        gatherer = new Image("res/images/gatherer.png");
        tree = new Image("res/images/tree.png");

        this.treeArray = treeArray;
        this.gathererArray = gathererArray;
    }

    public static void main(String[] args) {
        //Create Arrays to be filled by respective actors
        Tree internalTreeArray[] = new Tree[MAX_ACTORS];
        Gatherer internalGathererArray[] = new Gatherer[MAX_ACTORS];


        try(BufferedReader br = new BufferedReader(new FileReader("res/worlds/test.csv"))){
            String text;

            //Initialise counts to find input into arrays
            int treeCount = 0;
            int gathererCount = 0;

            //Parse through each non-empty line
            while ((text = br.readLine()) != null) {

                //Read in each line as an array
                String cells[] = text.split(",");

                //Declare each input as required
                String type = cells[0];
                int xCoordinate = Integer.parseInt(cells[1]);
                int yCoordinate = Integer.parseInt(cells[2]);

                if (type.equals("Tree")) {
                    //Add tree element to array of trees
                    internalTreeArray[treeCount]  = new Tree(xCoordinate, yCoordinate);
                    treeCount++;

                } else if (type.equals("Gatherer")) {
                    //Add gatherer element to array of gatherers
                    internalGathererArray[gathererCount] = new Gatherer(xCoordinate, yCoordinate);
                    gathererCount++;
                }
                else{
                    throw new Exception("Invalid Type!");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        //Run the game!
        ShadowLife game = new ShadowLife(internalTreeArray, internalGathererArray);
        game.run();

    }


    @Override
    public void update(Input input) {
        background.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);
        //draw all trees
        for (int i = 0; i < MAX_ACTORS; i++) {
            if (treeArray[i] == null) {
                break;
            } else {
                tree.draw(treeArray[i].xCoordinate, treeArray[i].yCoordinate);
            }
        }
        //draw all gatherers
        for (int j = 0; j < MAX_ACTORS; j++) {
            if (gathererArray[j] == null) {
                break;
            } else {
                gatherer.draw(gathererArray[j].xCoordinate, gathererArray[j].yCoordinate);
                //update gatherer position with call to gatherer method
                gathererArray[j].moveGatherer();
            }
        }
    }
}
