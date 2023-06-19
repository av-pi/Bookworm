package com.example.bookworm;

import static com.example.bookworm.Book.BOOK_STATUS_ABANDONED;
import static com.example.bookworm.Book.BOOK_STATUS_FINISHED;
import static com.example.bookworm.Book.BOOK_STATUS_INTERESTED;
import static com.example.bookworm.Book.BOOK_STATUS_READING;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ViewBookActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView bookImage;
    private Button addReading, wantToRead, finishedReading, abandoned, homePage;
    private TextView bookTitle, bookAuthor, bookLongDescription;

    public static final String CLICKED_BOOK = "clickedBook";

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

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
            this.book = (Book) intent.getSerializableExtra(CLICKED_BOOK);
            if (this.book != null) {
                this.setData();
                this.handleShelfButtonVisibility();
            }
        }
    }

    /**
     * Move book to the specified shelf
     * @param shelf
     */
    private void addBookToShelf(String shelf) {
        book.setStatus(shelf);
        AppDatabase db = AppDatabase.getDbInstance(this);
        db.bookDao().update(book);
    }

    /**
     * Click event handler to update shelf of book
     * @param v The view that was clicked.
     */
    public void onClick(View v) {

        //AppDatabase db = AppDatabase.getDbInstance(this);
        switch (v.getId()) {

            case R.id.btn_add_reading:
                if (v.isEnabled()) {
                    addBookToShelf(BOOK_STATUS_READING);
                    Toast.makeText(this, "Added " + book.getName() + " to reading shelf", Toast.LENGTH_SHORT).show();
                    handleShelfButtonVisibility();
                }
                break;

            case R.id.btn_add_interested:
                if (v.isEnabled()) {
                    addBookToShelf(BOOK_STATUS_INTERESTED);
                    Toast.makeText(this, "Added " + book.getName() + " to interested shelf", Toast.LENGTH_SHORT).show();
                    handleShelfButtonVisibility();
                }
                break;

            case R.id.btn_add_finished:
                if (v.isEnabled()) {
                    addBookToShelf(BOOK_STATUS_FINISHED);
                    Toast.makeText(this, "Added " + book.getName() + " to finished shelf", Toast.LENGTH_SHORT).show();
                    handleShelfButtonVisibility();
                }
                break;

            case R.id.btn_add_abandoned:
                if (v.isEnabled()) {
                    addBookToShelf(BOOK_STATUS_ABANDONED);
                    Toast.makeText(this, "Added " + book.getName() + " to abandoned shelf", Toast.LENGTH_SHORT).show();
                    handleShelfButtonVisibility();
                }
                break;

            case R.id.btn_go_home:
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);

            default:
                break;
        }

    }

    /**
     * Initialise the UI components of the page
     */
    private void initViews() {
        bookImage = findViewById(R.id.book_image);

        addReading = findViewById(R.id.btn_add_reading);
        addReading.setOnClickListener(this);

        wantToRead = findViewById(R.id.btn_add_interested);
        wantToRead.setOnClickListener(this);

        finishedReading = findViewById(R.id.btn_add_finished);
        finishedReading.setOnClickListener(this);

        abandoned = findViewById(R.id.btn_add_abandoned);
        abandoned.setOnClickListener(this);

        bookTitle = findViewById(R.id.txt_title);
        bookAuthor = findViewById(R.id.txt_author);
        bookLongDescription = findViewById(R.id.txt_long_desc);

        homePage = findViewById(R.id.btn_go_home);
        homePage.setOnClickListener(this);

    }

    /**
     * Handle visibility of shelf buttons based on which shelf book is in
     */
    private void handleShelfButtonVisibility() {
        if (book.getStatus().equals(BOOK_STATUS_READING)) {
            addReading.setEnabled(false);

            wantToRead.setEnabled(true);
            finishedReading.setEnabled(true);
            abandoned.setEnabled(true);
        }

        if (book.getStatus().equals(BOOK_STATUS_INTERESTED)) {
            wantToRead.setEnabled(false);

            addReading.setEnabled(true);
            finishedReading.setEnabled(true);
            abandoned.setEnabled(true);
        }

        if (book.getStatus().equals(BOOK_STATUS_FINISHED)) {
            finishedReading.setEnabled(false);

            wantToRead.setEnabled(true);
            addReading.setEnabled(true);
            abandoned.setEnabled(true);
        }

        if (book.getStatus().equals(BOOK_STATUS_ABANDONED)) {
            abandoned.setEnabled(false);

            wantToRead.setEnabled(true);
            finishedReading.setEnabled(true);
            addReading.setEnabled(true);
        }

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
     */
    private void setData() {
        bookTitle.setText(book.getName());
        bookAuthor.setText(book.getAuthor());
        bookLongDescription.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageURL())
                .into(bookImage);
    }


}