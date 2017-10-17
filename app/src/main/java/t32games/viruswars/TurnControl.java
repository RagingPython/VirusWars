package t32games.viruswars;

import android.view.View;


public class TurnControl implements View.OnClickListener{
    int[] semiturnX = new int[3];
    int[] semiturnY = new int[3];
    int semiturnPointer=0;
    GameLogic gL;

    public TurnControl(GameLogic gameLogic){
        gL = gameLogic;
    }

    public void newTurn() {
        semiturnPointer=0;
    }

    public void endTurn() {
        boolean ans = gL.makeTurn(semiturnPointer, semiturnX, semiturnY);
        if (ans) {newTurn();}
    }

    public void cellPressed(int x, int y) {
        boolean flag=false;
        for (int i = 0; i < semiturnPointer; i++) {
            if ((semiturnX[i] == x) & (semiturnY[i] == y)) {
                flag=true;
                semiturnPointer=i;
                i=3;
            }
        }
        if (!flag) {
            if((gL.getAvailability(x,y))&(semiturnPointer<3)){
                semiturnX[semiturnPointer]=x;
                semiturnY[semiturnPointer]=y;
                semiturnPointer=semiturnPointer+1;
            }
        }

    }

    public void startNewGame(){
        gL.startNewGame();
        newTurn();
    };

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id._buttonEndTurn){
            endTurn();
        }
        if (view.getId()==R.id._buttonNewGame){
            startNewGame();
        }
    }
}
