package com.example.bookworm;

import static com.example.bookworm.Book.BOOK_STATUS_FINISHED;
import static com.example.bookworm.Book.BOOK_STATUS_INTERESTED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FinishedBooksActivity extends AppCompatActivity {

    private RecyclerView finishedBooksRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_books);

        finishedBooksRecView = findViewById(R.id.finished_books_rec_view);

        BooksRecViewAdapter adapter = new BooksRecViewAdapter(this);
        finishedBooksRecView.setAdapter(adapter);

        finishedBooksRecView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase db = AppDatabase.getDbInstance(this);
        ArrayList<Book> booksList = (ArrayList<Book>) db.bookDao().getShelf(BOOK_STATUS_FINISHED);

        adapter.setBookList(booksList);
    }
}