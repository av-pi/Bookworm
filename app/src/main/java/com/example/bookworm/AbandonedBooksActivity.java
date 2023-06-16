package com.example.bookworm;

import static com.example.bookworm.Book.BOOK_STATUS_ABANDONED;
import static com.example.bookworm.Book.BOOK_STATUS_INTERESTED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AbandonedBooksActivity extends AppCompatActivity {

    private RecyclerView abandonedBooksRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abandoned_books);

        abandonedBooksRecView = findViewById(R.id.abandoned_books_rec_view);

        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this);
        abandonedBooksRecView.setAdapter(adapter);

        abandonedBooksRecView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase db = AppDatabase.getDbInstance(this);
        ArrayList<Book> booksList = (ArrayList<Book>) db.bookDao().getShelf(BOOK_STATUS_ABANDONED);

        adapter.setBookList(booksList);
    }
}