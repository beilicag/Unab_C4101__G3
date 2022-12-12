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
import com.example.sprint.InterProducto; //MainActivity2
import com.example.sprint.R;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {
    private ArrayList<Producto> arrayProductos;
    private Context context;

    public ProductoAdapter(Context context, ArrayList<Producto> arrayProductos) {
        this.arrayProductos = arrayProductos;
        this.context = context;
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

        Button tempButton = (Button) view.findViewById (R.id.tempButton);
        ImageView tempFoto = (ImageView) view.findViewById (R.id.tempFoto);
        TextView tempTitulo = (TextView) view.findViewById(R.id.tempTitulo);
        TextView tempPecio = (TextView) view.findViewById(R.id.tempPrecio);
        TextView tempDescripcion = (TextView) view.findViewById(R.id.tempDescripcion);

        Producto producto = arrayProductos.get(i);

        tempFoto.setImageResource(R.drawable.shop_10_large);
        tempTitulo.setText(producto.getName());
        tempPecio.setText(String.valueOf(producto.getPrice()));
        tempDescripcion.setText(producto.getDescription());

        tempFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context.getApplicationContext(), InterProducto.class);
                intent.putExtra("name", producto.getName());
                intent.putExtra("description", producto.getDescription());
                intent.putExtra("price", producto.getPrice());
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
                intent.putExtra("price", producto.getPrice());
                intent.putExtra("image", producto.getImage());
                context.startActivity(intent);

            }
        });


        return view;
    }
}
