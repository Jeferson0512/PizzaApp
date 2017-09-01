package com.jeferson.pizzaapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class PrincipalActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button btnOrdenar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        videoView = (VideoView) findViewById(R.id.videoview);
        btnOrdenar = (Button) findViewById(R.id.btnOrdenar);

        videoView.setMediaController(new MediaController(this));
//        videoView.setVideoURI(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.pizza));
        videoView.start();

        btnOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PrincipalActivity.this, ServicioActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
