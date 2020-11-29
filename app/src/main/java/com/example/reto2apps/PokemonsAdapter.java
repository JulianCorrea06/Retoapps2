package com.example.reto2apps;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PokemonsAdapter extends RecyclerView.Adapter<PokemonView> implements PokemonView.OnPokemonClicked {

    private ArrayList<Pokemon> pokemons;
    private String id;

    public PokemonsAdapter(String id) {
        this.id = id;
        pokemons = new ArrayList<>();

    }

    @NonNull
    @Override
    public PokemonView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.pokemon_list, parent, false);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        PokemonView pokemonView = new PokemonView(rowroot);
        return pokemonView;


    }

    @Override
    public void onBindViewHolder(@NonNull PokemonView holder, int position) {
        Pokemon currentPoke= pokemons.get(position);
        holder.getName().setText(currentPoke.getName());
        holder.setPokemon(currentPoke);

        Glide.with(holder.getRoot()).load(currentPoke.getSprites().getFrontSprite()).into(holder.getSprite());
        holder.setListener(this);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void addPokemon (Pokemon pokemon){
        pokemons.add(pokemon);

        this.notifyDataSetChanged();
    }

    public void addPokemonToStart (Pokemon pokemon){
        pokemons.add(0, pokemon);
        this.notifyDataSetChanged();

    }

    public void clearList(){
        pokemons.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public void OnPokemonClicked(Pokemon pokemon, View v) {
        Intent i = new Intent(v.getContext(), PokemonActivity.class);
        i.putExtra("pokemon", pokemon);
        i.putExtra("id", id);
        v.getContext().startActivity(i);

    }
}
