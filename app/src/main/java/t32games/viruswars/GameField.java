package t32games.viruswars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class GameField extends View implements View.OnTouchListener{
    private static int X_FIELD_SIZE = 10;
    private static int Y_FIELD_SIZE = 10;
    private static int BACKGROUND_COLOR = Color.WHITE;
    private static int GRID_LINE_COLOR = Color.BLACK;
    private static float GRID_LINE_WIDTH =4;
    private static int SPACING = 20;

    private Path gridPath;
    private Paint gridPaint;
    private int lastHeight = -1;
    private int lastWidth = -1;
    private float cellSize=0;
    private float leftSpacing=0;
    private float topSpacing=0;
    private float selectedX=-1;
    private float selectedY=-1;



    public GameField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path p = new Path();
        Paint s = new Paint();

        if ((lastHeight!=canvas.getHeight())|(lastWidth!=canvas.getWidth())) {
            recalculateGrid(canvas.getWidth(), canvas.getHeight());
        }

        for (int x=0; x<X_FIELD_SIZE; x++) {
            for (int y=0; y<Y_FIELD_SIZE; y++){
                if ((selectedX>(leftSpacing+x*cellSize))&(selectedX<(leftSpacing+(x+1)*cellSize))&(selectedY>(topSpacing+y*cellSize))&(selectedY<(topSpacing+(y+1)*cellSize))) {
                    p.reset();
                    p.addRect(0,0, cellSize,cellSize, Path.Direction.CCW);
                    p.offset(x*cellSize,y*cellSize);
                    p.offset(leftSpacing,topSpacing);
                    s.setColor(Color.RED);
                    canvas.drawPath(p,s);
                }
            }
        }



        gridPath.offset(leftSpacing,topSpacing);
        canvas.drawPath(gridPath, gridPaint);
    }

    private void recalculateGrid(int x, int y) {
        gridPath = new Path();
        gridPaint = new Paint();

        cellSize= (float) Math.floor((Math.min(x,y)-SPACING)/((double) Math.max(X_FIELD_SIZE,Y_FIELD_SIZE)));
        leftSpacing= (float)((x-X_FIELD_SIZE*cellSize)/2.0);
        topSpacing= (float)((y-Y_FIELD_SIZE*cellSize)/2.0);

        gridPath.reset();
        gridPath.moveTo(0,0);

        for(int i = 0; i<=X_FIELD_SIZE;i++) {
            gridPath.moveTo(cellSize*i,-GRID_LINE_WIDTH/2);
            gridPath.lineTo(cellSize*i,Y_FIELD_SIZE*cellSize+GRID_LINE_WIDTH/2);
        }
        for(int i = 0; i<=Y_FIELD_SIZE;i++) {
            gridPath.moveTo(-GRID_LINE_WIDTH/2,cellSize*i);
            gridPath.lineTo(X_FIELD_SIZE*cellSize+GRID_LINE_WIDTH/2,cellSize*i);
        }

        gridPaint.setColor(GRID_LINE_COLOR);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(GRID_LINE_WIDTH);
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
