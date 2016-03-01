package cat.flx.t06_notificationtest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.button1)).setOnClickListener(this);
        ((Button) findViewById(R.id.button2)).setOnClickListener(this);
        ((Button) findViewById(R.id.button3)).setOnClickListener(this);
        ((Button) findViewById(R.id.button4)).setOnClickListener(this);
        ((Button) findViewById(R.id.button5)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonClear)).setOnClickListener(this);
    }

    @Override public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button1: test1(); break;
            case R.id.button2: test2(); break;
            case R.id.button3: test3(); break;
            case R.id.button4: test4(); break;
            case R.id.button5: test5(); break;
            case R.id.buttonClear: clear(); break;
        }
    }

    public void test1() {
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Test 1")
                .setContentText("Contenido")
                .setLights(Color.GREEN, 500, 500)
                .setSound(soundUri);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    private int contador2 = 0;
    public void test2() {
        contador2++;
        Uri soundUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap bitmap =
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(bitmap)
                .setContentTitle("Test 2")
                .setContentText(contador2 + " notificaciones")
                .setContentInfo(String.valueOf(contador2))
                .setLights(Color.RED, 100, 900)
                .setSound(soundUri);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, builder.build());
    }

    private ArrayList<String> eventos3;
    public void test3() {
        if (eventos3 == null) eventos3 = new ArrayList<String>();
        String nuevo = SimpleDateFormat.getTimeInstance().format(new Date());
        eventos3.add(nuevo);
        String mensaje = nuevo;
        if (eventos3.size() > 1) mensaje = "Ultimo: " + mensaje;
        Uri soundUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap bitmap =
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(bitmap)
                .setContentTitle("Test 3")
                .setContentText(mensaje)
                .setContentInfo(String.valueOf(eventos3.size()))
                .setLights(Color.BLUE, 500, 500)
                .setSound(soundUri);
        NotificationCompat.InboxStyle estilo =
                new NotificationCompat.InboxStyle();
        estilo.setBigContentTitle("Test 3. Detalles:");
        for (String evento : eventos3) estilo.addLine(evento);
        builder.setStyle(estilo);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(3, builder.build());
    }

    public void test4() {
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap bitmap =
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(bitmap)
                .setContentTitle("Test 4")
                .setContentText("Haz clic para hacer foto")
                .setLights(Color.YELLOW, 250, 1250)
                .setSound(soundUri);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(4, builder.build());
    }

    public void test5() {
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap bitmap =
                BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Intent iFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PendingIntent piFoto = PendingIntent.getActivity(this, 0, iFoto, 0);
        Intent iLlamada = new Intent(Intent.ACTION_DIAL);
        iLlamada.setData(Uri.parse("tel:"));
        PendingIntent piLlamada = PendingIntent.getActivity(this, 0, iLlamada, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(bitmap)
                .setContentTitle("Test 5")
                .setContentText("Ya es la hora!")
                .setLights(Color.YELLOW, 250, 1250)
                .setSound(soundUri)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Opciones:"))
                .addAction (android.R.drawable.ic_menu_camera,
                        "Foto", piFoto)
                .addAction (android.R.drawable.ic_menu_call,
                        "Llamada", piLlamada);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(5, builder.build());
    }

    public void clear() {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
}