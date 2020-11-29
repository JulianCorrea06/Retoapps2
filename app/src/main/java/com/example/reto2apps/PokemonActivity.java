package com.example.reto2apps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

public class PokemonActivity extends AppCompatActivity {

    private TextView name;
    private TextView type;
    private TextView def;
    private TextView atk;
    private TextView vel;
    private TextView vida;
    private Button soltar;
    private Pokemon pokemon;
    private ImageView pokeImg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        pokemon = (Pokemon) getIntent().getExtras().getSerializable("pokemon");

        name = findViewById(R.id.details_tv_name);
        type = findViewById(R.id.details_tv_tipo);
        def = findViewById(R.id.details_tv_defensa);
        atk = findViewById(R.id.details_tv_ataque);
        vel = findViewById(R.id.details_tv_vel);
        vida = findViewById(R.id.details_tv_vida);
        soltar = findViewById(R.id.details_btn_soltar);
        pokeImg = findViewById(R.id.details_img_pokemon);

        name.setText(pokemon.getName().substring(0,1).toUpperCase() + pokemon.getName().substring(1));
        vida.setText(""+pokemon.getStats().get(0).getStatValue());
        atk.setText(""+pokemon.getStats().get(1).getStatValue());
        def.setText(""+pokemon.getStats().get(2).getStatValue());
        vel.setText(""+pokemon.getStats().get(5).getStatValue());

        Glide.with(this).load(pokemon.getSprites().getFrontSprite()).into(pokeImg);

        String type = pokemon.getType().get(0).getType().getName();
        if(pokemon.getType().size() >1){
            type += ", "+pokemon.getType().get(1).getType().getName();
        }


        soltar.setOnClickListener(this::reciclarPokemon);

    }

    private void reciclarPokemon(View v){
        String uid = getIntent().getExtras().getString("id");
        FirebaseFirestore.getInstance().collection("users").document(uid).collection("pokemons").document(pokemon.getDbId()).delete();
        finish();
    }

}