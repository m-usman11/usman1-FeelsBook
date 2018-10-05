package com.example.usman1_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ImageView imgView = findViewById(R.id.editImg);
        final TextView dateView = findViewById(R.id.dateEditText);
        final TextView commentView = findViewById(R.id.commentEditText);
        Button saveButton = findViewById(R.id.saveEditButton);
        Button deleteButton = findViewById(R.id.deleteEditButton);

        Intent intent = getIntent();
        final int emotionKey = intent.getIntExtra(HistoryAdapter.EMOTIONKEY, 0);
        final Emotion emote = MainActivity.getEmotionFromList(emotionKey);

        imgView.setImageResource(emote.getImage());
        dateView.setText(emote.getDate());
        commentView.setText(emote.getComment());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emote.setComment(commentView.getText().toString());
                emote.setDate(dateView.getText().toString());
                MainActivity.setOrKillEmotion(emotionKey, emote, true);
                MainActivity.modified = true;
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setOrKillEmotion(emotionKey, emote, false);
                MainActivity.modified = true;
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
