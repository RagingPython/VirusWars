package t32games.viruswars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class GameField extends View implements View.OnTouchListener, View.OnClickListener{
    private static final int BACKGROUND_COLOR = Color.WHITE;
    private static final int GRID_LINE_COLOR = Color.BLACK;
    private static final float GRID_LINE_WIDTH =0.05f;
    private static final float GAME_FIELD_RATIO = 0.95f;

    private CellArtist cellArtist;
    private GameLogic gL;
    private TurnControl tC;
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
        gL = new GameLogic();
        tC = new TurnControl(gL);
        cellArtist = new CellArtist();
        this.setBackgroundColor(BACKGROUND_COLOR);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Path p = new Path();
        Paint s = new Paint();

        if ((lastHeight!=canvas.getHeight())|(lastWidth!=canvas.getWidth())) {
            recalculateGrid(canvas.getWidth(), canvas.getHeight());
            lastHeight=canvas.getHeight();
            lastWidth=canvas.getWidth();
            cellArtist.initialize(cS);
        }

        if (gL.getPlayerTurn()==0) {
            canvas.drawColor(BACKGROUND_COLOR);
        } else {

            for (int x = 0; x < gL.X_FIELD_SIZE; x++) {
                for (int y = 0; y < gL.Y_FIELD_SIZE; y++) {
                    //TODO: change selected
                    cellArtist.drawCell(canvas, leftSpacing + x * cS, topSpacing + y * cS, gL.getPlayer(x, y), gL.getKilled(x, y), 0);
                }
            }
        }

        gridPath.offset(leftSpacing,topSpacing);
        canvas.drawPath(gridPath, gridPaint);
    }

    private void recalculateGrid(int x, int y) {
        gridPath = new Path();
        gridPaint = new Paint();

        cS = (float) Math.floor(Math.min(x,y)*GAME_FIELD_RATIO/((double) Math.max(gL.X_FIELD_SIZE,gL.Y_FIELD_SIZE)));
        leftSpacing= ((x-gL.X_FIELD_SIZE*cS)/2f);
        topSpacing= ((y-gL.Y_FIELD_SIZE*cS)/2f);

        gridPath.reset();
        gridPath.moveTo(0,0);

        for(int i = 0; i<=gL.X_FIELD_SIZE;i++) {
            gridPath.moveTo(cS*i,-GRID_LINE_WIDTH*cS/2f);
            gridPath.lineTo(cS*i,gL.Y_FIELD_SIZE*cS +cS*GRID_LINE_WIDTH/2f);
        }
        for(int i = 0; i<=gL.Y_FIELD_SIZE;i++) {
            gridPath.moveTo(-GRID_LINE_WIDTH*cS/2f, cS *i);
            gridPath.lineTo(gL.X_FIELD_SIZE*cS +GRID_LINE_WIDTH*cS/2f, cS*i);
        }

        gridPaint.setColor(GRID_LINE_COLOR);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(GRID_LINE_WIDTH);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (gL.getPlayerTurn()!=0) {
            if ((motionEvent.getAction() == MotionEvent.ACTION_DOWN) | (motionEvent.getAction() == MotionEvent.ACTION_MOVE)) {
                selectedX = motionEvent.getX();
                selectedY = motionEvent.getY();
            }
            this.invalidate();
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        tC.onClick(view);
    }
}
