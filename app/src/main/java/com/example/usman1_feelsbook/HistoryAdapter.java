package com.example.usman1_feelsbook;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * RecyclerView Adapter for the Emotions history
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<Emotion> emotionList;
    public static final String EMOTIONKEY = "usman1Feelsbook_EmotionKey";

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView commentTextView;
        TextView dateTextView;
        ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            commentTextView = view.findViewById(R.id.commentText);
            dateTextView = view.findViewById(R.id.dateText);
            imageView = view.findViewById(R.id.listImage);
        }
    }

    // Set the dataset emotionList
    public HistoryAdapter(ArrayList<Emotion> myDataset) {
        emotionList = myDataset;
    }

    // Create new views
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Set contents of items
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Emotion currEmote = emotionList.get(position);
        holder.commentTextView.setText(currEmote.getComment());
        holder.dateTextView.setText(currEmote.getDate());
        holder.imageView.setImageResource(currEmote.getImage());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditActivity.class);
                intent.putExtra(EMOTIONKEY, position);
                view.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of emotionList
    @Override
    public int getItemCount() {
        return emotionList.size();
    }
}
