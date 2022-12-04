package com.example.sprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

        interTitulo = (TextView) findViewById(R.id.interTitulo);
        interFoto = (ImageView) findViewById(R.id.interFoto);
        interDescripcion = (TextView) findViewById(R.id.interDescripcion);
        interPrecio = (TextView) findViewById(R.id.interPrecio);
        interBoton = (Button) findViewById(R.id.interBoton);
        interBtnBack = (Button) findViewById(R.id.interBtnBack);

        Intent intentIN = getIntent();
        interTitulo.setText(intentIN.getStringExtra("name"));
        interDescripcion.setText(intentIN.getStringExtra("description"));
        interPrecio.setText(intentIN.getStringExtra("price"));
        interFoto.setImageResource(intentIN.getIntExtra("image",0));

        interBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Productos.class);
                startActivity(intent);
            }
        });

        interBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Productos.class);
                startActivity(intent);

            }
        });
    }
}