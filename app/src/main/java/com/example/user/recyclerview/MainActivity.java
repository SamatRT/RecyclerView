package com.example.user.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Song> songs = new ArrayList<>();

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songs.add(new Song(R.drawable.stop , "Song1", "Samat", "angel_cuplet.txt.txt", "text1", "text1"));
        songs.add(new Song(R.drawable.stop , "Song2", "Kiril", "text1.tx", "text1", "text1"));
        songs.add(new Song(R.drawable.stop , "Song3", "Vova", "text1.tx", "text1", "text1"));
        songs.add(new Song(R.drawable.stop , "Song4", "Vadim", "text1.tx", "text1", "text1"));
        songs.add(new Song(R.drawable.stop , "Song5", "Chong", "text1.tx", "text1", "text1"));
        songs.add(new Song(R.drawable.stop , "Song6", "Sasha", "text1.tx", "text1", "text1"));

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(songs));
    }
}