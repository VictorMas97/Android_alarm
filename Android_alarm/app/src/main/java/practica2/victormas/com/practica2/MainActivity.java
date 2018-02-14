package practica2.victormas.com.practica2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TimePicker timePicker; // Declaramos un "TimePicker"
        Calendar cal; // Declaramos un calendario de la clase "calendar"
        final EditText editText; // Declaramos un "EditText"
        Button button; // Declaramos un "Button"

        timePicker = findViewById(R.id.timeP); // Asocia la variable "timePicker" con el "TimePicker" generado en el xml
        button = findViewById(R.id.button); // Asocia la variable "button" con el "Button" generado en el xml
        editText = findViewById(R.id.editT); // Asocia la variable "editText" con el "EditText" generado en el xml


        timePicker.setIs24HourView(true);  //Pone nuestro "TimePicker" en formato de 24 horas
        cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));  // Ponemos el calendario de la clase "calendar" en nuestra zona horaria
        timePicker.setCurrentHour(cal.get(cal.HOUR_OF_DAY));  // Introducimos la hora correcta en nuestro "TimePicker", en formato 24 horas

        button.setOnClickListener(new View.OnClickListener() // Añadimos un listener al botón y Llamamos a la función "onClick"
        {
            @Override
            public void onClick(View view) // Esta función se llama cuando el usuario presiona al botón / la variable "button"
            {
                Calendar c; //Creamos un segundo calendario de la clase "calendar"


                c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid")); // Ponemos el segundo calendario de la clase "calendar" en nuestra zona horaria

                int hour = timePicker.getCurrentHour() - c.get(c.HOUR_OF_DAY); // Definimos "hour" y le decimos que sea igual a la hora que marca el "TimePicker" menos la hora que marque el reloj del dispositivo cuando el usuario le de al botón
                int minutes = timePicker.getCurrentMinute() - c.get(c.MINUTE); // Definimos "minutes" y le decimos que sea igual a los minutos que marca el "TimePicker" menos los minutos que marque el reloj del dispositivo cuando el usuario le de al botón

                if(minutes < 0)  //Si la variable "minutes" es menor que 0,significa que se ha incrementado en 1 la hora y le sumará a dicha variable 60 y a la variable "hour" le restará 1
                {
                    minutes += 60;
                    hour -= 1;
                }

                if(hour < 0)  //Si la variable "hour" es menor que 0, significa que ha pasado un ciclo entero o se ha cambiado de día y le sumará a dicha variable 24
                {
                    hour += 24;
                }

                else if(hour == 0) //Si la variable "hour" vale 0 hay 3 posibilidades
                {
                    switch ( minutes ) //La opción depende de la variable "minutes"
                    {
                        case 0:

                            hour = 24; // Ponemos la variable "hour" igual a 24 para que la alarma suene en un día
                            Toast.makeText(getApplicationContext(), "The alarm will sound in 1 day", Toast.LENGTH_SHORT).show();
                            break;

                        case 1:

                            Toast.makeText(getApplicationContext(), "The alarm will sound in " + minutes + " minute", Toast.LENGTH_SHORT).show();
                            break;

                        default:

                            Toast.makeText(getApplicationContext(), "The alarm will sound in " + minutes + " minutes", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                else if(hour == 1) //Si la variable "hour" vale 1 hay 3 posibilidades
                {
                    switch ( minutes ) //La opción depende de la variable "minutes"
                    {
                        case 0:

                            Toast.makeText(getApplicationContext(), "The alarm will sound in " + hour + " hour", Toast.LENGTH_SHORT).show();
                            break;

                        case 1:

                            Toast.makeText(getApplicationContext(), "The alarm will sound in " + hour + " hour and " + minutes + " minute", Toast.LENGTH_SHORT).show();
                            break;

                        default:

                            Toast.makeText(getApplicationContext(), "The alarm will sound in " + hour + " hour and " + minutes + " minutes", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                else if(hour > 1) //Si la variable "hour" vale más de 1 hay 3 posibilidades
                {
                    switch ( minutes ) //La opción depende de la variable "minutes"
                    {
                        case 0:

                            Toast.makeText(getApplicationContext(), "The alarm will sound in " + hour + " hours", Toast.LENGTH_SHORT).show();
                            break;

                        case 1:

                            Toast.makeText(getApplicationContext(), "The alarm will sound in " + hour + " hours and " + minutes + " minute", Toast.LENGTH_SHORT).show();
                            break;

                        default:

                            Toast.makeText(getApplicationContext(), "The alarm will sound in " + hour + " hours and " + minutes + " minutes", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                Intent intent = new Intent(getApplicationContext(), MyService.class);  // Declaramos un "Intent"
                intent.putExtra("MEDICAMENT", editText.getText().toString());  // Introducimos al "intent" como extra el texto de nuestro "editText"
                intent.putExtra("HOUR", hour);  // Introducimos al "intent" como extra las horas que le quedan para que suene la alarma
                intent.putExtra("MINUTES", minutes);  // Introducimos al "intent" como extra los minutos que le quedan para que suene la alarma
                startService(intent); // Iniciamos un nuevo servicio con el "intent" incluido
            }
        });
    }
}