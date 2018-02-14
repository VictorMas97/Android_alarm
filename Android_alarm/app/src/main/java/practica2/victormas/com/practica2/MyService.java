package practica2.victormas.com.practica2;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service
{
    String medicine; // Declaramos un "String"

    public MyService()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        long hours, minutes; // Declaramos dos variable de tipo "long"

        medicine = intent.getStringExtra("MEDICAMENT"); // Introducimos la cadena de texto/String através del "intent" recibido por parámetro a la variable "medicine"

        hours = intent.getIntExtra("HOUR", 0) * 3600000;  // Introducimos el número de horas através del "intent" recibido por parámetro a la variable "hours" y pasamos de horas a milisegundos, multiplicandolo por 3.600.000
        minutes = intent.getIntExtra("MINUTES", 0) * 60000; // Introducimos el número de minutos através del "intent" recibido por parámetro a la variable "minutes" y pasamos de minutos a milisegundos, multiplicandolo por 60.000

        new Accountant(hours + minutes,1000).start(); //Instanciamos un nuevo cronómetro que suene una vez, dentro de el tiempo que falte hasta la hora que marca el "TmePiccker"
        return super.onStartCommand(intent, flags, startId);
    }




    class Accountant extends CountDownTimer
    {
        Accountant(long millisInFuture, long countDownInterval)  // Creamos una clase cronómetro y le tenemos que pasar por parámetro dos "long" uno que nos dice cuando va a finalizar y otra que nos advierte cuantas veces lo va a comprobar(no estoy muy seguro)
        {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
        }

        @Override
        public void onFinish() // Cuando el contador acaba crea un nuevo intent y le pasa como extra la variable "medicine"
        {
            Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("MEDICAMENT", medicine);
            startActivity(intent);
        }
    }
}