package com.example.bookworm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button allBooks, currentBooks, readBooks, wishList, abandoned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_all_books:
                Intent intent = new Intent(v.getContext(), AllBooksActivity.class);
                startActivity(intent);
                break;

            default:
                break;


        }
    }

    private void initViews() {
        allBooks = findViewById(R.id.btn_all_books);
        allBooks.setOnClickListener(this);

        currentBooks = findViewById(R.id.btn_current_books);
        readBooks = findViewById(R.id.btn_read_books);
        wishList = findViewById(R.id.btn_want_to_read);
        abandoned = findViewById(R.id.btn_abandoned);
    }


}