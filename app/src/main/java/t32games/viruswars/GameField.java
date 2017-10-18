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
    private static final int BACKGROUND_COLOR = Color.WHITE;
    private static final int GRID_LINE_COLOR = Color.BLACK;
    private static final float GRID_LINE_WIDTH =0.05f;
    private static final float GAME_FIELD_RATIO = 0.95f;

    private CellArtist cellArtist;
    private TurnControl tC;
    private FieldStateSnapshot fieldState = null;

    private Path gridPath;
    private Paint gridPaint;
    private int lastHeight = -1;
    private int lastWidth = -1;
    private int clickX = 0;
    private int clickY = 0;
    private float cS =0;
    private float leftSpacing=0;
    private float topSpacing=0;



    public GameField(Context context, AttributeSet attrs) {
        super(context, attrs);
        cellArtist = new CellArtist();
        this.setBackgroundColor(BACKGROUND_COLOR);
    }

    public void setTurnControl(TurnControl turnControl) {
        tC=turnControl;
    }

    public void setFieldData(FieldStateSnapshot fSS) {
        fieldState = fSS;
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

        if (fieldState==null) {
            canvas.drawColor(BACKGROUND_COLOR);
        } else {

            for (int x = 0; x < GameLogic.X_FIELD_SIZE; x++) {
                for (int y = 0; y < GameLogic.Y_FIELD_SIZE; y++) {
                    //TODO: change selected
                    cellArtist.drawCell(canvas, leftSpacing + x * cS, topSpacing + y * cS, fieldState.getPlayer(x,y), fieldState.getKilled(x, y), fieldState.getAvailability(x,y));
                }
            }
        }

        gridPath.offset(leftSpacing,topSpacing);
        canvas.drawPath(gridPath, gridPaint);
    }

    private void recalculateGrid(int x, int y) {
        gridPath = new Path();
        gridPaint = new Paint();

        cS = (float) Math.floor(Math.min(x,y)*GAME_FIELD_RATIO/((double) Math.max(GameLogic.X_FIELD_SIZE,GameLogic.Y_FIELD_SIZE)));
        leftSpacing= ((x-GameLogic.X_FIELD_SIZE*cS)/2f);
        topSpacing= ((y-GameLogic.Y_FIELD_SIZE*cS)/2f);

        gridPath.reset();
        gridPath.moveTo(0,0);

        for(int i = 0; i<=GameLogic.X_FIELD_SIZE;i++) {
            gridPath.moveTo(cS*i,-GRID_LINE_WIDTH*cS/2f);
            gridPath.lineTo(cS*i,GameLogic.Y_FIELD_SIZE*cS +cS*GRID_LINE_WIDTH/2f);
        }
        for(int i = 0; i<=GameLogic.Y_FIELD_SIZE;i++) {
            gridPath.moveTo(-GRID_LINE_WIDTH*cS/2f, cS *i);
            gridPath.lineTo(GameLogic.X_FIELD_SIZE*cS +GRID_LINE_WIDTH*cS/2f, cS*i);
        }

        gridPaint.setColor(GRID_LINE_COLOR);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(GRID_LINE_WIDTH);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //TODO: visual touch pointer
                break;
            case MotionEvent.ACTION_UP:
                resolveClick(motionEvent);
                tC.cellPressed(clickX, clickY);
                break;
        }
        return true;
    }

    private boolean resolveClick(MotionEvent motionEvent) {
        float cx = motionEvent.getX() - leftSpacing;
        float cy = motionEvent.getY() - topSpacing;
        if((cx<cS*GameLogic.X_FIELD_SIZE)&(cy<cS*GameLogic.Y_FIELD_SIZE)) {
            clickX=(int) Math.floor(cx/GameLogic.X_FIELD_SIZE);
            clickY=(int) Math.floor(cy/GameLogic.Y_FIELD_SIZE);
            return true;
        }
        return false;
    }
}
