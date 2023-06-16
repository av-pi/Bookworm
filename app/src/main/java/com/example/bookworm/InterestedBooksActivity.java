package com.example.bookworm;

import static com.example.bookworm.Book.BOOK_STATUS_INTERESTED;
import static com.example.bookworm.Book.BOOK_STATUS_READING;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class InterestedBooksActivity extends AppCompatActivity {

    private RecyclerView interestedBooksRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_books);

        interestedBooksRecView = findViewById(R.id.interested_books_rec_view);

        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this);
        interestedBooksRecView.setAdapter(adapter);

        interestedBooksRecView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase db = AppDatabase.getDbInstance(this);
        ArrayList<Book> booksList = (ArrayList<Book>) db.bookDao().getShelf(BOOK_STATUS_INTERESTED);

        adapter.setBookList(booksList);
    }
}