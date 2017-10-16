package t32games.viruswars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class GameField extends View {
    private static int X_FIELD_SIZE = 10;
    private static int Y_FIELD_SIZE = 10;
    private static int BACKGROUND_COLOR = Color.WHITE;
    private static int GRID_LINE_COLOR = Color.BLACK;
    private static int GRID_LINE_WIDTH =5;

    private Path gridPath;
    private Paint gridPaint;
    private int lastHeight = -1;
    private int lastWidth = -1;
    private int cellSize=0;
    private int leftSpacing=0;
    private int topSpacing=0;



    public GameField(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        
        if ((lastHeight!=canvas.getHeight())|(lastWidth!=canvas.getWidth())) {
            recalculateGrid(canvas.getWidth(), canvas.getHeight());
        }

        for (int x=0; x<X_FIELD_SIZE; x++) {
            for (int y=0; y<Y_FIELD_SIZE; y++){

            }
        }

        canvas.drawPath(gridPath, gridPaint);
    }

    private void recalculateGrid(int x, int y) {
        gridPath = new Path();
        gridPaint = new Paint();

        cellSize=(int) Math.floor(Math.min(x,y)/((double) Math.max(X_FIELD_SIZE,Y_FIELD_SIZE)));
        leftSpacing=(int) ((x-X_FIELD_SIZE*cellSize)/2.0);
        topSpacing=(int) ((y-Y_FIELD_SIZE*cellSize)/2.0);

        gridPath.reset();
        gridPath.moveTo(0,0);

        for(int i = 0; i<=X_FIELD_SIZE;i++) {
            gridPath.moveTo(cellSize*i,0);
            gridPath.lineTo(cellSize*i,Y_FIELD_SIZE*cellSize);
            Log.d("Line_to", String.valueOf(Y_FIELD_SIZE*cellSize));
        }
        for(int i = 0; i<=Y_FIELD_SIZE;i++) {
            gridPath.moveTo(0,cellSize*i);
            gridPath.lineTo(X_FIELD_SIZE*cellSize,cellSize*i);
        }

        gridPath.offset(leftSpacing,topSpacing);

        gridPaint.setColor(GRID_LINE_COLOR);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(GRID_LINE_WIDTH);
    }
}
