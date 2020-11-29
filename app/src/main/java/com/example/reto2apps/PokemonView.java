package com.example.reto2apps;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonView extends RecyclerView.ViewHolder implements View.OnClickListener{


    private Pokemon pokemon;
    private ConstraintLayout root;
    private ImageView sprite;
    private TextView name;
    private OnPokemonClicked listener;

    public PokemonView(ConstraintLayout root){

        super(root);
        this.root = root;

        sprite = root.findViewById(R.id.pokelist_img_sprite);
        name = root.findViewById(R.id.pokelist_tv_name);
        root.setOnClickListener(this);
    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public TextView getName() {
        return name;
    }

    @Override
    public void onClick(View v) {
        if (listener!= null) listener.OnPokemonClicked(this.pokemon, this.root);

    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void setListener(OnPokemonClicked listener) {
        this.listener = listener;
    }

    public interface OnPokemonClicked{

        void OnPokemonClicked(Pokemon pokemon, View v);

    }
}
