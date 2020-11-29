package com.example.reto2apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private Button ingresar;
    private EditText user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresar = findViewById(R.id.main_btn_ingresar);
        user = findViewById(R.id.main_edt_User);
        db=FirebaseFirestore.getInstance();

        ingresar.setOnClickListener(this::login);

    }

    private void login(View v){
        String username = user.getText().toString().trim();


        if(username.isEmpty()){
            Toast.makeText(this, "Escribe un nombre de entrenador", Toast.LENGTH_SHORT).show();

        }else{
            Intent i = new Intent(this, AtraparActivity.class);
            Query query = db.collection("users").whereEqualTo("username", username);
            query.get().addOnCompleteListener(
                    task -> {
                        if (task.getResult().getDocuments().size() >0){
                            for (DocumentSnapshot document : task.getResult().getDocuments()){
                                User user = document.toObject(User.class);
                                i.putExtra("id", user.getId());
                                Toast.makeText(this, "inicio sesion usuario existente", Toast.LENGTH_SHORT).show();
                                startActivity(i);
                                finish();
                            }
                        }else{
                            User user = new User(username, UUID.randomUUID().toString());
                            db.collection("users").document(user.getId()).set(user);
                            i.putExtra("id", user.getId());
                            Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                        }
                    }
            );
        }
    }

}