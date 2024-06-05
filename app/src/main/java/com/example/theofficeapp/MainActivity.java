package com.example.theofficeapp;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private List<Character> characterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);
        TextView tvShowName = findViewById(R.id.tvShowName);
        tvShowName.setText("The Office");
        characterList = loadCharactersFromJson();
        characterAdapter = new CharacterAdapter(this, characterList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(characterAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    private List<Character> loadCharactersFromJson() {
        List<Character> characters = new ArrayList<>();
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("Characters");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String name = obj.getString("name");
                String description = obj.getString("description");
                String image = obj.getString("image");

                characters.add(new Character(name, description, image));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return characters;
    }

    private void filter(String text) {
        List<Character> filteredList = new ArrayList<>();
        for (Character character : characterList) {
            if (character.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(character);
            }
        }
        characterAdapter.updateList(filteredList);
    }
}
