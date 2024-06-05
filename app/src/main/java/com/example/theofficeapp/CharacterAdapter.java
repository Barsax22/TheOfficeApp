package com.example.theofficeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private Context context;
    private List<Character> characterList;

    public CharacterAdapter(Context context, List<Character> characterList) {
        this.context = context;
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.nameTextView.setText(character.getName());
        holder.descriptionTextView.setText(character.getDescription());
        holder.imageView.setImageResource(context.getResources().getIdentifier(character.getImage(), "drawable", context.getPackageName()));
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public void updateList(List<Character> newList) {
        characterList = newList;
        notifyDataSetChanged();
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView descriptionTextView;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
