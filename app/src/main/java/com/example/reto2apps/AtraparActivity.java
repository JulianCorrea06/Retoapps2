package com.example.reto2apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;

import java.util.UUID;

public class AtraparActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText pokemonNameEdt;
    private EditText searchEdt;
    private String uid;
    private Button catchBtn;
    private Button searchBtn;
    private RecyclerView pokeViewList;
    private LinearLayoutManager layoutManager;
    private PokemonsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atrapar);

        db = FirebaseFirestore.getInstance();
        uid = getIntent().getExtras().getString("id");
        searchEdt = findViewById(R.id.atrapar_search_edt);
        pokemonNameEdt = findViewById(R.id.atrapar_catch_edt);
        catchBtn = findViewById(R.id.atrapar_catch_btn);
        searchBtn = findViewById(R.id.atrapar_search_btn);
        pokeViewList = findViewById(R.id.atrapar_lista_rv);

        pokeViewList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new PokemonsAdapter(uid);

        pokeViewList.setLayoutManager(layoutManager);
        pokeViewList.setAdapter(adapter);

        searchBtn.setOnClickListener(this::filerPokemon);
        catchBtn.setOnClickListener(this::catchPokemon);
        updatePokemons();

    }


    private void filerPokemon(View v){
            adapter.clearList();
            String pokeName = searchEdt.getText().toString().trim().toLowerCase();

            if (!pokeName.isEmpty()){
                Query query  = db.collection("users").document(uid).collection("pokemons").whereEqualTo("name",pokeName);
                query.get().addOnCompleteListener(
                        task -> {
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                adapter.addPokemon(document.toObject(Pokemon.class));
                            }

                        }
                );
            }else{
                updatePokemons();
            }

    }

    private void updatePokemons(){
        adapter.clearList();
        Query query = db.collection("users").document(uid).collection("pokemons").orderBy("time", Query.Direction.DESCENDING);
        query.get().addOnCompleteListener(
                task -> {
                    for (DocumentSnapshot document : task.getResult().getDocuments()){
                        adapter.addPokemon(document.toObject(Pokemon.class));
                    }
                }

        );
    }

    private void catchPokemon(View v){
            HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();

        String pokeName = pokemonNameEdt.getText().toString().trim().toLowerCase();

        if(pokeName.isEmpty()){
            Toast.makeText(this, "Escribe un nombre de un pokemon", Toast.LENGTH_SHORT).show();
        }else{
            new Thread(
                    () -> {
                        String response = https.GETrequest(Constants.BASE_URL + pokeName);


                        if(response !=null){
                        Pokemon pokemon = new Gson().fromJson(response, Pokemon.class);
                        pokemon.setTime(System.currentTimeMillis());
                        pokemon.setDbId(UUID.randomUUID().toString());
                        db.collection("users").document(uid).collection("pokemons").document(pokemon.getDbId()).set(pokemon);



                            runOnUiThread(
                                    () ->{
                                        adapter.addPokemonToStart(pokemon);
                                        String messageToast = pokemon.getName().substring(0, 1).toUpperCase() + pokemon.getName().substring(1) + "atrapado";

                                        Toast.makeText(this, messageToast,Toast.LENGTH_LONG).show();

                                    }
                            );
                    }



                    }
            ).start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatePokemons();
    }
}