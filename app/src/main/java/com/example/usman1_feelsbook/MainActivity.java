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

/**
 * MainActivity responsible for the history, feelings count, and addition of feelings
 */
public class MainActivity extends AppCompatActivity {

    private String FILENAME = "file.sav";
    private static ArrayList<Emotion> emotions = new ArrayList<>();
    public static Boolean modified = false;
    private EditText commentText;
    private RecyclerView history;
    private HistoryAdapter adapter;
    private RecyclerView.LayoutManager histLayoutManager;

    private TextView loveCount, joyCount, sadCount, angryCount, surprisedCount, scaredCount;
    int lovec, joyc, sadc, angryc, surprisedc, scaredc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (modified) { // Boolean set by EditActivity --> Must push changes
            appendToFile();
            modified = false;
        }
        loadFromFile(); // Load into list emotions

        commentText = findViewById(R.id.commentBox);
        history = findViewById(R.id.historyView);

        // Layout manager to display RecyclerView list items
        histLayoutManager = new LinearLayoutManager(this);
        history.setLayoutManager(histLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        clearComment();

        // Set emotions list and adapter to RecyclerView history
        loadFromFile();
        adapter = new HistoryAdapter(emotions);
        history.setAdapter(adapter);
    }

    /**
     * Sets the TextViews underneath emotion images to the number of emotions in history
     */
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

    /**
     * Load ArrayList emotions from memory, and re-count
     */
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
        // Sort array from newest to oldest emotion
        Collections.sort(emotions);
        Collections.reverse(emotions);
    }

    /**
     * Store ArrayList emotions into memory, and re-count
     */
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
        // Sort array from newest to oldest emotion
        Collections.sort(emotions);
        Collections.reverse(emotions);
    }

    private String getCommentText() {
        return commentText.getText().toString();
    }

    private void clearComment() {
        commentText.setText("");
    }

    /**
     * Add a new Emotion object to ArrayList emotions
     *
     * @param view Emoji Button clicked
     */
    public void addFeel(View view) {
        String comment = getCommentText();
        clearComment(); // May be ""
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

    /**
     * @param num index into ArrayList emotions
     * @return Emotion object at param num
     */
    public static Emotion getEmotionFromList(int num) {
        return emotions.get(num);
    }

    /**
     *
     * @param num index into ArrayList emotions
     * @param emotion set=true: new emotion to replace, set=false: emotion to remove
     * @param set set=true: modify, set=false: remove
     */
    public static void setOrKillEmotion(int num, Emotion emotion, Boolean set) {
        if (set)
            emotions.set(num, emotion);
        else // kill
            emotions.remove(emotion);
    }
}
