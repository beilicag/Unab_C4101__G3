package com.example.sprint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sprint.DB.DBFirebase;
import com.example.sprint.DB.DBLocal;
import com.example.sprint.Entidades.Producto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

public class ProductForm extends AppCompatActivity {

    private DBLocal dbLocal;
    private DBFirebase dbFirebase;
    private Button btnFormProduct;
    private ImageView imgFormProduct;
    private EditText editNameFormProduct, editDescriptionFormProduct, editPriceFormProduct;
    private String latitud;
    private String longitud;
    private MapView map;
    private MapController mapController;

    private final int GALLERY_INTENT = 1;
    private String urlImage = "";
    private StorageReference storageReference;
    private ActivityResultLauncher<String> content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        storageReference = FirebaseStorage.getInstance().getReference();
        content = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

                Uri uri = result;
                StorageReference filepath = storageReference.child("images").child(uri.getLastPathSegment());
                filepath.putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(ProductForm.this, "Imagen cargada", Toast.LENGTH_SHORT).show();
                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Uri downloadUrl = uri;
                                        urlImage = downloadUrl.toString();
                                        Toast.makeText(ProductForm.this,  urlImage, Toast.LENGTH_SHORT).show();
                                        Glide.with(ProductForm.this)
                                                .load(downloadUrl)
                                                .override(500,500)
                                                .into(imgFormProduct);
                                    }
                                });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProductForm.this, "Error en carga", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        latitud = "0";
        longitud = "0";
        btnFormProduct = (Button) findViewById(R.id.btnFormProduct);
        imgFormProduct = (ImageView) findViewById(R.id.imgFormProduct);
        editNameFormProduct = (EditText) findViewById(R.id.editNameFormProduct);
        editDescriptionFormProduct = (EditText) findViewById(R.id.EditDescriptionFormProduct);
        editPriceFormProduct = (EditText) findViewById(R.id.editPriceFormProduct);

        try {
            dbLocal = new DBLocal(this);
            dbFirebase = new DBFirebase();
        }catch (Exception e){
            Log.e("Error DB", e.toString());
        }
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        map = (MapView) findViewById(R.id.mapForm);
        map.setBuiltInZoomControls(true);
        mapController = (MapController) map.getController();

        GeoPoint colombia = new GeoPoint(4.570868, -74.297333);

        mapController.setCenter(colombia);
        mapController.setZoom(10);
        map.setMultiTouchControls(true);

        MapEventsReceiver mapEventsReceiver = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                Marker marker = new Marker(map);
                marker.setPosition(p);
                map.getOverlays().add(marker);
                latitud = String.valueOf(p.getLatitude());
                longitud = String.valueOf(p.getLongitude());
                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p){
                return false;
            }
        };

        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, mapEventsReceiver);
        map.getOverlays().add(mapEventsOverlay);

        Intent intentIN = getIntent();
        if(intentIN.getBooleanExtra("edit", false)){
            editNameFormProduct.setText(intentIN.getStringExtra("name"));
            editDescriptionFormProduct.setText(intentIN.getStringExtra("description"));
            editPriceFormProduct.setText(intentIN.getStringExtra("price"));
        }
        //BDLocal new DBLocal(this);

        btnFormProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto product = new Producto(
                        editNameFormProduct.getText().toString(),
                        editDescriptionFormProduct.getText().toString(),
                        Integer.parseInt(editPriceFormProduct.getText().toString().trim()),
                        urlImage, latitud, longitud
                );
                if(intentIN.getBooleanExtra("edit", false)){
                    product.setId(intentIN.getStringExtra("id"));
                    dbFirebase.updateData(product);

                }else {
                    //dbLocal.insertData(product);
                    dbFirebase.insertData(product);
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        imgFormProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.launch("image/*");
            }
        });

    }
}