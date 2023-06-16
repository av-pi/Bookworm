package com.example.bookworm;

import static com.example.bookworm.Book.BOOK_STATUS_ABANDONED;
import static com.example.bookworm.Book.BOOK_STATUS_FINISHED;
import static com.example.bookworm.Book.BOOK_STATUS_INTERESTED;
import static com.example.bookworm.Book.BOOK_STATUS_READING;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ViewBookActivity extends AppCompatActivity {
    private ImageView bookImage;
    private Button addReading, wantToRead, finishedReading, abandoned;
    private TextView bookTitle, bookAuthor, bookLongDescription;

    public static final String CLICKED_BOOK = "clickedBook";

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

//        String desc = "On the world called Hyperion, beyond the law of the Hegemony of Man, there waits the creature called the Shrike. There are those who worship it. There are those who fear it. And there are those who have vowed to destroy it. In the Valley of the Time Tombs, where huge, brooding structures move backward through time, the Shrike waits for them all. On the eve of Armageddon, with the entire galaxy at war, seven pilgrims set forth on a final voyage to Hyperion seeking the answers to the unsolved riddles of their lives. Each carries a desperate hope—and a terrible secret. And one may hold the fate of humanity in his hands.";
//
//        Book book = new Book(1,
//                "Hyperion",
//                "Dan Simmons",
//                "https://upload.wikimedia.org/wikipedia/en/7/73/Hyperion_cover.jpg",
//                "https://www.penguinrandomhouse.com/series/HYC/hyperion-cantos",
//                "On the eve of Armageddon, with the entire galaxy at war, seven pilgrims set forth on a final voyage to Hyperion seeking the answers to the unsolved riddles of their lives.",
//                desc);

        Intent intent = getIntent();

        if (intent != null) {
            Book book = (Book) intent.getSerializableExtra(CLICKED_BOOK);
            if (book != null) {
                this.setData(book);
                this.handleShelfButtons(book);
            }
        }
    }

    /**
     * Disable shelf buttons where the book already exists
     * @param book
     */
    private void handleShelfButtons(Book book) {
        if (book.getStatus().equals(BOOK_STATUS_READING)) {
            addReading.setEnabled(false);
        }

        if (book.getStatus().equals(BOOK_STATUS_INTERESTED)) {
            wantToRead.setEnabled(false);
        }

        if (book.getStatus().equals(BOOK_STATUS_FINISHED)) {
            finishedReading.setEnabled(false);
        }

        if (book.getStatus().equals(BOOK_STATUS_ABANDONED)) {
            abandoned.setEnabled(false);
        }

    }

    /**
     * Initialise the UI components of the page
     */
    private void initViews() {
        bookImage = findViewById(R.id.book_image);

        addReading = findViewById(R.id.btn_add_reading);
        wantToRead = findViewById(R.id.btn_add_interested);
        finishedReading = findViewById(R.id.btn_finished);
        abandoned = findViewById(R.id.btn_abandoned);

        bookTitle = findViewById(R.id.txt_title);
        bookAuthor = findViewById(R.id.txt_author);
        bookLongDescription = findViewById(R.id.txt_long_desc);

    }

    private boolean checkReadingStatus(Book book) {
        if (book.getStatus().equals(BOOK_STATUS_READING)) {
            return true;
        }
        return false;
    }

    private boolean checkInterestedStatus(Book book) {
        if (book.getStatus().equals(BOOK_STATUS_INTERESTED)) {
            return true;
        }
        return false;
    }

    private boolean checkFinishedStatus(Book book) {
        if (book.getStatus().equals(BOOK_STATUS_FINISHED)) {
            return true;
        }
        return false;
    }

    private boolean checkAbandonedStatus(Book book) {
        if (book.getStatus().equals(BOOK_STATUS_ABANDONED)) {
            return true;
        }
        return false;
    }

    /**
     * Populate UI elements with data about the current book
     * @param book
     */
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