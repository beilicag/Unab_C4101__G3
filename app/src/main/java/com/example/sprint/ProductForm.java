package com.example.sprint;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.sprint.DB.DBFirebase;
import com.example.sprint.DB.DBLocal;
import com.example.sprint.Entidades.Producto;

public class ProductForm extends AppCompatActivity {

    //private ProductService  productService;
    private DBLocal dbLocal;
    private DBFirebase dbFirebase;
    private Button btnFormProduct;
    private ImageView imgFormProduct;
    private EditText editNameFormProduct, editDescriptionFormProduct, editPriceFormProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        btnFormProduct = (Button) findViewById(R.id.btnFormProduct);
        imgFormProduct = (ImageView) findViewById(R.id.imgFormProduct);
        editNameFormProduct = (EditText) findViewById(R.id.editNameFormProduct);
        editDescriptionFormProduct = (EditText) findViewById(R.id.editDescriptionFormProduct);
        editPriceFormProduct = (EditText) findViewById(R.id.editPriceFormProduct);

        try {
            dbLocal = new DBLocal(this);
            dbFirebase = new DBFirebase();
        }catch (Exception e){
            Log.e("Error DB", e.toString());
        }
        //BDLocal new DBLocal(this);

        btnFormProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto product = new Producto(
                        editNameFormProduct.getText().toString(),
                        editDescriptionFormProduct.getText().toString(),
                        Integer.parseInt(editPriceFormProduct.getText().toString()), ""
                );
                //dbLocal.insertData(product);
                dbFirebase.insertData(product);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}