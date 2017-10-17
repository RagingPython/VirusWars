package t32games.viruswars;

/**
 * Created by nuzhdin on 17.10.2017.
 */

public class TurnControl {
    int player=0;
    int[] semiturnX = new int[3];
    int[] semiturnY = new int[3];
    int semiturnPointer=-1;
    GameLogic gL;

    public TurnControl(GameLogic gameLogic){
        gL = gameLogic;
    }

    public void newTurn(int p) {
        for (int i =0; i<3; i++) {
            semiturnX[i]=-1;
            semiturnY[i]=-1;
        }
        player=p;
        semiturnPointer=-1;
    }

    public void cellPressed(int x, int y) {
        for (int i =0; i<3; i++) {
            if ((semiturnX[i]==x)&(semiturnY[i]==y)) {


                i=3;
            }
        }
    }
}
