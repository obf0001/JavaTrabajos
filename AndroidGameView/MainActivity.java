package com.example.animacionkirby;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private Button pp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        pp = new Button(this);
        setContentView(R.layout.activity_main);
        gameView = findViewById(R.id.pantalla);
        pp = findViewById(R.id.pp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }
    /*Para crear la pantalla, crearemos una clase que herede de SurfaceView, que es una clase que nos permitirá
    pintar elementos  en  su  interior,  como  si  se  tratase  de  un  lienzo.  Al  crear  esta  clase,
    podremos  crear  un  objeto  de  ella en  la  interfaz  gráfica.  Esta  clase  deberá  sobrescribir  el  método
    onDraw,  el  cual  se  encargará  de  dibujar  todos  los elementos en la pantalla, y al que le llegará por
    parámetro un objeto de la clase Canvas, que será nuestro “lienzo”.*/

    //The Canvas class holds the "draw" calls. To draw something, you need 4 basic components:
// A Bitmap to hold the pixels, a Canvas to host the draw calls (writing into the bitmap),
// a drawing primitive (e.g. Rect, Path, text, Bitmap),
// and a paint (to describe the colors and styles for the drawing).

   public void playpause(View view){
       gameView.playpause();
   }
}