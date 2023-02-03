package com.example.reproductor;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imPlay;
    private ImageView imPause;
    private ImageView imParar;
    private TextView tv1;
    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imPlay = (ImageView) findViewById(R.id.play);
        imPause = (ImageView) findViewById(R.id.pause);
        imParar = (ImageView) findViewById(R.id.parar);
        music = MediaPlayer.create(this, R.raw.cancion);
    }
    //Este método se ejecutará cuando se presiona el boton
    public void Reproducir (View view){
        imPlay.setVisibility(imPlay.INVISIBLE);
        imPause.setVisibility(imPlay.VISIBLE);
        imParar.setVisibility(imPlay.INVISIBLE);
        music.start();

    }
    public void Pausar (View view){
        imPlay.setVisibility(imPlay.VISIBLE);
        imPause.setVisibility(imPlay.INVISIBLE);
        imParar.setVisibility(imPlay.INVISIBLE);
        music.pause();

    }
    public void Parar (View view){
        imPlay.setVisibility(imPlay.INVISIBLE);
        imPause.setVisibility(imPlay.INVISIBLE);
        imParar.setVisibility(imPlay.VISIBLE);
        music.stop();
        music = MediaPlayer.create(this, R.raw.cancion);

    }

}