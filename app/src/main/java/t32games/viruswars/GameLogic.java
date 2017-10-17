package t32games.viruswars;

/**
 * Created by nuzhdin on 17.10.2017.
 */

public class GameLogic {
    private int[][] players = new int[10][10];
    private boolean[][] killed = new boolean[10][10];

    public GameLogic(){
        for(int i = 0; i < 10;i++){
            for(int j = 0; j < 10;j++){
                players[i][j]=0;
                killed[i][j]=false;
            }
        }
    }

    public boolean getAvailability(int x, int y, int player){
        return false;
    }

    public int getPlayer(int x, int y){
        return players[x][y];
    }

    public boolean getKilled(int x, int y){
        return killed[x][y];
    }
}
