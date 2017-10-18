package t32games.viruswars;

public class GameLogic {
    public static final int IDLE =0;
    public static final int PLAYER_1 =1;
    public static final int PLAYER_2 =2;
    public static final int WINNER_PLAYER_1 =3;
    public static final int WINNER_PLAYER_2 =4;
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

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void startNewGame(){
        reset();
        playerTurn=PLAYER_1;
    }

    public boolean makeTurn(int semiturnPointer, int[] semiturnX, int[] semiturnY) {
        boolean flag =true;
        if (semiturnPointer==0) {
            flag=false;
        }
        for(int i=0;i<semiturnPointer;i++) {
            if (!getAvailability(semiturnX[i],semiturnY[i])){
                flag = false;
            }
        }
        if (!flag){
            int[][] map = getAvailabilityMap();
            int availabilitySum = 0;
            for (int x=0;x<X_FIELD_SIZE;x++) {
                for (int y = 0; y < Y_FIELD_SIZE; y++) {
                    availabilitySum+=map[x][y];
                }
            }
            if (availabilitySum==semiturnPointer){
                flag=true;
            }
        }
        if (flag){
            for(int i=0;i<semiturnPointer;i++) {
                if (players[semiturnX[i]][semiturnY[i]]==0) {
                    players[semiturnX[i]][semiturnY[i]]=playerTurn;
                    killed[semiturnX[i]][semiturnY[i]]=false;
                } else {
                    killed[semiturnX[i]][semiturnY[i]]=true;
                }
            }
            if (playerTurn==PLAYER_1) {
                playerTurn=PLAYER_2;
            } else if (playerTurn==PLAYER_2) {
                playerTurn=PLAYER_1;
            }
        }
        checkGameEnd();
        return flag;
    }

    public FieldStateSnapshot getFieldData() {
        int[][] av = new int[X_FIELD_SIZE][Y_FIELD_SIZE];
        FieldStateSnapshot ans = new FieldStateSnapshot();
        ans.setPlayers(players,killed);
        ans.setAvailability(getAvailabilityMap());
        return ans;
    }

    public boolean getAvailability(int x, int y){
        return getAvailabilityMap()[x][y]==1;
    }

    public int[][] getAvailabilityMap() {
        int[][] map = new int[X_FIELD_SIZE][Y_FIELD_SIZE];

        for (int i=0;i<X_FIELD_SIZE;i++){
            for (int j=0;j<Y_FIELD_SIZE;j++){
                if ((players[i][j]==playerTurn)&(!killed[i][j])){
                    map[i][j]=1;
                } else {
                    map[i][j]=0;
                }
            }
        }

        boolean flag=true;
        while (flag) {
            flag = false;
            for (int x=0;x<X_FIELD_SIZE;x++){
                for (int y=0;y<Y_FIELD_SIZE;y++){
                    if ((map[x][y]==0)&(players[x][y]!=playerTurn)&(!killed[x][y])) {
                        for(int dx=-1; dx<2;dx++){
                            for(int dy=-1; dy<2;dy++){
                                if (!((dx==0)&(dy==0))) {
                                    if((x+dx>=0)&(x+dx<X_FIELD_SIZE)&(y+dy>=0)&(y+dy<Y_FIELD_SIZE)) {
                                        if((map[x+dx][y+dy]==1)&(players[x+dx][y+dy]!=0)) {
                                            map[x][y]=1;
                                            flag=true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        if ((playerTurn==PLAYER_1)&(players[0][Y_FIELD_SIZE]!=PLAYER_1)) {
            map[0][Y_FIELD_SIZE]=1;
        }
        if ((playerTurn==PLAYER_2)&(players[X_FIELD_SIZE][0]!=PLAYER_2)) {
            map[X_FIELD_SIZE][0]=1;
        }

        return map;
    }

    private void checkGameEnd(){

    }
}
