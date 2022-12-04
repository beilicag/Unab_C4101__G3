package com.example.sprint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.sprint.Adaptadores.ProductoAdapter;
import com.example.sprint.Entidades.Producto;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {
    private ListView listViewProductos;
    private ProductoAdapter productoAdapter;
    private ArrayList<Producto> arrayProductos;

    @Override
    protected  void onCreate(Bundle saveIntanceStates) {
        super.onCreate(saveIntanceStates);
        setContentView(R.layout.activity_productos);

        listViewProductos = (ListView) findViewById(R.id.listViewProductos);
        arrayProductos = new ArrayList<>();

        Producto producto1 = new Producto("Botas para hielo", "Lorem ipsum dolor sit amet", 250000, R.drawable.shop_10_large);
        Producto producto2 = new Producto("Guantes de beisbol", "Lorem ipsum dolor sit amet", 250000, R.drawable.shop_15_large);
        Producto producto3 = new Producto("Pesas mancuerna", "Lorem ipsum dolor sit amet", 250000, R.drawable.shop_18_large);
        Producto producto4 = new Producto("raquetas tenis de mesa", "Lorem ipsum dolor sit amet", 250000, R.drawable.shop_31_large);

        arrayProductos.add(producto1);
        arrayProductos.add(producto2);
        arrayProductos.add(producto3);
        arrayProductos.add(producto4);

        productoAdapter = new ProductoAdapter(this,arrayProductos);

        listViewProductos.setAdapter(productoAdapter);
    }
}