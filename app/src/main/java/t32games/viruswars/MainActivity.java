package t32games.viruswars;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{
    GameField gameField;
    Button buttonTurn1, buttonTurn2, buttonTurn3, buttonEndTurn;
    TextView textViewPlayerName;
    TmpGameClass tmpGameClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameField = (GameField) findViewById(R.id._gameField);
        buttonTurn1 = (Button) findViewById(R.id._buttonTurn1);
        buttonTurn2 = (Button) findViewById(R.id._buttonTurn2);
        buttonTurn3 = (Button) findViewById(R.id._buttonTurn3);
        buttonEndTurn = (Button) findViewById(R.id._buttonEndTurn);
        textViewPlayerName = (TextView) findViewById(R.id._textViewPlayerName);
        tmpGameClass = new TmpGameClass();

        buttonTurn1.setOnClickListener(this);
        buttonTurn2.setOnClickListener(this);
        buttonTurn3.setOnClickListener(this);
        buttonEndTurn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

    }
}
