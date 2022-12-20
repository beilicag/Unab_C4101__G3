package com.example.sprint;

import android.content.Context;
import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sprint.Entidades.Producto;
import com.google.firebase.storage.StorageReference;

public class InterProducto extends AppCompatActivity {

    private final int GALLERY_INTENT = 1;
    private String urlImage = "";
    private StorageReference storageReference;
    private ActivityResultLauncher<String> content;

    private TextView interTitulo, interDescripcion, interPrecio;
    private ImageView interFoto;
    private Button interBoton;
    private Button interBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inter_producto);

        interBoton = (Button) findViewById(R.id.interBoton);
        interTitulo = (TextView) findViewById(R.id.interTitulo);
        interDescripcion = (TextView) findViewById(R.id.interDescripcion);
        interPrecio = (TextView) findViewById(R.id.interPrecio);
        interFoto = (ImageView) findViewById(R.id.interFoto);
        interBtnBack = (Button) findViewById(R.id.interBtnBack);

        Intent intentIN = getIntent();
        interTitulo.setText(intentIN.getStringExtra("name"));
        interDescripcion.setText(intentIN.getStringExtra("description"));
        interPrecio.setText(String.valueOf(intentIN.getIntExtra("price", 0)));
        //interFoto.setImageResource(intentIN.getIntExtra("image",0));
        interFoto.setImageResource(R.drawable.sin_foto);

        Glide.with(InterProducto.this)
                .load(intentIN.getStringExtra("image"))
                .override(500,500)
                .into(interFoto);

        interBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        interBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }
}