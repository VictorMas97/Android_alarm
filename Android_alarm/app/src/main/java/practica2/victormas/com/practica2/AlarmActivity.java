package practica2.victormas.com.practica2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AlarmActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        TextView text;  // Declaramos un "TextView"

        text = findViewById(R.id.textView); // Asocia la variable "text" con el "TextView" generado en el xml

        text.setText(getIntent().getStringExtra("MEDICAMENT"));  // Introducimos el texto que hemos recibido mediante el intent
    }
}