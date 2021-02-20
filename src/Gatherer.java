import java.util.Random;
public class Gatherer {
    int xCoordinate, yCoordinate;

    //Size of each block
    static final int SIZE = 64;

    //Milliseconds per tick
    static final int TICK_MILLISECONDS = 500;

    //Number of directions a gatherer can move in
    static final int N_DIRECTIONS = 4;

    //Direction key:
    static final int UP = 1;
    static final int DOWN = 3;
    static final int RIGHT = 2;
    static final int LEFT = 4;

    //Last move
    long lastMove = System.currentTimeMillis();

    public Gatherer (int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void moveGatherer() {
        if (System.currentTimeMillis() - lastMove > TICK_MILLISECONDS) {
            Random random = new Random();

            //Pick a number between 1-4, to decide what direction the gatherer moves in (note function is exclusive)
            //1: UP, 2: RIGHT, 3:DOWN, 4:LEFT
            int direction = random.nextInt(N_DIRECTIONS+1);

            if (direction == UP) {
                this.yCoordinate -= SIZE;
            } else if (direction == RIGHT) {
                this.xCoordinate += SIZE;
            } else if (direction == DOWN) {
                this.yCoordinate += SIZE;
            } else if (direction == LEFT) {
                this.xCoordinate -= SIZE;
            }
            lastMove = System.currentTimeMillis();
        }
    }
}
