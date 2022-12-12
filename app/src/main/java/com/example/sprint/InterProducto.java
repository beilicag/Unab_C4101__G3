package com.example.sprint;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InterProducto extends AppCompatActivity {

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
        interFoto.setImageResource(intentIN.getIntExtra("image",0));

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