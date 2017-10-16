package t32games.viruswars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;


public class GameField extends View {
    private static int X_FIELD_SIZE = 10;
    private static int Y_FIELD_SIZE = 10;
    private static int BACKGROUND_COLOR = Color.WHITE;
    private static int GRID_LINE_COLOR = Color.BLACK;
    private static int GRID_LINE_WIDTH =3;

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
        
        if ((lastHeight!=getHeight())|(lastWidth!=getWidth())) {
            recalculateGrid(getWidth(), getHeight());
        }

        for (int x=0; x<X_FIELD_SIZE; x++) {
            for (int y=0; y<Y_FIELD_SIZE; y++){

            }
        }

    }

    private void recalculateGrid(int x, int y) {
        gridPath = new Path();
        gridPaint = new Paint();

        cellSize=(int) Math.floor(Math.min(x,y)/((double) Math.max(x,y)));
        leftSpacing=(int) ((x-X_FIELD_SIZE*cellSize)/2.0);
        topSpacing=(int) ((y-Y_FIELD_SIZE*cellSize)/2.0);

        gridPath.reset();
        gridPath.moveTo(0,0);

        for(int i = 0; i<=X_FIELD_SIZE;i++) {
            gridPath.moveTo(cellSize*i,0);
            gridPath.lineTo(cellSize*i,Y_FIELD_SIZE*cellSize);
        }
        for(int i = 0; i<=Y_FIELD_SIZE;i++) {
            gridPath.moveTo(0,cellSize*i);
            gridPath.lineTo(X_FIELD_SIZE*cellSize,cellSize*i);
        }

        gridPaint.setColor(GRID_LINE_COLOR);
        gridPaint.setStrokeWidth(GRID_LINE_WIDTH);
    }
}
