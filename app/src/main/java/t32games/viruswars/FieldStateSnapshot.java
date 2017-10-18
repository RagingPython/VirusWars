package t32games.viruswars;


public class FieldStateSnapshot {
    public static final int CELL_NOT_AVAILABLE=0;
    public static final int CELL_AVAILABLE=1;
    public static final int CELL_SELECTED=2;


    private int[][] players;
    private boolean[][] killed;
    private int[][] availability;

    public void setPlayers(int[][] pl, boolean[][] k) {
        players = pl;
        killed = k;
    }

    public void setAvailability(int[][] av) {
        availability = av;
    }

    public void setCellSelected(int x, int y) {
        availability[x][y]=CELL_SELECTED;
    }

    public void setCellUnSelected(int x, int y) {
        availability[x][y]=CELL_AVAILABLE;
    }

    public int getPlayer(int x,int y) {
        return players[x][y];
    }

    public boolean getKilled(int x, int y) {
        return killed[x][y];
    }

    public int getAvailability(int x,int y) {
        return availability[x][y];
    }
}
