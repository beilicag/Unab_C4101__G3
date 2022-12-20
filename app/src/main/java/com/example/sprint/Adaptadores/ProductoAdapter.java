package com.example.sprint.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sprint.DB.DBFirebase;
import com.example.sprint.Entidades.Producto;
import com.example.sprint.InterProducto;
import com.example.sprint.MainActivity;
import com.example.sprint.ProductForm;
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
        ImageButton btnDelete = (ImageButton) view.findViewById (R.id.btnDelete);
        ImageButton btnEdit = (ImageButton) view.findViewById (R.id.btnEdit);
        ImageView tempFoto = (ImageView) view.findViewById (R.id.tempFoto);
        TextView tempTitulo = (TextView) view.findViewById(R.id.tempTitulo);
        TextView tempPecio = (TextView) view.findViewById(R.id.tempPrecio);
        TextView tempDescripcion = (TextView) view.findViewById(R.id.tempDescripcion);



        Producto producto = arrayProductos.get(i);

        tempFoto.setImageResource(R.drawable.sin_foto);
        tempTitulo.setText(producto.getName());
        tempPecio.setText(String.valueOf(producto.getPrice()));
        tempDescripcion.setText(producto.getDescription());

        Glide.with(context)
                .load(producto.getImage())
                .override(500,500)
                .into(tempFoto);



        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBFirebase dbFirebase;
                dbFirebase = new DBFirebase();
                dbFirebase.deleteData(producto.getId());
                Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
                context.startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), ProductForm.class);
                intent.putExtra("edit", true);
                intent.putExtra("id", producto.getId());
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
                intent.putExtra("edit", true);
                intent.putExtra("id", producto.getId());
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
