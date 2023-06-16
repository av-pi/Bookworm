package com.example.bookworm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView allBooksRecView;
    private BooksRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new BooksRecViewAdapter(this);
        allBooksRecView = findViewById(R.id.all_books_rec_view);

        allBooksRecView.setAdapter(adapter);
        allBooksRecView.setLayoutManager(new LinearLayoutManager(this));

        //ArrayList<Book> books = new ArrayList<>();

        List<Book> books = retrieveAllBooks();

        // TODO: delete commented code
//        Book book = new Book(1,
//                "Dune",
//                "Frank Herbert",
//                "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/4dbeb2105957829.5f851d78e22f1.jpg",
//                "https://openlibrary.org/works/OL893415W/Dune",
//                "A mythic and emotionally charged hero's journey.",
//                "Long description");
//
//        addBookToDb(book);
//        books.add(book);

//        books.add(new Book(1,
//                "Dune",
//                "Frank Herbert",
//                "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/4dbeb2105957829.5f851d78e22f1.jpg",
//                "https://openlibrary.org/works/OL893415W/Dune",
//                "A mythic and emotionally charged hero's journey.",
//                "Long description"));
//
//        books.add(new Book(1,
//                "Hyperion",
//                "Dan Simmons",
//                "https://upload.wikimedia.org/wikipedia/en/7/73/Hyperion_cover.jpg",
//                "https://www.penguinrandomhouse.com/series/HYC/hyperion-cantos",
//                "On the eve of Armageddon, with the entire galaxy at war, seven pilgrims set forth on a final voyage to Hyperion seeking the answers to the unsolved riddles of their lives.",
//                "Long description"));

        adapter.setBookList((ArrayList<Book>) books);

    }

    private void addBookToDb(Book book) {
        AppDatabase db = AppDatabase.getDbInstance(this);
        db.bookDao().insert(book);
    }

    private List<Book> retrieveAllBooks() {
        AppDatabase db = AppDatabase.getDbInstance(this);
        return db.bookDao().getAll();
    }
}