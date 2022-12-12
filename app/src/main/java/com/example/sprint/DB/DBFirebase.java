package com.example.sprint.DB;

import android.util.Log;


import androidx.annotation.NonNull;

import com.example.sprint.Adaptadores.ProductoAdapter;
import com.example.sprint.Entidades.Producto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBFirebase {

    private FirebaseFirestore db;

    public DBFirebase(){
        this.db = FirebaseFirestore.getInstance();
    }

    public void insertData(Producto producto){
        // Create a new user with a first and last name
        Map<String, Object> prod = new HashMap<>();
        prod.put("id", producto.getId());
        prod.put("name", producto.getName());
        prod.put("description", producto.getDescription());
        prod.put("price", producto.getPrice());
        prod.put("image", producto.getImage());

        // Add a new document with a generated ID
        db.collection("products").add(prod);
    }

    public void getData(ProductoAdapter adapter, ArrayList<Producto> list){
        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Producto producto = new Producto(
                                        document.getData().get("id").toString(),
                                        document.getData().get("name").toString(),
                                        document.getData().get("description").toString(),
                                        Integer.parseInt(document.getData().get("price").toString()),
                                        document.getData().get("image").toString()
                                );
                                list.add(producto);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.e("Error document", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
