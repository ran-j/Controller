package br.ucam_campos.controller;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static AudioManager myAudioManager;

    private Button btsil;
    private Button btvib;
    private Button btnor;

    private Button btvolu;
    private Button btvold;
    private Button btvolm;
    private Button btvoln;
    private Context context;



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noto3();

        // audio manager
        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //volume
        btvolu = (Button) findViewById(R.id.button);
        btvold = (Button) findViewById(R.id.button2);
        btvolm = (Button) findViewById(R.id.button3);
        btvoln = (Button) findViewById(R.id.button7);


        //perfis
        btvib = (Button) findViewById(R.id.button5);
        btnor = (Button) findViewById(R.id.button4);
        btsil = (Button) findViewById(R.id.button6);


        btsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(MainActivity.this, "Modo Silêncioso", Toast.LENGTH_LONG).show();
            }
        });

        btnor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(MainActivity.this, "Modo Normal", Toast.LENGTH_LONG).show();
            }
        });

        btvib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(MainActivity.this, "Modo Vibracal", Toast.LENGTH_LONG).show();
            }
        });

        btvolu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aumentar();
            }
        });

        btvold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diminuir();
            }
        });

        btvolm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mutar();
            }
        });


        btvoln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = 1;
                myAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void noto3() {
        Notification.Builder notif;
        NotificationManager nm;
        notif = new Notification.Builder(getApplicationContext());
        notif.setSmallIcon(R.drawable.bell_nor);
        notif.setContentTitle("Controle de Audio");
        Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notif.setSound(path);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent yesReceive = new Intent();
        yesReceive.setAction(AppConstant.YES_ACTION);
        PendingIntent pendingIntentYes = PendingIntent.getBroadcast(this, 12345, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT);
        notif.addAction(R.drawable.mais, "", pendingIntentYes);


        Intent yesReceive2 = new Intent();
        yesReceive2.setAction(AppConstant.STOP_ACTION);
        PendingIntent pendingIntentYes2 = PendingIntent.getBroadcast(this, 12345, yesReceive2, PendingIntent.FLAG_UPDATE_CURRENT);
        notif.addAction(R.drawable.menos, "", pendingIntentYes2);


        Intent yesReceive3 = new Intent();
        yesReceive3.setAction(AppConstant.STOP_MAYBE);
        PendingIntent pendingIntentYes3 = PendingIntent.getBroadcast(this, 12345, yesReceive3, PendingIntent.FLAG_UPDATE_CURRENT);
        notif.addAction(R.drawable.mute, "", pendingIntentYes3);

        nm.notify(10, notif.getNotification());
    }

    static void aumentar(){
        myAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
    }

    static void diminuir(){
        myAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
    }

    static void mutar(){
        myAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
    }

}