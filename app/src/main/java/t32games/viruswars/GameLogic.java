package t32games.viruswars;

/**
 * Created by nuzhdin on 17.10.2017.
 */

public class GameLogic {
    public static final int PLAYER_1 =1;
    public static final int PLAYER_2 =2;
    public static final int CELL_AVAILABLE = 1;
    public static final int CELL_NOT_AVAILABLE = 0;
    public static final int CELL_SELECTED =2;
    public static final int X_FIELD_SIZE = 10;
    public static final int Y_FIELD_SIZE = 10;

    private int[][] players = new int[10][10];
    private boolean[][] killed = new boolean[10][10];
    private int playerTurn;

    public GameLogic(){
        reset();
    }

    public void reset() {
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 10;j++){
                players[i][j]=0;
                killed[i][j]=false;
            }
        }
        playerTurn=0;
    }

    public boolean getAvailability(int x, int y, int player){
        return false;
    }

    public boolean getAvailability(int x, int y){
        return getAvailability(x,y,playerTurn);
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void startNewGame(){
        reset();
        playerTurn=PLAYER_1;
    }

    public int getPlayer(int x, int y){
        return players[x][y];
    }

    public boolean getKilled(int x, int y){
        return killed[x][y];
    }

    public boolean makeTurn(int semiturnPointer, int[] semiturnX, int[] semiturnY) {

        return true;
    }
}
