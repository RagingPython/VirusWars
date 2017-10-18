package t32games.viruswars;

import android.view.View;


public class TurnControl implements View.OnClickListener{
    int[] semiturnX = new int[3];
    int[] semiturnY = new int[3];
    int semiturnPointer=0;
    GameLogic gL;
    GameField gF;

    public TurnControl(){}

    public void setGameLogic(GameLogic gameLogic) {
        gL=gameLogic;
    }

    public void setGameField(GameField gameField) {
        gF=gameField;
    }

    public void newTurn() {
        semiturnPointer=0;
        refreshView();
    }

    public void endTurn() {
        if (gL.makeTurn(semiturnPointer, semiturnX, semiturnY)) {newTurn();}
    }

    public void cellPressed(int x, int y) {
        boolean flag=false;
        if ((gL.getPlayerTurn()==GameLogic.PLAYER_1)|(gL.getPlayerTurn()==GameLogic.PLAYER_2)) {
            for (int i = 0; i < semiturnPointer; i++) {
                if ((semiturnX[i] == x) & (semiturnY[i] == y)) {
                    flag = true;
                    semiturnPointer = i;
                    i = 3;
                    refreshView();
                }
            }
            if (!flag) {
                if ((gL.getAvailability(x, y)) & (semiturnPointer < 3)) {
                    semiturnX[semiturnPointer] = x;
                    semiturnY[semiturnPointer] = y;
                    semiturnPointer = semiturnPointer + 1;
                    refreshView();
                }
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

    private void refreshView() {
        switch (gL.getPlayerTurn()) {
            case GameLogic.IDLE:
                gF.setFieldData(null);
                break;
            case GameLogic.PLAYER_1:
            case GameLogic.PLAYER_2:
                FieldStateSnapshot fSS = gL.getFieldData();
                for (int i=0; i<semiturnPointer; i++){
                    fSS.setCellSelected(semiturnX[i],semiturnY[i]);
                }
                gF.setFieldData(fSS);
                break;
            case GameLogic.WINNER_PLAYER_1:
            case GameLogic.WINNER_PLAYER_2:
                gF.setFieldData(gL.getFieldData());
                //TODO other win visual
                break;
        }
        gF.invalidate();
    }
}
