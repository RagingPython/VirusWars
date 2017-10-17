package t32games.viruswars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class GameField extends View implements View.OnTouchListener{
    public static final int PLAYER_1 =1;
    public static final int PLAYER_2 =2;
    public static final int CELL_AVAILABLE = 1;
    public static final int CELL_NOT_AVAILABLE = 0;
    public static final int CELL_SELECTED =2;

    private static final int X_FIELD_SIZE = 10;
    private static final int Y_FIELD_SIZE = 10;
    private static final int BACKGROUND_COLOR = Color.WHITE;
    private static final int GRID_LINE_COLOR = Color.BLACK;
    private static final float GRID_LINE_WIDTH =4;
    private static final int SPACING = 20;

    private CellArtist cellArtist;
    private GameLogic gL;
    private Path gridPath;
    private Paint gridPaint;
    private int lastHeight = -1;
    private int lastWidth = -1;
    private float cS =0;
    private float leftSpacing=0;
    private float topSpacing=0;
    private float selectedX=-1;
    private float selectedY=-1;



    public GameField(Context context, AttributeSet attrs) {
        super(context, attrs);
        cellArtist = new CellArtist(this);
    }

    public void setGameLogic(GameLogic gameLogic){
        gL=gameLogic;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path p = new Path();
        Paint s = new Paint();

        if ((lastHeight!=canvas.getHeight())|(lastWidth!=canvas.getWidth())) {
            recalculateGrid(canvas.getWidth(), canvas.getHeight());
            cellArtist.initialize(cS);
        }

        for (int x=0; x<X_FIELD_SIZE; x++) {
            for (int y=0; y<Y_FIELD_SIZE; y++){
                cellArtist.drawCell(canvas,leftSpacing+x*cS,topSpacing+y*cS,gL.getPlayer(x,y),gL.getKilled(x,y),0);
            }
        }



        gridPath.offset(leftSpacing,topSpacing);
        canvas.drawPath(gridPath, gridPaint);
    }

    private void recalculateGrid(int x, int y) {
        gridPath = new Path();
        gridPaint = new Paint();

        cS = (float) Math.floor((Math.min(x,y)-SPACING)/((double) Math.max(X_FIELD_SIZE,Y_FIELD_SIZE)));
        leftSpacing= (float)((x-X_FIELD_SIZE* cS)/2.0);
        topSpacing= (float)((y-Y_FIELD_SIZE* cS)/2.0);

        gridPath.reset();
        gridPath.moveTo(0,0);

        for(int i = 0; i<=X_FIELD_SIZE;i++) {
            gridPath.moveTo(cS *i,-GRID_LINE_WIDTH/2);
            gridPath.lineTo(cS *i,Y_FIELD_SIZE* cS +GRID_LINE_WIDTH/2);
        }
        for(int i = 0; i<=Y_FIELD_SIZE;i++) {
            gridPath.moveTo(-GRID_LINE_WIDTH/2, cS *i);
            gridPath.lineTo(X_FIELD_SIZE* cS +GRID_LINE_WIDTH/2, cS *i);
        }

        gridPaint.setColor(GRID_LINE_COLOR);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(GRID_LINE_WIDTH);
    }

    private void cellPressed(int x, int y) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if ((motionEvent.getAction()==MotionEvent.ACTION_DOWN)|(motionEvent.getAction()==MotionEvent.ACTION_MOVE)){
            selectedX=motionEvent.getX();
            selectedY=motionEvent.getY();
        }
        this.invalidate();
        return true;
    }
}
