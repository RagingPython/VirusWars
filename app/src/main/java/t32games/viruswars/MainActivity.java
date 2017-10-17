package t32games.viruswars;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity{
    GameField gameField;
    Button buttonNewGame, buttonEndTurn;
    TextView textViewPlayerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameField = (GameField) findViewById(R.id._gameField);
        buttonNewGame = (Button) findViewById(R.id._buttonNewGame);
        buttonEndTurn = (Button) findViewById(R.id._buttonEndTurn);
        textViewPlayerName = (TextView) findViewById(R.id._textViewPlayerName);

        //buttonTurn1.setOnClickListener(this);
        //buttonTurn2.setOnClickListener(this);
        buttonNewGame.setOnClickListener(gameField);
        buttonEndTurn.setOnClickListener(gameField);
        gameField.setOnTouchListener(gameField);
    }

}
