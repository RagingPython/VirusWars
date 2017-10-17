package t32games.viruswars;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class CellArtist {
    float cS;
    Path playerAlivePath = new Path();
    Path playerDeadPath = new Path();

    Paint player1AlivePaint = new Paint();
    Paint player2AlivePaint = new Paint();
    Paint player1DeadPaint = new Paint();
    Paint player2DeadPaint = new Paint();
    Paint selectedPaint = new Paint();
    Paint availablePaint = new Paint();
    Paint player1ControlPaint = new Paint();
    Paint player2ControlPaint = new Paint();

    public CellArtist(GameField gameField){
        player1DeadPaint.setStyle(Paint.Style.STROKE);
        player2DeadPaint.setStyle(Paint.Style.STROKE);
        player1AlivePaint.setColor(Color.BLUE);
        player1DeadPaint.setColor(Color.BLUE);
        player2AlivePaint.setColor(Color.RED);
        player2DeadPaint.setColor(Color.RED);
        selectedPaint.setColor(Color.YELLOW);
        availablePaint.setARGB(255,255,255,150);
        player2ControlPaint.setARGB(255,255,170,170);
        player1ControlPaint.setARGB(255,200,200,255);
    }

    public void initialize(float cellSize) {
        cS=cellSize;
        playerAlivePath.reset();
        playerAlivePath.addCircle(cS/2, cS/2, cS/3, Path.Direction.CW);
        playerDeadPath.reset();
        playerDeadPath.moveTo(cS/6, cS/6);
        playerDeadPath.lineTo(5*cS/6,5*cS/6);
        playerDeadPath.moveTo(5*cS/6, cS/6);
        playerDeadPath.lineTo(cS/6, 5*cS/6);
    }

    public void drawCell(Canvas canvas, float x, float y, int player, boolean killed, int selected) {
        Paint p = new Paint();
        Path pth;
        p.setColor(Color.WHITE);
        if (selected == GameField.CELL_SELECTED) {
            p=selectedPaint;
        } else if (selected == GameField.CELL_AVAILABLE) {
            p=availablePaint;
        } else if (((player==GameField.PLAYER_1)&(!killed))|((player==GameField.PLAYER_2)&(killed))) {
            p=player1ControlPaint;
        } else if (((player==GameField.PLAYER_2)&(!killed))|((player==GameField.PLAYER_1)&(killed))) {
            p=player2ControlPaint;
        }
        if (p!=null) {
            canvas.drawRect(x,y,x+cS,y+cS,p);
        }

        if (!killed) {
            pth=playerAlivePath;
        } else {
            pth=playerDeadPath;
        }

        p=null;

        if ((player==GameField.PLAYER_1)&(!killed)) {
            pth=playerAlivePath;
            p=player1AlivePaint;
        } else if ((player==GameField.PLAYER_1)&(killed)) {
            pth=playerDeadPath;
            p=player1DeadPaint;
        } else if ((player==GameField.PLAYER_2)&(!killed)) {
            pth=playerAlivePath;
            p=player2AlivePaint;
        } else if ((player==GameField.PLAYER_2)&(killed)) {
            pth=playerDeadPath;
            p=player2DeadPaint;
        }

        if (p!=null) {
            Path pth2 = new Path();
            pth2.reset();
            pth2.addPath(pth);
            pth2.offset(x,y);
            canvas.drawPath(pth2,p);
        }
    }
}
