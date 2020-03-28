package com.example.jokempo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    ImageButton pedra;
    ImageButton papel;
    ImageButton tesoura;

    ImageView jogador1;
    ImageView jogador2;

    Animation aparece;
    Animation some;

    int jogada1;
    int jogada2;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alex_play);

        pedra = findViewById(R.id.pedra);
        papel = findViewById(R.id.papel);
        tesoura = findViewById(R.id.tesoura);

        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);

        aparece = new AlphaAnimation(0,1);
        some = new AlphaAnimation(1,0);

        aparece.setDuration(2000);
        some.setDuration(200);

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.INVISIBLE);
                jogador2.startAnimation(aparece);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sortearPlayer2();
                jogador2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                verificarJogada();
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void tocouBotao(View view){
        tocaSom();
        jogador1.setScaleX(-1f);

        switch (view.getId()){
            case (R.id.pedra): jogador1.setImageResource(R.drawable.pedra);
            jogada1 = 1;
            break;
            case (R.id.tesoura): jogador1.setImageResource(R.drawable.papel);
            jogada1 = 2;
            break;
            case (R.id.papel): jogador1.setImageResource(R.drawable.tesoura);
            jogada1 = 3;
            break;
        }

        jogador2.setImageResource(R.drawable.interrogacao);
        jogador2.startAnimation(some);
    }

    public void sortearPlayer2 (){
        Random random = new Random();
        int resultado = random.nextInt(3);

        switch (resultado){
            case 0: jogador2.setImageResource(R.drawable.pedra);
            jogada2 = 1;
            break;
            case 1: jogador2.setImageResource(R.drawable.papel);
            jogada2 = 2;
            break;
            case 2: jogador2.setImageResource(R.drawable.tesoura);
            jogada2 = 3;
            break;
        }
    }

    public void verificarJogada (){
        if(jogada1 == jogada2){
            Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show();
        }
        if(jogada1 == 1 && jogada2 == 2){
            Toast.makeText(this, "Jogador 2 Ganhou!", Toast.LENGTH_SHORT).show();
        }
        if (jogada1 == 2 && jogada2 == 3){
            Toast.makeText(this, "Jogador 2 Ganhou!", Toast.LENGTH_SHORT).show();
        }
        if (jogada1 == 3 && jogada2 == 1){
            Toast.makeText(this, "Jogador 2 Ganhou!", Toast.LENGTH_SHORT).show();
        }
        if (jogada2 == 1 && jogada1 == 2){
            Toast.makeText(this, "Jogador 1 Ganhou!", Toast.LENGTH_SHORT).show();
        }
        if (jogada2 == 2 && jogada1 == 3){
            Toast.makeText(this, "Jogador 1 Ganhou!", Toast.LENGTH_SHORT).show();
        }
        if (jogada2 == 3 && jogada1 == 1){
            Toast.makeText(this, "Jogador 1 Ganhou!", Toast.LENGTH_SHORT).show();
        }
    }
    public void tocaSom (){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        super.onDestroy();
    }
}
