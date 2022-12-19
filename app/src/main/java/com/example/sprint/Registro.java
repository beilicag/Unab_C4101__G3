package com.example.sprint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registro extends AppCompatActivity {

    private Button linkLogin, btnFormRegist;
    private EditText editPasswordRegist, editPasswordConfirm, editEmailRegist;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        linkLogin = (Button) findViewById(R.id.linkLogin);
        btnFormRegist = (Button) findViewById(R.id.btnFormRegist);
        editPasswordRegist = (EditText) findViewById(R.id.editPasswordRegist);
        editPasswordConfirm = (EditText) findViewById(R.id.editPasswordConfirm);
        editEmailRegist = (EditText) findViewById(R.id.editEmailRegist);
        mAuth = FirebaseAuth.getInstance();

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        btnFormRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmailRegist.getText().toString().trim();
                String password = editPasswordRegist.getText().toString().trim();
                String confirmar = editPasswordConfirm.getText().toString().trim();
                if (password.compareTo(confirmar) == 0){
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(),"Error al crear usuario", Toast.LENGTH_SHORT).show();
                                    Log.e("UserCreate", e.toString());
                                }
                            });
                }else{
                    Toast.makeText(getApplicationContext(), "las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}