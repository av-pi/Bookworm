package com.example.bookworm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {
    private ImageView bookImage;
    private Button addReading, wantToRead, finishedReading, abandoned;
    private TextView bookTitle, bookAuthor, bookLongDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
//        String desc = "On the world called Hyperion, beyond the law of the Hegemony of Man," + "\n " +
//                "there waits the creature called the Shrike. There are those who worship it." + "\n " +
//                "There are those who fear it. And there are those who have vowed to destroy it." + "\n" +
//                "In the Valley of the Time Tombs, where huge, brooding structures move backward through time," + "\n" +
//                " the Shrike waits for them all. On the eve of Armageddon, with the entire galaxy at war," + "\n" +
//                "seven pilgrims set forth on a final voyage to Hyperion seeking the answers to the unsolved" + "\n" +
//                "riddles of their lives. Each carries a desperate hope—and a terrible secret. And one may hold" + "\n" +
//                "the fate of humanity in his hands.";

        String desc = "On the world called Hyperion, beyond the law of the Hegemony of Man, there waits the creature called the Shrike. There are those who worship it. There are those who fear it. And there are those who have vowed to destroy it. In the Valley of the Time Tombs, where huge, brooding structures move backward through time, the Shrike waits for them all. On the eve of Armageddon, with the entire galaxy at war, seven pilgrims set forth on a final voyage to Hyperion seeking the answers to the unsolved riddles of their lives. Each carries a desperate hope—and a terrible secret. And one may hold the fate of humanity in his hands.";

        Book book = new Book(1,
                "Hyperion",
                "Dan Simmons",
                "https://upload.wikimedia.org/wikipedia/en/7/73/Hyperion_cover.jpg",
                "https://www.penguinrandomhouse.com/series/HYC/hyperion-cantos",
                "On the eve of Armageddon, with the entire galaxy at war, seven pilgrims set forth on a final voyage to Hyperion seeking the answers to the unsolved riddles of their lives.",
                desc);

        this.setData(book);
    }

    private void initViews() {
        bookImage = findViewById(R.id.book_image);

        addReading = findViewById(R.id.btn_add_reading);
        wantToRead = findViewById(R.id.btn_want_to_read);
        finishedReading = findViewById(R.id.btn_finished);
        abandoned = findViewById(R.id.btn_abandoned);

        bookTitle = findViewById(R.id.txt_title);
        bookAuthor = findViewById(R.id.txt_author);
        bookLongDescription = findViewById(R.id.txt_long_desc);

    }

    private void setData(Book book) {
        bookTitle.setText(book.getName());
        bookAuthor.setText(book.getAuthor());
        bookLongDescription.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageURL())
                .into(bookImage);
    }

}