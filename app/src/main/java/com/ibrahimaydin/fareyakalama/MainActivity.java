package com.ibrahimaydin.fareyakalama;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
TextView timeText;
TextView skorText;
int skor;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
Handler handler;
Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText=(TextView) findViewById(R.id.timeText);
        skorText=(TextView) findViewById(R.id.skorText);
        skor=0;
        imageView=findViewById(R.id.imageView);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageArray =new ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};

        sakla();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("SÜRE: "+l/1000);
            }

            @Override
            public void onFinish() {
            timeText.setText("Zaman bitti");
            handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Yeniden?");
                if(skor>=25){
                    alert.setMessage("Skorunuz: "+skor+" tebrikler");
                }
                else alert.setMessage("Skorunuz: "+skor +"\nTekrar başlamak ister misin");

                alert.setPositiveButton("evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent=getIntent();
                    finish();
                    startActivity(intent);
                    }
                });
                alert.setNegativeButton("hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Oyun bitti",Toast.LENGTH_LONG).show();
                        finish();//hayır denince oyunu kapatır
                    }
                });
                alert.show();
            }
        }.start();
    }
    public void skorArttir (View view){
skor++;
skorText.setText("SKOR: "+skor);
    }
    public void sakla(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
            }
                Random random=new Random();
                int i=random.nextInt(8);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,400); // 10 saniyede 25 farklı görüntü
        }

        };
        handler.post(runnable);

    }

}