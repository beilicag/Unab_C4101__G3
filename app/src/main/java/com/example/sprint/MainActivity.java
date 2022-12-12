package com.example.sprint;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    //@SuppressLint("MissingInflatedId")
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

        //dbLocal = new DBLocal(this);
        /*
        Producto producto1 = new Producto("Botas para hielo", "Lorem ipsum dolor sit amet", 250000, "");
        Producto producto2 = new Producto("Guantes de beisbol", "Lorem ipsum dolor sit amet", 250000, "");
        Producto producto3 = new Producto("Pesas mancuerna", "Lorem ipsum dolor sit amet", 250000, "");
        Producto producto4 = new Producto("raquetas tenis de mesa", "Lorem ipsum dolor sit amet", 250000, "");

        dbLocal.insertData(producto1);
        dbLocal.insertData(producto2);
        dbLocal.insertData(producto3);
        dbLocal.insertData(producto4);

        arrayProductos.add(producto1);
        arrayProductos.add(producto2);
        arrayProductos.add(producto3);
        arrayProductos.add(producto4);
*/
        //arrayProductos = productService.cursorToArrayList(dbLocal.getData());
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
        switch (item.getItemId()){
            case R.id.itemAgregar:
                Intent intent = new Intent(getApplicationContext(), ProductForm.class);
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