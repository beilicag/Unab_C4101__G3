package com.example.sprint;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sprint.Adaptadores.ProductoAdapter;
import com.example.sprint.DB.DBFirebase;
import com.example.sprint.DB.DBLocal;
import com.example.sprint.Entidades.Producto;
import com.example.sprint.servicios.ProductService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBLocal dbLocal;
    private DBFirebase dbFirebase;

    private ProductService productService;
    private ListView listViewProductos;
    private ProductoAdapter productoAdapter;
    private ArrayList<Producto> arrayProductos;

    @Override
    protected  void onCreate(Bundle saveIntanceStates) {
        super.onCreate(saveIntanceStates);
        setContentView(R.layout.activity_main);

        arrayProductos = new ArrayList<>();
        productService = new ProductService();

        try {
            dbLocal = new DBLocal(this);
            dbFirebase = new DBFirebase();
        }catch (Exception e){
        Log.e("Error DB", e.toString());
        }

        listViewProductos = (ListView) findViewById(R.id.listViewProductos);
        productoAdapter = new ProductoAdapter(this, arrayProductos);
        listViewProductos.setAdapter(productoAdapter);
        dbFirebase.getData(productoAdapter, arrayProductos);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override

    public  boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.itemAgregar:
                intent = new Intent(getApplicationContext(), ProductForm.class);
                startActivity(intent);
                return  true;
            case R.id.mapLocations:
                ArrayList<String> latitudes = new ArrayList<>();
                ArrayList<String> longitudes = new ArrayList<>();
                for (int i=0; i< arrayProductos.size(); i++){
                    latitudes.add(arrayProductos.get(i).getLatitud());
                    longitudes.add(arrayProductos.get(i).getLongitud());
                }
                intent = new Intent(getApplicationContext(), Maps.class);
                intent.putStringArrayListExtra("latitudes", latitudes);
                intent.putStringArrayListExtra("longitudes", longitudes);
                startActivity(intent);
                return  true;

            case R.id.itemFavorite:
                Toast.makeText(getApplicationContext(), "Favoritos", Toast.LENGTH_SHORT).show();
                return  true;
            case R.id.itemShare:
                Toast.makeText(getApplicationContext(), "Compartir", Toast.LENGTH_SHORT).show();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}