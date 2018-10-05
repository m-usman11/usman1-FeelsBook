package com.example.usman1_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    // https://mincong-h.github.io/2017/02/16/convert-date-to-string-in-java/
    // https://stackoverflow.com/questions/7873480/android-one-onclick-method-for-multiple-buttons
    // https://stackoverflow.com/questions/3914404/how-to-get-current-moment-in-iso-8601-format-with-date-hour-and-minute
    // https://www.youtube.com/watch?v=Vyqz_-sJGFk
    // https://eclass.srv.ualberta.ca/pluginfile.php/4548736/mod_resource/content/4/File_Storage_in_Java_Notes.pdf
    // http://www.codebind.com/java-tutorials/java-example-convert-date-string/

    public static final String FILENAME = "file.sav";
    private static ArrayList<Emotion> emotions = new ArrayList<>();
    public static Boolean modified = false;
    private EditText commentText;
    private RecyclerView history;
    private HistoryAdapter adapter;
    private RecyclerView.LayoutManager histLayoutManager;

    private TextView loveCount, joyCount, sadCount, angryCount, surprisedCount, scaredCount;
    int lovec, joyc, sadc, angryc, surprisedc, scaredc;


    public static Emotion getEmotionFromList(int num) {
        return emotions.get(num);
    }

    public static void setOrKillEmotion(int num, Emotion emotion, Boolean set) {
        if (set)
            emotions.set(num, emotion);
        else    // kill
            emotions.remove(emotion);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (modified) {
            appendToFile();
            modified = false;
        }
        loadFromFile();
        commentText = findViewById(R.id.commentBox);
        history = findViewById(R.id.historyView);

        histLayoutManager = new LinearLayoutManager(this);
        history.setLayoutManager(histLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        clearComment();
        loadFromFile();
        adapter = new HistoryAdapter(emotions);
        history.setAdapter(adapter);
    }

    private void countFeels() {
        lovec = joyc = sadc = angryc = surprisedc = scaredc = 0;
        loveCount = findViewById(R.id.loveCount);
        joyCount = findViewById(R.id.joyCount);
        sadCount = findViewById(R.id.sadCount);
        angryCount = findViewById(R.id.angryCount);
        surprisedCount = findViewById(R.id.surprisedCount);
        scaredCount = findViewById(R.id.scaredCount);
        for (int i=0; i<emotions.size(); i++) {
            switch(emotions.get(i).getImage()) {
                case R.drawable.love:
                    lovec++;
                    break;
                case R.drawable.joy:
                    joyc++;
                    break;
                case R.drawable.sad:
                    sadc++;
                    break;
                case R.drawable.angry:
                    angryc++;
                    break;
                case R.drawable.surprised:
                    surprisedc++;
                    break;
                case R.drawable.scared:
                    scaredc++;
                    break;
            }
        }
        loveCount.setText(Integer.toString(lovec));
        joyCount.setText(Integer.toString(joyc));
        sadCount.setText(Integer.toString(sadc));
        angryCount.setText(Integer.toString(angryc));
        surprisedCount.setText(Integer.toString(surprisedc));
        scaredCount.setText(Integer.toString(scaredc));
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            ObjectInputStream in = new ObjectInputStream(fis);
            emotions = (ArrayList<Emotion>) in.readObject();
            in.close();
            fis.close();
        } catch (ClassNotFoundException e) {
            emotions = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        countFeels();
        Collections.sort(emotions);
        Collections.reverse(emotions);
    }

    private void appendToFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
            ObjectOutputStream writer = new ObjectOutputStream(fos);
            writer.writeObject(emotions);
            writer.close();
            fos.close();
        } catch (FileNotFoundException e) {
            emotions = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        countFeels();
        Collections.sort(emotions);
        Collections.reverse(emotions);
    }

    private String getCommentText() {
        return commentText.getText().toString();
    }

    private void clearComment() {
        commentText.setText("");
    }

    public void addFeel(View view) {
        String comment = getCommentText();
        clearComment();
        switch(view.getId()) {
            case R.id.loveButton:
                emotions.add(0, new Love(comment));
                break;
            case R.id.joyButton:
                emotions.add(0, new Joy(comment));
                break;
            case R.id.sadButton:
                emotions.add(0, new Sad(comment));
                break;
            case R.id.angryButton:
                emotions.add(0, new Angry(comment));
                break;
            case R.id.scaredButton:
                emotions.add(0, new Scared(comment));
                break;
            case R.id.surprisedButton:
                emotions.add(0, new Surprised(comment));
                break;
        }
        adapter.notifyDataSetChanged();
        appendToFile();
        Toast toast = Toast.makeText(getApplicationContext(), "Added Emotion!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
