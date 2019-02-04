package com.example.user.recyclerview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {

    ArrayList<Song> songs = new ArrayList<>();

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView title, author, PrimeCuplet, PrimePripev, LastText;
        Button detailButton;
        ImageView music_photo;
        LinearLayout list_itemLayout;
        ImageView mImageView;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            detailButton = itemView.findViewById(R.id.detailButton);
            music_photo = itemView.findViewById(R.id.music_photo);
            list_itemLayout = itemView.findViewById(R.id.list_itemLayout);
        }
    }

    public Adapter(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        AdapterViewHolder holder = new AdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterViewHolder adapterViewHolder, int position) {
        Song song = songs.get(position);
        adapterViewHolder.music_photo.setImageResource(song.getmImageResource());

        final String title = song.getTitle();
        final String author = song.getAuthor();
        final String PrimeCuplet = song.getPrimeCuplet();
        final String PrimePripev = song.getPrimePripev();
        final String LastText = song.getLastText();

        adapterViewHolder.title.setText(title);
        adapterViewHolder.author.setText(author);

        adapterViewHolder.detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".SongActivity");
                adapterViewHolder.list_itemLayout.getContext().startActivity(intent);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(adapterViewHolder.list_itemLayout.getContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("cuplet", PrimeCuplet);
                editor.putString("pripev", PrimePripev);
                editor.putString("last", LastText);
                editor.apply();
            }
        });

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
