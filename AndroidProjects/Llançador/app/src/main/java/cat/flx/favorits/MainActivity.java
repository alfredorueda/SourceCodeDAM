package cat.flx.favorits;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

//Implementa l'OnClickListener per poder utilitzar "this" quan se li fa un setOnClickListener
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Es declaren tots els botons de l'aplicació
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //S'agafa el botó i s'assigna a una variable
        button1 = (Button) findViewById(R.id.btn01);
        button2 = (Button) findViewById(R.id.btn02);
        button3 = (Button) findViewById(R.id.btn03);
        button4 = (Button) findViewById(R.id.btn04);
        button5 = (Button) findViewById(R.id.btn05);
        button6 = (Button) findViewById(R.id.btn06);
        button7 = (Button) findViewById(R.id.btn07);
        button8 = (Button) findViewById(R.id.btn08);
        button9 = (Button) findViewById(R.id.btn09);
        button10 = (Button) findViewById(R.id.btn10);

        //A cada botó se li assigna un escoltador d'events de click
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);

    }

    @Override public void onClick(View arg0) {
        //Genera un intent per obrir el navegador
        if (arg0.equals(button1)) {
            Uri uri = Uri.parse("http://www.google.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        //Obre la càmera
        if (arg0.equals(button2)){
            Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(i);
        }
        //Obre la vista del marcador de telèfon amb el 112 marcat
        if (arg0.equals(button3)){
            Uri uri = Uri.parse("tel:112");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        }
        //Obre una vista de Google Maps amb unes coordenades exactes (Stucom)
        if (arg0.equals(button4)){
            Uri uri = Uri.parse("geo:41.3858557,2.1653083");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        //Escriu un missatge a un destinatari i queda preparat per enviar-lo
        if (arg0.equals(button5)){
            Uri uri = Uri.parse("smsto:666666666");
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", "Hello darkness my old friend.");
            startActivity(intent);
        }
        //Obre l'aplicació de mail i hi genera un nou correu, llest per enviar-lo
        if (arg0.equals(button6)){
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("message/rfc822");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, "test@gmail.com");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hola");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Cuerpo del mensaje");

            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
        //Obre la calculadora
        if (arg0.equals(button7)){
            Intent i = new Intent();
            i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
            startActivity(i);
        }
        //Obre el calendari i es prepara per generar un event
        if (arg0.equals(button8)){
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            startActivity(intent);
        }
        //Obre l'app dels settings
        if (arg0.equals(button9)){
            Intent dialogIntent = new Intent(android.provider.Settings.ACTION_SETTINGS);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialogIntent);
        }
        //Mostra els contactes que hi ha al telèfon
        if (arg0.equals(button10)){
            Intent i = new Intent();
            i.setComponent(new ComponentName("com.android.contacts", "com.android.contacts.DialtactsContactsEntryActivity"));
            i.setAction("android.intent.action.MAIN");
            i.addCategory("android.intent.category.LAUNCHER");
            i.addCategory("android.intent.category.DEFAULT");
            startActivity(i);
        }
    }
}
