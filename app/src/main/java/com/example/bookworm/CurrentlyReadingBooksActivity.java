package com.example.bookworm;

import static com.example.bookworm.Book.BOOK_STATUS_READING;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class CurrentlyReadingBooksActivity extends AppCompatActivity {

    private RecyclerView currentlyReadingBooksRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading_books);

        currentlyReadingBooksRecView = findViewById(R.id.currently_reading_books_rec_view);
        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this);
        currentlyReadingBooksRecView.setAdapter(adapter);

        currentlyReadingBooksRecView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase db = AppDatabase.getDbInstance(this);
        ArrayList<Book> booksList = (ArrayList<Book>) db.bookDao().getShelf(BOOK_STATUS_READING);

        adapter.setBookList(booksList);
    }
}