package com.example.sprint.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sprint.Entidades.Producto;
import com.example.sprint.InterProducto;
import com.example.sprint.R;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Producto> arrayProductos;

    public ProductoAdapter(Context context, ArrayList<Producto> arrayProductos) {
        this.context = context;
        this.arrayProductos = arrayProductos;
    }

    @Override
    public int getCount() {
        return arrayProductos.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayProductos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view = layoutInflater.inflate(R.layout.producto_template, null);

        Producto producto = arrayProductos.get(i);
        Button tempButton = (Button) view.findViewById (R.id.tempButton);
        ImageView tempFoto = (ImageView) view.findViewById (R.id.tempFoto);
        TextView tempTitulo = (TextView) view.findViewById(R.id.tempTitulo);
        TextView tempPecio = (TextView) view.findViewById(R.id.tempPrecio);
        TextView tempDescripcion = (TextView) view.findViewById(R.id.tempDescripcion);

        tempFoto.setImageResource(producto.getImage());
        tempTitulo.setText(producto.getName());
        tempPecio.setText(String.valueOf(producto.getPrice()));
        tempDescripcion.setText(producto.getDescription());

        tempFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context.getApplicationContext(), InterProducto.class);
                intent.putExtra("name", producto.getName());
                intent.putExtra("description", producto.getDescription());
                intent.putExtra("price", String.valueOf(producto.getPrice()));
                intent.putExtra("image", producto.getImage());
                context.startActivity(intent);

            }
        });

        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context.getApplicationContext(), InterProducto.class);
                intent.putExtra("name", producto.getName());
                intent.putExtra("description", producto.getDescription());
                intent.putExtra("price", String.valueOf(producto.getPrice()));
                intent.putExtra("image", producto.getImage());
                context.startActivity(intent);

            }
        });


        return view;
    }
}
